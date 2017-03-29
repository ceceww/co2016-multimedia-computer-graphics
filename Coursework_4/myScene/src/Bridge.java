
import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

/**
 *
 * @author cecew
 */
public class Bridge {
    public static BranchGroup bridge(){
        BranchGroup bg = new BranchGroup();
        
        Appearance bridgeApp = new Appearance();
        ColoringAttributes bridgeCA = new ColoringAttributes();
        Color3f brown = new Color3f(0.52f,0.37f,0.26f);
        bridgeCA.setColor(brown);
        bridgeApp.setColoringAttributes(bridgeCA);
        
        Transform3D sup1pos = new Transform3D();
        sup1pos.setTranslation(new Vector3d(0.0,0.22,0.0));
	TransformGroup sup1TG = new TransformGroup(sup1pos);
        
        Transform3D sup2pos = new Transform3D();
        sup2pos.setTranslation(new Vector3d(0.75,0.22,0.0));
	TransformGroup sup2TG = new TransformGroup(sup2pos);
        
        Transform3D toppos = new Transform3D();
        toppos.setTranslation(new Vector3d(0.37,0.5,0.0));
        TransformGroup topTG = new TransformGroup(toppos);
        
        Box sup1 = new Box(0.05f,0.3f,0.1f,bridgeApp);
        Box sup2 = new Box(0.05f,0.3f,0.1f,bridgeApp);
        Box top = new Box (0.44f,0.05f,0.1f,bridgeApp);
                
        bg.addChild(sup1TG);
        bg.addChild(sup2TG);
        bg.addChild(topTG);
        sup1TG.addChild(sup1);
        sup2TG.addChild(sup2);
        topTG.addChild(top);
        
        return bg;
    }
}
