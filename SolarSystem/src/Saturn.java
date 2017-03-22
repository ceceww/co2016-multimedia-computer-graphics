
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.BorderLayout;
import java.awt.Container;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.PointLight;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cecew
 */
public class Saturn extends JFrame{
     private BranchGroup createSceneGraph() {
        
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 1000);
 
	// creating a branch group
	BranchGroup objRoot = new BranchGroup();

	// creating a transform group 
	TransformGroup mainTG = new TransformGroup();		
	mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	mainTG.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
        
        Transform3D yAxis = new Transform3D();
   
        TransformGroup saturnTG0 = new TransformGroup();
        saturnTG0.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        
        //this the rotation causing orbit (so apply to both rings and planet)
       
        Alpha rotationAlphaSaturn = new Alpha(-1, 8000);
         
        RotationInterpolator rotatorSaturn = new RotationInterpolator(rotationAlphaSaturn,
         saturnTG0, yAxis, 0.0f, (float) Math.PI * (2.0f));
         rotatorSaturn.setSchedulingBounds(bounds);
        
        
	Transform3D saturn3d = new Transform3D();
        saturn3d.setScale(new Vector3d(2.0, 2.0 ,2.0));
        saturn3d.setTranslation(new Vector3d(0.0, 0.0 ,-15));
	TransformGroup saturnTG1 = new TransformGroup(saturn3d);
        
        //Only for the sphere
	TransformGroup saturnTG2 = new TransformGroup();
	saturnTG2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        Alpha rotationAlphaSaturn2 = new Alpha(-1, 8000);
        Transform3D yAxis2 = new Transform3D();
        // RotationInterpolator always rotates around y axis by default 
	// *** see scene graph example 1 for a change to the z axis  *** 
        // *** but try this out yAxis2.rotZ(Math.PI/2); *** 
        RotationInterpolator rotatorSaturn2 = new RotationInterpolator(rotationAlphaSaturn2,
         saturnTG2, yAxis2, 0.0f, (float) Math.PI * (2.0f));
         rotatorSaturn2.setSchedulingBounds(bounds);
         
         Sphere saturn = new Sphere(0.5f);
         
         
        // creating an appearance for rings
	Appearance ringsApp = new Appearance();
	Color3f ringsColor = new Color3f(0.75f, 0.7f, 0.5f);
	ColoringAttributes ringsCA = new ColoringAttributes();
	ringsCA.setColor(ringsColor);
	ringsApp.setColoringAttributes(ringsCA);
	
        // create rings
	Cylinder rings = new Cylinder(0.7f, 0.05f,ringsApp);
         
         
         // Create light from sun
        // PointLight is used to radiate light from the origin (where sun is)
        PointLight sunlight = new PointLight();
        sunlight.setCapability(PointLight.ALLOW_POSITION_WRITE);
        sunlight.setCapability(PointLight.ALLOW_ATTENUATION_WRITE);
        sunlight.setPosition(0.0f,0.0f,0.0f);
        sunlight.setAttenuation(0.3f,0.0f,0.0f); //how much it lights up the planets
        sunlight.setInfluencingBounds(bounds);
        
        
        saturnTG0.addChild(saturnTG1);
        saturnTG1.addChild(rings);
	saturnTG1.addChild(saturnTG2);
        saturnTG2.addChild(saturn);
        saturnTG0.addChild(rotatorSaturn);
        saturnTG2.addChild(rotatorSaturn2);
        objRoot.addChild(mainTG);
	mainTG.addChild(saturnTG0);
        
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
     
     public Saturn(){
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
        setTitle("Saturn");
        setSize(700,700);
        setVisible(true);
     }
     
      public static void main (String[] args){
        Saturn saturn = new Saturn();
    }
     
     
    
}
