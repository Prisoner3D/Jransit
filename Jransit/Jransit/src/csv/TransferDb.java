package csv;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TransferDb {
	private CSVUtilities csv;
	private List<String> CSVData;

	public TransferDb() {
		this.csv = new CSVUtilities(new File("Jransit\\data\\transfers.txt"));
		this.CSVData = this.csv.getCSVData();
	}

	public List<Transfer> getTransfers(String fromID) {
		List<Entity> entities = csv.getAllEntities(1, fromID);
		List<Transfer> transfers = new ArrayList<>();
		for (Entity ent : entities) {
			transfers.add(new Transfer(ent));
		}
		return transfers;
	}
}
