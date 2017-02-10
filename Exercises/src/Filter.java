
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cecew
 */
public class Filter {
    
    public static final int RED = 2;
    public static final int BLUE = 0;
    public static final int GREEN = 1;
    
    private static BufferedImage filter (BufferedImage img , int choice ) {
    BufferedImage ans = new BufferedImage (img.getWidth() , img.getHeight(),BufferedImage.TYPE_INT_RGB);
    int color;
    for(int i =0; i<img.getWidth();i++) {
    for(int j =0; j<img.getHeight();j++) {
    switch(choice) {
    case BLUE : color= (img.getRGB(i,j)&0xff);
    ans.setRGB(i,j,color);
    break;
    case GREEN : color =(img.getRGB(i,j)&0xff00);
    ans.setRGB(i,j,color);
    break;
    case RED: color=(img.getRGB(i,j) & 0xff0000);
    ans.setRGB(i,j,color);
    } }}
    return ans;}
    
    public static void main(String[] args) {
        BufferedImage original = null;
        File outputRed = new File("filteredRed.jpg");
        
       try{
	    original = ImageIO.read(new File("consciousness.jpg"));
	}
	catch (Exception e){}
       try{
           ImageIO.write(filter(original, RED), "jpg", outputRed);
       } 
       catch (Exception e){}
    }
}
