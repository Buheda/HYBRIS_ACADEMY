package tests.core.commands;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.commands.Command;
import core.commands.RemoveAllProductsCommand;
import core.persistent.CommandsErrors;

public class RemoveAllProductsTest {
	
	class CommandAdapter extends RemoveAllProductsCommand implements Command {
		
		@Override
		public boolean isSpecificParamsValuesValid() {
			return super.isSpecificParamsValuesValid();
		}
		
		@Override
		public boolean isParamsValid(String paramsList[]) {
			return super.isParamsValid(paramsList);
		}	
	};
	
	private static final String paramsList[] = {"password"};
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
		assertTrue(command.isSpecificParamsValuesValid());
	}

	@Test
	public void testExecute_Valid() throws Exception {
		TestProductQueries.createTestProduct();
		cmdParamsList.put("password", core.persistent.FinalProperties.REMOVE_ALL_PRODUCTS_PASSWORD);
		command.setParams(cmdParamsList);
		
		command.execute();
		assertFalse(TestProductQueries.isProductsExists());
	}
	
	@Test
	public void testExecute_Valid_noRecordsInDB() throws Exception {
		TestProductQueries.removeAllProducts();
		cmdParamsList.put("password", core.persistent.FinalProperties.REMOVE_ALL_PRODUCTS_PASSWORD);
		command.setParams(cmdParamsList);
		
		assertTrue(command.execute());
		assertFalse(TestProductQueries.isProductsExists());
	}
	
	@Test
	public void testExecute_InvalidPassword() throws Exception {
		TestProductQueries.createTestProduct();
		cmdParamsList.put("password", "fa");
		command.setParams(cmdParamsList);
		
		assertFalse(command.execute());
		assertEquals(CommandsErrors.INCORRECT_PASSWORD, CommandsErrors.getLastError());
	}
	
	@Test
	public void testIsParamsValid_Valid() {
		cmdParamsList.put("password", "fa");
		command.setParams(cmdParamsList);
		
		assertTrue(command.isParamsValid(paramsList));
	}
	
	@Test
	public void testIsParamsValid_NoParams(){
		command.setParams(null);
		assertFalse(command.isParamsValid(paramsList));
		assertEquals(CommandsErrors.INVALID_PARAMETERS, CommandsErrors.getLastError());
	}

	@Test
	public void testIsParamsValid_MissingPassword_no_Params() {
		command.setParams(cmdParamsList);

		assertFalse(command.isParamsValid(paramsList));
		assertEquals(CommandsErrors.INVALID_PARAMETERS, CommandsErrors.getLastError());
	}
	
	@Test
	public void testIsParamsValid_InvalidPassword() {
		cmdParamsList.put("dsf", "sdf");
		command.setParams(cmdParamsList);
		
		assertFalse(command.isParamsValid(paramsList));
		assertEquals(CommandsErrors.INVALID_KEY, CommandsErrors.getLastError());
		
		cmdParamsList.put("password", "");
		command.setParams(cmdParamsList);
		
		assertFalse(command.isParamsValid(paramsList));
		assertEquals(CommandsErrors.INVALID_VALUE, CommandsErrors.getLastError());
	}
}
