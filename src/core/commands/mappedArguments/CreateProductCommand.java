package core.commands.mappedArguments;

import java.util.Arrays;
import java.util.HashSet;

import core.commands.Command;
import core.db.dao.ProductDAO;
import core.db.entity.Products_status;
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
		    return Integer.parseInt(params.get("--status")) >= 0 && 
		    		Integer.parseInt(params.get("--status")) < 3;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	public boolean executeCommand() throws Exception {
		boolean isQueryOK = true;
		int statusInt = Integer.parseInt(params.get("--status"));
		Products_status status = Products_status.in_stock; 
		switch (statusInt) {
			case 0: 
				status = Products_status.out_of_stock;
				break;
			case 1:
				status = Products_status.in_stock;
				break;					
			case 2:
				status = Products_status.running_low;
				break;
			default:
				status = Products_status.in_stock;
				break;
		}
			
		int id = ProductDAO.createProduct(params.get("--name"), 
										Integer.parseInt(params.get("--price")),
										status,
										DateTimeFormatter.getNow());
		System.out.println("Product was successfully added with id= "+id);
		return isQueryOK;
	}


}
