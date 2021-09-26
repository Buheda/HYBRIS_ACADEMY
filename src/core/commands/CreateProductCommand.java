package core.commands;

import java.util.HashMap;

import core.db.dao.ProductDAO;
import core.db.entity.Products;
import core.db.entity.Products_status;
import core.util.DateTimeFormatter;

public class CreateProductCommand extends BaseCommandImp implements Command {

	@Override
	public boolean isParamsValid() {
		String paramsList[] = {"name", "price", "status"};
	    return (
				null != params && 
				super.checkParamsExist(paramsList) &&
				Integer.parseInt(params.get("status")) >=0 && 
				Integer.parseInt(params.get("status")) < 3);
	}

	@Override
	public boolean execute() throws Exception {
		boolean isQueryExecuted = paramsCheckingProcess();
		
		int statusInt = Integer.parseInt(params.get("status"));
		Products_status status = null; 
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
			default :
				status = Products_status.in_stock;					
		}
		
		Products newProduct = new Products(
			params.get("name"), 
			Integer.parseInt(params.get("price")),
			status,
			DateTimeFormatter.getNow());
			

		//create_product --name n --price 0 --status 0

	
		System.out.println(newProduct.toString());
		
		ProductDAO.CreateProduct(newProduct);
		return isQueryExecuted;
	}
}
