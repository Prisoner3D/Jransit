package mapObjects;

import java.io.File;
import java.io.FileNotFoundException;

import com.teamdev.jxmaps.Icon;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.Size;

import info.BusInfo;

public class Bus
{
	private Marker marker;
	private Map map;
	private BusInfo info;
	public Bus(Map map, BusInfo info, boolean place)
	{
		this.info = info;
		this.map = map;
		this.marker = new Marker(map);
		String basePath = (new File("").getAbsolutePath());
		File file = new File(basePath + "\\busIcons\\" + info.getLineName() + ".png");
		Icon ico = new Icon();
		if (file.exists()) {
			ico.loadFromFile(file);
		} else {
			file = new File(basePath + "\\busIcons\\bus.png");
			ico.loadFromFile(file);
		}
		ico.setScaledSize(new Size(24, 24));
        marker.setIcon(ico);
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
