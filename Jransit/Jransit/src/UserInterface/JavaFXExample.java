package UserInterface;
	
import com.jfoenix.controls.JFXSlider;
import com.teamdev.jxmaps.ControlPosition;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
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
    @Override
    public void init() throws Exception {
        // Initializing of JavaFX engine
        MapView.InitJavaFX();
    }

    @Override
    public void start(final Stage primaryStage) {
        // Creation of a JavaFX map view
        final MapView mapView = new MapView();

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
                    // Setting map options
                    map.setOptions(options);
                    // Setting the map center
                    //40.6890° N, 73.9768° W
                    //40.7829° N, 73.9654° W
                    map.setCenter(new LatLng(40.6890, -73.9768));
                    // Setting initial zoom value
                    map.setZoom(20.0);
                }
            }
        });
        StackPane root = new StackPane();
        BorderPane map = new BorderPane(mapView);
        map.setMaxSize(1000,1000);
        BorderPane slider = new BorderPane();
        slider.setMinWidth(500);
		JFXSlider hor_left = new JFXSlider();
		hor_left.setMaxWidth(500);
		slider.getChildren().add(hor_left);
        slider.setAlignment(hor_left, Pos.CENTER_RIGHT);
        root.getChildren().addAll(map,slider);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
