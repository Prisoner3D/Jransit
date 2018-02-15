package bus_api;

import java.io.IOException;

/**
 * @author kevin
 * @date 8/8/14
 */
public class BusTimeStopMonitoring extends BusTime {
	/**
	 * Initialize a new BusTime developer token Do not call this method directly.
	 * Use BusTimeBuilder
	 * 
	 * @param key
	 *            Your developer key. Get a developer key here:
	 *            http://bit.ly/1oqwa54
	 */
	protected BusTimeStopMonitoring(String key) throws IOException {
		super(key, BusTimeConstants.BASE_STOP_MONITORING_URL.getValue());
	}

	public BusTimeStopMonitoring setMonitoringRef(String monitoringRef) {
		this.parameters.put("MonitoringRef", monitoringRef);
		return this;
	}

	public BusTimeStopMonitoring setStopMonitoringDetailLevel(String stopMonitoringDetailLevel) {
		this.parameters.put("StopMonitoringDetailLevel", stopMonitoringDetailLevel);
		return this;
	}
}
