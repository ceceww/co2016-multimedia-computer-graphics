import com.sun.j3d.utils.geometry.Box;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingBox;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Material;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;

/**
 * Class for creating the colliding object
 * cube() returns a branch group of a cube object
 * 
 * @author Cecelia Wisniewska
 *
 */
public class CollidingObject {
    public static BranchGroup cube(){
        // creating branch group
        BranchGroup bg = new BranchGroup();
        
        // ------------------ begin build the cube -- 

        /* CUBE APPEARANCE  --------------------------------- */

        // a blue appearance for the cube
        // parameters specify behavior involving light
        // 1 ambient color: amount of ambient light reflected 
        // 2 emissive color: light emitted 
        // 3 diffuse color: the RGB color of material when illuminated
        // 4 specular color: the RGB specular color of the material 
        // 5 shininess value: larger value ==> shinier 
        Color3f ambientColourCube = new Color3f(0.0f,0.0f,0.8f);
        Color3f emissiveColourCube = new Color3f(0.0f,0.0f,0.0f);
        Color3f diffuseColourCube = new Color3f(0.0f,0.0f,0.7f);
        Color3f specularColourCube = new Color3f(0.0f,0.0f,0.8f);
        float shininessCube = 128.0f;
        
        // Generate the appearance and apply material to cube
        Appearance cubeApp = new Appearance();
        cubeApp.setMaterial(new Material(ambientColourCube,emissiveColourCube,
                               diffuseColourCube,specularColourCube,shininessCube));

        /* END CUBE APPEARANCE  --------------------------------- */

        // *** Generate the physical cube.
        float cubeHalfLength = 0.1f;
        // centred at the origin; parameters are HALF the depth, height and width
        Box cube = new Box(cubeHalfLength,cubeHalfLength,cubeHalfLength,cubeApp);

        //Position the cube and assign it to a transformation group.
        Transform3D tfCube = new Transform3D();
        tfCube.rotY(Math.PI/6);
        TransformGroup tgCube = new TransformGroup(tfCube);
        tgCube.addChild(cube);

        // These properties are needed to allow the cube to moved around the scene.
        // *** PICK allows the cube to be "picked up" with the mouse, and moved around.
        tgCube.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tgCube.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        tgCube.setCapability(TransformGroup.ENABLE_PICK_REPORTING);

        // ------------------ End build the cube -- 
        
        //The CollisionBounds for the cube.
        double kk = 1.0;
        // using BoundingBox is more precise than BoundingSphere for CollisionBounds
          cube.setCollisionBounds(new BoundingBox(new Point3d(-kk*cubeHalfLength,
                                                               -kk*cubeHalfLength,
                                                            -kk*cubeHalfLength),
                                                new Point3d(kk*cubeHalfLength,
                                                             kk*cubeHalfLength,
                                                               kk*cubeHalfLength)));
         
        // Enabled for collision purposes (the default is true)
        cube.setCollidable(true);
    
        bg.addChild(tgCube);
        
        return bg;
    }
}
