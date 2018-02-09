package util;

import info.Location;

public class MappingFunctions {
	public static double[] mapCoordinatesToPixels(Location current, Location upperLeft, Location lowerRight) {
		double hypotenuse = upperLeft.distanceTo(current);
		double bearing = upperLeft.angleTo(current);
		double currentDistanceX = Math.cos(bearing * Math.PI / 180.0) * hypotenuse;
		double currentDistanceY = Math.sin(bearing * Math.PI / 180.0) * hypotenuse;
		double totalHypotenuse = upperLeft.distanceTo(lowerRight);
		double totalDistanceX = totalHypotenuse * Math.cos(upperLeft.angleTo(lowerRight) * Math.PI / 180.0);
		double totalDistanceY = totalHypotenuse * Math.sin(upperLeft.angleTo(lowerRight) * Math.PI / 180.0);
		double currentPixelX = currentDistanceX / totalDistanceX * 800; //TODO : Save in constants
		double currentPixelY = currentDistanceY / totalDistanceY * 1075; //TODO : Save in constants

		return new double[] {currentPixelX, currentPixelY};
	}
}
