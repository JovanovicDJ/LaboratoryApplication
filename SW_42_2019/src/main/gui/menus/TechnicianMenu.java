package main.gui.menus;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import entities.users.User;
import main.gui.LoginGui;
import main.service.technician.AddPatient;
import main.service.technician.CreatingRequestMedTech;
import main.service.technician.SamplesPickingReview;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class TechnicianMenu {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TechnicianMenu window = new TechnicianMenu();
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
	public TechnicianMenu() {
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public TechnicianMenu(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user) {
		frame = new JFrame();
		frame.setTitle("Meni za medicinskog tehničara");
		frame.setBounds(100, 100, 530, 435);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon logo = new ImageIcon(getClass().getResource("/logo.png"));
		frame.setIconImage(logo.getImage());
		frame.getContentPane().setLayout(null);
		
		JLabel technicianlogo = new JLabel("");
		technicianlogo.setBounds(10, 11, 53, 60);
		Image img1 = new ImageIcon(this.getClass().getResource("/technicianlogo.png")).getImage();
		technicianlogo.setIcon(new ImageIcon(img1));
		frame.getContentPane().add(technicianlogo);
		
		JLabel welcomingMessage = new JLabel("<html><font size=6>Dobro došli "+ user.getName() + ".</font></html>");
		welcomingMessage.setBounds(73, 11, 316, 60);
		frame.getContentPane().add(welcomingMessage);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new LoginGui();
				
			}
		});
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		Image img = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img));
		btnNewButton.setBounds(400, 352, 104, 33);
		frame.getContentPane().add(btnNewButton);
		
		JButton registrationBtn = new JButton("Dodavanje pacijenta");
		registrationBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new AddPatient(user); 
			}
		});
		registrationBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		registrationBtn.setVerticalAlignment(SwingConstants.CENTER);
		registrationBtn.setBackground(Color.LIGHT_GRAY);
		Image img2 = new ImageIcon(this.getClass().getResource("/registrationIconBigger.png")).getImage();
		registrationBtn.setIcon(new ImageIcon(img2));
		registrationBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		registrationBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		registrationBtn.setBounds(10, 92, 155, 238);
		frame.getContentPane().add(registrationBtn);
		
		JButton newRequestBtn = new JButton("Zahtev za nalaz");
		newRequestBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new CreatingRequestMedTech(user);
				
			}
		});
		newRequestBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		newRequestBtn.setVerticalAlignment(SwingConstants.CENTER);
		Image img3 = new ImageIcon(this.getClass().getResource("/medreportIcon.png")).getImage();
		newRequestBtn.setIcon(new ImageIcon(img3));
		newRequestBtn.setBackground(Color.LIGHT_GRAY);
		newRequestBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		newRequestBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		newRequestBtn.setBounds(183, 92, 150, 238);
		frame.getContentPane().add(newRequestBtn);
		
		JButton homeVisitCheckBtn = new JButton("Odlazak po uzorak");
		homeVisitCheckBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new SamplesPickingReview(user); 	
				
			}
		});
		homeVisitCheckBtn.setFont(new Font("Tahoma", Font.BOLD, 11));
		homeVisitCheckBtn.setVerticalAlignment(SwingConstants.CENTER);
		homeVisitCheckBtn.setBackground(Color.LIGHT_GRAY);
		Image img4 = new ImageIcon(this.getClass().getResource("/homeVisitIcon.png")).getImage();
		homeVisitCheckBtn.setIcon(new ImageIcon(img4));
		homeVisitCheckBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
		homeVisitCheckBtn.setHorizontalTextPosition(SwingConstants.CENTER);
		homeVisitCheckBtn.setBounds(354, 92, 150, 238);
		frame.getContentPane().add(homeVisitCheckBtn);
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
