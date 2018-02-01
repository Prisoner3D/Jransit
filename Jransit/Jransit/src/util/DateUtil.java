package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public DateUtil() {
		// TODO Auto-generated constructor stub
	}

	public static String getTime(long unixSeconds) {
		Date date = new Date(unixSeconds*1000L);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		sdf.setTimeZone(Calendar.getInstance().getTimeZone());
		String formattedDate = sdf.format(date);
		return formattedDate;
	}
}
