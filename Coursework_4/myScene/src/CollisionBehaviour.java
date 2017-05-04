import java.util.Enumeration;
import javax.media.j3d.Alpha;
import javax.media.j3d.Behavior;
import javax.media.j3d.Bounds;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnCollisionExit;

/**
 * This class initiates two alternating movements
 * when a collision occurs.
 * 
 * @author Cecelia Wisniewska
 *
 */
public class CollisionBehaviour extends Behavior{

   public BranchGroup ufo;

   //The movement is started, and continues, when a collision exit occurs.
   //used in both initialize and process stimulus 
   public WakeupOnCollisionExit i_and_pS_criterion;

   //The Alphas associated with the movements.
   public Alpha[] alphas;

   //Each movement corresponds to an Alpha object;
   //alphas is an array of the (two) Alpha objects
   //with array entry index (0 or 1 indicating which Alpha): 
   int whichAlpha;

   public CollisionBehaviour(BranchGroup theShape, Alpha[] theAlphas,
                              Bounds theBounds) {

     ufo  = theShape;
     alphas = theAlphas;
     setSchedulingBounds(theBounds);

   }

   public void initialize() {
         //At the very first collision, alphas[0] should be carried out.
     whichAlpha = 0;

     i_and_pS_criterion = new WakeupOnCollisionExit(ufo);

     wakeupOn(i_and_pS_criterion);
   }


   public void processStimulus(Enumeration criteria) {
      while (criteria.hasMoreElements())
      {
          WakeupCriterion theCriterion = (WakeupCriterion) criteria.nextElement();

          //Set the starting time to "now".
          alphas[whichAlpha].setStartTime(System.currentTimeMillis());

	  //Next time, the other movement should be carried out.
          if (whichAlpha == 0) {whichAlpha = 1;} else {whichAlpha = 0;}
	  
      } // end while

      	 // set the next wake up (another collision entry) 
          i_and_pS_criterion = new WakeupOnCollisionExit(ufo);
          wakeupOn(i_and_pS_criterion);
   }
}
