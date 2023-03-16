package main.service.patient;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import entities.other.Analysis;
import entities.other.MedicalResult;
import entities.users.Patient;
import entities.users.User;
import main.gui.menus.PatientMenu;
import main.service.AnalyseLoader;
import main.service.MedResultLoader;
import main.service.UserLoader;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Color;

public class ResultsReviewer {

	private JFrame frame;
	
	String path = ".\\src\\data\\medicalResults.txt";
	MedResultLoader mrl = new MedResultLoader(path);
	String pathAnalysis = ".\\src\\data\\typesOfAnalysis.txt";
	AnalyseLoader al = new AnalyseLoader(pathAnalysis);
	String pathUsers = ".\\src\\data\\users.txt";
	UserLoader userload = new UserLoader(pathUsers);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultsReviewer window = new ResultsReviewer();
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
	public ResultsReviewer() {
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public ResultsReviewer(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize(User user) {
		frame = new JFrame();
		frame.setBounds(100, 100, 745, 330);
		frame.setTitle("Pregled i štampanje rezultata");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon logo = new ImageIcon(getClass().getResource("/logo.png"));
		frame.setIconImage(logo.getImage());
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 709, 227);
		frame.getContentPane().add(scrollPane);
		
		String[] columns = {"Stanje", "ID nalaza", "ID analize", "Ime analize", "Vrsta analize", "Min.ref.vr.", "Maks.ref.vr.", "Rezultat", "Merna jed.", "Štampanje"};
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
					return String.class;
				case 7:
					return String.class;
				case 8:
					return String.class;
				case 9:
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
		
		JTable table = new JTable();
		table.setModel(model);
		table.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowSelected = table.getSelectedRow();
				if ((Boolean)model.getValueAt(rowSelected, 9) == false) {
					if (model.getValueAt(rowSelected, 0).equals("Završeno")) {
						model.setValueAt(true, rowSelected, 9);
					} else {
						model.setValueAt(false, rowSelected, 9);
					}
				} else {
					model.setValueAt(false, rowSelected, 9);
				}
				
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setPreferredWidth(40);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(70);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.getColumnModel().getColumn(6).setPreferredWidth(50);
		table.getColumnModel().getColumn(7).setPreferredWidth(40);
		table.getColumnModel().getColumn(8).setPreferredWidth(40);
		table.getColumnModel().getColumn(9).setPreferredWidth(40);
		table.setAutoCreateRowSorter(true);
		
		for (MedicalResult mr : mrl.getMedicalResults()) {
			Patient p = (Patient) user;
			if (mr.getPatient().getLBO().equals(p.getLBO())) {
				for (Map.Entry<String, Double> entry : mr.getmapIDsAndResults().entrySet()) {
						Analysis analysis = al.getAnalysisByID(entry.getKey());
						String stage;
						if (mr.getStage().split(" ")[0].equals("gotov")) {
							stage = "Završeno";
						} else {
							stage = "U obradi";
						}
						model.addRow(new Object[] {stage, mr.getID(), analysis.getId(), analysis.getName(), analysis.getType(), String.valueOf(analysis.getMinValue()), String.valueOf(analysis.getMaxValue()), String.valueOf(entry.getValue()), analysis.getMeasuringUnit(), false});
				}
			}
		}
		
		scrollPane.setViewportView(table);
		
		JButton printBtn = new JButton("Štampanje");
		printBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < model.getRowCount(); i++) {
					if ((Boolean)model.getValueAt(i, 9) == true) {
						printingResults(user.getUsername(), String.valueOf(model.getValueAt(i, 1)));
						JOptionPane.showMessageDialog(null, "Odštampano.", "Potvrda", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
		printBtn.setBackground(Color.LIGHT_GRAY);
		printBtn.setBounds(624, 249, 95, 30);
		frame.getContentPane().add(printBtn);
		
		JButton exitBtn = new JButton("");
		exitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new PatientMenu(user);
			}
		});
		Image img = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		exitBtn.setIcon(new ImageIcon(img));
		exitBtn.setBackground(Color.LIGHT_GRAY);
		exitBtn.setBounds(519, 249, 95, 30);
		frame.getContentPane().add(exitBtn);
		
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public boolean printingResults(String username, String MedResultID) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh-mm-ss-dd-MM-yyy");
		LocalDateTime now = LocalDateTime.now();
		User user = userload.getUserByUsername(username);
		String fileName = user.getName()+user.getSurname()+dtf.format(now)+".txt";
		String newFilePath = "C:\\Users\\djord\\Oop\\SW_42_2019\\rep\\printedResults\\"+fileName;
		File file = null;
		try {
			file = new File(newFilePath);
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		MedicalResult mr = mrl.getMedResultById(MedResultID);
		Patient p = (Patient) user;
		FileWriter myWriter;
		try {
			myWriter = new FileWriter(file);
			myWriter.write("ID nalaza: " + mr.getID() +"\n");
			myWriter.write("Ime i prezime: " + p.getName() + " " + p.getSurname() + "\n");
			myWriter.write("Datum rodjenja: " + p.getDateOfBirth() + "\n");;
			myWriter.write("Pol: " + p.getGender() +"\n");
			myWriter.write("LBO: " + p.getLBO()+ "\n");
			myWriter.write("Datum i vreme obrade: " + mr.getStage().split(" ")[1] + "\n");
			myWriter.write("\n");
			myWriter.write(String.format("%-20s %-20s %-25s %-25s %-20s %-20s ", "Analiza", "Vrsta", "Min. ref. vr.", "Maks. ref. vr.", "Rezultat", "Merna jedinica"));
			myWriter.write("\n");
			for (Map.Entry<String, Double> entry : mr.getmapIDsAndResults().entrySet()) {
				Analysis a = al.getAnalysisByID(entry.getKey());
				myWriter.write(String.format("%-20s %-20s %-25s %-25s %-20s %-20s", a.getName(), a.getType(), String.valueOf(a.getMinValue()), String.valueOf(a.getMaxValue()), String.valueOf(entry.getValue()), a.getMeasuringUnit()));
				myWriter.write("\n");
			}
			
			myWriter.write("\n");
			myWriter.write("\n");
			myWriter.write("\n");
			myWriter.write("Cena: " + String.valueOf(mr.getPrice()));
			myWriter.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return true;
		
	}
}
	
