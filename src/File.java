import java.util.ArrayList;

public class File {

	private int fileId;
	private static int fileSize;
	private ArrayList<Knot> knots;
	
	public File(int fileId, int fileSize){
		knots = new ArrayList<Knot>();
		this.fileId = fileId;
		this.fileSize = fileSize;
	}
	
	public Knot getKnot(int index) {
		return knots.get(index);
	}
	
	public void addKnot(Knot knot) {
		knots.add(knot);
		knot.setFile(fileId);
	}
	
	public void removeKnots() {
		for (Knot knot : knots) {
			knot.setFile(-1);
		}
		knots.clear();
	}
	
	public int getFileId(){
		return fileId;
	}
	
	public static int fileSize(){
		return fileSize;
	}

}
