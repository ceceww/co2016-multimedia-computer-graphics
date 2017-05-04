import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cone;
import com.sun.j3d.utils.geometry.Cylinder;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

/**
 *
 * @author cecew
 */
public class Train {
    public static BranchGroup train(){
        
        BranchGroup bg = new BranchGroup();
        
        Material redMaterial = new Material(); 
        redMaterial.setAmbientColor(new Color3f(1.0f,0.0f,0.0f));
        redMaterial.setSpecularColor(new Color3f(0.5f,0.5f,0.5f)); 
        redMaterial.setDiffuseColor(new Color3f(0.3f,0.3f,0.3f));
        
        Material blueMaterial = new Material(); 
        blueMaterial.setAmbientColor(new Color3f(0.0f,0.0f,1.0f));
        blueMaterial.setSpecularColor(new Color3f(0.5f,0.5f,0.5f)); 
        blueMaterial.setDiffuseColor(new Color3f(0.3f,0.3f,0.3f));
        
        Material darkMaterial = new Material(); 
        darkMaterial.setAmbientColor(new Color3f(0.1f,0.1f,0.1f));
        darkMaterial.setSpecularColor(new Color3f(0.2f,0.2f,0.2f)); 
        darkMaterial.setDiffuseColor(new Color3f(0.3f,0.3f,0.3f));
        
        //BASE
         // creating an appearance for base (box)
	Appearance baseApp = new Appearance();
	Color3f baseColor = new Color3f(0.0f, 0.0f, 0.55f); //dark blue
	ColoringAttributes baseCA = new ColoringAttributes();
	baseCA.setColor(baseColor);
	baseApp.setColoringAttributes(baseCA);
        baseApp.setMaterial(blueMaterial);
        
         //creation of base
        Box base = new Box(0.1f,0.03f, 0.25f,baseApp);
        
        bg.addChild(base);
        
        //BODY
        //transform group to position body
        Transform3D bodyPos = new Transform3D();
        bodyPos.setTranslation(new Vector3d(0.0, 0.1 ,0.1));
	TransformGroup bodyTG1 = new TransformGroup(bodyPos);
        
        //rotate body
        Transform3D bodyRot = new Transform3D();
        bodyRot.rotX(Math.PI/2);
        TransformGroup bodyTG2 = new TransformGroup(bodyRot);
        
         //creating an appearance for body (cylinder)
	Appearance bodyApp = new Appearance();
	Color3f bodyColor = new Color3f(1.0f, 0.0f, 0.0f); //red
	ColoringAttributes bodyCA = new ColoringAttributes();
	bodyCA.setColor(bodyColor);
	bodyApp.setColoringAttributes(bodyCA);
        bodyApp.setMaterial(redMaterial);
        
        //creation of body
        Cylinder body = new Cylinder(0.1f,0.25f,bodyApp);
        
        bg.addChild(bodyTG1);
        bodyTG1.addChild(bodyTG2);
        bodyTG2.addChild(body);
        
        //CAB
        //transform group to position cab
        Transform3D cabPos = new Transform3D();
        cabPos.setTranslation(new Vector3d(0.0, 0.13 ,-0.12));
	TransformGroup cabTG1 = new TransformGroup(cabPos);
        
        //creating an appearance for cab (box)
        Appearance cabApp = new Appearance();
        Color3f cabColor = new Color3f(1.0f, 0.0f, 0.0f); //red
        ColoringAttributes cabCA = new ColoringAttributes();
	cabCA.setColor(cabColor);
	cabApp.setColoringAttributes(cabCA);
        cabApp.setMaterial(redMaterial);
        
        //creation of cab
        Box cab = new Box(0.1f,0.1f,0.1f, cabApp);
        
        bg.addChild(cabTG1);
        cabTG1.addChild(cab);
        
         //FUNNEL
        //transform group to position funnel
        Transform3D funnelPos = new Transform3D();
        funnelPos.setTranslation(new Vector3d(0.0, 0.2 ,0.1));
        funnelPos.setScale(new Vector3d(2.0,2.0,2.0));
	TransformGroup funnelTG1 = new TransformGroup(funnelPos);
        
        //rotate funnel
        Transform3D funnelRot = new Transform3D();
        funnelRot.rotX(Math.PI);
        TransformGroup funnelTG2 = new TransformGroup(funnelRot);
        
        //creating an appearance for funnel (cone)
        Appearance funnelApp = new Appearance();
        Color3f funnelColor = new Color3f(0.0f, 0.0f, 0.0f); 
        ColoringAttributes funnelCA = new ColoringAttributes();
	funnelCA.setColor(funnelColor);
	funnelApp.setColoringAttributes(funnelCA);
        funnelApp.setMaterial(darkMaterial);
        
        //creation of funnel
        Cone funnel = new Cone(0.03f,0.15f, funnelApp);
        
        bg.addChild(funnelTG1);
        funnelTG1.addChild(funnelTG2);
        funnelTG2.addChild(funnel);
        
        //ROOF
        //transform group to position roof
        Transform3D roofPos = new Transform3D();
        roofPos.setTranslation(new Vector3d(0.0, 0.28 ,-0.12));
	TransformGroup roofTG1 = new TransformGroup(roofPos);
        
        //creating an appearance for roof (box)
        Appearance roofApp = new Appearance();
        Color3f roofColor = new Color3f(0.0f, 0.0f, 0.55f); //dark blue
        ColoringAttributes roofCA = new ColoringAttributes();
	roofCA.setColor(roofColor);
	roofApp.setColoringAttributes(roofCA);
        roofApp.setMaterial(blueMaterial);
        
        //creation of roof
        Box roof = new Box(0.12f,0.05f,0.12f,roofApp);
        
        bg.addChild(roofTG1);
        roofTG1.addChild(roof);
        
        //WHEELS
        Transform3D wheel1Pos = new Transform3D();
        Transform3D wheel2Pos = new Transform3D();
        Transform3D wheel3Pos = new Transform3D();
        Transform3D wheel4Pos = new Transform3D();
        wheel1Pos.setTranslation(new Vector3d(0.1, 0.0, 0.12));
        TransformGroup wheel1TG1 = new TransformGroup(wheel1Pos);
        wheel2Pos.setTranslation(new Vector3d(0.1,0.0,-0.12));
        TransformGroup wheel2TG1 = new TransformGroup(wheel2Pos);
        wheel3Pos.setTranslation(new Vector3d(-0.1,0.0,0.12));
        TransformGroup wheel3TG1 = new TransformGroup(wheel3Pos);
        wheel4Pos.setTranslation(new Vector3d(-0.1,0.0,-0.12));
        TransformGroup wheel4TG1 = new TransformGroup(wheel4Pos);
        
        //rotate wheels
        Transform3D wheelRot = new Transform3D();
        wheelRot.rotX(Math.PI/2);
        wheelRot.rotZ(Math.PI/2);
        TransformGroup wheel1TG2 = new TransformGroup(wheelRot);
        TransformGroup wheel2TG2 = new TransformGroup(wheelRot);
        TransformGroup wheel3TG2 = new TransformGroup(wheelRot);
        TransformGroup wheel4TG2 = new TransformGroup(wheelRot);
        
          //creating an appearance for wheels (cylinder)
        Appearance wheelsApp = new Appearance();
        Color3f wheelsColor = new Color3f(0.2f, 0.2f, 0.2f); //dark grey
        ColoringAttributes wheelsCA = new ColoringAttributes();
	wheelsCA.setColor(wheelsColor);
	wheelsApp.setColoringAttributes(wheelsCA);
        wheelsApp.setMaterial(darkMaterial);
        
        //creation of wheels
        Cylinder wheel1 = new Cylinder(0.07f,0.03f,wheelsApp);
        Cylinder wheel2 = new Cylinder(0.07f,0.03f,wheelsApp);
        Cylinder wheel3 = new Cylinder(0.07f,0.03f,wheelsApp);
        Cylinder wheel4 = new Cylinder(0.07f,0.03f,wheelsApp);
        
        bg.addChild(wheel1TG1);
        wheel1TG1.addChild(wheel1TG2);
        wheel1TG2.addChild(wheel1);
        
        bg.addChild(wheel2TG1);
        wheel2TG1.addChild(wheel2TG2);
        wheel2TG2.addChild(wheel2);
        
        bg.addChild(wheel3TG1);
        wheel3TG1.addChild(wheel3TG2);
        wheel3TG2.addChild(wheel3);
        
        bg.addChild(wheel4TG1);
        wheel4TG1.addChild(wheel4TG2);
        wheel4TG2.addChild(wheel4);
        
        return bg;
        
    }
}
