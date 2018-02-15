package UserInterface;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import csv.CSVUtilities;

/**
 * Reads slider value to update map according to time.
 * 
 * @author BT_1N3_06
 *
 */
public class TimelineReader {
	CSVUtilities csv;
	Date DateFrom;
	Date DateTo;
	TimeSlider slider;
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
	LocalDateTime now = LocalDateTime.now();
	Instant instant = now.atZone(ZoneId.systemDefault()).toInstant();
	Date dateNow = Date.from(instant);

	public TimelineReader(CSVUtilities csv, TimeSlider slider) {
		this.DateFrom = dateNow;
		this.csv = csv;
		this.DateTo = (Date) dtf.parse((csv.getDataInt(csv.getnumColumns()).get(csv.getCSVData().size())).toString());
		this.slider = slider;
	}

	/**
	 * Updates, every 15 seconds, position of items on the map and redraws path
	 * based on slider's time value.
	 */
	public void updateMap() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// update map icon and locations
				// slider.getValue(); compare to closest value in csv
				// retrieve latlng and update position of train marker
				// redraw path
			}
		}, 0, 15000);
	}
}
