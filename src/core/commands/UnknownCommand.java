package core.commands;

public class UnknownCommand extends BaseCommandImp implements Command {

	@Override
	protected boolean isParamsValid() {
		return true;
	}
	
	@Override
	public boolean execute() {
		System.err.println("Unknown command, see Help");
		return false;
	}

}

