package UserInterface;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXSlider.IndicatorPosition;

import javafx.geometry.Orientation;
import javafx.scene.control.Slider;

public class TimelineReader {
		CSVUtilities csv;
		Date DateFrom;
		Date DateTo;
		JFXSlider slider;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		LocalDateTime now = LocalDateTime.now();
	    Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
	    Date dateNow = Date.from(instant);

	public TimelineReader(CSVUtilities csv, JFXSlider slider) {
		this.DateFrom = dateNow;
		this.csv = csv;
		this.DateTo = (Date) dtf.parse((csv.getDataInt(csv.getnumColumns()).get(csv.CSVData.size())).toString());
		this.slider = slider;
	}
	
    public void sliderValues(double min, double max, double value) {
    	slider.setMinWidth(min);
    	slider.setMaxWidth(max);
        slider.setMin(0); //to be based on rows
        slider.setMax(100); //to be based on rows
        slider.setValue(1); 
        slider.setMinorTickCount(0);
        slider.setMajorTickUnit(1);
        slider.setSnapToTicks(true);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
    }
    
    
   
     
	/**TODO:
	 * SLIDER
	 * CSV Reader
	 * Time component
	 * etc..
	 */
	/**
	 * hor_left.setMinWidth(500);
			hor_left.setIndicatorPosition(IndicatorPosition.RIGHT);
			JFXSlider ver_left = new JFXSlider();
			ver_left.setMinHeight(500);
			ver_left.setOrientation(Orientation.VERTICAL);
			JFXSlider ver_right = new JFXSlider();
			ver_right.setMinHeight(500);
			ver_right.setOrientation(Orientation.VERTICAL);
	 */

}
