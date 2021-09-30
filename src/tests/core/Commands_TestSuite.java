package tests.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import tests.core.commands.*;
import tests.core.commands.listedArguments.CreateOrderCommandTest;
import tests.core.commands.listedArguments.RemoveAllProductsTest;
import tests.core.commands.listedArguments.RemoveProductCommandTest;
import tests.core.commands.mappedArguments.CreateProductCommandTest;
import tests.core.commands.mappedArguments.UpdateOrderCommandTest;

@RunWith(Suite.class)
@SuiteClasses({
	CreateProductCommandTest.class,
	CreateOrderCommandTest.class,
	UpdateOrderCommandTest.class,
	///ShowProductsCommandTest.class, 
	RemoveAllProductsTest.class, 
	RemoveProductCommandTest.class
	})
public class Commands_TestSuite {

}
