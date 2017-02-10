/**
 * 
 * Time-stamp: <2017-01-24 17:29:51 rlc3>
 * 
 * ImageManipulation.java
 *
 * Class allows the manipulation of an image by 
 * three alternative methods.
 *
 * @author Roy Crole
 * 
 */

import java.awt.image.*;
import java.awt.*;
import java.util.*; 

public class ImageManipulation { 

    static public void linearBox(BufferedImage image, int n, int x, int y, int size) {
					 
       BufferedImage temp = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
       (temp.getGraphics()).drawImage(image, 0, 0, image.getWidth(), image.getHeight(),null);
        
       if((MouseInfo.getPointerInfo().getLocation().x<image.getWidth())&&(MouseInfo.getPointerInfo().getLocation().y<image.getHeight())){
           for (int i=x-size; i<=x+size; i++) {
               for (int j=y-size; j<=y+size; j++){
  
	        int O = x-size;
                int P = x+size;
                int D = x+n;
		
                if(i>O&&i<D){
                    int prei = linTrans(O, i, D, P);
                    image.setRGB(i, j, temp.getRGB(prei, j));
                }
                else{
                     image.setRGB(i,j,0xaaaaaa);
                }            
               }
           }
        }
   }

 
    static int linTrans (int O, int i, int D, int P) {
        double a = D-P;
        double b = D-O;
        double m = a/b;
        double k = D - D*m; 
        int prei = (int) (i*m + k);
    	return prei;
    } 
    
    static public void linearOct(BufferedImage image, int n, int x, int y, int size) {
					 
	BufferedImage temp=new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        (temp.getGraphics()).drawImage(image, 0, 0, image.getWidth(), image.getHeight(),null);
        
        if((MouseInfo.getPointerInfo().getLocation().x<image.getWidth())&&(MouseInfo.getPointerInfo().getLocation().y<image.getHeight())){
           for (int i=x-size; i<=x+size; i++) {
                for (int j=y-size; j<=y+size; j++){
                    
		    int [] pre = new int[2];
		    int preI; 
		    int preJ;

                    int I = i-x;
		    int J = -(j-y);
                    
		    double d =  Math.sqrt(I*I + J*J);
             
                    if (d<size){
                        
         	     if (I<size&&0<(-J)&&(-J)<I) {
                       pre = octlinTrans(0, d, size, I, J);
		       preI = pre[0]; preJ = pre[1];
                       int prei = preI+x;
                       int prej = -preJ+y;
                       image.setRGB(i, j, temp.getRGB(prei,prej));
                     } 
                     
		     else { 
                         
                        if(I<size&&0<J&&J<I){
                           pre = octlinTrans(0, d, size, I, J); 
                           preI = pre[0]; preJ = pre[1];
                           int prei = preI+x;
                           int prej = -preJ+y;
                           image.setRGB(i,j, temp.getRGB(prei,prej));
                        }
                         
                        else { 
                            if(-size<I&&0<-J&&I<J){
                               pre = octlinTrans(0, d, size, I, J); 
                               preI = -pre[0]; preJ = -pre[1];
                               int prei = preI+x;
                               int prej = -preJ+y;
                               image.setRGB(i,j, temp.getRGB(prei,prej));
                            } 
		        }
                     }
             
                    } else { 
                      image.setRGB(i,j,0xaaaaaa);
		      } 

	        }
       	    } 
        }
    } 
 
    static int[] octlinTrans (int O, double d, int D, int I, int J) { 
   	  		  
        int [] list = new int[2];
                    
        float fI = (float) I;
        float fJ = (float) J;
                    
        float joveri = fJ/fI;
                
        double theta = Math.atan(joveri);
                   
        double P =  D/(Math.cos(theta));
             
		  
        int intd = (int) d;
                   
        int intP = (int) P;
                
        double pred = linTrans(O, intd, D, intP);
                    
        double newI = pred * Math.cos(theta);
                    
        double newJ = pred * Math.sin(theta);
                 

	int preI = (int) newI; 
        int preJ = (int) newJ; 

        list[0] = preI;
        list[1] = preJ;
                
	return list; 

    }

    static public void phaseShift(BufferedImage image, int n, int x, int y, int size) {

        BufferedImage temp=new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        (temp.getGraphics()).drawImage(image,0,0,image.getWidth(), image.getHeight(),null);

	for (int i=x-size; i<=Math.min(image.getWidth()-1-2*n, x+size); ++i) {
            for (int j=y-size; j<= Math.min(image.getHeight()-1-2*n, y+size); ++j) {
            
                if (i>=400){
                    image.setRGB(i,j, 0x0000ff);
                }
             
                else{
                    
                   int prep = temp.getRGB(i, j+2*n);
                   int green = (prep >> 8) & 0xFF;
                   
         
                   int a = (prep>>24) & 0xFF;
                   int p = (a<<24) | (green<<16) | (green<<8) | green;
                
                   image.setRGB(i,j,p);
                   
                }


            }
	}
   }
}
