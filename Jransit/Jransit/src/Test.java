import java.io.IOException;
import java.util.Map;

import api.MTAApi;
import api.MTAApiStaticFactory;
import api.TrainFeed;
import info.Direction;
import info.LineInfo;
import info.LineInfoStaticFactory;
import info.Location;
import info.StationInfo;
import info.TrainInfo;

public class Test {
	public static void main(String[] args) throws IOException, InterruptedException {
	    // For Alex: http://web.mta.info/developers/data/nyct/subway/Stations.csv
	    System.out.println("Jransit: Backend");
	    // MTAApi and LineInfo Factory Testing
	    // Change: Pass in List of TrainFeeds
	    // Change: Add Thread.sleep(200) to prevent header errors
		Map<TrainFeed, MTAApi> apis = MTAApiStaticFactory.getApis();
		Map<String, LineInfo> mapOfLineInfos = LineInfoStaticFactory.getLines();
		System.out.println(mapOfLineInfos); // Need to add toStrings and hashCodes maybe
		
		System.out.println(apis.get(TrainFeed.BLUE).getTripIds());
		// Change: Add getTripIds for a certain line
		
		// Location Testing
		// Location location = new Location(35.0, 35.0);
		// Location location2 = new Location(33.0, 33.0);
		// System.out.println(location.angleTo(location2));
		// System.out.println(Direction.NORTH);
		
        // apis.get(TrainFeed.GREEN).printEverything();
        // Change: Add textfile exporting ^
        
        // System.out.println(apis.get(TrainFeed.GREEN).getVehiclePosition("117121_G..N"));
		System.out.println(apis.get(TrainFeed.BLUE).getStopTimes("129000_E..N"));
        TrainInfo test = new TrainInfo(apis.get(TrainFeed.BLUE), "129000_E..N");
        
        //LineInfo gTrain = mapOfLineInfos.get("G");
        // System.out.println(gTrain.getStationInfos());
        /*
        for (StationInfo test2 : gTrain.getStationInfos()) {
            System.out.println(test2.getName());
        }
        */
        // System.out.println(apis.get(TrainFeed.GREEN).getStopTimes("116046_G..S"));
        System.out.println(test.getCurrentStation());
	}
}
