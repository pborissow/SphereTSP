package tr.edu.ege;


import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D.Double;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.LineAttributes;
import javax.media.j3d.LineStripArray;
import javax.media.j3d.Material;
import javax.media.j3d.PointLight;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Text3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.media.j3d.View;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3f;




public class SphereTSP extends Applet implements ActionListener {
   Appearance nitelikKure;
   Box box1;
   Box box2;
   Box box3;
   JRadioButton rdOn;
   JRadioButton rdOff;
   ButtonGroup rGrup;
   JLabel lGenerationSize;
   JLabel lGenomeSize;
   JLabel lMutationRate;
   JLabel lCRate;
   JLabel lPopulationSize;
   JLabel lu;
   JLabel lv;
   public static JLabel lx;
   public static JLabel ly;
   public static JLabel lz;
   public static JLabel lnp;
   public static JTextField tfGenerationSize;
   public static JTextField tfMutationRate;
   public static JTextField tfCRate;
   public static JTextField tfPopulationSize;
   public static JTextField tfu;
   public static JTextField tfv;
   public static JTextField tfTourLength;
   public static JButton bSolveTSP;
   public static JButton bAddRandom;
   public static JButton bAddUV;
   public static JButton bSetUV;
   public static JButton bDelete;
   public static JButton bNext;
   public static JButton bPrev;
   public static JButton buPlus;
   public static JButton buMinus;
   public static JButton bvPlus;
   public static JButton bvMinus;
   public static JButton bReset;
   public static JButton bAbout;
   public static JCheckBox bLines;
   private View view = null;
   public static Points points;
   public static int pointNo = -1;
   BranchGroup b2;
   public static PointSphere pointCurrent = null;
   public static BranchGroup bgP;
   public static BranchGroup bgL;
   public static ColoringAttributes caRed;
   public static ColoringAttributes caBlack;
   public static Appearance appRed;
   public static Appearance appBlack;
   public static boolean flagLines = true;
   public static TransformGroup donusumGrubu;



