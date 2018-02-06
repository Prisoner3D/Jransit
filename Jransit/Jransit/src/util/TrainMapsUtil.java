package util;

public class TrainMapsUtil {
	
	public void main(String args) {
		
	}
	
	public int[] getTrainPosition(Double lat, Double longt, Long Time, Double lat2, Double longt2) {
	//	Double distanceX = lat * longt * Math.sqrt(Time); 
		Double distanceX = lat - lat2;
		Double distanceY = longt - longt2;
		Double distanceTotal = distanceX + distanceY;
		Double trainsDistance = distanceTotal / 16;
		
		
	}
}
