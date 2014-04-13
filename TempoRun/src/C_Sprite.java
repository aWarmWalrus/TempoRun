

public class C_Sprite extends Sprites{
	
	Animation a;
	int collisionTolerance = 1;
	
	public C_Sprite(int x, int y, int vX, int vY, Animation a){
		super(x,y,vX,vY);
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
	public int getTopY(){
		return Y;
	}
			
	//y of bot
	public int getBotY(){
		return Y + a.getImage().getHeight(null);
	}
		
	//x of left
	public int getLeftX(){
		return X;
	}
			
	//x of right
	public int getRightX(){
		return X + a.getImage().getWidth(null);
	}
			
	    
	//updates image
	public void update(long timePassed) {
		a.update(timePassed);
	}
	
	public boolean onTopOf(C_Sprite other){
		
		if (((Math.abs(this.getBotY() - other.getTopY()) <= collisionTolerance) || ((this.getBotY() > other.getTopY()) && (this.getBotY() < other.getBotY()))) && (this.getRightX() > other.getLeftX() || this.getLeftX() < other.getRightX())) 
			return true;
		
		return false;
	}
	
	public boolean onBotOf(C_Sprite other){
		
		if ((Math.abs(this.getTopY() - other.getBotY()) <= collisionTolerance) && (this.getRightX() > other.getLeftX() || this.getLeftX() < other.getRightX())) 
			return true;
		
		return false;
	}
	
	public boolean onLeftOf(C_Sprite other){
		if ((Math.abs(this.getRightX() - other.getLeftX()) <= collisionTolerance) && (this.getBotY() > other.getTopY() || (this.getTopY() < other.getBotY())))
			return true;
			
		return false;
	}
	
	public boolean onRightOf(C_Sprite other) {
		
		if((Math.abs(this.getLeftX() - other.getRightX()) <= collisionTolerance) && (this.getBotY() > other.getTopY()) || (this.getTopY() < other.getBotY()))
			return true;
		
		return false;
	}

}
