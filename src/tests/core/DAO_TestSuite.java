package tests.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.core.db.dao.*;

@RunWith(Suite.class)
@SuiteClasses({
	OrderDAOTest.class,
	ProductDAOTest.class
	})
public class DAO_TestSuite {

}
