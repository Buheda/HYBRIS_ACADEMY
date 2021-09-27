package core.db.entity;

public enum Products_status {
	out_of_stock,
	in_stock,
	running_low,
	none;
	
	private final static Products_status[] enumValues = Products_status.values(); 
	
	public static Products_status fromInteger(int x) {
		return enumValues[x];	
	}
} 