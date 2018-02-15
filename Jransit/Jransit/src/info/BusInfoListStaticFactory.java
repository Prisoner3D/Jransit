package info;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import api.BusTimeAPI;
import bus_api.BusTimeVehicleMonitoring;

public class BusInfoListStaticFactory {
	private static BusTimeVehicleMonitoring btvm;
	private static List<BusInfo> busses = new ArrayList<>();
	static {
		btvm = BusTimeAPI.getBusTimeVehicleMonitoring();
		
	}
	
	private static void updateList() {
		busses.clear();
		try {
			System.out.println("test");
			JsonElement allBuses = btvm.callApi();
			System.out.println("test");
			JsonArray allBusesArray = allBuses.getAsJsonObject().get("Siri")
										.getAsJsonObject().get("ServiceDelivery")
										.getAsJsonObject().get("VehicleMonitoringDelivery")
										.getAsJsonArray().get(0)
										.getAsJsonObject()
										.get("VehicleActivity")
										.getAsJsonArray();
			System.out.println("test");
			for (int i = 0; i < allBusesArray.size(); i++) {
				busses.add(new BusInfo(allBusesArray.get(i).getAsJsonObject()));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<BusInfo> getAllBuses() {
		System.out.println("test2");
		updateList();
		return busses;
	}
}
