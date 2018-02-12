package UserInterface;
	
import java.io.File;
import java.util.List;

import com.teamdev.jxmaps.ControlPosition;
import com.teamdev.jxmaps.Icon;
import com.teamdev.jxmaps.InfoWindow;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapMouseEvent;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.Marker;
import com.teamdev.jxmaps.MouseEvent;
import com.teamdev.jxmaps.javafx.MapView;

import api.MTAApiStaticFactory;
import api.TrainFeed;
import csv.Stop;
import csv.StopsStaticFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFXExample extends Application {
    final static MapView mapView = new MapView();
    final static Stage primaryStage = new Stage();
    TimelineReader readTime;
	//CSVUtilities csv = new CSVUtilities(new File("Jransit\\stops.txt"));
    
    @Override
    public void init() throws Exception {
        // Initializing of JavaFX engine
        MapView.InitJavaFX();
    }

    @Override
    public void start(final Stage primaryStage) {
        // Setting of a ready handler to MapView object. onMapReady will be called when map initialization is done and
        // the map object is ready to use. Current implementation of onMapReady customizes the map object.
    	mapView.setOnMapReadyHandler(new MapReadyHandler() {
            @Override
            public void onMapReady(MapStatus status) {
                // Check if the map is loaded correctly
                if (status == MapStatus.MAP_STATUS_OK) {
                    // Getting the associated map object
                    final Map map = mapView.getMap();
                    // Creating a map options object
                    MapOptions options = new MapOptions();
                    // Creating a map type control options object
                    
                    MapTypeControlOptions controlOptions = new MapTypeControlOptions();
                    // Changing position of the map type control
                    controlOptions.setPosition(ControlPosition.TOP_RIGHT);
                    // Setting map type control options
                    options.setMapTypeControlOptions(controlOptions);
                    map.setCenter(new LatLng(40.6890, -73.9768));
                    
                    // Setting initial zoom value
                    map.setZoom(20.0);
                    // Setting map options
                    map.setOptions(options);
                    Marker marker = new Marker(map);
                    Icon icon = new Icon();
                    File ccu = new File(getClass().getResource("bus-icon.png").getFile());
                    icon.loadFromFile(ccu);
                    marker.setIcon(icon);
                    // 40.650002, and the longitude is -73.949997.
                    List<Stop> stops = StopsStaticFactory.getAllStops();
                    for (Stop stop : stops) {
                    	Marker markerlol = new Marker(map);
                    	markerlol.setPosition(new LatLng(Double.valueOf(stop.getLatitude()), Double.valueOf(stop.getLongitude())));
                    	markerlol.setIcon(icon);
                    }
                    marker.setPosition(new LatLng(40.650002, -73.949997));
                    InfoWindow infoWindow = new InfoWindow(map);
                    infoWindow.setContent("dank");
                    // Showing info windows under the marker
                    infoWindow.open(map, marker);
                    // Adding event listener that intercepts clicking on map
                    map.addEventListener("click", new MapMouseEvent() {
                        @Override
                        public void onEvent(MouseEvent mouseEvent) {
                            // Closing initially created info window
                            infoWindow.close();
                            // Creating a new marker
                            Marker mark = new Marker(map);
                            // Move marker to the position where user clicked
                            mark.setTitle("Train");
                            mark.setPosition(mouseEvent.latLng());
                            
                            // Adding event listener that intercepts clicking on marker
                            mark.addEventListener("click", new MapMouseEvent() {
                                @Override
                                public void onEvent(MouseEvent mouseEvent) {
                                    // Removing marker from the map
                                	mark.remove();
                                }
                            });
                        }
                    });
                }
            }
        });
        StackPane root = new StackPane();
        BorderPane map = new BorderPane(mapView);
        map.setMaxSize(750,750);
        StackPane slider = new StackPane();
        slider.setMinWidth(500);
       // JFXSlider time = new JFXSlider();
		//TimeSlider history = new TimeSlider(time, 0, 3600, slider);
    /*	readTime = new TimelineReader(csv,history);
    	readTime.updateMap();*/
        root.getChildren().addAll(slider,map);
        Scene scene = new Scene(root,1000,1000);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
