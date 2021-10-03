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
		boolean isRemovedOK = false;
		for (String param : params) {
			if (!ProductDAO.removeProductsById(Integer.parseInt(param)))
				System.out.println("product id:"+param + " can't be deleted");
			else
				isRemovedOK = true;
		} 
		if (isRemovedOK)
			System.out.println("Products were successfully removed");
		return true;
	}



}
