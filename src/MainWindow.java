import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.SystemColor;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	private Knot knot;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 730, 327);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JPanel panel = new ManagerPanel();
		panel.setBackground(SystemColor.window);
		panel.setBounds(0, 0, 484, 288);
		frame.getContentPane().add(panel);

		final DefaultListModel<String> listFilename = new DefaultListModel<String>();
		JList<String> listPanel = new JList<String>(listFilename);
		listPanel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				String s = (String) listPanel.getSelectedValue();
				if (knot != null) {
					HelperDraw[] ps = knot.getPoints();
					for (int i = 0; i < ps.length; i++) {
						ManagerPanel.setMemoryPoint(ps[i].getX(), ps[i].getY(), 2);
					}
				}
				Knot note = ManagerPanel.getKnote(s);
				if (note != null) {
					HelperDraw[] ps = note.getPoints();
					if (ps != null) {
						for (int i = 0; i < ps.length; i++) {
							ManagerPanel.setMemoryPoint(ps[i].getX(), ps[i].getY(), 3);
						}
					}
					panel.repaint();
					knot = note;
					textField_1.setText(knot.fileSize() + "");
				}
			}
		});
		listPanel.setBounds(589, 0, 125, 288);
		frame.getContentPane().add(listPanel);

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listPanel.removeAll();
				listFilename.clear();
				ManagerPanel.setFree();
				String str = textField.getText();
				if (Integer.parseInt(str) % 2 != 0 && str != "") {
					JOptionPane.showMessageDialog(null,
							"Не делится на 2. Попробуйте снова");
				} else {
					ManagerPanel.startup(Integer.parseInt(str));
				}
				panel.repaint();
			}
		});
		btnCreate.setBounds(494, 33, 85, 23);
		frame.getContentPane().add(btnCreate);

		textField = new JTextField();
		textField.setBounds(494, 11, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = textField_1.getText();
				try {
					if (Integer.parseInt(str) % 2 == 0) {
						String s = JOptionPane
								.showInputDialog("Введите название файла");
						if (s != null) {
							Boolean add = ManagerPanel.addFile(s,
									Integer.parseInt(str));
							if (add) {
								listFilename.addElement(s);
								listPanel.setModel(listFilename);
							}
							panel.repaint();
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Не делится на 2. Попробуйте снова");
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Попробуйте снова");
				}
			}
		});
		btnAdd.setBounds(494, 98, 89, 23);
		frame.getContentPane().add(btnAdd);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = (String) listPanel.getSelectedValue();
				ManagerPanel.Delete(s);
				knot = null;
				listFilename.removeElement(s);
				listPanel.setModel(listFilename);
				panel.repaint();
			}
		});
		btnDelete.setBounds(494, 128, 89, 23);
		frame.getContentPane().add(btnDelete);

		JButton btnCopy = new JButton("Copy");
		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = (String) listPanel.getSelectedValue();
				Boolean add = ManagerPanel.addFile(s + "copy", ManagerPanel.getKnote(s)
						.fileSize());
				if (add) {
					listFilename.addElement(s + "copy");
					listPanel.setModel(listFilename);
				}
				panel.repaint();
			}
		});
		btnCopy.setBounds(494, 156, 89, 23);
		frame.getContentPane().add(btnCopy);

		textField_1 = new JTextField();
		textField_1.setBounds(494, 67, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listPanel.removeAll();
				listFilename.clear();
				ManagerPanel.clear();
				panel.repaint();
			}
		});
		btnClear.setBounds(494, 185, 89, 23);
		frame.getContentPane().add(btnClear);
	}
}
