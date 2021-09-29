package tests.core.commands;

import static org.junit.Assert.*;

import org.junit.Test;

import core.commands.listedArguments.CreateOrderCommand;
import core.persistent.CommandsErrors;

public class CreateOrderCommandTest {
	
	CreateOrderCommand command = new CreateOrderCommand();

	@Test
	public void testExecute_Valid_FewIds() throws Exception {
		StringBuffer cmdParamd = new StringBuffer("");
		
		Integer id = TestProductQueries.createTestProduct();
		assertTrue(TestProductQueries.isProductsExistsById(id));
		cmdParamd.append(" "+id.toString());
		
		id = TestProductQueries.createTestProduct();
		assertTrue(TestProductQueries.isProductsExistsById(id));
		cmdParamd.append(" "+id.toString());
		
		assertTrue(command.execute(cmdParamd.toString()));
	}

	@Test
	public void testExecute_noParams() throws Exception {
		assertFalse(command.execute(""));
		assertEquals(CommandsErrors.INVALID_LIST, CommandsErrors.getLastError());
	}
	
	@Test
	public void testExecute_Invalid_String() throws Exception {
		assertFalse(command.execute("ds"));
		assertEquals(CommandsErrors.INVALID_LIST, CommandsErrors.getLastError());
	}
	
	@Test
	public void testExecute_Invalid_Id() throws Exception {
		Integer id = TestProductQueries.createTestProduct();
		assertTrue(TestProductQueries.isProductsExistsById(id));
		assertFalse(command.execute((++id).toString()));
	}
}
