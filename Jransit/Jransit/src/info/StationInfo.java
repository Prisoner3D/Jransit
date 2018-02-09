package info;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.transit.realtime.GtfsRealtime.TripUpdate.StopTimeUpdate;

import api.MTAApi;
import csv.Stop;
import csv.StopsStaticFactory;

public class StationInfo {
    private Stop stop;
    private String name;
    private String route;
    private MTAApi api;
    private Location location;

    public StationInfo(MTAApi api, Stop stop) throws IOException {
        this.api = api;
        this.stop = stop;
        this.name = stop.getName();
        if (this.name != null) {
            this.route = this.name.substring(0, 1); // Prob should add to Stop @afeng
        }
        this.location = new Location(Double.parseDouble(stop.getLongitude()), Double.parseDouble(stop.getLatitude()));
    }

    public String getLine() {
        return stop.getStopID().substring(0, 1);
    }

    public long getArrivalTime() { // Should be current station
        List<TrainInfo> traininfos = api.getTrains();
        List<TrainInfo> filteredTrains = new ArrayList<>();
        for (TrainInfo x : traininfos) {
            if (stop.getStopID().equals(x.getNextStation())) {
                return x.getArrivalTime(stop.getStopID());
            }
        }
        return -1;
    }

    // Rewrite and add to MTAApi so that it does not access VehiclePosition
    public List<Long> getAllArrivalTimes() {
        List<Long> times = new ArrayList<Long>();
        for (TrainInfo train: api.getTrains()) {
            for (StopTimeUpdate stu : train.getStopTime()) {
                if (stu.getStopId().equals(name)) {
                    times.add(stu.getArrival().getTime());
                }
            }
        }
        return times;
    }
    
    public double getLongitude() {
        return Double.parseDouble(stop.getLongitude());
    }

    public double getLatitude() {
        return Double.parseDouble(stop.getLatitude());
    }

    public String getStationName() {
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
