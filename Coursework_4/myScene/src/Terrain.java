import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3f;

/**
 *
 * @author cecew
 */
public class Terrain {
     public static BranchGroup terrain(){
        BranchGroup bg = new BranchGroup();
        
        //grass appearance
        Appearance surfaceApp = new Appearance();
        ColoringAttributes surfaceCA = new ColoringAttributes();
        Color3f green = new Color3f(0.30f,0.75f,0.20f);
        surfaceCA.setColor(green);
        surfaceApp.setColoringAttributes(surfaceCA);
        
        //transformation to position dirt under grass
        Transform3D dirt3d = new Transform3D();
        dirt3d.setTranslation(new Vector3f(0.0f,-0.25f,0.0f));
        TransformGroup dirtTG = new TransformGroup(dirt3d);
        
        //dirt appearance
        Appearance dirtApp = new Appearance();
        ColoringAttributes dirtCA = new ColoringAttributes();
        Color3f brown = new Color3f(0.55f,0.40f,0.03f);
        dirtCA.setColor(brown);
        dirtApp.setColoringAttributes(dirtCA);
        
        Box surface = new Box(2.5f,0.05f,2.5f,surfaceApp);
        Box dirt = new Box(2.5f,0.20f,2.5f,dirtApp);
        
        bg.addChild(surface);
        bg.addChild(dirtTG);
        dirtTG.addChild(dirt);
        
        return bg;
    }
}
