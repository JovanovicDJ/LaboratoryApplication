package main.service.owner;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import entities.other.MedicalResult;
import entities.users.User;
import main.gui.menus.OwnerMenu;
import main.service.MedResultLoader;
import main.service.UserLoader;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

public class ProfitReviewer {

	private JFrame frame;
	private JTable table;
	
	String path = ".\\src\\data\\users.txt";
	UserLoader userload = new UserLoader(path);
	
	String pathResults = ".\\src\\data\\medicalResults.txt";
	MedResultLoader mrl = new MedResultLoader(pathResults);
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfitReviewer window = new ProfitReviewer();
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
	public ProfitReviewer() {
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public ProfitReviewer(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user) {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 320);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Pregled prihoda");
		ImageIcon logo = new ImageIcon(getClass().getResource("/logo.png"));
		frame.setIconImage(logo.getImage());
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 464, 218);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID nalaza", "Datum zavr\u0161etka", "Pacijent", "Ku\u0107na poseta", "Prihod"
			}
		));
		
		for (MedicalResult mr : mrl.getMedicalResults()) {
			if (mr.getStage().split(" ")[0].equals("gotov")) {
				String date = mr.getStage().split(" ")[1];
				String strMonth = date.split("\\.")[1];
				int month = Calendar.getInstance().get(Calendar.MONTH);
				if (Integer.parseInt(strMonth) == month+1) {
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					String visit = null;
					if (mr.isHouseVisit() == true) {
						visit = "Da";
					} else {
						visit = "Ne";
					}
					model.addRow(new Object[] {mr.getID(), date, mr.getPatient().getName()+" "+mr.getPatient().getSurname(), visit, String.valueOf(mr.getPrice()) });
				}
			}
		}
		
		table.getColumnModel().getColumn(1).setPreferredWidth(94);
		table.getColumnModel().getColumn(2).setPreferredWidth(105);
		table.getColumnModel().getColumn(3).setPreferredWidth(78);
		scrollPane.setViewportView(table);
		
		btnNewButton = new JButton();
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
		btnNewButton.setBounds(384, 240, 90, 35);
		frame.getContentPane().add(btnNewButton);
		
		frame.setResizable(false);
		frame.setVisible(true);
	}

}
