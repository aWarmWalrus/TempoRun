import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;


public class TheGame extends Applet implements ActionListener{

	ArrayList<Jumpy> characters=new ArrayList<Jumpy>();
	ArrayList<Platform> platforms=new ArrayList<Platform>();
	int timeElapsed=0;
	long lastFrame;
	long cumTime;
	//SomeMusicAPI musicReader=new SomeMusicAPI();
	public TheGame(){
		//calls actionPerformed every 30 ms
		Timer myTimer;
		myTimer=new Timer(100, this);
		myTimer.start();
		
		cumTime = System.currentTimeMillis();
		characters.add(new Jumpy(500, 0, -1, 0, new Animation()));
		platforms.add(new Platform(0, 250, 0, 0, Color.black, new Animation()));
		System.out.println("Inside Loop!");

		//gives it a file to read Commented Out because yolo( and its not done yet)
		//SomeMusicAPI.read("file.wav");
		
		//initialize time of last frame
		lastFrame=getTime();
	}
	
	public void paint(Graphics g){
		setSize(600,500);
		//drawImages here
		//for(int x=0;x<characters.size();x++){
//		g.drawRect(characters.get(0).getX(), characters.get(0).getY(), characters.get(0).getWidth(), characters.get(0).getHeight());
			g.drawImage(characters.get(0).getImage(),Math.round(characters.get(0).getX()),Math.round(characters.get(0).getY()),null);
//			System.out.println(characters.get(0).getImage().toString());
		//}
			System.out.println(characters.get(0).getVX());
		//for(int x=0;x<platforms.size();x++){
			//image platform
			g.drawImage(platforms.get(0).getImage(),Math.round(platforms.get(0).getX()),Math.round(platforms.get(0).getY()),null);
		//}
	}
	private Image dbImage; 
	private Graphics dbg; 
	@Override
	public void update(Graphics g){
		
		if (dbImage == null) {
			dbImage = createImage (this.getSize().width, this.getSize().height); 
			dbg = dbImage.getGraphics (); 
		} 
		// clear screen in background 
		dbg.setColor (getBackground ()); 
		dbg.fillRect (0, 0, this.getSize().width, this.getSize().height); 

		// draw elements in background 
		dbg.setColor (getForeground()); 
		paint (dbg); 

		// draw image on the screen 
		g.drawImage (dbImage, 0, 0, this); 
	}
	public long getTime(){
		long timePassed = System.currentTimeMillis() - cumTime;
		cumTime += timePassed;
		
		return System.currentTimeMillis();

	}

	public int getDelta(){
		long time=getTime();
		int delta=(int)(time-lastFrame);
		lastFrame=time;
		return (int) lastFrame;
	}
	public void newPlatform(){

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		long timePassed = System.currentTimeMillis() - cumTime;
		cumTime += timePassed;
		
		
		//pass time to musicAPI
		
		//update position
		for(int x=0;x<characters.size();x++){
			characters.get(x).updatePos(timePassed);
			//scroll through platforms to find one under player x
			for(int pIndex=0;pIndex<platforms.size();pIndex++){
				if(characters.get(x).onTopOf(platforms.get(pIndex))){
					//ends jump
					characters.get(x).setVY(0);
					characters.get(x).setOnPlatform(true);
					characters.get(x).setY(platforms.get(pIndex).getTopY());
				}else{
					//jump still hasnt ended
					characters.get(x).setOnPlatform(false);
				}

			}

		}
		for(int x=0;x<platforms.size();x++){
			//updates platform position & removes platforms when needed
			platforms.get(x).updatePos(timePassed);
			if(platforms.get(x).getX()+platforms.get(x).getWidth()<0){
				platforms.remove(x);
				x--;
			}
		}
		//making the new platforms

	}

}
