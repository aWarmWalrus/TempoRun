import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener{
	
	public Jumpy J;
	public float moveSpeed = .5F;
	
	public Controller (Jumpy jump, int mapWidth, int mapHeight){
		J = jump;
		J.setWidthHeight(mapWidth, mapHeight);
	}
	
	public Jumpy getJumpy(){
		return J;
	}
	public void keyPressed(KeyEvent e) {
		
		int keycode = e.getKeyCode();
		
		if (keycode == KeyEvent.VK_RIGHT){
			J.setVX(moveSpeed);
			
		}
		else if (keycode == KeyEvent.VK_LEFT){
			J.setVX(-moveSpeed);
			
		}
		if (keycode == KeyEvent.VK_SPACE){
			J.jump();
			
		}
			
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int keycode = e.getKeyCode();
		
		if (keycode == KeyEvent.VK_RIGHT){
			J.setVX(0);
		}
		else if (keycode == KeyEvent.VK_LEFT){
			J.setVX(0);
		}

		
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
}
