package core.commands.listedArguments;

import java.util.ArrayList;
import java.util.Arrays;

import core.commands.Command;
import core.persistent.CommandsErrors;
import core.util.StringUtil;

public abstract class BaseCommand_ArgumentsList implements Command {

	protected ArrayList<String> params;
	protected abstract boolean isParamsValuesValid();
	protected abstract boolean executeCommand() throws Exception;
	
	@Override
	final public boolean execute(String argsString) throws Exception {
		if (!StringUtil.isEmptyString(argsString)) {
			params = new ArrayList<String>(Arrays.asList(argsString.split(" ")));
			if (isParamsValuesValid()) {
				return executeCommand();
			};
		} 	
		
		CommandsErrors.showCommandsErrorsMessage(CommandsErrors.INVALID_LIST);
		return false; 

	}	
}
