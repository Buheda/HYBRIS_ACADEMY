package tests.core.commands.listedArguments;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import core.commands.listedArguments.RemoveProductByIDCommand;
import core.persistent.CommandsErrors;
import tests.core.commands.TestProductQueries;

public class RemoveProductCommandTest {

	RemoveProductByIDCommand command = new RemoveProductByIDCommand();
		
	@Before
	public void before() throws Exception {
		TestProductQueries.cleanDB();
	}
	
	@AfterClass
	public static void AfterClass() throws Exception {
		TestProductQueries.cleanDB();
	}
	
	@Test
	public void testExecute_Valid() throws Exception {
		Integer id = TestProductQueries.createTestProduct();
		assertTrue(TestProductQueries.isProductsExistsById(id));
				
		assertTrue(command.execute(id.toString()));
		assertFalse(TestProductQueries.isProductsExistsById(id));
		
		id = TestProductQueries.createTestProduct();
		assertTrue(TestProductQueries.isProductsExistsById(id));
		id = TestProductQueries.createTestProduct();
		assertTrue(TestProductQueries.isProductsExistsById(id));

		assertTrue(command.execute(id.toString()+ " "+ (--id).toString()));
		assertFalse(TestProductQueries.isProductsExistsById(id));
	}

	@Test
	public void testExecute_InvalidId() throws Exception {
		Integer id = TestProductQueries.createTestProduct();
		assertFalse(TestProductQueries.isProductsExistsById(++id));
		assertTrue(command.execute(id.toString()));	
	}
	
	@Test
	public void testExecute_StringId() throws Exception {
		assertFalse(command.execute("sdfa"));
		assertEquals(CommandsErrors.INVALID_LIST, CommandsErrors.getLastError());
	}
	
	@Test
	public void testExecute_NoParams() throws Exception {
		assertFalse(command.execute(""));
		assertEquals(CommandsErrors.INVALID_LIST, CommandsErrors.getLastError());
	}

}
