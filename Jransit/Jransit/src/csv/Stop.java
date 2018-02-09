package csv;


public class Stop implements EntityComposition {
	private final String stopID;
	private final Entity entity;
	private final String zoneID;
	private final String longitude;
	private final String latitude;
	private final String stopUrl;
	private final String description;
	private final String name;
	private final String locationType;
	private final String parentStation;
	
	public String getStopID() {
		return stopID;
	}
	
	public Stop(Entity entity) {
		this.stopID = entity.getPrimaryKey();
		this.entity = entity;
		this.zoneID = entity.getAttribute("zone_id");
		this.longitude = entity.getAttribute("stop_lon");
		this.latitude = entity.getAttribute("stop_lat");
		this.stopUrl = entity.getAttribute("stop_url");
		this.description = entity.getAttribute("stop_desc");
		this.name = entity.getAttribute("stop_name");
		this.locationType = entity.getAttribute("location_type");
		this.parentStation = entity.getAttribute("parent_station");
	}
	
	public Entity getEntity() {
		return entity;
	}

	public String getZoneID() {
		return zoneID;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getStopUrl() {
		return stopUrl;
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

	
	@Override
	public String toString() {
		return String.valueOf(entity.getAttributes());
	}
}
