package main.service.technician;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import entities.other.Analysis;
import entities.other.Finance;
import entities.other.MedicalResult;
import entities.users.User;
import main.gui.menus.TechnicianMenu;
import main.service.AnalyseLoader;
import main.service.MedResultLoader;

import javax.swing.JScrollPane;
import javax.swing.JTable;
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
//import java.util.ArrayList;
//import java.util.List;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

public class CreatingRequestMedTech {

	private JFrame frame;
	private JTextField lboField;
	//private List<String> selectedAnalysis;
	
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
					CreatingRequestMedTech window = new CreatingRequestMedTech();
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
	
	public CreatingRequestMedTech() {
		//this.selectedAnalysis = new ArrayList<String>();
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public CreatingRequestMedTech(User user) {
		this();
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
	private void initialize(User user) {
		frame = new JFrame();
		frame.setBounds(100, 100, 560, 545);
		frame.setTitle("Kreiranje zahteva za analizu");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon logo = new ImageIcon(getClass().getResource("/logo.png"));
		frame.setIconImage(logo.getImage());
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("ID nalaza:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 11, 89, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel idLbl = new JLabel("");
		String id = String.format("%05d", mrl.getMedicalResults().size());
		idLbl.setText(id);
		idLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		idLbl.setBounds(147, 12, 80, 25);
		frame.getContentPane().add(idLbl);
		
		JLabel lblNewLabel = new JLabel("LBO pacijenta:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 56, 89, 25);
		frame.getContentPane().add(lblNewLabel);
		
		lboField = new JTextField();
		lboField.setBounds(109, 57, 150, 25);
		frame.getContentPane().add(lboField);
		lboField.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 104, 524, 200);
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
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Datum i vreme:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(10, 385, 105, 25);
		frame.getContentPane().add(lblNewLabel_2);
		
		JComboBox comboBoxDay = new JComboBox();
		comboBoxDay.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBoxDay.setBounds(125, 386, 40, 25);
		frame.getContentPane().add(comboBoxDay);
		comboBoxDay.setEnabled(false);
		
		JComboBox comboBoxMonth = new JComboBox();
		comboBoxMonth.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		comboBoxMonth.setBounds(174, 386, 40, 25);
		frame.getContentPane().add(comboBoxMonth);
		comboBoxMonth.setEnabled(false);
		
		JComboBox comboBoxYear = new JComboBox();
		comboBoxYear.setModel(new DefaultComboBoxModel(new String[] {"2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030"}));
		comboBoxYear.setBounds(224, 386, 60, 25);
		frame.getContentPane().add(comboBoxYear);
		comboBoxYear.setEnabled(false);
		
		JComboBox comboBoxHour = new JComboBox();
		comboBoxHour.setModel(new DefaultComboBoxModel(new String[] {"06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21"}));
		comboBoxHour.setBounds(327, 386, 40, 25);
		frame.getContentPane().add(comboBoxHour);
		comboBoxHour.setEnabled(false);
		
		JComboBox comboBoxMinut = new JComboBox();
		comboBoxMinut.setModel(new DefaultComboBoxModel(new String[] {"00", "05", "10", "15", "20", "25", "30", "35", "40", "45", "50", "55"}));
		comboBoxMinut.setBounds(377, 386, 40, 25);
		frame.getContentPane().add(comboBoxMinut);
		comboBoxMinut.setEnabled(false);
		
		
		JRadioButton rdbtnWithTime = new JRadioButton("Sa vremenom");
		rdbtnWithTime.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (rdbtnWithTime.isSelected() == true) {
					comboBoxDay.setEnabled(true);
					comboBoxMonth.setEnabled(true);
					comboBoxYear.setEnabled(true);
					comboBoxHour.setEnabled(true);
					comboBoxMinut.setEnabled(true);
				} else {
					comboBoxHour.setEnabled(false);
					comboBoxMinut.setEnabled(false);
				}
				
			}
		});
		rdbtnWithTime.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnWithTime.setBounds(327, 333, 150, 23);
		frame.getContentPane().add(rdbtnWithTime);
		rdbtnWithTime.setEnabled(false);
		
		
		JRadioButton rdbtnNoTime = new JRadioButton("Bez vremena");
		rdbtnNoTime.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (rdbtnNoTime.isSelected() == true) {
					comboBoxDay.setEnabled(true);
					comboBoxMonth.setEnabled(true);
					comboBoxYear.setEnabled(true);
				}				
			}
		});
		rdbtnNoTime.setFont(new Font("Tahoma", Font.PLAIN, 13));
		rdbtnNoTime.setBounds(160, 333, 122, 23);
		frame.getContentPane().add(rdbtnNoTime);
		rdbtnNoTime.setEnabled(false);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNoTime);
		group.add(rdbtnWithTime);
		
		JCheckBox chckbxHomeVisit = new JCheckBox("Ku\u0107na poseta");
		chckbxHomeVisit.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (chckbxHomeVisit.isSelected() == true) {
					rdbtnNoTime.setEnabled(true);
					rdbtnWithTime.setEnabled(true);
				} else {
					rdbtnNoTime.setEnabled(false);
					rdbtnNoTime.setSelected(false);
					rdbtnWithTime.setEnabled(false);
					rdbtnWithTime.setSelected(false);
					comboBoxMinut.setEnabled(false);
					comboBoxHour.setEnabled(false);
					comboBoxDay.setEnabled(false);
					comboBoxMonth.setEnabled(false);
					comboBoxYear.setEnabled(false);
				}
				
			}
		});
		chckbxHomeVisit.setFont(new Font("Tahoma", Font.PLAIN, 13));
		chckbxHomeVisit.setBounds(10, 333, 133, 23);
		frame.getContentPane().add(chckbxHomeVisit);
		
		JButton createBtn = new JButton("Kreiraj");
		createBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = idLbl.getText();
				String stage;
				if (chckbxHomeVisit.isSelected() == true) {
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
				String lbo = lboField.getText();
				String dateTime;
				String date = String.valueOf(comboBoxDay.getSelectedItem())+"."+String.valueOf(comboBoxMonth.getSelectedItem())+"."+String.valueOf(comboBoxYear.getSelectedItem())+". ";
				String time;
				if (rdbtnWithTime.isSelected() == true) {
						time = String.valueOf(comboBoxHour.getSelectedItem())+":"+String.valueOf(comboBoxMinut.getSelectedItem());
					} else {
						time = "00:00";
					}
				dateTime = date + time;
				String laborants = "";
				String houseVisit = String.valueOf(chckbxHomeVisit.isSelected());
				String withTime;
				if (chckbxHomeVisit.isSelected() == true) {
					if (rdbtnWithTime.isSelected() == true) {
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
				creatingRequestMT(id, stage, IDsAndResults, analysisTypes, lbo, laborants, dateTime, houseVisit, withTime, String.valueOf(price));
				JOptionPane.showMessageDialog(null, "Novi zahtev za analizu je podnet", "Potvrda", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		createBtn.setBackground(Color.LIGHT_GRAY);
		createBtn.setBounds(445, 463, 90, 35);
		frame.getContentPane().add(createBtn);
		
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
		exitBtn.setBounds(345, 463, 90, 35);
		frame.getContentPane().add(exitBtn);
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public boolean creatingRequestMT(String id, String stage, String IDsAndResults, String analysisTypes, String lbo, String laborants, String dateTime, String houseVisit, String withTime, String price) {
		MedicalResult mr = new MedicalResult(id, stage, IDsAndResults, analysisTypes, lbo, laborants, dateTime, houseVisit, withTime, price);
		mrl.getMedicalResults().add(mr);
		mrl.updateMedResults(mrl.getMedicalResults(), path);
		return true;
	}
	
	
}
