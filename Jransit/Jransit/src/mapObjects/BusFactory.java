package mapObjects;

import java.util.ArrayList;
import java.util.List;

import com.teamdev.jxmaps.Map;

import info.BusInfo;
import info.BusInfoListStaticFactory;

public class BusFactory {
	private List<Bus> busses = new ArrayList<>();
	public void placeBusses(Map m) {
		List<BusInfo> busses = BusInfoListStaticFactory.getAllBuses();
		for (BusInfo b : busses) {
			this.busses.add(new Bus(m, b));
		}
	}
	public List<Bus> getBusses() {
		return busses;
	}
}
