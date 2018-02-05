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
		String key = "e7ed4dd1445f127eb503c38630a5d3e0";
		this.api = api;
		this.longitude = Double.parseDouble(stop.getLongitude()); // go into alex's csv and pull it
		this.latitude = Double.parseDouble(stop.getLatitude());; // see above
		this.name = stop.getName(); // same as above
	}
	
	public double getLongitude() {
		return this.getLongitude();
	} 
	
	public double getLatitude() {
		return this.getLatitude();
	}
	
	public String getTrainName() {
		return this.name;
	}
}
