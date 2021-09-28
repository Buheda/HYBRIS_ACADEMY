package core.commands;

import java.util.HashMap;

import core.persistent.CommandsErrors;
import core.util.StringUtil;

public abstract class BaseCommandImp implements Command {

	protected HashMap<String, String> params;
	
	protected abstract boolean isSpecificParamsValuesValid();
	
	protected boolean isParamsValid(String paramsList[]) {
		if (null != paramsList && null != params && !params.isEmpty()) {
			for (String param : paramsList) {
				if (!params.containsKey(param)) {
					CommandsErrors.showCommandsErrorsMessage(CommandsErrors.INVALID_KEY, param);
					return false;
				}
				else if(StringUtil.isEmptyString(params.get(param))) {
					CommandsErrors.showCommandsErrorsMessage(CommandsErrors.INVALID_VALUE, param);
					return false;
				}
			}
		}
		else {
			CommandsErrors.showCommandsErrorsMessage(CommandsErrors.INVALID_PARAMETERS);
			return false;
		}

		if (!isSpecificParamsValuesValid()){
			CommandsErrors.showCommandsErrorsMessage(CommandsErrors.INVALID_VALUE);
			return false;
		}
		return true;
	}
	
	@Override
	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}
}
