package util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateTimeFormatter {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm");
    
    public static Timestamp getNow() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        formatDateTime(now);
        return now;
    }
    
    public static void formatDateTime(Timestamp timestamp) {
		try {
			timestamp = new Timestamp(dateFormat.parse(dateFormat.format(timestamp)).getTime());
		} catch (ParseException e) {
			timestamp = new Timestamp(System.currentTimeMillis());
		}
    }

}
