
import java.awt.Image;
import java.util.ArrayList;

public class Animation {
	
	private ArrayList scenes1, scenes2, scenes3;
	private ArrayList currentList;
	private int sceneIndex;
	private long movieTime;
	private long totalTime1, totalTime2, totalTime3;
	private long currentTotalTime;
	
	//constructor
	public Animation(){//different animation sequences
		scenes1 = new ArrayList(); 
		scenes2 = new ArrayList();
		scenes3 = new ArrayList();
		
		currentList = scenes1;
		totalTime1 = 0;
		start();
	}
	
	//returns current animation list
	public ArrayList getCurrentList(){
		return currentList;
	}
	
	//sets a new animation list
	public void setCurrentList(int x){
		if (x == 1){
			currentList = scenes1;
			totalTime1 = 0;
		}
		if (x == 2){
			currentList = scenes2;
			totalTime2 = 0;
		}
		if (x == 3){
			currentList = scenes3;
			totalTime3 = 0;
		}
		start();
	}
	//add scene to ArrayList and set time for each scene
	public synchronized void addScene(Image i, long time){
		if (currentList == scenes1){
			totalTime1 += time;
			currentList.add(new OneScene(i, totalTime1));
		}
		if (currentList == scenes2){
			totalTime2 += time;
			currentList.add(new OneScene(i, totalTime2));
		}
		if (currentList == scenes3){
			totalTime3 += time;
			currentList.add(new OneScene(i, totalTime3));
		}

		
	}
	
	//Start animation from beginning
	public synchronized void start(){
		movieTime = 0;
		sceneIndex = 0;
	}

	//change scenes
	public synchronized void update(long timePassed){
		long totalTime = 0;
		if(currentList.size() > 1){
			movieTime += timePassed;
			if (currentList == scenes1){
				totalTime = totalTime1;
			}
			if (currentList == scenes2){
				totalTime = totalTime2;
			}
			if (currentList == scenes3){
				totalTime = totalTime3;
			}
			if(movieTime >= totalTime){
				movieTime = 0;
				sceneIndex = 0;
			}
			while(movieTime > getScene(sceneIndex).endTime){
				sceneIndex++;
			}
		}
	}
	
	//get animations current scene(image)
	public synchronized Image getImage(){
		if(currentList.size() == 0){
			return null;
		}
		else{
			return getScene(sceneIndex).pic;
		}
	}
	
	//get Scenes
	private OneScene getScene(int x){
		return (OneScene)currentList.get(x);
	}
	
	//PRIVATE INNER CLASS (OBJECT SCENE)
	private class OneScene{
		Image pic;
		long endTime;
		
		public OneScene(Image pic, long endTime){
			this.pic = pic;
			this.endTime = endTime;
		}
		
	}

}
