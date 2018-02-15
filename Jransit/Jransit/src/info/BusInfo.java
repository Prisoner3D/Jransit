package info;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * Information about buses. (position, line, route destination)
 * 
 * @author
 *
 */
public class BusInfo {
	private double lat;
	private double lon;
	private String lineName;
	private String destinationName; // LAST STATION
	private String nextStop = "N/A";
	private String presentableDistance;
	private JsonObject busJson;

	/**
	 * Converts data from JsonObject into usable fields
	 * 
	 * @param b
	 *            JsonObject with bus information
	 */
	// expected arrival time
	public BusInfo(JsonObject b) {
		this.busJson = b;
		JsonObject data = b.get("MonitoredVehicleJourney").getAsJsonObject();
		// 
		destinationName = data.get("DestinationName").toString().replace("\"", "");
		JsonObject location = data.get("VehicleLocation").getAsJsonObject();
		JsonElement lineName = data.get("PublishedLineName");
		if (data.get("MonitoredCall") != null) {
			this.nextStop = data.get("MonitoredCall").getAsJsonObject().get("StopPointName").toString().replace("\"",
					"");
			this.presentableDistance = data.get("MonitoredCall").getAsJsonObject().get("Extensions").getAsJsonObject()
					.get("Distances").getAsJsonObject().get("PresentableDistance").toString().replace("\"", "");
			;
		}

		lon = location.get("Longitude").getAsDouble();
		lat = location.get("Latitude").getAsDouble();
		this.lineName = lineName.getAsString();
	}

	public JsonObject getBusJson() {
		return busJson;
	}

	public String getDestinationName() {
		return destinationName;
	}

	public double getLat() {
		return lat;
	}

	public String getLineName() {
		return lineName;
	}

	public double getLng() {
		return lon;
	}

	public String getNextStop() {
		return nextStop;
	}

	public String getPresentableDistance() {
		return presentableDistance;
	}

}