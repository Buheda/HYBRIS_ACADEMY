package core.commands;

import core.db.dao.ProductDAO;
import core.db.entity.Products;
import core.db.entity.Products_status;
import core.util.DateTimeFormatter;

public class CreateProductCommand extends BaseCommandImp implements Command {

	@Override
	protected boolean isSpecificParamsValuesValid() {
		try {
			Integer.parseInt(params.get("price"));
		    return Integer.parseInt(params.get("status")) >= 0 && 
		    		Integer.parseInt(params.get("status")) < 3;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean execute() throws Exception {
		boolean isQueryOK = false;
		String paramsList[] = {"name", "price", "status"};
		if (isParamsValid(paramsList)) {
			int statusInt = Integer.parseInt(params.get("status"));
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
			}
			
			Products newProduct = new Products(
				params.get("name"), 
				Integer.parseInt(params.get("price")),
				status,
				DateTimeFormatter.getNow());
			
			int rowsCount = ProductDAO.createProduct(newProduct);
			isQueryOK = true;
			System.out.printf("%d rows added\n", rowsCount);
		}
		return isQueryOK;
	}
}
