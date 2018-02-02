import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		String key = "e7ed4dd1445f127eb503c38630a5d3e0";
		MTAApi mta = new MTAApi(key, TrainFeed.BLUE);
		//System.out.println(mta.getTrains());
		
		//mta.getStations();
		//System.out.println(mta.getStopTimes("081100_E..S"));
		System.out.println(mta.getArrivalTime("081100_E..S", "A34S"));
		//System.out.println(mta.grabTrainInfo("081100_E..S"));
		LineInfo ETRAIN = new LineInfo("E");
		TrainInfo test = new TrainInfo("081100_E..S", ETRAIN);
		System.out.println(test.getCurrentStation());
		System.out.println(test.getNextStation());
	}
}
