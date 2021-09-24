package db.queries;

public class ExitApplicationCommand implements Command {

	@Override
	public boolean execute() {
		System.exit(0);	
		return true;
	}

}
