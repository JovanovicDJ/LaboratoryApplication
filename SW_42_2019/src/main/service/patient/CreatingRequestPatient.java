package main.service.patient;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import entities.other.Analysis;
import entities.other.Finance;
import entities.other.MedicalResult;
import entities.users.Patient;
import entities.users.User;
import main.gui.menus.PatientMenu;
import main.service.AnalyseLoader;
import main.service.MedResultLoader;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;

public class CreatingRequestPatient {

	private JFrame frame;
	
	String path = ".\\src\\data\\medicalResults.txt";
	MedResultLoader mrl = new MedResultLoader(path);
	String pathAnalysis = ".\\src\\data\\typesOfAnalysis.txt";
	AnalyseLoader al = new AnalyseLoader(pathAnalysis);
	String pathFinance = ".\\src\\data\\finance.txt";
	Finance finance = new Finance(pathFinance);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreatingRequestPatient window = new CreatingRequestPatient();
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
	public CreatingRequestPatient() {
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public CreatingRequestPatient(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	private void initialize(User user) {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 500);
		frame.setTitle("Kreiranje zahteva za analizu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon logo = new ImageIcon(getClass().getResource("/logo.png"));
		frame.setIconImage(logo.getImage());
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID nalaza:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 11, 80, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel resultIdLbl = new JLabel("");
		resultIdLbl.setText( String.format("%05d", mrl.getMedicalResults().size()));
		resultIdLbl.setBounds(100, 11, 80, 25);
		frame.getContentPane().add(resultIdLbl);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 47, 464, 210);
		frame.getContentPane().add(scrollPane);
		
		String[] columns = {"ID analize", "Ime analize", "Vrsta analize", "Cena", "Izabrano"};
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
				if ((Boolean)model.getValueAt(rowSelected, 4) == false) {
					model.setValueAt(true, rowSelected, 4);
				} else {
					model.setValueAt(false, rowSelected, 4);
				}
				
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(60);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(55);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.setAutoCreateRowSorter(true);
		
		for (Analysis a : al.getAnalyses()) {
			model.addRow(new Object[] {a.getId(), a.getName(), a.getType(), String.valueOf(a.getPrice()), false});
		}
		
		scrollPane.setViewportView(table);
		
		
		JLabel lblNewLabel_1 = new JLabel("Preuzimanje uzorka:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 268, 144, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		JComboBox comboBoxDay = new JComboBox();
		comboBoxDay.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBoxDay.setBounds(10, 370, 45, 25);
		frame.getContentPane().add(comboBoxDay);
		comboBoxDay.setEnabled(false);
		
		JComboBox comboBoxMonth = new JComboBox();
		comboBoxMonth.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		comboBoxMonth.setBounds(65, 370, 45, 25);
		frame.getContentPane().add(comboBoxMonth);
		comboBoxMonth.setEnabled(false);
		
		JComboBox comboBoxYear = new JComboBox();
		comboBoxYear.setModel(new DefaultComboBoxModel(new String[] {"2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
		comboBoxYear.setBounds(120, 370, 60, 25);
		frame.getContentPane().add(comboBoxYear);
		comboBoxYear.setEnabled(false);
		
		JComboBox comboBoxHour = new JComboBox();
		comboBoxHour.setModel(new DefaultComboBoxModel(new String[] {"06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"}));
		comboBoxHour.setBounds(211, 370, 45, 25);
		frame.getContentPane().add(comboBoxHour);
		comboBoxHour.setEnabled(false);
		
		JComboBox comboBoxMinut = new JComboBox();
		comboBoxMinut.setModel(new DefaultComboBoxModel(new String[] {"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"}));
		comboBoxMinut.setBounds(266, 370, 45, 25);
		frame.getContentPane().add(comboBoxMinut);
		comboBoxMinut.setEnabled(false);
		
		JRadioButton withTimeRdbtn = new JRadioButton("Sa vremenom");
		withTimeRdbtn.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (withTimeRdbtn.isSelected() == true) {
					comboBoxYear.setEnabled(true);
					comboBoxMonth.setEnabled(true);
					comboBoxDay.setEnabled(true);
					comboBoxHour.setEnabled(true);
					comboBoxMinut.setEnabled(true);
				} else {
					comboBoxYear.setEnabled(false);
					comboBoxMonth.setEnabled(false);
					comboBoxDay.setEnabled(false);
					comboBoxHour.setEnabled(false);
					comboBoxMinut.setEnabled(false);
				}
				
			}
		});
		withTimeRdbtn.setBounds(211, 332, 109, 25);
		frame.getContentPane().add(withTimeRdbtn);
		withTimeRdbtn.setEnabled(false);
		
		JRadioButton noTimeRdbtn = new JRadioButton("Bez vremena");
		noTimeRdbtn.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (noTimeRdbtn.isSelected() == true) {
					comboBoxYear.setEnabled(true);
					comboBoxMonth.setEnabled(true);
					comboBoxDay.setEnabled(true);
				} else {
					comboBoxYear.setEnabled(false);
					comboBoxMonth.setEnabled(false);
					comboBoxDay.setEnabled(false);
				}
				
			}
		});
		noTimeRdbtn.setBounds(322, 332, 109, 25);
		frame.getContentPane().add(noTimeRdbtn);
		noTimeRdbtn.setEnabled(false);
		
