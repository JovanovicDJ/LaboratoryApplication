package main.gui.menus;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import entities.other.Analysis;
import entities.other.MedicalResult;
import entities.users.Laborant;
import entities.users.User;
import main.gui.LoginGui;
import main.service.AnalyseLoader;
import main.service.MedResultLoader;
import main.service.laborant.AnalyzerForMedResult;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class LaborantMenu {

	private JFrame frame;
	
	String path = ".\\src\\data\\medicalResults.txt";
	MedResultLoader mrl = new MedResultLoader(path);
	//String pathAnalysis = "C:\\Users\\djord\\Oop\\SW_42_2019\\src\\data\\typesOfAnalysis.txt";
	String pathAnalysis = ".\\src\\data\\typesOfAnalysis.txt";
	AnalyseLoader al = new AnalyseLoader(pathAnalysis);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LaborantMenu window = new LaborantMenu();
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
	public LaborantMenu() {
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public LaborantMenu(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("serial")
	private void initialize(User user) {
		frame = new JFrame();
		frame.setBounds(100, 100, 610, 435);
		frame.setTitle("Meni za lobaranta");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon logo = new ImageIcon(getClass().getResource("/logo.png"));
		frame.setIconImage(logo.getImage());
		frame.getContentPane().setLayout(null);
		
		JLabel laborantlogo = new JLabel("");
		laborantlogo.setBounds(10, 11, 53, 60);
		Image img1 = new ImageIcon(this.getClass().getResource("/laborantlogo.png")).getImage();
		laborantlogo.setIcon(new ImageIcon(img1));
		frame.getContentPane().add(laborantlogo);
		
		JLabel welcomingMessage = new JLabel("<html><font size=6>Dobro došli "+ user.getName() + ".</font></html>");
		welcomingMessage.setBounds(86, 11, 316, 60);
		frame.getContentPane().add(welcomingMessage);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 82, 561, 257);
		frame.getContentPane().add(scrollPane);
		
		String[] columns = {"ID nalaza", "ID analize", "Ime analize", "Vrsta analize", "Min.r.vr.", "Maks.r.vr.", "Merna jed.", "Za analizu"};
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
				if ((Boolean)model.getValueAt(rowSelected, 7) == false) {
					model.setValueAt(true, rowSelected, 7);
				} else {
					model.setValueAt(false, rowSelected, 7);
				}
				
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(40);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(70);
		table.getColumnModel().getColumn(4).setPreferredWidth(40);
		table.getColumnModel().getColumn(5).setPreferredWidth(40);
		table.getColumnModel().getColumn(6).setPreferredWidth(40);
		table.getColumnModel().getColumn(7).setPreferredWidth(40);
		table.setAutoCreateRowSorter(true);
		
		for (MedicalResult mr : mrl.getMedicalResults()) {
			if (mr.getStage().equals("uzorak uzet")) {
				for (Map.Entry<String, Double> entry : mr.getmapIDsAndResults().entrySet()) {
					if (entry.getValue() == 0.0) {
						Analysis analysis = al.getAnalysisByID(entry.getKey());
						Laborant laborant = (Laborant) user;
						if( Arrays.asList(laborant.getSpec()).contains(analysis.getType())) {
							model.addRow(new Object[] {mr.getID(), analysis.getId(), analysis.getName(), analysis.getType(), String.valueOf(analysis.getMinValue()), String.valueOf(analysis.getMaxValue()), analysis.getMeasuringUnit(), false});
					}
				}
			}
		}
		}
		scrollPane.setViewportView(table);
		
		JButton exitBtn = new JButton("");
		exitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new LoginGui();
				
			}
		});
		Image img = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		exitBtn.setIcon(new ImageIcon(img));
		exitBtn.setBackground(Color.LIGHT_GRAY);
		exitBtn.setBounds(363, 350, 104, 33);
		frame.getContentPane().add(exitBtn);
		
		JButton startAnalysisMachine = new JButton("Analiziranje");
		startAnalysisMachine.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < model.getRowCount(); i++) {
					if ((Boolean)model.getValueAt(i, 7) == true) {
						MedicalResult mr = mrl.getMedResultById(String.valueOf(model.getValueAt(i, 0)));
						Analysis a = al.getAnalysisByID(String.valueOf(model.getValueAt(i, 1)));
						frame.setVisible(false);
						new AnalyzerForMedResult(user, a, mr);
						break;
					}
				}
				
			}
		});
		//naci koji je oznacen, samo jedan moze, poslati tu analizu i poslati usera, da bi se dodalo na workDone
		//u novoj klasi analizu poslati u AnalysingMachine klasu koja ce da vrati brojeve, on moze da potvrdi, 
		//ako potvrdi workDone +1, proveri se da li su ostale analize resene ako jesu promeniti stanje na gotovo i dodati datum
		//kad je zavrsen u stanje, ako ponisti vratiti ga u meni, proveriti da li su ostale analize iz zahteva uradjene
		startAnalysisMachine.setBackground(Color.LIGHT_GRAY);
		startAnalysisMachine.setBounds(477, 350, 104, 33);
		frame.getContentPane().add(startAnalysisMachine);
		
		
		frame.setResizable(false);
		frame.setVisible(true);
	}

}
