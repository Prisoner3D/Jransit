import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MTAApiStaticFactory {
	private static Map<TrainFeed, MTAApiPointer> apis = new HashMap<>();
	static {
		apis.put(TrainFeed.NUM_S, new MTAApiPointer());
		apis.put(TrainFeed.BLUE,new MTAApiPointer());
		apis.put(TrainFeed.YELLOW, new MTAApiPointer());
		apis.put(TrainFeed.ORANGE, new MTAApiPointer());
		apis.put(TrainFeed.GRAY, new MTAApiPointer());
		apis.put(TrainFeed.GREEN, new MTAApiPointer());
		apis.put(TrainFeed.BROWN, new MTAApiPointer());
		apis.put(TrainFeed.SIR, new MTAApiPointer());
	}
	public static Map<TrainFeed, MTAApiPointer> getApis() {
		update();
		return apis;
	}
	private static final String key = "e7ed4dd1445f127eb503c38630a5d3e0";
	public static void update() {		
		try {
			apis.get(TrainFeed.NUM_S).reference(new MTAApi(key, TrainFeed.NUM_S));
			apis.get(TrainFeed.BLUE).reference(new MTAApi(key, TrainFeed.BLUE));
			apis.get(TrainFeed.YELLOW).reference(new MTAApi(key, TrainFeed.YELLOW));
			apis.get(TrainFeed.ORANGE).reference(new MTAApi(key, TrainFeed.ORANGE));
			apis.get(TrainFeed.GRAY).reference(new MTAApi(key, TrainFeed.GRAY));
			apis.get(TrainFeed.GREEN).reference(new MTAApi(key, TrainFeed.GREEN));
			apis.get(TrainFeed.BROWN).reference(new MTAApi(key, TrainFeed.BROWN));
			apis.get(TrainFeed.SIR).reference(new MTAApi(key, TrainFeed.SIR));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private MTAApiStaticFactory() {
		
	}

}
