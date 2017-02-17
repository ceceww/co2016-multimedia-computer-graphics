/**
 * RGBFrame.java
 *
 * Time-stamp: <2017-01-20 13:35:26 rlc3> edited
 *
 * Converts an image into versions where each pixel p
 * is converted into either a RED, GREEN or BLUE version
 * by extracting the R, G or B component from the original pixel 
 * 
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class RGBFrame extends JFrame {

    public final String RED = "red";
    public final String BLUE = "green";
    public final String GREEN = "blue";
    
    private BufferedImage original;
    private JLabel original_L, rgb_r_L, rgb_g_L, rgb_b_L;
    
    public RGBFrame() {
	setTitle("Image Manipulation Tool");
	setSize(1200,800);
	setDefaultCloseOperation(EXIT_ON_CLOSE);

	Container cp = getContentPane();
	cp.setLayout(null);

	try {
	    //  original = ImageIO.read(new File("face.gif"));
	    original = ImageIO.read(new File("consciousness.jpg"));
	}
	catch (Exception e){}

	// display the original and also filtered images 
	original_L  = new JLabel(new ImageIcon(original));
	original_L.setBounds(0,0,400,400);
	cp.add(original_L);

	rgb_r_L  = new JLabel(new ImageIcon(filter(original,RED)));
	rgb_r_L.setBounds(450,0,400,400);
	cp.add(rgb_r_L);
	rgb_g_L  = new JLabel(new ImageIcon(filter(original,GREEN)));
	rgb_g_L.setBounds(450,450,400,400);
	cp.add(rgb_g_L);

	rgb_b_L  = new JLabel(new ImageIcon(filter(original,BLUE)));
	rgb_b_L.setBounds(0,450,400,400);
	cp.add(rgb_b_L);

    } // IFrame constructor

    // make R, G or B copies of original image
    private BufferedImage filter (BufferedImage img, String choice) {
	BufferedImage ans = new BufferedImage(
				img.getWidth(),img.getHeight(),
				BufferedImage.TYPE_INT_RGB);
	int graylvl;
	for (int x=0; x<img.getWidth(); x++) {
	    for (int y=0;y<img.getHeight();y++) {

		switch (choice) {
		case BLUE : graylvl= (img.getRGB(x,y) & 0xff);
		ans.setRGB(x,y,graylvl);
		    break;
		case GREEN : graylvl =(img.getRGB(x,y) & 0xff00) ;
		ans.setRGB(x,y,graylvl);
		    break;
		case RED : graylvl = (img.getRGB(x,y) & 0xff0000);
		ans.setRGB(x,y,graylvl);
		} 
    	       
               // Try writing a modified switch statement
	       // for each pixel, extract the 8-bit RED, GREEN or BLUE component C
	       // then set the pixel to have RGB colour c.c.c (grayscale) 

	    }
	}
	return ans;
    }

    public static void main(String[] args) {
	RGBFrame i= new RGBFrame();
	i.setVisible(true);
    }

} // RGBFrame
