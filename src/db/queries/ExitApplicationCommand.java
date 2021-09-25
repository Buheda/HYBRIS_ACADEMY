package db.queries;

import java.util.HashMap;

public class ExitApplicationCommand implements Command {

	@Override
	public boolean execute(HashMap<String, String> params) {
		System.exit(0);	
		return true;
	}

}
