package util;

import java.util.List;

import csv.Stop;
import csv.StopDb;
import info.Location;

/**
 * Utilities to manage train displays on the map.
 * @author 
 *
 */

public class TrainMapsUtil {
	
	/**
	 * Calculates position of train between two points
	 * @param lat latitude of location
	 * @param lon longitude of location
	 * @param time current time 
	 * @param lat2 latitude of destination
	 * @param lon2 longitude of destination
	 * @param vel velocity of train
	 * @return the train's position relative to a destination and location
	 */
	// THE TIME IN VELOCITY AND TIME FIELD MUST BE MATCHING UNITS
	public static Double[] getTrainPosition(Double lat, Double lon, Double time, Double lat2, Double lon2, Double vel) {
	//	Double distanceX = lat * longt * Math.sqrt(Time); 
		Double distanceX = lat - lat2;
		Double distanceY = lon - lon2;
		Double distanceTotal = Math.sqrt(Math.pow(distanceX, 2)+ Math.pow(distanceY,2));
		Double trainsDistance = (1 * vel) / 111139;
		Double proportion = trainsDistance / distanceTotal;
		Double longitudeX = lat2 + (distanceX * proportion);
		Double latitudeY = lon2 + (distanceY * proportion);
		/*if(lat2 < 0) {
			if(latitudeY < lat2)
				latitudeY = lat2;
		}
		else if(lon2 < latitudeY) {
			latitudeY = lat2;
		}
		
		if(lon2 < 0) {
			if(longitudeX < lon2)
				longitude*X = lon2;
		}
		else if(lon2 < longitudeX) {
			longitudeX = lon2;
		}*/
		return new Double[]{longitudeX,latitudeY};
		
		
	}
	
	/**
	 * Calculates position of train between two points
	 * @param lat latitude of location
	 * @param lon longitude of location
	 * @param time current time 
	 * @param lat2 latitude of destination
	 * @param lon2 longitude of destination
	 * @param vel velocity of train
	 * @return the train's position relative to a destination and location
	 */
	
	// TODO: Confirm whether or not the order of current vs next is right
	// vel in mph
	public static Location getTrainPosition(Location currentStop, Location nextStop, long timeC, long timeN, double vel) {
		//	Double distanceX = lat * longt * Math.sqrt(Time); 
		Double distanceX = currentStop.getLongitude() - nextStop.getLongitude();
		Double distanceY = currentStop.getLatitude() - nextStop.getLatitude();
		Double distanceTotal = Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));
		
		Double trainDistance = ((timeC - timeN) * vel) / 111139;
		Double proportion = trainDistance / distanceTotal;
		Double longitudeX = nextStop.getLongitude() + (distanceX * proportion);
		Double latitudeY = nextStop.getLatitude() + (distanceY * proportion);
			/*if(lat2 < 0) {
				if(latitudeY < lat2)
					latitudeY = lat2;
			}
			else if(lon2 < latitudeY) {
				latitudeY = lat2;
			}
			
			if(lon2 < 0) {
				if(longitudeX < lon2)
					longitude*X = lon2;
			}
			else if(lon2 < longitudeX) {
				longitudeX = lon2;
			}*/
			return new Location (latitudeY,longitudeX);
		}
	// JOSH FIX
//	public static Double[] getTrainPosition(Double lat, Double lon, Double time, Double lat2, Double lon2, Double vel) {
//	    //  Double distanceX = lat * longt * Math.sqrt(Time); 
//	        Double distanceX = lat2 - lat;
//	        Double distanceY = lon2 - lon;
//	        Double distanceTotal = Math.sqrt(Math.pow(distanceX, 2)+ Math.pow(distanceY,2));
//	        Double trainsDistance = (time * vel) / 111139;
//	        Double proportion = trainsDistance / distanceTotal;
//	        Double longitudeX = lat + distanceX * proportion;
//	        Double latitudeY = lon + distanceY * proportion;
//	        /*if(lat2 < 0) {
//	            if(latitudeY < lat2)
//	                latitudeY = lat2;
//	        }
//	        else if(lon2 < latitudeY) {
//	            latitudeY = lat2;
//	        }
//	        
//	        if(lon2 < 0) {
//	            if(longitudeX < lon2)
//	                longitude*X = lon2;
//	        }
//	        else if(lon2 < longitudeX) {
//	            longitudeX = lon2;
//	        }*/
//	        return new Double[]{longitudeX,latitudeY};
//	        
//	        
//	    }
}
