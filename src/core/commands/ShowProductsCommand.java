package core.commands;

import java.util.List;

import core.db.dao.ProductDAO;
import core.db.entity.Products;

public class ShowProductsCommand extends BaseCommandImp implements Command {

	@Override
	protected boolean isSpecificParamsValuesValid() {
		return true;
	}
	
	@Override
	public boolean execute() throws Exception {
		boolean isQueryOK = false;
		int nameFieldLength = ProductDAO.getAllProducts_MaxLength();
		List<Products> productsList = ProductDAO.getProductsList();
		isQueryOK = true;
		for (Products product : productsList) {
			System.out.println(product.toString(nameFieldLength));
		}
		return isQueryOK;
	}

}

