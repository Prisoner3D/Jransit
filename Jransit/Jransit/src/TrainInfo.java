import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.transit.realtime.GtfsRealtime.TripUpdate.StopTimeUpdate;
import com.google.transit.realtime.GtfsRealtime.VehiclePosition;

public class TrainInfo {
	private String id;
	private VehiclePosition trainPosition;
	private List<StopTimeUpdate> stopTimes = new ArrayList<>();
	private String currentStation = null;
	private String nextStation = null;
	private Direction direction = null;
	private LineInfo line;
	
	public TrainInfo(MTAApi api, String trip_id, LineInfo line) throws IOException {
		this.id = trip_id;
		this.trainPosition = api.grabVehiclePosition(id);
		this.stopTimes = api.getStopTimes(id);
		if (stopTimes.size() > 0) {
			this.currentStation = stopTimes.get(0).getStopId();
			this.nextStation = stopTimes.get(1).getStopId();
			this.direction = Direction.getDirection(stopTimes.get(0).getStopId().charAt(stopTimes.get(0).getStopId().length() - 1));
		}
		this.line = line; // Joshua u need to fix this pronto plsssssss	
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
