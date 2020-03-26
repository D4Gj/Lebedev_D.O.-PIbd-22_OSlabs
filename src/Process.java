import java.util.ArrayList;
import java.util.Random;

public class Process {

	Random rand = new Random();
	private ArrayList<Threads> threads;

	private int quant = 10;
	private String description = "������� ";
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
	
	public void makeProcess() {
		if ((quant < 1)) {
			System.out.println("���������� ����� ������� ������ 1");
			System.exit(0);
		}
		System.out.println();
		System.out.println(getDescription() + "  ����� ����: " + maxTime);
		int sizeThreads = threads.size();
		for (int i = 0, iq = 0; i < sizeThreads; i++, iq++) {
			Threads thread = threads.get(i);
			int thQuant = quant / threads.size();
			if (iq < quant % threads.size()) {
				thQuant++;
			}
			if (!thread.needTime()) {
				System.out.println(thread.getDescription() + " ��������");
				threads.remove(i);
				sizeThreads = threads.size();
				i--;
				break;
			}
			while (thQuant > 0) {
				if (thread.needTime() & isHaveTime() & thQuant > 0) {
					thread.makeThread();
					thQuant--;
					resultTime++;
				}
				if (!thread.needTime()) {
					System.out.println(thread.getDescription() + " ��������");
					threads.remove(i);
					sizeThreads = threads.size();
					i--;
					break;
				}
				if (!isHaveTime()) {
					System.out.println("������������ ����� " + getDescription() + " �������");
					return;
				}
				if (thQuant <= 0) {
					System.out.println("����� �� " + thread.getDescription() + " �����");
					break;
				}				
			}
		}
		System.out.println();
	}
}
