package mapObjects;

import java.io.File;

import com.teamdev.jxmaps.Icon;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.Marker;

import info.BusInfo;
import javafx.scene.image.Image;

public class Bus
{
	private Marker marker;
	private Map map;
	private BusInfo info;
	public Bus(Icon i, Map map, BusInfo info, boolean place)
	{
		System.out.println("Bus created");
		this.info = info;
		this.map = map;
		this.marker = new Marker(map);
        marker.setIcon(i);
		if (place) {
         this.marker.setPosition(new LatLng(Double.valueOf(info.getLat()), Double.valueOf(info.getLng())));
		}
	}
	
	public void updatePosition()
	{
		this.marker.setPosition(new LatLng(Double.valueOf(this.info.getLat()), Double.valueOf(this.info.getLng())));
	}

	public Marker getMarker() {
		return marker;
	}

	public Map getMap() {
		return map;
	}

	public BusInfo getInfo() {
		return info;
	}
	
	
}
