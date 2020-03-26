import java.util.ArrayList;
import java.util.Random;

public class Process {

	Random rand = new Random();
	private ArrayList<Threads> threads;

	private String description = "Процесс ";
	private int maxTime;
	private int resultTime = 0;

	public Process(String description, int maxTime) {
		this.description += description;
		this.maxTime = maxTime;
		threads = new ArrayList<Threads>();
		for (int i = 0; i < rand.nextInt(5) + 1; i++) {
			threads.add(new Threads("" + i, rand.nextInt(10) + 1));
		}
	}

	public int getCountOfThreads() {
		return threads.size();
	}

	public boolean isEmpty() {
		return threads.size() > 0 ? false : true;
	}
	public void setResultTime() {
		this.resultTime++;
	}
	public int getResultTime() {
		return resultTime;
	}

	public int getMaxTime() {
		return maxTime;
	}

	public String getDescription() {
		return description;
	}

	public boolean isHaveTime() {
		return getMaxTime()>getResultTime()?true:false;
	}
	
	public Threads Lists(int index) {
		return threads.get(index);
	}
	
	public int Amount() {
		return threads.size();
	}
	
	public void procKiller(int index) {
		threads.remove(index);
	}	
}
