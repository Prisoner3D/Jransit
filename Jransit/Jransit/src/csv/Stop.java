package csv;

public class Stop extends Entity {
	private final String stopID;
	public String getZoneID() {
		return zoneID;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getStopURL() {
		return stopURL;
	}

	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public String getLocationType() {
		return locationType;
	}

	public String getParentStation() {
		return parentStation;
	}

	private final String zoneID;
	private final String longitude;
	private final String latitude;
	private final String stopURL;
	private final String description;
	private final String name;
	private final String locationType;
	private final String parentStation;
	
	public String getStopID() {
		return stopID;
	}
	
	public Stop(String stopID) {
		super(stopID);
		this.stopID = stopID;
		this.zoneID = this.getAttribute("zone_id");
		this.longitude = this.getAttribute("stop_lon");
		this.latitude = this.getAttribute("stop_lat");
		this.stopURL = this.getAttribute("stop_url");
		this.description = this.getAttribute("stop_desc");
		this.name = this.getAttribute("stop_name");
		this.locationType = this.getAttribute("location_type");
		this.parentStation = this.getAttribute("parent_station");
	}
	
}
