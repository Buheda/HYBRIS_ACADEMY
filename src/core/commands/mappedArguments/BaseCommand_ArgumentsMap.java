package core.commands.mappedArguments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.commands.Command;
import core.persistent.CommandsErrors;
import core.util.StringUtil;

public abstract class BaseCommand_ArgumentsMap implements Command {
	
	protected HashMap<String, String> params;
	
	protected abstract HashSet<String> getCommandParamsSet(); 
	protected abstract boolean isParamsValuesValid();
	protected abstract boolean executeCommand() throws Exception;
		
	protected static ArrayList<String> strToList(String str){
		 //regex for parsing (--param arg) or (--param)
		String regex = "(--\\S+\\s+[^(--|\\s+)]+)|(--\\S+[^\\s]+)";
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
			ArrayList<String> argsArr = strToList(argsString);
			if (!argsArr.isEmpty()) {
				params = new HashMap<>();
				Set<String> paramsSet = getCommandParamsSet();
				for (int i=0;i<argsArr.size();i++) {
					String[] pair = argsArr.get(i).split(" ");	
					if (2 !=pair.length ||  StringUtil.isEmptyString(pair[1].trim())) {
						CommandsErrors.showCommandsErrorsMessage(CommandsErrors.INVALID_VALUE, pair[0].trim());
						return false;
					}
					
					paramsSet.remove(pair[0].trim());
					params.put(pair[0].trim(), pair[1].trim());
				}
				if (!paramsSet.isEmpty()) {
					CommandsErrors.showCommandsErrorsMessage(CommandsErrors.INVALID_KEY, paramsSet.iterator().next().toString());
					return false;
				}
				if (!isParamsValuesValid()){
					CommandsErrors.showCommandsErrorsMessage(CommandsErrors.INVALID_VALUE);
					return false;
				}
				return executeCommand();
			} 
		}
		CommandsErrors.showCommandsErrorsMessage(CommandsErrors.INVALID_PARAMETERS);
		return false;
	}
}
