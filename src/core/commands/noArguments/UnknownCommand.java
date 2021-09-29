package core.commands.noArguments;

import core.commands.Command;

public class UnknownCommand implements Command {
	
	@Override
	public boolean execute(String argsString) throws Exception {
		System.err.println("Unknown command, see Help");
		return true;
	}

}

