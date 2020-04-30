import java.util.ArrayList;
import java.util.Random;

public class Process {
	Random rand = new Random();
	private int base;
	//private int lim;
	private int maxTime;
	private int resultTime;
	private String descr="proc";
	VirtualMemory virMem = new VirtualMemory();
	Planner planner = new Planner();
	public ArrayList<String> mas;

	public Process(ArrayList<String> mas,int index) {
		this.base = virMem.getSizeVirt();
		this.mas=mas;
		descr+=index+" ";
		maxTime = mas.size();
		for (int i = 0; i < mas.size(); i++) {
			virMem.setPage(new Page("proc"+index+"page"+i));
		}
	}
	public int getBase() {
		return base;
	}

	public boolean isNeedTime() {
		return maxTime > resultTime;
	}

	public void makeProcess(int quant) {
		System.out.print(descr+maxTime+": ");
		while (isNeedTime()) {
			if(quant<0) {
				System.out.println();
				return;
			}
			Page res=planner.Request(getBase()+rand.nextInt(mas.size()));
			System.out.print(res.getData());
			resultTime++;
			quant--;
		}
		System.out.println();
	}

}
