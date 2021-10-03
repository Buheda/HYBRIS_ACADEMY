package tests.core.commands.mappedArguments;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import core.commands.mappedArguments.CreateProductCommand;
import core.persistent.CommandsErrors;
import tests.core.commands.TestProductQueries;

public class CreateProductCommandTest {
	
	CreateProductCommand command = new CreateProductCommand();
	
	@Before
	public void before() throws Exception {
		TestProductQueries.cleanDB();
	}
	
	@AfterClass
	public static void AfterClass() throws Exception {
		TestProductQueries.cleanDB();
	}
	
	@Test
	public void testExecute_True() throws Exception {
		assertTrue(command.execute("--name n --price 10 --status in_stock"));
		assertTrue(command.execute("--price 10 --name n     --status out_of_stock"));
		assertTrue(command.execute(" --status running_low --price 10 --name n "));
	}
	
	@Test
	public void testExecute_False_InvalidParams() throws Exception {
		assertFalse(command.execute("--name n"));
		assertEquals(CommandsErrors.INVALID_KEY, CommandsErrors.getLastError());
		assertFalse(command.execute("--price 10"));
		assertEquals(CommandsErrors.INVALID_KEY, CommandsErrors.getLastError());
		assertFalse(command.execute("--status in_stock"));
		assertEquals(CommandsErrors.INVALID_KEY, CommandsErrors.getLastError());
		
		assertFalse(command.execute("--name n --price sdf --status out_of_stock"));
		assertEquals(CommandsErrors.INVALID_VALUE, CommandsErrors.getLastError());
	}
	
	@Test
	public void testExecute_False_NotEmptyCommandStr_EmptyParams() throws Exception {
		assertFalse(command.execute("param"));
		assertEquals(CommandsErrors.INVALID_PARAMETERS, CommandsErrors.getLastError());
	}
	
	@Test
	public void testExecute_False_NoParams() throws Exception {
		assertFalse(command.execute(""));
		assertEquals(CommandsErrors.INVALID_PARAMETERS, CommandsErrors.getLastError());

		assertFalse(command.execute("name"));
		assertEquals(CommandsErrors.INVALID_PARAMETERS, CommandsErrors.getLastError());

		assertFalse(command.execute("name"));
		assertEquals(CommandsErrors.INVALID_PARAMETERS, CommandsErrors.getLastError());

	}
	
	@Test
	public void testExecute_False_InvalidName() throws Exception {
		assertFalse(command.execute("--price sdf --status 1"));
		assertEquals(CommandsErrors.INVALID_KEY, CommandsErrors.getLastError());
		
		assertFalse(command.execute("--name --price sdf --status out_of_stock"));
		assertEquals(CommandsErrors.INVALID_VALUE, CommandsErrors.getLastError());
	}
		
	@Test
	public void testExecute_False_InvalidStatus() throws Exception {
		assertFalse(command.execute("--name sdf --price 10"));
		assertEquals(CommandsErrors.INVALID_KEY, CommandsErrors.getLastError());

		assertFalse(command.execute("--price 10 --name n     --status fdgdfg"));
		assertEquals(CommandsErrors.INVALID_VALUE, CommandsErrors.getLastError());

		assertFalse(command.execute("--name sdf --status --price 10"));
		assertEquals(CommandsErrors.INVALID_VALUE, CommandsErrors.getLastError());
		
		assertFalse(command.execute("--name sdf --price 10 --status"));
		assertEquals(CommandsErrors.INVALID_VALUE, CommandsErrors.getLastError());
	}
	
	@Test
	public void testExecute_False_InvalidPrice() throws Exception {
		assertFalse(command.execute("--name sdf --status in_stock"));
		assertEquals(CommandsErrors.INVALID_KEY, CommandsErrors.getLastError());

		assertFalse(command.execute("--name sdf --status --price 10"));
		assertEquals(CommandsErrors.INVALID_VALUE, CommandsErrors.getLastError());
		
		assertFalse(command.execute("--name sdf --price 10 --status"));
		assertEquals(CommandsErrors.INVALID_VALUE, CommandsErrors.getLastError());
	}
	
}
