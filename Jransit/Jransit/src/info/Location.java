package info;

/**
 * Useful information of an object's location
 * 
 * @author
 *
 */
public class Location {
    private final double longitude;
    private final double latitude;

    /**
     * Location Constructor: Constructor for a Location object
     * 
     * @param longitude
     *            : Longitude (X)
     * @param latitude
     *            : Latitude (Y)
     */
    public Location(final double latitude, final double longitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    /**
     * angleTo(): Angle between two Location objects in degrees, North is 0 deg,
     * South is 180 deg.
     * 
     * @param location
     *            : Other Location object used in calculation
     * @return double : Angle between objects in degrees
     */
    public double angleTo(final Location location) {
        final double longDiff = this.longitude - location.getLongitude();
        final double latDiff = this.latitude - location.getLatitude();
        return (Math.toDegrees(Math.atan2(longDiff, latDiff) + 2 * Math.PI) + 180L) % 360L;
    }

    // public void setLongitude(double longitude) {
    // this.longitude = longitude;
    // }

    /**
     * distanceTo(): Distance between two Location objects
     * 
     * @param location
     *            : other Location object used in calculations
     * @return double : the distance
     */
    public double distanceTo(final Location location) {
        final double longDiff = this.longitude - location.getLongitude();
        final double latDiff = this.latitude - location.getLatitude();
        return Math.sqrt(Math.pow(longDiff, 2) + Math.pow(latDiff, 2));
    }

    // public void setLatitude(double latitude) {
    // this.latitude = latitude;
    // }

    @Override
    public boolean equals(Object location) {
        if (this == location) {
            return true;
        }
        if (!(location instanceof Location)) {
            return false;
        }
        Location o = (Location) location;
        return this.longitude == o.getLongitude() && this.latitude == o.getLatitude();
    }

    /**
     * getLatitude(): Returns latitude
     * 
     * @return double : latitude of the Location object
     */
    public double getLatitude() {
        return this.latitude;
    }

    /**
     * getLongitude(): Returns longitude
     * 
     * @return double : longitude of the Location object
     */
    public double getLongitude() {
        return this.longitude;
    }

    @Override
    public String toString() {
        return "Lat: " + this.getLatitude() + " Long: " + this.getLongitude();
    }
}
