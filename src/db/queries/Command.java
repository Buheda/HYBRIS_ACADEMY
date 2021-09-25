package db.queries;

import java.util.HashMap;

public interface Command {
	public boolean execute(HashMap<String,String> params);
}
