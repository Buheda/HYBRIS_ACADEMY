package core.commands.mappedArguments;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import core.commands.Command;
import core.db.dao.OrderDAO;

public class UpdateOrderCommand extends BaseCommand_ArgumentsMap implements Command {

	@Override
	protected Set<String> getCommandParamsSet() {
		Set<String> paramsSet = new HashSet<String>(Arrays.asList("--orderid", "--productid", "--quantity"));
		return paramsSet;
	}

	@Override
	protected boolean isParamsValuesValid() {
		try {
			Integer.parseInt(params.get("--orderid"));
		    Integer.parseInt(params.get("--productid"));
		    Integer.parseInt(params.get("--quantity"));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	protected boolean executeCommand() throws Exception {
		boolean isQueryOK = false;
		if (!OrderDAO.updateOrderedItemQuantity(
 				Integer.parseInt(params.get("--orderid")), 
				Integer.parseInt(params.get("--productid")), 
				Integer.parseInt(params.get("--quantity")))) {
			System.out.println("There is nothing to update");
		} else {
			System.out.println("orders item quantity was successfully updated");
			isQueryOK = true;
		}
		return isQueryOK;
	}


}
