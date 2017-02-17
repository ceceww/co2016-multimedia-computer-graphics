import javax.swing.*;
import java.applet.*;
import java.net.*;
import java.awt.*;
import java.awt.event.*;

public final class Example1 extends JApplet implements ActionListener{
    AudioClip track;
    public void init() {
	try{
	track = getAudioClip(new URL(getCodeBase(),"Front_Center.wav"));
	} catch (Exception e) {}
	track.play();
	Button b = new Button("play");
	getContentPane().add(b);
	b.addActionListener(this);
    }
     public void actionPerformed(ActionEvent e){
	track.play();
    }
}
