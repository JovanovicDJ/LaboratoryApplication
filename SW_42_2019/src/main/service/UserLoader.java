package main.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import entities.users.Laborant;
import entities.users.MedTechnician;
import entities.users.Owner;
import entities.users.Patient;
import entities.users.User;

public class UserLoader {

	private List<User> users;
	
	public UserLoader() {
		this.users = new ArrayList<User>();
	}
	
	public UserLoader(String fileName) {
		this();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));
			
			String line;
			
			while((line = in.readLine()) != null) {
				line = line.trim();
				User u = parse(line);
				this.users.add(u);
			}
			in.close();
			
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

			
	}


	private User parse(String line) {
		String[] tokens = line.split(";");
		if (tokens[0].trim().equals("V")) {
			Owner o = new Owner(tokens[0].trim(), tokens[1].trim(), tokens[2].trim(), tokens[3].trim(), tokens[4].trim());
			return o;
		} else if (tokens[0].trim().equals("MT")) {
			String workDone;
			if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH)==1) {
				workDone = "0";
			} else {
				workDone = tokens[7].trim();
			}
			MedTechnician mt = new MedTechnician(tokens[0].trim(), tokens[1].trim(), tokens[2].trim(), tokens[3].trim(), tokens[4].trim(), tokens[5].trim(), tokens[6].trim(), workDone);
			return mt;
		} else if (tokens[0].trim().equals("L")) {
			String workDone;
			if (Calendar.getInstance().get(Calendar.DAY_OF_MONTH)==1) {
				workDone = "0";
			} else {
				workDone = tokens[7].trim();
			}
			Laborant l = new Laborant(tokens[0].trim(), tokens[1].trim(), tokens[2].trim(), tokens[3].trim(), tokens[4].trim(), tokens[5].trim(), tokens[6].trim(), workDone, tokens[8].trim());
			return l;
		} else {
			Patient p = new Patient(tokens[0].trim(), tokens[1].trim(), tokens[2].trim(), tokens[3].trim(), tokens[4].trim(), tokens[5].trim(), tokens[6].trim(),
									tokens[7].trim(), tokens[8].trim(), tokens[9].trim());
			return p;
		}
	
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public boolean updateUsers(List<User> users, String filename) {
		FileWriter myWriter;
		try {
			myWriter = new FileWriter(filename);
			for (User user : users) {
				if (user instanceof Owner) {
					myWriter.write(user.getRole()+";"+user.getName()+";"+user.getSurname()+";"+user.getUsername()+";"+user.getPassword()+"\n");
				} else if (user instanceof MedTechnician) {
					MedTechnician u = (MedTechnician) user;
					myWriter.write(u.getRole()+";"+u.getName()+";"+u.getSurname()+";"+u.getUsername()+";"+u.getPassword()+";"+u.getInternship()+";"
								   +u.getQualification()+";"+String.valueOf(u.getWorkDone())+"\n");
				} else if (user instanceof Laborant) {
					Laborant u = (Laborant) user;
					String spec = String.join(",", u.getSpec());
					myWriter.write(u.getRole()+";"+u.getName()+";"+u.getSurname()+";"+u.getUsername()+";"+u.getPassword()+";"+u.getInternship()+";"
								   +u.getQualification()+";"+String.valueOf(u.getWorkDone())+";"+spec+"\n");
				} else if (user instanceof Patient) {
					Patient u = (Patient) user;
					myWriter.write(u.getRole()+";"+u.getName()+";"+u.getSurname()+";"+u.getUsername()+";"+u.getPassword()+";"+u.getLBO()+";"+u.getGender()+";"
								   +u.getDateOfBirth()+";"+u.getPhoneNum()+";"+u.getAddress()+"\n");
				}
			}
			
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
		
	}
	
	public Patient findPatientByLBO(String LBO) {
		for (User user : users) {
			if (user instanceof Patient && ((Patient) user).getLBO().equals(LBO)) {
				return (Patient) user;
			}
		}
		return null;
	}
	
	public Laborant findLaboranByUsername (String username) {
		for (User user : users) {
			if (user instanceof Laborant && user.getUsername().equals(username)) {
				return (Laborant) user;
			}
		}
		return null;
	}
	
	public User getUserByUsername(String username) {
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

}
