import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Block {
	public static ArrayList<Knot> memory;
	public static int n = 64;

	public static int getN() {
		return n;
	}
	public static void setN(int n) {
		Block.n = n;
	}
	public Block() {
		memory = new ArrayList<Knot>();
		for (int i = 0; i < n; i++) {
			memory.add(i, new Knot(-1, i));
		}
	}
	public void Visualmemory(Graphics g, int width, int height) {
		int cellHeight = 25;
		int cellWidth = 25;
		int chsize = 0;
		int left = 0;
		int top = 0;
		for (int i = 0; i < n; i++) {
			left = chsize * cellWidth;
			chsize++;
			switch(memory.get(i).getFileId()) {
			case -1:
				g.setColor(Color.GRAY);
				break;
			default:
				g.setColor(Color.RED);
				break;
			}
		    g.fillRect(left, top, cellWidth, cellHeight);
			g.setColor(Color.WHITE);
			g.drawRect(left, top, cellWidth, cellHeight);
			g.drawString(memory.get(i).getFileId() + "", left + 5, top + 20);
			if (chsize == 16) {
				top += cellHeight;
				chsize = 0;
				left = 0;
			}
		}
	}
}
