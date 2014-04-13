import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.Random;


import javax.swing.Timer;

import com.musicg.wave.extension.Spectrogram;


public class TheGame extends Applet implements ActionListener, KeyListener{

	Controller player;
	Jumpy J;
	ArrayList<Platform> platforms=new ArrayList<Platform>();
	//ArrayList<Song> playlist = new ArrayList<Song>();
	Song theSong;
	Spectrogram spect;
	double[][] intensities;
	int timeElapsed=0;
	long lastFrame;
	long cumTime;
//<<<<<<< HEAD
	int score;
	long startTime;
	boolean isPlaying=false;
	Thread SONG;
	int mapWidth = 1000;
	int mapHeight = 1000;
	Random rand;
	
	//SomeMusicAPI musicReader=new SomeMusicAPI();
	public TheGame(){
		startTime = System.currentTimeMillis();
		String currentDir = "";
		try {
			currentDir = new File("").getAbsolutePath();
//			System.out.println(currentDir);
			System.out.println("ALSKDFJ");

			theSong = new Song(currentDir + "\\House.wav");
			System.out.println("ALSKDFJ");

			spect = theSong.getSpect();
			intensities = spect.getNormalizedSpectrogramData();
		} catch (FileNotFoundException e) {
			System.out.println("Couldn't find the file");			
			e.printStackTrace();
		}
		//SONG = new Thread(theSong);
		ExecutorService service = Executors.newFixedThreadPool(2);
		try {
			service.submit(new Song(currentDir + "\\House.wav"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//=======
//	Random rand;
//	int score = 0;
//	//SomeMusicAPI musicReader=new SomeMusicAPI();
//	public TheGame() throws FileNotFoundException{
//		//calls actionPerformed every 30 ms
//		Timer myTimer;
//		myTimer=new Timer(30, this);
//		myTimer.start();
//
//		String currentDir = new File("").getAbsolutePath();
//		System.out.println(currentDir);
//		theSong = new Song(currentDir + "\\RunAway.wav");
//		rand = new Random();
//
//		
		player = new Controller(new Jumpy(0, 0, 0, .1F, new Animation()), mapWidth, mapHeight);
		J = player.J;
		platforms.add(new Platform(0, 550, 0.08F, 0, Color.black, new Animation()));
		platforms.add(new Platform(600, 350, -.05F, 0, Color.black, new Animation()));
		this.addKeyListener(player);
		setFocusable(true);
		
		
//>>>>>>> 00f1a7f0d04bdd2721f7a2f9ac079c9bc8b23db6
		Thread ENGINE=new Thread(new engine());
		System.out.println("adsfasf" + SONG == null);
		ENGINE.run();
//<<<<<<< HEAD
//		characters.add(new Jumpy(100, 0, 0, 0, new Animation()));
//		platforms.add(new Platform(0, 50, 0, 0, Color.black, new Animation()));
//		
////		addKeyListener();
//		
//		//gives it a file to read Commented Out because yolo( and its not done yet)
//		//SomeMusicAPI.read("file.wav");
//=======
//
//
//
//>>>>>>> 00f1a7f0d04bdd2721f7a2f9ac079c9bc8b23db6
		
		//calls actionPerformed every 30 ms
		Timer myTimer;
		myTimer=new Timer(30, this);
		myTimer.start();
		
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
					}else{
						//jump still hasnt ended
						player.J.setOnPlatform(false);
					}

				}

			
			for(int x=0;x<platforms.size();x++){
				//updates platform position & removes platforms when needed
				platforms.get(x).updatePos(currentTime);
				if(platforms.get(x).getY() < 0){
					platforms.remove(x);
					x--;
					score++;
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
			g.drawImage(player.J.getImage(),Math.round(player.J.getX()),Math.round(player.J.getY()),null);
//			System.out.println(characters.get(0).getImage().toString());
			
			
			
			if (rand.nextInt(20) + 1 == 10)
				platforms.add(new Platform(rand.nextInt(mapWidth - 50), mapHeight, 0, -.5F, Color.black, new Animation()));
			
			
			
			

		for(int x=0;x<platforms.size();x++){
			//image platform
			g.drawImage(platforms.get(x).getImage(),Math.round(platforms.get(x).getX()),Math.round(platforms.get(x).getY()),null);
		}
	}
	
	
	
	
	private Image dbImage; 
	private Graphics dbg; 
	@Override
	public void update(Graphics g){
		System.out.println("KLAJIMI");
		timeElapsed = (int) (System.currentTimeMillis() - startTime);
		if (dbImage == null) {
			dbImage = createImage (this.getSize().width, this.getSize().height); 
			dbg = dbImage.getGraphics (); 
		} 
		// clear screen in background 
		dbg.setColor (Color.BLACK); 
		dbg.fillRect (0, 0, this.getSize().width, this.getSize().height); 
		int lastx = 0;
		int lasty = 0;
		for (int i = 0; i < 256; i += 2){
			int c = 255 - i;
			dbg.setColor(new Color(c , c / 3 ,255-c));
			int height = Math.max(2, (int) (intensities[(int)timeElapsed][c] * 500 - 230));
			double fat = this.getSize().width;
//			dbg.fillRect((int)(i * (fat/255.0)), (int) this.getSize().getHeight(), (int)Math.ceil(fat/255.0) * 2, 
//					height);
//			dbg.setColor(Color.BLACK);
			int x0 = lastx;
			int x1 = (int) (i * Math.ceil(fat/255.0));
			int y0 = lasty;
			int y1 = (int) (this.getSize().getHeight() - height);
			dbg.drawLine(x0, y0, x1, y1);
			lasty = y1;
			lastx = x1;
		}
		
		// draw elements in background 
		dbg.setColor (getForeground()); 
		paint (dbg); 

		System.out.println("supp" + timeElapsed);
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
//		if(!isPlaying){
//			isPlaying = true;
//			SONG.run();
//		}
	}
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
}
