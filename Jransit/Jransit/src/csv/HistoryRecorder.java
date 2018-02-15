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
		
		List<String> times = csv.getDataString(0);
		List<String> data = csv.getDataString(1);
		int state = 0;
		String curVal = times.get(0);
		boolean checkingForEnd = false;
		int startIdxForState = 0;
		int endIdxForState = 0;
		int idx = 1;
		for (; idx < times.size(); idx++) {
			if (!curVal.equals(times.get(idx))) {
				curVal = times.get(idx);
				state++;
			}
			if (numOfStatesBehind == 0 && state == 1) {
				endIdxForState = idx;
				break;
			}
			if (state > numOfStatesBehind) {
				endIdxForState = idx;
				break;
			}
			if (state == numOfStatesBehind && !checkingForEnd) {
				startIdxForState = idx;
				checkingForEnd = true;
			}
		}
		// if the state wanted is the last one, set last to size
		if (startIdxForState > endIdxForState) {
			endIdxForState = times.size() - 1;
		}
		
		return data.subList(startIdxForState, endIdxForState + 1);
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

	public void write(List<Bus> bus) {
		long time = System.currentTimeMillis();
		for (Bus b : bus) {
			
			csv.write(String.valueOf(time) + csv.getDelimiter() + b.getInfo().getBusJson());
		}
	}
}
