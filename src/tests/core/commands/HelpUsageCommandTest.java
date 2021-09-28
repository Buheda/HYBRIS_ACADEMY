package tests.core.commands;

import static org.junit.Assert.*;

import javax.naming.directory.InvalidAttributesException;

import org.junit.Test;

import core.commands.Command;
import core.commands.HelpUsageCommand;

public class HelpUsageCommandTest {
	class CommandAdapter extends HelpUsageCommand implements Command {
		
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
	public void testIsSpecificParamsValid(){
		assertTrue(command.isSpecificParamsIsValid());
	}
 
	@Test
	public void testExecute() {
		command.execute();
	}
	
	@Test(expected = InvalidAttributesException.class)
	public void testCheckParams() throws Exception {
		command.checkParams(null);
	}

}
