package bus_api;

import gumi.builders.UrlBuilder;

import java.io.IOException;
import java.net.URL;

/**
 * A builder that lets you set your key once, and then can use that key to
 * create new StopMonitoring or VehicleMonitoring objects in the future.
 * 
 * @author kevin
 * @date 8/8/14
 */
public class BusTimeBuilder {

	private String key;

	public BusTimeStopMonitoring createNewBusTimeStopMonitoring() throws IOException {
		return new BusTimeStopMonitoring(key);
	}

	public BusTimeVehicleMonitoring createNewBusTimeVehicleMonitoring() throws IOException {
		return new BusTimeVehicleMonitoring(key);
	}

	public BusTimeBuilder setKey(String key) throws IOException {
		this.key = key;
		if (!testBusTimeTokenValidity()) {
			System.err.println("ERROR: The specified developer key is invalid!");
		}
		return this;
	}

	/**
	 * Test if the current developer token is valid
	 * 
	 * @return true if token is valid and rate is not exceeded, false if token is
	 *         invalid or rate is exceeded
	 * @throws IOException
	 *             Thrown if the test API call cannot be successfully completed
	 */
	public boolean testBusTimeTokenValidity() throws IOException {
		// Make a call to a random bus stop to make sure this token is valid
		UrlBuilder builder = UrlBuilder.fromString(BusTimeConstants.BASE_STOP_MONITORING_URL.getValue())
				.addParameter(BusTimeConstants.PARAMETER_MONITORING_REF.getValue(), "308214");
		URL url = builder.toUrl();

		// Gets the JSON for this random bus stop. If the response code is forbidden,
		// then this token is not valid
		try {
			BusTime.getJsonFromUrl(url);
		} catch (IOException e) {
			if (e.getMessage().contains("HTTP response code: 403")) {
				return false;
			}
		}
		return true;
	}

}
