package util;

public class TrainMapsUtil {
	
	public static void main(String[] args) {
		
		Double[]potato = getTrainPosition(100.0,100.0,3600.0,0.0,0.0,500.0);
		System.out.println(potato[0] + "  " + potato[1]);
	}
	
	public static Double[] getTrainPosition(Double lat, Double lon, Double time, Double lat2, Double lon2, Double vel) {
	//	Double distanceX = lat * longt * Math.sqrt(Time); 
		Double distanceX = lat2 - lat;
		Double distanceY = lon2 - lon;
		Double distanceTotal = Math.sqrt(Math.pow(distanceX, 2)+ Math.pow(distanceY,2));
		Double trainsDistance = (time * vel) / 111139;
		Double proportion = trainsDistance / distanceTotal;
		Double longitudeX = lat + distanceX * proportion;
		Double latitudeY = lon + distanceY * proportion;
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
}
