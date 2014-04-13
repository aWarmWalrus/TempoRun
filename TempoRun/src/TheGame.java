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

import javax.swing.Timer;

import com.musicg.wave.extension.Spectrogram;


public class TheGame extends Applet implements ActionListener, KeyListener{

	ArrayList<Jumpy> characters=new ArrayList<Jumpy>();
	ArrayList<Platform> platforms=new ArrayList<Platform>();
	//ArrayList<Song> playlist = new ArrayList<Song>();
	Song theSong;
	Spectrogram spect;
	double[][] intensities;
	int timeElapsed=0;
	long lastFrame;
	long cumTime;
	long startTime;
	boolean isPlaying=false;
	Thread SONG;
	
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
		Thread ENGINE=new Thread(new engine());
		System.out.println("adsfasf" + SONG == null);
		ENGINE.run();
		characters.add(new Jumpy(100, 0, 0, 0, new Animation()));
		platforms.add(new Platform(0, 50, 0, 0, Color.black, new Animation()));
		
//		addKeyListener();
		
		//gives it a file to read Commented Out because yolo( and its not done yet)
		//SomeMusicAPI.read("file.wav");
		
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
			
			//update position
			for(int x=0;x<characters.size();x++){
				characters.get(x).updatePos(currentTime);
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
				platforms.get(x).updatePos(currentTime);
				if(platforms.get(x).getX()+platforms.get(x).getWidth()<0){
					platforms.remove(x);
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
		setSize(1000,500);
		
		//drawImages here
		//for(int x=0;x<characters.size();x++){
//		g.drawRect(characters.get(0).getX(), characters.get(0).getY(), characters.get(0).getWidth(), characters.get(0).getHeight());
			g.drawImage(characters.get(0).getImage(),Math.round(characters.get(0).getX()),Math.round(characters.get(0).getY()),null);
//			System.out.println(characters.get(0).getImage().toString());

		for(int x=0;x<platforms.size();x++){
			//image platform
			g.drawImage(platforms.get(0).getImage(),Math.round(platforms.get(0).getX()),Math.round(platforms.get(0).getY()),null);
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
