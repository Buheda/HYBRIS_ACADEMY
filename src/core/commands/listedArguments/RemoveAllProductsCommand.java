package core.commands.listedArguments;

import core.commands.Command;
import core.db.dao.ProductDAO;

public class RemoveAllProductsCommand extends BaseCommand_ArgumentsList implements Command {
		
	@Override
	protected boolean isParamsValuesValid() {
		return true;
	}
	
	@Override
	protected boolean executeCommand() throws Exception {
		boolean isQueryOK = false;
		if (core.persistent.FinalProperties.REMOVE_ALL_PRODUCTS_PASSWORD.equals(params.get(0))) {
			ProductDAO.removeAllProducts();
			System.out.println("Products were successfully removed");
			isQueryOK = true;
		} else {
			System.err.println("ERROR: Incorrect password for removing all products");
		}
		return isQueryOK;
	}
}

