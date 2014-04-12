import java.awt.Color;
import java.awt.Image;


public class C_Sprite extends Sprites{
	
	Animation a;
	int collisionTolerance = 1;
	
	public C_Sprite(int x, int y, int vX, int vY, Color C, Animation a){
		super(x,y,vX,vY,C);
		this.a = a;
	}
	
	//get sprite width
	public int getWidth(){
		return a.getImage().getWidth(null);
	}
	
	//get sprite height
	public int getHeight(){
		return a.getImage().getHeight(null);
	}
			
	//y of top
	public float getTopY(){
		return Y;
	}
			
	//y of bot
	public float getBotY(){
		return Y + a.getImage().getHeight(null);
	}
		
	//x of left
	public float getLeftX(){
		return X;
	}
			
	//x of right
	public float getRightX(){
		return X + a.getImage().getWidth(null);
	}
			
	//get sprites image
	public Image getImage(){
		return a.getImage();
	}

		    
	//updates image
	public void update(long timePassed) {
		a.update(timePassed);
	}
	
	public boolean onTopOf(C_Sprite other){
		
		if ((this.getBotY() - other.getTopY() < collisionTolerance) && (this.getRightX() > other.getLeftX() || this.getLeftX() < other.getRightX())) 
			return true;
		
		return false;
	}
	
	public boolean onBotOf(C_Sprite other){
		
		if ((this.getTopY() - other.getBotY() < collisionTolerance) && (this.getRightX() > other.getLeftX() || this.getLeftX() < other.getRightX())) 
			return true;
		
		return false;
	}
	
	public boolean onLeftOf(C_Sprite other){
		if ((this.getRightX() - other.getLeftX() < collisionTolerance) && (this.getBotY() > other.getTopY() || (this.getTopY() < other.getBotY())))
			return true;
			
		return false;
	}
	
	public boolean onRightOf(C_Sprite other) {
		
		if((this.getLeftX() - other.getRightX() < collisionTolerance) && (this.getBotY() > other.getTopY()) || (this.getTopY() < other.getBotY()))
			return true;
		
		return false;
	}

}
