
public class LineInfo {
	private Stations[] stations;
	private String trainName;
	private int numberOfTrains;
	
	public LineInfo(String trainName){
		this.stations = null ; // loop through all stations with a certain track via scheduled track that pushes into propertrains station array
								// should it be ordered northbound or southbound
		this.trainName = trainName;
	}
	
	public getDistanceBetween(Station station1, Station station2) {
		//need to get a train that has just arrived at said station and keep a csv of distances using the average velocity and expexted arrival time 
		//from the new station
	}
	
	
	public getStations() {
		return this.stations;
	}
}
