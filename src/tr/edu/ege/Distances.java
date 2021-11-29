package tr.edu.ege;

public class Distances {
   double[][] dist;
   int n;
   Points p;

   public Distances() {
   }

   public Distances(Points var1) {
      this.p = var1;
      this.n = var1.size();
      this.dist = new double[this.n][this.n];

      int var2;
      int var3;
      for(var2 = 0; var2 < this.n; ++var2) {
         for(var3 = 0; var3 < var2; ++var3) {
            PointSphere var4 = (PointSphere)var1.elementAt(var2);
            PointSphere var5 = (PointSphere)var1.elementAt(var3);
            double var6 = 1.0D;
            double var8 = 1.0D;
            double var10 = var4.x * var5.x + var4.y * var5.y + var4.z * var5.z;
            double var12 = Math.acos(var10 / (var6 * var8));
            this.dist[var2][var3] = var12;
         }
      }

      for(var2 = 0; var2 < this.n; ++var2) {
         for(var3 = 0; var3 < var2; ++var3) {
            if (var2 != var3) {
               this.dist[var3][var2] = this.dist[var2][var3];
            }
         }
      }

   }

}