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
 * 
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
		

		// MTAApi and LineInfo Factory Testing
		Map<TrainFeed, MTAApi> apis = MTAApiStaticFactory.getApis();
		Map<String, LineInfo> mapOfLineInfos = LineInfoStaticFactory.getLines();

		// Location Testing
		Location location = new Location(35.0, 35.0);
		Location location2 = new Location(33.0, 33.0);
		
		// 

		apis.get(TrainFeed.BLUE).printEverything();
		for (VehiclePosition vp : apis.get(TrainFeed.BLUE).getTripIdAndTrain().values()) {
			
		}
		// 

		List<TrainInfo> allBlueTrains = apis.get(TrainFeed.BLUE).getTrains();
		for (TrainInfo train : allBlueTrains) {
			if (!train.getId().equals("069528_A..S")) {
				continue;
			}
			

			
			
			break;
		}

		// 
		// 
		// TrainInfo test = new TrainInfo(apis.get(TrainFeed.BLUE), "133050_E..N");

		// LineInfo gTrain = mapOfLineInfos.get("G");
		// 
		/*
		 * for (StationInfo test2 : gTrain.getStationInfos()) {
		 *  }
		 */
		// 
		// 
	}
}
