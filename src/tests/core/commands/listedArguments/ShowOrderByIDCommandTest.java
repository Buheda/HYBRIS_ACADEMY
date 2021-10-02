package tests.core.commands.listedArguments;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import core.commands.listedArguments.ShowOrderByIDCommand;
import core.db.entity.Order_items;
import core.persistent.CommandsErrors;
import tests.core.commands.TestProductQueries;

public class ShowOrderByIDCommandTest {

	ShowOrderByIDCommand command = new ShowOrderByIDCommand();
	static int orderId = -1;

	@BeforeClass
	public static void beforeClass() throws Exception {
		TestProductQueries.cleanDB();
		orderId = TestProductQueries.createTestOrder();
		TestProductQueries.createTestOrderItem(new Order_items(orderId, TestProductQueries.createTestProduct(), 1));
		TestProductQueries.createTestOrderItem(new Order_items(orderId, TestProductQueries.createTestProduct(), 2));
	}
	
	@AfterClass
	public static void AfterClass() throws Exception {
		TestProductQueries.cleanDB();
	}
	
	@Test
	public void testExecute_Valid() throws Exception {
		assertTrue(command.execute(Integer.toString(orderId)));
		assertTrue(command.execute(orderId+ " 12345"));
		
		assertTrue(command.execute(Integer.toString(orderId)+" szfds"));
	}

	@Test
	public void testExecute_Invalid_Id() throws Exception {
		assertFalse(command.execute(Integer.toString(++orderId)));
	}
	
	@Test
	public void testExecute_Invalid_StringId() throws Exception {
		assertFalse(command.execute("sdfa"));
		assertEquals(CommandsErrors.INVALID_LIST, CommandsErrors.getLastError());

		assertFalse(command.execute("sdfa "+Integer.toString(orderId)));
		assertEquals(CommandsErrors.INVALID_LIST, CommandsErrors.getLastError());
	}
	
	@Test
	public void testExecute_NoParams() throws Exception {
		assertFalse(command.execute(""));
		assertEquals(CommandsErrors.INVALID_LIST, CommandsErrors.getLastError());
	}

}
