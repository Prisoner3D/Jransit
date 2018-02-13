package bus_api;

import java.io.IOException;

/**
 * @author kevin
 * @date 8/8/14
 */
public class BusTimeVehicleMonitoring extends BusTime {
    /**
     * Initializes a new BusTime developer token.
     * Do not call this method directly. Use BusTimeBuilder
     * @param key Your developer key. Get a developer key here: http://bit.ly/1oqwa54
     */
    protected BusTimeVehicleMonitoring(String key) throws IOException {
        super(key, BusTimeConstants.BASE_VEHICLE_MONITORING_URL.getValue());
    }

    public BusTimeVehicleMonitoring setVehicleRef(String vehicleRef) {
        this.parameters.put("VehicleRef", vehicleRef);
        return this;
    }
    public BusTimeVehicleMonitoring setVehicleMonitoringDetailLevel(String vehicleMonitoringDetailLevel) {
        this.parameters.put("VehicleMonitoringDetailLevel", vehicleMonitoringDetailLevel);
        return this;
    }
}
