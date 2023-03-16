package main.gui;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import entities.users.Laborant;
import entities.users.MedTechnician;
import entities.users.Owner;
import entities.users.Patient;
import entities.users.User;
import main.gui.menus.LaborantMenu;
import main.gui.menus.OwnerMenu;
import main.gui.menus.PatientMenu;
import main.gui.menus.TechnicianMenu;
import main.service.UserLoader;

import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class LoginGui {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;
	
	String path = ".\\src\\data\\users.txt";
	UserLoader userload = new UserLoader(path);
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGui window = new LoginGui();
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
	public LoginGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("Labaratorija Labi");
		frame.setBounds(100, 100, 625, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		ImageIcon logo = new ImageIcon(getClass().getResource("/logo.png"));
		frame.setIconImage(logo.getImage());
		
		JLabel P = new JLabel("Prijavite se na sistem");
		P.setBackground(Color.WHITE);
		P.setFont(new Font("Tahoma", Font.BOLD, 20));
		P.setBounds(72, 11, 365, 54);
		frame.getContentPane().add(P);
		
		JLabel lblNewLabel = new JLabel("Korisni\u010Dko ime");
		lblNewLabel.setBackground(Color.LIGHT_GRAY);
		lblNewLabel.setBounds(72, 76, 88, 41);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					passwordField.requestFocus(); 
				}
				
			}
		});
		textField.setBounds(165, 81, 210, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Lozinka");
		lblNewLabel_1.setBounds(72, 144, 88, 41);
		frame.getContentPane().add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode()==KeyEvent.VK_ENTER) {
					login(textField.getText(), passwordField.getText());
				}
				
			}
		});
		passwordField.setBounds(165, 149, 210, 30);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("Izlaz");
		Image img = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		btnNewButton.setIcon(new ImageIcon(img));
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton.setBounds(280, 209, 95, 30);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Prijava");
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.addActionListener(new ActionListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void actionPerformed(ActionEvent e) {
				login(textField.getText().trim(), passwordField.getText());
				
								}
			
		});
		btnNewButton_1.setBounds(165, 209, 95, 30);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblimg = new JLabel("");
		Image img1 = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		lblimg.setIcon(new ImageIcon(img1));
		lblimg.setBounds(427, 30, 165, 206);
		frame.getContentPane().add(lblimg);
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public void login(String username, String password) {
		//username = textField.getText().trim();
		//String password = passwordField.getText().trim();
		for (User user : userload.getUsers()) {
			if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
				if (user instanceof Owner) {
					frame.setVisible(false);
					new OwnerMenu(user);
				} else if (user instanceof MedTechnician) {
					frame.setVisible(false);
					new TechnicianMenu(user);
				} else if (user instanceof Laborant) {
					frame.setVisible(false);
					new LaborantMenu(user);
				} else if (user instanceof Patient) {
					frame.setVisible(false);
					new PatientMenu(user);
				}
			} else if (user.getUsername().equals(username)) {
				JOptionPane.showMessageDialog(null, "Pogrešna lozinka", "Greška", JOptionPane.INFORMATION_MESSAGE);
			} else if (user.getPassword().equals(password)) {
				JOptionPane.showMessageDialog(null, "Pogrešno korisničko ime", "Greška", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}
	}
}
