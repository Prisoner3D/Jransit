package UserInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXSlider.IndicatorPosition;

import javafx.geometry.Orientation;
import javafx.scene.control.Slider;

public class TimelineReader {
		//private CSVUtilities csv;
		private Date DateFrom;
		private Date DateTo;
		private JFXSlider slider;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		LocalDateTime now = LocalDateTime.now();

	public TimelineReader(CSVUtilities csv, JFXSlider slider) {
		this.DateFrom = new Date(dtf.format(now));
		//this.csv = csv;
		//this.DateTo = last row of csv reader history sort DESC 
		//dates (csv.getDataInt(numColumns));
		this.slider = slider;
	}
	
    public void sliderValues(double min, double max, double value) {
    	slider.setMinWidth(min);
    	slider.setMaxWidth(max);
    	slider.getIndicatorPosition();
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
