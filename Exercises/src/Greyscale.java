
import java.awt.Container;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cecew
 */
public class Greyscale extends JFrame {
    
     public final String gray = "gray";
    
    private BufferedImage original;
    private JLabel original_L, greyscale_L;
    
     public Greyscale() {
	setTitle("Image Manipulation Tool");
	setSize(1200,800);
	setDefaultCloseOperation(EXIT_ON_CLOSE);

	Container cp = getContentPane();
	cp.setLayout(null);
        
        try{
	    original = ImageIO.read(new File("consciousness.jpg"));
	}
	catch (Exception e){}
        
        original_L  = new JLabel(new ImageIcon(original));
	original_L.setBounds(0,0,400,400);
	cp.add(original_L);
        
        greyscale_L  = new JLabel(new ImageIcon(greyscale(original)));
	greyscale_L.setBounds(450,0,400,400);
	cp.add(greyscale_L);
     }
     
     private BufferedImage greyscale(BufferedImage img) {
        BufferedImage ans = new BufferedImage(img.getWidth(),img.getHeight(), BufferedImage.TYPE_INT_RGB);
        for (int x=0; x<img.getWidth(); x++) {
	    for (int y=0;y<img.getHeight();y++) {
               int rgb = img.getRGB(x, y);
               int r = (rgb >> 16) & 0xFF;
               int g = (rgb >> 8) & 0xFF;
               int b = (rgb & 0xFF);

               int grayLevel = (r + g + b) / 3;
               int gray = (grayLevel << 16) + (grayLevel << 8) + grayLevel; 
               ans.setRGB(x, y, gray);
                }
              
        }
      return ans;
     }
    
   public static void main(String[] args) {
        Greyscale i = new Greyscale();
	i.setVisible(true);
    }
}
