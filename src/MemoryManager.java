import java.awt.Graphics;

import javax.swing.JPanel;

public class MemoryManager extends JPanel {
	
	Block Memory;
	
	public MemoryManager(Block memory) {
		this.Memory = memory;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Memory.Visualmemory(g, this.getWidth(), this.getHeight());
	}
}
