package core.commands.mappedArguments;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import core.commands.Command;
import core.commands.listedArguments.BaseCommand_ArgumentsList;

public class UpdateOrderCommand extends BaseCommand_ArgumentsMap implements Command {

	@Override
	protected HashSet<String> getCommandParamsSet() {
		HashSet<String> paramsSet = new HashSet<String>(Arrays.asList("--orderId", "--productId", "--quantity"));
		return paramsSet;
	}

	@Override
	protected boolean isParamsValuesValid() {
		try {
			Integer.parseInt(params.get("--orderId"));
		    Integer.parseInt(params.get("--productId"));
		    Integer.parseInt(params.get("--quantity"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	protected boolean executeCommand() throws Exception {
		// TODO Auto-generated method stub
		return false;
	}


}
