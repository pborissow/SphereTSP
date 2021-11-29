package tr.edu.ege;

/* Decompiler 315ms, total 358ms, lines 518 */
import com.sun.j3d.utils.geometry.Box;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.media.j3d.Appearance;
import javax.media.j3d.Behavior;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.LineStripArray;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnBehaviorPost;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;

public class AWTInteractionBehavior extends Behavior implements ActionListener {
   private WakeupCriterion criterion;
   Appearance nitelikKure;
   Points points;
   BranchGroup b2;
   int state;
   public static Distances distances;

   public AWTInteractionBehavior(Appearance var1, int var2) {
      this.nitelikKure = var1;
      this.state = var2;
   }

   public AWTInteractionBehavior(Points var1, BranchGroup var2, int var3) {
      this.points = var1;
      this.state = var3;
      this.b2 = var2;
   }

   public void initialize() {
      this.criterion = new WakeupOnBehaviorPost(this, 500);
      this.wakeupOn(this.criterion);
   }

   public void processStimulus(Enumeration var1) {
      TransparencyAttributes var2;
      String var3;
      double var4;
      double var6;
      String var23;
      if (this.state == 0) {
         var2 = new TransparencyAttributes();
         var2.setTransparency(0.7F);
         var2.setTransparencyMode(2);
         this.nitelikKure.setTransparencyAttributes(var2);
      } else if (this.state == 1) {
         var2 = new TransparencyAttributes();
         var2.setTransparency(0.0F);
         var2.setTransparencyMode(4);
         this.nitelikKure.setTransparencyAttributes(var2);
      } else if (this.state == 2) {
         var23 = SphereTSP.tfu.getText();
         var3 = SphereTSP.tfv.getText();
         var4 = 0.0D;
         var6 = 0.0D;

         try {
            var4 = Double.parseDouble(var23);
            var6 = Double.parseDouble(var3);
         } catch (NumberFormatException var22) {
            SphereTSP.tfu.setText("0.0");
            SphereTSP.tfv.setText("0.0");
         }

         this.points.add(new PointSphere(var4, var6));
         this.addPoint((PointSphere)this.points.lastElement());
         this.setButtonsAdd();
         if (SphereTSP.bgP != null) {
            SphereTSP.bgP.detach();
         }

         this.refreshUV();
         SphereTSP.lnp.setText("N:" + this.points.size());
      } else if (this.state == 3) {
         double var24 = Math.random();
         var4 = Math.random();
         this.points.add(new PointSphere(var24, var4));
         this.addPoint((PointSphere)this.points.lastElement());
         this.setButtonsAdd();
         if (SphereTSP.bgP != null) {
            SphereTSP.bgP.detach();
         }

         this.refreshUV();
         SphereTSP.lnp.setText("N:" + this.points.size());
      } else if (this.state == 4) {
         if (SphereTSP.pointCurrent != null) {
            SphereTSP.pointCurrent.bg.detach();
            this.points.remove(SphereTSP.pointCurrent);
            if (SphereTSP.bgP != null) {
               SphereTSP.bgP.detach();
            }
         }

         if (this.points.size() > 0) {
            SphereTSP.pointCurrent = (PointSphere)this.points.lastElement();
            this.changeColor(SphereTSP.appRed);
            this.refreshUV();
         }

         SphereTSP.lnp.setText("N:" + this.points.size());
         if (this.points.size() == 1) {
            SphereTSP.bNext.setEnabled(false);
            SphereTSP.bPrev.setEnabled(false);
         }

         if (this.points.size() == 0) {
            SphereTSP.bDelete.setEnabled(false);
            SphereTSP.buPlus.setEnabled(false);
            SphereTSP.bvPlus.setEnabled(false);
            SphereTSP.buMinus.setEnabled(false);
            SphereTSP.bvMinus.setEnabled(false);
         }
      }

      if (this.state == 5 && SphereTSP.pointCurrent != null && this.points.size() > 1) {
         this.changeColor(SphereTSP.appBlack);
         SphereTSP.pointNo = ++SphereTSP.pointNo < this.points.size() ? SphereTSP.pointNo : 0;
         SphereTSP.pointCurrent = (PointSphere)this.points.elementAt(SphereTSP.pointNo);
         this.changeColor(SphereTSP.appRed);
         this.refreshUV();
      }

      if (this.state == 6 && SphereTSP.pointCurrent != null && this.points.size() > 1) {
         this.changeColor(SphereTSP.appBlack);
         SphereTSP.pointNo = --SphereTSP.pointNo < 0 ? this.points.size() - 1 : SphereTSP.pointNo;
         SphereTSP.pointCurrent = (PointSphere)this.points.elementAt(SphereTSP.pointNo);
         this.changeColor(SphereTSP.appRed);
         this.refreshUV();
      }

      if (this.state == 7 && SphereTSP.pointCurrent != null) {
         SphereTSP.pointCurrent.bg.detach();
         SphereTSP.pointCurrent.convertToXYZ(SphereTSP.pointCurrent.u + 0.02D, SphereTSP.pointCurrent.v);
         this.addPointBranch(SphereTSP.pointCurrent, SphereTSP.appRed);
         if (SphereTSP.bgP != null) {
            SphereTSP.bgP.detach();
         }

         this.refreshUV();
      }

      if (this.state == 8 && SphereTSP.pointCurrent != null) {
         SphereTSP.pointCurrent.bg.detach();
         SphereTSP.pointCurrent.convertToXYZ(SphereTSP.pointCurrent.u - 0.02D, SphereTSP.pointCurrent.v);
         this.addPointBranch(SphereTSP.pointCurrent, SphereTSP.appRed);
         if (SphereTSP.bgP != null) {
            SphereTSP.bgP.detach();
         }

         this.refreshUV();
      }

      if (this.state == 9 && SphereTSP.pointCurrent != null) {
         SphereTSP.pointCurrent.bg.detach();
         SphereTSP.pointCurrent.convertToXYZ(SphereTSP.pointCurrent.u, SphereTSP.pointCurrent.v + 0.02D);
         this.addPointBranch(SphereTSP.pointCurrent, SphereTSP.appRed);
         if (SphereTSP.bgP != null) {
            SphereTSP.bgP.detach();
         }

         this.refreshUV();
      }

      if (this.state == 10 && SphereTSP.pointCurrent != null) {
         SphereTSP.pointCurrent.bg.detach();
         SphereTSP.pointCurrent.convertToXYZ(SphereTSP.pointCurrent.u, SphereTSP.pointCurrent.v - 0.02D);
         this.addPointBranch(SphereTSP.pointCurrent, SphereTSP.appRed);
         if (SphereTSP.bgP != null) {
            SphereTSP.bgP.detach();
         }

         this.refreshUV();
      }

      if (this.state == 11 && SphereTSP.pointCurrent != null) {
         var23 = SphereTSP.tfu.getText();
         var3 = SphereTSP.tfv.getText();
         var4 = 0.0D;
         var6 = 0.0D;

         try {
            var4 = Double.parseDouble(var23);
            var6 = Double.parseDouble(var3);
         } catch (NumberFormatException var21) {
            return;
         }

         SphereTSP.pointCurrent.bg.detach();
         SphereTSP.pointCurrent.convertToXYZ(var4, var6);
         this.addPointBranch(SphereTSP.pointCurrent, SphereTSP.appRed);
         if (SphereTSP.bgP != null) {
            SphereTSP.bgP.detach();
         }

         this.refreshUV();
      }

      int var25;
      int var26;
      if (this.state == 12) {
         var25 = this.points.size();
         if (var25 <= 1) {
            SphereTSP.tfTourLength.setText("0");
         } else {
            if (SphereTSP.bgP != null) {
               SphereTSP.bgP.detach();
            }

            distances = new Distances(this.points);
            String var9 = SphereTSP.tfGenerationSize.getText();

            try {
               var26 = Integer.parseInt(var9);
            } catch (NumberFormatException var20) {
               var26 = 1024;
            }

            if (var26 > 65536 || var26 < 1) {
               var26 = 1024;
            }

            SphereTSP.tfGenerationSize.setText("" + var26);
            var9 = SphereTSP.tfPopulationSize.getText();

            int var27;
            try {
               var27 = Integer.parseInt(var9);
            } catch (NumberFormatException var19) {
               var27 = 250;
            }

            if (var27 > 65536 || var27 < 3) {
               var27 = 250;
            }

            SphereTSP.tfPopulationSize.setText("" + var27);
            var9 = SphereTSP.tfCRate.getText();

            double var5;
            try {
               var5 = Double.parseDouble(var9);
            } catch (NumberFormatException var18) {
               var5 = 0.8D;
            }

            if (var5 > 1.0D || var5 < 0.0D) {
               var5 = 0.8D;
            }

            SphereTSP.tfCRate.setText("" + var5);
            var9 = SphereTSP.tfMutationRate.getText();

            double var7;
            try {
               var7 = Double.parseDouble(var9);
            } catch (NumberFormatException var17) {
               var7 = 0.05D;
            }

            if (var7 > 1.0D || var7 < 0.0D) {
               var7 = 0.05D;
            }

            SphereTSP.tfMutationRate.setText("" + var7);
            GA var10 = new GA(var5, var7, var27, var26, var25);
            var10.m_elitism = true;
            var10.Go();
            int[] var11 = new int[var25];
            double var12 = 10000.0D;
            var12 = var10.GetBest(var11);
            SphereTSP.tfTourLength.setText("" + -var12);
            SphereTSP.bgP = new BranchGroup();
            SphereTSP.bgP.setCapability(17);
            SphereTSP.bgP.setCapability(12);
            SphereTSP.bgP.setCapability(13);

            for(int var14 = 0; var14 < var11.length; ++var14) {
               PointSphere var15 = (PointSphere)this.points.elementAt(var11[var14]);
               PointSphere var16 = (PointSphere)this.points.elementAt(var11[0]);
               if (var14 != var11.length - 1) {
                  var16 = (PointSphere)this.points.elementAt(var11[var14 + 1]);
               }

               this.drawArc(var15, var16);
            }

            this.b2.addChild(SphereTSP.bgP);
         }
      }

      if (this.state == 13) {
         var25 = this.points.size();

         for(var26 = 0; var26 < var25; ++var26) {
            ((PointSphere)this.points.elementAt(0)).bg.detach();
            this.points.remove(0);
         }

         if (SphereTSP.bgP != null) {
            SphereTSP.bgP.detach();
         }

         SphereTSP.pointCurrent = null;
         SphereTSP.lnp.setText("N:0");
         SphereTSP.bNext.setEnabled(false);
         SphereTSP.bPrev.setEnabled(false);
         SphereTSP.bDelete.setEnabled(false);
         SphereTSP.buPlus.setEnabled(false);
         SphereTSP.bvPlus.setEnabled(false);
         SphereTSP.buMinus.setEnabled(false);
         SphereTSP.bvMinus.setEnabled(false);
      }

      if (this.state == 14) {
         SphereTSP.flagLines = !SphereTSP.flagLines;
         SphereTSP.lines(SphereTSP.donusumGrubu);
      }

      this.wakeupOn(this.criterion);
   }

