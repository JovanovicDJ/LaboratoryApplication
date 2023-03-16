package entities.other;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import entities.users.Patient;
import main.service.UserLoader;

public class MedicalResult {
	
	private String ID;
	private String stage;
	private Map<String, Double> mapIDsAndResults;
	private String[] analysisTypes;
	private Patient patient;
	private String[] laborants;
	private LocalDateTime dateTime;
	private boolean houseVisit;
	private boolean withTime;
	private long price;
	
	final private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
	
	String path = ".\\src\\data\\users.txt";
	UserLoader userload = new UserLoader(path);
	
	public MedicalResult() {
		this.mapIDsAndResults = new HashMap<String, Double>();
	}

	public MedicalResult(String id, String stage, String IDsAndResults, String analysisTypes, String patientLBO, String laborants, String date, String houseVisit, String withTime, String price) {
		this();
		this.ID = id;
		this.stage = stage;
		makeMap(IDsAndResults);
		this.analysisTypes = analysisTypes.split(",");
		this.patient = userload.findPatientByLBO(patientLBO);
		this.laborants = laborants.split(",");
		this.dateTime = LocalDateTime.parse(date, dtf);
		this.houseVisit = Boolean.parseBoolean(houseVisit);
		this.withTime = Boolean.parseBoolean(withTime);
		this.price = Long.parseLong(price);
		
		
	}
	
	private void makeMap(String IDsAndResults) {
		String[] tokens = IDsAndResults.split(",");
		for (String token : tokens) {
			String[]pair = token.split(":");
			this.mapIDsAndResults.put(pair[0].trim(), Double.parseDouble(pair[1].trim()));
		}
		
	}

	public String getID() {
		return ID;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	public Map<String, Double> getmapIDsAndResults() {
		return mapIDsAndResults;
	}

	public String[] getAnalysisTypes() {
		return analysisTypes;
	}

	public Patient getPatient() {
		return patient;
	}

	public String[] getLaborants() {
		return laborants;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public boolean isHouseVisit() {
		return houseVisit;
	}

	public boolean isWithTime() {
		return withTime;
	}

	public long getPrice() {
		return price;
	}
	
}
