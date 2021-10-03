package tests.core.util;

import static org.junit.Assert.*;

import org.junit.Test;

import core.util.StringUtil;

public class StringUtilTest {

	@Test
	public void isEmptyString_True() {
		assertTrue(StringUtil.isEmptyString(null));
		assertTrue(StringUtil.isEmptyString(""));
		assertTrue(StringUtil.isEmptyString("  "));
	}

	@Test
	public void isEmptyString_False() {
		assertFalse(StringUtil.isEmptyString(" a  "));
	}
}
