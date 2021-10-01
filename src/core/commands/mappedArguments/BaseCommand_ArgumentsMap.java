package core.commands.mappedArguments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.commands.Command;
import core.persistent.CommandsErrors;
import core.util.StringUtil;

public abstract class BaseCommand_ArgumentsMap implements Command {
	
	protected Map<String, String> params;
	
	protected abstract Set<String> getCommandParamsSet(); 
	protected abstract boolean isParamsValuesValid();
	protected abstract boolean executeCommand() throws Exception;
		
	protected ArrayList<String> strToList(String str){
		//regex for parsing (--param arg) or (--param)
		//String regex = "(--\\S+\\s+[^(--|\\s+)]+)|(--\\S+[^\\s]+)";
		
		//String regex = "(--\\S+\\s+[^-]\\S+)|(--\\S+\\s+)";
		String regex = "--\\S+\\s+(?!-{2})\\S+|--\\S+";
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(str);
		ArrayList<String> argsArr = new ArrayList<String>();
		while(m.find()) {
			argsArr.add(m.group());
		}
		return argsArr;
	}
	
	@Override
	final public boolean execute(String argsString) throws Exception {
		final int allowedSplittedWordsLength = 2;
		if (!StringUtil.isEmptyString(argsString)) {
			//String[] words = argsString.trim().split("\\s+");
			ArrayList<String> argsArr = strToList(argsString.trim());
	
			if (!argsArr.isEmpty()) {
				params = new HashMap<>();
				Set<String> paramsSet = getCommandParamsSet();

				for (int i=0;i<argsArr.size();i++) {
	
					String[] pair = argsArr.get(i).split(" ");
					if (allowedSplittedWordsLength != pair.length) {
						CommandsErrors.showCommandsErrorsMessage(CommandsErrors.INVALID_VALUE, pair[0]);
						return false;
					}
					
					paramsSet.remove(pair[0].toLowerCase());
					params.put(pair[0].toLowerCase(), pair[1]);
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
