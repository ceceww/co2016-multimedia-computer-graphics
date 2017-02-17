/**
 * A simple Java sound file example (Java code to play a sound file).
 * 
 * Time-stamp: <2017-01-24 18:14:27 rlc3>
 * 
 */

import java.io.*;
import sun.audio.*;
import javax.sound.sampled.*;
import java.util.*;

public class Example3 {

  public static void main(String[] args) {
  
  try {
    // open the sound file as a Java input stream
    String myAudioFile = "Front_Center.wav";
    InputStream in = new FileInputStream(myAudioFile);

    // create an audiostream from the inputstream
    AudioStream audioStream = new AudioStream(in);

    // play the audio clip with the audioplayer class
    AudioPlayer.player.start(audioStream);
  } catch(Exception e){}

}} // End Example3 
