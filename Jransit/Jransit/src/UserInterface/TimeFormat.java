package UserInterface;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Formats time 
 * @author BT_1N3_06
 *
 */
public class TimeFormat {
	 	int hour;
	    int minute;
	    Date date;

	    public TimeFormat(Date date)
	    {
	    	this.date = date;
	    	this.hour = date.getHours();
	    	this.minute = date.getMinutes();
	    }
	    
	    /**
	     * HH:MM display format
	     */
	    
	    public String displayDate() {
	    	DateFormat format = new SimpleDateFormat("HH:mm");
	    	System.out.println(format.format(this.date));
			return format.format(this.date);
	    }
	    
	    /**
	     * @return current time in minutes
	     */
	    public Long minutes() {
	    	SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy");
			Date theDate = this.date;
			long minutes2 = theDate.getTime() / 60000;
			return minutes2;
	    }
	   
}