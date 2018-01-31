import java.io.IOException;

// use arrival times to predict location pg.9
public class Test {
	public static void main(String[] args) throws IOException {
		String key = "e7ed4dd1445f127eb503c38630a5d3e0";
		MTAApi mta = new MTAApi(key, TrainFeed.NUM_S);
		mta.getTrains();
		//mta.getStations(); getStations will be removed, stations will be created upon initialization
	}
}
