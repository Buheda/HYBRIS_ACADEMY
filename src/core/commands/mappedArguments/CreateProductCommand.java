package core.commands.mappedArguments;

import java.util.Arrays;
import java.util.HashSet;

import core.commands.Command;
import core.db.dao.ProductDAO;
import core.db.entity.Products;
import core.util.DateTimeFormatter;

public class CreateProductCommand extends BaseCommand_ArgumentsMap implements Command {
		
	@Override
	protected HashSet<String> getCommandParamsSet() {
		HashSet<String> paramsSet = new HashSet<String>(Arrays.asList("--name", "--price", "--status"));
		return paramsSet;
	}
	
	@Override
	protected boolean isParamsValuesValid() {
		try {
			//params.get("--price").matches("\\d+");
			Integer.parseInt(params.get("--price"));
			return Products.getStatusTypes().contains(params.get("--status"));
		    /*return Integer.parseInt(params.get("--status")) >= 0 && 
		    		Integer.parseInt(params.get("--status")) < 3;*/
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean executeCommand() throws Exception {
		boolean isQueryOK = false;
		
		int id = ProductDAO.createProduct(params.get("--name"), 
										Integer.parseInt(params.get("--price")),
										params.get("--status"),
										DateTimeFormatter.getNow());
		if (-1 != id) {
			System.out.println("Product was successfully added with id= "+id);
			isQueryOK = true;
		} else {
			System.err.println("Products wasn't added. Please check status");
		}
		
		return isQueryOK;
	}


}
