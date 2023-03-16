package main.service.technician;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import entities.users.Patient;
import entities.users.User;
import main.gui.menus.TechnicianMenu;
import main.service.UserLoader;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;

public class AddPatient {

	private JFrame frame;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField usernameField;
	private JTextField passwordField;
	private JTextField lboField;
	private JTextField telephoneField;
	private JTextField addressField;
	private JRadioButton rdbtnMan;
	private JRadioButton rdbtnWoman;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxMonth;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxDay;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBoxYear;
	
	String path = ".\\src\\data\\users.txt";
	UserLoader userload = new UserLoader(path);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddPatient window = new AddPatient();
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
	public AddPatient() {
		
	}
	
	/**
	 * @wbp.parser.entryPoint
	 */
	public AddPatient(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize(User user) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 460);
		frame.setTitle("Dodavanje pacijenata");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon logo = new ImageIcon(getClass().getResource("/logo.png"));
		frame.setIconImage(logo.getImage());
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Ime:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 11, 100, 25);
		frame.getContentPane().add(lblNewLabel);
		
		nameField = new JTextField();
		nameField.setBounds(143, 11, 260, 23);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Prezime:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(10, 47, 100, 25);
		frame.getContentPane().add(lblNewLabel_1);
		
		surnameField = new JTextField();
		surnameField.setBounds(143, 49, 260, 23);
		frame.getContentPane().add(surnameField);
		surnameField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Korisni\u010Dko ime:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(10, 83, 100, 25);
		frame.getContentPane().add(lblNewLabel_2);
		
		usernameField = new JTextField();
		usernameField.setBounds(143, 86, 260, 23);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Lozinka:");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_3.setBounds(10, 119, 100, 25);
		frame.getContentPane().add(lblNewLabel_3);
		
		passwordField = new JTextField();
		passwordField.setText("");
		passwordField.setBounds(143, 122, 260, 23);
		frame.getContentPane().add(passwordField);
		passwordField.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("LBO:");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_4.setBounds(10, 155, 100, 25);
		frame.getContentPane().add(lblNewLabel_4);
		
		lboField = new JTextField();
		lboField.setBounds(143, 156, 260, 23);
		frame.getContentPane().add(lboField);
		lboField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Pol:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_5.setBounds(10, 191, 100, 25);
		frame.getContentPane().add(lblNewLabel_5);
		
		rdbtnMan = new JRadioButton("Mu\u0161ko");
		rdbtnMan.setBounds(143, 193, 109, 23);
		frame.getContentPane().add(rdbtnMan);
		
		rdbtnWoman = new JRadioButton("\u017Densko");
		rdbtnWoman.setBounds(254, 193, 109, 23);
		frame.getContentPane().add(rdbtnWoman);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnWoman);
		group.add(rdbtnMan);
		
		JLabel lblNewLabel_6 = new JLabel("Datum ro\u0111enja:");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_6.setBounds(10, 227, 100, 25);
		frame.getContentPane().add(lblNewLabel_6);
		
		comboBoxDay = new JComboBox();
		comboBoxDay.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBoxDay.setBounds(143, 229, 50, 23);
		frame.getContentPane().add(comboBoxDay);
		
		comboBoxMonth = new JComboBox();
		comboBoxMonth.setModel(new DefaultComboBoxModel(new String[] {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12"}));
		comboBoxMonth.setBounds(222, 229, 50, 23);
		frame.getContentPane().add(comboBoxMonth);
		
		comboBoxYear = new JComboBox();
		comboBoxYear.setModel(new DefaultComboBoxModel(new String[] {"2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007", "2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989", "1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971", "1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1956", "1955", "1954", "1953", "1952", "1951", "1950"}));
		comboBoxYear.setBounds(300, 229, 60, 23);
		frame.getContentPane().add(comboBoxYear);
		
		JLabel lblNewLabel_7 = new JLabel("Telefon:");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_7.setBounds(10, 263, 100, 25);
		frame.getContentPane().add(lblNewLabel_7);
		
		telephoneField = new JTextField();
		telephoneField.setBounds(143, 266, 260, 23);
		frame.getContentPane().add(telephoneField);
		telephoneField.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Adresa:");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel_8.setBounds(10, 299, 100, 25);
		frame.getContentPane().add(lblNewLabel_8);
		
		addressField = new JTextField();
		addressField.setBounds(143, 300, 260, 23);
		frame.getContentPane().add(addressField);
		addressField.setColumns(10);
		
		JButton addButton = new JButton("Dodaj");
		addButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String date = String.valueOf(comboBoxDay.getSelectedItem())+"."+String.valueOf(comboBoxMonth.getSelectedItem())+"."+String.valueOf(comboBoxYear.getSelectedItem()+".");
				addingPatient(nameField.getText(), surnameField.getText(), usernameField.getText(), passwordField.getText(), lboField.getText(), rdbtnMan.isSelected(), date, telephoneField.getText(), addressField.getText());
				JOptionPane.showMessageDialog(null, "Novi pacijent je dodat", "Uspešno", JOptionPane.INFORMATION_MESSAGE);
				
			}

		});
		addButton.setBackground(Color.LIGHT_GRAY);
		addButton.setBounds(324, 379, 100, 31);
		frame.getContentPane().add(addButton);
		
		JButton exitBtn = new JButton("");
		exitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new TechnicianMenu(user);
			}
		});
		Image img = new ImageIcon(this.getClass().getResource("/exit.png")).getImage();
		exitBtn.setIcon(new ImageIcon(img));
		exitBtn.setBackground(Color.LIGHT_GRAY);
		exitBtn.setBounds(214, 379, 100, 31);
		frame.getContentPane().add(exitBtn);
		
		
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public boolean addingPatient(String name, String surname, String username, String password, String lbo, boolean selected, String date, String phone, String address) {		
		String gender = null;
		if (selected == true) {
			gender = "M"; 
		} else {
			gender = "Z";
		}
		Patient p = new Patient("P", name, surname, username, password, lbo, gender, date, phone, address);
		userload.getUsers().add(p);
		userload.updateUsers(userload.getUsers(), path);
		return true;
	}
}
	
