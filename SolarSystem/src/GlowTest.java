
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
import javax.media.j3d.Bounds;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Material;
import javax.media.j3d.PointLight;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.SpotLight;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author cecew
 */
public class GlowTest extends JFrame{
    
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
        
        Transform3D star3d = new Transform3D();
        star3d.setScale(new Vector3d(2.0, 2.0 ,2.0));
        star3d.setTranslation(new Vector3d(0.0, 0.0 ,-20));
	TransformGroup starTG = new TransformGroup(star3d);
        
        Sphere star = new Sphere(0.05f);
        
        Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
        Color3f black = new Color3f (0.0f,0.0f,0.0f);
        
         Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);

         DirectionalLight light1 = new DirectionalLight(white, light1Direction);
         light1.setInfluencingBounds(bounds);
      
        Material material = new Material();
        material.setDiffuseColor(black);
        material.setSpecularColor(white);
        material.setShininess(7.0f);
        Appearance apstar = new Appearance();
        apstar.setMaterial(material);
      
        star.setAppearance(apstar);
        
      /*  SpotLight spotlight = new SpotLight();
        spotlight.setInfluencingBounds(bounds);
        spotlight.setPosition(new Point3f(0.0f, -0.7f, 0.5f));
        spotlight.setSpreadAngle(0.5f);
        spotlight.setConcentration(50.0f);
        
        objRoot.addChild(spotlight);*/
      
        objRoot.addChild(mainTG);
        mainTG.addChild(starTG);
        starTG.addChild(star);
        mainTG.addChild(light1);
        
   
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
   
    
    
    
    
    public GlowTest(){
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
	translate.set( 0.0f, 0.0f, 10.0f);
        T3D.setTranslation(translate);
	cameraTG.setTransform(T3D);
        setTitle("Solar System");
        setSize(700,700);
        setVisible(true);
    }
    
    public static void main (String[] args){
        GlowTest test = new GlowTest();
    }
}

