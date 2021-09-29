package core.commands.listedArguments;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.commands.Command;
import core.persistent.CommandsErrors;
import core.util.StringUtil;

public abstract class BaseCommand_ArgumentsList implements Command {

	protected ArrayList<String> params;
	protected abstract boolean isParamsValuesValid();
	protected abstract boolean executeCommand() throws Exception;
	
	protected static ArrayList<String> strToList(String str){
		 //regex for parsing (--param arg) or (--param)
		String regex = "((\\S+)|(\\S+$))";
		Pattern pattern = Pattern.compile(regex);
		Matcher m  = pattern.matcher(str);
		ArrayList<String> argsArr = new ArrayList<String>();
		while(m.find()) {
			argsArr.add(m.group());
		}	
		return argsArr;
	}
	
	@Override
	final public boolean execute(String argsString) throws Exception {
		if (!StringUtil.isEmptyString(argsString)) {
			params = strToList(argsString);
			if (isParamsValuesValid()) {
				return executeCommand();
			};
		} 	
		
		CommandsErrors.showCommandsErrorsMessage(CommandsErrors.INVALID_LIST);
		return false; 

	}	
}
