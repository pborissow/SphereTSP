package tr.edu.ege;
import java.util.ArrayList;
import java.util.Collections;
public class GA {
   public double m_mutationRate;
   private double m_crossoverRate;
   private int m_populationSize;
   private int m_generationSize;
   private int m_genomeSize;
   private double m_totalFitness;
   private String m_strFitness;
   public boolean m_elitism;
   private ArrayList m_thisGeneration;
   private ArrayList m_nextGeneration;
   private ArrayList m_fitnessTable;
   boolean flag = false;

   public GA() {
      this.InitialValues();
      this.m_mutationRate = 0.05D;
      this.m_crossoverRate = 0.8D;
      this.m_populationSize = 100;
      this.m_generationSize = 2000;
      this.m_strFitness = "";
   }

   public GA(double var1, double var3, int var5, int var6, int var7) {
      this.InitialValues();
      this.m_mutationRate = var3;
      this.m_crossoverRate = var1;
      this.m_populationSize = var5;
      this.m_generationSize = var6;
      this.m_genomeSize = var7;
      this.m_strFitness = "";
   }

   public GA(int var1) {
      this.InitialValues();
      this.m_genomeSize = var1;
   }

   public void InitialValues() {
      this.m_elitism = false;
   }

   public void Go() {
      this.m_fitnessTable = new ArrayList();
      this.m_thisGeneration = new ArrayList(this.m_generationSize);
      this.m_nextGeneration = new ArrayList(this.m_generationSize);
      this.CreateGenomes();
      this.RankPopulation();

      for(int var1 = 0; var1 < this.m_generationSize && !this.flag; ++var1) {
         this.CreateNextGeneration();
         this.RankPopulation();
      }

   }

   private int RouletteSelection() {
      double var1 = Math.random() * this.m_totalFitness;
      int var3 = -1;
      int var5 = 0;
      int var6 = this.m_populationSize - 1;
      int var4 = (var6 - var5) / 2;

      while(var3 == -1 && var5 <= var6) {
         if (var1 < (Double)((Double)this.m_fitnessTable.get(var4))) {
            var6 = var4;
         } else if (var1 > (Double)((Double)this.m_fitnessTable.get(var4))) {
            var5 = var4;
         }

         var4 = (var5 + var6) / 2;
         if (var6 - var5 == 1) {
            var3 = var6;
         }
      }

      return var3;
   }

   private void RankPopulation() {
      this.m_totalFitness = 0.0D;

      for(int var1 = 0; var1 < this.m_populationSize; ++var1) {
         Genome var2 = (Genome)this.m_thisGeneration.get(var1);
         var2.m_fitness = theActualFunction(var2.Genes());
         if (var2.m_fitness == 0.0D) {
            this.flag = true;
         }

         this.m_totalFitness += var2.m_fitness;
      }

      Collections.sort(this.m_thisGeneration, new GenomeComparer());
      double var4 = 0.0D;
      this.m_fitnessTable.clear();

      for(int var3 = 0; var3 < this.m_populationSize; ++var3) {
         var4 += ((Genome)this.m_thisGeneration.get(var3)).m_fitness;
         this.m_fitnessTable.add(var4);
      }

   }

   private void CreateGenomes() {
      for(int var1 = 0; var1 < this.m_populationSize; ++var1) {
         Genome var2 = new Genome(this.m_genomeSize);
         this.m_thisGeneration.add(var2);
      }

   }

   private void CreateNextGeneration() {
      this.m_nextGeneration.clear();
      Genome var1 = null;
      if (this.m_elitism) {
         var1 = (Genome)this.m_thisGeneration.get(this.m_populationSize - 1);
      }

      int var2;
      for(var2 = 0; var2 < this.m_populationSize; var2 += 2) {
         int var3 = this.RouletteSelection();
         int var4 = this.RouletteSelection();
         Genome var5 = (Genome)this.m_thisGeneration.get(var3);
         Genome var6 = (Genome)this.m_thisGeneration.get(var4);
         Genome var7 = new Genome(this.m_genomeSize, false);
         Genome var8 = new Genome(this.m_genomeSize, false);
         if (Math.random() < this.m_crossoverRate) {
            var5.Crossover(var6, var7, var8);
         } else {
            for(int var9 = 0; var9 < this.m_genomeSize; ++var9) {
               var7.Genes()[var9] = var5.Genes()[var9];
               var8.Genes()[var9] = var6.Genes()[var9];
            }
         }

         var7.Mutate(this.m_mutationRate);
         var8.Mutate(this.m_mutationRate);
         this.m_nextGeneration.add(var7);
         this.m_nextGeneration.add(var8);
      }

      if (this.m_elitism && var1 != null) {
         this.m_nextGeneration.set(0, var1);
      }

      this.m_thisGeneration.clear();

      for(var2 = 0; var2 < this.m_populationSize; ++var2) {
         this.m_thisGeneration.add(this.m_nextGeneration.get(var2));
      }

   }

   public double GetBest(int[] var1) {
      Genome var2 = (Genome)this.m_thisGeneration.get(this.m_populationSize - 1);
      var2.GetValues(var1);
      return var2.m_fitness;
   }

   public double GetWorst(int[] var1) {
      Genome var2 = (Genome)this.m_thisGeneration.get(0);
      var2.GetValues(var1);
      return var2.m_fitness;
   }

   public static double theActualFunction(int[] var0) {
      double var1 = 0.0D;

      for(int var3 = 0; var3 < var0.length - 1; ++var3) {
         var1 += AWTInteractionBehavior.distances.dist[var0[var3]][var0[var3 + 1]];
      }

      var1 += AWTInteractionBehavior.distances.dist[var0[0]][var0[var0.length - 1]];
      return -var1;
   }
}