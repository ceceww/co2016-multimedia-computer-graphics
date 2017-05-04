import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

/**
 * Class for creating a bridge
 * bridge() returns a branch group of a bridge object
 * 
 * @author Cecelia Wisniewska
 *
 */
public class Bridge {
    public static BranchGroup bridge(){
        // creating branch group
        BranchGroup bg = new BranchGroup();
        
        // creating a material for the bridge appearance
        Material woodMaterial = new Material(); 
        woodMaterial.setAmbientColor(new Color3f(0.52f,0.37f,0.26f));
        woodMaterial.setSpecularColor(new Color3f(0.1f,0.1f,0.1f)); 
        woodMaterial.setDiffuseColor(new Color3f(0.1f,0.1f,0.1f));
        
        // creating an appearance for a bridge
        Appearance bridgeApp = new Appearance();
        bridgeApp.setMaterial(woodMaterial);
        
        // creating transform group (new sup1TG, from sup1pos) positioning the first bridge support
        // first creating a transformation sup1pos
        Transform3D sup1pos = new Transform3D();
        sup1pos.setTranslation(new Vector3d(0.0,0.22,0.0));
	TransformGroup sup1TG = new TransformGroup(sup1pos);
        
        // creating transform group (new sup2TG, from sup2pos) positioning the second bridge support
        // first creating a transformation sup2pos
        Transform3D sup2pos = new Transform3D();
        sup2pos.setTranslation(new Vector3d(0.75,0.22,0.0));
	TransformGroup sup2TG = new TransformGroup(sup2pos);
        
        // creating transform group (new topTG, from toppos) positioning the top of bridge
        // first creating a transformation toppos
        Transform3D toppos = new Transform3D();
        toppos.setTranslation(new Vector3d(0.37,0.5,0.0));
        TransformGroup topTG = new TransformGroup(toppos);
        
        // creating 3D shapes: boxes
        Box sup1 = new Box(0.05f,0.3f,0.1f,bridgeApp);
        Box sup2 = new Box(0.05f,0.3f,0.1f,bridgeApp);
        Box top = new Box (0.44f,0.05f,0.1f,bridgeApp);
                
        // make edge relations between nodes
        bg.addChild(sup1TG);
        bg.addChild(sup2TG);
        bg.addChild(topTG);
        sup1TG.addChild(sup1);
        sup2TG.addChild(sup2);
        topTG.addChild(top);
        
        return bg;
    }
}
