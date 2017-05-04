
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.Switch;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author cecew
 */
public class Tree {
      public static BranchGroup trunk(){
        BranchGroup bg = new BranchGroup();
        
        Appearance trunkApp = new Appearance();
        Color3f trunkColor = new Color3f(0.52f, 0.37f, 0.26f); //brown
        ColoringAttributes trunkCA = new ColoringAttributes();
	trunkCA.setColor(trunkColor);
	trunkApp.setColoringAttributes(trunkCA);
       
        
        Cylinder trunk = new Cylinder(0.1f,0.5f, trunkApp);
        
        bg.addChild(trunk);
        return bg;
      }
      
      public static BranchGroup top(){
          
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
        
        BranchGroup bg = new BranchGroup();
        
        //GREEN
        Material leavesGreenMaterial = new Material(); 
        leavesGreenMaterial.setAmbientColor(new Color3f(0.0f,0.35f,0.0f));
        leavesGreenMaterial.setSpecularColor(new Color3f(0.5f,0.5f,0.5f)); 
        leavesGreenMaterial.setDiffuseColor(new Color3f(0.3f,0.3f,0.3f));
        
        Appearance leavesAppG = new Appearance();
        leavesAppG.setMaterial(leavesGreenMaterial);
       
        Transform3D leavesM3d = new Transform3D();
        leavesM3d.setTranslation(new Vector3d(0.0, 0.17 ,0.0));
        TransformGroup leavesMTGG = new TransformGroup(leavesM3d);
        
        Transform3D leavesT3d = new Transform3D();
        leavesT3d.setTranslation(new Vector3d(0.0, 0.34 ,0.0));
        TransformGroup leavesTTGG = new TransformGroup(leavesT3d);
        
        BranchGroup greenTop = new BranchGroup();
        
        Cone leavesLG = new Cone(0.4f,0.7f,leavesAppG);
        Cone leavesMG = new Cone(0.3f,0.5f,leavesAppG);
        Cone leavesTG = new Cone(0.2f,0.3f,leavesAppG); 
        
        greenTop.addChild(leavesLG);
        greenTop.addChild(leavesMTGG);
        leavesMTGG.addChild(leavesMG);
        greenTop.addChild(leavesTTGG);
        leavesTTGG.addChild(leavesTG);
        
        Transform3D top3d = new Transform3D();
        top3d.setTranslation(new Vector3f(0.010f,0.075f,0.0f));
        TransformGroup topTG = new TransformGroup(top3d);
        
        topTG.addChild(greenTop);
       
         
        bg.addChild(topTG);
        return bg;
      }
}
