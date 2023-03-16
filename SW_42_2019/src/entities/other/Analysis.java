package entities.other;

public class Analysis {

	private String id;
	private String name;
	private String type;
	private double minValue;
	private double maxValue;
	private String measuringUnit;
	private long price;
	
	public Analysis() {
		
	}
	
	public Analysis (String id, String name, String type, String minValue, String maxValue, String mUnit, String price) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.minValue = Double.parseDouble(minValue);
		this.maxValue = Double.parseDouble(maxValue);
		this.measuringUnit = mUnit;
		this.price = Long.parseLong(price);
	}
	
	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}


	public long getPrice() {
		return price;
	}

	public double getMinValue() {
		return minValue;
	}

	public double getMaxValue() {
		return maxValue;
	}

	public String getMeasuringUnit() {
		return measuringUnit;
	}


	
}
