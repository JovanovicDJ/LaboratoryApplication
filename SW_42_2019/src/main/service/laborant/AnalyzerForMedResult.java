package main.service.laborant;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import entities.other.Analysis;
import entities.other.AnalyzingMachine;
import entities.other.MedicalResult;
import entities.users.Laborant;
import entities.users.User;
import main.gui.menus.LaborantMenu;
import main.service.MedResultLoader;
import main.service.UserLoader;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.awt.Color;
import javax.swing.JButton;

public class AnalyzerForMedResult {

	private JFrame frame;
	AnalyzingMachine machine = new AnalyzingMachine();
	String path = ".\\src\\data\\medicalResults.txt";
	MedResultLoader mrl = new MedResultLoader(path);
	String path_users = ".\\src\\data\\users.txt";
	UserLoader userload = new UserLoader(path_users);
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy."); 
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AnalyzerForMedResult window = new AnalyzerForMedResult();
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
	public AnalyzerForMedResult() {
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public AnalyzerForMedResult(User user, Analysis analysis, MedicalResult medResult) {
		initialize(user, analysis, medResult);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user, Analysis analysis, MedicalResult medResult) {
		frame = new JFrame();
		frame.setBounds(100, 100, 272, 360);
		frame.setTitle("Rezultati analize");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon logo = new ImageIcon(getClass().getResource("/logo.png"));
		frame.setIconImage(logo.getImage());
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID nalaza:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(10, 11, 80, 25);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel resultIdLbl = new JLabel("");
		resultIdLbl.setText(medResult.getID());
		resultIdLbl.setBounds(88, 11, 80, 25);
		frame.getContentPane().add(resultIdLbl);
		
		JLabel lblNewLabel_1 = new JLabel("ID analize:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 47, 80, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel analysisIdLbl = new JLabel("");
		analysisIdLbl.setText(analysis.getId());
		analysisIdLbl.setBounds(88, 47, 80, 25);
		frame.getContentPane().add(analysisIdLbl);
		
		JLabel lblNewLabel_2 = new JLabel("Ime analize:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 83, 80, 25);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel nameLbl = new JLabel("");
		nameLbl.setText(analysis.getName());
		nameLbl.setBounds(88, 83, 80, 25);
		frame.getContentPane().add(nameLbl);
		
		JLabel lblNewLabel_3 = new JLabel("Vrsta analize:");
		lblNewLabel_3.setForeground(Color.BLACK);
		lblNewLabel_3.setBackground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_3.setBounds(10, 119, 80, 25);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel typeLbl = new JLabel("");
		typeLbl.setText(analysis.getType());
		typeLbl.setBounds(88, 119, 80, 25);
		frame.getContentPane().add(typeLbl);
		
		JLabel lblNewLabel_4 = new JLabel("Min. ref. vr:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_4.setBounds(10, 155, 80, 25);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel minRefLbl = new JLabel("");
		minRefLbl.setText(String.valueOf(analysis.getMinValue()));
		minRefLbl.setBounds(88, 155, 80, 25);
		frame.getContentPane().add(minRefLbl);
		
		JLabel mesUnitLbl = new JLabel("");
		mesUnitLbl.setText(analysis.getMeasuringUnit());
		mesUnitLbl.setBounds(178, 155, 46, 25);
		frame.getContentPane().add(mesUnitLbl);
		
		JLabel lblNewLabel_5 = new JLabel("Maks. ref. vr:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_5.setEnabled(true);
		lblNewLabel_5.setBounds(10, 191, 80, 25);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel maksRefLbl = new JLabel("");
		maksRefLbl.setText(String.valueOf(analysis.getMaxValue()));
		maksRefLbl.setBounds(88, 191, 80, 25);
		frame.getContentPane().add(maksRefLbl);
		
		JLabel mesuUnitLbl = new JLabel("");
		mesuUnitLbl.setText(analysis.getMeasuringUnit());
		mesuUnitLbl.setBounds(178, 191, 46, 25);
		frame.getContentPane().add(mesuUnitLbl);
		
		JLabel lblNewLabel_6 = new JLabel("Rezultat:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_6.setBounds(10, 227, 80, 25);
		frame.getContentPane().add(lblNewLabel_6);
		
		
		double result = Double.parseDouble(machine.getAnalysisResult(analysis.getMinValue(), analysis.getMaxValue()));
		//String result = machine.getAnalysisResult(analysis.getMinValue(), analysis.getMaxValue());
		
		JLabel resultLbl = new JLabel("");
		resultLbl.setText(String.valueOf(result));
		resultLbl.setBounds(88, 227, 80, 25);
		frame.getContentPane().add(resultLbl);
		
		JLabel mesResUnitlbl = new JLabel("");
		mesResUnitlbl.setText(analysis.getMeasuringUnit());
		mesResUnitlbl.setEnabled(true);
		mesResUnitlbl.setBounds(178, 227, 46, 25);
		frame.getContentPane().add(mesResUnitlbl);
		
		JButton confirmBtn = new JButton("Potvrdi");
		confirmBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				analyzingRequest(user.getUsername(), medResult.getID(), analysis.getId(), result);
				JOptionPane.showMessageDialog(null, "Rezultat analize je zabeležen.", "Potvrda", JOptionPane.INFORMATION_MESSAGE);
				frame.setVisible(false);
				new LaborantMenu(user);
				
				
			}
		});
		Image img1 = new ImageIcon(this.getClass().getResource("/confirm.png")).getImage();
		confirmBtn.setIcon(new ImageIcon(img1));
		confirmBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		confirmBtn.setBackground(Color.LIGHT_GRAY);
		confirmBtn.setBounds(136, 270, 110, 40);
		frame.getContentPane().add(confirmBtn);
		
		JButton declineBtn = new JButton("Poni\u0161ti");
		declineBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new LaborantMenu(user);
			}
		});
		Image img = new ImageIcon(this.getClass().getResource("/decline.png")).getImage();
		declineBtn.setIcon(new ImageIcon(img));
		declineBtn.setFont(new Font("Tahoma", Font.PLAIN, 13));
		declineBtn.setBackground(Color.LIGHT_GRAY);
		declineBtn.setBounds(10, 270, 110, 40);
		frame.getContentPane().add(declineBtn);
		
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public boolean analyzingRequest(String username, String MedResID, String analysisID, double result) {
		Laborant laborant = userload.findLaboranByUsername(username);
		double newWorkDone = laborant.getWorkDone() + 1.0;
		laborant.setWorkDone(newWorkDone);
		userload.updateUsers(userload.getUsers(), path_users);
		boolean done = true;
		MedicalResult medicalResult =  mrl.getMedResultById(MedResID);
		Map<String, Double> idsAndResult = medicalResult.getmapIDsAndResults();
		idsAndResult.put(analysisID, result);
		for (Map.Entry<String, Double> entry : idsAndResult.entrySet()) {
			if (entry.getValue() == 0.0) {
				done = false;
			}
		}
		if (done == true) {
			LocalDateTime today = LocalDateTime.now();
			medicalResult.setStage("gotov " + dtf.format(today));
		}
		
		mrl.updateMedResults(mrl.getMedicalResults(), path);
		return true;
	}
	
	
}
