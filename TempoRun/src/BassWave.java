import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;


public class BassWave extends C_Sprite{

	
	BufferedImage a1=null;
	BufferedImage a2=null;
	BufferedImage a3=null;
	BufferedImage a4=null;
	BufferedImage a5=null;
	BufferedImage img = null;
	Random rand = new Random();
	
	public BassWave(float x, float y, float vX, float vY, Animation a) {
		super(x, y, vX, vY, a);
		LoadImages();
		setImage();
	}
	
public void LoadImages(){
		
		try {
			a1 = ImageIO.read(new File("basswave1.png"));
			a2 = ImageIO.read(new File("basswave2.png"));
			a3 = ImageIO.read(new File("basswave3.png"));
			a4 = ImageIO.read(new File("basswave4.png"));
			a5 = ImageIO.read(new File("basswave5.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		a.addScene(a1, 50);
		a.addScene(a2, 50);
		a.addScene(a3, 50);
		a.addScene(a4, 50);
		a.addScene(a5, 50);
	}	

	public void setImage(){
		switch (rand.nextInt(5)){
		case 0: img = a1; break;
		case 1: img = a2; break;
		case 2: img = a3; break;
		case 3: img = a4;break;
		case 4: img = a5;break;
		default: img = a5;
	}
}
	
	public Image getImage(){
		
		return img;
		
	}
	
	

}
