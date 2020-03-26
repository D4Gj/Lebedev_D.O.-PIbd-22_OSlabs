import java.util.ArrayList;
import java.util.Random;

public class Planner {
	private  ArrayList<Process> myProcesses = new ArrayList<Process>();
	private Random rand = new Random();
	
	public void create() {
		for (int i = 0; i < rand.nextInt(5) + 1; i++) {
			myProcesses.add(new Process("" + i, rand.nextInt(50) + 1));
		}
	}
	public void info() {
		for (int i = 0; i < myProcesses.size(); i++) {
			System.out.println(myProcesses.get(i).getDescription()
					+ " Потоков: " + myProcesses.get(i).getCountOfThreads());
		}
		System.out.println();
	}
	public void start() {		
		while (!myProcesses.isEmpty()) {
			int sizeProcesses = myProcesses.size();
			for (int i = 0; i < sizeProcesses; i++) {
				if (myProcesses.get(i).isHaveTime()) {
					if (!myProcesses.get(i).isEmpty()) {
						myProcesses.get(i).makeProcess();
					} else {
						System.out.println("Все потоки "
								+ myProcesses.get(i).getDescription()
								+ "  выполнены");
						myProcesses.remove(i);
						sizeProcesses = myProcesses.size();
						i--;
					}
				} else {
					myProcesses.remove(i);
					sizeProcesses = myProcesses.size();
					i--;
				}
			}
		}
		System.out.println("Все процессы выполнены");
	}
}