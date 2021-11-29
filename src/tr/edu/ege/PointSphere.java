package tr.edu.ege;

import javax.media.j3d.BranchGroup;

public class PointSphere {
       public double u;
       public double v;
       double x;
       double y;
       double z;
       BranchGroup bg;

       public PointSphere() {
       }

       public PointSphere(double var1, double var3) {
          this.u = var1;
          this.v = var3;
          this.convertToXYZ();
       }

       public void convertToXYZ() {
          this.x = Math.cos(6.283185307179586D * this.u) * Math.sin(3.141592653589793D * this.v);
          this.y = Math.sin(6.283185307179586D * this.u) * Math.sin(3.141592653589793D * this.v);
          this.z = Math.cos(3.141592653589793D * this.v);
       }

       public void convertToXYZ(double var1, double var3) {
          this.u = var1;
          this.v = var3;
          this.x = Math.cos(6.283185307179586D * var1) * Math.sin(3.141592653589793D * var3);
          this.y = Math.sin(6.283185307179586D * var1) * Math.sin(3.141592653589793D * var3);
          this.z = Math.cos(3.141592653589793D * var3);
       }
}