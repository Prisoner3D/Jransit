package csv;

import java.util.List;

public class StopsStaticFactory {
	private static List<Stop> stops;
	
	public static List<Stop> getAllStops() {
		if (stops == null)
			stops = new StopDb().getAllStops();
		return stops;
	}
	public static Stop getStop(String stopID) {
		if (stops == null)
			stops = new StopDb().getAllStops();
		for (Stop stop : stops) {
			if (stop.getStopID().equals(stopID)) {
				return stop;
			}
		}
		return null;
	}
}
