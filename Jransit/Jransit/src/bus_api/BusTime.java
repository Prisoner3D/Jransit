package bus_api;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import gumi.builders.UrlBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * A BusTime object is your developer authentication with the MTA BusTime API.
 * This class shouldn't be directly instantiated. You must create either a
 * BusTimeStopMonitoring or BusTimeVehicleMonitoring object. If you would like
 * to create both conveniently without supplying your developer key twice, you
 * can use the BusTimeBuilder class.
 * 
 * @author kevin
 * @date 8/8/14
 */
class BusTime {

	/**
	 * Given a URL object, returns a JSON representation of the response given when
	 * querying that URL
	 * 
	 * @param url
	 *            URL to query for a JSON response
	 * @return JSON object representing that URL query
	 * @throws IOException
	 *             If the HTTP call cannot be completed
	 */
	protected static JsonElement getJsonFromUrl(URL url) throws IOException {
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
		request.connect();

		JsonParser jp = new JsonParser();
		return jp.parse(new InputStreamReader((InputStream) request.getContent()));
	}
	protected final String BASE_URL;

	protected Map<String, String> parameters = new HashMap<String, String>();

	/**
	 * Initialize a new BusTime developer token
	 * 
	 * @param key
	 *            Your developer key. Get a developer key here:
	 *            http://bit.ly/1oqwa54
	 */
	protected BusTime(String key, String baseUrl) throws IOException {
		setKey(key);
		this.BASE_URL = baseUrl;
	}

	/**
	 * Call the BusTime API using the previously-set parameters and return a
	 * JsonElement
	 * 
	 * @return JsonElement returned by the API call
	 * @throws IOException
	 *             if the API call couldn't be made
	 */
	public JsonElement callApi() throws IOException {
		UrlBuilder builder = UrlBuilder.fromString(BASE_URL);
		for (Map.Entry<String, String> parameter : parameters.entrySet()) {
			builder = builder.addParameter(parameter.getKey(), parameter.getValue());
		}
		return getJsonFromUrl(builder.toUrl());
	}

	@SuppressWarnings("unchecked")
	public <T extends BusTime> T setDirectionRef(String directionRef) {
		this.parameters.put("DirectionRef", directionRef);
		return (T) this;
	}

	// Methods to set parameters that both StopMonitoring and VehicleMonitoring
	// share in common
	@SuppressWarnings("unchecked")
	public <T extends BusTime> T setKey(String key) {
		this.parameters.put("key", key);
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public <T extends BusTime> T setLineRef(String lineRef) {
		this.parameters.put("LineRef", lineRef);
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public <T extends BusTime> T setMaximumNumberOfCallsOnwards(String maximumNumberOfCallsOnwards) {
		this.parameters.put("MaximumNumberOfCallsOnwards", maximumNumberOfCallsOnwards);
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public <T extends BusTime> T setMaximumStopVisits(String maximumStopVisits) {
		this.parameters.put("MaximumStopVisits", maximumStopVisits);
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public <T extends BusTime> T setMinimumStopVisitsPerLine(String minimumStopVisitsPerLine) {
		this.parameters.put("MinimumStopVisitsPerLine", minimumStopVisitsPerLine);
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public <T extends BusTime> T setOperatorRef(String operatorRef) {
		this.parameters.put("OperatorRef", operatorRef);
		return (T) this;
	}

}