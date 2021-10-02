package tests.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.core.commands.listedArguments.*;
import tests.core.commands.mappedArguments.*;

@RunWith(Suite.class)
@SuiteClasses({
	CreateProductCommandTest.class,
	CreateOrderCommandTest.class,
	UpdateOrderCommandTest.class,
	ShowOrderByIDCommandTest.class,
	RemoveAllProductsTest.class, 
	RemoveProductCommandTest.class
	})
public class Commands_TestSuite {

}
