import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;
import java.awt.Container;
import javax.media.j3d.Appearance;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;
/**
 *
 * @author cecew
 */
public class Track {
    public static BranchGroup track(){
        BranchGroup bg = new BranchGroup();
      
         //set up the materials
        int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
        
        // Set up the texture map for the track
        
        TextureLoader loadertrack = new TextureLoader("train_track.jpg", "RGB", new Container());
        Texture texturetrack = loadertrack.getTexture();
        texturetrack.setBoundaryModeS(Texture.WRAP);
        texturetrack.setBoundaryModeT(Texture.WRAP);
        texturetrack.setBoundaryColor( new Color4f( 0.0f, 1.0f, 0.0f, 0.0f ) );    
        
        // Set up the texture attributes
        //could be REPLACE, BLEND or DECAL instead of MODULATE
        TextureAttributes texatttrack = new TextureAttributes();
        texatttrack.setTextureMode(TextureAttributes.MODULATE);
        
        Appearance trackApp = new Appearance();
        trackApp.setTexture(texturetrack);
        trackApp.setTextureAttributes(texatttrack);
    
        Cylinder track = new Cylinder(1.5f,0.01f,primflags,trackApp); 
        
        Appearance innerApp = new Appearance();
        ColoringAttributes innerCA = new ColoringAttributes();
        Color3f green = new Color3f(0.30f,0.75f,0.20f);
        innerCA.setColor(green);
        innerApp.setColoringAttributes(innerCA);
        
        Cylinder trackInner = new Cylinder(1.1f,0.015f,innerApp);
        
        bg.addChild(track);
        bg.addChild(trackInner);
        
        return bg;
    }
}
