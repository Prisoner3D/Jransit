package UserInterface;

import java.io.File;
import java.io.IOException;

import com.jfoenix.controls.JFXSlider;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.shapes.MapShapeOptions;
import com.lynden.gmapsfx.util.MarkerImageFactory;
import com.sun.prism.PhongMaterial.MapType;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sun.invoke.util.BytecodeDescriptor;

public class JavaFXExample2 extends Application implements MapComponentInitializedListener {

	GoogleMapView mapView;
	GoogleMap map;

	@Override
	public void mapInitialized() {
		// Set the initial properties of the map.
		MapOptions mapOptions = new MapOptions();

		mapOptions.center(new LatLong(47.6097, -122.3331)).mapType(MapTypeIdEnum.ROADMAP).overviewMapControl(false)
				.panControl(false).rotateControl(false).scaleControl(false).streetViewControl(false).zoomControl(false)
				.zoom(12);

		map = mapView.createMap(mapOptions);
		/*
		 * //Add a marker to the map MapShapeOptions markerOptions = new
		 * MapViewOptions(); markerOptions.position( new LatLong(47.6, -122.3) )
		 * .visible(Boolean.TRUE) .title("My Marker");
		 * markerOptions.icon(MarkerImageFactory.createMarkerImage(
		 * "C:\\Users\\BT_1N3_05\\git\\jransit\\Jransit\\Jransit\\src\\UserInterface\\bus-icon.png",
		 * ".png")); Marker marker = new Marker( markerOptions );
		 */

		// map.addMarker(marker);

	}

	@Override
	public void start(Stage stage) throws Exception {

		// Create the JavaFX component and set this as a listener so we know when
		// the map has been initialized, at which point we can then begin manipulating
		// it.
		mapView = new GoogleMapView();
		mapView.addMapInializedListener(this);
		BorderPane map = new BorderPane(mapView);
		map.setMaxSize(750, 750);
		StackPane root = new StackPane();
		StackPane slider = new StackPane();
		slider.setMinWidth(500);
		// TimeSlider history = new TimeSlider(new JFXSlider(), 0, 3600, slider);
		/*
		 * readTime = new TimelineReader(csv,history); readTime.updateMap();
		 */
		root.getChildren().addAll(mapView, slider);
		Scene scene = new Scene(root);
		// stage.getIcons().add(new
		// Image(getClass().getResourceAsStream("bus-icon.png")));
		stage.setTitle("JavaFX and Google Maps");
		stage.setScene(scene);
		stage.show();
	}

}