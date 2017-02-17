/**
 * A simple Java sound file example (Java code to play a sound file).
 * 
 * Can be modified to work as within an applet ...
 * 
 * Time-stamp: <2017-01-24 18:34:45 rlc3>
 */

import java.awt.*;
import java.applet.*;
import java.net.*;

public class Example0 {

    public static void main(String[] args) {

	try{

        AudioClip ac = Applet.newAudioClip(new URL(
			        "file:Front_Center.wav"));
        ac.play ();
	// What happens if the next two lines are removed?
	// Why does this happen?
        System.out.println ("Press return to exit.");
        System.in.read ();
        ac.stop ();

	} catch(Exception e){}

	System.exit(0);

    }

} // Example0
