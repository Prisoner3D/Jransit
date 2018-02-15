package mapObjects;

import java.io.File;
import java.io.FileNotFoundException;

import com.teamdev.jxmaps.Icon;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapMouseEvent;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.MouseEvent;
import com.teamdev.jxmaps.Size;

import info.BusInfo;

public class Bus {
    private Marker marker;
    private Map map;
    private BusInfo info;

    public Bus(Map map, BusInfo info, boolean place) {
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
        this.marker.addEventListener("click", new MapMouseEvent() {
            @Override
            public void onEvent(MouseEvent mouseEvent) {
                InfoWindow infoWindow = new InfoWindow(map);
                infoWindow.setContent("<p>" + info.getLineName() + "</br>" + "Next Stop: " + info.getNextStop() + "</br>" + "Last Stop: " + info.getDestinationName() + "</p>");
                infoWindow.open(map, marker);
            }
        });
    }

    public void updatePosition() {
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
