package csv;

public class Stop extends Entity {
	private final String stopID;
	
	public String getStopID() {
		return stopID;
	}
	
	public Stop(String stopID) {
		super(stopID);
		this.stopID = stopID;
	}
	
	public String getZoneID() {
		return this.getAttribute("zone_id");
	}
	
	public String getLongitude() {
		return this.getAttribute("stop_lon");
	}
	
	public String getLatitude() {
		return this.getAttribute("stop_lat");
	}
	
	public String getStopUrl() {
		return this.getAttribute("stop_url");
	}
	
	public String getStopDescription() {
		return this.getAttribute("stop_desc");
	}
	
	public String getStopName() {
		return this.getAttribute("stop_name");
	}
	
	public String getLocationType() {
		return this.getAttribute("location_type");
	}
	
	public String getParentStation() {
		return this.getAttribute("parent_station");
	}
}
