package core.commands.noArguments;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;


import core.commands.Command;
import core.db.dao.ProductDAO;

public class ShowAllOrderedProductsCommand implements Command {
	
	private void showProductsTableLine(String id, String name, String price, String status, String quantity) {
		System.out.printf("|%s|%s|%s|%s|%s|\n", id, name, price, status, quantity);
	}
	
	private void showProductsTable(List<HashMap<Object,Object>> productsList) throws Exception {
		int nameFieldLength = ProductDAO.getAllProducts_MaxLength();
		
		final String idField = "Product ID";
		final int idFieldLength = idField.length();
		 
		final String nameField = "Product Name";
		final int minNameFieldLength = nameField.length();
		
		final String priceField = "Product Price";
		final int priceFieldLength = "Product Price".length();
		
		final String statusField = "Product Status";
		final int statusFieldLength = statusField.length();

		final String quantityField = "Quantity";
		final int quantityFieldLength = quantityField.length();
		
	    if (nameFieldLength < minNameFieldLength)
			nameFieldLength = minNameFieldLength;
	    
	    showProductsTableLine(
				String.format("%-"+idFieldLength+"s", idField),
				String.format("%-"+nameFieldLength+"s", nameField),
				String.format("%-"+priceFieldLength+"s", priceField),
				String.format("%-"+statusFieldLength+"s", statusField),
				String.format("%-"+quantityFieldLength+"s", quantityField)				
				);
	
	    showProductsTableLine(
				String.format("%-"+idFieldLength+"s", 
						String.join("", Collections.nCopies(idFieldLength, "-"))),
				String.format("%-"+nameFieldLength+"."+ nameFieldLength+"s", 
						String.join("", Collections.nCopies(nameFieldLength, "-"))),
				String.format("%-"+priceFieldLength+"."+priceFieldLength+"s",
						String.join("", Collections.nCopies(priceFieldLength, "-"))),
				String.format("%-"+statusFieldLength+"s",
						String.join("", Collections.nCopies(statusFieldLength, "-"))),
				String.format("%-"+quantityFieldLength+"s",
						String.join("", Collections.nCopies(quantityFieldLength, "-")))
				);
	    
	    for (HashMap<Object, Object> product : productsList) {
	    	showProductsTableLine(
					String.format("%-"+idFieldLength+"s", product.get("id")),
					String.format("%-"+ nameFieldLength+"s", product.get("name")),
					String.format("%-"+priceFieldLength+"s", product.get("price")),
					String.format("%-"+statusFieldLength+"s", product.get("status")),
					String.format("%-"+quantityFieldLength+"s", product.get("quantity"))				
					);
		}	   
	}
	
	@Override
	public boolean execute(String argsString) throws Exception {
		boolean isQueryOK = false;
		List<HashMap<Object, Object>> productsList = ProductDAO.getAllOrderedProductsList();
		isQueryOK = true;
		if (productsList.isEmpty()) {
			System.out.println("Ordered products table is empty");
		} else {
			showProductsTable(productsList);
		}
		return isQueryOK;
	}
}

