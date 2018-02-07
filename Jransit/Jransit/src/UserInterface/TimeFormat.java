package UserInterface;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}