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
 * Class for creating a track
 * track() returns a branch group of a track object
 * 
 * @author Cecelia Wisniewska
 *
 */
public class Track {
    public static BranchGroup track(){
        
        // creating a branch group
        BranchGroup bg = new BranchGroup();
      
        // set up the materials
        int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
        
        // set up the texture map for the track
        // the image loaded for track texture is "train_track.jpg"
        TextureLoader loadertrack = new TextureLoader("train_track.jpg", "RGB", new Container());
        Texture texturetrack = loadertrack.getTexture();
        texturetrack.setBoundaryModeS(Texture.WRAP);
        texturetrack.setBoundaryModeT(Texture.WRAP);
        texturetrack.setBoundaryColor( new Color4f( 0.0f, 1.0f, 0.0f, 0.0f ) );    
        
        // create and set up the texture attributes
        TextureAttributes texatttrack = new TextureAttributes();
        texatttrack.setTextureMode(TextureAttributes.MODULATE);
        
        // creating an appearance for track
        // set the texture attributes
        Appearance trackApp = new Appearance();
        trackApp.setTexture(texturetrack);
        trackApp.setTextureAttributes(texatttrack);
    
        // creating 3D shape: cylinder for track
        Cylinder track = new Cylinder(1.5f,0.01f,primflags,trackApp); 
        
        // creating an appearance for the inside of track area
        // the color green is the same as color of terrain surface
        Appearance innerApp = new Appearance();
        ColoringAttributes innerCA = new ColoringAttributes();
        Color3f green = new Color3f(0.30f,0.75f,0.20f);
        innerCA.setColor(green);
        innerApp.setColoringAttributes(innerCA);
        
        // creating 3D shape: cylinder for trackInner
        Cylinder trackInner = new Cylinder(1.1f,0.015f,innerApp);
        
        // make edge relations between nodes
        bg.addChild(track);
        bg.addChild(trackInner);
        
        return bg;
    }
}
