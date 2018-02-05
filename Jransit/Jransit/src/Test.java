import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		String key = "e7ed4dd1445f127eb503c38630a5d3e0";
		MTAApi mta = new MTAApi(key, TrainFeed.GREEN);
		//System.out.println(mta.getTrains());
		
		// For Alex: http://web.mta.info/developers/data/nyct/subway/Stations.csv
		
		//mta.printEverything();
		
		//System.out.println(mta.grabTrainInfo("081100_E..S"));
		LineInfo gTrain = new LineInfo(mta, "G");
		//LineInfo lTrain = new LineInfo(mta, "L");
		//LineInfo ETRAIN = new LineInfo(mta, "G");
		TrainInfo test = new TrainInfo(mta, "067650_G..S");
		//System.out.println(gTrain.getStations());
		for (StationInfo test2 : gTrain.getStationInfos()) {
			//System.out.println(test2.getName());
		}
		System.out.println(mta.getStopTimes("067650_G..S"));
		System.out.println(test.getCurrentStation());
	}
}
