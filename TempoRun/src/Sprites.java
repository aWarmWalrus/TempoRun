import java.awt.Color;


public class Sprites {
	
	protected int X;
	protected int Y;
	protected int VX;
	protected int VY;
	protected Color color;
	
	
	public Sprites(int x, int y, int vX, int vY, Color C){
		this.X = x;
		this.Y = y;
		this.VX = vX;
		this.VY = vY;
		this.color = C;
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
	
	public Color getColor(){
		return color;
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
	
	public void setColor(Color c){
		this.color = c;
	}

}
