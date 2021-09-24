package db.queries;

public class UnknownCommand implements Command {

	@Override
	public boolean execute() {
		System.err.println("Unknown command");
		return false;
	}

}

