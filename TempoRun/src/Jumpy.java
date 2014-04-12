

public class Jumpy extends C_Sprite{

	final int JUMP_VAL = 15;
	protected boolean onPlatform = false;
	
	public Jumpy(int x, int y, int vX, int vY, Animation a) {
		super(x, y, vX, vY, a);
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
		X += VX * timePassed;
		if (VY > 0){
			Y += VY * timePassed;
			VY -= 1;
		}
		this.a.update(timePassed);
	}

}
