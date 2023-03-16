package main.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entities.other.MedicalResult;

public class MedResultLoader {

	private List<MedicalResult> medicalResults;
	
	final private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy. HH:mm");
	
	public MedResultLoader() {
		this.medicalResults = new ArrayList<MedicalResult>();
	}

	public MedResultLoader(String fileName) {
		this();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));
			
			String line;
			
			while((line = in.readLine()) != null) {
				line = line.trim();
				MedicalResult mr = parse(line);
				this.medicalResults.add(mr);
			}
			in.close();
			
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public List<MedicalResult> getMedicalResults() {
		return medicalResults;
	}

	private MedicalResult parse(String line) {
		String[] tokens = line.split(";");
		MedicalResult mr = new MedicalResult(tokens[0].trim(), tokens[1].trim(), tokens[2].trim(), tokens[3].trim(), tokens[4].trim(), tokens[5].trim(), tokens[6].trim(), tokens[7].trim(), tokens[8].trim(), tokens[9].trim());
		return mr;
	}
	
	public boolean updateMedResults(List<MedicalResult> medicalResults, String filename) {
		FileWriter myWriter;
		try {
			myWriter = new FileWriter(filename);
			for (MedicalResult medicalResult : medicalResults) {
				String acumulator = "";
				for (Map.Entry<String, Double> entry : medicalResult.getmapIDsAndResults().entrySet()) {
					acumulator = acumulator + entry.getKey()+":"+String.valueOf(entry.getValue())+",";
				}
				String types = String.join(",", medicalResult.getAnalysisTypes());
				String laborants = String.join(",", medicalResult.getLaborants());
				String dateFormated = medicalResult.getDateTime().format(dtf);
				String houseVisitFormated = String.valueOf(medicalResult.isHouseVisit());
				String withTimeFormated = String.valueOf(medicalResult.isWithTime());
				String priceFormated = String.valueOf(medicalResult.getPrice());
				myWriter.write(medicalResult.getID()+";"+medicalResult.getStage()+";"+acumulator+";"+types+";"+medicalResult.getPatient().getLBO()+";"+laborants+";"+dateFormated+";"+houseVisitFormated+";"+withTimeFormated+";"+priceFormated+"\n");
			}
			
			
			
			myWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public MedicalResult getMedResultById(String id) {
		for (MedicalResult medicalResult : medicalResults) {
			if (medicalResult.getID().equals(id)) {
				return medicalResult;
			}
		}
		return null;
		
		
	}
	
}
