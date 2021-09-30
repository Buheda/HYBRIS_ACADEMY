package core.commands;

public interface Command {
	
	default boolean execute() throws Exception {
		return execute("");
	}
	public boolean execute(String argsString) throws Exception;
}
