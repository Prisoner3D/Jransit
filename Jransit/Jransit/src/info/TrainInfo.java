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
import com.google.transit.realtime.GtfsRealtime.VehiclePosition.VehicleStopStatus;

/**
 * Train information that changes. (position, station location, etc.)
 * 
 * @author
 *
 */
public class TrainInfo {
    private final MTAApi api;
    private final String id;
    private final LineInfo line;
    private VehiclePosition trainPosition;
    private List<StopTimeUpdate> stopTimes = null;
    private String currentStation = null;
    private String nextStation = null;
    private Direction direction = null;
    private Location location;
    private VehicleStopStatus status;

    public TrainInfo(final MTAApi api, final String trip_id) throws IOException {
        this.api = api;
        this.id = trip_id;
        this.trainPosition = api.getVehiclePosition(id);
        this.updateStation();
        this.line = new LineInfo(api, this.id.substring(this.id.indexOf("_") + 1, this.id.indexOf("_") + 2)); // Memory issue
        this.setLocation();
    }

    public double calcultateDistanceFromStation(final StationInfo station) {
        return -1; // use the time from station and distance from the next station
    }

    public long getArrivalTime(final String stop_id) {
        return api.getArrivalTime(this.id, stop_id);
    }

    public StationInfo getCurrentStation() {
        return new StationInfo(api, StopsStaticFactory.getStop(this.currentStation));
    }

    public Direction getDirection() {
        return this.direction;
    }

    public String getId() {
        return this.id;
    }

    public double getLatitude() {
        return location.getLatitude();
    }

    public LineInfo getLine() {
        return this.line;
    }

    public Location getLocation() {
        return this.location;
    }

    public double getLongitude() {
        return location.getLongitude();
    }

    public StationInfo getNextStation() {
        if (this.nextStation == null) {
            return null;
        }
        return new StationInfo(api, StopsStaticFactory.getStop(this.nextStation));
    }

    public List<StopTimeUpdate> getStopTimes() {
        return this.stopTimes;
    }

    public void setLocation() {
        updateStation();
        if (this.status.equals(VehicleStopStatus.STOPPED_AT)) {
            this.location = this.getCurrentStation().getLocation();
            return;
        }
        StationInfo current = this.getCurrentStation();
        StationInfo next = this.getNextStation();
        if (next == null) {
            this.location = current.getLocation();
            return;
        }
        Location currentCoords = current.getLocation();
        Location nextCoords = next.getLocation();
        this.location = TrainMapsUtil.getTrainPosition(currentCoords, nextCoords, this.stopTimes.get(1).getArrival().getTime(), this.stopTimes.get(0).getArrival().getTime(), 30.0);
    }

    public String toString() {
        return "Trip ID: " + id + " Current Station: " + currentStation + " Next Station: " + nextStation + " Direction: " + direction + this.location;
    }

    public void updateStation() {
        this.stopTimes = api.getStopTimes(id);
        if (stopTimes != null) {
            this.trainPosition = api.getVehiclePosition(id);
            this.status = trainPosition.getCurrentStatus();
            this.currentStation = stopTimes.get(0).getStopId(); // TODO: Does this always return the current stop?
            if (stopTimes.size() > 1) {
                this.nextStation = stopTimes.get(1).getStopId();
            }
            this.direction = Direction.getDirection(stopTimes.get(0).getStopId().charAt(stopTimes.get(0).getStopId().length() - 1));
        }
    }
}
