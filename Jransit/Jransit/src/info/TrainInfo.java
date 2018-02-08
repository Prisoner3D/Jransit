package info;
	
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.transit.realtime.GtfsRealtime.TripUpdate.StopTimeUpdate;

import api.MTAApi;

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
	
	// Add toString, hashCode 
	
	public TrainInfo(MTAApi api, String trip_id) throws IOException {
		this.api = api;
		this.id = trip_id;
		this.trainPosition = api.getVehiclePosition(id);
		this.stopTimes = api.getStopTimes(id);
		if (stopTimes != null) {
			this.currentStation = stopTimes.get(0).getStopId();
			this.nextStation = stopTimes.get(1).getStopId();
			this.direction = Direction.getDirection(stopTimes.get(0).getStopId().charAt(stopTimes.get(0).getStopId().length() - 1));
		}
		this.line = new LineInfo(api,this.id.substring(this.id.indexOf("_")+ 1,this.id.indexOf("_") + 2)); // Memory Issue?
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
        this.currentStation = this.nextStation;
        this.nextStation = null; // refer to above
    }

    public String getCurrentStation() {
        return this.currentStation;
    }

    public String getNextStation() {
        return this.nextStation;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public double getLatitude() {
        return -1; // CSV
    }

    public double getLongitude() {
        return -1; // CSV
    }
    
    public double calcultateDistanceFromStation() {
        return -1; // use the time from station and distance from the next station
	}
}
