import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;


public class TheGame extends Applet implements ActionListener{

	Controller player;
	Jumpy J;
	ArrayList<Platform> platforms=new ArrayList<Platform>();
	ArrayList<BassWave> BassWaves= new ArrayList<BassWave>();
	//ArrayList<Song> playlist = new ArrayList<Song>();
	Song theSong;
	int timeElapsed=0;
	long lastFrame;
	long cumTime;
	int mapWidth = 1000;
	int mapHeight = 1000;
	Random rand;
	int score = 0;
	//SomeMusicAPI musicReader=new SomeMusicAPI();
	public TheGame() throws FileNotFoundException{
		//calls actionPerformed every 30 ms
		Timer myTimer;
		myTimer=new Timer(30, this);
		myTimer.start();

		String currentDir = new File("").getAbsolutePath();
		System.out.println(currentDir);
		theSong = new Song(currentDir + "\\RunAway.wav");
		rand = new Random();

		
		player = new Controller(new Jumpy(0, 0, 0, .1F, new Animation()), mapWidth, mapHeight);
		J = player.J;
		platforms.add(new Platform(0, 550, 0.08F, 0, Color.black, new Animation()));
		platforms.add(new Platform(600, 350, -.05F, 0, Color.black, new Animation()));
		BassWaves.add(new BassWave(600, 350, 0, -.05F, new Animation()));
		this.addKeyListener(player);
		setFocusable(true);
		
		
		Thread ENGINE=new Thread(new engine());
		ENGINE.run();



		
		//initialize time of last frame
		lastFrame=getTime();
	}
	
	public class engine implements Runnable, ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			int currentTime=getDelta();
			//pass time to musicAPI
			
			
			
			if (J.touchTop == true) {System.out.println("Score: " + score); J.touchTop = false; score = 0;}
			
			
			
			//update positio
			player.getJumpy().updatePos(currentTime);
				//scroll through platforms to find one under player x
				for(int pIndex=0;pIndex<platforms.size();pIndex++){
					if(player.J.onTopOf(platforms.get(pIndex))){
						//ends jump
						player.J.setVY(0);
						J.setVX(platforms.get(pIndex).getVX());
						player.J.setOnPlatform(true);
						player.J.setY(platforms.get(pIndex).getTopY() - J.getImage().getHeight(null));
						System.out.println("On a Platform!");
					}else{
						//jump still hasnt ended
						if (!(J.getBotY() > mapHeight))
							player.J.setOnPlatform(false);
					}

				}
				for(int wIndex=0;wIndex<BassWaves.size();wIndex++){
					if(player.J.onTopOf(BassWaves.get(wIndex))){
						//wavePush
						player.J.setVY(BassWaves.get(wIndex).getVY() + .2F);
					}


				}
				

			
			for(int x=0;x<platforms.size();x++){
				//updates platform position & removes platforms when needed
				platforms.get(x).updatePos(currentTime);
				if(platforms.get(x).getBotY() < 0){
					platforms.remove(x);
					x--;
					score++;
				}
			}
			
			for(int x=0;x<BassWaves.size();x++){
				//updates platform position & removes platforms when needed
				BassWaves.get(x).updatePos(currentTime);
				if(BassWaves.get(x).getBotY() < 0){
					BassWaves.remove(x);
					x--;
				}
			}
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			Timer test;
			test=new Timer(30, this);
			test.start();
		}
		
	}
	public void paint(Graphics g){
		setSize(mapWidth,mapHeight);
		//drawImages here

		//for(int x=0;x<characters.size();x++){
//		g.drawRect(characters.get(0).getX(), characters.get(0).getY(), characters.get(0).getWidth(), characters.get(0).getHeight());
			
//			System.out.println(characters.get(0).getImage().toString());
			
			
			
			if (rand.nextInt(20) + 1 == 10)
				platforms.add(new Platform(rand.nextInt(mapWidth - 50), mapHeight, 0, -.5F, Color.black, new Animation()));
			if (rand.nextInt(100) + 1 == 15)
				BassWaves.add(new BassWave(0, mapHeight, 0, -.8F, new Animation()));	
			
			
			
		for(int x=0;x<BassWaves.size();x++){
			//image platform
			g.drawImage(BassWaves.get(x).getImage(),Math.round(BassWaves.get(x).getX()),Math.round(BassWaves.get(x).getY()),null);
		}

		for(int x=0;x<platforms.size();x++){
			//image platform
			g.drawImage(platforms.get(x).getImage(),Math.round(platforms.get(x).getX()),Math.round(platforms.get(x).getY()),null);
		}
		g.drawImage(player.J.getImage(),Math.round(player.J.getX()),Math.round(player.J.getY()),null);
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
		dbg.setColor (Color.BLACK); 
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
		return (Integer) delta;
	}
	public void newPlatform(){

	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		repaint();
		long timePassed = System.currentTimeMillis() - cumTime;
		cumTime += timePassed;
	}
	
	
}
