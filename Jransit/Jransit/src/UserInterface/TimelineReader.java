package UserInterface;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXSlider.IndicatorPosition;

import javafx.geometry.Orientation;

public class TimelineReader {
		private CSVReader csv;
		private DateFrom;
		private DateTo;
		private JFXSlider slider;
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
		LocalDateTime now = LocalDateTime.now();

	public TimelineReader(CSVReader csv, JFXSlider slider) {
		this.DateFrom = new Date(dtf.format(now));
		this.csv = csv;
		//this.DateTo = last row of csv reader history sort DESC
		this.slider = slider;
	}
	
    public sliderValues(double min, double max, double value) {
        super(min, max, value);
        initialize();
    }
    

    public IndicatorPosition getIndicatorPosition() {
        return indicatorPosition == null ? IndicatorPosition.LEFT : indicatorPosition.get();
    }

    public StyleableObjectProperty<IndicatorPosition> indicatorPositionProperty() {
        return this.indicatorPosition;
    }

    public void setIndicatorPosition(IndicatorPosition pos) {
        this.indicatorPosition.set(pos);
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
