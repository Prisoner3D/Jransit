package UserInterface;
	
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXSlider.IndicatorPosition;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			HBox slider = new HBox();
			JFXSlider hor_left = new JFXSlider();
			hor_left.setMinWidth(500);
			JFXSlider hor_right = new JFXSlider();
			hor_left.setMinWidth(500);
			hor_left.setIndicatorPosition(IndicatorPosition.RIGHT);
			JFXSlider ver_left = new JFXSlider();
			ver_left.setMinHeight(500);
			ver_left.setOrientation(Orientation.VERTICAL);
			JFXSlider ver_right = new JFXSlider();
			ver_right.setMinHeight(500);
			ver_right.setOrientation(Orientation.VERTICAL);
			ver_right.setIndicatorPosition(IndicatorPosition.RIGHT);
			root.getChildren().add(slider);
			slider.getChildren().addAll(hor_left, hor_right, ver_left, ver_right);
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
