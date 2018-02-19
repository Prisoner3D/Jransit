package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Date utilities to format displayed dates.
 * 
 * @author
 *
 */
public class DateUtil {

    /**
     * Formats unixSeconds to a String representation of the date
     * 
     * @param unixSeconds
     *            time in unixSeconds
     * @return yyyy-MM-dd HH:mm:ss formatted data
     */
    public static String getTime(long unixSeconds) {
        Date date = new Date(unixSeconds * 1000L);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        sdf.setTimeZone(Calendar.getInstance().getTimeZone());
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    public DateUtil() {
        
    }
}
