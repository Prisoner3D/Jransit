package UserInterface;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.application.Application;


public class slider extends Application {
	public void start(Stage primaryStage) throws Exception{
        Slider slider = new Slider(0, 3, 0);
        slider.setMin(0);
        slider.setMax(3);
        slider.setValue(1);
        slider.setMinorTickCount(0);
        slider.setMajorTickUnit(1);
        slider.setSnapToTicks(true);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);

        slider.setLabelFormatter(new StringConverter<Double>() {
            @Override
            public String toString(Double n) {
                if (n < 0.5) return "Novice";
                if (n < 1.5) return "Intermediate";
                if (n < 2.5) return "Advanced";

                return "Expert";
            }

            @Override
            public Double fromString(String s) {
                switch (s) {
                    case "Novice":
                        return 0d;
                    case "Intermediate":
                        return 1d;
                    case "Advanced":
                        return 2d;
                    case "Expert":
                        return 3d;

                    default:
                        return 3d;
                }
            }
        });

        slider.setMinWidth(380);

        HBox layout = new HBox(slider);
        layout.setPadding(new Insets(30));

        primaryStage.setScene(new Scene(layout));
        primaryStage.show();
    }


    public static void main(String[] args) {
    	launch(args);
    }
}
