package core.commands;

public class ExitApplicationCommand extends BaseCommandImp implements Command {

	@Override
	protected boolean isSpecificParamsIsValid() {
		return true;
	}
	
	@Override
	public void execute() {
		System.exit(0);	
	}
}
