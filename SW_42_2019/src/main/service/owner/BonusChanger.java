package main.service.owner;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import entities.other.Finance;
import entities.users.User;
import main.gui.menus.OwnerMenu;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class BonusChanger {

	private JFrame frame;
	private JTextField newMedTechBonus;
	private JTextField newLaborantBonus;

	String path = ".\\src\\data\\finance.txt";
	Finance finance = new Finance(path);
	Map<String, Double> dict = finance.getDict();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BonusChanger window = new BonusChanger();
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
	public BonusChanger() {
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public BonusChanger(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user) {
		frame = new JFrame();
		frame.setBounds(100, 100, 471, 261);
		frame.setTitle("Promena bonusa za radnike");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon logo = new ImageIcon(getClass().getResource("/logo.png"));
		frame.setIconImage(logo.getImage());
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Bonusi");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 11, 81, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Trenutni bonusi");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(150, 11, 121, 30);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Novi bonusi");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(323, 11, 122, 30);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Za med. tehni\u010Dara");
		lblNewLabel_3.setBounds(10, 63, 129, 30);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Za laboranta");
		lblNewLabel_4.setBounds(10, 110, 129, 30);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel medTechnBonusNow = new JLabel("");
		medTechnBonusNow.setText(String.valueOf(dict.get("bonusTechnician")));
		medTechnBonusNow.setBounds(150, 63, 121, 30);
		frame.getContentPane().add(medTechnBonusNow);
		
		newMedTechBonus = new JTextField();
		newMedTechBonus.setBounds(323, 63, 110, 25);
		frame.getContentPane().add(newMedTechBonus);
		newMedTechBonus.setColumns(10);
		
		JLabel laborantBonusNow = new JLabel("");
		laborantBonusNow.setText(String.valueOf(dict.get("bonusLaborant")));
		laborantBonusNow.setBounds(150, 110, 121, 30);
		frame.getContentPane().add(laborantBonusNow);
		
		newLaborantBonus = new JTextField();
		newLaborantBonus.setBounds(323, 110, 110, 25);
		frame.getContentPane().add(newLaborantBonus);
		newLaborantBonus.setColumns(10);
		
		JButton changeBtn = new JButton("Promeni");
		changeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				changeBonus(newLaborantBonus.getText(), newMedTechBonus.getText());
				JOptionPane.showMessageDialog(null, "Uspešno, možete se vratiti u meni", "Potvrda", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		changeBtn.setBackground(Color.LIGHT_GRAY);
		changeBtn.setBounds(337, 170, 96, 30);
		frame.getContentPane().add(changeBtn);
		
		JButton exitBtn = new JButton("");
		exitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new OwnerMenu(user);
				
			}
		});
		Image img = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		exitBtn.setIcon(new ImageIcon(img));
		exitBtn.setBackground(Color.LIGHT_GRAY);
		exitBtn.setBounds(231, 170, 96, 30);
		frame.getContentPane().add(exitBtn);
		
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public boolean changeBonus(String bonusL, String bonusMT) {
		if (bonusMT.equals("")) {
			Double newBonusMedTech = dict.get("bonusTechnician");
			dict.put("bonusTechnician", newBonusMedTech);
		} else {
			Double newBonusMedTech = Double.parseDouble(bonusMT);
			dict.put("bonusTechnician", newBonusMedTech);
		}
		if (bonusL.equals("")) {
			Double newBonusLab = dict.get("bonusLaborant");
			dict.put("bonusLaborant", newBonusLab);
		} else {
			Double newBonusLab = Double.parseDouble(bonusL);
			dict.put("bonusLaborant", newBonusLab);
		}
		finance.updateFile(path, dict);
		return true;
	}

}