   public void actionPerformed(ActionEvent var1) {
      this.postId(500);
   }

   public void drawArc(PointSphere var1, PointSphere var2) {
      Appearance var3 = new Appearance();
      ColoringAttributes var4 = new ColoringAttributes();
      var4.setColor(0.0F, 1.0F, 0.0F);
      var4.setShadeModel(3);
      var3.setColoringAttributes(var4);
      LineAttributes var5 = new LineAttributes();
      var5.setLineWidth(5.0F);
      var5.setLineAntialiasingEnable(true);
      var3.setLineAttributes(var5);
      Point3f[] var6 = new Point3f[51];
      double var7 = Math.sqrt(var1.x * var1.x + var1.y * var1.y + var1.z * var1.z);
      double var9 = Math.sqrt(var2.x * var2.x + var2.y * var2.y + var2.z * var2.z);
      double var11 = var1.x * var2.x + var1.y * var2.y + var1.z * var2.z;
      double var13 = Math.acos(var11 / (var7 * var9));
      double var15;
      double var17;
      double var19;
      if (var13 >= 2.792526803190927D) {
         var15 = var1.y * var2.z - var1.z * var2.y;
         var17 = var1.z * var2.x - var1.x * var2.z;
         var19 = var1.x * var2.y - var1.y * var2.x;
         double var59 = var1.x;
         double var23 = var1.y;
         double var25 = var1.z;
         double var27 = Math.sqrt(var15 * var15 + var17 * var17 + var19 * var19);
         double var29 = var15 * var15 + var17 * var17 + var19 * var19;
         double var31 = (var15 * (var15 * var59 + var17 * var23 + var19 * var25) + (var59 * (var17 * var17 + var19 * var19) - var15 * (var17 * var23 + var19 * var25)) * Math.cos(var13) + var27 * (-var19 * var23 + var17 * var25) * Math.sin(var13)) / var29;
         double var33 = (var17 * (var15 * var59 + var17 * var23 + var19 * var25) + (var23 * (var15 * var15 + var19 * var19) - var17 * (var15 * var59 + var19 * var25)) * Math.cos(var13) + var27 * (var19 * var59 - var15 * var25) * Math.sin(var13)) / var29;
         double var35 = (var19 * (var15 * var59 + var17 * var23 + var19 * var25) + (var25 * (var15 * var15 + var17 * var17) - var19 * (var15 * var59 + var17 * var23)) * Math.cos(var13) + var27 * (-var17 * var59 - var15 * var23) * Math.sin(var13)) / var29;
         Math.sqrt((var2.x - var31) * (var2.x - var31) + (var2.y - var33) * (var2.y - var33) + (var2.z - var35) * (var2.z - var35));
         double var39 = (var15 * (var15 * var59 + var17 * var23 + var19 * var25) + (var59 * (var17 * var17 + var19 * var19) - var15 * (var17 * var23 + var19 * var25)) * Math.cos(-var13) + var27 * (-var19 * var23 + var17 * var25) * Math.sin(-var13)) / var29;
         double var41 = (var17 * (var15 * var59 + var17 * var23 + var19 * var25) + (var23 * (var15 * var15 + var19 * var19) - var17 * (var15 * var59 + var19 * var25)) * Math.cos(-var13) + var27 * (var19 * var59 - var15 * var25) * Math.sin(-var13)) / var29;
         double var43 = (var19 * (var15 * var59 + var17 * var23 + var19 * var25) + (var25 * (var15 * var15 + var17 * var17) - var19 * (var15 * var59 + var17 * var23)) * Math.cos(-var13) + var27 * (-var17 * var59 - var15 * var23) * Math.sin(-var13)) / var29;
         Math.sqrt((var2.x - var39) * (var2.x - var39) + (var2.y - var41) * (var2.y - var41) + (var2.z - var43) * (var2.z - var43));
         double var47 = var13 / 2.0D;
         var31 = (var15 * (var15 * var59 + var17 * var23 + var19 * var25) + (var59 * (var17 * var17 + var19 * var19) - var15 * (var17 * var23 + var19 * var25)) * Math.cos(var47) + var27 * (-var19 * var23 + var17 * var25) * Math.sin(var47)) / var29;
         var33 = (var17 * (var15 * var59 + var17 * var23 + var19 * var25) + (var23 * (var15 * var15 + var19 * var19) - var17 * (var15 * var59 + var19 * var25)) * Math.cos(var47) + var27 * (var19 * var59 - var15 * var25) * Math.sin(var47)) / var29;
         var35 = (var19 * (var15 * var59 + var17 * var23 + var19 * var25) + (var25 * (var15 * var15 + var17 * var17) - var19 * (var15 * var59 + var17 * var23)) * Math.cos(var47) + var27 * (-var17 * var59 - var15 * var23) * Math.sin(var47)) / var29;
         double var49 = Math.sqrt(var31 * var31 + var33 * var33 + var35 * var35);
         double var51 = Math.sqrt(var2.x * var2.x + var2.y * var2.y + var2.z * var2.z);
         double var53 = var31 * var2.x + var33 * var2.y + var35 * var2.z;
         double var55 = Math.acos(var53 / (var49 * var51));
         if (var55 < 1.5707963267948966D) {
            this.drawArc(var1, var31, var33, var35);
            this.drawArc(var2, var31, var33, var35);
         } else {
            var47 = -var47;
            var31 = (var15 * (var15 * var59 + var17 * var23 + var19 * var25) + (var59 * (var17 * var17 + var19 * var19) - var15 * (var17 * var23 + var19 * var25)) * Math.cos(var47) + var27 * (-var19 * var23 + var17 * var25) * Math.sin(var47)) / var29;
            var33 = (var17 * (var15 * var59 + var17 * var23 + var19 * var25) + (var23 * (var15 * var15 + var19 * var19) - var17 * (var15 * var59 + var19 * var25)) * Math.cos(var47) + var27 * (var19 * var59 - var15 * var25) * Math.sin(var47)) / var29;
            var35 = (var19 * (var15 * var59 + var17 * var23 + var19 * var25) + (var25 * (var15 * var15 + var17 * var17) - var19 * (var15 * var59 + var17 * var23)) * Math.cos(var47) + var27 * (-var17 * var59 - var15 * var23) * Math.sin(var47)) / var29;
            this.drawArc(var1, var31, var33, var35);
            this.drawArc(var2, var31, var33, var35);
         }

      } else {
         var15 = var2.x - var1.x;
         var17 = var2.y - var1.y;
         var19 = var2.z - var1.z;

         for(int var21 = 0; var21 <= 50; ++var21) {
            double var22 = var1.x + var15 * (double)var21 / 50.0D;
            double var24 = var1.y + var17 * (double)var21 / 50.0D;
            double var26 = var1.z + var19 * (double)var21 / 50.0D;
            double var28 = Math.sqrt(var22 * var22 + var24 * var24 + var26 * var26);
            var22 /= var28;
            var24 /= var28;
            var26 /= var28;
            var6[var21] = new Point3f((float)(0.52D * var22), (float)(0.52D * var24), (float)(0.52D * var26));
         }

         int[] var57 = new int[]{51};
         LineStripArray var16 = new LineStripArray(var6.length, 1, var57);
         var16.setCoordinates(0, var6);
         var16.setCoordinates(0, var6);
         Shape3D var58 = new Shape3D(var16, var3);
         TransformGroup var18 = new TransformGroup();
         var18.addChild(var58);
         SphereTSP.bgP.addChild(var18);
      }
   }

