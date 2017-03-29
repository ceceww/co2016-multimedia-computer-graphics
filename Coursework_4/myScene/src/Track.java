import com.sun.j3d.utils.geometry.Cylinder;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.vecmath.Color3f;
/**
 *
 * @author cecew
 */
public class Track {
    public static BranchGroup track(){
        BranchGroup bg = new BranchGroup();
      
        Cylinder track = new Cylinder(1.5f,0.01f,null); 
        
        Appearance innerApp = new Appearance();
        ColoringAttributes innerCA = new ColoringAttributes();
        Color3f black = new Color3f(0.0f,0.0f,0.0f);
        innerCA.setColor(black);
        innerApp.setColoringAttributes(innerCA);
        
        Cylinder trackInner = new Cylinder(1.1f,0.015f,innerApp);
        
        bg.addChild(track);
        bg.addChild(trackInner);
        
        return bg;
    }
}
