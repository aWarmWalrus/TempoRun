import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Platform extends C_Sprite{

	protected Color color;
	BufferedImage a1=null;
	BufferedImage a2=null;
	BufferedImage a3=null;
	BufferedImage a4=null;
	BufferedImage a5=null;
	BufferedImage a6=null;
	public Platform(float x, float y, float vX, float vY, Color C, Animation a) {
		super(x, y, vX, vY, a);
		color = C;
		LoadImages();
	}
	
	public Image getImage(){
		return a1;
	}
	public void LoadImages(){
		
		try {
			a1 = ImageIO.read(new File("platform1.png"));
			a2 = ImageIO.read(new File("platform2.png"));
			a3 = ImageIO.read(new File("platform3.png"));
			a4 = ImageIO.read(new File("platform4.png"));
			a5 = ImageIO.read(new File("platform5.png"));
			a6 = ImageIO.read(new File("platform6.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		a.addScene(a1, 50);
		a.addScene(a2, 50);
		a.addScene(a3, 50);
		a.addScene(a4, 50);
		a.addScene(a5, 50);
		a.addScene(a6, 50);
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setColor(Color c){
		color = c;
	}
	
	

	
	

}
