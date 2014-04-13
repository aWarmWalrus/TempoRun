import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener{
	
	TheGame game; 
	Jumpy J;
	
	public Controller (TheGame g){
		game = g;
		J = game.characters.get(0);
		g.addKeyListener(g);
	}
	public void keyPressed(KeyEvent e) {
		
		int keycode = e.getKeyCode();
		
		if (keycode == KeyEvent.VK_RIGHT){
			J.setVX(10 + game.platforms.get(0).VX);
		}
		else if (keycode == KeyEvent.VK_LEFT){
			game.characters.get(0).setVX(game.platforms.get(0).VX-10);
		}
		if (keycode == KeyEvent.VK_SPACE){
			game.characters.get(0).jump();
		}
			
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int keycode = e.getKeyCode();
		
		if (keycode == KeyEvent.VK_RIGHT){
			J.setVX(game.platforms.get(0).VX);
		}
		else if (keycode == KeyEvent.VK_LEFT){
			game.characters.get(0).setVX(game.platforms.get(0).VX);
		}

		
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	
}
