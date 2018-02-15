package mapObjects;

import info.Location;
import util.ImageUtilities;
import info.TrainInfo;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import util.MappingFunctions;

/**
 * Representation of trains
 * @author
 *
 */
public class Train extends Pane implements MapObject {
	private TrainInfo info;
	private double x;
	private double y;
	private Image icon;
	
	/**
	 * Train Constructor
	 * @param info train information
	 */
	public Train(TrainInfo info) {
		super();
		this.info = info;
		double curLat = info.getLongitude();
		double curLon = info.getLatitude();
		System.out.println(curLat + "  " + curLon);
		Location current = new Location(curLat, curLon);
		System.out.println("HIHIHIHIHI");
		System.out.println("hihihiih: " + curLat + " " + curLon);
		Location topLeft = new Location(40.8531617,-73.951866); //TODO move these constants lat lon to a constant class
		Location bottomRight = new Location(40.6373576,-73.9279334); //TODO move these constants lat lon to a constant class
		double[] pixelCoord = MappingFunctions.mapCoordinatesToPixels(current, topLeft, bottomRight);
		this.x = pixelCoord[0];
		this.y = pixelCoord[1];
		this.relocate(x, y);
		System.out.println(x + " " +  y);
		//this.icon = new Image(ImageUtilities.getTrainIcon(this.info.getLine().getLineLetter())); //Train Icon must be capitalized if a letter.
		System.out.println(getClass().getResource("1.png"));														 //Express trains must have the icon + exp.
		ImageView lineIcon = new ImageView(getClass().getResource(this.info.getLine().getLineLetter() + ".png").toExternalForm());
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
