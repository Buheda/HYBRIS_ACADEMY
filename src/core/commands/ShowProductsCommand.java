package core.commands;

import java.util.List;

import core.db.dao.ProductDAO;
import core.db.entity.Products;

public class ShowProductsCommand extends BaseCommandImp implements Command {

	@Override
	protected boolean isSpecificParamsIsValid() {
		return true;
	}
	
	@Override
	public void execute() throws Exception {
		int nameFieldLength = ProductDAO.getAllProducts_MaxLength();
		List<Products> productsList = ProductDAO.getProductsList();
		for (Products product : productsList) {
			System.out.println(product.toString(nameFieldLength));
		}
	}

}

