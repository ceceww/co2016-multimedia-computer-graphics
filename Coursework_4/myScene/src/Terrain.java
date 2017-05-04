import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

/**
 * Class for creating terrain
 * terrain() returns a branch group of a terrain object
 * 
 * @author Cecelia Wisniewska
 *
 */
public class Terrain {
     public static BranchGroup terrain(){
        // creating branch group
        BranchGroup bg = new BranchGroup();
        
        // creating an appearance for surface (grass)
        Appearance surfaceApp = new Appearance();
        // creating and setting coloring attributes
        ColoringAttributes surfaceCA = new ColoringAttributes();
        Color3f green = new Color3f(0.30f,0.75f,0.20f);
        surfaceCA.setColor(green);
        surfaceApp.setColoringAttributes(surfaceCA);
        
        // transformation to position dirt under surface (grass)
        Transform3D dirt3d = new Transform3D();
        dirt3d.setTranslation(new Vector3f(0.0f,-0.25f,0.0f));
        TransformGroup dirtTG = new TransformGroup(dirt3d);
        
        // creating an appearance for dirt
        Appearance dirtApp = new Appearance();
        // creating and setting coloring attributes
        ColoringAttributes dirtCA = new ColoringAttributes();
        Color3f brown = new Color3f(0.55f,0.40f,0.03f);
        dirtCA.setColor(brown);
        dirtApp.setColoringAttributes(dirtCA);
        
        // cretaing 3D shapes: boxes for surface and dirt
        Box surface = new Box(2.5f,0.05f,2.5f,surfaceApp);
        Box dirt = new Box(2.5f,0.20f,2.5f,dirtApp);
        
        // make edge relations between nodes
        bg.addChild(surface);
        bg.addChild(dirtTG);
        dirtTG.addChild(dirt);
        
        return bg;
    }
}
