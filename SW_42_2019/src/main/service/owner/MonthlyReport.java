package main.service.owner;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import entities.other.MedicalResult;
import entities.users.Laborant;
import entities.users.MedTechnician;
import entities.users.User;
import main.gui.menus.OwnerMenu;
import main.service.MedResultLoader;
import main.service.UserLoader;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.awt.Color;
import javax.swing.JButton;

public class MonthlyReport {

	private JFrame frame;

	String path = ".\\src\\data\\users.txt";
	UserLoader userload = new UserLoader(path);
	String pathResults = ".\\src\\data\\medicalResults.txt";
	MedResultLoader mrl = new MedResultLoader(pathResults);
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonthlyReport window = new MonthlyReport();
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
	public MonthlyReport() {
		
	}	
	/**
	 * @wbp.parser.entryPoint
	 */
	public MonthlyReport(User user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(User user) {
		frame = new JFrame();
		frame.setBounds(100, 100, 350, 280);
		frame.setTitle("Mesečni izveštaj");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ImageIcon logo = new ImageIcon(getClass().getResource("/logo.png"));
		frame.setIconImage(logo.getImage());
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Izveštaj za mesec: ");
		int month = Calendar.getInstance().get(Calendar.MONTH);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(10, 11, 125, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel monthLbl = new JLabel("");
		String currentMonth = null;
		switch (month) {
			case 0:
				currentMonth = "Januar";
				break;
			case 1:
				currentMonth = "Februar";
				break;
			case 2:
				currentMonth = "Mart";
				break;
			case 3:
				currentMonth = "April";
				break;
			case 4:
				currentMonth = "Maj";
				break;
			case 5:
				currentMonth = "Jun";
				break;
			case 6:
				currentMonth = "Jul";
				break;
			case 7:
				currentMonth = "Avgust";
				break;
			case 8:
				currentMonth = "Septembar";
				break;
			case 9:
				currentMonth = "Oktobar";
				break;
			case 10:
				currentMonth = "Novembar";
				break;
			case 11:
				currentMonth = "Decembar";
				break;
				
		}
		monthLbl.setText(String.valueOf(currentMonth));
		monthLbl.setFont(new Font("Tahoma", Font.PLAIN, 13));
		monthLbl.setBounds(145, 11, 125, 30);
		frame.getContentPane().add(monthLbl);
		
		JLabel lblNewLabel_1 = new JLabel("Rashodi:");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(10, 64, 66, 30);
		frame.getContentPane().add(lblNewLabel_1);
		
		double salaries = 0;
		for (User u : userload.getUsers()) {
			if (u instanceof Laborant) {
				Laborant l = (Laborant) u;
				salaries += l.getSalary();
			} else if (u instanceof MedTechnician) {
				MedTechnician mt = (MedTechnician) u;
				salaries += mt.getSalary();
			}
		}
		
		JLabel moneyOutLbl = new JLabel("");
		moneyOutLbl.setText(String.valueOf(salaries));
		moneyOutLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		moneyOutLbl.setBounds(86, 64, 90, 30);
		frame.getContentPane().add(moneyOutLbl);
		
		JLabel lblNewLabel_2 = new JLabel("Prihodi:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel_2.setBounds(10, 105, 66, 30);
		frame.getContentPane().add(lblNewLabel_2);
		
		long profit = 0;
		for (MedicalResult mr : mrl.getMedicalResults()) {
			if (mr.getStage().split(" ")[0].equals("gotov")) {
				String date = mr.getStage().split(" ")[1];
				String strMonth = date.split("\\.")[1];
				if (Integer.parseInt(strMonth) == month+1) {
					profit += mr.getPrice();
				}
			}
		}
		
		JLabel moneyInLbl = new JLabel("");
		moneyInLbl.setText(String.valueOf(profit)+".0");
		moneyInLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		moneyInLbl.setBounds(86, 105, 90, 30);
		frame.getContentPane().add(moneyInLbl);
		
		JLabel lblNewLabel_3 = new JLabel("________________________");
		lblNewLabel_3.setBounds(10, 134, 187, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		double profitDouble = Double.parseDouble(String.valueOf(profit));
		double endResult = profitDouble - salaries;
		
		
		JLabel resultLbl = new JLabel("");
		if (endResult < 0) {
			resultLbl.setText(String.valueOf(endResult));
			resultLbl.setForeground(Color.RED);
		} else if (endResult > 0) {
			resultLbl.setText(String.valueOf(endResult));
			resultLbl.setForeground(Color.GREEN);
		} else {
			resultLbl.setText(String.valueOf(endResult));
		}
		
		resultLbl.setFont(new Font("Tahoma", Font.PLAIN, 12));
		resultLbl.setBounds(86, 150, 90, 30);
		frame.getContentPane().add(resultLbl);
		
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
		exitBtn.setBounds(234, 200, 90, 30);
		frame.getContentPane().add(exitBtn);
		
		frame.setVisible(true);
	}

}
