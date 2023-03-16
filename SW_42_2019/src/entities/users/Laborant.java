package entities.users;

import java.util.Calendar;

import entities.other.Finance;

public class Laborant extends User {

	private double internship;
	private String qualification;
	private double salary;
	private double workDone;
	private String[] spec;
	
	String path = ".\\src\\data\\finance.txt";
	Finance finance = new Finance(path);
	
	public Laborant() {
		
	}
	
	public Laborant(String role, String name, String surname, String username, String password, String internship, String qualification, String workDone, String spec) {
		super(role, name, surname, username, password);
		this.internship = Double.parseDouble(internship);
		this.qualification = qualification;
		this.workDone = Double.parseDouble(workDone);
		this.spec = spec.split(",");
		this.countSalary();
	}
	
	@Override
	public void countSalary() {
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		double workingYears = (double)currentYear - this.internship;
		if (workingYears == 0) {
			workingYears = 1;
		}
		
		this.salary = workingYears*finance.getDict().get("base")*finance.getDict().get(this.qualification)+finance.getDict().get("bonusLaborant")*this.workDone;
	}

	
	public double getInternship() {
		return internship;
	}


	public String getQualification() {
		return qualification;
	}

	public double getSalary() {
		return salary;
	}

	public double getWorkDone() {
		return workDone;
	}
	

	public void setWorkDone(double workDone) {
		this.workDone = workDone;
	}

	public String[] getSpec() {
		return spec;
	}
}