		ButtonGroup groupForTime = new ButtonGroup();
		groupForTime.add(noTimeRdbtn);
		groupForTime.add(withTimeRdbtn);
		
		JRadioButton houseVisitRdbtn = new JRadioButton("Ku\u0107na poseta");
		houseVisitRdbtn.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (houseVisitRdbtn.isSelected() == true) {
					noTimeRdbtn.setEnabled(true);
					withTimeRdbtn.setEnabled(true);
				} else {
					comboBoxYear.setEnabled(false);
					comboBoxMonth.setEnabled(false);
					comboBoxDay.setEnabled(false);
					comboBoxHour.setEnabled(false);
					comboBoxMinut.setEnabled(false);
					noTimeRdbtn.setEnabled(false);
					withTimeRdbtn.setEnabled(false);
				}

				
			}
		});
		houseVisitRdbtn.setBounds(211, 304, 109, 25);
		frame.getContentPane().add(houseVisitRdbtn);
		
		JRadioButton noVisitRdbtn = new JRadioButton("Dono\u0161enje u labaratoriju");
		noVisitRdbtn.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (noVisitRdbtn.isSelected() == true) {
					comboBoxYear.setEnabled(true);
					comboBoxMonth.setEnabled(true);
					comboBoxDay.setEnabled(true);
				} else {
					comboBoxYear.setEnabled(false);
					comboBoxMonth.setEnabled(false);
					comboBoxDay.setEnabled(false);
				}
				
			}
		});
		noVisitRdbtn.setBounds(10, 304, 199, 25);
		frame.getContentPane().add(noVisitRdbtn);
		
		ButtonGroup groupForVisit = new ButtonGroup();
		groupForVisit.add(noVisitRdbtn);
		groupForVisit.add(houseVisitRdbtn);
		
		JButton createBtn = new JButton("Kreiraj");
		createBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String stage;
				if (houseVisitRdbtn.isSelected() == true) {
					stage = "uzorak nije uzet";
				} else {
					stage = "uzorak uzet";
				}
				String IDsAndResults = "";
				String analysisTypes= "";
				long price = 0;
				for (int i = 0; i < model.getRowCount(); i++) {
					if ((Boolean)model.getValueAt(i, 4) == true) {
						IDsAndResults = IDsAndResults +String.valueOf(model.getValueAt(i, 0)) +":0,";
						analysisTypes = analysisTypes + String.valueOf(model.getValueAt(i, 2)) +",";
						price = price + Long.parseLong(String.valueOf(model.getValueAt(i, 3)));
					}
				}
				Patient patient = (Patient) user;
				String lbo = patient.getLBO();
				String dateTime;
				String date = String.valueOf(comboBoxDay.getSelectedItem())+"."+String.valueOf(comboBoxMonth.getSelectedItem())+"."+String.valueOf(comboBoxYear.getSelectedItem())+". ";
				String time;
				if (withTimeRdbtn.isSelected() == true) {
						time = String.valueOf(comboBoxHour.getSelectedItem())+":"+String.valueOf(comboBoxMinut.getSelectedItem());
					} else {
						time = "00:00";
					}
				dateTime = date + time;
				String laborants = "";
				String houseVisit = String.valueOf(houseVisitRdbtn.isSelected());
				String withTime;
				if (houseVisitRdbtn.isSelected() == true) {
					if (withTimeRdbtn.isSelected() == true) {
						withTime = "true";
						String priceWithTime = String.valueOf(finance.getDict().get("visitWithTime")).replace(".0", "");
						price = price + Long.parseLong(priceWithTime);
					} else {
						withTime = "false";
						String priceWithoutTime = String.valueOf(finance.getDict().get("visitWithoutTime")).replace(".0", "");
						price = price + Long.parseLong(priceWithoutTime);
					}
				} else {
					withTime = "false";
				}
				creatingRequestPatient(stage, IDsAndResults, analysisTypes, lbo, laborants, dateTime, houseVisit, withTime, String.valueOf(price));
				JOptionPane.showMessageDialog(null, "Novi zahtev za analizu je podnet", "Potvrda", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		createBtn.setBackground(Color.LIGHT_GRAY);
		createBtn.setBounds(379, 417, 95, 33);
		frame.getContentPane().add(createBtn);
		
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
		exitBtn.setBounds(271, 417, 95, 33);
		frame.getContentPane().add(exitBtn);
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public boolean creatingRequestPatient(String stage, String IDsResults, String analysisTypes, String lbo, String laborants, String dateTime, String houseVisit, String withTime, String price) {
		String id = String.format("%05d", mrl.getMedicalResults().size());
		MedicalResult mr = new MedicalResult(id, stage, IDsResults, analysisTypes, lbo, laborants, dateTime, houseVisit, withTime, price);
		mrl.getMedicalResults().add(mr);
		mrl.updateMedResults(mrl.getMedicalResults(), path);
		return true;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
