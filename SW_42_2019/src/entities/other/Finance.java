package entities.other;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Finance {
	
	private Map<String, Double> dict;
	
	public Finance() {
		this.dict = new HashMap<String, Double>();
	};
	
	public Finance(String path) {
		this();
		loadFile(path);
		
	}

	private void loadFile(String path) {
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
			
			String line;
			
			while((line = in.readLine()) != null) {
				line = line.trim();
				String[] tokens = line.split(";");
				this.dict.put(tokens[0].trim(), Double.parseDouble(tokens[1].trim()));
			}
			in.close();
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
	public void updateFile(String path, Map<String, Double> coeffs) {
		FileWriter myWriter;
		try {
			myWriter = new FileWriter(path);
			for (Map.Entry<String, Double> entry : coeffs.entrySet()) {
					myWriter.write(entry.getKey()+";"+String.valueOf(entry.getValue())+"\n");
				}
		myWriter.close();
		}  catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public Map<String, Double> getDict() {
		return dict;
	}
	
}
