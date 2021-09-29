package core.commands;

public interface Command {
	
	public boolean execute(String argsString) throws Exception;
}
