import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		String key = "e7ed4dd1445f127eb503c38630a5d3e0";
		MTAApi mta = new MTAApi(key, TrainFeed.GREEN);
		//System.out.println(mta.getTrains());
		
		//mta.printEverything();
		//System.out.println(mta.getStopTimes("078100_E..S"));
		//System.out.println(mta.grabTrainInfo("081100_E..S"));
		LineInfo ETRAIN = new LineInfo(mta, "G");
		TrainInfo test = new TrainInfo(mta, "077650_G..S", ETRAIN);
		System.out.println(ETRAIN.getStations());
		for (StationInfo test2 : ETRAIN.getStationInfos()) {
			System.out.println(test2.getStationName());
		}
		System.out.println(test.getCurrentStation());
	}
}
