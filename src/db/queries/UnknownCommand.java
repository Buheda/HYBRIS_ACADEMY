package db.queries;

import java.util.HashMap;

public class UnknownCommand implements Command {

	@Override
	public boolean execute(HashMap<String, String> params) {
		System.err.println("Unknown command, see Help");
		return false;
	}

}

