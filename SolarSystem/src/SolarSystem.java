import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.PointLight;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Color4f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author cecew
 */
public class SolarSystem extends JFrame{
    
    private BranchGroup createSceneGraph() {
        
        // creating the bounds of the universe
        // API: The scheduling region defines a spatial volume 
	//        that serves to enable the scheduling of Behavior nodes. 
	BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 1000);
 
	// creating a branch group
	BranchGroup objRoot = new BranchGroup();

	// creating a transform group 
	TransformGroup mainTG = new TransformGroup();		
	mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        
        //MERCURY

	// creating a rotation interpolator for mercury so mercury will orbit around the sun
	TransformGroup mercuryTG0 = new TransformGroup();
	mercuryTG0.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // 15000 is the time for the rotation 
	// -1 sets up infinite loop
        Alpha rotationAlphaMercury = new Alpha(-1, 15000);
         //rotate 360 degrees (full orbit) around the sun by setting ending angle to 2*pi radians
        Transform3D yAxis = new Transform3D(); //this is also used for the other planets
         RotationInterpolator rotatorMercury = new RotationInterpolator(rotationAlphaMercury,
         mercuryTG0, yAxis, 0.0f, (float) Math.PI * (2.0f));
         rotatorMercury.setSchedulingBounds(bounds);
        
         // creating another transform group (new mercuryTG1, from mer
        // first creating a transformation mer which gives mercury initial position of -5 along z-axis from sun
	Transform3D mer = new Transform3D();
        mer.setScale(new Vector3d(2.0, 2.0 ,2.0));
        mer.setTranslation(new Vector3d(0.0, 0.0 ,-5));
	Transform3D helperT3D= new Transform3D();
	//helperT3D.rotZ(Math.PI);
	//t.mul(helperT3D);
	//t.mul(helperT3D);
	TransformGroup mercuryTG1 = new TransformGroup(mer);
        
        // creating a rotation interpolator for a new mercuryTG2
	TransformGroup mercuryTG2 = new TransformGroup();
	mercuryTG2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // this behaviour object causes mercury to rotate around the locale y axis
	// -1 means indefinite loop count 
        Alpha rotationAlphaMercury2 = new Alpha(-1, 8000);
        Transform3D yAxis2 = new Transform3D();
        // RotationInterpolator always rotates around y axis by default 
	// *** see scene graph example 1 for a change to the z axis  *** 
        // *** but try this out yAxis2.rotZ(Math.PI/2); *** 
        RotationInterpolator rotatorMercury2 = new RotationInterpolator(rotationAlphaMercury2,
         mercuryTG2, yAxis2, 0.0f, (float) Math.PI * (2.0f));
         rotatorMercury2.setSchedulingBounds(bounds);

	//creation of mercury
	Sphere mercury = new Sphere(.5f);
        
        //VENUS
        //creating a rotation interpolator for venus so venus will orbit around the sun
        TransformGroup venusTG0 = new TransformGroup();
        venusTG0.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
       
        Alpha rotationAlphaVenus = new Alpha(-1, 15000);
         
        RotationInterpolator rotatorVenus = new RotationInterpolator(rotationAlphaVenus,
         venusTG0, yAxis, 0.0f, (float) Math.PI * (2.0f));
         rotatorVenus.setSchedulingBounds(bounds);
        
        // creating another transform group (new venusTG1, from ven)
        // first creating a transformation ven which gives venus initial position of -10 along z-axis from sun
	Transform3D ven = new Transform3D();
        ven.setScale(new Vector3d(2.0, 2.0 ,2.0));
        ven.setTranslation(new Vector3d(0.0, 0.0 ,-10));
	TransformGroup venusTG1 = new TransformGroup(ven);
        
        // creating a rotation interpolator for a new mercuryTG2
	TransformGroup venusTG2 = new TransformGroup();
	venusTG2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // this behaviour object causes mercury to rotate around the locale y axis
	// -1 means indefinite loop count 
        Alpha rotationAlphaVenus2 = new Alpha(-1, 8000);
        // RotationInterpolator always rotates around y axis by default 
	// *** see scene graph example 1 for a change to the z axis  *** 
        // *** but try this out yAxis2.rotZ(Math.PI/2); *** 
        RotationInterpolator rotatorVenus2 = new RotationInterpolator(rotationAlphaVenus2,
         venusTG2, yAxis2, 0.0f, (float) Math.PI * (2.0f));
         rotatorVenus2.setSchedulingBounds(bounds);
        
