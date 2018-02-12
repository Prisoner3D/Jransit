package util;

import info.Location;

public class MappingFunctions {
	public static double[] mapCoordinatesToPixels(Location current, Location upperLeft, Location lowerRight) {
		double hypotenuse = upperLeft.distanceTo(current);
		System.out.println(hypotenuse);
		double bearing = upperLeft.angleTo(current);
		
		double currentDistanceX = Math.abs(Math.cos(bearing * Math.PI / 180.0) * hypotenuse);
		double currentDistanceY = Math.abs(Math.sin(bearing * Math.PI / 180.0) * hypotenuse);
		double totalHypotenuse = upperLeft.distanceTo(lowerRight);
		double totalDistanceX = Math.abs(totalHypotenuse * Math.cos(upperLeft.angleTo(lowerRight) * Math.PI / 180.0));
		double totalDistanceY = Math.abs(totalHypotenuse * Math.sin(upperLeft.angleTo(lowerRight) * Math.PI / 180.0));
		System.out.println(currentDistanceX + " " + currentDistanceY);
		System.out.println(totalDistanceX + " " + totalDistanceY);
		double currentPixelX = (currentDistanceX / totalDistanceX) * 1075; //TODO : Save in constants
		double currentPixelY = (currentDistanceY / totalDistanceY) * 800; //TODO : Save in constants

		return new double[] {currentPixelX, currentPixelY};
	}
}
