import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import javax.media.j3d.Alpha;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.ColoringAttributes;
import javax.media.j3d.Material;
import javax.media.j3d.PointLight;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.TransparencyAttributes;
import javax.swing.JFrame;
import javax.vecmath.Color3f;
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
        
        //PLANETS
        
        //default material for planets
        Material planetmaterial = new Material(); 
        planetmaterial.setSpecularColor(new Color3f(Color.WHITE)); 
        planetmaterial.setDiffuseColor(new Color3f(Color.WHITE));

        //MERCURY

	// creating a rotation interpolator for mercury so mercury will orbit around the sun
	TransformGroup mercuryTG0 = new TransformGroup();
	mercuryTG0.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // 2750 is the time for the rotation 
	// -1 sets up infinite loop
        //it takes mercury 88 days to orbit around the sun
        Alpha rotationAlphaMercury = new Alpha(-1, 2415);
         //rotate 360 degrees (full orbit) around the sun by setting ending angle to 2*pi radians
        Transform3D yAxis = new Transform3D(); //this is also used for the other planets
         RotationInterpolator rotatorMercury = new RotationInterpolator(rotationAlphaMercury,
         mercuryTG0, yAxis, 0.0f, (float) Math.PI * (2.0f));
         rotatorMercury.setSchedulingBounds(bounds);
        
         // creating another transform group (new mercuryTG1, from mer
        // first creating a transformation mer which gives mercury initial position of -1 along z-axis from sun
	Transform3D mercury3d = new Transform3D();
        mercury3d.setScale(new Vector3d(2.0, 2.0 ,2.0));
        mercury3d.setTranslation(new Vector3d(0.0, 0.0 ,-1.0));
	Transform3D helperT3D= new Transform3D();
	//helperT3D.rotZ(Math.PI);
	//t.mul(helperT3D);
	//t.mul(helperT3D);
	TransformGroup mercuryTG1 = new TransformGroup(mercury3d);
        
        // creating a rotation interpolator for a new mercuryTG2
	TransformGroup mercuryTG2 = new TransformGroup();
	mercuryTG2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // this behaviour object causes mercury to rotate around the locale y axis
	// -1 means indefinite loop count 
        //one rotation of mercury takes 59 earth days
        //rotation of mercury is in proportion to its orbit
        Alpha rotationAlphaMercury2 = new Alpha(-1, 1616);
        Transform3D yAxis2 = new Transform3D();
        // RotationInterpolator always rotates around y axis by default 
	// *** see scene graph example 1 for a change to the z axis  *** 
        // *** but try this out yAxis2.rotZ(Math.PI/2); *** 
        RotationInterpolator rotatorMercury2 = new RotationInterpolator(rotationAlphaMercury2,
         mercuryTG2, yAxis2, 0.0f, (float) Math.PI * (2.0f));
         rotatorMercury2.setSchedulingBounds(bounds);

     
        // Set up the texture map for mercury
        
        TextureLoader loadermer = new TextureLoader("mercurytexture.jpeg", "RGB", new Container());
        Texture texturemer = loadermer.getTexture();
        texturemer.setBoundaryModeS(Texture.WRAP);
        texturemer.setBoundaryModeT(Texture.WRAP);
        texturemer.setBoundaryColor( new Color4f( 0.0f, 1.0f, 0.0f, 0.0f ) );    
        
        // Set up the texture attributes
        //could be REPLACE, BLEND or DECAL instead of MODULATE
        TextureAttributes texattmer = new TextureAttributes();
        texattmer.setTextureMode(TextureAttributes.MODULATE);
        
        Appearance apmer = new Appearance();
        apmer.setTexture(texturemer);
        apmer.setTextureAttributes(texattmer);
        apmer.setMaterial(planetmaterial);
        
        //set up the materials
        int primflags = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
      
	//creation of mercury
	Sphere mercury = new Sphere(0.05f,primflags,apmer);
        
        //VENUS
        //creating a rotation interpolator for venus so venus will orbit around the sun
        TransformGroup venusTG0 = new TransformGroup();
        venusTG0.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
       
        //it takes venus 225 days to orbit around the sun
        Alpha rotationAlphaVenus = new Alpha(-1, 6173);
         
        RotationInterpolator rotatorVenus = new RotationInterpolator(rotationAlphaVenus,
         venusTG0, yAxis, 0.0f, (float) Math.PI * (2.0f));
         rotatorVenus.setSchedulingBounds(bounds);
        
        // creating another transform group (new venusTG1, from ven)
        // first creating a transformation ven which gives venus initial position of -2 along z-axis from sun
	Transform3D venus3d = new Transform3D();
        venus3d.setScale(new Vector3d(2.0, 2.0 ,2.0));
        venus3d.setTranslation(new Vector3d(0.0, 0.0 ,-2));
	TransformGroup venusTG1 = new TransformGroup(venus3d);
        
        // creating a rotation interpolator for a new venusTG2
	TransformGroup venusTG2 = new TransformGroup();
	venusTG2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // this behaviour object causes venus to rotate around the locale y axis
	// -1 means indefinite loop count 
        //one rotation of venus takes 243 earth days
        //rotation speed is in proportion to venus orbit around the sun
        Alpha rotationAlphaVenus2 = new Alpha(-1, 6658);
        // RotationInterpolator always rotates around y axis by default 
	// *** see scene graph example 1 for a change to the z axis  *** 
        // *** but try this out yAxis2.rotZ(Math.PI/2); *** 
        RotationInterpolator rotatorVenus2 = new RotationInterpolator(rotationAlphaVenus2,
         venusTG2, yAxis2, 0.0f, -(float) Math.PI * (2.0f)); //change direction of rotation of venus; venus has retrograde rotation.
         rotatorVenus2.setSchedulingBounds(bounds);
         
        // Set up the texture map for venus
        
        TextureLoader loaderven = new TextureLoader("venustexture.jpg", "RGB", new Container());
        Texture textureven = loaderven.getTexture();
        textureven.setBoundaryModeS(Texture.WRAP);
        textureven.setBoundaryModeT(Texture.WRAP);
        textureven.setBoundaryColor( new Color4f( 0.0f, 1.0f, 0.0f, 0.0f ) );    
        
        // Set up the texture attributes
        //could be REPLACE, BLEND or DECAL instead of MODULATE
        TextureAttributes texattven = new TextureAttributes();
        texattven.setTextureMode(TextureAttributes.MODULATE);
        
        Appearance apven = new Appearance();
        apven.setTexture(textureven);
        apven.setTextureAttributes(texattven);
        apven.setMaterial(planetmaterial);
         
        //creation of venus
        Sphere venus = new Sphere (0.1f,primflags,apven);
        
        //EARTH
        //creating a rotation interpolator for earth so earth will orbit around the sun
        TransformGroup earthTG0 = new TransformGroup();
        earthTG0.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
       
        //it takes Earth 365 days to orbit the sun
        Alpha rotationAlphaEarth = new Alpha(-1, 10000);
         
        RotationInterpolator rotatorEarth = new RotationInterpolator(rotationAlphaEarth,
         earthTG0, yAxis, 0.0f, (float) Math.PI * (2.0f));
         rotatorEarth.setSchedulingBounds(bounds);
        
        // creating another transform group (new earthTG1, from ear)
        // first creating a transformation ear which gives earth initial position of -3 along z-axis from sun
	Transform3D earth3d = new Transform3D();
        earth3d.setScale(new Vector3d(2.0, 2.0 ,2.0));
        earth3d.setTranslation(new Vector3d(0.0, 0.0 ,-3));
	TransformGroup earthTG1 = new TransformGroup(earth3d);
        
        // creating a rotation interpolator for a new earthTG2
	TransformGroup earthTG2 = new TransformGroup();
	earthTG2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // this behaviour object causes earth to rotate around the locale y axis
	// -1 means indefinite loop count 
        //earth rotates 365 time in a year
        //earth's rotation is NOT in proportion to it's orbit around the sun
        //because rotation would be too fast and won't look good
        Alpha rotationAlphaEarth2 = new Alpha(-1, 1000);
        // RotationInterpolator always rotates around y axis by default 
	// *** see scene graph example 1 for a change to the z axis  *** 
        // *** but try this out yAxis2.rotZ(Math.PI/2); *** 
        RotationInterpolator rotatorEarth2 = new RotationInterpolator(rotationAlphaEarth2,
        earthTG2, yAxis2, 0.0f, (float) Math.PI * (2.0f));
         rotatorEarth2.setSchedulingBounds(bounds);
         
         // Set up the texture map for earth 
  
        TextureLoader loaderear = new TextureLoader("earthtexture.jpeg", "RGB", new Container());
        Texture textureear = loaderear.getTexture();
        textureear.setBoundaryModeS(Texture.WRAP);
        textureear.setBoundaryModeT(Texture.WRAP);
        textureear.setBoundaryColor( new Color4f( 0.0f, 1.0f, 0.0f, 0.0f ) );    
        
        // Set up the texture attributes
        //could be REPLACE, BLEND or DECAL instead of MODULATE
        TextureAttributes texattear = new TextureAttributes();
        texattear.setTextureMode(TextureAttributes.MODULATE);
        
        Appearance apear = new Appearance();
        apear.setTexture(textureear);
        apear.setTextureAttributes(texattear);
        apear.setMaterial(planetmaterial);
      
        //creation of earth
        Sphere earth = new Sphere (0.15f,primflags,apear);
        
        //MARS
        //creating a rotation interpolator for mars so mars will orbit around the sun
        TransformGroup marsTG0 = new TransformGroup();
        marsTG0.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
       
        //it takes mars 687 days to orbit the sun
        Alpha rotationAlphaMars = new Alpha(-1, 18822);
         
        RotationInterpolator rotatorMars = new RotationInterpolator(rotationAlphaMars,
         marsTG0, yAxis, 0.0f, (float) Math.PI * (2.0f));
         rotatorMars.setSchedulingBounds(bounds);
        
        // creating another transform group (new marsTG1, from mars3d)
        // first creating a transformation mars3d which gives mars initial position of -4 along z-axis from sun
	Transform3D mars3d = new Transform3D();
        mars3d.setScale(new Vector3d(2.0, 2.0 ,2.0));
        mars3d.setTranslation(new Vector3d(0.0, 0.0 ,-4));
	TransformGroup marsTG1 = new TransformGroup(mars3d);
        
        // creating a rotation interpolator for a new marsTG2
	TransformGroup marsTG2 = new TransformGroup();
	marsTG2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // this behaviour object causes mars to rotate around the locale y axis
	// -1 means indefinite loop count
        //it takes mars approx. 24 hrs to rotate once on its axis
        //mars's rotation is NOT in proportion to it's orbit around the sun
        Alpha rotationAlphaMars2 = new Alpha(-1, 1000);
        // RotationInterpolator always rotates around y axis by default 
	// *** see scene graph example 1 for a change to the z axis  *** 
        // *** but try this out yAxis2.rotZ(Math.PI/2); *** 
        RotationInterpolator rotatorMars2 = new RotationInterpolator(rotationAlphaMars2,
        marsTG2, yAxis2, 0.0f, (float) Math.PI * (2.0f));
         rotatorMars2.setSchedulingBounds(bounds);
         
         // Set up the texture map for mars
        
        TextureLoader loadermar = new TextureLoader("marstexture.jpg", "RGB", new Container());
        Texture texturemar = loadermar.getTexture();
        texturemar.setBoundaryModeS(Texture.WRAP);
        texturemar.setBoundaryModeT(Texture.WRAP);
        texturemar.setBoundaryColor( new Color4f( 0.0f, 1.0f, 0.0f, 0.0f ) );    
        
        // Set up the texture attributes
        //could be REPLACE, BLEND or DECAL instead of MODULATE
        TextureAttributes texattmar = new TextureAttributes();
        texattmar.setTextureMode(TextureAttributes.MODULATE);
        
        Appearance apmar = new Appearance();
        apmar.setTexture(texturemar);
        apmar.setTextureAttributes(texattmar);
        apmar.setMaterial(planetmaterial);
        //creation of mars
        Sphere mars = new Sphere (0.075f,primflags,apmar);
        
        //JUPITER
        //creating a rotation interpolator for jupiter so jupiter will orbit around the sun
        TransformGroup jupiterTG0 = new TransformGroup();
        jupiterTG0.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
       
        //it takes jupiter 12yrs to orbit the sun
        Alpha rotationAlphaJupiter = new Alpha(-1, 120000);
         
        RotationInterpolator rotatorJupiter = new RotationInterpolator(rotationAlphaJupiter,
         jupiterTG0, yAxis, 0.0f, (float) Math.PI * (2.0f));
         rotatorJupiter.setSchedulingBounds(bounds);
        
        // creating another transform group (new jupiterTG1, from jupiter3d)
        // first creating a transformation jupiter3d which gives jupiter initial position of -5 along z-axis from sun
	Transform3D jupiter3d = new Transform3D();
        jupiter3d.setScale(new Vector3d(2.0, 2.0 ,2.0));
        jupiter3d.setTranslation(new Vector3d(0.0, 0.0 ,-5));
	TransformGroup jupiterTG1 = new TransformGroup(jupiter3d);
        
        // creating a rotation interpolator for a new jupiterTG2
	TransformGroup jupiterTG2 = new TransformGroup();
	jupiterTG2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // this behaviour object causes jupiter to rotate around the locale y axis
	// -1 means indefinite loop count 
        //it takes jupiter approx. 9hrs to rotate once on its axis
        //jupiter's rotation is NOT in proportion to it's orbit around the sun
        Alpha rotationAlphaJupiter2 = new Alpha(-1, 500);
        // RotationInterpolator always rotates around y axis by default 
	// *** see scene graph example 1 for a change to the z axis  *** 
        // *** but try this out yAxis2.rotZ(Math.PI/2); *** 
        RotationInterpolator rotatorJupiter2 = new RotationInterpolator(rotationAlphaJupiter2,
        jupiterTG2, yAxis2, 0.0f, (float) Math.PI * (2.0f));
         rotatorJupiter2.setSchedulingBounds(bounds);
         
         // Set up the texture map for jupiter
        
        TextureLoader loaderjup = new TextureLoader("jupitertexture.jpg", "RGB", new Container());
        Texture texturejup = loaderjup.getTexture();
        texturejup.setBoundaryModeS(Texture.WRAP);
        texturejup.setBoundaryModeT(Texture.WRAP);
        texturejup.setBoundaryColor( new Color4f( 0.0f, 1.0f, 0.0f, 0.0f ) );    
        
        // Set up the texture attributes
        //could be REPLACE, BLEND or DECAL instead of MODULATE
        TextureAttributes texattjup = new TextureAttributes();
        texattjup.setTextureMode(TextureAttributes.MODULATE);
        
        Appearance apjup = new Appearance();
        apjup.setTexture(texturejup);
        apjup.setTextureAttributes(texattjup);
        apjup.setMaterial(planetmaterial);
        //creation of jupiter
        Sphere jupiter = new Sphere (0.25f,primflags,apjup);
        
        //Saturn
        //creating a rotation interpolator for saturn its rings so they will orbit around the sun
        TransformGroup saturnTG0 = new TransformGroup();
        saturnTG0.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
       
        //it takes Saturn 29yrs to orbit the sun
        Alpha rotationAlphaSaturn = new Alpha(-1, 290000);
         
        RotationInterpolator rotatorSaturn = new RotationInterpolator(rotationAlphaSaturn,
         saturnTG0, yAxis, 0.0f, (float) Math.PI * (2.0f));
         rotatorSaturn.setSchedulingBounds(bounds);
        
        // creating another transform group (new saturnTG1, from saturn3d)
        // first creating a transformation saturn3d which gives saturn and rings initial position of -7 along z-axis from sun
	Transform3D saturn3d = new Transform3D();
        saturn3d.setScale(new Vector3d(2.0, 2.0 ,2.0));
        saturn3d.setTranslation(new Vector3d(0.0, 0.0 ,-7));
	TransformGroup saturnTG1 = new TransformGroup(saturn3d);
        
        // creating a rotation interpolator for a new saturnTG2
	TransformGroup saturnTG2 = new TransformGroup();
	saturnTG2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // this behaviour object causes saturn to rotate around the locale y axis
	// -1 means indefinite loop count 
        //it takes saturn approx. 10 hours to rotate once on it's axis
        //saturn's rotation is NOT in proportion to it's orbit around the sun
        Alpha rotationAlphaSaturn2 = new Alpha(-1, 600);
        // RotationInterpolator always rotates around y axis by default 
	// *** see scene graph example 1 for a change to the z axis  *** 
        // *** but try this out yAxis2.rotZ(Math.PI/2); *** 
        RotationInterpolator rotatorSaturn2= new RotationInterpolator(rotationAlphaSaturn2,
        saturnTG2, yAxis2, 0.0f, (float) Math.PI * (2.0f));
         rotatorSaturn2.setSchedulingBounds(bounds);
         
         // Set up the texture map for saturn 
  
        TextureLoader loadersat = new TextureLoader("saturntexture.jpg", "RGB", new Container());
        Texture texturesat = loadersat.getTexture();
        texturesat.setBoundaryModeS(Texture.WRAP);
        texturesat.setBoundaryModeT(Texture.WRAP);
        texturesat.setBoundaryColor( new Color4f( 0.0f, 1.0f, 0.0f, 0.0f ) );    
        
        // Set up the texture attributes
        //could be REPLACE, BLEND or DECAL instead of MODULATE
        TextureAttributes texattsat = new TextureAttributes();
        texattsat.setTextureMode(TextureAttributes.MODULATE);
        
        Appearance apsat = new Appearance();
        apsat.setTexture(texturesat);
        apsat.setTextureAttributes(texattsat);
        apsat.setMaterial(planetmaterial);
      
        //creation of saturn
        Sphere saturn = new Sphere (0.2f,primflags,apsat);
        
        //SATURN'S RINGS
        // creating an appearance for rings
	Appearance ringsApp = new Appearance();
	Color3f ringsColor = new Color3f(0.75f, 0.7f, 0.5f);
	ColoringAttributes ringsCA = new ColoringAttributes();
        TransparencyAttributes ringsTA = new TransparencyAttributes(TransparencyAttributes.NICEST, 0.3f);
	ringsCA.setColor(ringsColor);
	ringsApp.setColoringAttributes(ringsCA);
        ringsApp.setTransparencyAttributes(ringsTA);
	
        // create rings
	Cylinder rings = new Cylinder(0.25f, 0.01f,ringsApp);
        
        //URANUS
        //creating a rotation interpolator for uranus so uranus will orbit around the sun
        TransformGroup uranusTG0 = new TransformGroup();
        uranusTG0.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
       
        //it takes uranus 84 years to orbit the sun
        Alpha rotationAlphaUranus = new Alpha(-1, 840000);
         
        RotationInterpolator rotatorUranus = new RotationInterpolator(rotationAlphaUranus,
         uranusTG0, yAxis, 0.0f, (float) Math.PI * (2.0f));
         rotatorUranus.setSchedulingBounds(bounds);
        
        // creating another transform group (new uranusTG1, from uranus3d)
        // first creating a transformation uranus3d which gives uranus initial position of -9 along z-axis from sun
	Transform3D uranus3d = new Transform3D();
        uranus3d.setScale(new Vector3d(2.0, 2.0 ,2.0));
        uranus3d.setTranslation(new Vector3d(0.0, 0.0 ,-9));
	TransformGroup uranusTG1 = new TransformGroup(uranus3d);
        
        // creating a rotation interpolator for a new uranusTG2
	TransformGroup uranusTG2 = new TransformGroup();
	uranusTG2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // this behaviour object causes uranus to rotate around the locale y axis
	// -1 means indefinite loop count 
        //it takes approx. 17hrs for uranus to rotate once on its axis
        //uranus's rotation is NOT in proportion to it's orbit around the sun
        Alpha rotationAlphaUranus2 = new Alpha(-1, 800);
        // RotationInterpolator always rotates around y axis by default 
	// *** see scene graph example 1 for a change to the z axis  *** 
        // *** but try this out yAxis2.rotZ(Math.PI/2); *** 
        //set max angle to negative because uranus has retrograde rotation, like venus
        RotationInterpolator rotatorUranus2 = new RotationInterpolator(rotationAlphaUranus2,
        uranusTG2, yAxis2, 0.0f, -(float) Math.PI * (2.0f));
         rotatorUranus2.setSchedulingBounds(bounds);
         
         // Set up the texture map for uranus 
  
        TextureLoader loaderura = new TextureLoader("uranustexture.jpeg", "RGB", new Container());
        Texture textureura = loaderura.getTexture();
        textureura.setBoundaryModeS(Texture.WRAP);
        textureura.setBoundaryModeT(Texture.WRAP);
        textureura.setBoundaryColor( new Color4f( 0.0f, 1.0f, 0.0f, 0.0f ) );    
        
        // Set up the texture attributes
        //could be REPLACE, BLEND or DECAL instead of MODULATE
        TextureAttributes texattura = new TextureAttributes();
        texattura.setTextureMode(TextureAttributes.MODULATE);
        
        Appearance apura = new Appearance();
        apura.setTexture(textureura);
        apura.setTextureAttributes(texattura);
        apura.setMaterial(planetmaterial);
      
        //creation of uranus
        Sphere uranus = new Sphere (0.175f,primflags,apura);
        
        //Neptune
        //creating a rotation interpolator for neptune so neptune will orbit around the sun
        TransformGroup neptuneTG0 = new TransformGroup();
        neptuneTG0.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
       
        //it takes Neptune 165 years to orbit the sun
        Alpha rotationAlphaNeptune = new Alpha(-1, 1650000);
         
        RotationInterpolator rotatorNeptune = new RotationInterpolator(rotationAlphaNeptune,
         neptuneTG0, yAxis, 0.0f, (float) Math.PI * (2.0f));
         rotatorNeptune.setSchedulingBounds(bounds);
        
        // creating another transform group (new neptuneTG1, from neptune3d)
        // first creating a transformation neptune3d which gives neptune initial position of -10 along z-axis from sun
	Transform3D neptune3d = new Transform3D();
        neptune3d.setScale(new Vector3d(2.0, 2.0 ,2.0));
        neptune3d.setTranslation(new Vector3d(0.0, 0.0 ,-10));
	TransformGroup neptuneTG1 = new TransformGroup(neptune3d);
        
        // creating a rotation interpolator for a new neptuneTG2
	TransformGroup neptuneTG2 = new TransformGroup();
	neptuneTG2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // this behaviour object causes neptune to rotate around the locale y axis
	// -1 means indefinite loop count 
        //it takes neptune approx. 19hrs to rotate once on its axis
        //rotation of neptune is NOT in proportion to its orbit around the sun
        Alpha rotationAlphaNeptune2 = new Alpha(-1, 900);
        // RotationInterpolator always rotates around y axis by default 
	// *** see scene graph example 1 for a change to the z axis  *** 
        // *** but try this out yAxis2.rotZ(Math.PI/2); *** 
        RotationInterpolator rotatorNeptune2 = new RotationInterpolator(rotationAlphaNeptune2,
        neptuneTG2, yAxis2, 0.0f, (float) Math.PI * (2.0f));
         rotatorNeptune2.setSchedulingBounds(bounds);
         
         // Set up the texture map for neptune 
  
        TextureLoader loadernep = new TextureLoader("neptunetexture.jpeg", "RGB", new Container());
        Texture texturenep = loadernep.getTexture();
        texturenep.setBoundaryModeS(Texture.WRAP);
        texturenep.setBoundaryModeT(Texture.WRAP);
        texturenep.setBoundaryColor( new Color4f( 0.0f, 1.0f, 0.0f, 0.0f ) );    
        
        // Set up the texture attributes
        //could be REPLACE, BLEND or DECAL instead of MODULATE
        TextureAttributes texattnep = new TextureAttributes();
        texattnep.setTextureMode(TextureAttributes.MODULATE);
        
        Appearance apnep = new Appearance();
        apnep.setTexture(texturenep);
        apnep.setTextureAttributes(texattnep);
        apnep.setMaterial(planetmaterial);
      
        //creation of neptune
        Sphere neptune = new Sphere (0.175f,primflags,apnep);
        
        
        //SUN
        
        Transform3D sun3d = new Transform3D();
        sun3d.setScale(new Vector3d(1.0, 1.0 ,1.0));
        sun3d.setTranslation(new Vector3d(0.0, 0.0 ,0.0));
	TransformGroup sunTG1 = new TransformGroup(sun3d);
        
        //rotation of sun
        
        // creating a rotation interpolator for a new sunTG0
	TransformGroup sunTG2 = new TransformGroup();
	sunTG2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
        // this behaviour object causes earth to rotate around the locale y axis
	// -1 means indefinite loop count 
        Alpha rotationAlphaSun = new Alpha(-1, 20000);
        // RotationInterpolator always rotates around y axis by default 
	// *** see scene graph example 1 for a change to the z axis  *** 
        // *** but try this out yAxis2.rotZ(Math.PI/2); *** 
        RotationInterpolator rotatorSun = new RotationInterpolator(rotationAlphaSun,
        sunTG2, yAxis2, 0.0f, (float) Math.PI * (2.0f));
         rotatorSun.setSchedulingBounds(bounds);
        
        // Set up the texture map for sun
        
        TextureLoader loadersun = new TextureLoader("suntexture.jpg", "RGB", new Container());
        Texture texturesun = loadersun.getTexture();
        texturesun.setBoundaryModeS(Texture.WRAP);
        texturesun.setBoundaryModeT(Texture.WRAP);
        texturesun.setBoundaryColor( new Color4f( 0.0f, 1.0f, 0.0f, 0.0f ) );    
        
        // Set up the texture attributes
        //could be REPLACE, BLEND or DECAL instead of MODULATE
        //By altering texture attributes we can alter appearance of sun
        TextureAttributes texAttSun = new TextureAttributes();
        texAttSun.setTextureMode(TextureAttributes.MODULATE);
        
        Appearance apsun = new Appearance();
        apsun.setTexture(texturesun);
        apsun.setTextureAttributes(texAttSun);
    
        //creation of sun
        Sphere sun = new Sphere(0.5f, primflags, apsun);
        
        // Create light from sun
        // PointLight is used to radiate light from the origin (where sun is)
        PointLight sunlight = new PointLight();
        sunlight.setCapability(PointLight.ALLOW_POSITION_WRITE);
        sunlight.setCapability(PointLight.ALLOW_ATTENUATION_WRITE);
        sunlight.setPosition(0.0f,0.0f,0.0f);
        sunlight.setAttenuation(0.5f,0.0f,0.0f); //how much it lights up the planets
        sunlight.setInfluencingBounds(bounds);

        
        // MAKING RELATIONS BETWEEN THE SCENE GRAPH NODES
	// planets are transformed by TG2 then TG1
        // sun is at the origin 
	objRoot.addChild(mainTG);
	mainTG.addChild(mercuryTG0);
        mainTG.addChild(venusTG0);
        mainTG.addChild(earthTG0);
        mainTG.addChild(marsTG0);
        mainTG.addChild(jupiterTG0);
        mainTG.addChild(saturnTG0);
        mainTG.addChild(uranusTG0);
        mainTG.addChild(neptuneTG0);
        mainTG.addChild(sunTG1);
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
        //EARTH
        earthTG0.addChild(earthTG1);
        earthTG1.addChild(earthTG2);
        earthTG2.addChild(earth);
        earthTG0.addChild(rotatorEarth);
        earthTG2.addChild(rotatorEarth2);
        //MARS
        marsTG0.addChild(marsTG1);
        marsTG1.addChild(marsTG2);
        marsTG2.addChild(mars);
        marsTG0.addChild(rotatorMars);
        marsTG2.addChild(rotatorMars2);
        //JUPITER
        jupiterTG0.addChild(jupiterTG1);
        jupiterTG1.addChild(jupiterTG2);
        jupiterTG2.addChild(jupiter);
        jupiterTG0.addChild(rotatorJupiter);
        jupiterTG2.addChild(rotatorJupiter2);
        //SATURN
        saturnTG0.addChild(saturnTG1);
        saturnTG1.addChild(saturnTG2);
        saturnTG1.addChild(rings);
        saturnTG2.addChild(saturn);
        saturnTG0.addChild(rotatorSaturn);
        saturnTG2.addChild(rotatorSaturn2);
        //URANUS
        uranusTG0.addChild(uranusTG1);
        uranusTG1.addChild(uranusTG2);
        uranusTG2.addChild(uranus);
        uranusTG0.addChild(rotatorUranus);
        uranusTG2.addChild(rotatorUranus2);
        //NEPTUNE
        neptuneTG0.addChild(neptuneTG1);
        neptuneTG1.addChild(neptuneTG2);
        neptuneTG2.addChild(neptune);
        neptuneTG0.addChild(rotatorNeptune);
        neptuneTG2.addChild(rotatorNeptune2);
        //SUN
        sunTG1.addChild(sunTG2);
        sunTG2.addChild(sun);
        sunTG2.addChild(rotatorSun);
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
	translate.set( 0.0f, 0.0f, 10.0f);
        T3D.setTranslation(translate);
	cameraTG.setTransform(T3D);
        setTitle("Solar System");
        setSize(700, 700);
        setVisible(true);
    }
    
    public static void main (String[] args){
        SolarSystem system = new SolarSystem();
    }
}
