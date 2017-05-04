import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.SpotLight;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import static javax.media.j3d.TransparencyAttributes.BLENDED;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;

/**
 *
 * @author cecew
 */
public class UFO {
    public static BranchGroup ufo(){
        
        BranchGroup bg = new BranchGroup();
        
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.9, 0.0),
        0.5);
        
        //transparency for cabin
        TransparencyAttributes ta = new TransparencyAttributes();
        ta.setTransparencyMode(BLENDED);
        ta.setTransparency(0.4f);
        
        //materials
        Material ufoMaterial = new Material(); 
        ufoMaterial.setAmbientColor(new Color3f(0.3f,0.3f,0.3f));
        ufoMaterial.setSpecularColor(new Color3f(0.5f,0.5f,0.5f)); 
        ufoMaterial.setDiffuseColor(new Color3f(0.3f,0.3f,0.3f));
        
        Material lightsMaterial = new Material();
        lightsMaterial.setSpecularColor(new Color3f(1.0f,1.0f,1.0f)); 
        lightsMaterial.setDiffuseColor(new Color3f(1.0f,0.89f,0.01f));
        lightsMaterial.setShininess(10.0f);
        
        //lights
        SpotLight sl = new SpotLight();
        sl.setInfluencingBounds(bounds);
        sl.setPosition(new Point3f(0.0f, 0.0f, 0.0f));
        sl.setSpreadAngle(0.5f);
        sl.setConcentration(100.0f);
        
        //BASE
         
        //position parts of the base
        Transform3D baseTopPos = new Transform3D();
        baseTopPos.setTranslation(new Vector3d(0.0,0.03,0.0));
        TransformGroup baseTopTG = new TransformGroup(baseTopPos);
        
        Transform3D baseBotPos = new Transform3D();
        baseBotPos.setTranslation(new Vector3d(0.0,-0.03,0.0));
        TransformGroup baseBotTG = new TransformGroup(baseBotPos);
        
        //creating an appearance for base (cylinders)
	Appearance baseApp = new Appearance();
        baseApp.setMaterial(ufoMaterial);
        
        //creation of base
        Cylinder baseTop = new Cylinder(0.25f,0.03f,baseApp);
        Cylinder baseMid = new Cylinder(0.35f,0.03f,baseApp);
        Cylinder baseBot = new Cylinder(0.25f,0.03f,baseApp);
        
        //LIGHTS OF UFO
        Transform3D light1pos = new Transform3D();
        light1pos.setTranslation(new Vector3d(-0.30,0.01,0.0));
        TransformGroup light1TG = new TransformGroup(light1pos);
        
        Transform3D light2pos = new Transform3D();
        light2pos.setTranslation(new Vector3d(-0.21,0.01,0.19));
        TransformGroup light2TG = new TransformGroup(light2pos);
        
        Transform3D light3pos = new Transform3D();
        light3pos.setTranslation(new Vector3d(0.0,0.01,0.30));
        TransformGroup light3TG = new TransformGroup(light3pos);
        
        Transform3D light4pos = new Transform3D();
        light4pos.setTranslation(new Vector3d(0.21,0.01,0.19));
        TransformGroup light4TG = new TransformGroup(light4pos);
        
        Transform3D light5pos = new Transform3D();
        light5pos.setTranslation(new Vector3d(0.30,0.01,0.0));
        TransformGroup light5TG = new TransformGroup(light5pos);
        
        Transform3D light6pos = new Transform3D();
        light6pos.setTranslation(new Vector3d(-0.21,0.01,-0.19));
        TransformGroup light6TG = new TransformGroup(light6pos);
        
        Transform3D light7pos = new Transform3D();
        light7pos.setTranslation(new Vector3d(0.21,0.01,-0.19));
        TransformGroup light7TG = new TransformGroup(light7pos);
        
        Transform3D light8pos = new Transform3D();
        light8pos.setTranslation(new Vector3d(0.0,0.01,-0.30));
        TransformGroup light8TG = new TransformGroup(light8pos);
        
        //appearance of the lights
        Appearance lightsApp = new Appearance();
        Color3f lightsColor = new Color3f(0.0f,0.0f,0.0f);
        ColoringAttributes lightsCA = new ColoringAttributes();
	lightsCA.setColor(lightsColor);
	lightsApp.setColoringAttributes(lightsCA);
        lightsApp.setMaterial(lightsMaterial);
        
        //creation of lights
        Sphere light1 = new Sphere(0.02f,lightsApp);
        Sphere light2 = new Sphere(0.02f,lightsApp);
        Sphere light3 = new Sphere(0.02f,lightsApp);
        Sphere light4 = new Sphere(0.02f,lightsApp);
        Sphere light5 = new Sphere(0.02f,lightsApp);
        Sphere light6 = new Sphere(0.02f,lightsApp);
        Sphere light7 = new Sphere(0.02f,lightsApp);
        Sphere light8 = new Sphere(0.02f,lightsApp);
         
        //CAB        
        //transform group to position cab
        Transform3D cabPos = new Transform3D();
        cabPos.setTranslation(new Vector3d(0.0, 0.0 ,0.0));
	TransformGroup cabTG = new TransformGroup(cabPos);
        
        // creating an appearance for cabin (sphere)
	Appearance cabApp = new Appearance();
	Color3f cabColor = new Color3f(0.5f, 0.5f, 0.5f); //grey
	ColoringAttributes cabCA = new ColoringAttributes();
	cabCA.setColor(cabColor);
	cabApp.setColoringAttributes(cabCA);
        cabApp.setMaterial(ufoMaterial);
        cabApp.setTransparencyAttributes(ta);
        
        
        //creation of cabin
        Sphere cabin = new Sphere(0.15f,cabApp);
        
        //base
        bg.addChild(baseMid);
        bg.addChild(baseTopTG);
        bg.addChild(baseBotTG);
        baseTopTG.addChild(baseTop);
        baseBotTG.addChild(baseBot);
        
        //lights of UFO
        bg.addChild(light1TG);
        light1TG.addChild(light1);
        bg.addChild(light2TG);
        light2TG.addChild(light2);
        bg.addChild(light3TG);
        light3TG.addChild(light3);
        bg.addChild(light4TG);
        light4TG.addChild(light4);
        bg.addChild(light5TG);
        light5TG.addChild(light5);
        bg.addChild(light6TG);
        light6TG.addChild(light6);
        bg.addChild(light7TG);
        light7TG.addChild(light7);
        bg.addChild(light8TG);
        light8TG.addChild(light8);
        
        //cab
        bg.addChild(cabTG);
        cabTG.addChild(cabin);
        
        return bg;
        
    }
}
