package main.gui.menus;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import entities.users.User;
import main.gui.LoginGui;
import main.service.owner.AddAnalysis;
import main.service.owner.AddWorker;
import main.service.owner.AnalysisReview;
import main.service.owner.BonusChanger;
import main.service.owner.CoefficientChanger;
import main.service.owner.ExpensesReviewer;
import main.service.owner.MonthlyReport;
import main.service.owner.ProfitReviewer;
import main.service.owner.VisitPriceChanger;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;

public class OwnerMenu {

	private JFrame frame;
	
	//String path = "C:\\Users\\djord\\Oop\\SW_42_2019\\src\\data";
	//Finance finace = new Finance(path);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OwnerMenu window = new OwnerMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public OwnerMenu() {
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public OwnerMenu(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user) {
		frame = new JFrame();
		frame.setTitle("Meni za administratore");
		frame.setBounds(100, 100, 530, 390);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		ImageIcon logo = new ImageIcon(getClass().getResource("/logo.png"));
		frame.setIconImage(logo.getImage());
		
		JLabel lblNewLabel = new JLabel("<html><font size=6>Dobro do\u0161li " + user.getName() + ".</font></html>" );
		lblNewLabel.setBounds(73, 11, 316, 60);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Izve\u0161taji");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_3.setBounds(349, 82, 109, 26);
		frame.getContentPane().add(lblNewLabel_3);
		
		JButton btnNewButton_1 = new JButton("Pregled analiza");
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new AnalysisReview(user);
				
			}
		});
		btnNewButton_1.setBounds(183, 119, 142, 39);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton logOutbtn = new JButton();
		logOutbtn.setBackground(Color.LIGHT_GRAY);
		Image img = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		logOutbtn.setIcon(new ImageIcon(img));
		logOutbtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new LoginGui();
				
			}
		});
		logOutbtn.setBounds(400, 302, 104, 40);
		frame.getContentPane().add(logOutbtn);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(174, 82, 161, 209);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton_4 = new JButton("Dodavanje analiza");
		btnNewButton_4.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new AddAnalysis(user);
				
			}
		});
		btnNewButton_4.setBounds(10, 87, 141, 37);
		panel.add(btnNewButton_4);
		btnNewButton_4.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblNewLabel_2 = new JLabel("Entiteti");
		lblNewLabel_2.setBounds(10, 4, 116, 26);
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnNewButton_5 = new JButton("Dodavanje radnika");
		btnNewButton_5.setBounds(10, 135, 141, 39);
		panel.add(btnNewButton_5);
		btnNewButton_5.setBackground(Color.LIGHT_GRAY);
		btnNewButton_5.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new AddWorker(user);
				
			}
		});
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_1.setBounds(10, 82, 154, 209);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Finansije");
		lblNewLabel_1.setBounds(6, 9, 52, 15);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		JButton btnNewButton = new JButton("Koeficijenti");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setBounds(6, 37, 138, 38);
		panel_1.add(btnNewButton);
		
		JButton changeVisitPrice = new JButton("Cena poseta");
		changeVisitPrice.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new VisitPriceChanger(user);
				
			}
		});
		changeVisitPrice.setBackground(Color.LIGHT_GRAY);
		changeVisitPrice.setBounds(6, 86, 138, 38);
		panel_1.add(changeVisitPrice);
		
		JButton changeBonusBtn = new JButton("Bonusi");
		changeBonusBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new BonusChanger(user);
				
			}
		});
		changeBonusBtn.setBackground(Color.LIGHT_GRAY);
		changeBonusBtn.setBounds(6, 135, 138, 38);
		panel_1.add(changeBonusBtn);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2.setBounds(345, 82, 159, 209);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		
		JButton btnNewButton_2 = new JButton("Mesecni bilans");
		btnNewButton_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new MonthlyReport(user);
				
			}
		});
		btnNewButton_2.setBounds(10, 37, 139, 39);
		panel_2.add(btnNewButton_2);
		btnNewButton_2.setBackground(Color.LIGHT_GRAY);
		
		JButton moneyOutBtn = new JButton("Rashodi");
		moneyOutBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ExpensesReviewer(user);
				
			}
		});
		moneyOutBtn.setBackground(Color.LIGHT_GRAY);
		moneyOutBtn.setBounds(10, 87, 139, 39);
		panel_2.add(moneyOutBtn);
		
		JButton moneyInBtn = new JButton("Prihodi");
		moneyInBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ProfitReviewer(user);
				
			}
		});
		moneyInBtn.setBackground(Color.LIGHT_GRAY);
		moneyInBtn.setBounds(10, 137, 139, 39);
		panel_2.add(moneyInBtn);
		
		JLabel adminlogo = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/adminlogo.png")).getImage();
		adminlogo.setIcon(new ImageIcon(img1));
		adminlogo.setBounds(10, 11, 53, 60);
		frame.getContentPane().add(adminlogo);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new CoefficientChanger(user);
				
			}
		});
		
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
