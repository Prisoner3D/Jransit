package bus_api;

import java.io.IOException;

import com.google.gson.JsonElement;

import bus_api.BusTimeBuilder;
import bus_api.BusTimeVehicleMonitoring;
import info.BusInfoListStaticFactory;

public class Test {
	public static void main(String[] args) throws IOException, InterruptedException {
		BusInfoListStaticFactory.getAllBuses();

	}
}
