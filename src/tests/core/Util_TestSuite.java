package tests.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.core.util.DateTimeFormatter_Test;
import tests.core.util.StringUtil_Test;

@RunWith(Suite.class)
@SuiteClasses({ DateTimeFormatter_Test.class, StringUtil_Test.class})
public class Util_TestSuite {

}
