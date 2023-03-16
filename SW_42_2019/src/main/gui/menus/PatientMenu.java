package main.gui.menus;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import entities.users.User;
import main.gui.LoginGui;
import main.service.patient.CreatingRequestPatient;
import main.service.patient.ResultsReviewer;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class PatientMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PatientMenu window = new PatientMenu();
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
	public PatientMenu() {
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public PatientMenu(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user) {
		frame = new JFrame();
		frame.setBounds(100, 100, 469, 448);
		frame.setTitle("Mani za pacijenta");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon logo = new ImageIcon(getClass().getResource("/logo.png"));
		frame.setIconImage(logo.getImage());
		frame.getContentPane().setLayout(null);
		
		JLabel patientlogo = new JLabel("");
		patientlogo.setBounds(10, 11, 53, 60);
		Image img1 = new ImageIcon(this.getClass().getResource("/patientlogo.png")).getImage();
		patientlogo.setIcon(new ImageIcon(img1));
		frame.getContentPane().add(patientlogo);
		
		JLabel welcomingMessage = new JLabel("<html><font size=6>Dobro došli "+ user.getName() + ".</font></html>");
		welcomingMessage.setBounds(86, 11, 316, 60);
		frame.getContentPane().add(welcomingMessage);
		
		JButton exitBtn = new JButton("");
		exitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new LoginGui();
				
			}
		});
		exitBtn.setBackground(Color.LIGHT_GRAY);
		Image img = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		exitBtn.setIcon(new ImageIcon(img));
		exitBtn.setBounds(335, 362, 104, 33);
		frame.getContentPane().add(exitBtn);
		
		JButton createRequestPatientBtn = new JButton("Zahtev za analizu");
		createRequestPatientBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new CreatingRequestPatient(user);
			}
		});
		createRequestPatientBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		Image img2 = new ImageIcon(this.getClass().getResource("/medreportIcon.png")).getImage();
		createRequestPatientBtn.setIcon(new ImageIcon(img2));
		createRequestPatientBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		createRequestPatientBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		createRequestPatientBtn.setBackground(Color.LIGHT_GRAY);
		createRequestPatientBtn.setBounds(10, 82, 204, 268);
		frame.getContentPane().add(createRequestPatientBtn);
		
		JButton resultsReviewBtn = new JButton("Pregled rezultata analize");
		resultsReviewBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ResultsReviewer(user);
				
			}
		});
		resultsReviewBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		Image img3 = new ImageIcon(this.getClass().getResource("/reviewResultsLogo.png")).getImage();
		resultsReviewBtn.setIcon(new ImageIcon(img3));
		resultsReviewBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		resultsReviewBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		resultsReviewBtn.setBackground(Color.LIGHT_GRAY);
		resultsReviewBtn.setBounds(235, 82, 204, 268);
		frame.getContentPane().add(resultsReviewBtn);
		
		frame.setResizable(false);
		frame.setVisible(true);
	}

}
