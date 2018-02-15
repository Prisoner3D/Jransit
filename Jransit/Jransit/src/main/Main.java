package main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import UserInterface.MapsApp;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Main application class.
 */
public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Creates the main application scene.
	 *
	 * @param mainPane
	 *            the main application layout.
	 *
	 * @return the created scene.
	 */
	private Scene createScene(Pane mainPane) {
		Scene scene = new Scene(mainPane);

		scene.getStylesheets().setAll(getClass().getResource("vista.css").toExternalForm());

		return scene;
	}

	/**
	 * Loads the main fxml layout. Sets up the vista switching VistaNavigator. Loads
	 * the first vista into the fxml layout.
	 *
	 * @return the loaded pane.
	 * @throws IOException
	 *             if the pane could not be loaded.
	 */
	private Pane loadMainPane() throws IOException {
		FXMLLoader loader = new FXMLLoader();
		String basePath = (new File("").getAbsolutePath());
		File file = new File(basePath + "\\Jransit\\src\\main.fxml");
		String pathToVista1 = basePath + "\\Jransit\\src\\vista1.fxml";
		Pane mainPane = (Pane) loader.load(new FileInputStream(file));

		MainController mainController = loader.getController();

		VistaNavigator.setMainController(mainController);
		VistaNavigator.loadVista(VistaNavigator.VISTA_1);

		return mainPane;
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Jransit");
		stage.setHeight(800);
		stage.setWidth(800);
		stage.setScene(createScene(loadMainPane()));

		stage.show();
	}
}
