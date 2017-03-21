
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.media.j3d.Alpha;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author cecew
 */
public class Practice0 extends JFrame{
    
    public Practice0(){
       this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Container cp = getContentPane();
	cp.setLayout(new BorderLayout());
	Canvas3D c = new Canvas3D(SimpleUniverse.getPreferredConfiguration() );
	cp.add("Center", c);
	BranchGroup scene = createSceneGraph();
	SimpleUniverse u = new SimpleUniverse(c);
	u.getViewingPlatform().setNominalViewingTransform();
	u.addBranchGraph(scene);

    	// *** create a viewing platform
	TransformGroup cameraTG = u.getViewingPlatform().
	                            getViewPlatformTransform();
	// starting postion of the viewing platform
	Vector3f translate = new Vector3f(); 
      	Transform3D T3D = new Transform3D();
	// move along z axis by 10.0f ("move away from the screen") 
	translate.set( 0.0f, 0.0f, 10.0f);
        T3D.setTranslation(translate);
	cameraTG.setTransform(T3D);
        setTitle("Java 3D Practice");
        setSize(512,512);
        setVisible(true);
    }
    
    public BranchGroup createSceneGraph() {
        
        // creating the bounds of the universe
	// see mouse behaviour below 
	BoundingSphere bounds =
	    new BoundingSphere(new Point3d(0.0,0.0,0.0), 10.0);
        
        BranchGroup objRoot = new BranchGroup();
        BranchGroup bgA = new BranchGroup();
        
        // creating a transform group 
	TransformGroup mainTG = new TransformGroup();		
	mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        
        TransformGroup TGA = new TransformGroup();		
	// set the capability to TRANSFORM the TGA (by ROTATION) 
	TGA.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	TGA.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        
        objRoot.addChild(mainTG);
        mainTG.addChild(bgA);
        bgA.addChild(TGA);
        
        
        Transform3D yAxis = new Transform3D();
        Transform3D zAxis = new Transform3D();
        yAxis.rotX(Math.PI/2); 
        zAxis = yAxis; // use SENSIBLE VARIABLE NAMES !!
        Alpha rotationAlphaTGA = new Alpha(-1, 4000);
        
        RotationInterpolator rotatorTGA = new RotationInterpolator(rotationAlphaTGA,
         TGA, zAxis, 0.0f, (float) Math.PI * (2.0f));
         rotatorTGA.setSchedulingBounds(bounds);
         
         // creating a transform group (cubeTG1, from t)
        // first creating a transformation t 
	Transform3D t = new Transform3D();
        t.setTranslation(new Vector3d(0.0, 0.0 ,0.0));
	TransformGroup cubeTG = new TransformGroup(t);
        
        ColorCube c = new ColorCube();
        
        TGA.addChild(cubeTG);
        cubeTG.addChild(c);
        TGA.addChild(rotatorTGA);
        
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
    
    public static void main (String[] args){
        Practice0 practice = new Practice0();
    }
    
}
