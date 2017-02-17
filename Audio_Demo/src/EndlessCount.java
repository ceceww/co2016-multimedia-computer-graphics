/**
 * A simple Java program to illustrate threads 
 * 
 * Time-stamp: <2017-01-24 18:17:23 rlc3>
 * 
 */

public class EndlessCount implements Runnable {
    
    Thread thread;
    char id;

    public EndlessCount(char c) {
	id=c;
    }

    // start the thread 
    public void start() {
	thread = new Thread(this);
	thread.start();
    }
    
    // stop the thread
    public void stop() {
	thread = null;
    }
    
    // what to do when the thread runs 
    public void run() {
	int c=0;
	while (thread!=null) {
	    System.out.println(id +": "+c++);
	}
    } 
} 
