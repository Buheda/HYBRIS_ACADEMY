package core.commands.noArguments;

import java.util.Collections;
import java.util.List;


import core.commands.Command;
import core.db.dao.ProductDAO;
import core.db.entity.Products;

public class ShowAllProductsCommand implements Command {
	
	private void showProductsTableLine(String id, String name, String price, String status) {
		System.out.printf("|%s|%s|%s|%s|\n", id, name, price, status);
	}
	
	private void showProductsTable(List<Products> productsList) {
		int nameFieldLength = ProductDAO.getAllProducts_MaxLength();
				
		final String idField = "Product ID";
		final int idFieldLength = idField.length();
		
		final String nameField = "Product Name";
		final int minNameFieldLength = nameField.length();
		
		final String priceField = "Product Price";
		final int priceFieldLength = priceField.length();
		
		final String statusField = "Product Status";
		final int statusFieldLength = statusField.length();
					
	    if (nameFieldLength < minNameFieldLength)
			nameFieldLength = minNameFieldLength;
	    
	    showProductsTableLine(
				String.format("%-"+idFieldLength+"s", idField),
				String.format("%-"+nameFieldLength+"s", nameField),
				String.format("%-"+priceFieldLength+"s", priceField),
				String.format("%-"+statusFieldLength+"s", statusField)
				);
	
	    showProductsTableLine(
				String.format("%-"+idFieldLength+"s", 
						String.join("", Collections.nCopies(idFieldLength, "-"))),
				String.format("%-"+nameFieldLength+"."+ nameFieldLength+"s", 
						String.join("", Collections.nCopies(nameFieldLength, "-"))),
				String.format("%-"+priceFieldLength+"."+priceFieldLength+"s",
						String.join("", Collections.nCopies(priceFieldLength, "-"))),
				String.format("%-"+statusFieldLength+"s",
						String.join("", Collections.nCopies(statusFieldLength, "-")))
				);
		
		for (Products product : productsList) {
			showProductsTableLine(
					String.format("%-"+idFieldLength+"s", product.getId()),
					String.format("%-"+nameFieldLength+"s", product.getName()),
					String.format("%-"+priceFieldLength+"s", product.getPrice()),
					String.format("%-"+statusFieldLength+"s", product.getStatus())
					);
		}
	}
	
	@Override
	public boolean execute(String argsString) throws Exception {
		boolean isQueryOK = false;
		List<Products> productsList = ProductDAO.getProductsList();
		isQueryOK = true;
		if (productsList.isEmpty()) {
			System.out.println("Products table is empty");
		} else {
			showProductsTable(productsList);
		}
		return isQueryOK;
	}
}

