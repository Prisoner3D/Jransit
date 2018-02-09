package UserInterface;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.jfoenix.controls.JFXSlider;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

/**
 * Slider that manages the time.
 * @author BT_1N3_06
 */

public class TimeSlider {
	  JFXSlider slider;
	  LocalDateTime now = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      Date in = new Date();
      LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
      Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
	  TimeFormat time = new TimeFormat(out);

	public TimeSlider(JFXSlider slide,int i, int j, StackPane parent) {
		this.slider = slide;
    	slider.setMinWidth(i);
    	slider.setMaxWidth(j);
        slider.setMin(0); //to be based on earliest record
        slider.setMax(time.hour); // current time
        slider.setMinorTickCount(0);
        slider.setMajorTickUnit(1);
        slider.setSnapToTicks(true);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
		slider.setMaxWidth(1000);
		parent.getChildren().add(slider);
		parent.setAlignment(slider, Pos.BOTTOM_CENTER);
		slider.setShowTickLabels(true);
	}
	
	/**
	 * Format displayed time on slider
	 */
	public void displayTime() {
      StringConverter<Double> stringConverter = new StringConverter<Double>() {

          @Override
          public String toString(Double object) {
              long seconds = object.longValue();
              long minutes = TimeUnit.SECONDS.toMinutes(seconds);
              long remainingseconds = seconds - TimeUnit.MINUTES.toSeconds(minutes);
              return String.format("%02d", minutes) + ":" + String.format("%02d", remainingseconds);
          }

          @Override
          public Double fromString(String string) {
              return null;
          }
      };
          slider.setLabelFormatter(stringConverter);

          Text text = new Text();

          slider.valueProperty().addListener((observable, oldValue, newValue) ->
                  text.setText(stringConverter.toString(newValue.doubleValue())));
	}
	
}
