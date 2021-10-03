package tests.core.commands.listedArguments;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import core.commands.listedArguments.RemoveAllProductsCommand;
import core.persistent.CommandsErrors;
import tests.core.commands.TestProductQueries;

public class RemoveAllProductsTest {
		
	RemoveAllProductsCommand command = new RemoveAllProductsCommand();

	@Before
	public void before() throws Exception {
		TestProductQueries.cleanDB();
		
		TestProductQueries.createTestProduct();
		assertTrue(TestProductQueries.isProductsExists());
	}
	
	@AfterClass
	public static void AfterClass() throws Exception {
		TestProductQueries.cleanDB();
	}
	
	@Test
	public void testExecute_True() throws Exception {
		assertTrue(command.execute(core.persistent.FinalProperties.REMOVE_ALL_PRODUCTS_PASSWORD));
		assertFalse(TestProductQueries.isProductsExists());
	}
	
	@Test
	public void testExecute_False_noRecordsInDB() throws Exception {
		TestProductQueries.removeAllProducts();
		assertFalse(TestProductQueries.isProductsExists());
	}
	
	@Test
	public void testExecute_False_InvalidPassword() throws Exception {
		assertFalse(command.execute("fa"));
	}
	
	@Test
	public void testExecute_False_NoParams() throws Exception{
		assertFalse(command.execute(""));
		assertEquals(CommandsErrors.INVALID_LIST, CommandsErrors.getLastError());
	}

	@Test
	public void testExecute_False_MissingPassword() throws Exception {
		assertFalse(command.execute("--param abcd"));
	}
}
