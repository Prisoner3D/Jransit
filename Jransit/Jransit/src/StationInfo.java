import java.io.IOException;

import csv.Stop;

public class StationInfo {
	private double latitude;
	private double longitude;
	private Stop stop;
	private String name;
	private String route;
	private MTAApi api;
	
	public StationInfo(MTAApi api, Stop stop) throws IOException {
		this.api = api;
		this.longitude = Double.parseDouble(stop.getLongitude()); // go into alex's csv and pull it
		this.latitude = Double.parseDouble(stop.getLatitude());; // see above
		this.name = stop.getName(); // same as above
	}
	
	public double getLongitude() {
		return longitude;
	} 
	
	public double getLatitude() {
		return latitude;
	}
	
	public String getTrainName() {
		return name;
	}
}
