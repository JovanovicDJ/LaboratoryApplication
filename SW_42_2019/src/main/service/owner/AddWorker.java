package main.service.owner;

import java.awt.EventQueue;

import javax.swing.JFrame;

import entities.users.Laborant;
import entities.users.MedTechnician;
import entities.users.User;
import main.gui.menus.OwnerMenu;
import main.service.UserLoader;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;

public class AddWorker {

	private JFrame frmDodavanjeRadnika;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField usernameField;
	private JTextField passwordField;
	private JTextField textField;
	private JTextField specField;

	String path = ".\\src\\data\\users.txt";
	UserLoader userload = new UserLoader(path);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddWorker window = new AddWorker();
					window.frmDodavanjeRadnika.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddWorker() {
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public AddWorker(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize(User user) {
		frmDodavanjeRadnika = new JFrame();
		frmDodavanjeRadnika.setTitle("Dodavanje radnika");
		frmDodavanjeRadnika.setBounds(100, 100, 450, 430);
		frmDodavanjeRadnika.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon logo = new ImageIcon(getClass().getResource("/logo.png"));
		frmDodavanjeRadnika.setIconImage(logo.getImage());
	
		frmDodavanjeRadnika.getContentPane().setLayout(null);
		
		JLabel role = new JLabel("Uloga:");
		role.setFont(new Font("Tahoma", Font.PLAIN, 14));
		role.setBounds(10, 23, 111, 28);
		frmDodavanjeRadnika.getContentPane().add(role);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Laborant", "Medicinski tehničar"}));
		comboBox.setSelectedItem(null);
		comboBox.setBounds(134, 28, 139, 22);
		frmDodavanjeRadnika.getContentPane().add(comboBox);

		
		JLabel name = new JLabel("Ime:");
		name.setFont(new Font("Tahoma", Font.PLAIN, 14));
		name.setBounds(10, 67, 111, 28);
		frmDodavanjeRadnika.getContentPane().add(name);
		
		nameField = new JTextField();
		nameField.setBounds(134, 72, 219, 23);
		frmDodavanjeRadnika.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel surname = new JLabel("Prezime:");
		surname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		surname.setBounds(10, 106, 111, 28);
		frmDodavanjeRadnika.getContentPane().add(surname);
		
		surnameField = new JTextField();
		surnameField.setBounds(134, 109, 219, 23);
		frmDodavanjeRadnika.getContentPane().add(surnameField);
		surnameField.setColumns(10);
		
		JLabel username = new JLabel("Korisni\u010Dko ime:");
		username.setFont(new Font("Tahoma", Font.PLAIN, 14));
		username.setBounds(10, 145, 111, 28);
		frmDodavanjeRadnika.getContentPane().add(username);
		
		usernameField = new JTextField();
		usernameField.setBounds(134, 150, 219, 23);
		frmDodavanjeRadnika.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		JLabel password = new JLabel("Lozinka:");
		password.setFont(new Font("Tahoma", Font.PLAIN, 14));
		password.setBounds(10, 184, 111, 28);
		frmDodavanjeRadnika.getContentPane().add(password);
		
		passwordField = new JTextField();
		passwordField.setBounds(134, 187, 219, 23);
		frmDodavanjeRadnika.getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
		JLabel eduLevel = new JLabel("Nivo obrazovanja:");
		eduLevel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		eduLevel.setBounds(10, 223, 122, 28);
		frmDodavanjeRadnika.getContentPane().add(eduLevel);
		
		textField = new JTextField();
		textField.setBounds(134, 226, 139, 23);
		frmDodavanjeRadnika.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEduLevel = new JLabel("(4-8)");
		lblEduLevel.setBounds(283, 228, 70, 23);
		frmDodavanjeRadnika.getContentPane().add(lblEduLevel);
		
		JLabel spec = new JLabel("Specijalizacija:");
		spec.setFont(new Font("Tahoma", Font.PLAIN, 14));
		spec.setBounds(10, 262, 111, 28);
		frmDodavanjeRadnika.getContentPane().add(spec);
		
		specField = new JTextField();
		specField.setBounds(134, 265, 219, 23);
		frmDodavanjeRadnika.getContentPane().add(specField);
		specField.setColumns(10);
		
		JButton addBtn = new JButton("Dodaj");
		addBtn.setBackground(Color.LIGHT_GRAY);
		addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean s = addWorker(String.valueOf(comboBox.getSelectedItem()), nameField.getText(), surnameField.getText(), usernameField.getText(), passwordField.getText(), textField.getText(), specField.getText());
				if (s) {
					JOptionPane.showMessageDialog(null, "Novi radnik je dodat", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(null, "Izaberite ulogu", "Greška", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		addBtn.setBounds(311, 334, 98, 33);
		frmDodavanjeRadnika.getContentPane().add(addBtn);
		
		JButton exitBtn = new JButton();
		Image img = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		exitBtn.setIcon(new ImageIcon(img));
		exitBtn.setBackground(Color.LIGHT_GRAY);
		exitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frmDodavanjeRadnika.setVisible(false);
				new OwnerMenu(user);
				
			}
		});
		exitBtn.setBounds(203, 334, 98, 33);
		frmDodavanjeRadnika.getContentPane().add(exitBtn);
		
		JLabel lblNewLabel = new JLabel("*obavezno popuniti za laboranta*");
		lblNewLabel.setBounds(134, 290, 219, 14);
		frmDodavanjeRadnika.getContentPane().add(lblNewLabel);
		
		frmDodavanjeRadnika.getContentPane().add(comboBox);

		
		frmDodavanjeRadnika.setResizable(false);
		frmDodavanjeRadnika.setVisible(true);
	}
	
	public boolean addWorker(String selected, String name, String surname, String username, String password, String eduLvl, String spec) {
		String internship = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
		String workdone = "0"; 
		if (selected=="") {
			return false;
		} else if (selected.equals("Laborant")) {
			Laborant lab = new Laborant("L", name, surname, username, password, internship, eduLvl, workdone, spec);
			userload.getUsers().add(lab);
			userload.updateUsers(userload.getUsers(), path);
			return true;
		} else if (selected.equals("Medicinski tehničar")) {
			MedTechnician medtech = new MedTechnician("MT", name, surname, username, password, internship, eduLvl, workdone);
			userload.getUsers().add(medtech);
			userload.updateUsers(userload.getUsers(), path);
			return true;
		}
		return true;
		
	}
}
