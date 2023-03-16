package main.service.owner;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JFrame;

import entities.other.Finance;
import entities.users.User;
import main.gui.menus.OwnerMenu;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

public class CoefficientChanger {

	private JFrame frame;
	
	String path = ".\\src\\data\\finance.txt";
	Finance finance = new Finance(path);
	Map<String, Double> dict = finance.getDict();
	
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoefficientChanger window = new CoefficientChanger();
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
	public CoefficientChanger() {
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public CoefficientChanger(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user) {
		frame = new JFrame();
		frame.setTitle("Promena koeficijenata");
		frame.setBounds(100, 100, 450, 430);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		ImageIcon logo = new ImageIcon(getClass().getResource("/logo.png"));
		frame.setIconImage(logo.getImage());
		
		JLabel lblNewLabel = new JLabel("Stepen obrazovanja");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel.setBounds(10, 11, 134, 24);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Trenutno stanje");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(154, 11, 113, 24);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Novi koeficijent");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_2.setBounds(287, 11, 137, 24);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Osnovni");
		lblNewLabel_3.setBounds(10, 46, 96, 24);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Stru\u010Dno osposobljen");
		lblNewLabel_4.setBounds(10, 72, 131, 24);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Srednje stru\u010Dno");
		lblNewLabel_5.setBounds(10, 96, 113, 30);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Srednje");
		lblNewLabel_6.setBounds(10, 124, 113, 24);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Vi\u0161e srednje");
		lblNewLabel_7.setBounds(10, 150, 120, 24);
		frame.getContentPane().add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Osnovno akademsko");
		lblNewLabel_8.setBounds(10, 174, 113, 24);
		frame.getContentPane().add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Master studije");
		lblNewLabel_9.setBounds(10, 198, 113, 24);
		frame.getContentPane().add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Doktorske studije");
		lblNewLabel_10.setBounds(10, 222, 131, 24);
		frame.getContentPane().add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("_______________________________________________________________");
		lblNewLabel_11.setBounds(10, 257, 414, 14);
		frame.getContentPane().add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("Baza za platu");
		lblNewLabel_12.setBounds(10, 287, 96, 24);
		frame.getContentPane().add(lblNewLabel_12);
		
		JLabel lblNewLabel_21 = new JLabel();
		lblNewLabel_21.setText(String.valueOf(dict.get("base")));
		lblNewLabel_21.setBounds(154, 292, 87, 14);
		frame.getContentPane().add(lblNewLabel_21);
		
	
		JLabel lblNewLabel_13 = new JLabel();
		lblNewLabel_13.setText(String.valueOf(dict.get("1")));
		lblNewLabel_13.setBounds(154, 46, 87, 19);
		frame.getContentPane().add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel();
		lblNewLabel_14.setText(String.valueOf(dict.get("2")));
		lblNewLabel_14.setBounds(154, 72, 87, 19);
		frame.getContentPane().add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel();
		lblNewLabel_15.setText(String.valueOf(dict.get("3")));
		lblNewLabel_15.setBounds(154, 96, 87, 22);
		frame.getContentPane().add(lblNewLabel_15);
		
		JLabel lblNewLabel_16 = new JLabel();
		lblNewLabel_16.setText(String.valueOf(dict.get("4")));
		lblNewLabel_16.setBounds(154, 124, 87, 19);
		frame.getContentPane().add(lblNewLabel_16);
		
		JLabel lblNewLabel_17 = new JLabel();
		lblNewLabel_17.setText(String.valueOf(dict.get("5")));
		lblNewLabel_17.setBounds(154, 150, 87, 19);
		frame.getContentPane().add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel();
		lblNewLabel_18.setText(String.valueOf(dict.get("6")));
		lblNewLabel_18.setBounds(154, 174, 87, 19);
		frame.getContentPane().add(lblNewLabel_18);
		
		JLabel lblNewLabel_19 = new JLabel();
		lblNewLabel_19.setText(String.valueOf(dict.get("7")));
		lblNewLabel_19.setBounds(154, 198, 87, 19);
		frame.getContentPane().add(lblNewLabel_19);
		
		JLabel lblNewLabel_20 = new JLabel();
		lblNewLabel_20.setText(String.valueOf(dict.get("8")));
		lblNewLabel_20.setBounds(154, 222, 87, 19);
		frame.getContentPane().add(lblNewLabel_20);
		
		textField = new JTextField();
		textField.setBounds(287, 45, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(287, 71, 86, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		
		textField_2 = new JTextField();
		textField_2.setBounds(287, 96, 86, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		
		textField_3 = new JTextField();
		textField_3.setBounds(287, 124, 86, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		
		textField_4 = new JTextField();
		textField_4.setBounds(287, 152, 86, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		
		textField_5 = new JTextField();
		textField_5.setBounds(287, 176, 86, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		
		textField_6 = new JTextField();
		textField_6.setBounds(287, 224, 86, 20);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		
		textField_7 = new JTextField();
		textField_7.setBounds(287, 200, 86, 20);
		frame.getContentPane().add(textField_7);
		textField_7.setColumns(10);
		
		
		textField_8 = new JTextField();
		textField_8.setBounds(287, 289, 86, 20);
		frame.getContentPane().add(textField_8);
		textField_8.setColumns(10);
		
		
		JButton btnNewButton = new JButton("Promeni");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String one = textField.getText();
				String two = textField_1.getText();
				String three = textField_2.getText();
				String four = textField_3.getText();
				String five = textField_4.getText();
				String six = textField_5.getText();
				String seven = textField_6.getText();
				String eight = textField_7.getText();
				String base = textField_8.getText();
				changeCoeff(one, two, three, four, five, six, seven, eight, base);
				JOptionPane.showMessageDialog(null, "Uspešno, možete se vratiti u meni", "Potvrda", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		btnNewButton.setBounds(328, 340, 96, 40);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton();
		Image img = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		btnNewButton_1.setIcon(new ImageIcon(img));
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new OwnerMenu(user);
				
			}
		});
		btnNewButton_1.setBounds(222, 340, 96, 40);
		frame.getContentPane().add(btnNewButton_1);
		
		//frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public boolean changeCoeff(String one, String two, String three, String four, String five, String six, String seven, String eight, String base) {
		if (one.equals("")) {
			Double oneDouble = dict.get("1");
			dict.put("1", oneDouble);
		} else {
			Double oneDouble = Double.parseDouble(one); 
			dict.put("1", oneDouble);
		}
		if (two.equals("")) {
			Double twoDouble = dict.get("2");
			dict.put("2", twoDouble);
		} else {
			Double twoDouble = Double.parseDouble(two);
			dict.put("2", twoDouble);
		}
		if (three.equals("")) {
			Double threeDouble = dict.get("3");
			dict.put("3", threeDouble);
		} else {
			Double threeDouble = Double.parseDouble(three);
			dict.put("3", threeDouble);
		}
		if (four.equals("")) {
			Double fourDouble = dict.get("4");
			dict.put("4", fourDouble);
		} else {
			Double fourDouble = Double.parseDouble(four);
			dict.put("4", fourDouble);
		}
		if (five.equals("")) {
			Double fiveDouble = dict.get("5");
			dict.put("5", fiveDouble);
		} else {
			Double fiveDouble = Double.parseDouble(five);
			dict.put("5", fiveDouble);
		}
		if (six.equals("")) {
			Double sixDouble = dict.get("6");
			dict.put("6", sixDouble);
		} else {
			Double sixDouble = Double.parseDouble(six);
			dict.put("6", sixDouble);
		}
		if (seven.equals("")) {
			Double sevenDouble = dict.get("7");
			dict.put("7", sevenDouble);
		} else {
			Double sevenDouble = Double.parseDouble(seven);
			dict.put("7", sevenDouble);
		}
		if (eight.equals("")) {
			Double eightDouble = dict.get("8");
			dict.put("8", eightDouble);
		} else {
			Double eightDouble = Double.parseDouble(eight);
			dict.put("8", eightDouble);
		}
		if (base.equals("")) {
			Double baseDouble = dict.get("base");
			dict.put("base", baseDouble);
		} else {
			Double baseDouble = Double.parseDouble(base);
			dict.put("base", baseDouble);
		}
		finance.updateFile(path, dict);
		return true;
	}
}
