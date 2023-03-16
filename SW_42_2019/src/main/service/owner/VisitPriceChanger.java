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

public class VisitPriceChanger {

	private JFrame frame;
	private JTextField futurePriceWithTime;
	private JTextField textField;

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
					VisitPriceChanger window = new VisitPriceChanger();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * 
	 */
	public VisitPriceChanger() {
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public VisitPriceChanger(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user) {
		frame = new JFrame();
		frame.setBounds(100, 100, 471, 260);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Promena cene poseta");
		ImageIcon logo = new ImageIcon(getClass().getResource("/logo.png"));
		frame.setIconImage(logo.getImage());
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tip posete");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 11, 114, 28);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Trenutna cena");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(171, 11, 114, 28);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nova cena");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(314, 11, 114, 28);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Sa zadatim vremenom");
		lblNewLabel_3.setBounds(10, 60, 151, 28);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel priceWithTimeNow = new JLabel("");
		priceWithTimeNow.setText(String.valueOf(dict.get("visitWithTime")));
		priceWithTimeNow.setBounds(171, 60, 114, 28);
		frame.getContentPane().add(priceWithTimeNow);
		
		futurePriceWithTime = new JTextField();
		futurePriceWithTime.setBounds(314, 62, 114, 24);
		frame.getContentPane().add(futurePriceWithTime);
		futurePriceWithTime.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Bez zadatog vremena");
		lblNewLabel_4.setBounds(10, 99, 151, 28);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel priceWithoutTime = new JLabel("");
		priceWithoutTime.setText(String.valueOf(dict.get("visitWithoutTime")));
		priceWithoutTime.setBounds(171, 99, 114, 28);
		frame.getContentPane().add(priceWithoutTime);
		
		textField = new JTextField();
		textField.setBounds(314, 101, 114, 24);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton changeBtn = new JButton("Promeni");
		changeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String newWithTime = futurePriceWithTime.getText();
				String newWithoutTime = textField.getText();
				changeVisitPrice(newWithTime, newWithoutTime);
				JOptionPane.showMessageDialog(null, "Uspešno, možete se vratiti u meni", "Potvrda", JOptionPane.INFORMATION_MESSAGE);
				
				
			}
		});
		changeBtn.setBackground(Color.LIGHT_GRAY);
		changeBtn.setBounds(333, 167, 95, 33);
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
		exitBtn.setBounds(221, 167, 95, 33);
		frame.getContentPane().add(exitBtn);
		
		
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
	
	public boolean changeVisitPrice(String newWithTime, String newWithoutTime) {
		if (newWithTime.equals("")) {
			Double priceWithTime = dict.get("visitWithTime");
			dict.put("visitWithTime", priceWithTime);
		} else {
			Double priceWithTime = Double.parseDouble(newWithTime);
			dict.put("visitWithTime", priceWithTime);
		}
		if (newWithoutTime.equals("")) {
			Double priceWithoutTime = dict.get("visitWithoutTime");
			dict.put("visitWithoutTime", priceWithoutTime);
		} else {
			Double priceWithoutTime = Double.parseDouble(newWithoutTime);
			dict.put("visitWithoutTime", priceWithoutTime);
		}
		finance.updateFile(path, dict);
		return true;
	}
	
}
