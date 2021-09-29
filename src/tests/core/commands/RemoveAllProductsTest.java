package tests.core.commands;

import static org.junit.Assert.*;

import org.junit.Test;

import core.commands.listedArguments.RemoveAllProductsCommand;
import core.persistent.CommandsErrors;

public class RemoveAllProductsTest {
		
	RemoveAllProductsCommand command = new RemoveAllProductsCommand();

	@Test
	public void testExecute_Valid() throws Exception {		
		assertTrue(command.execute(core.persistent.FinalProperties.REMOVE_ALL_PRODUCTS_PASSWORD));
		assertFalse(TestProductQueries.isProductsExists());
	}
	
	@Test
	public void testExecute_Valid_noRecordsInDB() throws Exception {
		TestProductQueries.removeAllProducts();
		assertTrue(command.execute(core.persistent.FinalProperties.REMOVE_ALL_PRODUCTS_PASSWORD));
		assertFalse(TestProductQueries.isProductsExists());
	}
	
	@Test
	public void testExecute_InvalidPassword() throws Exception {
		TestProductQueries.createTestProduct();
		assertFalse(command.execute("fa"));
		assertEquals(CommandsErrors.INCORRECT_PASSWORD, CommandsErrors.getLastError());
	}
	
	@Test
	public void testExecute_NoParams() throws Exception{
		assertFalse(command.execute(""));
		assertEquals(CommandsErrors.INVALID_LIST, CommandsErrors.getLastError());
	}

	@Test
	public void testExecute_MissingPassword_no_Params() throws Exception {
		assertFalse(command.execute("--param abcd"));
		assertEquals(CommandsErrors.INCORRECT_PASSWORD, CommandsErrors.getLastError());
	}
}
