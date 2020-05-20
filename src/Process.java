import java.util.ArrayList;
import java.util.Random;

public class Process {
	Random rand = new Random();
	private int base;
	ArrayList<String> mas;
	private int maxTime;
	private int resultTime;
	int id;
	private String descr="proc";
	VirtualMemory virMem = new VirtualMemory();
	Planner planner = new Planner();

	public Process(ArrayList<String> mas,int index) {
		base = virMem.getSizeVirt();
		this.mas=mas;
		descr+=index+" ";	
		id=index;
		maxTime = mas.size();
		for (int i = 0; i < mas.size(); i++) {
			this.virMem.setPage(new Page("proc"+index+"page"+i));
		}
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
			Page res=planner.Request(base+rand.nextInt(mas.size()),base,mas.size());
			System.out.print(res.getData());
			resultTime++;
			quant--;
		}
		System.out.println();
	}

}
