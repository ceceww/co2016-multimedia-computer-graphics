
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseWheelZoom;
import com.sun.j3d.utils.picking.behaviors.PickTranslateBehavior;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.BoundingBox;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.PositionInterpolator;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/**
 * 3D environment consisting of a train, track, bridge along the track, ufo, 
 * tree, terrain, and cube (object used for collision detection)
 * 
 * Navigation: Arrow keys (keyboard) to rotate and move, 
 * RMB to click and drag cube, mouse wheel to zoom
 * 
 * @author Cecelia Wisniewska
 *
 */

public class Example3D extends JFrame {
    
    public Canvas3D c;
    
    public BranchGroup createSceneGraph() {
        
        // creating the bounds of the universe
	// see mouse behaviour below 
	BoundingSphere bounds =
	    new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
 
	// creating branch group
	BranchGroup objRoot = new BranchGroup();
        
	// creating a main transform group
	TransformGroup mainTG = new TransformGroup();		
        // set the capability to TRANSFORM the main TG (eg mouse MOVEMENT) 
	mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        objRoot.addChild(mainTG);
        
        /* TRAIN  --------------------------------- */
        
        // creating a rotation interpolator for train so it will move along a circular path
        TransformGroup trainTG1 = new TransformGroup();
	trainTG1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        Alpha rotationAlphaTrain = new Alpha(-1, 8000);
        Transform3D yAxis = new Transform3D();
        // set maximum angle to 2*pi radians so path is full circle
        RotationInterpolator rotatorTrain = new RotationInterpolator(rotationAlphaTrain,
         trainTG1, yAxis, 0.0f, (float) Math.PI * (2.0f));
        rotatorTrain.setSchedulingBounds(bounds);
        
        // creating another transform group (new trainTG2, from train3d) positioning train from origin
        // first creating a transformation train3d
	Transform3D train3d = new Transform3D();
        train3d.rotY(-Math.PI/2);
        train3d.setTranslation(new Vector3d(0.0, 0.0 ,-1.3));
	TransformGroup trainTG2 = new TransformGroup(train3d);
        
        // creating branch group for train
        BranchGroup train = Train.train(); 
        
        trainTG1.addChild(trainTG2);
        trainTG2.addChild(train);
        trainTG1.addChild(rotatorTrain);
        
        /* END TRAIN  --------------------------------- */
        
        /* TRACK  --------------------------------- */
        
        // creating transform group (new trackTG, from track3d) which positions the track
        // first creating a transformation track3d
	Transform3D track3d = new Transform3D();
        track3d.setTranslation(new Vector3d(0.0,-0.08,0.0));
	TransformGroup trackTG = new TransformGroup(track3d);
        
        // creating branch group for track
        BranchGroup track = Track.track();
       
        trackTG.addChild(track);
        
        /* END TRACK  --------------------------------- */
        
        /* BRIDGE  --------------------------------- */
        
        // creating transform group (new bridgeTG, from bridge3d) which positions bridge over track
        // first creating a transformation bridge3d
        Transform3D bridge3d = new Transform3D();
        bridge3d.rotY(Math.PI/4);
        bridge3d.setTranslation(new Vector3d(0.5, 0.0 ,-0.6));
        TransformGroup bridgeTG = new TransformGroup(bridge3d);
        
        // creating branch group for bridge
        BranchGroup bridge = Bridge.bridge();
        
        bridgeTG.addChild(bridge);
        
        /* END BRIDGE  --------------------------------- */
        
        /* TERRAIN  --------------------------------- */
        
        // creating transform group (new terrainTG, from terrain3d) which positions the terrain
        // first creating a transformation terrain3d
        Transform3D terrain3d = new Transform3D();
        terrain3d.setTranslation(new Vector3d(0.0, -0.14 ,0.0));
        TransformGroup terrainTG = new TransformGroup(terrain3d);
        
        // creating branch group for terrain
        BranchGroup terrain = Terrain.terrain();
        terrainTG.addChild(terrain);
       
        /* END TERRAIN  --------------------------------- */
        
        /* LIGHTING --------------------------------- */
        
        // Set up the global lights
	// first the ambient light
	// light from all directions
	// typically use white or 'gray' light
        Color3f alColor = new Color3f (0.6f, 0.6f, 0.6f);
        AmbientLight al = new AmbientLight (alColor);  
        al.setInfluencingBounds(bounds);
        // next the directional light
        // parallel light rays come FROM infinity TOWARDS the vector lightDir1 
 	Vector3f lightDir1 = new Vector3f (0.0f, 0.0f, -1f);
        Color3f lightColor1 = new Color3f(1.0f,1.0f,1.0f);
        DirectionalLight light1  = new DirectionalLight (lightColor1, lightDir1);
        // light has no effect, ie Influence, outside of the bounds 
    	light1.setInfluencingBounds (bounds);
        
        Vector3f lightDir2  = new Vector3f(1.0f,-1.0f,0.5f);
        DirectionalLight light2 = new DirectionalLight(lightColor1, lightDir2);
        light2.setInfluencingBounds(bounds);
        
        // add the lighting
        objRoot.addChild(al);
        objRoot.addChild(light1);
        objRoot.addChild(light2);
        
        /* END LIGHTING --------------------------------- */ 
        
        /* UFO  --------------------------------- */
        
        // creating transform group (new ufoTG1, from ufo3d) which positions ufo
        // first creating a transformation ufo3d
        Transform3D ufo3d = new Transform3D();
        ufo3d.setTranslation(new Vector3d(-2.0, 0.1 ,0.0));
        TransformGroup ufoTG1 = new TransformGroup(ufo3d);
        
        // creating a rotation interpolator for a new ufoTG2
	TransformGroup ufoTG2 = new TransformGroup();
	ufoTG2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	// -1 means indefinite loop count 
        Alpha rotationAlphaUfo = new Alpha(-1, 5000);
        RotationInterpolator rotatorUfo = new RotationInterpolator(rotationAlphaUfo,
        ufoTG2, new Transform3D(), 0.0f, -(float) Math.PI * (2.0f));
         rotatorUfo.setSchedulingBounds(bounds);
         
        // creating branch group for ufo
        BranchGroup ufo = UFO.ufo();
        
        ufoTG1.addChild(ufoTG2);
        ufoTG2.addChild(rotatorUfo);
        
        // --- *** begin ufo movement --
         
        //This transformation group is needed for the movement of the ufo.
         TransformGroup tgmUFO = new TransformGroup();
         tgmUFO.addChild(ufo);
         
        //The movement up and down (along z axis)
        Transform3D xAxis = new Transform3D();
        xAxis.rotZ(Math.PI/2);
        float maxDist = 1.5f;
        
        // An alpha for the down to up movement 
        // Alpha(number_of_times_for_movement,time_movement_takes) 
        Alpha ufoAlphaU = new Alpha(1,2000);
        //The starting time is first postponed until "infinity".
        ufoAlphaU.setStartTime(Long.MAX_VALUE);
        //The interpolator for the movement.
        // PosInt(theAlpha, TGroup_to_attach_to, axis_of_movement_default_X_Axis, start_position, end_position)
        PositionInterpolator ufoMoveU = new PositionInterpolator(ufoAlphaU,tgmUFO,
                                                                 xAxis,0.0f,maxDist);
        // An alpha for the up to down movement 
        Alpha ufoAlphaD = new Alpha(1,2000);
        //The starting time is first postponed until "infinity".
        ufoAlphaD.setStartTime(Long.MAX_VALUE);
        //The interpolator for the movement.
        PositionInterpolator ufoMoveD = new PositionInterpolator(ufoAlphaD,tgmUFO,
                                                                 xAxis,maxDist,0.0f);
        
        ufoMoveU.setSchedulingBounds(bounds);
        ufoMoveD.setSchedulingBounds(bounds);
        
        //Add the movements to the transformation group.
        tgmUFO.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        tgmUFO.addChild(ufoMoveU);
        tgmUFO.addChild(ufoMoveD);
        
        ufoTG2.addChild(tgmUFO);
        
        // --- end UFO movement --
         
        /* END UFO --------------------------------- */ 
         
        /* *** COLLISION DETECTION --------------------------------- */
        
        //The CollisionBounds for ufo.
        ufo.setCollisionBounds(new BoundingBox(new Point3d(0.0,-0.15,0.0),
                                                new Point3d(0.1,0.15,0.1)));
        
        // Enabled for collision purposes (the default is true)
        ufo.setCollidable(true);

        //CollisionBehaviour class takes care of the movement(s) of the ufo.
        Alpha[] ufoAlphas= new Alpha[2];
        ufoAlphas[0] = ufoAlphaU;
        ufoAlphas[1] = ufoAlphaD;
        CollisionBehaviour scb2 = new CollisionBehaviour(ufo,ufoAlphas,bounds);
        mainTG.addChild(scb2);
        
        /* END COLLISION DETECTION --------------------------------- */
        
        /* TREE  --------------------------------- */
        
        // creating transform group (new trunkTG, from trunk3d) which positions trunk
        // first creating a transformation trunk3d
        Transform3D trunk3d = new Transform3D();
        trunk3d.setTranslation(new Vector3d(2.0, 0.0 ,0.0));
        TransformGroup trunkTG = new TransformGroup(trunk3d);
        
        // creating branch group for trunk
        BranchGroup trunk = Tree.trunk();
        
        trunkTG.addChild(trunk);
        
        // creating transform group (new leavesTG, from leaves3d) which positions leaves
        // first creating a transformation leaves3d
        Transform3D leaves3d = new Transform3D();
        leaves3d.setTranslation(new Vector3d(2.0, 0.5 ,0.0));
        TransformGroup leavesTG = new TransformGroup(leaves3d);
        
        // creating branch group for leaves
        BranchGroup leaves = Tree.top();
        leavesTG.addChild(leaves);
        
        /* END TREE  --------------------------------- */
         
        /* CUBE  --------------------------------- */
      
        // creating transform group (new cubeTG, from cube3d) which positions cube
        // first creating a transformation cube3d
        Transform3D cube3d = new Transform3D();
        cube3d.setTranslation(new Vector3d(0.0, 0.03 ,0.0));
        TransformGroup cubeTG = new TransformGroup(cube3d);
        
        // creating branch group for cube
        BranchGroup cube = CollidingObject.cube();
        cubeTG.addChild(cube);
        
        /* END CUBE  --------------------------------- */
        
        
        // make remaining edge relations between the scene graph nodes
        mainTG.addChild(trainTG1);
        mainTG.addChild(trackTG);
        mainTG.addChild(bridgeTG);
        mainTG.addChild(terrainTG);
        mainTG.addChild(ufoTG1);
        mainTG.addChild(trunkTG);
        mainTG.addChild(leavesTG);
        mainTG.addChild(cubeTG);
        
        // Create the rotate behavior node
	MouseRotate behavior = new MouseRotate();
	behavior.setTransformGroup(mainTG);
	objRoot.addChild(behavior);
	behavior.setSchedulingBounds(bounds);
	
	// Create the zoom behavior node
        MouseWheelZoom behavior2 = new MouseWheelZoom();
        behavior2.setTransformGroup(mainTG);
	objRoot.addChild(behavior2);	
        behavior2.setSchedulingBounds(bounds);
        
        // Enable navigation using keyboard
        // This is needed because PickTrans requires use of RMB
        KeyNavigatorBehavior knb = new KeyNavigatorBehavior(mainTG);
        knb.setSchedulingBounds(bounds);
        objRoot.addChild(knb);
      
        // The PickTranslateBehavior for moving the cube.
        PickTranslateBehavior pickTrans = new PickTranslateBehavior(cube,c,bounds);
        cubeTG.addChild(pickTrans); 
    
        objRoot.compile();
        return objRoot;
    }
    
    // create a "standard" universe using SimpleUniverse 
    public Example3D() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	Container cp = getContentPane();
	cp.setLayout(new BorderLayout());
	c = new Canvas3D(SimpleUniverse.getPreferredConfiguration() );
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
	translate.set(0.0f, 1.0f, 8.0f);
        T3D.setTranslation(translate);
	cameraTG.setTransform(T3D);
        setTitle("Train Scene");
        setSize(700,700);
        setVisible(true);
    }

    public static void main(String[] args) {        

	Example3D train = new Example3D();

    }
}
