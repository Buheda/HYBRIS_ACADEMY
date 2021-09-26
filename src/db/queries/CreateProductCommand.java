package db.queries;

import java.security.InvalidParameterException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.HashMap;

import shopApp.dao.ProductDAO;
import shopApp.entity.Products;
import shopApp.entity.Products_status;
import util.DateTimeFormatter;
import util.StringUtil;

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
		
		params.put("id", "0");
		
		Products newProduct = new Products(params);
		System.out.println(newProduct.toString());
		return isQueryExecuted;
	}

}
