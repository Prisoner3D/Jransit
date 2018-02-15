package mapObjects;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teamdev.jxmaps.Icon;
import com.teamdev.jxmaps.Map;

import UserInterface.JavaFXExample;
import info.BusInfo;
import info.BusInfoListStaticFactory;

public class BusFactory {
	private List<Bus> busses = new ArrayList<>();
	private Map m; // placeBusses initializes m and Ico and always runs first
	private Icon ico;
	public void placeBusses(List<String> busData) {
		this.removeBusses();
		JsonParser parser = new JsonParser();
		for (String s : busData) {
			JsonObject o = parser.parse(s).getAsJsonObject();
			this.busses.add(new Bus(JavaFXExample.mapView.getMap(), new BusInfo(o), true));
		}
	}
	
	
	public List<Bus> placeBusses(Map m, Icon ico, boolean place) {
		List<BusInfo> busses = BusInfoListStaticFactory.getAllBuses();
		this.removeBusses();
		for (int i = 0; i < busses.size(); i++) {
			this.busses.add(new Bus(m, busses.get(i), place));
		}
		return new ArrayList<>(this.busses);
	}
	
	public void removeBusses() {
		for (Bus bus : busses) {
			bus.getMarker().remove();
		}
		this.busses.clear();
	}
	
	public List<Bus> getBusses() {
		return busses;
	}
}
