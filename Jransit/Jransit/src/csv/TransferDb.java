package csv;

import java.io.File;
import java.util.List;

public class TransferDb {
	private CSVUtilities csv;
	private List<String> CSVData;
	
	public TransferDb() {
		this.csv = new CSVUtilities(new File("Jransit\\data\\stops.txt"));
		this.CSVData = this.csv.getCSVData();
	}
	
	public List<Transfer> getTransfers() {
		
	}
}
