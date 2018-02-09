package csv;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StopDb {
	private CSVUtilities csv;
	private List<String> CSVData;
	
	public StopDb() {
		this.csv = new CSVUtilities(new File("Jransit\\data\\stops.txt"));
		this.CSVData = this.csv.getCSVData();
	}
	
	public Stop getStop(String primaryKey) {
		return new Stop(csv.getEntity(1, primaryKey));
	}
	
	public List<Stop> getAllStops() {
		List<Stop> stops = new ArrayList<Stop>();
		List<Entity> entities = this.csv.getAllEntities(1);
		for (Entity entity : entities) {
			stops.add(new Stop(entity));
		}
		return stops;
	}
}
