import javafx.fxml.FXMLLoader;

import java.io.IOException;

/**
 * Utility class for controlling navigation between vistas.
 *
 * All methods on the navigator are static to facilitate simple access from
 * anywhere in the application.
 */
public class VistaNavigator {

	/**
	 * Convenience constants for fxml layouts managed by the navigator.
	 */
	public static final String MAIN = "main.fxml";
	public static final String VISTA_1 = "vista1.fxml";
	public static final String VISTA_2 = "vista2.fxml";

	/** The main application layout controller. */
	private static MainController mainController;

	/**
	 * Stores the main controller for later use in navigation tasks.
	 *
	 * @param mainController
	 *            the main application layout controller.
	 */
	public static void setMainController(MainController mainController) {
		VistaNavigator.mainController = mainController;
	}

	/**
	 * Loads the vista specified by the fxml file into the vistaHolder pane of the
	 * main application layout.
	 * 
	 * @param fxml
	 *            the fxml file to be loaded.
	 */
	public static void loadVista(String fxml) {
		try {
			mainController.setVista(FXMLLoader.load(VistaNavigator.class.getResource(fxml)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}