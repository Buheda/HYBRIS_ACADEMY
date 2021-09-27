package core.commands;

import java.security.InvalidKeyException;
import java.util.HashMap;

import javax.management.InvalidAttributeValueException;
import javax.naming.directory.InvalidAttributesException;

import core.util.StringUtil;

public abstract class BaseCommandImp implements Command {

	protected HashMap<String, String> params;
	
	protected abstract boolean isSpecificParamsIsValid() throws Exception;
	
	protected void checkParams(String paramsList[]) throws Exception  {
		if (null != paramsList && null != params) {
			for (String param : paramsList) {
				if (!params.containsKey(param))
					throw new InvalidKeyException("Missing parameter '"+param+"'. See help");
				else if(StringUtil.isEmptyString(params.get(param))) {	
					throw new InvalidAttributeValueException("Empty or missing value for parameter '"+param+"'. See help");
				}
			}
		}
		else
			throw new InvalidAttributesException("Missing parameters. See help");
		if (!isSpecificParamsIsValid())
			throw new InvalidAttributeValueException("Incorrect parameter'. See help");
	}
	
	@Override
	public void setParams(HashMap<String, String> params) {
		this.params = params;
	}
	
	
}
