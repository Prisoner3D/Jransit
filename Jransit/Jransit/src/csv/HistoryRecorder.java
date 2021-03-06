package csv;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import mapObjects.Bus;

/**
 * Reads and write time series data of busses
 *
 */
public class HistoryRecorder {
    private CSVUtilities csv;

    public HistoryRecorder(CSVUtilities csv) {
        this.csv = csv;
    }

    /**
     * Gets data from x states behind the livest state
     * @param numOfStatesBehind
     * @return bus data for one time series
     */
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

    /**
     * resets the content in the file
     */
    public void resetFile() {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(csv.getFile());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        pw.close();
    }

    /**
     * Writes bus data into the file
     * @param bus list of busses
     */
    public void write(List<Bus> bus) {
        long time = System.currentTimeMillis();
        csv.customBusWrite(bus, time);
    }
}
