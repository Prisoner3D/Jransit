package UserInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.swing.JTextField;

import com.teamdev.jxmaps.ControlPosition;
import com.teamdev.jxmaps.DirectionsLeg;
import com.teamdev.jxmaps.DirectionsRequest;
import com.teamdev.jxmaps.DirectionsResult;
import com.teamdev.jxmaps.DirectionsRouteCallback;
import com.teamdev.jxmaps.DirectionsStatus;
import com.teamdev.jxmaps.Icon;
import com.teamdev.jxmaps.LatLng;
import com.teamdev.jxmaps.Map;
import com.teamdev.jxmaps.MapMouseEvent;
import com.teamdev.jxmaps.MapOptions;
import com.teamdev.jxmaps.MapReadyHandler;
import com.teamdev.jxmaps.MapStatus;
import com.teamdev.jxmaps.MapTypeControlOptions;
import com.teamdev.jxmaps.MouseEvent;
import com.teamdev.jxmaps.TravelMode;
import com.teamdev.jxmaps.javafx.MapView;

import api.BusThread;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import mapObjects.Bus;
import mapObjects.TimelineSlider;

public class MapsApp extends Application {
	public final static MapView mapView = new MapView();
	public final static Stage primaryStage = new Stage();
	public static Label countdown;
	public static Icon busImage;
	public static BusThread busThread;
	public static TimelineSlider slider;
	public static Timer timer;
	private List<Bus> nonVisibleMarkers = new ArrayList<Bus>();
	
	public static void main(String[] args) {
		launch(args);
	}
	// Modified version of
	// https://stackoverflow.com/questions/47655695/javafx-countdown-timer-in-label-settext
	/**
	 * Starts a countdown on the countdown text
	 * @param interval
	 */
	public static void setTimer(AtomicInteger interval) {
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				if (interval.get() > 0) {
					Platform.runLater(() -> countdown.setText(interval + ""));
					interval.decrementAndGet();
				} else {
					Platform.runLater(() -> countdown.setText("(Grabbing API)"));
					synchronized(busThread) {
						busThread.notify();
					}
					timer.cancel();
				}
			}
		}, 1000, 1000);
	}
	private JTextField fromField;
	private JTextField toField;

	private int directionClicks = 0;

	private Label distance;

	TimelineReader readTime;
	// CSVUtilities csv = new CSVUtilities(new File("Jransit\\stops.txt"));

	private void calculateDirection() {
		// Getting the associated map object
		final Map map = mapView.getMap();
		// Creating a directions request
		DirectionsRequest request = new DirectionsRequest();
		// Setting of the origin location to the request
		request.setOriginString(fromField.getText());
		// Setting of the destination location to the request
		request.setDestinationString(toField.getText());
		// Setting of the travel mode
		request.setTravelMode(TravelMode.DRIVING);
		// Calculating the route between locations
		mapView.getServices().getDirectionService().route(request, new DirectionsRouteCallback(map) {
			@Override
			public void onRoute(DirectionsResult result, DirectionsStatus status) {
				// Checking of the operation status
				if (status == DirectionsStatus.OK) {
					// Drawing the calculated route on the map
					for (DirectionsLeg leg : result.getRoutes()[0].getLegs()) {
						Platform.runLater(() -> distance.setText("Duration: " + leg.getDuration().getText()));
					}
					map.getDirectionsRenderer().setDirections(result);
				}
			}
		});

	}

	@Override
	public void init() throws Exception {
		// Initializing of JavaFX engine
		MapView.InitJavaFX();
	}

	@Override
	public void start(final Stage primaryStage) {
		fromField = new JTextField("");
		toField = new JTextField("");
		// Setting of a ready handler to MapView object. onMapReady will be called when
		// map initialization is done and
		// the map object is ready to use. Current implementation of onMapReady
		// customizes the map object.
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

					// Adding event listener that intercepts clicking on map

					map.addEventListener("click", new MapMouseEvent() {
						@Override
						public void onEvent(MouseEvent mouseEvent) {
							LatLng loc = mouseEvent.latLng();
							if (directionClicks % 2 == 0) {
								fromField.setText(loc.getLat() + "," + loc.getLng());
								directionClicks++;
							} else {
								toField.setText(loc.getLat() + "," + loc.getLng());
								calculateDirection();
								directionClicks++;
							}
						}
					});
					busThread = new BusThread(map, busImage);
					busThread.start();
				}
			}
		});
		BorderPane root = new BorderPane();
		BorderPane map = new BorderPane(mapView);
		map.setMaxSize(750, 750);

		slider = new TimelineSlider();

		countdown = new Label();
		countdown.setText("(Grabbing API)");

		Label countdownPad = new Label();
		countdownPad.setText("Update in ");

		distance = new Label();
		distance.setText("Click on two points to calculate the duration between them.");

		HBox filterWrapper = new HBox();
		Label filterLbl = new Label("Filter by bus: ");
		TextField filterText = new TextField();
		filterWrapper.getChildren().addAll(filterLbl, filterText);
		filterText.setPrefWidth(300);
		filterWrapper.setAlignment(Pos.CENTER);
		filterWrapper.setPadding(new Insets(0, 0, 40, 0));
		filterText.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,
		            String oldValue, String newValue) {
		    	nonVisibleMarkers.forEach(b -> {
		    		b.getMarker().setVisible(true);
		    	});
		    	// Filter by matching characters from start for busname and text value to get
		    	// non matching busses to set visible to false
		    	List<Bus> nonMatchingBusses = busThread.getBusFac().getBusses().stream().filter(bus -> {
		    		String busName = bus.getInfo().getLineName();
		    		if (newValue.length() > busName.length()) {
		    			return true;
		    		}
		    		for (int i = 0; i < newValue.length(); i++) {
		    			if (newValue.charAt(i) != busName.charAt(i)) {
		    				return true;
		    			}
		    		}
		    		return false;
		    	}).collect(Collectors.toList());
		    	nonMatchingBusses.forEach(b -> {
		    		b.getMarker().setVisible(false);
		    	});
		    	nonVisibleMarkers = nonMatchingBusses;
		    }
		});
		
		HBox countdownText = new HBox(countdownPad, countdown);
		countdownText.setAlignment(Pos.CENTER);

		VBox bottom = new VBox();
		bottom.styleProperty().set("-fx-font-size: 20");
		bottom.setAlignment(Pos.CENTER);
		bottom.getChildren().addAll(
				filterWrapper, 
				distance, 
				countdownText, 
				slider.getSlider()
		);
		root.setCenter(map);
		root.setBottom(bottom);
		Scene scene = new Scene(root, 1000, 1000);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void stop() {
		busThread.clearFile();
		System.exit(0);
	}
}