   public BranchGroup goruntuAgaciOlustur() {
      BranchGroup var1 = new BranchGroup();
      BoundingSphere var2 = new BoundingSphere(new Point3d(0.0D, 0.0D, 0.0D), 100.0D);
      donusumGrubu = new TransformGroup();
      donusumGrubu.setCapability(17);
      donusumGrubu.setCapability(18);
      donusumGrubu.setCapability(13);
      donusumGrubu.setCapability(14);
      this.b2 = new BranchGroup();
      this.b2.setCapability(17);
      this.b2.setCapability(18);
      this.b2.setCapability(13);
      this.b2.setCapability(14);
      donusumGrubu.addChild(this.b2);
      var1.addChild(donusumGrubu);
      Color3f var3 = new Color3f(0.9F, 0.9F, 0.9F);
      Background var4 = new Background(var3);
      var4.setApplicationBounds(var2);
      var1.addChild(var4);
      PointLight var5 = new PointLight();
      var5.setEnable(true);
      var5.setColor(new Color3f(0.5F, 0.5F, 0.5F));
      var5.setPosition(new Point3f(500.0F, 500.0F, 500.0F));
      var5.setInfluencingBounds(var2);
      var1.addChild(var5);
      Vector3f var6 = new Vector3f(-1.0F, -1.0F, -1.0F);
      DirectionalLight var7 = new DirectionalLight(new Color3f(0.9F, 0.9F, 0.9F), var6);
      var7.setInfluencingBounds(var2);
      var1.addChild(var7);
      Vector3f var8 = new Vector3f(1.0F, -1.0F, 1.0F);
      DirectionalLight var9 = new DirectionalLight(new Color3f(1.0F, 1.0F, 1.0F), var8);
      var9.setInfluencingBounds(var2);
      var1.addChild(var9);
      Color3f var10 = new Color3f(0.5F, 0.5F, 0.5F);
      AmbientLight var11 = new AmbientLight(var10);
      var11.setInfluencingBounds(var2);
      var1.addChild(var11);
      caRed = new ColoringAttributes();
      caRed.setColor(1.0F, 0.0F, 0.0F);
      caRed.setShadeModel(3);
      caBlack = new ColoringAttributes();
      caBlack.setColor(0.0F, 0.0F, 0.0F);
      caBlack.setShadeModel(3);
      appRed = new Appearance();
      appRed.setColoringAttributes(caRed);
      appBlack = new Appearance();
      appBlack.setColoringAttributes(caBlack);
      this.nitelikKure = new Appearance();
      this.nitelikKure.setCapability(10);
      this.nitelikKure.setCapability(11);
      Material var12 = new Material();
      var12.setAmbientColor(new Color3f(0.0F, 0.0F, 0.9F));
      var12.setDiffuseColor(0.7F, 0.7F, 0.7F);
      var12.setEmissiveColor(0.0F, 0.0F, 0.0F);
      var12.setSpecularColor(0.3F, 0.3F, 0.3F);
      var12.setShininess(50.0F);
      this.nitelikKure.setMaterial(var12);
      TransparencyAttributes var13 = new TransparencyAttributes();
      var13.setTransparency(0.7F);
      var13.setTransparencyMode(2);
      this.nitelikKure.setTransparencyAttributes(var13);
      donusumGrubu.addChild(new Sphere(0.5F, 3, 50, this.nitelikKure));
      this.eksenler(donusumGrubu);
      lines(donusumGrubu);
      AWTInteractionBehavior[] var14 = new AWTInteractionBehavior[15];
      var14[0] = new AWTInteractionBehavior(this.nitelikKure, 0);
      this.rdOn.addActionListener(var14[0]);
      var14[0].setSchedulingBounds(var2);
      donusumGrubu.addChild(var14[0]);
      var14[1] = new AWTInteractionBehavior(this.nitelikKure, 1);
      this.rdOff.addActionListener(var14[1]);
      var14[1].setSchedulingBounds(var2);
      donusumGrubu.addChild(var14[1]);
      var14[2] = new AWTInteractionBehavior(points, this.b2, 2);
      bAddUV.addActionListener(var14[2]);
      var14[2].setSchedulingBounds(var2);
      donusumGrubu.addChild(var14[2]);
      var14[3] = new AWTInteractionBehavior(points, this.b2, 3);
      bAddRandom.addActionListener(var14[3]);
      var14[3].setSchedulingBounds(var2);
      donusumGrubu.addChild(var14[3]);
      var14[4] = new AWTInteractionBehavior(points, this.b2, 4);
      bDelete.addActionListener(var14[4]);
      var14[4].setSchedulingBounds(var2);
      donusumGrubu.addChild(var14[4]);
      var14[5] = new AWTInteractionBehavior(points, this.b2, 5);
      bNext.addActionListener(var14[5]);
      var14[5].setSchedulingBounds(var2);
      donusumGrubu.addChild(var14[5]);
      var14[6] = new AWTInteractionBehavior(points, this.b2, 6);
      bPrev.addActionListener(var14[6]);
      var14[6].setSchedulingBounds(var2);
      donusumGrubu.addChild(var14[6]);
      var14[7] = new AWTInteractionBehavior(points, this.b2, 7);
      buPlus.addActionListener(var14[7]);
      var14[7].setSchedulingBounds(var2);
      donusumGrubu.addChild(var14[7]);
      var14[8] = new AWTInteractionBehavior(points, this.b2, 8);
      buMinus.addActionListener(var14[8]);
      var14[8].setSchedulingBounds(var2);
      donusumGrubu.addChild(var14[8]);
      var14[9] = new AWTInteractionBehavior(points, this.b2, 9);
      bvPlus.addActionListener(var14[9]);
      var14[9].setSchedulingBounds(var2);
      donusumGrubu.addChild(var14[9]);
      var14[10] = new AWTInteractionBehavior(points, this.b2, 10);
      bvMinus.addActionListener(var14[10]);
      var14[10].setSchedulingBounds(var2);
      donusumGrubu.addChild(var14[10]);
      var14[11] = new AWTInteractionBehavior(points, this.b2, 11);
      bSetUV.addActionListener(var14[11]);
      var14[11].setSchedulingBounds(var2);
      donusumGrubu.addChild(var14[11]);
      var14[12] = new AWTInteractionBehavior(points, this.b2, 12);
      bSolveTSP.addActionListener(var14[12]);
      var14[12].setSchedulingBounds(var2);
      donusumGrubu.addChild(var14[12]);
      var14[13] = new AWTInteractionBehavior(points, this.b2, 13);
      bReset.addActionListener(var14[13]);
      var14[13].setSchedulingBounds(var2);
      donusumGrubu.addChild(var14[13]);
      var14[14] = new AWTInteractionBehavior(points, this.b2, 14);
      bLines.addActionListener(var14[14]);
      var14[14].setSchedulingBounds(var2);
      donusumGrubu.addChild(var14[14]);

      for(int var15 = 0; var15 < points.size(); ++var15) {
         this.b2 = new BranchGroup();
         this.b2.setCapability(17);
         this.b2.setCapability(18);
         this.b2.setCapability(13);
         TransformGroup var16 = new TransformGroup();
         Appearance var17 = new Appearance();
         ColoringAttributes var18 = new ColoringAttributes();
         var18.setColor(0.0F, 0.0F, 0.0F);
         var18.setShadeModel(3);
         var17.setColoringAttributes(var18);
         Double var19 = (Double)points.elementAt(var15);
         Transform3D var20 = new Transform3D();
         double var21 = Math.cos(6.283185307179586D * var19.x) * Math.sin(3.141592653589793D * var19.y);
         double var23 = Math.sin(6.283185307179586D * var19.x) * Math.sin(3.141592653589793D * var19.y);
         double var25 = Math.cos(3.141592653589793D * var19.y);
         var20.setTranslation(new Vector3f((float)var21, (float)var23, (float)var25));
         var16.setTransform(var20);
         var16.addChild(new com.sun.j3d.utils.geometry.Box(0.1F, 0.1F, 0.1F, var17));
         this.b2.addChild(var16);
         donusumGrubu.addChild(this.b2);
      }

      MouseRotate var27 = new MouseRotate();
      var27.setTransformGroup(donusumGrubu);
      donusumGrubu.addChild(var27);
      var27.setSchedulingBounds(var2);
      MouseTranslate var28 = new MouseTranslate();
      var28.setTransformGroup(donusumGrubu);
      donusumGrubu.addChild(var28);
      var28.setSchedulingBounds(var2);
      MouseZoom var29 = new MouseZoom();
      var29.setTransformGroup(donusumGrubu);
      donusumGrubu.addChild(var29);
      var29.setSchedulingBounds(var2);
      var1.compile();
      return var1;
   }

