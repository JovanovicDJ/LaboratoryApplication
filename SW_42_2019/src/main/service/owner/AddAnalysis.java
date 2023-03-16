package main.service.owner;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import entities.other.Analysis;
import entities.users.User;
import main.gui.menus.OwnerMenu;
import main.service.AnalyseLoader;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.Color;

public class AddAnalysis {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	String path = ".\\src\\data\\typesOfAnalysis.txt";
	AnalyseLoader al = new AnalyseLoader(path);
	private JTextField minValueField;
	private JTextField maxValueField;
	private JTextField measurUnitField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddAnalysis window = new AddAnalysis();
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
	public AddAnalysis() {
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public AddAnalysis(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user) {
		frame = new JFrame();
		frame.setBounds(100, 100, 460, 370);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Dodavanje analize");
		ImageIcon logo = new ImageIcon(getClass().getResource("/logo.png"));
		frame.setIconImage(logo.getImage());
		
		
		frame.setResizable(false);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID analize:");
		lblNewLabel.setBounds(10, 21, 111, 27);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(247, 27, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		String id = String.format("%04d", al.getAnalyses().size());
		lblNewLabel_1.setText(id);
		
		JLabel lblNewLabel_2 = new JLabel("Ime analize:");
		lblNewLabel_2.setBounds(10, 59, 111, 27);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(143, 62, 246, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Vrsta analize:");
		lblNewLabel_3.setBounds(10, 100, 111, 27);
		frame.getContentPane().add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(143, 103, 246, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Min. ref. vrednost");
		lblNewLabel_5.setBounds(10, 138, 127, 27);
		frame.getContentPane().add(lblNewLabel_5);
		
		minValueField = new JTextField();
		//minValueField.addKeyListener(new KeyListener() {
			
			//@Override
			//public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			//}
			
			//@Override
			//public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			//}
			
			//@Override
			//public void keyPressed(KeyEvent e) {
				//try {
					//Double.parseDouble(minValueField.getText());
				//} catch (Exception e2) {
					// TODO: handle exception
					//JOptionPane.showMessageDialog(null, "Los unos podataka", "Greska", JOptionPane.ERROR_MESSAGE);
				//}
				
			//}
		//});
		minValueField.setBounds(143, 141, 246, 20);
		frame.getContentPane().add(minValueField);
		minValueField.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Maks. ref. vrednost");
		lblNewLabel_6.setBounds(10, 176, 127, 27);
		frame.getContentPane().add(lblNewLabel_6);
		
		maxValueField = new JTextField();
		maxValueField.setBounds(143, 179, 246, 20);
		frame.getContentPane().add(maxValueField);
		maxValueField.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Merna jedinica");
		lblNewLabel_7.setBounds(10, 214, 111, 27);
		frame.getContentPane().add(lblNewLabel_7);
		
		measurUnitField = new JTextField();
		measurUnitField.setBounds(142, 217, 247, 20);
		frame.getContentPane().add(measurUnitField);
		measurUnitField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Cena analize:");
		lblNewLabel_4.setBounds(10, 249, 98, 27);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField_2 = new JTextField();
		textField_2.setBounds(143, 252, 246, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Dodaj");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				addAnalysis(textField.getText(), textField_1.getText(), minValueField.getText(), maxValueField.getText(), measurUnitField.getText(), textField_2.getText());
				
				JOptionPane.showMessageDialog(null, "Uspešno dodata analiza", "Uspeh", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnNewButton.setBounds(320, 283, 98, 33);
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
		btnNewButton_1.setBounds(212, 283, 98, 33);
		frame.getContentPane().add(btnNewButton_1);
		
		
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public boolean addAnalysis(String n, String t, String min, String max, String u, String p) {
		String id = String.format("%04d", al.getAnalyses().size());
		Analysis a = new Analysis(id, n, t, min, max, u, p);
		al.getAnalyses().add(a);
		al.updateAnalysis(al.getAnalyses(), path);
		return true;
	}
	
	
}
