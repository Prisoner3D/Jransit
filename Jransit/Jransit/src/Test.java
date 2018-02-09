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
	    // MTAApi and LineInfo Factory Testing
		Map<TrainFeed, MTAApi> apis = MTAApiStaticFactory.getApis();
		Map<String, LineInfo> mapOfLineInfos = LineInfoStaticFactory.getLines();
		System.out.println(mapOfLineInfos);
		/*
		// Location Testing
		Location location = new Location(35.0, 35.0);
		Location location2 = new Location(33.0, 33.0);
		System.out.println(location.angleTo(location2));
		
		System.out.println(Direction.NORTH);
		MTAApi forever = new MTAApi("e7ed4dd1445f127eb503c38630a5d3e0", TrainFeed.GREEN);
		System.out.println(forever.getTripIds());
		
        // For Alex: http://web.mta.info/developers/data/nyct/subway/Stations.csv
		
        apis.get(TrainFeed.GREEN).printEverything();
        // Might add textfile exporting ^
        
        System.out.println(apis.get(TrainFeed.GREEN).getVehiclePosition("081100_E..S"));
        TrainInfo test = new TrainInfo(apis.get(TrainFeed.GREEN), "067650_G..S");
        
        LineInfo gTrain = mapOfLineInfos.get("G");
        System.out.println(gTrain.getStationInfos());
        for (StationInfo test2 : gTrain.getStationInfos()) {
            System.out.println(test2.getName());
        }
        System.out.println(apis.get(TrainFeed.GREEN).getStopTimes("067650_G..S"));
        System.out.println(test.getCurrentStation());*/
	}
}
