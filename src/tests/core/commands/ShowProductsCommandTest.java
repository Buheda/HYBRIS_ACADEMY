package tests.core.commands;

import static org.junit.Assert.*;

import org.junit.Test;

import core.commands.Command;
import core.commands.ShowProductsCommand;

public class ShowProductsCommandTest {
	class CommandAdapter extends ShowProductsCommand implements Command {
		
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
	public void testExecute() throws Exception {
		command.execute();
	}


}
