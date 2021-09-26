package core.util;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;

public class DateTimeFormatter {
    private static final DateFormat dateFormatter = new SimpleDateFormat("YYYY-MM-dd HH:mm");
       
    public static Timestamp getNow() {
        return new Timestamp(System.currentTimeMillis());
    }
    
    public static String timestampToStr(Timestamp timestamp) {
		return dateFormatter.format(timestamp);
    }
}
