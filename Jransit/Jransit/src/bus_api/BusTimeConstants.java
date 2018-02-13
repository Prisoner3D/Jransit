package bus_api;

/**
 * @author kevin
 * @date 8/8/14
 */
public enum BusTimeConstants {

    BASE_VEHICLE_MONITORING_URL("http://bustime.mta.info/api/siri/vehicle-monitoring.json"),
    BASE_STOP_MONITORING_URL("http://bustime.mta.info/api/siri/stop-monitoring.json"),
    PARAMETER_MONITORING_REF("MonitoringRef")
    ;

    private String value; // Value
    BusTimeConstants(String value) { this.value = value; } // Constructor for value
    public String getValue() { return value; }
}
