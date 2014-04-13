import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class BassWave extends C_Sprite{

	
	BufferedImage a1 = null;
	public BassWave(float x, float y, float vX, float vY, Animation a) {
		super(x, y, vX, vY, a);
		loadImages();
	}
	
	public void loadImages(){
		try{
			a1 = ImageIO.read(new File("platform1.png"));
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		a.addScene(a1, 50);
	}
	

	
	public Image getImage(){
		
		return a1;
		
	}
	
	

}
