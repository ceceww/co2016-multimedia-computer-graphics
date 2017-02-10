/**
 *
 * @author cecew
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*; 
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public final class rgbButtons extends JFrame implements ActionListener {

    private BufferedImage redI; 
    private JLabel redL;
    private Button redB;
    private BufferedImage greenI;
    private JLabel greenL;
    private Button greenB;
    private BufferedImage blueI;
    private JLabel blueL;
    private Button blueB;
    
    public rgbButtons() {
	setTitle("Simple Gui: Select Colour Squares with Buttons");
	setSize(1200,800);
	setDefaultCloseOperation(EXIT_ON_CLOSE);

	Container cp = new Container(); 
        
        add(cp);

        try {
            redI  = ImageIO.read(new File("red.gif"));
        }
        catch (Exception e){}

        redL  = new JLabel(new ImageIcon(redI));
        redL.setLocation(100,400);
        redL.setSize(100,100);
        redL.setVisible(false);
        cp.add(redL);
        // ??
	// set location, size etc and add to contentpane 

	redB = new Button("redon");
        redB.setLocation(100,100);
        redB.setSize(100,100);
        cp.add(redB);
	// ?? 
	// set location, size etc and add to contentpane 

        redB.addActionListener(this);
        
	// ?? 
	// addActionListener 
        
         try {
            greenI  = ImageIO.read(new File("green.gif"));
        }
        catch (Exception e){}
         
        greenL  = new JLabel(new ImageIcon(greenI));
        greenL.setLocation(400,400);
        greenL.setSize(100,100);
        greenL.setVisible(false);
        cp.add(greenL);
        
        greenB = new Button("greenon");
        greenB.setLocation(400,100);
        greenB.setSize(100,100);
        cp.add(greenB);
        
        greenB.addActionListener(this);
        
        try {
            blueI  = ImageIO.read(new File("blue.gif"));
        }
        catch (Exception e){}
         
        blueL  = new JLabel(new ImageIcon(blueI));
        blueL.setLocation(700,400);
        blueL.setSize(100,100);
        blueL.setVisible(false);
        cp.add(blueL);
        
        blueB = new Button("blueon");
        blueB.setLocation(700,100);
        blueB.setSize(100,100);
        cp.add(blueB);
        
        blueB.addActionListener(this);
         

    } // end constructor rgbButtons 
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource()== redB){
            if(redB.getLabel().equals("redon")){
                redL.setVisible(true);
                redB.setLabel("redoff");
            }
            else {
                redL.setVisible(false);
                redB.setLabel("redon");
            }
        }
        // ??
	// if event source is redB labelled "redon", make the red
	// square appear and set lable to redoff
	// otherwise ... 

   	// ??
	// similar code for green and blue
        
          if (e.getSource()== greenB){
            if(greenB.getLabel().equals("greenon")){
                greenL.setVisible(true);
                greenB.setLabel("greenoff");
            }
            else {
                greenL.setVisible(false);
                greenB.setLabel("greenon");
            }
        }
          
           if (e.getSource()== blueB){
            if(blueB.getLabel().equals("blueon")){
                blueL.setVisible(true);
                blueB.setLabel("blueoff");
            }
            else {
                blueL.setVisible(false);
                blueB.setLabel("blueon");
            }
        }

    } // end actionPerformed

    public static void main(String[] args) {
	rgbButtons i= new rgbButtons();
	i.setVisible(true);

    }  // Main method  

} // end JFrame

