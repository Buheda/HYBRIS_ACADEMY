package core.commands;

import core.db.dao.ProductDAO;

public class RemoveProductCommand extends BaseCommandImp implements Command {

	@Override
	protected boolean isSpecificParamsValuesValid() {
		try {
			Integer.parseInt(params.get("id"));
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean execute() throws Exception {
		boolean isQueryOK = false;
		String paramsList[] = {"id"};
		if (isParamsValid(paramsList)) {	
			int rowsCount = ProductDAO.removeProductsById(Integer.parseInt(params.get("id")));
			isQueryOK = true;
			if (0 == rowsCount) {
				System.out.println("There is nothing to delete");
			}
		}
		return isQueryOK;
	}
}
