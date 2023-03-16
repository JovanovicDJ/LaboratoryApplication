package main.service.technician;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import entities.other.MedicalResult;
import entities.users.MedTechnician;
import entities.users.User;
import main.gui.menus.TechnicianMenu;
import main.service.MedResultLoader;
import main.service.UserLoader;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import java.awt.Color;

public class SamplesPickingReview {

	private JFrame frame;
	private JTable table;
	
	String path = ".\\src\\data\\medicalResults.txt";
	MedResultLoader mrl = new MedResultLoader(path);
	String path_users = ".\\src\\data\\users.txt";
	UserLoader userload = new UserLoader(path_users);
	
	final private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");

	/**
	 * Launch the 
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SamplesPickingReview window = new SamplesPickingReview();
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
	public SamplesPickingReview() {
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public SamplesPickingReview(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize(User user) {
		frame = new JFrame();
		frame.setBounds(100, 100, 650, 380);
		frame.setTitle("Poregled za odlaske po uzorak");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon logo = new ImageIcon(getClass().getResource("/logo.png"));
		frame.setIconImage(logo.getImage());
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 614, 245);
		frame.getContentPane().add(scrollPane);
		
		
		String[] columns = {"ID nalaza", "Ime", "Prezime", "Datum i vreme", "Adresa", "Telefon", "Doneto"};
		DefaultTableModel model = new DefaultTableModel(new Object[][] {}, columns) {
			public Class<?> getColumnClass(int column) {
				switch(column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2: 
					return String.class;
				case 3:
					return String.class;
				case 4:
					return String.class;
				case 5:
					return String.class;
				case 6:
					return Boolean.class;
				default:
					return String.class;
				
			}
			}
			
			@Override
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return false;
				
			}
			
		};
		
		table = new JTable();
		table.setModel(model);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelected = table.getSelectedRow();
				if ((Boolean)model.getValueAt(rowSelected, 6) == false) {
					model.setValueAt(true, rowSelected, 6);
				} else {
					model.setValueAt(false, rowSelected, 6);
				}
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(2).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setMinWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(115);
		table.getColumnModel().getColumn(3).setMinWidth(67);
		table.getColumnModel().getColumn(4).setPreferredWidth(115);
		table.getColumnModel().getColumn(4).setMinWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(90);
		table.getColumnModel().getColumn(5).setMinWidth(60);
		table.setAutoCreateRowSorter(true);
		
		for (MedicalResult result : mrl.getMedicalResults()) {
			if (result.getStage().equals("uzorak nije uzet")) {
				String dateFormated = result.getDateTime().format(dtf);
				model.addRow(new Object[] {result.getID(), result.getPatient().getName(), result.getPatient().getSurname(), dateFormated, result.getPatient().getAddress(), result.getPatient().getPhoneNum(), false});
			
			}
		}
		
		scrollPane.setViewportView(table);
	
		
		JButton pickedBtn = new JButton("Doneto");
		pickedBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < model.getRowCount(); i++) {
					if ((Boolean)model.getValueAt(i, 6) == true) {
						checkingPicked(user.getUsername(), String.valueOf(model.getValueAt(i, 0)));
						JOptionPane.showMessageDialog(null, "Donošenje uzorka je zabeleženo", "Potvrda", JOptionPane.INFORMATION_MESSAGE);
					}
				}
				
			}
		});
		pickedBtn.setBackground(Color.LIGHT_GRAY);
		pickedBtn.setBounds(524, 295, 100, 35);
		frame.getContentPane().add(pickedBtn);
		
		JButton exitBtn = new JButton("");
		exitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new TechnicianMenu(user);
			}
		});
		Image img = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		exitBtn.setIcon(new ImageIcon(img));
		exitBtn.setBackground(Color.LIGHT_GRAY);
		exitBtn.setBounds(409, 295, 100, 35);
		frame.getContentPane().add(exitBtn);
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public boolean checkingPicked(String username, String id) {
		MedTechnician mt = (MedTechnician) userload.getUserByUsername(username);
		mt.setWorkDone(mt.getWorkDone() + 1.0);
		userload.updateUsers(userload.getUsers(), path_users);
		MedicalResult mr = mrl.getMedResultById(id);
		mr.setStage("uzorak uzet");
		mrl.updateMedResults(mrl.getMedicalResults(), path);
		return true;
	}
	
	
}
