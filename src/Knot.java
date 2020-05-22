public class Knot {
	
	private int knotId;
	private int fileId;
	private Knot knot;
	
	public Knot(int fileId,int knotId){
		this.fileId = fileId;
		this.knotId = knotId;
	}
	
	public void setKnot(int fileId,int knotId) {
		knot = new Knot(fileId,knotId);
	}
	
	public void setFile(int fileId) {
		this.fileId=fileId;
	}
	
	public Knot nextKnot() {
		return knot;
	}
	
	public int getFileId() {
		return fileId;
	}	
}
