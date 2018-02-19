package mapObjects;

import info.Location;
import info.StationInfo;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import util.MappingFunctions;

/**
 * Representation of train stations
 * 
 * @author
 *
 */
public class Station extends Pane implements MapObject {
    private StationInfo info;
    private double x;
    private double y;
    private Image icon;

    /**
     * Station Constructor
     * 
     * @param info
     *            station information
     */
    public Station(StationInfo info) {
        super();
        this.info = info;
        double curLat = info.getLongitude();
        double curLon = info.getLatitude();
        Location current = new Location(curLon, curLat); // BROKEN
        Location topLeft = new Location(40.8531617, -73.951866); // TODO move these constants lat lon to a constant class
        Location bottomRight = new Location(40.6373576, -73.9279334); // TODO move these constants lat lon to a constant class
        double[] pixelCoord = MappingFunctions.mapCoordinatesToPixels(current, topLeft, bottomRight);
        this.x = pixelCoord[0];
        this.y = pixelCoord[1];
        this.relocate(x, y);

        // this.icon = new
        // Image(ImageUtilities.getTrainIcon(this.info.getLine().getLineLetter()));
        // Train Icon must be capitalized if a letter.
        // Express trains must have the icon + exp.
        ImageView lineIcon = new ImageView(getClass().getResource("bus-icon.png").toExternalForm());
        this.getChildren().addAll(lineIcon);
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

}
