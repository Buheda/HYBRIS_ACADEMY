package tests.core.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import core.commands.Command;
import core.commands.UnknownCommand;
import core.persistent.CommandsErrors;

public class UnknownCommandTest {
	
	class CommandAdapter extends UnknownCommand implements Command {
		
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
	public void testIsSpecificParamsValuesValid() {
		assertTrue(command.isSpecificParamsValuesValid());
	}

	@Test
	public void testExecute() throws Exception {
		assertTrue(command.execute());
	}

	@Test
	public void testisParamsValid(){
		assertFalse(command.isParamsValid(null));
		assertEquals(CommandsErrors.INVALID_PARAMETERS, CommandsErrors.getLastError());
	}
}
