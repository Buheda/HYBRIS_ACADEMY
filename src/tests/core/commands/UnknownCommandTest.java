package tests.core.commands;

import static org.junit.Assert.assertTrue;

import javax.naming.directory.InvalidAttributesException;

import org.junit.Test;

import core.commands.Command;
import core.commands.UnknownCommand;

public class UnknownCommandTest {
	
	class CommandAdapter extends UnknownCommand implements Command {
		
		@Override
		public boolean isSpecificParamsIsValid() {
			return super.isSpecificParamsIsValid();
		}
		
		@Override
		public void checkParams(String paramsList[]) throws Exception {
			super.checkParams(paramsList);
		}		
	};
	
	CommandAdapter command = new CommandAdapter();

	@Test
	public void testIsSpecificParamsIsValid() {
		assertTrue(command.isSpecificParamsIsValid());
	}

	@Test
	public void testExecute() throws Exception {
		command.execute();
	}

	@Test(expected = InvalidAttributesException.class)
	public void testCheckParams() throws Exception {
		command.checkParams(null);
	}

}
