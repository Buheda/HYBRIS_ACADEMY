package tests.core.commands;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.commands.Command;
import core.commands.CreateProductCommand;
import core.persistent.CommandsErrors;

public class CreateProductCommandTest {

	class CommandAdapter extends CreateProductCommand implements Command {
		
		@Override
		public boolean isSpecificParamsValuesValid() {
			return super.isSpecificParamsValuesValid();
		}
				
		@Override
		public boolean isParamsValid(String paramsList[]) {
			return super.isParamsValid(paramsList);
		}		
	};
	
	private static final String paramsList[] = {"name", "price", "status"};
	CommandAdapter command = new CommandAdapter();
	HashMap<String, String> cmdParamsList;
	
    @Before
    public void init() {
	   cmdParamsList = new HashMap<>();
    }
  
    @After
    public void deinit() {
        cmdParamsList.clear();
    }
    
	@Test
	public void testIsSpecificParamsValid() {
		command.setParams(cmdParamsList);
		cmdParamsList.put("price", "10");

		cmdParamsList.put("status", "0");
		assertTrue(command.isSpecificParamsValuesValid());
		
		cmdParamsList.put("price", "0");
		assertTrue(command.isSpecificParamsValuesValid());
		
		cmdParamsList.put("status", "1");
		assertTrue(command.isSpecificParamsValuesValid());
		
		cmdParamsList.put("status", "2");
		assertTrue(command.isSpecificParamsValuesValid());
	}
	
	@Test
	public void testisSpecificParamsValid_False() {
		command.setParams(cmdParamsList);
		
		assertFalse(command.isSpecificParamsValuesValid());
		
		cmdParamsList.put("price", "fgd");
		assertFalse(command.isSpecificParamsValuesValid());
		
		cmdParamsList.put("price", "10");
		cmdParamsList.put("status", "3");
		assertFalse(command.isSpecificParamsValuesValid());
		
		cmdParamsList.put("status", "-1");		
		assertFalse(command.isSpecificParamsValuesValid());
	}
	
	@Test
	public void testExecute_Valid() throws Exception {
		String name = "n";
		cmdParamsList.put("name", name);
		String price = "10";
		cmdParamsList.put("price", price);
		cmdParamsList.put("status", "0");
		command.setParams(cmdParamsList);
		
		assertTrue(command.execute());
		
		cmdParamsList.put("status", "1");
		assertTrue(command.execute());
		
		cmdParamsList.put("status", "2");
		assertTrue(command.execute());
	}

	@Test
	public void testExecute_Invalid() throws Exception {
		cmdParamsList.put("name", "n");
		cmdParamsList.put("price", "sdf");
		cmdParamsList.put("status", "0");
		command.setParams(cmdParamsList);
		
		assertFalse(command.execute());
		assertEquals(CommandsErrors.INVALID_VALUE, CommandsErrors.getLastError());
	}
	
	@Test
	public void testIsParamsValid() {
		cmdParamsList.put("name", "n");
		cmdParamsList.put("price", "10");
		cmdParamsList.put("status", "0");
		command.setParams(cmdParamsList);
		
		assertTrue(command.isParamsValid(paramsList));
	}
	
	@Test
	public void testIsParamsValid_NoParams() {
		command.setParams(null);
		assertFalse(command.isParamsValid(paramsList));
		assertEquals(CommandsErrors.INVALID_PARAMETERS, CommandsErrors.getLastError());
	}
	
	@Test
	public void testIsParamsValid_InvalidName() {
		cmdParamsList.put("price", "10");
		cmdParamsList.put("status", "1");
		command.setParams(cmdParamsList);

		assertFalse(command.isParamsValid(paramsList));
		assertEquals(CommandsErrors.INVALID_KEY, CommandsErrors.getLastError());
		
		cmdParamsList.put("name", "");
		command.setParams(cmdParamsList);
		assertFalse(command.isParamsValid(paramsList));
		assertEquals(CommandsErrors.INVALID_VALUE, CommandsErrors.getLastError());

	}
		
	@Test
	public void testIsParamsValid_InvalidStatus() {
		cmdParamsList.put("name", "asd");
		cmdParamsList.put("price", "10");
		command.setParams(cmdParamsList);

		assertFalse(command.isParamsValid(paramsList));
		assertEquals(CommandsErrors.INVALID_KEY, CommandsErrors.getLastError());

		cmdParamsList.put("status", "");
		assertFalse(command.isParamsValid(paramsList));
		assertEquals(CommandsErrors.INVALID_VALUE, CommandsErrors.getLastError());
	}
	
	@Test
	public void testIsParamsValid_InvalidPrice() {
		cmdParamsList.put("name", "dsf");
		cmdParamsList.put("status", "1");
		command.setParams(cmdParamsList);

		assertFalse(command.isParamsValid(paramsList));
		assertEquals(CommandsErrors.INVALID_KEY, CommandsErrors.getLastError());

		cmdParamsList.put("price", "");
		assertFalse(command.isParamsValid(paramsList));
		assertEquals(CommandsErrors.INVALID_VALUE, CommandsErrors.getLastError());
		
		cmdParamsList.put("price", "af");
		assertFalse(command.isParamsValid(paramsList));
		assertEquals(CommandsErrors.INVALID_VALUE, CommandsErrors.getLastError());
	}	
}
