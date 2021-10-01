package core.commands.listedArguments;

import core.commands.Command;
import core.db.dao.ProductDAO;

public class RemoveProductByIDCommand extends BaseCommand_ArgumentsList implements Command {
	
	@Override
	protected boolean isParamsValuesValid() {
		try {
			for (String param : params) {
				Integer.parseInt(param);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean executeCommand() throws Exception {
		for (String param : params) {
			ProductDAO.removeProductsById(Integer.parseInt(param));
		}
		System.out.println("Products were successfully removed");
		return true;
	}



}
