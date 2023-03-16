package main.service.owner;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import entities.other.Analysis;
import entities.users.User;
import main.gui.menus.OwnerMenu;
import main.service.AnalyseLoader;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;

public class AnalysisReview {

	private JFrame frame;
	private JTable table;

	String path = ".\\src\\data\\typesOfAnalysis.txt";
	AnalyseLoader al = new AnalyseLoader(path);
	private JButton btnNewButton;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnalysisReview window = new AnalysisReview();
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
	public AnalysisReview() {
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public AnalysisReview(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user) {
		frame = new JFrame();
		frame.setBounds(100, 100, 725, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Pregled analiza");
		ImageIcon logo = new ImageIcon(getClass().getResource("/logo.png"));
		frame.setIconImage(logo.getImage());
		
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 51, 687, 284);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Ime", "Grupa analize", "Min.ref.vrd.", "Maks.ref.vrd.", "Merna jed.", "Cena"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		table.getColumnModel().getColumn(4).setPreferredWidth(85);
		table.setAutoCreateRowSorter(true);
		
		for (Analysis a : al.getAnalyses()) {
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.addRow(new Object[] {a.getId(), a.getName(), a.getType(), String.valueOf(a.getMinValue()), String.valueOf(a.getMaxValue()), a.getMeasuringUnit(),String.valueOf(a.getPrice())});
		}
		
		scrollPane.setViewportView(table);
		
		btnNewButton = new JButton();
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		Image img = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img));
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new OwnerMenu(user);
				
			}
		});
		btnNewButton.setBounds(608, 11, 89, 33);
		frame.getContentPane().add(btnNewButton);
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
