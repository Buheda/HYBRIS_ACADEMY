package core.commands;

public class UnknownCommand extends BaseCommandImp implements Command {

	@Override
	protected boolean isSpecificParamsIsValid() {
		return true;
	}
	
	@Override
	public void execute() {
		System.err.println("Unknown command, see Help");
	}

}

