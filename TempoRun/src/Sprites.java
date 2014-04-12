import java.awt.Color;


public class Sprites {
	
	private int X;
	private int Y;
	private int VX;
	private int VY;
	private Color color;
	
	
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
	
	public void setX(int x){
		this.X = x;
	}
	

}
