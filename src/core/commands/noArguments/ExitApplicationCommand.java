package core.commands.noArguments;

import apps.ShopApplication;
import core.commands.Command;

public class ExitApplicationCommand implements Command {

	@Override
	public boolean execute(String argsString) throws Exception {
		ShopApplication.shutdownApplication();
		return true;
	}
	

}
