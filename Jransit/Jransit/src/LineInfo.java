import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import csv.CSVUtilities;
import csv.Stop;
import csv.StopDb;
import csv.StopsStaticFactory;

public class LineInfo {
	private ArrayList<StationInfo> stations;
	// private ArrayList<Station> stations; Currently unknown 
	private String trainName;
	private int numberOfStations;
	//CSVUtilities reader = new CSVUtilities(new File("Jransit\\data\\stops.txt"));
	public LineInfo(String trainName) throws IOException{
		this.stations = null;   // loop through all stations with a certain track via scheduled track that pushes into propertrains station array
		                        // should it be ordered northbound or southbound
		this.trainName = trainName;
		List<Stop> stationColumns = StopsStaticFactory.getAllStops();
		for(Stop x : stationColumns) {
			System.out.println(x.getEntity().getAttribute("stop_id"));
			if(x != null && x.getEntity().getAttribute("stop_id").substring(0, 1).equals(trainName)) {
				 System.out.println(x.getStopID());
				 StationInfo newStation = new StationInfo(x);
				 stations.add(newStation);
			}
		}
		this.numberOfStations = stations.size();
	}
	
	public double getDistanceBetween(StationInfo station1, StationInfo station2) {
        return -1;
		//need to get a train that has just arrived at said station and keep a csv of distances using the average velocity and expexted arrival time 
		//from the new station
	}
	/*
	public Station getStations() {
		return null;
	}
	*/
	public ArrayList<StationInfo> getStationInfos() {
		return this.stations;
	}
	
	public double getTimeBetweenStations(StationInfo sta1, StationInfo sta2) {
		return -1;
		// WIP
	}
}
