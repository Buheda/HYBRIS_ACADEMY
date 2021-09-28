package tests.core.commands;

import static org.junit.Assert.*;

import java.security.InvalidKeyException;
import java.util.HashMap;

import javax.management.InvalidAttributeValueException;
import javax.naming.directory.InvalidAttributesException;
import javax.security.sasl.AuthenticationException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.commands.Command;
import core.commands.RemoveAllProductsCommand;

public class RemoveAllProductsTest {
	
	class CommandAdapter extends RemoveAllProductsCommand implements Command {
		
		@Override
		public boolean isSpecificParamsIsValid() {
			return super.isSpecificParamsIsValid();
		}
		
		@Override
		public void checkParams(String paramsList[]) throws Exception {
			super.checkParams(paramsList);
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
	public void testIsSpecificParamsIsValid() {
		assertTrue(command.isSpecificParamsIsValid());
	}

	@Test(expected = AuthenticationException.class)
	public void testExecute_incorrectPassword() throws Exception {
		TestProductQueries.createTestProduct();
		cmdParamsList.put("password", "fa");
		command.setParams(cmdParamsList);
		command.execute();
	}

	@Test
	public void testExecute_Valid() throws Exception {
		TestProductQueries.createTestProduct();
		cmdParamsList.put("password", persistent.FinalProperties.REMOVE_ALL_PRODUCTS_PASSWORD);
		command.setParams(cmdParamsList);
		
		command.execute();
		assertFalse(TestProductQueries.isProductsExists());
	}
	
	@Test
	public void testExecute_Valid_noRecordsInDB() throws Exception {
		TestProductQueries.removeAllProducts();
		cmdParamsList.put("password", persistent.FinalProperties.REMOVE_ALL_PRODUCTS_PASSWORD);
		command.setParams(cmdParamsList);
		
		command.execute();
		assertFalse(TestProductQueries.isProductsExists());
	}
	
	@Test
	public void testCheckParams_Valid() throws Exception {
		cmdParamsList.put("password", "fa");
		command.setParams(cmdParamsList);
		
		command.checkParams(paramsList);
	}
	
	@Test(expected = InvalidAttributesException.class)
	public void testCheckParams_NoParams() throws Exception {
		command.setParams(null);
		
		command.checkParams(paramsList);
	}

	@Test(expected = InvalidKeyException.class)
	public void testCheckParams_MissingPassword_no_Params() throws Exception {
		command.setParams(cmdParamsList);

		command.checkParams(paramsList);
	}
	
	@Test(expected = InvalidAttributeValueException.class)
	public void testCheckParams_EmptyPassword() throws Exception {
		cmdParamsList.put("password", "");
		command.setParams(cmdParamsList);
		
		command.checkParams(paramsList);
	}
	

}
