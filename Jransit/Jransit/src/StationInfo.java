import java.io.IOException;

import csv.Stop;
import csv.StopsStaticFactory;

public class StationInfo {
	private Stop stop;
	private String name;
	private String route;
	private MTAApi api;
	
	
	public StationInfo(MTAApi api, Stop stop) throws IOException {
		this.api = api;
		this.stop = stop;
	}
	
	public double getLongitude() {
		return Double.parseDouble(stop.getLongitude());
	} 
	
	public double getLatitude() {
		return Double.parseDouble(stop.getLatitude());
	}
	
	public String getTrainName() {
		return stop.getName();
	}
	
	public String getZoneID() {
		return stop.getZoneID();
	}

	public String getStopUrl() {
		return stop.getStopUrl();
	}

	public String getDescription() {
		return stop.getDescription();
	}

	public String getName() {
		return stop.getName();
	}

	public String getLocationType() {
		return stop.getLocationType();
	}
	/*
	public String getParentStation() {
		//return StopsStaticFactory.getStop(stop.getParentStation());
	}
	*/
}
