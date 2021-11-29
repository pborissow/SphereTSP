package tr.edu.ege;



import java.util.ArrayList;

class Genome {
   public int[] m_genes;
   public int m_length;
   public double m_fitness;

   public Genome() {
   }

   public Genome(int var1) {
      this.m_length = var1;
      this.m_genes = new int[var1];
      this.CreateGenes();
   }

   public Genome(int var1, boolean var2) {
      this.m_length = var1;
      this.m_genes = new int[var1];
      if (var2) {
         this.CreateGenes();
      }

   }

   public Genome(int[] var1) {
      this.m_length = var1.length;
      this.m_genes = new int[this.m_length];

      for(int var2 = 0; var2 < this.m_length; ++var2) {
         this.m_genes[var2] = var1[var2];
      }

   }

   public void genList() {
      for(int var1 = 0; var1 < this.m_length; ++var1) {
         System.out.print(this.m_genes[var1]);
      }

      System.out.println();
   }

   private void CreateGenes() {
      ArrayList var1 = new ArrayList();

      int var2;
      for(var2 = 0; var2 < this.m_length; ++var2) {
         var1.add(var2);
      }

      for(var2 = 0; var2 < this.m_length; ++var2) {
         int var3 = (int)(Math.random() * (double)(this.m_length - var2));
         this.m_genes[var2] = (Integer)((Integer)var1.get(var3));
         var1.remove(var3);
      }

   }

   public void Crossover(Genome var1, Genome var2, Genome var3) {
      int var4 = (int)(Math.random() * (double)this.m_length);
      int var5 = (int)(Math.random() * (double)this.m_length);
      int var6;
      if (var4 > var5) {
         var6 = var4;
         var4 = var5;
         var5 = var6;
      }

      for(var6 = 0; var6 < this.m_length; ++var6) {
         var2.m_genes[var6] = this.m_genes[var6];
         var3.m_genes[var6] = var1.m_genes[var6];
      }

      int[] var10 = new int[var5 - var4 + 1];
      int var7 = 0;

      int var8;
      for(var8 = 0; var8 < this.m_length; ++var8) {
         for(int var9 = var4; var9 <= var5; ++var9) {
            if (var1.m_genes[var8] == this.m_genes[var9]) {
               var10[var7] = var1.m_genes[var8];
               var3.m_genes[var8] = this.m_genes[var4 + var7];
               ++var7;
               break;
            }
         }
      }

      for(var8 = var4; var8 <= var5; ++var8) {
         var2.m_genes[var8] = var10[var8 - var4];
      }

   }

   public void Mutate(double var1) {
      int var3 = -1;
      int var4 = -1;
      double var5 = 0.0D;

      int var8;
      int var9;
      for(var8 = 0; var8 < this.m_length - 1; ++var8) {
         var9 = this.m_genes[var8];
         int var10 = this.m_genes[0];
         if (var8 < this.m_length - 1) {
            var10 = this.m_genes[var8 + 1];
         }

         for(int var11 = var8 + 1; var11 < this.m_length; ++var11) {
            int var12 = this.m_genes[var11];
            int var13 = this.m_genes[0];
            if (var11 < this.m_length - 1) {
               var13 = this.m_genes[var11 + 1];
            }

            double var14 = AWTInteractionBehavior.distances.dist[var9][var10] + AWTInteractionBehavior.distances.dist[var12][var13];
            double var16 = AWTInteractionBehavior.distances.dist[var9][var12] + AWTInteractionBehavior.distances.dist[var10][var13];
            if (var14 - var16 > var5) {
               var5 = var14 - var16;
               var3 = var8;
               var4 = var11;
            }
         }
      }

      if (var3 != -1) {
         var8 = var4;

         for(var9 = var3 + 1; var8 >= var9 + 1; ++var9) {
            int var7 = this.m_genes[var9];
            this.m_genes[var9] = this.m_genes[var8];
            this.m_genes[var8] = var7;
            --var8;
         }
      }

   }

   public int[] Genes() {
      return this.m_genes;
   }

   public void GetValues(int[] var1) {
      for(int var2 = 0; var2 < this.m_length; ++var2) {
         var1[var2] = this.m_genes[var2];
      }

   }
}