   public void drawArc(PointSphere var1, double var2, double var4, double var6) {
      Appearance var8 = new Appearance();
      ColoringAttributes var9 = new ColoringAttributes();
      var9.setColor(0.0F, 1.0F, 0.0F);
      var9.setShadeModel(3);
      var8.setColoringAttributes(var9);
      LineAttributes var10 = new LineAttributes();
      var10.setLineWidth(5.0F);
      var10.setLineAntialiasingEnable(true);
      var8.setLineAttributes(var10);
      Point3f[] var11 = new Point3f[51];
      double var12 = Math.sqrt(var1.x * var1.x + var1.y * var1.y + var1.z * var1.z);
      double var14 = Math.sqrt(var2 * var2 + var4 * var4 + var6 * var6);
      double var16 = var1.x * var2 + var1.y * var4 + var1.z * var6;
      double var18 = Math.acos(var16 / (var12 * var14));
      double var20 = var2 - var1.x;
      double var22 = var4 - var1.y;
      double var24 = var6 - var1.z;

      for(int var26 = 0; var26 <= 50; ++var26) {
         double var27 = var1.x + var20 * (double)var26 / 50.0D;
         double var29 = var1.y + var22 * (double)var26 / 50.0D;
         double var31 = var1.z + var24 * (double)var26 / 50.0D;
         double var33 = Math.sqrt(var27 * var27 + var29 * var29 + var31 * var31);
         var27 /= var33;
         var29 /= var33;
         var31 /= var33;
         var11[var26] = new Point3f((float)(0.52D * var27), (float)(0.52D * var29), (float)(0.52D * var31));
      }

      int[] var35 = new int[]{51};
      LineStripArray var36 = new LineStripArray(var11.length, 1, var35);
      var36.setCoordinates(0, var11);
      var36.setCoordinates(0, var11);
      Shape3D var28 = new Shape3D(var36, var8);
      TransformGroup var37 = new TransformGroup();
      var37.addChild(var28);
      SphereTSP.bgP.addChild(var37);
   }

