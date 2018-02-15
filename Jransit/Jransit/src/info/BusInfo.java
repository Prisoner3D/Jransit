package info;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Information about buses. (position, line, route destination)
 * @author 
 *
 */
public class BusInfo {
	private double lat;
	private double lon;
	private String lineName;
	private String destinationName;
	private JsonObject busJson;
	// expected arrival time
	
	/**
	 * Converts data from JsonObject into usable fields
	 * @param b JsonObject with bus information
	 */
	public BusInfo(JsonObject b) {
		this.busJson = b;
		JsonObject data = b.get("MonitoredVehicleJourney")
		.getAsJsonObject();
		JsonObject location = data.get("VehicleLocation")
		.getAsJsonObject();
		JsonElement lineName = data.get("PublishedLineName");
		
		lon = location.get("Longitude").getAsDouble();
		lat = location.get("Latitude").getAsDouble();
		this.lineName = lineName.getAsString();
	}
	public double getLat() {
		return lat;
	}
	public double getLng() {
		return lon;
	}
	public JsonObject getBusJson() {
		return busJson;
	}
	public String getLineName() {
		return lineName;
	}
	
	
	
	
}
