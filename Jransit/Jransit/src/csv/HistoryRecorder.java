package csv;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import mapObjects.Bus;

public class HistoryRecorder {
	private CSVUtilities csv;
	public HistoryRecorder(CSVUtilities csv) {
		this.csv = csv;
	}
	
	public List<String> get(int numOfStatesBehind) {
		List<String> times = csv.getDataString(1);
		List<String> data = csv.getDataString(2);
		int idx = 0;
		int state = 0;
		String curVal = times.get(0);
		boolean checkingForEnd = false;
		int startIdxForState = 0;
		int endIdxForState = 0;
		for (int i = 1; i < times.size(); i++) {
			if (!curVal.equals(times.get(i))) {
				state++;
			}
			if (state > numOfStatesBehind) {
				endIdxForState = i;
			}
			if (state == numOfStatesBehind && !checkingForEnd) {
				startIdxForState = i;
				checkingForEnd = true;
			}
		}
		return data.subList(startIdxForState, endIdxForState + 1);
	}
	
	public void write(List<Bus> bus) {
		long time = System.currentTimeMillis();
		for (Bus b : bus) {
			System.out.println("Write");
			csv.write(String.valueOf(time) + csv.getDelimiter() + b.getInfo().getBusJson());
		}
	}
	
	public void resetFile() {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(csv.getFile());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		pw.close();
	}
}
