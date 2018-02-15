package api;

import java.io.IOException;

import bus_api.BusTimeBuilder;
import bus_api.BusTimeStopMonitoring;
import bus_api.BusTimeVehicleMonitoring;

/**
 * 
 * @author Alex
 *
 */
public class BusTimeAPI {
	private static BusTimeBuilder btb;
	static {
		btb = new BusTimeBuilder();
		try {
			btb.setKey("d6ae757b-e881-497f-a066-93a482464b43");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static BusTimeVehicleMonitoring getBusTimeVehicleMonitoring() {
		try {
			return btb.createNewBusTimeVehicleMonitoring();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static BusTimeStopMonitoring getBusTimeStopMonitoring() {
		try {
			return btb.createNewBusTimeStopMonitoring();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
}
