import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



public class Jumpy extends C_Sprite{

	final int JUMP_VAL = 15;
	protected boolean onPlatform = false;
	BufferedImage a1=null;
	public Jumpy(float x, float y, float vX, float vY, Animation a) {
		super(x, y, vX, vY, a);
		loadImages();
	}
	
	public void loadImages(){
		a1 = null;
		try {
			a1 = ImageIO.read(new File("character.png"));
		} catch (IOException e){
			
		}
		a.addScene(a1, 50);
	}
	
	public Image getImage(){
		/*
		a1 = null;
		try {
			a1 = ImageIO.read(new File("character.png"));
		} catch (IOException e){
			
		}*/
		return a1;
//		return new ImageIcon("F:\\KINGSTON\\HackRU2014\\TempoRun\\TempoRun\\character.png").getImage();
	}
	public boolean isOnPlatform(){
		return onPlatform;
	}
	
	public void setOnPlatform(boolean b){
		onPlatform = b;
	}
	
	public void jump(){
		if (onPlatform)
			this.setVY(JUMP_VAL);
	}
	
	//change position
	public void updatePos(long timePassed){
//		System.out.println("Update Ran! X is now " + X);
		X = X + VX * timePassed;
		if (VY > 0){
			Y = Y + VY * timePassed;
			VY = VY - 1;
		}
		X = Math.max(X, 0);
//		System.out.println(timePassed);
		this.a.update(timePassed);
	}

}
