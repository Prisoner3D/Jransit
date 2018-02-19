package util;

import info.Location;

/**
 * Functions to manipulate displayed map information
 * 
 * @author
 *
 */
public class MappingFunctions {

    /**
     * Used to calculate distance through triangles.
     * 
     * @param current
     *            current location
     * @param upperLeft
     *            the x-component distance to the current location
     * @param lowerRight
     *            the y-component distance to the current location
     * @return location of X and Y coordinate
     */
    public static double[] mapCoordinatesToPixels(Location current, Location upperLeft, Location lowerRight) {
        double hypotenuse = upperLeft.distanceTo(current);

        double bearing = upperLeft.angleTo(current);

        double currentDistanceX = Math.abs(Math.cos(bearing * Math.PI / 180.0) * hypotenuse);
        double currentDistanceY = Math.abs(Math.sin(bearing * Math.PI / 180.0) * hypotenuse);
        double totalHypotenuse = upperLeft.distanceTo(lowerRight);
        double totalDistanceX = Math.abs(totalHypotenuse * Math.cos(upperLeft.angleTo(lowerRight) * Math.PI / 180.0));
        double totalDistanceY = Math.abs(totalHypotenuse * Math.sin(upperLeft.angleTo(lowerRight) * Math.PI / 180.0));

        double currentPixelX = (currentDistanceX / totalDistanceX) * 1075; // TODO : Save in constants
        double currentPixelY = (currentDistanceY / totalDistanceY) * 800; // TODO : Save in constants

        return new double[] { currentPixelX, currentPixelY };
    }
}
