/**
 * Describe class EndlessTest here.
 *
 * Time-stamp: <2017-01-24 18:17:42 rlc3> edited by rlc
 *
 */

public final class EndlessTest {

    public static void main (String[] args) {

	EndlessCount c1= new EndlessCount('a');
	EndlessCount c2= new EndlessCount('b');

	System.out.println("Let's start both threads!");
	System.out.println("Press Any Key to Continue ... ");

  	try{System.in.read ();} catch(Exception e){}

	c2.start();
	c1.start();

	System.out.println("Both threads started");
	for (int i=1; i<300;++i)
	System.out.println("main counter:" +i);

	c1.stop();
	System.out.println("first thread stopped");
	c2.stop();
	System.out.println("second thread stopped");

    }

}
