import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ManagerControl {

	private static int fileId = -1;
	private static int chsize=0;
	private static int freememory;
	private static boolean stoper;
	public static ArrayList<File> Files = new ArrayList<File>();

	public static void addFile(int size) {
		chsize = 0;
		stoper = true;
		fileId=Files.size();
		
		if (size > freememory) {
			JOptionPane.showMessageDialog(null, "Не хватает места");
			stoper = false;
			return;
		}		
		File file = new File(fileId,size);		
		for (int i=0; i<=Block.n; i++) {

			if (size > chsize) {

				if (Block.memory.get(i).getFileId() != -1) {
					while (Block.memory.get(i).getFileId() != -1) {
						i++;
					}
				}
				file.addKnot(Block.memory.get(i));
				freememory --;
				chsize++;
			}

			if (size == chsize) {
				Files.add(fileId, file);
				JOptionPane.showMessageDialog(null, "Добавлен:" + file);
				return ;
			}
		}
		
		return ;
	}

	public static void Delete(int id) {
		freememory=freememory+Files.get(id).fileSize();
		Files.get(id).removeKnots();		
	}

	public static void clear() {
		Files.removeAll(Files);
	}
	
}
