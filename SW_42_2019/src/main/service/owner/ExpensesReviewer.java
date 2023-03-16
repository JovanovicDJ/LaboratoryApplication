package main.service.owner;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import entities.users.Laborant;
import entities.users.MedTechnician;
import entities.users.User;
import main.gui.menus.OwnerMenu;
import main.service.UserLoader;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ExpensesReviewer {

	private JFrame frame;
	private JTable table;
	
	String path = ".\\src\\data\\users.txt";
	UserLoader userload = new UserLoader(path);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExpensesReviewer window = new ExpensesReviewer();
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
	public ExpensesReviewer() {
		
	}

	
	/**
	 * @wbp.parser.entryPoint
	 */
	public ExpensesReviewer(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize(User user) {
		frame = new JFrame();
		frame.setBounds(100, 100, 540, 365);
		frame.setTitle("Pregled rashoda");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon logo = new ImageIcon(getClass().getResource("/logo.png"));
		frame.setIconImage(logo.getImage());
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 504, 258);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Ime", "Prezime", "Uloga", "Posao za bonus", "Plata"
			}
		) {
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
				Object.class, String.class, String.class, Object.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		
		for (User u : userload.getUsers()) {
			if (u instanceof Laborant) {
				Laborant l = (Laborant) u;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] {l.getName(), l.getSurname(), "Laborant", String.valueOf(l.getWorkDone()), String.valueOf(l.getSalary())});
				
			} else if (u instanceof MedTechnician) {
				MedTechnician mt = (MedTechnician) u;
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] {mt.getName(), mt.getSurname(), "Tehničar", String.valueOf(mt.getWorkDone()), String.valueOf(mt.getSalary())});
			}
		}
		
		table.getColumnModel().getColumn(3).setPreferredWidth(93);
		table.getColumnModel().getColumn(3).setMinWidth(66);
		scrollPane.setViewportView(table);
		
		JButton btnNewButton = new JButton();
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new OwnerMenu(user);
				
			}
		});
		Image img = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(414, 280, 100, 35);
		frame.getContentPane().add(btnNewButton);
		
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
