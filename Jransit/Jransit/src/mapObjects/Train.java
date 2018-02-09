package mapObjects;

import info.Location;
import util.ImageUtilities;
import info.TrainInfo;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import util.MappingFunctions;

public class Train extends Pane implements MapObject {
	private TrainInfo info;
	private double x;
	private double y;
	private Image icon;
	public Train(TrainInfo info) {
		super();
		
		double curLat = info.getLongitude();
		double curLon = info.getLatitude();
		Location current = new Location(curLat, curLon);
		Location topLeft = new Location(40.8531617,-73.951866); //TODO move these constants lat lon to a constant class
		Location bottomRight = new Location(40.6373576,-73.9279334); //TODO move these constants lat lon to a constant class
		double[] pixelCoord = MappingFunctions.mapCoordinatesToPixels(current, topLeft, bottomRight);
		this.x = pixelCoord[0];
		this.y = pixelCoord[1];
		this.relocate(x, y);
		this.icon = new Image(ImageUtilities.getTrainIcon(this.info.getLine().getLineLetter())); //Train Icon must be capitalized if a letter.
																								 //Express trains must have the icon + exp.
		ImageView lineIcon = new ImageView();
		lineIcon.setImage(this.icon);
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
