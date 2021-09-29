package tests.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.core.commands.*;

@RunWith(Suite.class)
@SuiteClasses({
	CreateProductCommandTest.class, 
	///ShowProductsCommandTest.class, 
	RemoveAllProductsTest.class, 
	RemoveProductCommandTest.class
	})
public class Commands_TestSuite {

}
