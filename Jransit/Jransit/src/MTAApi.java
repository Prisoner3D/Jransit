import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.protobuf.ExtensionRegistry;
import com.google.transit.realtime.GtfsRealtime;
import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.google.transit.realtime.GtfsRealtime.Position;
import com.google.transit.realtime.GtfsRealtime.TripUpdate.StopTimeUpdate;
import com.google.transit.realtime.GtfsRealtime.VehiclePosition;
import com.google.transit.realtime.GtfsRealtimeNYCT;

public class MTAApi {
    /*
        Useful info: 
        Each stop_time_update is a future stop time, past stop times are omitted. The first StopTime in the sequence is the stop the train
        is currently approaching, stopped at or about to leave. A stop is dropped from the sequence when the train departs the station.
    
        Arrival and Departure time are the same? Possibly an unused feature?
        
        trip_id - Lookup in trips.txt for partial match
        
        A VehiclePosition is in its own FeedMessage. The previous FeedMessage will contain the StopTimes for that VehiclePosition (Train)
        
        Unable to figure out the current position of the train while it is between stations - Prediction based off previous trip_updates?
        
        current_stop_sequence - Possibly the stop # of of the line?
        
        ** Note that the predicted times are not updated when the train is not moving. Feed consumers can detect this condition using the 
        timestamp in the VehiclePosition message. **
    */
	private final String API_KEY;
	private final TrainFeed feedID;
	private final FeedMessage feed;

	public MTAApi(String key, TrainFeed feedID) throws IOException {
		this.API_KEY = key;
		this.feedID = feedID;
		this.feed = getFeed(feedID);
	}
	
	private FeedMessage getFeed(TrainFeed id) throws IOException {
		ExtensionRegistry registry = ExtensionRegistry.newInstance();
		registry.add(GtfsRealtimeNYCT.nyctFeedHeader);
		registry.add(GtfsRealtimeNYCT.nyctStopTimeUpdate);
		registry.add(GtfsRealtimeNYCT.nyctTripDescriptor);
		URL url = new URL("http://datamine.mta.info/mta_esi.php?key=" + API_KEY + "&feed_id=" + id); // id = what group of trains
		GtfsRealtime.FeedMessage feed = GtfsRealtime.FeedMessage.parseFrom(url.openStream(), registry);
		return feed;
	}

	/**
	 * getTrains(): Filters out VehiclePosition objects, which represent trains and puts them into a list.
	 * 
	 * @return List<VehiclePosition> : The list of filtered "trains"
	 */
	// ? Change VehiclePosition to TrainInfo ?
	public List<VehiclePosition> getTrains() {
		List<VehiclePosition> trains = new ArrayList<>();
		for (FeedEntity entity : this.feed.getEntityList()) {
			if (!entity.hasVehicle()) {
				continue;
			}
			trains.add(entity.getVehicle());
			// System.out.println(entity.getVehicle());
		}
		return trains;
	}
	
	/**
	 * grabVehiclePosition(): Searches for a VehiclePosition object with the passed trip_id.
	 * 
	 * @param trip_id : The term used for search
	 * @return VehiclePosition : The object if found, else null
	 */
	// ? Add StopTrips ?
    public VehiclePosition grabVehiclePosition(String trip_id) {
        // Uses trip_id to get train's VehiclePosition
        for (FeedEntity entity : this.feed.getEntityList()) {
            if (!entity.hasVehicle()) {
                continue;
            }
            if (entity.getVehicle().getTrip().getTripId().equals(trip_id)) {
                return entity.getVehicle();
            }
        }
        return null; // Should it return null?
    }
    
    /**
     * getArrivalTime(): Gets the arrival time of a specified train at at specified station
     * 
     * @param trip_id : The train
     * @param stop_id : The station
     * @return long : The expected arrival time in Unix Time
     */
    public long getArrivalTime(String trip_id, String stop_id) {
    	List<StopTimeUpdate> stopTimes;
    	if ((stopTimes = getStopTimes(trip_id)) != null) {
    		for (StopTimeUpdate stu : stopTimes) {
        		if (stu.getStopId().equals(stop_id)) {
        			return stu.getArrival().getTime();
        		}
        	}
    	}
    	return -1;
    }
    
    /**
     * getStopTimes(): Gets all stop times for a specified train
     * 
     * @param trip_id : The train
     * @return List<StopTimeUpdate> : All current and future stops
     */
    public List<StopTimeUpdate> getStopTimes(String trip_id) {
    	for (FeedEntity ent : this.feed.getEntityList()) {
			if (ent.hasVehicle()) {
				continue;
			}
			//System.out.println(ent);
			if (ent.getTripUpdate().getTrip().getTripId().equals(trip_id)) {
				
				return ent.getTripUpdate().getStopTimeUpdateList();
			}
		}
		return null;
    }
    
	/**
	 * printEverything() : Prints everything in the feed.
	 */
	public void printEverything() {
		// List<Station> stations = new ArrayList<>();
		for (FeedEntity ent : this.feed.getEntityList()) {
			if (!ent.hasVehicle()) {
				// continue;
			}
			System.out.println(ent.toString());
			System.out.println(ent.getTripUpdate().getTrip().getRouteId());
			// break;
		}	
	}
	
	public void grabStationInfo() {
		
    }
	
	public void getLines() {
	    
	}
	
	public void grabLineInfo() {
		
	}
}