   public static void lines(TransformGroup var0) {
      if (!flagLines) {
         if (bgL != null) {
            bgL.detach();
         }
      } else {
         bgL = new BranchGroup();
         bgL.setCapability(17);
         bgL.setCapability(12);
         bgL.setCapability(13);
         Appearance var1 = new Appearance();
         ColoringAttributes var2 = new ColoringAttributes();
         var2.setColor(0.0F, 1.0F, 1.0F);
         var2.setShadeModel(3);
         var1.setColoringAttributes(var2);
         LineAttributes var3 = new LineAttributes();
         var3.setLineWidth(0.5F);
         var3.setLineAntialiasingEnable(true);
         var1.setLineAttributes(var3);
         Point3f[] var5 = new Point3f[19];
         TransformGroup var6 = null;

         PointSphere var4;
         int var7;
         int var8;
         LineStripArray var9;
         Shape3D var10;
         int[] var11;
         for(var7 = 0; var7 <= 360; var7 += 20) {
            for(var8 = 0; var8 <= 180; var8 += 10) {
               var4 = new PointSphere((double)var7 / 360.0D, (double)var8 / 180.0D);
               var5[var8 / 10] = new Point3f((float)(0.51D * var4.x), (float)(0.51D * var4.y), (float)(0.51D * var4.z));
            }

            var11 = new int[]{19};
            var9 = new LineStripArray(var5.length, 1, var11);
            var9.setCoordinates(0, var5);
            var9.setCoordinates(0, var5);
            var10 = new Shape3D(var9, var1);
            var6 = new TransformGroup();
            var6.addChild(var10);
            bgL.addChild(var6);
         }

         var5 = new Point3f[37];

         for(var7 = 0; var7 <= 360; var7 += 20) {
            for(var8 = 0; var8 <= 360; var8 += 10) {
               var4 = new PointSphere((double)var8 / 360.0D, (double)var7 / 180.0D);
               var5[var8 / 10] = new Point3f((float)(0.51D * var4.x), (float)(0.51D * var4.y), (float)(0.51D * var4.z));
            }

            var11 = new int[]{19};
            var9 = new LineStripArray(var5.length, 1, var11);
            var9.setCoordinates(0, var5);
            var9.setCoordinates(0, var5);
            var10 = new Shape3D(var9, var1);
            var6 = new TransformGroup();
            var6.addChild(var10);
            bgL.addChild(var6);
         }

         var0.addChild(bgL);
      }

   }

