package tests.core.commands.listedArguments;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import core.commands.listedArguments.CreateOrderCommand;
import core.persistent.CommandsErrors;
import tests.core.commands.TestProductQueries;

public class CreateOrderCommandTest {
	
	CreateOrderCommand command = new CreateOrderCommand();

	@Before
	public void before() throws Exception {
		TestProductQueries.cleanDB();
	}
	
	@AfterClass
	public static void AfterClass() throws Exception {
		TestProductQueries.cleanDB();
	}
	
	@Test
	public void testExecute_True_FewIds() throws Exception {
		StringBuffer cmdParamd = new StringBuffer("");
		Integer id = TestProductQueries.createTestProduct();
		assertTrue(TestProductQueries.isProductsExistsById(id));
		cmdParamd.append("   "+id.toString());
		
		id = TestProductQueries.createTestProduct();
		assertTrue(TestProductQueries.isProductsExistsById(id));
		cmdParamd.append(" "+id.toString());
		
		assertTrue(command.execute(cmdParamd.toString()));
	}

	@Test
	public void testExecute_True_DubbleProducts() throws Exception {
		Integer id = TestProductQueries.createTestProduct();
		assertTrue(TestProductQueries.isProductsExistsById(id));

		assertTrue(command.execute(id.toString()+"   "+id.toString()));
	}
	
	@Test
	public void testExecute_False_NoParams() throws Exception {
		assertFalse(command.execute(""));
		assertEquals(CommandsErrors.INVALID_LIST, CommandsErrors.getLastError());
	}
	
	@Test
	public void testExecute_False_String() throws Exception {
		assertFalse(command.execute("ds"));
		assertEquals(CommandsErrors.INVALID_LIST, CommandsErrors.getLastError());
	}
	
	@Test
	public void testExecute_False_NotExistedId() throws Exception {
		Integer id = TestProductQueries.createTestProduct();
		assertTrue(TestProductQueries.isProductsExistsById(id));
		assertFalse(command.execute((++id).toString()));
	}
}
