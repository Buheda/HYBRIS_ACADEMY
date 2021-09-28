package tests.core.commands;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.commands.Command;
import core.commands.RemoveProductCommand;
import core.persistent.CommandsErrors;

public class RemoveProductCommandTest {
	
	class CommandAdapter extends RemoveProductCommand implements Command {
		
		@Override
		public boolean isSpecificParamsValuesValid() {
			return super.isSpecificParamsValuesValid();
		}
		
		@Override
		public boolean isParamsValid(String paramsList[]) {
			return super.isParamsValid(paramsList);
		}		
	};
	
	private static final String paramsList[] = {"id"};
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
	public void testIsSpecificParamsValuesValid() {
		cmdParamsList.put("id", "1");
		command.setParams(cmdParamsList);
		assertTrue(command.isSpecificParamsValuesValid());
	}

	@Test
	public void testExecute_Valid() throws Exception {
		TestProductQueries.removeAllProducts();
		Integer id = TestProductQueries.createTestProduct();
		assertTrue(TestProductQueries.isProductsExistsById(id));
		
		cmdParamsList.put("id", id.toString());
		command.setParams(cmdParamsList);
		
		command.execute();
		assertFalse(TestProductQueries.isProductsExistsById(id));
	}

	@Test
	public void testExecute_InvalidId() throws Exception {
		Integer id = TestProductQueries.createTestProduct();
		
		cmdParamsList.put("id", (++id).toString());
		command.setParams(cmdParamsList);
		
		command.execute();
		assertFalse(TestProductQueries.isProductsExistsById(id));
	}
	
	@Test
	public void testisParamsValid_Valid() {
		cmdParamsList.put("id", "10");
		command.setParams(cmdParamsList);
		
		assertTrue(command.isParamsValid(paramsList));
	}
	
	@Test
	public void testisParamsValid_NoParams() {
		command.setParams(null);
		
		assertFalse(command.isParamsValid(paramsList));
		assertEquals(CommandsErrors.INVALID_PARAMETERS, CommandsErrors.getLastError());
	}

	@Test
	public void testisParamsValid_InvalidId() {
		cmdParamsList.put("dsf", "sdf");
		command.setParams(cmdParamsList);
		
		assertFalse(command.isParamsValid(paramsList));
		assertEquals(CommandsErrors.INVALID_KEY, CommandsErrors.getLastError());
		
		cmdParamsList.put("id", "");
		command.setParams(cmdParamsList);
		
		assertFalse(command.isParamsValid(paramsList));
		assertEquals(CommandsErrors.INVALID_VALUE, CommandsErrors.getLastError());
	}
	

}
