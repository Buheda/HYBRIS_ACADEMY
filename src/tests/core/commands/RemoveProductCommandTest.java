package tests.core.commands;

import static org.junit.Assert.*;

import java.security.InvalidKeyException;
import java.util.HashMap;

import javax.management.InvalidAttributeValueException;
import javax.naming.directory.InvalidAttributesException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import core.commands.Command;
import core.commands.RemoveProductCommand;

public class RemoveProductCommandTest {
	
	class CommandAdapter extends RemoveProductCommand implements Command {
		
		@Override
		public boolean isSpecificParamsIsValid() {
			return super.isSpecificParamsIsValid();
		}
		
		@Override
		public void checkParams(String paramsList[]) throws Exception {
			super.checkParams(paramsList);
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
	public void testIsSpecificParamsIsValid() {
		cmdParamsList.put("id", "1");
		command.setParams(cmdParamsList);
		assertTrue(command.isSpecificParamsIsValid());
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
	public void testCheckParams_Valid() throws Exception {
		cmdParamsList.put("id", "10");
		command.setParams(cmdParamsList);
		
		command.checkParams(paramsList);
	}
	
	@Test(expected = InvalidAttributesException.class)
	public void testCheckParams_NoParams() throws Exception {
		command.setParams(null);
		
		command.checkParams(paramsList);
	}

	@Test(expected = InvalidKeyException.class)
	public void testCheckParams_MIssingId() throws Exception {
		command.setParams(cmdParamsList);

		command.checkParams(paramsList);
	}
	
	@Test(expected = InvalidAttributeValueException.class)
	public void testCheckParams_EmptyId() throws Exception {
		cmdParamsList.put("id", "");
		command.setParams(cmdParamsList);
		
		command.checkParams(paramsList);
	}
}
