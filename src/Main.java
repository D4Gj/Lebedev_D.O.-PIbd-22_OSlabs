import java.util.ArrayList;
import java.util.Random;

public class Main {
	private static ArrayList<Process> myProcesses = new ArrayList<Process>();
	private static Random rand = new Random();
	private static int quant = 10;

	public static void main(String[] args) {
		create();
		info();
		start2();
	}

	private static void start2() {
		if (quant < 1) {
			System.out.println("Квант времени меньше 1");
			return;
		}
		while (!myProcesses.isEmpty()) {
			int sizeProcesses = myProcesses.size();
			for (int i = 0; i < sizeProcesses; i++) {
				if (myProcesses.get(i).isHaveTime()) {
					if (!myProcesses.get(i).isEmpty()) {
						myProcesses.get(i).makeProcess(quant);
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

	
	private static void info() {
		for (int i = 0; i < myProcesses.size(); i++) {
			System.out.println(myProcesses.get(i).getDescription()
					+ " Потоков: " + myProcesses.get(i).getCountOfThreads());
		}
		System.out.println();
	}

	private static void create() {
		for (int i = 0; i < rand.nextInt(5) + 1; i++) {
			myProcesses.add(new Process("" + i, rand.nextInt(50) + 1));
		}
	}

}
