import java.awt.Color;
import java.awt.Image;

import javax.swing.ImageIcon;


public class Platform extends C_Sprite{

	protected Color color;
	
	public Platform(int x, int y, int vX, int vY, Color C, Animation a) {
		super(x, y, vX, vY, a);
		color = C;
		LoadImages();
	}
	
	public Image getImage(){
		return new ImageIcon("platform1.png").getImage();
	}
	public void LoadImages(){
		Image a1 = new ImageIcon("platform1.png").getImage();
		Image a2 = new ImageIcon("platform2.png").getImage();
		Image a3 = new ImageIcon("platform3.png").getImage();
		Image a4 = new ImageIcon("platform4.png").getImage();
		Image a5 = new ImageIcon("platform5.png").getImage();
		Image a6 = new ImageIcon("platform6.png").getImage();
		a.addScene(a1, 50);
		a.addScene(a2, 50);
		a.addScene(a3, 50);
		a.addScene(a4, 50);
		a.addScene(a5, 50);
		a.addScene(a6, 50);
	}
	
	public Color getColor(){
		return color;
	}
	
	public void setColor(Color c){
		color = c;
	}
	
	

	
	

}
