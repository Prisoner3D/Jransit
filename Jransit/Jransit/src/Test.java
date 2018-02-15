import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.google.transit.realtime.GtfsRealtime.VehiclePosition;

import api.MTAApi;
import api.MTAApiStaticFactory;
import api.TrainFeed;
import ch.qos.logback.core.net.SyslogOutputStream;
import info.Direction;
import info.LineInfo;
import info.LineInfoStaticFactory;
import info.Location;
import info.StationInfo;
import info.TrainInfo;

/**
 * Test MTA API 
 * @author 
 *
 */
public class Test {
	public static void main(String[] args) throws IOException, InterruptedException {
	    // For Alex: http://web.mta.info/developers/data/nyct/subway/Stations.csv
	    
		// DIRECTION: AIzaSyD0pqUj9v0WYoBJ5dORWFyHcaMHWQ4_iAM
	    // NOTE: LOCATION IS LAT FIRST
	    
	    // TODO
	    // Add in dynamic factory creation based on List of TrainFeeds
	    // Add toString, hashCodes and equals
	    // Add getTripIds for only a certain line in feed
	    // Add textfile for printEverything()
	    // Add backup api
	    System.out.println("Jransit: Master");
	    
	    // MTAApi and LineInfo Factory Testing
		Map<TrainFeed, MTAApi> apis = MTAApiStaticFactory.getApis();
		Map<String, LineInfo> mapOfLineInfos = LineInfoStaticFactory.getLines();
		
		// Location Testing
		Location location = new Location(35.0, 35.0);
		Location location2 = new Location(33.0, 33.0);
		System.out.println(location.angleTo(location2));
		//System.out.println(Direction.NORTH);
		
        apis.get(TrainFeed.BLUE).printEverything();
        for (VehiclePosition vp : apis.get(TrainFeed.BLUE).getTripIdAndTrain().values()) {
        	System.out.println(vp.getCurrentStatus());
        }
		//System.out.println(apis.get(TrainFeed.BLUE).getTripIds());
		
        List<TrainInfo> allBlueTrains = apis.get(TrainFeed.BLUE).getTrains();
        for (TrainInfo train : allBlueTrains) {
        	if (!train.getId().equals("069528_A..S")) {
        		continue;
        	}
        	System.out.println(train.getId());
        	
        	System.out.println(train.getStopTimes().size());
            System.out.println(train.getArrivalTime(train.getCurrentStation().getStopID()));
            break;
        }
		
        //System.out.println(apis.get(TrainFeed.GREEN).getVehiclePosition("117121_G..N"));
		//System.out.println(apis.get(TrainFeed.BLUE).getStopTimes("133050_E..N"));
        //TrainInfo test = new TrainInfo(apis.get(TrainFeed.BLUE), "133050_E..N");
        
        //LineInfo gTrain = mapOfLineInfos.get("G");
        //System.out.println(gTrain.getStationInfos());
        /*
        for (StationInfo test2 : gTrain.getStationInfos()) {
            System.out.println(test2.getName());
        }
        */
        //System.out.println(apis.get(TrainFeed.GREEN).getStopTimes("116046_G..S"));
        //System.out.println(test.getCurrentStation());
	}
}
