package info;

import com.google.gson.JsonObject;

public class BusInfo {
	private double lat;
	private double lon;
	private String lineName;
	private String destinationName;
	// expected arrival time
	public BusInfo(JsonObject b) {
		JsonObject data = b.get("MonitoredVehicleJourney")
		.getAsJsonObject();
		JsonObject location = data.get("VehicleLocation")
		.getAsJsonObject();
		lon = location.get("Longitude").getAsDouble();
		lat = location.get("Latitude").getAsDouble();
	}
	public double getLat() {
		return lat;
	}
	public double getLng() {
		return lon;
	}
	
}
