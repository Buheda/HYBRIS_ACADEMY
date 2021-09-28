package core.commands;

public class ExitApplicationCommand extends BaseCommandImp implements Command {

	@Override
	protected boolean isSpecificParamsValuesValid() {
		return true;
	}
	
	@Override
	public boolean execute() {
		System.exit(0);
		return true;	
	}
}
