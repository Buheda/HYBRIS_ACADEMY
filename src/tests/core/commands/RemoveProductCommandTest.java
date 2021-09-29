package tests.core.commands;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import core.commands.listedArguments.RemoveProductCommand;
import core.persistent.CommandsErrors;

public class RemoveProductCommandTest {

	RemoveProductCommand command = new RemoveProductCommand();
	HashMap<String, String> argsString;
	
	@Test
	public void testExecute_Valid() throws Exception {
		System.out.println("RemoveProductCommandTest.testExecute_Valid()");
		TestProductQueries.removeAllProducts();
		Integer id = TestProductQueries.createTestProduct();
		assertTrue(TestProductQueries.isProductsExistsById(id));
				
		assertTrue(command.execute(id.toString()));
		assertFalse(TestProductQueries.isProductsExistsById(id));
		
		id = TestProductQueries.createTestProduct();
		assertTrue(TestProductQueries.isProductsExistsById(id));
		id = TestProductQueries.createTestProduct();
		assertTrue(TestProductQueries.isProductsExistsById(id));

		assertTrue(command.execute((--id).toString()+ " "+ id.toString()));
		assertFalse(TestProductQueries.isProductsExistsById(id));
	}

	@Test
	public void testExecute_InvalidId() throws Exception {
		System.out.println("RemoveProductCommandTest.testExecute_InvalidId()");
		Integer id = TestProductQueries.createTestProduct();
		assertFalse(TestProductQueries.isProductsExistsById(++id));
		assertTrue(command.execute(id.toString()));	
	}
	
	@Test
	public void testExecute_StringId() throws Exception {
		System.out.println("RemoveProductCommandTest.testExecute_StringId()");
		assertFalse(command.execute("sdfa"));	
	}
	
	@Test
	public void testExecute_NoParams() throws Exception {
		System.out.println("RemoveProductCommandTest.testExecute_NoParams()");
		assertFalse(command.execute(""));
		assertEquals(CommandsErrors.INVALID_LIST, CommandsErrors.getLastError());
	}

}
