/**
 * Describe class (Step 5) Example3D here.
 *
 * Time-stamp: <2017-02-10 18:45:48 rlc3> edited by rlc 
 *
 */

// import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.*;
import com.sun.j3d.utils.behaviors.mouse.*; 
import javax.swing.JFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Box;

public class Example3D extends JFrame {
    
  private SimpleUniverse u = null;

    public BranchGroup createSceneGraph() {

	// creating the bounds of the universe
	// see mouse behaviour below 
	BoundingSphere bounds =
	    new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
 
	// creating a branch group
	BranchGroup objRoot = new BranchGroup();

	// creating a transform group 
	TransformGroup mainTG = new TransformGroup();		
	mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);

	// creating another transform group (cubeTG1, from t)
        // first creating a transformation t 
	Transform3D t = new Transform3D();
        t.setScale(new Vector3d(2.0, 2.0 ,2.0));
	t.setTranslation(new Vector3d(0.0, 0.0 ,-5));
        // scale by 2 THEN translate -5 along z-axis !!
	// Let T be the matrix for the translation; ditto S. 
        // So M_t = T . S
	Transform3D helperT3D= new Transform3D();
	helperT3D.rotZ(Math.PI); // sets helperT3D to be rotation around Z by pi
	t.mul(helperT3D);
	// now M_t = (T . S) . RZ 
	helperT3D.rotX(Math.PI/2);
	t.mul(helperT3D);
	// now M_t = ((T . S) . RZ) . RX = T . S . RZ . RX  
        // so t is rotX then rotZ then scale by 2 then translate -5 along z-axis 
	TransformGroup cubeTG1 = new TransformGroup(t);

	// creating another transform group (cubeTG2, from tt)
        // to illustrate the order of execution of transform groups 
	// from child node c back to objRoot at (***)
	Transform3D tt = new Transform3D();
	tt.rotY(Math.PI);
	TransformGroup cubeTG2 = new TransformGroup(tt);

	// creating an appearance (for a box)
	Appearance greenApp = new Appearance();
	Color3f greenColor = new Color3f(0.0f, 1.0f, 0.0f);
	ColoringAttributes greenCA = new ColoringAttributes();
	greenCA.setColor(greenColor);
	greenApp.setColoringAttributes(greenCA);
	
        // create a box
	Box b = new Box(0.8f,0.8f,.1f,greenApp);

	// creating 3D shapes: colorcubes
        // try altering the ".5" sizing parameter
	ColorCube c = new ColorCube(.5);
	ColorCube co = new ColorCube(.5);

        // make edge relations between the scene graph nodes
	// cube c is transformed by cubeTG2 then cubeTG1 (***)
        // cube co and box b at the origin 
	objRoot.addChild(mainTG);
	mainTG.addChild(cubeTG1);
	cubeTG1.addChild(cubeTG2);
        cubeTG2.addChild(c);
	mainTG.addChild(b);
	mainTG.addChild(co);
	
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
 
    // create a "standard" universe using SimpleUniverse 
    public Example3D() {
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
        setTitle("Step 5: A cube+green box and translated scaled rotated cube");
        setSize(700,700);
        setVisible(true);
    }

    public static void main(String[] args) {        

	Example3D step5 = new Example3D();

    }
    
}