   public void eksenler(TransformGroup var1) {
      Appearance var2 = new Appearance();
      ColoringAttributes var3 = new ColoringAttributes();
      var3.setColor(0.5F, 0.5F, 0.5F);
      var3.setShadeModel(3);
      var2.setColoringAttributes(var3);
      LineAttributes var4 = new LineAttributes();
      var4.setLineWidth(3.0F);
      var4.setLinePattern(0);
      var4.setLineAntialiasingEnable(true);
      var2.setLineAttributes(var4);
      Point3f[] var5 = new Point3f[]{new Point3f(0.7F, 0.0F, 0.0F), new Point3f(0.0F, 0.0F, 0.0F), new Point3f(0.0F, 0.7F, 0.0F), new Point3f(0.0F, 0.0F, 0.0F), new Point3f(0.0F, 0.0F, 0.7F)};
      int[] var6 = new int[]{5};
      LineStripArray var7 = new LineStripArray(var5.length, 1, var6);
      var7.setCoordinates(0, var5);
      Shape3D var8 = new Shape3D(var7, var2);
      TransformGroup var9 = new TransformGroup();
      var9.addChild(var8);
      var1.addChild(var9);
      var2 = new Appearance();
      var3 = new ColoringAttributes();
      var3.setColor(0.5F, 0.0F, 0.0F);
      var3.setShadeModel(3);
      var2.setColoringAttributes(var3);
      Material var23 = new Material();
      var23.setAmbientColor(1.0F, 0.0F, 0.0F);
      var23.setDiffuseColor(0.9F, 0.9F, 0.9F);
      var23.setEmissiveColor(0.0F, 0.0F, 0.0F);
      var23.setSpecularColor(1.0F, 1.0F, 1.0F);
      var23.setShininess(40.0F);
      var2.setMaterial(var23);
      TransformGroup var24 = new TransformGroup();
      Transform3D var25 = new Transform3D();
      var25.setTranslation(new Vector3f(0.7F, 0.0F, 0.0F));
      Transform3D var26 = new Transform3D();
      var26.rotZ(-1.5707963267948966D);
      var25.mul(var26);
      var24.setTransform(var25);
      var24.addChild(new Cone(0.03F, 0.1F, var2));
      var1.addChild(var24);
      TransformGroup var27 = new TransformGroup();
      Transform3D var28 = new Transform3D();
      var28.setTranslation(new Vector3f(0.0F, 0.7F, 0.0F));
      var27.setTransform(var28);
      var27.addChild(new Cone(0.03F, 0.1F, var2));
      var1.addChild(var27);
      TransformGroup var10 = new TransformGroup();
      Transform3D var11 = new Transform3D();
      var11.setTranslation(new Vector3f(0.0F, 0.0F, 0.7F));
      var26 = new Transform3D();
      var26.rotX(1.5707963267948966D);
      var11.mul(var26);
      var10.setTransform(var11);
      var10.addChild(new Cone(0.03F, 0.1F, var2));
      var1.addChild(var10);
      TransformGroup var12 = new TransformGroup();
      Transform3D var13 = new Transform3D();
      var13.setScale(0.1D);
      var12.setTransform(var13);
      var1.addChild(var12);
      Font3D var14 = new Font3D(new Font("TestFont", 0, 1), new FontExtrusion());
      Text3D var15 = new Text3D(var14, "x", new Point3f(8.0F, 0.0F, 0.0F));
      Shape3D var16 = new Shape3D();
      var16.setGeometry(var15);
      var16.setAppearance(var2);
      var12.addChild(var16);
      Font3D var17 = new Font3D(new Font("TestFont", 0, 1), new FontExtrusion());
      Text3D var18 = new Text3D(var17, "y", new Point3f(0.0F, 8.0F, 0.0F));
      Shape3D var19 = new Shape3D();
      var19.setGeometry(var18);
      var19.setAppearance(var2);
      var12.addChild(var19);
      Font3D var20 = new Font3D(new Font("TestFont", 0, 1), new FontExtrusion());
      Text3D var21 = new Text3D(var20, "z", new Point3f(-1.0F, 0.0F, 8.0F));
      Shape3D var22 = new Shape3D();
      var22.setGeometry(var21);
      var22.setAppearance(var2);
      var12.addChild(var22);
   }

