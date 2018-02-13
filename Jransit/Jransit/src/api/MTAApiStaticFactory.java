package api;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MTAApiStaticFactory {
	private static final Map<TrainFeed, MTAApi> apis = new HashMap<>();
	private static final String key = "e7ed4dd1445f127eb503c38630a5d3e0";
	// 7367a96d9cddc41240437aad6f9f483b
	// e7ed4dd1445f127eb503c38630a5d3e0
	
	static {
        try {
            apis.put(TrainFeed.NUM_S, new MTAApi(key, TrainFeed.NUM_S));
            Thread.sleep(200);
            apis.put(TrainFeed.BLUE, new MTAApi(key, TrainFeed.BLUE));
            Thread.sleep(200);
            apis.put(TrainFeed.YELLOW, new MTAApi(key, TrainFeed.YELLOW));
            Thread.sleep(200);
            apis.put(TrainFeed.ORANGE, new MTAApi(key, TrainFeed.ORANGE));
            Thread.sleep(200);
            apis.put(TrainFeed.GRAY, new MTAApi(key, TrainFeed.GRAY));
            Thread.sleep(200);
            apis.put(TrainFeed.GREEN, new MTAApi(key, TrainFeed.GREEN));
            Thread.sleep(200);
            apis.put(TrainFeed.BROWN, new MTAApi(key, TrainFeed.BROWN));
            Thread.sleep(200);
            apis.put(TrainFeed.SIR, new MTAApi(key, TrainFeed.SIR));
            Thread.sleep(200);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
	
	private MTAApiStaticFactory() {
        
    }
	
	public static Map<TrainFeed, MTAApi> getApis() {
		update();
		return apis;
	}
	
	public static Map<TrainFeed, MTAApi> getApis(final List<TrainFeed> trainFeeds) {
	    //final Map<TrainFeed, MTAApi> apis = new HashMap<>();
	    for (final TrainFeed tf : trainFeeds) {
	        
	    }
        return apis;
    }
	
	public static MTAApi getApi(final TrainFeed tf) throws IOException {
		return new MTAApi(key, tf);
	}
	
	public static void update() {		
		try {
			apis.get(TrainFeed.NUM_S).updateFeed();
			Thread.sleep(200);
			apis.get(TrainFeed.BLUE).updateFeed();
			Thread.sleep(200);
			apis.get(TrainFeed.YELLOW).updateFeed();
			Thread.sleep(200);
			apis.get(TrainFeed.ORANGE).updateFeed();
			Thread.sleep(200);
			apis.get(TrainFeed.GRAY).updateFeed();
			Thread.sleep(200);
			apis.get(TrainFeed.GREEN).updateFeed();
			Thread.sleep(200);
			apis.get(TrainFeed.BROWN).updateFeed();
			Thread.sleep(200);
			apis.get(TrainFeed.SIR).updateFeed();
			Thread.sleep(200);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
