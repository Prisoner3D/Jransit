package info;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import api.BusTimeAPI;
import bus_api.BusTimeVehicleMonitoring;

/**
 * Contains bus information that stays the same
 * 
 * @author
 *
 */
public class BusInfoListStaticFactory {
	private static BusTimeVehicleMonitoring btvm;
	private static List<BusInfo> busses = new ArrayList<>();
	static {
		btvm = BusTimeAPI.getBusTimeVehicleMonitoring();

	}

	public static List<BusInfo> getAllBuses() {
		
		updateList();
		return busses;
	}

	/**
	 * Update the list of buses
	 */
	private static void updateList() {
		busses.clear();
		try {
			
			JsonElement allBuses = btvm.callApi();
			
			JsonArray allBusesArray = allBuses.getAsJsonObject().get("Siri").getAsJsonObject().get("ServiceDelivery")
					.getAsJsonObject().get("VehicleMonitoringDelivery").getAsJsonArray().get(0).getAsJsonObject()
					.get("VehicleActivity").getAsJsonArray();
			
			for (int i = 0; i < allBusesArray.size(); i++) {
				busses.add(new BusInfo(allBusesArray.get(i).getAsJsonObject()));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
