package core.commands;

import java.util.HashMap;

public class UnknownCommand implements Command {

	@Override
	public boolean isParamsOk(HashMap<String, String> params) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean execute(HashMap<String, String> params) {
		System.err.println("Unknown command, see Help");
		return false;
	}

}

