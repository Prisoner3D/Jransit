package util;

public class MappingFunctions {
	public void mapCoordinatesToPixels() {
		Location upperLeft = new Location("");
		upperLeft.setLatitude(41.866514127810355);
		upperLeft.setLongitude(-87.6720142364502);
		Location lowerRight = new Location("");
		lowerRight.setLatitude(41.83397145565242);
		lowerRight.setLongitude(-87.62824058532715);
		Location current = new Location("");
		current.setLatitude(41.850033);
		current.setLongitude(-87.65005229999997);
		double hypotenuse = upperLeft.distanceTo(current);
		double bearing = upperLeft.bearingTo(current);
		double currentDistanceX = Math.cos(bearing * Math.PI / 180.0) * hypotenuse;
//		                     "percentage to mark the position"
		double totalHypotenuse = upperLeft.distanceTo(lowerRight);
		double totalDistanceX = totalHypotenuse * Math.cos(upperLeft.bearingTo(lowerRight) * Math.PI / 180.0);
		double currentPixelX = currentDistanceX / totalDistanceX * 512;

		System.out.println(currentPixelX); // 259.3345493341548
	}
}
