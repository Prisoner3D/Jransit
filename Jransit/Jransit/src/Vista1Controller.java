
import UserInterface.MapsApp;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

/**
 * Controller class for the first vista.
 */
public class Vista1Controller {

	/**
	 * Event handler fired when the user requests a new vista.
	 *
	 * @param event
	 *            the event that triggered the handler.
	 */
	@FXML
	void nextPane(ActionEvent event) {
		Platform.runLater(new Runnable() {
			public void run() {

				new MapsApp().start(new Stage());
			}
		});
	}

}