   public double calcLen(double var1, double var3, double var5) {
      return var1 * var1 + var3 * var3 + var5 * var5;
   }

   public void setButtonsAdd() {
      if (this.points.size() == 1) {
         SphereTSP.bDelete.setEnabled(true);
         SphereTSP.buPlus.setEnabled(true);
         SphereTSP.bvPlus.setEnabled(true);
         SphereTSP.buMinus.setEnabled(true);
         SphereTSP.bvMinus.setEnabled(true);
      }

      if (this.points.size() == 2) {
         SphereTSP.bNext.setEnabled(true);
         SphereTSP.bPrev.setEnabled(true);
      }

   }

   public void refreshUV() {
      SphereTSP.tfu.setText("" + (float)SphereTSP.pointCurrent.u);
      SphereTSP.tfv.setText("" + (float)SphereTSP.pointCurrent.v);
      SphereTSP.lx.setText("x:" + (float)SphereTSP.pointCurrent.x);
      SphereTSP.ly.setText("y:" + (float)SphereTSP.pointCurrent.y);
      SphereTSP.lz.setText("z:" + (float)SphereTSP.pointCurrent.z);
   }

   public void addPoint(PointSphere var1) {
      if (this.points.size() > 1) {
         this.changeColor(SphereTSP.appBlack);
      }

      this.addPointBranch(var1, SphereTSP.appRed);
      SphereTSP.pointCurrent = var1;
      SphereTSP.pointNo = this.points.size() - 1;
   }

   public void changeColor(Appearance var1) {
      PointSphere var2 = SphereTSP.pointCurrent;
      var2.bg.detach();
      this.addPointBranch(var2, var1);
   }

   public void addPointBranch(PointSphere var1, Appearance var2) {
      BranchGroup var3 = new BranchGroup();
      var3.setCapability(17);
      var3.setCapability(12);
      var3.setCapability(13);
      TransformGroup var4 = new TransformGroup();
      Transform3D var5 = new Transform3D();
      var5.setTranslation(new Vector3f((float)(var1.x * 0.5D), (float)(var1.y * 0.5D), (float)(var1.z * 0.5D)));
      var4.setTransform(var5);
      Box var6 = new Box(0.01F, 0.01F, 0.01F, var2);
      var4.addChild(var6);
      var3.addChild(var4);
      this.b2.addChild(var3);
      var1.bg = var3;
   }
}
