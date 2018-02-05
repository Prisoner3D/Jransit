package UserInterface;
	
import com.jfoenix.controls.JFXSlider;
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
import com.teamdev.jxmaps.MarkerOptions;
import com.teamdev.jxmaps.MouseEvent;
import com.teamdev.jxmaps.javafx.MapView;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class JavaFXExample extends Application {
    final static MapView mapView = new MapView();
    final static Stage primaryStage = new Stage();
	
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
                    // 40.650002, and the longitude is -73.949997.
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
                            final Marker marker = new Marker(map);
                            // Move marker to the position where user clicked
                            marker.setPosition(mouseEvent.latLng());

                            // Adding event listener that intercepts clicking on marker
                            marker.addEventListener("click", new MapMouseEvent() {
                                @Override
                                public void onEvent(MouseEvent mouseEvent) {
                                    // Removing marker from the map
                                    marker.remove();
                                }
                            });
                        }
                    });
                }
            }
        });
        StackPane root = new StackPane();
        BorderPane map = new BorderPane(mapView);
        map.setMaxSize(1000,1000);
        Pane slider = new Pane();
        slider.setMinWidth(500);
        
		JFXSlider hor_left = new JFXSlider();
		hor_left.setMin(0); //to be based on rows
		hor_left.setMax(100); //to be based on rows
		hor_left.setValue(1); 
		hor_left.setMinorTickCount(0);
		hor_left.setMajorTickUnit(1);
		hor_left.setSnapToTicks(true);
		hor_left.setShowTickMarks(true);
		hor_left.setShowTickLabels(true);
		hor_left.setMaxWidth(500);
		slider.getChildren().add(hor_left);
     //   slider.setAlignment(hor_left, Pos.CENTER_RIGHT);
        
        root.getChildren().addAll(slider,map);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
