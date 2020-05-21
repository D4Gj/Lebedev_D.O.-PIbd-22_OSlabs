public class Knot {
	
	private File file;
	private HelperDraw[] helperDraws;
	private String filename;
	private int filesize;
	
	public Knot(File file, HelperDraw[] points){
		this.file = file;
		this.helperDraws = points;
		filename = file.getName();
		filesize = file.fileSize();
	}
	
	public HelperDraw[] getPoints(){
		return helperDraws;
	}
	
	public File getFile(){
		return file;
	}
	
	public String getName(){
		return filename;
	}
	
	public int fileSize(){
		return filesize;
	}
}