   public SphereTSP() {
      this.setLayout(new BorderLayout());
      GraphicsConfiguration var1 = SimpleUniverse.getPreferredConfiguration();
      Canvas3D var2 = new Canvas3D(var1);
      this.add("Center", var2);
      points = new Points();
      this.box1 = Box.createHorizontalBox();
      this.box1.add(new JLabel("  "), (Object)null);
      this.box1.add(new JLabel("Transparency:"));
      this.rdOn = new JRadioButton("On", true);
      this.rdOn.setToolTipText("All points are visible");
      this.rdOff = new JRadioButton("Off", false);
      this.rdOff.setToolTipText("Only points which is on the front faces from viewing position are visible");
      this.rGrup = new ButtonGroup();
      this.rGrup.add(this.rdOn);
      this.rGrup.add(this.rdOff);
      this.box1.add(this.rdOn, (Object)null);
      this.box1.add(this.rdOff, (Object)null);
      this.box1.add(new JLabel("  "), (Object)null);
      bLines = new JCheckBox("Lines");
      bLines.setMnemonic(76);
      bLines.setSelected(true);
      bLines.addActionListener(this);
      this.box1.add(bLines, (Object)null);
      this.box1.add(new JLabel("  "), (Object)null);
      bAbout = new JButton("About");
      bAbout.setMaximumSize(new Dimension(100, 27));
      bAbout.addActionListener(this);
      this.box1.add(bAbout, (Object)null);
      this.add("North", this.box1);
      this.box2 = Box.createVerticalBox();
      this.add("West", this.box2);
      bSolveTSP = new JButton("Solve");
      bSolveTSP.setMaximumSize(new Dimension(100, 27));
      bSolveTSP.addActionListener(this);
      this.lGenerationSize = new JLabel("Generation Size");
      this.lGenerationSize.setMaximumSize(new Dimension(140, 27));
      this.lGenerationSize.setAlignmentX(0.0F);
      tfGenerationSize = new JTextField("1024");
      tfGenerationSize.setMaximumSize(new Dimension(140, 27));
      this.lPopulationSize = new JLabel("Population Size");
      tfPopulationSize = new JTextField("250");
      tfPopulationSize.setMaximumSize(new Dimension(140, 27));
      this.lMutationRate = new JLabel("Mutation Rate");
      tfMutationRate = new JTextField("0.05");
      tfMutationRate.setMaximumSize(new Dimension(140, 27));
      this.lCRate = new JLabel("Crossover Rate");
      tfCRate = new JTextField("0.80");
      tfCRate.setMaximumSize(new Dimension(140, 27));
      tfTourLength = new JTextField("--------");
      tfTourLength.setMaximumSize(new Dimension(140, 27));
      this.box2.add(new JLabel("Genetic Algorithms"), (Object)null);
      this.box2.add(this.lGenerationSize, (Object)null);
      this.box2.add(tfGenerationSize, (Object)null);
      this.box2.add(this.lPopulationSize, (Object)null);
      this.box2.add(tfPopulationSize, (Object)null);
      this.box2.add(this.lMutationRate, (Object)null);
      this.box2.add(tfMutationRate, (Object)null);
      this.box2.add(this.lCRate, (Object)null);
      this.box2.add(tfCRate, (Object)null);
      this.box2.add(bSolveTSP);
      this.box2.add(new JLabel("Tour Length"), (Object)null);
      this.box2.add(tfTourLength, (Object)null);
      this.box2.add(new JLabel("         "), (Object)null);
      this.box2.add(new JLabel("Current Point"), (Object)null);
      bDelete = new JButton("Remove");
      bDelete.setMaximumSize(new Dimension(100, 27));
      bDelete.addActionListener(this);
      bNext = new JButton("Next");
      bNext.setMaximumSize(new Dimension(100, 27));
      bNext.addActionListener(this);
      bPrev = new JButton("Prev");
      bPrev.setMaximumSize(new Dimension(100, 27));
      bPrev.addActionListener(this);
      buPlus = new JButton("u+");
      buPlus.setMaximumSize(new Dimension(100, 27));
      buPlus.addActionListener(this);
      buMinus = new JButton("u-");
      buMinus.setMaximumSize(new Dimension(100, 27));
      buMinus.addActionListener(this);
      bvPlus = new JButton("v+");
      bvPlus.setMaximumSize(new Dimension(100, 27));
      bvPlus.addActionListener(this);
      bvMinus = new JButton("v-");
      bvMinus.setMaximumSize(new Dimension(100, 27));
      bvMinus.addActionListener(this);
      lx = new JLabel("x :");
      lx.setMaximumSize(new Dimension(140, 27));
      ly = new JLabel("y :");
      ly.setMaximumSize(new Dimension(140, 27));
      lz = new JLabel("z :");
      lz.setMaximumSize(new Dimension(140, 27));
      this.box2.add(bDelete);
      this.box2.add(bNext);
      this.box2.add(bPrev);
      this.box2.add(buPlus);
      this.box2.add(buMinus);
      this.box2.add(bvPlus);
      this.box2.add(bvMinus);
      this.box2.add(lx);
      this.box2.add(ly);
      this.box2.add(lz);
      bDelete.setEnabled(false);
      bNext.setEnabled(false);
      bPrev.setEnabled(false);
      buPlus.setEnabled(false);
      bvPlus.setEnabled(false);
      buMinus.setEnabled(false);
      bvMinus.setEnabled(false);
      this.box3 = Box.createHorizontalBox();
      this.add("South", this.box3);
      bAddRandom = new JButton("Add Random Point");
      bAddRandom.setMaximumSize(new Dimension(100, 27));
      bAddRandom.addActionListener(this);
      bAddUV = new JButton("Add");
      bAddUV.setMaximumSize(new Dimension(100, 27));
      bSetUV = new JButton("Set");
      bSetUV.setMaximumSize(new Dimension(100, 27));
      this.lu = new JLabel("u:");
      this.lu.setMaximumSize(new Dimension(140, 27));
      this.lu.setAlignmentX(0.0F);
      tfu = new JTextField("     ", 8);
      tfu.setMaximumSize(new Dimension(140, 27));
      this.lv = new JLabel("v:");
      this.lv.setMaximumSize(new Dimension(140, 27));
      this.lv.setAlignmentX(0.0F);
      tfv = new JTextField("     ", 8);
      tfv.setMaximumSize(new Dimension(140, 27));
      lnp = new JLabel("N:         ");
      lnp.setMaximumSize(new Dimension(140, 27));
      lnp.setMinimumSize(new Dimension(80, 27));
      this.box3.add(lnp);
      this.box3.add(bAddRandom);
      JPanel var3 = new JPanel();
      var3.add(this.lu);
      var3.add(tfu);
      var3.add(this.lv);
      var3.add(tfv);
      var3.add(bAddUV);
      var3.add(bSetUV);
      this.box3.add(var3);
      bReset = new JButton("Reset");
      bReset.setMaximumSize(new Dimension(100, 27));
      bReset.addActionListener(this);
      this.box3.add(bReset);
      BranchGroup var4 = this.goruntuAgaciOlustur();
      SimpleUniverse var5 = new SimpleUniverse(var2);
      var5.getViewingPlatform().setNominalViewingTransform();
      this.view = var5.getViewer().getView();
      var5.addBranchGraph(var4);
   }



   public void actionPerformed(ActionEvent var1) {
      if (var1.getSource() == bAbout) {
         JOptionPane.showMessageDialog(this, "Copyright 2006 by Aybars UGUR", "Info", 1);
      }

   }

   public static void main(String[] var0) {
      new MainFrame(new SphereTSP(), 600, 600);
   }
}


