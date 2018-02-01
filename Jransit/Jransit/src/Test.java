import java.io.IOException;

// use arrival times to predict location pg.9
public class Test {
	public static void main(String[] args) throws IOException {
		String key = "e7ed4dd1445f127eb503c38630a5d3e0";
		MTAApi mta = new MTAApi(key, TrainFeed.BLUE);
		//mta.getTrains();
		System.out.println(mta.grabTrainInfo("134900_E..N"));
	}
}
