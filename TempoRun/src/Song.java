
import com.musicg.wave.Wave;
import com.musicg.wave.extension.Spectrogram;
import com.musicg.graphic.GraphicRender;

public class Song {
	
	final private String file;
	final private Wave wave;
	
	public Song(String filename){
		file = filename;
		wave = new Wave(filename);
	}
	
	public Spectrogram getSpect(){
		return new Spectrogram(wave);
	}
	
	public int getPlatY(int time){
		Spectrogram spect = getSpect();
		double[][] data = spect.getNormalizedSpectrogramData();
		
		
		
		return 0;

	}
}
