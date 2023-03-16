package entities.users;

public class Patient extends User {

	private String LBO;
	private String gender;
	private String dateOfBirth;
	private String phoneNum;
	private String address;
	
	public Patient() {
		
	}

	public Patient(String role, String name, String surname, String username, String password, String LBO, String gender, String dateOfBirth, String phoneNum, String address) {
		super(role, name, surname, username, password);
		this.LBO = LBO; 
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.phoneNum = phoneNum;
		this.address = address;
	}

	public String getLBO() {
		return LBO;
	}


	public String getGender() {
		return gender;
	}


	public String getDateOfBirth() {
		return dateOfBirth;
	}


	public String getPhoneNum() {
		return phoneNum;
	}


	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "Patient [name=" + this.getName() + ", surname=" + this.getSurname() + ", username=" + this.getUsername() + ", LBO=" + LBO +
				", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", phoneNum=" + phoneNum + ", address=" + address + "]";
	}

	
	
}
