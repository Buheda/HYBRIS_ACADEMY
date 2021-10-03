package core.commands.listedArguments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import core.commands.Command;
import core.persistent.CommandsErrors;
import core.util.StringUtil;

public abstract class BaseCommand_ArgumentsList implements Command {

	protected List<String> params;
	protected abstract boolean isParamsValuesValid();
	protected abstract boolean executeCommand() throws Exception;
	
	@Override
	final public boolean execute(String argsString) throws Exception {
		if (!StringUtil.isEmptyString(argsString)) {
			String[] words = argsString.trim().split("\\s+");
			params = new ArrayList<String>(Arrays.asList(words));
			if (isParamsValuesValid()) {
				return executeCommand();
			};
		} 	
		
		CommandsErrors.showCommandsErrorsMessage(CommandsErrors.INVALID_LIST);
		return false; 

	}	
}
