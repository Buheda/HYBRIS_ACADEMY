package core.commands.noArguments;

import java.util.List;

import core.commands.Command;
import core.db.dao.ProductDAO;
import core.db.entity.Products;

public class ShowProductsCommand implements Command {
	
	@Override
	public boolean execute(String argsString) throws Exception {
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

