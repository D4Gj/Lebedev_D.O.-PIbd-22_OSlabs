import java.util.ArrayList;
import java.util.Random;

public class Main {
	private static ArrayList<Process> Processes = new ArrayList<Process>();
	private static Random rand = new Random();
	private static int quant = 5;

	public static void main(String[] args) {
		create();
		start();
	}

	private static void start() {
		if (quant < 1) {
			System.out.println("quant < 1");
			return;
		}
		while (!Processes.isEmpty()) {
			int sizeProcesses = Processes.size();
			for (int i = 0; i < sizeProcesses; i++) {
				if (Processes.get(i).isNeedTime()) {
					Processes.get(i).makeProcess(quant);
					PhysicalMemory.Out();
				} else {
					Processes.remove(i);
					sizeProcesses = Processes.size();
					i--;
				}
			}
		}
		System.out.println("Done");
	}

	private static void create() {
		for (int i = 0; i < rand.nextInt(10) + 1; i++) {
			ArrayList<String> date = new ArrayList<String>();
			for (int j = 0; j < rand.nextInt(20) + 1; j++) {
				date.add("" + i);
			}
			Processes.add(new Process(date,i));
		}
	}
}
