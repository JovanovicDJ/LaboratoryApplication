package entities.other;

import java.util.concurrent.ThreadLocalRandom;

public class AnalyzingMachine {

	public AnalyzingMachine() {
		
	}
	
	public String getAnalysisResult(double minValue, double maxValue) {
		double difference = (maxValue-minValue)/2;
		double max = maxValue + difference;
		double min = minValue - difference;
		if (min == 0.0 || min < 0.0) {
			min = 0.1;
		}
		double random = ThreadLocalRandom.current().nextDouble(min, max);
		return String.format("%.2f", random).replace(",", ".");
		}
	

}
