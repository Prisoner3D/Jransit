package info;

public class Location {
    private final double longitude;
    private final double latitude;
    
    public Location(final double longitude, final double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    public double getLongitude() {
        return this.longitude;
    }
    
//    public void setLongitude(double longitude) {
//        this.longitude = longitude;
//    }
    
    public double getLatitude() {
        return this.latitude;
    }
    
//    public void setLatitude(double latitude) {
//        this.latitude = latitude;
//    }
    
    public double distanceTo(final Location location) {
        final double longDiff = this.longitude - location.getLongitude();
        final double latDiff = this.latitude - location.getLatitude();
        return Math.sqrt(Math.pow(longDiff, 2) + Math.pow(latDiff, 2));
    }
    
    public double angleTo(final Location location) {
        final double longDiff = this.longitude - location.getLongitude();
        final double latDiff = this.latitude - location.getLatitude();
        return (Math.toDegrees(Math.atan2(longDiff, latDiff) + 2 * Math.PI) + 180L) % 360L;
    }
}
