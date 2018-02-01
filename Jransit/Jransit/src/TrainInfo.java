import com.google.transit.realtime.GtfsRealtime.VehiclePosition;

public class TrainInfo {
	private String id;
	private String parentStation;
	private String nextStation;
	private VehiclePosition trainPosition;
	private Direction direction;
	private LineInfo line;
	
	public TrainInfo(String trainID) {
		this.id = trainID;
		this.parentStation = trainPosition.getStopId();
		this.nextStation = null;// go into the api or line info and find the next line Maybe loop and find the station in the array after the parent station
		this.direction = Direction.getDirection(trainPosition.getStopId().charAt(trainPosition.getStopId().length()));
	} 
	
	public double calcultateDistanceFromStation() {
        return -1;
		// use the time from station and distance from the next station
	}
	
	public LineInfo getLine() {
		return this.line;
	}
	
    public void updateStation() {
        this.parentStation = this.nextStation; // get the next station using current parent station
        this.nextStation = null; // refer to above
    }

    public String getParentStation() {
        return this.parentStation;
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
