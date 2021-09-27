package core.commands;

import core.db.dao.ProductDAO;

public class RemoveAllProductsCommand extends BaseCommandImp implements Command {

	@Override
	protected boolean isSpecificParamsIsValid() {
		return true;
	}
	
	@Override
	public void execute() throws Exception {
		String paramsList[] = {"password"};
		checkParams(paramsList);			
		
		int rowsCount = ProductDAO.removeAllProducts(params.get("password"));
		if (0==rowsCount) {
			System.out.println("There is nothing to delete");
			if (ProductDAO.isProductsExists())
				System.out.printf("Products wasn't delete");
		}
	}
}

