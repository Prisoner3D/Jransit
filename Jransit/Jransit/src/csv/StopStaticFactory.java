package csv;

import java.util.List;

public class StopStaticFactory {
	private List<Stop> stops;
	public StopStaticFactory() {
		this.stops = new StopDb().getAllStops();
	}
	public List<Stop> getStops() {
		return stops;
	}
	public Stop getStop(String stopID) {
		for (Stop stop : stops) {
			if (stop.getStopID().equals(stopID)) {
				return stop;
			}
		}
		return null;
	}
}
