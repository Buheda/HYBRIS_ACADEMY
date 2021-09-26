package db.queries;

import java.util.HashMap;

public interface Command {
	
	public boolean isParamsOk(HashMap<String, String> params);
	public boolean execute(HashMap<String,String> params) throws Exception;

}
