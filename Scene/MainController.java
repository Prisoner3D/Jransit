import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;

/**
 * Main controller class for the entire layout, the skeleton for the scenes that are to come
 * @author Katherin
 *
 */
public class MainController {

	/** Holder of a switchable vista. */
	@FXML
	private StackPane vistaHolder;

	/**
	 * Replaces the vista displayed in the vista holder with a new vista.
	 *
	 * @param node
	 *            the vista node to be swapped in.
	 */
	public void setVista(Node node) {
		vistaHolder.getChildren().setAll(node);
	}

}