import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;


public class TheGame extends Applet implements ActionListener{
	
	ArrayList<Jumpy> characters=new ArrayList<Jumpy>();
	ArrayList<Platform> platforms=new ArrayList(Platform)();
	
	long lastFrame;
	SomeMusicAPI musicReader=new SomeMusicAPI();
	public TheGame(){
		//calls actionPerformed every 30 ms
		Timer myTimer;
		myTimer=new Timer(30, this);
		myTimer.start();
		//gives it a file to read
		SomeMusicAPI.read("file.wav");
		//initialize time of last frame
		lastFrame=getTime();
	}
	public void paint(Graphics g){
		//drawImages here
		for(int x=0;x<characters.size();x++){
			g.drawImage(characters.get(x).getX(),characters.get(x).getY(),characters.get(x).getImage());
		}
		for(int x=0;x<platforms.size();x++){
			//image platform
			g.drawImage(platforms.get(x).getX(),platforms.get(x).getY(),platforms.get(x).getImage());
		}
	}
	public long getTime(){
		return System.currentTimeMillis();
	}
	
	public int getDelta(){
		long time=getTime();
		int delta=(int)(time-lastFrame);
		lastFrame=time;
		return (Integer) null;
	}
	public void newPlatform(){
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		int delta=getDelta();
		//update position
		for(int x=0;x<characters.size();x++){
			characters.get(x).update(delta);
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
			platforms.get(x).update(delta);
			if(platforms.get(x).getX()+platforms.get(x).getLength()<0){
				platforms.remove(x);
				x--;
			}
		}
		//making the new platforms
		
	}
	
}
