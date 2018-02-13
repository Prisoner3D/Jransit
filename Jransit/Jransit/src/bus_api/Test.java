package bus_api;
import java.io.IOException;

import com.google.gson.JsonElement;

import bus_api.BusTimeBuilder;
import bus_api.BusTimeVehicleMonitoring;

public class Test {
	public static void main(String[] args) throws IOException, InterruptedException {
		BusTimeBuilder btb = new BusTimeBuilder();
		btb.setKey("d6ae757b-e881-497f-a066-93a482464b43");
		BusTimeVehicleMonitoring test = btb.createNewBusTimeVehicleMonitoring();
		JsonElement json = test.callApi();
		System.out.println(json.getAsJsonObject().get("Siri"));
	}
}
