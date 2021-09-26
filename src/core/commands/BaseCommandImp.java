package core.commands;

import java.security.InvalidParameterException;
import java.util.HashMap;

import core.util.StringUtil;

public abstract class BaseCommandImp implements Command {

	protected HashMap<String, String> params;
	
	protected boolean checkParamsExist(String paramsList[]) {
		for (String param : paramsList) {
			if (!params.containsKey(param) || StringUtil.isEmptyString(params.get(param))) {
				return false;
			}
		}
		return true;
	}
	
	protected boolean paramsCheckingProcess() {
		if (!isParamsValid())
			throw new InvalidParameterException("Incorrect or missing parameters. See help");
		return true;
	}
	
	protected abstract boolean isParamsValid();
	
	@Override
	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}
	
	
}
