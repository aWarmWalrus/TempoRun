import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener{
	
	public Jumpy J;
	public float moveSpeed = .5F;
	
//<<<<<<< HEAD
//	public Controller (TheGame g){
//		game = g;
//		J = game.characters.get(0);
//		g.addKeyListener(g);
//=======
	public Controller (Jumpy jump, int mapWidth, int mapHeight){
		J = jump;
		J.setWidthHeight(mapWidth, mapHeight);
	}
	
	public Jumpy getJumpy(){
		return J;
//>>>>>>> 00f1a7f0d04bdd2721f7a2f9ac079c9bc8b23db6
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
