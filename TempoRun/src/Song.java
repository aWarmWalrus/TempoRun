
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Callable;

import javax.swing.Timer;

import com.musicg.wave.Wave;
import com.musicg.wave.WaveHeader;
import com.musicg.wave.extension.Spectrogram;
import com.musicg.graphic.GraphicRender;

public class Song implements Callable<Object>,ActionListener{
	Timer test;
	final private String file;
	final private Wave wave;
	
	public Song(String filename) throws FileNotFoundException{
		file = filename;
		InputStream inputStream = new FileInputStream(filename);
		wave = new Wave(inputStream);
		
//		MakeSound m = new MakeSound();
//		m.playSound(filename);
	}
	
	public Spectrogram getSpect(){
		return new Spectrogram(wave);
	}
	
	public static double getBass(Spectrogram spect, int frame){
		double[] freq = spect.getNormalizedSpectrogramData()[frame];
		double total = 0;
		for(int i = 0; i < 100; i++){
			total += freq[i];
		}
		return total / 100.0;
	}
	
	public static double getMiddle(Spectrogram spect, int frame){
		double[] freq = spect.getNormalizedSpectrogramData()[frame];
	
		double total = 0;
		for(int i = 100; i < 200; i++){
			total += freq[i];
		}
		return total / 100.0;
	}
	
	public static double getTreble(Spectrogram spect, int frame){
		double[] freq = spect.getNormalizedSpectrogramData()[frame];
		double total = 0;
		for(int i = 155; i < 255; i++){
			total += freq[i];
		}
		return total / 100.0;
	}
	
	public int getPlatY(int time){
		Spectrogram spect = getSpect();
		double[][] data = spect.getAbsoluteSpectrogramData();
		int highestIndex = 0;
		double highestFreq = data[time][highestIndex];
		for(int i = 0; i < data[time].length; i++){
			if(data[time][i] > data[time][highestIndex]){
				highestIndex = i;
				highestFreq = data[time][i];
			}
		}
		System.out.println(highestIndex + ", " + highestFreq);
		return highestIndex;
	}

//	@Override
//	public void run() {
//		// TODO Auto-generated method stub
//		test=new Timer(500, this);
//		test.start();
//		
//	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		test.stop();
		MakeSound m = new MakeSound();
		m.playSound(file);
		
	}

	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		MakeSound m = new MakeSound();
		m.playSound(file);
		return null;
	}
	
}
