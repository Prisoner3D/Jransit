package csv;

import java.util.List;

/**
 * Preserves a single instance of all stops
 * 
 * @author Alex
 *
 */
public class StopsStaticFactory {
	private static List<Stop> stops;
	static {
		stops = new StopDb().getAllStops();
	}

	public static List<Stop> getAllStops() {
		return stops;
	}

	public static Stop getStop(String stopID) {
		for (Stop stop : stops) {
			if (stop.getStopID().equals(stopID)) {
				return stop;
			}
		}
		return null;
	}
}
