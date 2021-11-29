package tr.edu.ege;

import java.util.Comparator;

class GenomeComparer implements Comparator {
   public int compare(Object var1, Object var2) {
      if (((Genome)var1).m_fitness > ((Genome)var2).m_fitness) {
         return 1;
      } else {
         return ((Genome)var1).m_fitness == ((Genome)var2).m_fitness ? 0 : -1;
      }
   }
}