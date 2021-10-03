package tests.core.commands.mappedArguments;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import core.commands.mappedArguments.UpdateOrderCommand;
import core.db.entity.Order_items;
import core.persistent.CommandsErrors;
import tests.core.commands.TestProductQueries;

public class UpdateOrderCommandTest {
	
	UpdateOrderCommand command = new UpdateOrderCommand();
	static int orderId = -1;
	
	@Before
	public void before() throws Exception {
		TestProductQueries.cleanDB();
		orderId = TestProductQueries.createTestOrder();
	}
	
	@AfterClass
	public static void AfterClass() throws Exception {
		TestProductQueries.cleanDB();
	}
	
	@Test
	public void testExecuteCommand_True() throws Exception {
		int productId1 = TestProductQueries.createTestProduct();
		int productId2 = TestProductQueries.createTestProduct();
		
		TestProductQueries.createTestOrderItem(new Order_items(orderId, productId1, 1));
		TestProductQueries.createTestOrderItem(new Order_items(orderId, productId2, 2));
		
		assertTrue(command.execute("--orderid "+orderId+" --productid "+productId1+" --quantity 20"));
		assertTrue(command.execute("--orderid "+orderId+" --productid "+productId2+" --quantity 40"));
		assertEquals(20, TestProductQueries.getOrderItemsQuantity(orderId, productId1));
		assertEquals(40, TestProductQueries.getOrderItemsQuantity(orderId, productId2));
	}

	@Test
	public void testExecute_False_NoParams() throws Exception {
		assertFalse(command.execute(""));
		assertEquals(CommandsErrors.INVALID_PARAMETERS, CommandsErrors.getLastError());
	}
	
	@Test
	public void testExecute_False_InvalidOrderId() throws Exception {
		assertFalse(command.execute("--productid 0 --quantity 0"));
		assertEquals(CommandsErrors.INVALID_KEY, CommandsErrors.getLastError());
		
		assertFalse(command.execute("--orderid --productid 0 --quantity 0"));
		assertEquals(CommandsErrors.INVALID_VALUE, CommandsErrors.getLastError());
		
		assertFalse(command.execute("--orderid jkk --productid 0 --quantity 0"));
		assertEquals(CommandsErrors.INVALID_VALUE, CommandsErrors.getLastError());
	}
	
	@Test
	public void testExecute_False_InvalidProductId() throws Exception {
		assertFalse(command.execute("--orderid 0 --quantity 0"));
		assertEquals(CommandsErrors.INVALID_KEY, CommandsErrors.getLastError());
		
		assertFalse(command.execute("--orderid 0 --productid --quantity 0"));
		assertEquals(CommandsErrors.INVALID_VALUE, CommandsErrors.getLastError());
		
		assertFalse(command.execute("--orderid 0 --productid sfgsfg --quantity 0"));
		assertEquals(CommandsErrors.INVALID_VALUE, CommandsErrors.getLastError());
	}
	
	@Test
	public void testExecute_False_InvalidQuantity() throws Exception {
		assertFalse(command.execute("--orderid 0 --productid 0"));
		assertEquals(CommandsErrors.INVALID_KEY, CommandsErrors.getLastError());
		
		assertFalse(command.execute("--orderid 0 --productid 0 --quantity"));
		assertEquals(CommandsErrors.INVALID_VALUE, CommandsErrors.getLastError());
		
		assertFalse(command.execute("--orderid 0 --productid 0 --quantity fgjg"));
		assertEquals(CommandsErrors.INVALID_VALUE, CommandsErrors.getLastError());
	}
	
	@Test
	public void testExecute_False_NothingToUpdate() throws Exception {		
		assertFalse(command.execute("--orderid -1 --productid -1 --quantity 10"));
	}
}
