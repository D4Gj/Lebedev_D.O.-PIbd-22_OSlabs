import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ManagerPanel extends JPanel {

	private static int[][] memory;
	private static int size;
	private static int countElement;
	private static int countFree;
	private static int sizeNodeForDrawing = 40;
	private static ArrayList<Knot> knots;
	private int x = 0, y = 0;

	public static void startup(int _size) {
		size = _size;
		if (knots != null) {
			knots.removeAll(knots);
		}
		knots = new ArrayList<Knot>();
		countFree = size / 2;
		countElement = size / 2;
		if (countElement / sizeNodeForDrawing == 0) {
			memory = new int[1][];
			memory[0] = new int[countElement % sizeNodeForDrawing];
			for (int j = 0; j < memory[0].length; j++) {
				memory[0][j] = 1;
			}
		}
		memory = new int[(countElement / sizeNodeForDrawing) + 2][];
		for (int i = 0; i < memory.length - 1; i++) {
			memory[i] = new int[sizeNodeForDrawing];
			for (int j = 0; j < memory[i].length; j++) {
				memory[i][j] = 1;
			}
		}
		memory[(countElement / sizeNodeForDrawing) ] = new int[countElement % sizeNodeForDrawing];
		for (int j = 0; j < countElement % sizeNodeForDrawing; j++) {
			memory[countElement / sizeNodeForDrawing ][j] = 1;
		}
	}

	public static boolean addFile(String name, int size) {
		if (knots != null) {
			for (int i = 0; i < knots.size(); i++) {
				if (knots.get(i).getFile().getName().equals(name)) {
					JOptionPane
							.showMessageDialog(null, "файл с таким именем уже существует");
					return false;
				}
			}
		}
		if (size / 2 > countFree) {
			JOptionPane.showMessageDialog(null, "Не хватает места");
			return false;
		}		
		countFree -= size / 2;
		countElement = size / 2;
		HelperDraw[] ps = new HelperDraw[countElement];
		int ps_id = 0;
		for (int i = 0; i < memory.length && countElement > 0; i++) {
			for (int j = 0; j < memory[i].length && countElement > 0; j++) {
				if (memory[i][j] == 1) {
					countElement--;
					memory[i][j] = 2;
					ps[ps_id++] = new HelperDraw(i, j);
				}
			}
		}
		File file = new File(name,size);
		Knot knot = new Knot(file,ps);
		knots.add(knot);
		return true;
	}

	public static void Delete(String s) {
		HelperDraw[] ps = getKnote(s).getPoints();
		if (ps != null) {
			for (int i = 0; i < ps.length; i++) {
				memory[ps[i].getX()][ps[i].getY()] = 1;
			}
		}
		countFree += getKnote(s).fileSize() / 2;
		knots.remove(getKnote(s));
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (memory != null) {
			for (int i = 0; i < memory.length - 1; i++) {
				if (memory[i] != null) {
					for (int j = 0; j < memory[i].length; j++) {
						switch(memory[i][j]) {
						case 1:
							g.setColor(Color.GRAY);
							break;
						case 2:
							g.setColor(Color.BLUE);
							break;
						case 3:
							g.setColor(Color.RED);
							break;
						}
						g.fillRect(x, y, 11, 11);
						x += 12;
					}
					x = 0;
					y += 12;
				}
			}
		}	
		y = 0;
	}

	public static void setMemoryPoint(int i, int j, int p) {
		memory[i][j] = p;
	}

	public ArrayList<Knot> getFiles() {
		return knots;
	}

	public static Knot getKnote(String s) {
		if (knots != null) {
			for (int i = 0; i < knots.size(); i++) {
				if (knots.get(i).getName().equals(s)) {
					return knots.get(i);
				}
			}
		}
		return null;
	}

	public static void setSize(int s) {
		size = s;
	}

	public static int[][] get() {
		return memory;
	}

	public static void clear() {
		if (knots != null) {
			knots.removeAll(knots);
		}
		for (int i = 0; i < memory.length - 1; i++) {
			for (int j = 0; j < memory[i].length; j++) {
				memory[i][j] = 1;
			}
		}
		countFree = size / 2;
	}
	
	public static void setFree(){
		countFree = size / 2;
	}
}
