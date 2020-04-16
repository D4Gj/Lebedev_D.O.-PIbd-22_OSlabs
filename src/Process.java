import java.util.ArrayList;
import java.util.Random;

public class Process {
	Random rand = new Random();
	private int base;
	private int lim;
	private int maxTime;
	private int resultTime;
	private String descr="proc";
	VirtualMemory virMem = new VirtualMemory();
	Planner planner = new Planner();

	public Process(ArrayList<String> mas,int index) {
		this.base = virMem.getSizeVirt();
		this.lim = mas.size();
		descr+=index+" ";
		maxTime = lim;
		for (int i = 0; i < lim; i++) {
			virMem.setPage(new Page("proc"+index+"page"+i));
		}
	}
	public int getBase() {
		return base;
	}

	public int getLim() {
		return lim;
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
			Page res=planner.Request(base+rand.nextInt(lim), base, lim);
			System.out.print(res.getData());
			resultTime++;
			quant--;
		}
		System.out.println();
	}

}
