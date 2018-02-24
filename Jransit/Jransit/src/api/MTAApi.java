package api;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.protobuf.ExtensionRegistry;
import com.google.transit.realtime.GtfsRealtime;
import com.google.transit.realtime.GtfsRealtime.FeedEntity;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.google.transit.realtime.GtfsRealtime.Position;
import com.google.transit.realtime.GtfsRealtime.TripUpdate.StopTimeUpdate;

import info.TrainInfo;

import com.google.transit.realtime.GtfsRealtime.VehiclePosition;
import com.google.transit.realtime.GtfsRealtimeNYCT;

/**
 *  UNUSED FOR NOW
 */
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
    private FeedMessage feed;

    public MTAApi(final String key, final TrainFeed feedID) throws IOException {
        this.API_KEY = key;
        this.feedID = feedID;
        this.feed = getFeed(feedID);
    }

    public void updateFeed() throws IOException {
        this.feed = getFeed(feedID);
    }

    private FeedMessage getFeed(final TrainFeed id) throws IOException {
        ExtensionRegistry registry = ExtensionRegistry.newInstance();
        registry.add(GtfsRealtimeNYCT.nyctFeedHeader);
        registry.add(GtfsRealtimeNYCT.nyctStopTimeUpdate);
        registry.add(GtfsRealtimeNYCT.nyctTripDescriptor);
        URL url = new URL("http://datamine.mta.info/mta_esi.php?key=" + API_KEY + "&feed_id=" + id); // ID = NUMBERS ONLY
        GtfsRealtime.FeedMessage feed = GtfsRealtime.FeedMessage.parseFrom(url.openStream(), registry);
        return feed;
    }

    /**
     * getTrains(): Filters out VehiclePosition objects in a line, which represent trains, and puts them into a list.
     * 
     * @return List<VehiclePosition> : The list of filtered "trains"
     */
    public List<TrainInfo> getTrains() {
        final List<String> tripIds = getTripIds();
        final List<TrainInfo> trains = new ArrayList<>();
        for (final String tripId : tripIds) {
            try {
                trains.add(new TrainInfo(this, tripId));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return trains;
    }

    /**
     * getTripIds(): Makes a list with all available trip_ids in the line
     * 
     * @return List<String> : List of all available trip_ids
     */
    public List<String> getTripIds() {
        final List<String> ids = new ArrayList<String>();
        for (final FeedEntity entity : this.feed.getEntityList()) {
            if (!entity.hasVehicle()) {
                continue;
            }
            ids.add(entity.getVehicle().getTrip().getTripId());
        }
        return ids;
    }

    /**
     * getTripIdAndTrain(): Makes a map assigning the trip_id and the VehiclePosition in the current line
     * 
     * @return Map<String, VehiclePosition> : Map of trip_id assigned to VehiclePositon
     */
    // Change from VehiclePosition to TrainInfo?
    public Map<String, VehiclePosition> getTripIdAndTrain() {
        final Map<String, VehiclePosition> ids = new HashMap<String, VehiclePosition>();
        for (final FeedEntity entity : this.feed.getEntityList()) {
            if (!entity.hasVehicle()) {
                continue;
            }
            ids.put(entity.getVehicle().getTrip().getTripId(), entity.getVehicle());
        }
        return ids;
    }

    /**
     * getVehiclePosition(): Searches for a VehiclePosition object with the passed trip_id in the line
     * 
     * @param trip_id : The term used for search
     * @return VehiclePosition : The object if found, else null
     */
    // Add StopTrips?
    public VehiclePosition getVehiclePosition(final String trip_id) {
        // Uses trip_id to get train's VehiclePosition
        for (final FeedEntity entity : this.feed.getEntityList()) {
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
     * getArrivalTime(): Gets the arrival time of a specified train at at specified station within the line
     * 
     * @param trip_id : The train
     * @param stop_id : The station
     * @return long : The expected arrival time in Unix Time
     */
    public long getArrivalTime(final String trip_id, final String stop_id) {
        final List<StopTimeUpdate> stopTimes = getStopTimes(trip_id);
        if (stopTimes != null) {
            for (final StopTimeUpdate stu : stopTimes) {
                if (stu.getStopId().equals(stop_id)) {
                    return stu.getArrival().getTime();
                }
            }
        }
        return -1;
    }

    /**
     * getStopTimes(): Gets all stop times for a specified train within the line
     * 
     * @param trip_id : The train
     * @return List<StopTimeUpdate> : All current and future stops
     */
    public List<StopTimeUpdate> getStopTimes(final String trip_id) {
        for (final FeedEntity ent : this.feed.getEntityList()) {
            if (ent.hasVehicle()) {
                continue;
            }
            // System.out.println(ent);
            if (ent.getTripUpdate().getTrip().getTripId().equals(trip_id)) {

                return ent.getTripUpdate().getStopTimeUpdateList();
            }
        }
        return null;
    }

    /**
     * printEverything() : Prints everything in the feed for the line
     */
    public void printEverything() {
        // List<Station> stations = new ArrayList<>();
        for (final FeedEntity ent : this.feed.getEntityList()) {
            if (!ent.hasVehicle()) {
                // continue;
            }
            System.out.println(ent.toString());
            System.out.println(ent.getTripUpdate().getTrip().getRouteId());
            // break;
        }
    }

    public void getStationInfo() {

    }

    public void getLines() {

    }

    public void getLineInfo() {

    }
}