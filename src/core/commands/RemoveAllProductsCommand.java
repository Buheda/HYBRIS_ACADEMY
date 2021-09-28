package core.commands;

import core.db.dao.ProductDAO;

public class RemoveAllProductsCommand extends BaseCommandImp implements Command {

	@Override
	protected boolean isSpecificParamsValuesValid() {
		return true;
	}
	
	@Override
	public boolean execute() throws Exception {
		boolean isQueryOK = false;
		String paramsList[] = {"password"};
		if (isParamsValid(paramsList)) {
			int rowsCount = ProductDAO.removeAllProducts(params.get("password"));
			if (-1 != rowsCount)
				isQueryOK = true;
			if (0 == rowsCount) {
				System.out.println("There is nothing to delete");
				if (ProductDAO.isProductsExists()) {
					System.out.println("Products wasn't delete");
					isQueryOK = false;
				}
			}
		}
		return isQueryOK;

	}
}

