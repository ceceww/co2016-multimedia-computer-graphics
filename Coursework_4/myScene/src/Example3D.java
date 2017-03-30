
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 *
 * @author cecew
 */
public class Example3D extends JFrame {
    
    public BranchGroup createSceneGraph() {
        
        // creating the bounds of the universe
	// see mouse behaviour below 
	BoundingSphere bounds =
	    new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
 
	// creating branch group
	BranchGroup objRoot = new BranchGroup();
      
	// creating transform group
	TransformGroup mainTG = new TransformGroup();		
	mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        
        //TRAIN
        
        //creating a rotation interpolator for train so it will move along a circular path
        TransformGroup trainTG0 = new TransformGroup();
	trainTG0.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Alpha rotationAlphaTrain = new Alpha(-1, 8000);
        //set ending angle to 2*pi radians so path is full circle
        Transform3D yAxis = new Transform3D();
        RotationInterpolator rotatorTrain = new RotationInterpolator(rotationAlphaTrain,
         trainTG0, yAxis, 0.0f, (float) Math.PI * (2.0f));
        rotatorTrain.setSchedulingBounds(bounds);
        
         // creating another transform group (new trainTG1, from train3d) positioning train from origin
	Transform3D train3d = new Transform3D();
        train3d.rotY(-Math.PI/2);
        train3d.setTranslation(new Vector3d(0.0, 0.0 ,-1.3));
	TransformGroup trainTG1 = new TransformGroup(train3d);
        
        BranchGroup bgTrain = Train.train(); //create a new train
        
        //TRACK
        // creating transform group (new trackTG1, from track3d) which positions the track
	Transform3D track3d = new Transform3D();
        track3d.setTranslation(new Vector3d(0.0,-0.08,0.0));
	TransformGroup trackTG1 = new TransformGroup(track3d);
        
        BranchGroup bgTrack = Track.track();
        
        //BRIDGE
        
        //transformation to position bridge over track
        Transform3D bridge3d = new Transform3D();
        bridge3d.setTranslation(new Vector3d(0.87, 0.0 ,0.0));
        TransformGroup bridgeTG = new TransformGroup(bridge3d);
        
        BranchGroup bgBridge = Bridge.bridge();
        
        //TERRAIN
        
        //transformation to position the terrain
        Transform3D terrain3d = new Transform3D();
        terrain3d.setTranslation(new Vector3d(0.0, -0.14 ,0.0));
        TransformGroup terrainTG = new TransformGroup(terrain3d);
        
        BranchGroup bgTerrain = Terrain.terrain();
        
        Color3f lightColor = new Color3f (1.0f, 1.0f, 1.0f);
 	Vector3f light1Direction = new Vector3f (0.0f, 0.0f, -1f);
        DirectionalLight light1  = new DirectionalLight (lightColor, light1Direction);
    	light1.setInfluencingBounds (bounds);

    	AmbientLight ambientLightNode = new AmbientLight (lightColor);
    	ambientLightNode.setInfluencingBounds (bounds);

        
        //relations between nodes
        objRoot.addChild(mainTG);
        objRoot.addChild(light1); //lighting
        objRoot.addChild (ambientLightNode);
        mainTG.addChild(trainTG0);
        
       //make train move
        trainTG0.addChild(trainTG1);
        trainTG1.addChild(bgTrain);
        trainTG0.addChild(rotatorTrain);
 
        //track
        mainTG.addChild(trackTG1);
        trackTG1.addChild(bgTrack);
        
        //bridge
        mainTG.addChild(bridgeTG);
        bridgeTG.addChild(bgBridge);
       
        //terrain
        mainTG.addChild(terrainTG);
        terrainTG.addChild(bgTerrain);
        
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
        //create a viewing platform
	TransformGroup cameraTG = u.getViewingPlatform().getViewPlatformTransform();
	// starting postion of the viewing platform
        Vector3f translate = new Vector3f(); 
      	Transform3D T3D = new Transform3D();
	// move along z axis by 7.0f ("move away from the screen") 
	translate.set( 0.0f, 0.0f, 7.0f);
        T3D.setTranslation(translate);
	cameraTG.setTransform(T3D);
        setTitle("Train");
        setSize(700,700);
        setVisible(true);
    }

    public static void main(String[] args) {        

	Example3D train = new Example3D();

    }
}
