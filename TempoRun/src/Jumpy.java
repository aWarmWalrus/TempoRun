import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;



public class Jumpy extends C_Sprite{

	final float JUMP_VAL = -1;
	protected boolean onPlatform = false;
	BufferedImage a1=null;
	int mapWidth;
	int mapHeight;
	boolean touchTop = false;
	
	public Jumpy(float x, float y, float vX, float vY, Animation a) {
		super(x, y, vX, vY, a);
		loadImages();
	}
	
	public void setWidthHeight(int W, int H){
		mapWidth = W;
		mapHeight = H;
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
		//if (onPlatform)
			this.setVY(JUMP_VAL);
	}
	
	//change position
	public void updatePos(long timePassed){
		X = X + VX * timePassed;
		if (VY <= -JUMP_VAL && !onPlatform){
			Y = Y + VY * timePassed;
			VY = VY + .100F;
		}
		else if (!onPlatform){
			Y = Y + VY * timePassed;
			VY = VY + .1F;
		}
		else
			Y = Y + VY * timePassed;
		if (X < 0) {X = 0; VX = 0;}
		else if (this.getRightX() > mapWidth){ X = mapWidth - this.getImage().getWidth(null); VX = 0;}
		if (Y < 0) {Y = 0; VY = 0;touchTop = true;}
		else if (this.getBotY() > mapHeight) {Y = mapWidth - this.getImage().getHeight(null); VY = 0;}
//		System.out.println(timePassed);
		this.a.update(timePassed);
	}

}
