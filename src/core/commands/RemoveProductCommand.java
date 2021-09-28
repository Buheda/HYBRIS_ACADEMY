package core.commands;

import core.db.dao.ProductDAO;

public class RemoveProductCommand extends BaseCommandImp implements Command {

	@Override
	protected boolean isSpecificParamsIsValid() {
		try {
			Integer.parseInt(params.get("id"));
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	@Override
	public void execute() throws Exception {
		int rowsCount = ProductDAO.removeProductsById(Integer.parseInt(params.get("id")));
		if (0 == rowsCount) {
			System.out.printf("There is nothing to delete");
		}
	}
}
