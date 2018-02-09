package util;

import java.util.List;

import csv.Stop;
import csv.StopDb;
import info.Location;

public class TrainMapsUtil {
	
	public static void main(String[] args) {
		
		Double[]potato = getTrainPosition(100.0,100.0,1.0,0.0,0.0,500.0);
		System.out.println(potato[0] + "  " + potato[1]);
		StopDb stopdb = new StopDb();
		double maxlon = 0;
		double minlon = 0;
		double minlat = 0;
		double maxlat = 0;
		
		List<Stop> stop = stopdb.getAllStops();
		for(Stop x:stop) {
			if((Double.parseDouble(x.getLatitude()) > maxlat)) {
				maxlat = Double.parseDouble(x.getLatitude());						//attempting to find the max long lat/ min long lat
			}
			if((Double.parseDouble(x.getLatitude()) < minlat)) {
				minlat = Double.parseDouble(x.getLatitude());
			}
			if((Double.parseDouble(x.getLongitude()) > maxlon)) {
				maxlon = Double.parseDouble(x.getLongitude());
			}
			if((Double.parseDouble(x.getLongitude()) < minlat)) {
				minlat = Double.parseDouble(x.getLongitude());
			}
		}
		
		System.out.println("The largest longitude is " + maxlon);
		System.out.println("The smallest longitude is " + minlon);
		System.out.println("The largest latitude is " + maxlat);
		System.out.println("The smallest latitude is " + minlat);
	}
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
	
	// TODO: Confirm whether or not the order of current vs next is right
	public static Location getTrainPosition(Location currentStop, Location nextStop, long time, double vel) {
		//	Double distanceX = lat * longt * Math.sqrt(Time); 
			Double distanceX = currentStop.getLongitude()- nextStop.getLongitude();
			Double distanceY = currentStop.getLatitude() - nextStop.getLatitude();
			Double distanceTotal = Math.sqrt(Math.pow(distanceX, 2)+ Math.pow(distanceY,2));
			Double trainsDistance = (1 * vel) / 111139;
			Double proportion = trainsDistance / distanceTotal;
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
			return new Location (longitudeX,latitudeY);
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
