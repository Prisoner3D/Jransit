import java.io.IOException;

import api.MTAApi;
import api.MTAApiStaticFactory;
import api.TrainFeed;
import csv.StopsStaticFactory;
import info.StationInfo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import mapObjects.Station;
import mapObjects.Train;

/**
 * Main application class.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        stage.setTitle("Jransit");
        stage.setTitle("Jransit");
		stage.setHeight(1075);
		stage.setWidth(800);
		
		Pane pane = loadMainPane();
		
		stage.show();
        stage.setScene(
            createScene(
            		pane
            )
        );
        
        MTAApi theApi = MTAApiStaticFactory.getApi(TrainFeed.NUM_S);
        StationInfo theTrainInfo = new StationInfo(theApi, StopsStaticFactory.getAllStops().get(0));
        Station theStation = new Station(theTrainInfo);
        pane.getChildren().add(theStation);
        stage.show();
    }

    /**
     * Loads the main fxml layout.
     * Sets up the vista switching VistaNavigator.
     * Loads the first vista into the fxml layout.
     *
     * @return the loaded pane.
     * @throws IOException if the pane could not be loaded.
     */
    private Pane loadMainPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();

        Pane mainPane = (Pane) loader.load(
            getClass().getResourceAsStream(
                VistaNavigator.MAIN
            )
        );

        MainController mainController = loader.getController();

        VistaNavigator.setMainController(mainController);
        VistaNavigator.loadVista(VistaNavigator.VISTA_1);

        return mainPane;
    }

    /**
     * Creates the main application scene.
     *
     * @param mainPane the main application layout.
     *
     * @return the created scene.
     */
    private Scene createScene(Pane mainPane) {
        Scene scene = new Scene(
            mainPane
        );

        scene.getStylesheets().setAll(
            getClass().getResource("vista.css").toExternalForm()
        );

        return scene;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
