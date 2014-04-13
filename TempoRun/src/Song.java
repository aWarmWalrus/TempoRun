
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.musicg.wave.Wave;
import com.musicg.wave.WaveHeader;
import com.musicg.wave.extension.Spectrogram;
import com.musicg.graphic.GraphicRender;

public class Song {
	
	final private String file;
	final private Wave wave;
	
	public Song(String filename) throws FileNotFoundException{
		file = filename;
		InputStream inputStream = new FileInputStream(filename);
		wave = new Wave(inputStream);
	}
	
//	private void initWaveWithInputStream(InputStream inputStream) {
//		// reads the first 44 bytes for header
//		WaveHeader waveHeader = new WaveHeader(inputStream);
//
//		if (waveHeader.isValid()) {
//			// load data
//			try {
//				data = new byte[inputStream.available()];
//				inputStream.read(data);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			// end load data
//		} else {
//			System.err.println("Invalid Wave Header");
//		}
//	}
	
	public Spectrogram getSpect(){
		return new Spectrogram(wave);
	}
	
	public int getPlatY(int time){
		Spectrogram spect = getSpect();
		double[][] data = spect.getNormalizedSpectrogramData();
		
		
		
		return 0;

	}
}
