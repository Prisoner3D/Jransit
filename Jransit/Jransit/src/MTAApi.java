import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.ExtensionRegistry;
import com.google.transit.realtime.GtfsRealtime;
import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.google.transit.realtime.GtfsRealtime.Position;
import com.google.transit.realtime.GtfsRealtime.VehiclePosition;
import com.google.transit.realtime.GtfsRealtimeNYCT;

public class MTAApi {
	private final String API_KEY;
	private final TrainFeed feedID;
	private final FeedMessage feed;

	public MTAApi(String key, TrainFeed feedID) throws IOException {
		this.API_KEY = key;
		this.feedID = feedID;
		this.feed = getFeed(feedID);
	}

	// id is taken from trainfeed
	private FeedMessage getFeed(TrainFeed id) throws IOException {
		ExtensionRegistry registry = ExtensionRegistry.newInstance();
		registry.add(GtfsRealtimeNYCT.nyctFeedHeader);
		registry.add(GtfsRealtimeNYCT.nyctStopTimeUpdate);
		registry.add(GtfsRealtimeNYCT.nyctTripDescriptor);
		URL url = new URL("http://datamine.mta.info/mta_esi.php?key=" + API_KEY + "&feed_id=" + id);
		GtfsRealtime.FeedMessage feed = GtfsRealtime.FeedMessage.parseFrom(url.openStream(), registry);
		return feed;
	}

	public List<VehiclePosition> getTrains() {
		List<VehiclePosition> trains = new ArrayList<>();
		for (FeedEntity entity : this.feed.getEntityList()) {
			if (!entity.hasVehicle()) {
				continue;
			}
			trains.add(entity.getVehicle());
			//entity.getVehicle().getTrip();
			//debug
			//System.out.println(entity.getVehicle());
		}
		return trains;
	}
	
	
	// ignore, not going to be used
	// idk make a new station obj, and corrolate with stops.txt its broken btw
	public void getStations() {
		//List<Station> stations = new ArrayList<>();
		for (FeedEntity ent : this.feed.getEntityList()) {
			if (!ent.hasVehicle()) {
				continue;
			}
			System.out.println(ent.toString());
			System.out.println(ent.getTripUpdate().getTrip().getRouteId());
			//break;
		}
		
	}
		
}