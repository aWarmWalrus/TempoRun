import java.awt.Color;
import java.awt.Image;


public class Sprites {
	
	protected float X;
	protected float Y;
	protected float VX;
	protected float VY;
	protected Animation a;
	
	public Sprites(float x, float y, float vX, float vY){
		this.X = x;
		this.Y = y;
		this.VX = vX;
		this.VY = vY;
	}
	
	public Animation getAnimation(){
		return a;
	}
	
	public float getX(){
		return X;
	}
	
	public float getY(){
		return Y;
	}
	
	public float getVX(){
		return VX;
	}
	
	public float getVY(){
		return VY;
	}
	
	
	public void setAnimation(Animation i){
		a = i;
	}
	
	public void setX(float x){
		this.X = x;
	}
	
	public void setY(float Y){
		this.Y = Y;
	}
	
	public void setVX(float vx){
		this.VX = vx;
	}
	
	public void setVY(float vy){
		this.VY = vy;
	}
	
	
	//change position
	public void updatePos(long timePassed){
		X += VX * timePassed;
		Y += VY * timePassed;
		//this.a.update(timePassed);
	}
	
	//get sprites image
	public Image getImage(){
		return a.getImage();
	}

}
