package mapObjects;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.teamdev.jxmaps.Icon;
import com.teamdev.jxmaps.Map;

import info.BusInfo;
import info.BusInfoListStaticFactory;

public class BusFactory {
	private List<Bus> busses = new ArrayList<>();
	private Map m; // placeBusses initializes m and Ico and always runs first
	private Icon ico;
	public BusFactory(List<String> busData) {
		JsonParser parser = new JsonParser();
		for (String s : busData) {
			JsonObject o = parser.parse(s).getAsJsonObject();
			this.busses.add(new Bus(ico, m, new BusInfo(o), false));
		}
	}
	
	public BusFactory() {
		
	}
	
	public void placeBusses(Map m, Icon ico, boolean place) {
		List<BusInfo> busses = BusInfoListStaticFactory.getAllBuses();
		this.removeBusses();
		this.busses.clear();
		for (int i = 0; i < busses.size(); i++) {
			this.busses.add(new Bus(ico, m, busses.get(i), place));
		}
	}
	
	public void removeBusses() {
		for (Bus bus : busses) {
			bus.getMarker().remove();
		}
	}
	
	public List<Bus> getBusses() {
		return busses;
	}
}
