package mapObjects;

import java.io.File;

import com.teamdev.jxmaps.Icon;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.Marker;

import info.BusInfo;
import javafx.scene.image.Image;

public class Bus implements MapObject
{
	private Marker marker;
	private Map map;
	private BusInfo busLoc;
	public Bus(Map map, BusInfo latLon)
	{
		 this.marker = new Marker(map);
         Icon icon = new Icon();
         File ccu = new File(getClass().getResource("bus-icon.png").getFile());
         icon.loadFromFile(ccu);
         marker.setIcon(icon);
         this.marker.setPosition(new LatLng(Double.valueOf(latLon.getLat()), Double.valueOf(latLon.getLng())));
	}
	
	public void updatePosition()
	{
		this.marker.setPosition(new LatLng(Double.valueOf(this.busLoc.getLat()), Double.valueOf(this.busLoc.getLng())));
	}
}
