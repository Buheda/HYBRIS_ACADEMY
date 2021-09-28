package tests.core.commands;

import static org.junit.Assert.*;

import org.junit.Test;

import core.commands.Command;
import core.commands.ShowProductsCommand;
import core.persistent.CommandsErrors;

public class ShowProductsCommandTest {
	class CommandAdapter extends ShowProductsCommand implements Command {
		
		@Override
		public boolean isSpecificParamsValuesValid() {
			return super.isSpecificParamsValuesValid();
		}
				
		@Override
		public boolean isParamsValid(String paramsList[]) {
			return super.isParamsValid(paramsList);
		}		
	};
	CommandAdapter command = new CommandAdapter();

	@Test
	public void testIsSpecificParamsValid(){
		assertTrue(command.isSpecificParamsValuesValid());
	}
	
	@Test
	public void testExecute() throws Exception {
		TestProductQueries.createTestProduct();
		assertTrue(command.execute());
	}

	@Test
	public void testisParamsValid() {
		assertFalse(command.isParamsValid(null));
		assertEquals(CommandsErrors.INVALID_PARAMETERS, CommandsErrors.getLastError());
	}
	
}
