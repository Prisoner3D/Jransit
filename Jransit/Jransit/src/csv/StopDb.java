package csv;

import java.io.File;
import java.util.List;

public class StopDb {
	private CSVUtilities csv;
	private List<String> CSVData;
	
	public StopDb() {
		this.csv = new CSVUtilities(new File("Jransit\\data\\stops.txt"));
		this.CSVData = this.csv.getCSVData();
	}
	
	public Stop getStop(String primaryKey) {
		return (Stop) csv.getEntity(0, primaryKey);
	}
}
