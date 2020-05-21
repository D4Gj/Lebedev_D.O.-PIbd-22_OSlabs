public class File {

	private String fileName;
	private int fileSize;
	
	public File(String fileName, int fileSize){
		this.fileName = fileName;
		this.fileSize = fileSize;
	}
	
	public String getName(){
		return fileName;
	}
	
	public int fileSize(){
		return fileSize;
	}

}
