package info;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.MTAApi;
import csv.Stop;
import csv.StopsStaticFactory;

/**
 * Information about a train line
 * 
 * @author
 *
 */

public class LineInfo {
    private String lineLetter;
    private List<StationInfo> stations = new ArrayList<StationInfo>();
    private int numberOfStations;

    /**
     * Retrieves information about a train line
     * 
     * @param api
     *            mtaapi
     * @param trainName
     *            name of train to retrieve data
     * @throws IOException
     */
    public LineInfo(MTAApi api, String trainName) throws IOException {
        this.lineLetter = trainName;
        List<Stop> stationColumns = StopsStaticFactory.getAllStops();
        for (Stop x : stationColumns) {
            if (x != null && x.getEntity().getAttribute("stop_id").substring(0, 1).equals(trainName)) {
                StationInfo newStation = new StationInfo(api, x);
                this.stations.add(newStation);
            }
        }
        this.numberOfStations = stations.size();
    }

    public double getDistanceBetween(StationInfo station1, StationInfo station2) {
        return -1; // need to get a train that has just arrived at said station and keep a csv of
                   // distances using the average velocity and expexted arrival time from the new
                   // station
    }

    public String getLineLetter() {
        return this.lineLetter;
    }

    public int getNumberOfStations() {
        return this.numberOfStations;
    }

    public List<StationInfo> getStationInfos() {
        return this.stations;
    }

    public double getTimeBetweenStations(StationInfo sta1, StationInfo sta2) {
        return -1; // WIP
    }

    @Override
    public String toString() {
        return "Line: " + this.lineLetter + " Stations: " + this.stations;
    }
}
