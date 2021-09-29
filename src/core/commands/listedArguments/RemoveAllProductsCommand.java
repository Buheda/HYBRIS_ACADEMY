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
		int rowsCount = ProductDAO.removeAllProducts(params.get(0));
		if (-1 != rowsCount)
			isQueryOK = true;
		if (0 == rowsCount) {
			System.out.println("There is nothing to delete");
		} else {
			System.out.println("Products were successfully removed");
		}		
		return isQueryOK;
	}
}

