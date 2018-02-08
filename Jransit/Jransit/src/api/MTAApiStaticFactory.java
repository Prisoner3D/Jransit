package api;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MTAApiStaticFactory {
	private static Map<TrainFeed, MTAApi> apis = new HashMap<>();
	
	public static Map<TrainFeed, MTAApi> getApis() {
		update();
		return apis;
	}
	private static final String key = "e7ed4dd1445f127eb503c38630a5d3e0";
	static {
		try {
			apis.put(TrainFeed.GRAY, new MTAApi(key, TrainFeed.GRAY));
			apis.put(TrainFeed.NUM_S, new MTAApi(key, TrainFeed.NUM_S));
			apis.put(TrainFeed.BLUE, new MTAApi(key, TrainFeed.BLUE));
			apis.put(TrainFeed.YELLOW, new MTAApi(key, TrainFeed.YELLOW));
			apis.put(TrainFeed.ORANGE, new MTAApi(key, TrainFeed.ORANGE));
			apis.put(TrainFeed.GREEN, new MTAApi(key, TrainFeed.GREEN));
			apis.put(TrainFeed.BROWN, new MTAApi(key, TrainFeed.BROWN));
			apis.put(TrainFeed.SIR, new MTAApi(key, TrainFeed.SIR));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public static void update() {		
		try {
			apis.get(TrainFeed.NUM_S).updateFeed();
			apis.get(TrainFeed.BLUE).updateFeed();
			apis.get(TrainFeed.YELLOW).updateFeed();
			apis.get(TrainFeed.ORANGE).updateFeed();
			apis.get(TrainFeed.GRAY).updateFeed();
			apis.get(TrainFeed.GREEN).updateFeed();
			apis.get(TrainFeed.BROWN).updateFeed();
			apis.get(TrainFeed.SIR).updateFeed();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private MTAApiStaticFactory() {
		
	}

}
