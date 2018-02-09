package mapObjects;

import info.Location;
import info.TrainInfo;
import javafx.scene.layout.Pane;
import util.MappingFunctions;

public class Train extends Pane implements MapObject {
	private TrainInfo info;
	private double x;
	private double y;
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
	}
	
	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

}
