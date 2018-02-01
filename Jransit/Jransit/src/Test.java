import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		String key = "e7ed4dd1445f127eb503c38630a5d3e0";
		MTAApi mta = new MTAApi(key, TrainFeed.BLUE);
		//System.out.println(mta.getTrains());
		System.out.println(mta.grabTrainInfo("074450_E..S"));
	}
}
