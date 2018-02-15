package info;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class BusInfo {
    private double lat;
    private double lon;
    private String lineName;
    private String destinationName; // LAST STATION
    private String nextStop;
    private JsonObject busJson;

    // expected arrival time
    public BusInfo(JsonObject b) {
        this.busJson = b;
        JsonObject data = b.get("MonitoredVehicleJourney").getAsJsonObject();
        // System.out.println(data);
        destinationName = data.get("DestinationName").toString();
        JsonObject location = data.get("VehicleLocation").getAsJsonObject();
        JsonElement lineName = data.get("PublishedLineName");
        if (data.get("MonitoredCall") != null) {
            this.nextStop = data.get("MonitoredCall").getAsJsonObject().get("StopPointName").toString();
        }
        
        lon = location.get("Longitude").getAsDouble();
        lat = location.get("Latitude").getAsDouble();
        this.lineName = lineName.getAsString();
    }

    public String getNextStop() {
        return nextStop;
    }

    public String getDestinationName() {
        return destinationName;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lon;
    }

    public JsonObject getBusJson() {
        return busJson;
    }

    public String getLineName() {
        return lineName;
    }

}
