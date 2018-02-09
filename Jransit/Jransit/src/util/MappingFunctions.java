package util;

import info.Location;

public class MappingFunctions {
	public void mapCoordinatesToPixels() {
		Location upperLeft = new Location(41.866514127810355, -87.6720142364502);
		Location lowerRight = new Location(41.83397145565242, -87.62824058532715);
		Location current = new Location(41.850033, -87.65005229999997);
		double hypotenuse = upperLeft.distanceTo(current);
		double bearing = upperLeft.angleTo(current);
		double currentDistanceX = Math.cos(bearing * Math.PI / 180.0) * hypotenuse;
//		                     "percentage to mark the position"
		double totalHypotenuse = upperLeft.distanceTo(lowerRight);
		double totalDistanceX = totalHypotenuse * Math.cos(upperLeft.angleTo(lowerRight) * Math.PI / 180.0);
		double currentPixelX = currentDistanceX / totalDistanceX * 512;

		System.out.println(currentPixelX); // 259.3345493341548
	}
}
