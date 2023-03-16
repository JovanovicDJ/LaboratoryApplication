package entities.users;

public class User {

	private String role;
	private String name;
	private String surname;
	private String username;
	private String password;
	//private long salary;
	
	public User() {
		
	}
	
	public User(String role, String name, String surname, String username, String password) {
		this.role = role;
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
	}

	public String getRole() {
		return this.role;
	}
	
	public String getName() {
		return name;
	}


	public String getSurname() {
		return surname;
	}


	public String getUsername() {
		return username;
	}


	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", surname=" + surname + ", username=" + username + "]";
	}
	
	void countSalary() {};
	
	
	
}
