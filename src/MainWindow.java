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
	private static JPanel panel;
	private Block Memory=new Block();
	private Knot knot;
	private ManagerControl managerControl;

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

		panel = new MemoryManager(Memory);
		panel.setBackground(SystemColor.window);
		panel.setBounds(0, 0, 484, 288);
		frame.getContentPane().add(panel);
		
		final DefaultListModel<String> listFilename = new DefaultListModel<String>();
		JList<String> listPanel = new JList<String>(listFilename);
		listPanel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				int selectedIndex =listPanel.getSelectedIndex();
				panel.repaint();					
				textField_1.setText(File.fileSize() + "");				
			}
		});
		listPanel.setBounds(589, 0, 125, 288);
		frame.getContentPane().add(listPanel);
		
		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				managerControl.clear();					
				panel.repaint();
			}
		});
		btnCreate.setBounds(494, 33, 85, 23);
		frame.getContentPane().add(btnCreate);

		 managerControl = new ManagerControl();
		
		textField = new JTextField();
		textField.setBounds(494, 11, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = textField_1.getText();
				listFilename.addElement(str);
				listPanel.setModel(listFilename);
				ManagerControl.addFile(Integer.parseInt(textField_1.getText()));
				panel.repaint();
			}
		});
		btnAdd.setBounds(494, 98, 89, 23);
		frame.getContentPane().add(btnAdd);

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int s = listPanel.getSelectedIndex();
				ManagerControl.Delete(s);
				listFilename.removeElement(s);
				listPanel.setModel(listFilename);
				panel.repaint();
			}
		});
		btnDelete.setBounds(494, 128, 89, 23);
		frame.getContentPane().add(btnDelete);


		textField_1 = new JTextField();
		textField_1.setBounds(494, 67, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);

		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				listPanel.removeAll();
				listFilename.clear();
				ManagerControl.clear();
				panel.repaint();
			}
		});
		btnClear.setBounds(494, 185, 89, 23);
		frame.getContentPane().add(btnClear);
	}
}
