import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;


public class TheGame implements ActionListener{
	ArrayList<C_Sprite> sprites=new ArrayList<C_Sprite>();
	public TheGame(){
		Timer myTimer;
		myTimer=new Timer(30, this);
		myTimer.start();
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
