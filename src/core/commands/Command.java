package core.commands;

import java.util.HashMap;

public interface Command {
	
	public void setParams(HashMap<String, String> params);
	public boolean execute() throws Exception;


}
