package main.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import entities.other.Analysis;

public class AnalyseLoader {

	private List<Analysis> analyses;
	
	public AnalyseLoader() {
		this.analyses = new ArrayList<Analysis>();
	}
	
	public AnalyseLoader(String fileName) {
		this();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf-8"));
			
			String line;
			
			while((line = in.readLine()) != null) {
				line = line.trim();
				Analysis u = parse(line);
				this.analyses.add(u);
			}
			in.close();
			
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

			
	}

	private Analysis parse(String line) {
		String[] tokens = line.split(";");
		Analysis a = new Analysis(tokens[0].trim(), tokens[1].trim(), tokens[2].trim(), tokens[3].trim(), tokens[4].trim(), tokens[5].trim(), tokens[6].trim());
		return a;
	}

	public List<Analysis> getAnalyses() {
		return analyses;
	}

	public boolean updateAnalysis(List<Analysis> analysis, String filename) {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(filename);
			for (Analysis a : analysis) {
				fileWriter.write(a.getId()+";"+a.getName()+";"+a.getType()+";"+String.valueOf(a.getMinValue())+";"+String.valueOf(a.getMaxValue())+";"+a.getMeasuringUnit()+";"+String.valueOf(a.getPrice()+"\n"));
			}
			fileWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public Analysis getAnalysisByID(String ID) {
		for (Analysis analysis : analyses) {
			if (analysis.getId().equals(ID)) {
				return analysis;
			}
		}
		return null;
		
	}
	
	
	}