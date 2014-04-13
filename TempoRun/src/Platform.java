import java.awt.Color;


public class Platform extends C_Sprite{

	protected Color color;
	
	public Platform(int x, int y, int vX, int vY, Color C, Animation a) {
		super(x, y, vX, vY, a);
		color = C;
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setColor(Color c){
		color = c;
	}
	
	

	
	

}
