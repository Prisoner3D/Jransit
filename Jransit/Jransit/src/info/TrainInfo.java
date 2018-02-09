package info;
	
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.transit.realtime.GtfsRealtime.TripUpdate.StopTimeUpdate;

import api.MTAApi;
import csv.Entity;
import csv.Stop;
import csv.StopsStaticFactory;
import util.TrainMapsUtil;

import com.google.transit.realtime.GtfsRealtime.VehiclePosition;

public class TrainInfo {
	private MTAApi api;
	private String id;
	private VehiclePosition trainPosition;
	private List<StopTimeUpdate> stopTimes = new ArrayList<>();
	private String currentStation = null;
	private String nextStation = null;
	private Direction direction = null;
	private LineInfo line;
	private Location location;
	
	public TrainInfo(MTAApi api, String trip_id) throws IOException {
		this.api = api;
		this.id = trip_id;
		this.trainPosition = api.getVehiclePosition(id);
		updateStation();
		this.line = new LineInfo(api,this.id.substring(this.id.indexOf("_")+ 1,this.id.indexOf("_") + 2)); // Memory Issue?
		this.setLocation();
	}
	
	public String getId() {
		return this.id;
	}
	
	public long getArrivalTime(String stop_id) {
    	return api.getArrivalTime(this.id, stop_id);
    }
	
	public LineInfo getLine() {
		return this.line;
	}
	
    public void updateStation() {
    	this.stopTimes = api.getStopTimes(id);
		if (stopTimes != null) {
			this.currentStation = stopTimes.get(0).getStopId(); // TODO: Does this always return the current stop?
			if (stopTimes.size() > 1) {
			    this.nextStation = stopTimes.get(1).getStopId();
			}
			this.direction = Direction.getDirection(stopTimes.get(0).getStopId().charAt(stopTimes.get(0).getStopId().length() - 1));
		}
    }

    public StationInfo getCurrentStation() {
        return new StationInfo(api, StopsStaticFactory.getStop(this.currentStation));
    }

    public StationInfo getNextStation() {
        return new StationInfo(api, StopsStaticFactory.getStop(this.nextStation));
    }

    public Direction getDirection() {
        return this.direction;
    }
    
    public void setLocation() {
    	StationInfo current = this.getCurrentStation();
    	StationInfo next = this.getNextStation();
    	Location currentCoords = new Location(current.getLatitude(), current.getLongitude());
    	Location nextCoords = new Location(next.getLatitude(), next.getLongitude());
    	this.location = TrainMapsUtil.getTrainPosition(currentCoords, nextCoords, this.stopTimes.get(1).getArrival().getTime() - this.stopTimes.get(0).getArrival().getTime(), 30.0); //TODO change units on time and velocity;
    }
    
    public double getLatitude() {
    	
        return location.getLatitude();
    }

    public double getLongitude() {
        return location.getLongitude();
    }
    
    public double calcultateDistanceFromStation(StationInfo station) {
        return -1; // use the time from station and distance from the next station
	}
}