        //creation of venus
        Sphere venus = new Sphere (.5f);

        
        //SUN
        // Set up the texture map for sun
        
        TextureLoader loader = new TextureLoader("suntexture.jpg", "RGB", new Container());
        Texture texture = loader.getTexture();
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor( new Color4f( 0.0f, 1.0f, 0.0f, 0.0f ) );    
        
        // Set up the texture attributes
        //could be REPLACE, BLEND or DECAL instead of MODULATE
        //By altering texture attributes we can alter appearance of sun
        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);
        
        Appearance ap = new Appearance();
        ap.setTexture(texture);
        ap.setTextureAttributes(texAttr);
        
        //set up the material
        int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
        Sphere sun = new Sphere(1.0f, primflags, ap);
        
        // Create light from sun
        // PointLight is used to radiate light from the origin (where sun is)
        PointLight sunlight = new PointLight();
        sunlight.setCapability(PointLight.ALLOW_POSITION_WRITE);
        sunlight.setPosition(0.0f,0.0f,0.0f);
        sunlight.setInfluencingBounds(bounds);

        
        // MAKING RELATIONS BETWEEN THE SCENE GRAPH NODES
	// mercury is transformed by mercuryTG2 then mercuryTG1
        // sun is at the origin 
	objRoot.addChild(mainTG);
	mainTG.addChild(mercuryTG0);
        mainTG.addChild(venusTG0);
        //MERCURY
	mercuryTG0.addChild(mercuryTG1);
	mercuryTG1.addChild(mercuryTG2);
        mercuryTG2.addChild(mercury);
        mercuryTG0.addChild(rotatorMercury);
        mercuryTG2.addChild(rotatorMercury2);
        //VENUS
        venusTG0.addChild(venusTG1);
        venusTG1.addChild(venusTG2);
        venusTG2.addChild(venus);
        venusTG0.addChild(rotatorVenus);
        venusTG2.addChild(rotatorVenus2);
        //SUN
	mainTG.addChild(sun);
        mainTG.addChild(sunlight);
	
	// Create the rotate behavior node
	MouseRotate behavior = new MouseRotate();
	behavior.setTransformGroup(mainTG);
	objRoot.addChild(behavior);
	behavior.setSchedulingBounds(bounds);
	
	// Create the zoom behavior node
	MouseZoom behavior2 = new MouseZoom();
	behavior2.setTransformGroup(mainTG);
	objRoot.addChild(behavior2);	
        behavior2.setSchedulingBounds(bounds);
	
	// Create the translate behavior node
	 MouseTranslate behavior3 = new MouseTranslate();
	behavior3.setTransformGroup(mainTG);
	objRoot.addChild(behavior3);
	behavior3.setSchedulingBounds(bounds);

	objRoot.compile();
	return objRoot;
        
    }
    
    public SolarSystem(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Container cp = getContentPane();
	cp.setLayout(new BorderLayout());
	Canvas3D c = new Canvas3D(SimpleUniverse.getPreferredConfiguration() );
	cp.add("Center", c);
	BranchGroup scene = createSceneGraph();
	SimpleUniverse u = new SimpleUniverse(c);
	u.getViewingPlatform().setNominalViewingTransform();
	u.addBranchGraph(scene);
    	//create a viewing platform
	TransformGroup cameraTG = u.getViewingPlatform().getViewPlatformTransform();
	// starting postion of the viewing platform
	Vector3f translate = new Vector3f(); 
      	Transform3D T3D = new Transform3D();
	// move along z axis by 10.0f ("move away from the screen") 
	translate.set( 0.0f, 0.0f, 15.0f);
        T3D.setTranslation(translate);
	cameraTG.setTransform(T3D);
        setTitle("Solar System");
        setSize(700,700);
        setVisible(true);
    }
    
    public static void main (String[] args){
        SolarSystem system = new SolarSystem();
    }
}
