import java.util.ArrayList;
import java.util.Random;

public class Planner {
	private  ArrayList<Process> myProcesses = new ArrayList<Process>();
	private Random rand = new Random();
	private int quant = 10;
	
	public void create() {
		for (int i = 0; i < rand.nextInt(5) + 1; i++) {
			myProcesses.add(new Process("" + i, rand.nextInt(50) + 1));
		}
	}
	public void info() {
		for (int i = 0; i < myProcesses.size(); i++) {
			System.out.println(myProcesses.get(i).getDescription()
					+ " �������: " + myProcesses.get(i).getCountOfThreads());
		}
		System.out.println();
	}
	public void start() {
		if (quant < 1) {
			System.out.println("����� ������� ������ 1");
			return;
		}
		while (!myProcesses.isEmpty()) {
			int sizeProcesses = myProcesses.size();
			for (int i = 0; i < sizeProcesses; i++) {
				if (myProcesses.get(i).isHaveTime()) {
					if (!myProcesses.get(i).isEmpty()) {
						myProcesses.get(i).makeProcess(quant);
					} else {
						System.out.println("��� ������ "
								+ myProcesses.get(i).getDescription()
								+ "  ���������");
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
		System.out.println("��� �������� ���������");
	}
}