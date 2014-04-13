import java.awt.Color;


public class Sprites {
	
	protected int X;
	protected int Y;
	protected int VX;
	protected int VY;
	protected Animation a;
	
	public Sprites(int x, int y, int vX, int vY){
		this.X = x;
		this.Y = y;
		this.VX = vX;
		this.VY = vY;
	}
	
	public Animation getAnimation(){
		return a;
	}
	
	public int getX(){
		return X;
	}
	
	public int getY(){
		return Y;
	}
	
	public int getVX(){
		return VX;
	}
	
	public int getVY(){
		return VY;
	}
	
	
	public void setAnimation(Animation i){
		a = i;
	}
	
	public void setX(int x){
		this.X = x;
	}
	
	public void setY(int Y){
		this.Y = Y;
	}
	
	public void setVX(int vx){
		this.VX = vx;
	}
	
	public void setVY(int vy){
		this.VY = vy;
	}
	
	
	//change position
	public void updatePos(long timePassed){
		X += VX * timePassed;
		Y += VY * timePassed;
		this.a.update(timePassed);
	}

}
