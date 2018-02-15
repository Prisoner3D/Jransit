package UserInterface;
	
import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

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
import com.teamdev.jxmaps.Size;
import com.teamdev.jxmaps.javafx.MapView;

import api.BusThread;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mapObjects.TimelineSlider;

public class JavaFXExample extends Application {
    public final static MapView mapView = new MapView();
    public final static Stage primaryStage = new Stage();
    public static Label countdown;
    public static Icon busImage;
    public static BusThread busThread;
    public static TimelineSlider slider;
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
//                    Marker marker = new Marker(map);
//                    busImage = new Icon();
//                    File ccu = new File(getClass().getResource("bus-icon.png").getFile());
//                    busImage.loadFromFile(ccu);
//                    busImage.setScaledSize(new Size(24, 24));
//                    
//                    // 40.650002, and the longitude is -73.949997.
//
//                 /*   List<Stop> stops = StopsStaticFactory.getAllStops();
//                    for (Stop stop : stops) {
//>>>>>>> branch 'master' of https://github.com/Prisoner3D/Jransit.git
//                    	Marker markerlol = new Marker(map); 
//                    	markerlol.setPosition(new LatLng(Double.valueOf(stop.getLatitude()), Double.valueOf(stop.getLongitude())));
//                    	markerlol.setIcon(icon);
//                    }*/
//                    marker.setIcon(busImage);
//                    marker.setPosition(new LatLng(40.650002, -73.949997));
//                    InfoWindow infoWindow = new InfoWindow(map);
//                    infoWindow.setContent("dank");
//                    // Showing info windows under the marker
//                    infoWindow.open(map, marker);
//                    // Adding event listener that intercepts clicking on map
//                  
//                    
//                    // Adds reg google maps markers
//                    marker.addEventListener("click", new MapMouseEvent() {
//                        @Override
//                        public void onEvent(MouseEvent mouseEvent) {
//                            // Closing initially created info window
//                            infoWindow.close();
//                            // Creating a new marker
//                            Marker mark = new Marker(map);
//                            mark.setIcon(busImage);
//                            // Move marker to the position where user clicked
//                            mark.setTitle("Train");
//                            mark.setPosition(mouseEvent.latLng());
//                            infoWindow.open(map, mark);
//                            // Adding event listener that intercepts clicking on marker
//                            mark.addEventListener("click", new MapMouseEvent() {
//                                @Override
//                                public void onEvent(MouseEvent mouseEvent) {
//                                    // Removing marker from the map
//                                	mark.remove();
//                                }
//                            });
//                        }
//                    });
                    busThread = new BusThread(map, busImage);
                    busThread.start();
                }
            }
        });
        BorderPane root = new BorderPane();
        BorderPane map = new BorderPane(mapView);
        map.setMaxSize(750,750);
        
        slider = new TimelineSlider();
        
        countdown = new Label();
        countdown.setText("(Grabbing API)");
        
        Label countdownPad = new Label();
        countdownPad.setText("Update in ");
        
        HBox countdownText = new HBox(countdownPad, countdown);
        countdownText.setAlignment(Pos.CENTER);
        
        VBox bottom = new VBox();
        bottom.setAlignment(Pos.CENTER);
        bottom.getChildren().addAll(countdownText, slider.getSlider());
        root.setCenter(map);
        root.setBottom(bottom);
        Scene scene = new Scene(root,1000,1000);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    // Modified version of https://stackoverflow.com/questions/47655695/javafx-countdown-timer-in-label-settext
    public static void setTimer(AtomicInteger interval) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if(interval.get() > 0)
                {
                    Platform.runLater(() -> countdown.setText(interval + ""));
                    interval.decrementAndGet();
                }
                else {
                	Platform.runLater(() -> countdown.setText("(Grabbing API)"));
                    timer.cancel();
                }
            }
        }, 1000,1000);
    }

    @Override
    public void stop(){
    	busThread.clearFile();
    	System.exit(0);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
