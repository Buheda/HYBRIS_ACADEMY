package tests.core.util;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.sql.Date;

import org.junit.Test;

import core.util.DateTimeFormatter;

public class DateTimeFormatter_Tests {
	
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm");

	@Test
	public void getNow() {
		Timestamp expected = new Timestamp(System.currentTimeMillis());
		Timestamp actual = DateTimeFormatter.getNow();
		assertEquals(dateFormat.format(expected), dateFormat.format(actual));
	}
	
	@Test
	public void timestampToStr() {
		Timestamp expected = new Timestamp(System.currentTimeMillis());
		Timestamp actual = DateTimeFormatter.getNow();
		assertEquals(DateTimeFormatter.timestampToStr(actual), dateFormat.format(expected));
	}
}
