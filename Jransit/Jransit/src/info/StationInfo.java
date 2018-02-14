package info;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.MTAApi;
import csv.Stop;
import csv.StopsStaticFactory;

public class StationInfo {
    private Stop stop;
    private String name;
    private String route;
    private MTAApi api;
    private Location location;

    public StationInfo(MTAApi api, Stop stop) {
        this.api = api;
        this.stop = stop;
        this.location = new Location(Double.parseDouble(stop.getLatitude()), Double.parseDouble(stop.getLongitude()));
    }

    public String getLine() {
        return stop.getStopID().substring(0, 1);
    }

    public long getArrivalTime() {
        List<TrainInfo> traininfos = api.getTrains();
        List<TrainInfo> filteredTrains = new ArrayList<>();
        for (TrainInfo x : traininfos) {
            if (stop.getStopID().equals(x.getNextStation())) {
                return x.getArrivalTime(stop.getStopID());
            }
        }
        return -1;
    }

    public double getLongitude() {
        return Double.parseDouble(stop.getLongitude());
    }

    public double getLatitude() {
        return Double.parseDouble(stop.getLatitude());
    }

    public String getStopID() {
        return stop.getStopID();
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
    
    public Location getLocation() {
    	return this.location;
    }
    /*
    public String getParentStation() {
    	//return StopsStaticFactory.getStop(stop.getParentStation());
    }
    */
    @Override
    public String toString() {
        return "Station: " + this.name + " Line: " + this.route + " " + this.location;
    }
}
