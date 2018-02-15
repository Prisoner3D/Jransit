package mapObjects;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teamdev.jxmaps.Icon;
import com.teamdev.jxmaps.Map;

import UserInterface.MapsApp;
import info.BusInfo;
import info.BusInfoListStaticFactory;

/**
 * Represents a collection of buses
 * 
 * @author
 *
 */
public class BusFactory {
	private List<Bus> busses = new ArrayList<>();
	private Map m; // placeBusses initializes m and Ico and always runs first
	private Icon ico;

	public List<Bus> getBusses() {
		return busses;
	}

	/**
	 * Display a collection of buses on the map
	 * 
	 * @param busData
	 *            array containing buses to be displayed
	 */
	public void placeBusses(List<String> busData) {
		this.removeBusses();
		JsonParser parser = new JsonParser();
		for (String s : busData) {
			JsonObject o = parser.parse(s).getAsJsonObject();
			this.busses.add(new Bus(MapsApp.mapView.getMap(), new BusInfo(o), true));
		}
	}

	/**
	 * Create the list of buses to be displayed
	 * 
	 * @param m
	 * @param ico
	 * @param place
	 * @return
	 */
	public List<Bus> placeBusses(Map m, boolean place) {
		List<BusInfo> busses = BusInfoListStaticFactory.getAllBuses();
		if (place) {
			this.removeBusses();
		}
		
		for (int i = 0; i < busses.size(); i++) {
			this.busses.add(new Bus(m, busses.get(i), place));
		}
		return new ArrayList<>(this.busses);
	}

	/**
	 * Remove all bus markers from the map
	 */
	public void removeBusses() {
		for (Bus bus : busses) {
			bus.getMarker().remove();
		}
		this.busses.clear();
	}
}