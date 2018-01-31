package csv;

public class Stop {
	private final String stopID;
	private final Entity entity;
	
	public String getStopID() {
		return stopID;
	}
	
	public Stop(Entity entity) {
		this.stopID = entity.getPrimaryKey();
		this.entity = entity;
	}
	
	public final String getZoneID() {
		return entity.getAttribute("zone_id");
	}
	
	public final String getLongitude() {
		return entity.getAttribute("stop_lon");
	}
	
	public final String getLatitude() {
		return entity.getAttribute("stop_lat");
	}
	
	public final String getStopUrl() {
		return entity.getAttribute("stop_url");
	}
	
	public final String getStopDescription() {
		return entity.getAttribute("stop_desc");
	}
	
	public final String getStopName() {
		return entity.getAttribute("stop_name");
	}
	
	public final String getLocationType() {
		return entity.getAttribute("location_type");
	}
	
	public final String getParentStation() {
		return entity.getAttribute("parent_station");
	}
	
	@Override
	public String toString() {
		return String.valueOf(entity.getAttributes());
	}
}
