import java.util.ArrayList;

public class VirtualMemory {

	public static ArrayList<Page> virtualMemory = new ArrayList<Page>();

	public Page getPage(int i) {
		if (i>=0 &i < virtualMemory.size()) {
			virtualMemory.get(i).use();
			return virtualMemory.get(i);
		}
		return null;
		
	}
	
	public ArrayList<Page> getVirMem() {
		return virtualMemory;
	}
	
	public int setPage(Page p){
		virtualMemory.add(p);
		return virtualMemory.indexOf(p);
	}
	
	public int getSizeVirt(){
		return  virtualMemory.size();
	}
}
