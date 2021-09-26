package tests.core.util;

import static org.junit.Assert.*;

import org.junit.Test;

import core.util.StringUtil;

public class StringUtil_Test {

	@Test
	public void isEmptyString_true() {
		assertTrue(StringUtil.isEmptyString(null));
		assertTrue(StringUtil.isEmptyString(""));
		assertTrue(StringUtil.isEmptyString("  "));
	}

	@Test
	public void isEmptyString_false() {
		assertFalse(StringUtil.isEmptyString(" a  "));
	}
}
