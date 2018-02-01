import java.io.IOException;
import java.util.List;

import com.google.transit.realtime.GtfsRealtime;
import com.google.transit.realtime.GtfsRealtime.TripUpdate.StopTimeUpdate;
import com.google.transit.realtime.GtfsRealtime.VehiclePosition;

public class TrainInfo {
	private String id;
	private VehiclePosition trainPosition;
	private List<StopTimeUpdate> stopTimes;
	private String currentStation;
	private String nextStation;
	private Direction direction;
	private LineInfo line;
	
	public TrainInfo(String trip_id) throws IOException {
		String key = "e7ed4dd1445f127eb503c38630a5d3e0";
		MTAApi api = new MTAApi(key, TrainFeed.NUM_S); // Might change to using existing api object
		this.id = trip_id;
		this.trainPosition = api.grabVehiclePosition(trip_id);
		this.stopTimes = api.getStopTimes(trip_id);
		this.currentStation = trainPosition.getStopId();
		this.nextStation = stopTimes.get(1).getStopId();
		this.direction = Direction.getDirection(trainPosition.getStopId().charAt(trainPosition.getStopId().length()));
		this.line = new LineInfo(this.id.substring(this.id.length() - 4, this.id.length() - 3)); // Joshua's part
		System.out.println(this.line);
	} 
	
	public double calcultateDistanceFromStation() {
        return -1; // use the time from station and distance from the next station
	}
	
	public LineInfo getLine() {
		return this.line;
	}
	
    public void updateStation() {
        this.currentStation = this.nextStation; // get the next station using current parent station
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
        return 0;
    }

    public double getLongitude() {
        return 0;
    }
}
