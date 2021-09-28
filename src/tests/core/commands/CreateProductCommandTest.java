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
import core.commands.CreateProductCommand;

public class CreateProductCommandTest {

	class CommandAdapter extends CreateProductCommand implements Command {
		
		@Override
		public boolean isSpecificParamsIsValid() {
			return super.isSpecificParamsIsValid();
		}
				
		@Override
		public void checkParams(String paramsList[]) throws Exception {
			super.checkParams(paramsList);
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
	public void testIsSpecificParamsValid() throws Exception {
		command.setParams(cmdParamsList);
		cmdParamsList.put("price", "10");

		cmdParamsList.put("status", "0");
		assertTrue(command.isSpecificParamsIsValid());
		
		cmdParamsList.put("price", "0");
		assertTrue(command.isSpecificParamsIsValid());
		
		cmdParamsList.put("status", "1");
		assertTrue(command.isSpecificParamsIsValid());
		
		cmdParamsList.put("status", "2");
		assertTrue(command.isSpecificParamsIsValid());
	}
	
	@Test
	public void testisSpecificParamsValid_False() {
		command.setParams(cmdParamsList);
		
		assertFalse(command.isSpecificParamsIsValid());
		
		cmdParamsList.put("price", "fgd");
		assertFalse(command.isSpecificParamsIsValid());
		
		cmdParamsList.put("price", "10");
		cmdParamsList.put("status", "3");
		assertFalse(command.isSpecificParamsIsValid());
		
		cmdParamsList.put("status", "-1");		
		assertFalse(command.isSpecificParamsIsValid());
	}
	
	@Test
	public void testExecute_Valid() throws Exception {
		String name = "n";
		cmdParamsList.put("name", name);
		String price = "10";
		cmdParamsList.put("price", price);
		cmdParamsList.put("status", "0");
		command.setParams(cmdParamsList);
		
		command.execute();
		
		cmdParamsList.put("status", "1");
		command.execute();
		cmdParamsList.put("status", "2");
		command.execute();
	}

	@Test(expected = InvalidAttributeValueException.class)
	public void testExecute_Invalid() throws Exception {
		cmdParamsList.put("name", "n");
		cmdParamsList.put("price", "sdf");
		cmdParamsList.put("status", "0");
		command.setParams(cmdParamsList);
		
		command.execute();
	}
	
	@Test
	public void testCheckParams_Valid() throws Exception {
		cmdParamsList.put("name", "n");
		cmdParamsList.put("price", "10");
		cmdParamsList.put("status", "0");
		command.setParams(cmdParamsList);
		
		command.checkParams(paramsList);
	}
	@Test(expected = InvalidAttributesException.class)
	public void testCheckParams_NoParams() throws Exception {
		command.setParams(null);
		command.checkParams(paramsList);
	}
	
	@Test(expected = InvalidKeyException.class)
	public void testCheckParams_MissingName() throws Exception {
		cmdParamsList.put("price", "10");
		cmdParamsList.put("status", "1");
		command.setParams(cmdParamsList);

		command.checkParams(paramsList);
	}
	
	@Test(expected = InvalidAttributeValueException.class)
	public void testCheckParams_EmptyName() throws Exception {
		cmdParamsList.put("name", "");
		cmdParamsList.put("price", "10");
		cmdParamsList.put("status", "1");
		command.setParams(cmdParamsList);

		command.checkParams(paramsList);
	}
	
	@Test(expected = InvalidKeyException.class)
	public void testCheckParams_MissingStatus() throws Exception {
		cmdParamsList.put("name", "asd");
		cmdParamsList.put("price", "10");
		command.setParams(cmdParamsList);

		command.checkParams(paramsList);
	}
	
	@Test(expected = InvalidAttributeValueException.class)
	public void testCheckParams_EmptyStatus() throws Exception {
		cmdParamsList.put("name", "vs");
		cmdParamsList.put("price", "10");
		cmdParamsList.put("status", "");
		command.setParams(cmdParamsList);

		command.checkParams(paramsList);
	}
	
	@Test(expected = InvalidKeyException.class)
	public void testCheckParams_MissingPrice() throws Exception {
		cmdParamsList.put("name", "dsf");
		cmdParamsList.put("status", "1");
		command.setParams(cmdParamsList);

		command.checkParams(paramsList);
	}
	
	@Test(expected = InvalidAttributeValueException.class)
	public void testCheckParams_EmptyPrice() throws Exception {
		cmdParamsList.put("name", "ns");
		cmdParamsList.put("price", "");
		cmdParamsList.put("status", "2");
		command.setParams(cmdParamsList);
		
		command.checkParams(paramsList);
	}
	
}
