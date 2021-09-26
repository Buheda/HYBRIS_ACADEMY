package core.commands;

import java.security.InvalidParameterException;
import java.util.HashMap;

import core.db.dao.ProductDAO;
import core.db.entity.Products;
import core.db.entity.Products_status;
import core.util.DateTimeFormatter;
import core.util.StringUtil;


public class CreateProductCommand implements Command {

	@Override
	public boolean isParamsOk(HashMap<String, String> params) {
		boolean isProductStatusValid = Integer.parseInt(params.get("status")) >-1 && Integer.parseInt(params.get("status")) < 3; 
		if (isProductStatusValid && null != params && !StringUtil.isEmptyString(params.get("name"))) {
			return true; 
		}
		else return false;
	}
	
	@Override
	public boolean execute(HashMap<String, String> params) throws Exception {
		boolean isQueryExecuted = false;
		if (!isParamsOk(params))
			throw new InvalidParameterException("missing or null parameter. See help");
		
		System.out.println("CreateProductCommand.execute(): ");
		System.out.println(params);
		Products newProduct = null;
		
		try {	
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
		
			 newProduct = new Products(
					params.get("name"), 
					Integer.parseInt(params.get("price")),
					status,
					DateTimeFormatter.getNow());
			
		} catch (Exception e) {
			System.err.println("Incorrect command parameter");
			throw e;
		}
		System.out.println(newProduct.toString());

		//create_product --name n --price 0 --status 0

	
		System.out.println(newProduct.toString());
		
		ProductDAO.CreateProduct(newProduct);
		return isQueryExecuted;
	}
}
