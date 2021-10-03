package core.commands.noArguments;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import core.commands.Command;
import core.db.dao.OrderDAO;
import core.db.dao.ProductDAO;

public class ShowAllOrdersCommand implements Command {
	
	private void showOrdersTableLine(String id, String price, String name, String quantity, String created_at) {
		System.out.printf("|%s|%s|%s|%s|%s|\n", id, price, name, quantity, created_at);
	}
	
	private void showOrdersTable(List<HashMap<Object,Object>> orderList) throws Exception {
		int nameFieldLength = ProductDAO.getAllProducts_MaxLength();
		
		final String idField = "Order ID";
		final int idFieldLength = idField.length();
		 
		final String priceField = "Products Total Price";
		final int priceFieldLength = priceField.length();
		
		final String nameField = "Product Name";
		final int minNameFieldLength = nameField.length();
			
		final String quantityField = "Products Quantity";
		final int quantityFieldLength = quantityField.length();

		final String dateField = "Order Created Date";
		final int dateFieldLength = dateField.length();
		
	    if (nameFieldLength < minNameFieldLength)
			nameFieldLength = minNameFieldLength;
	    
	    showOrdersTableLine(
				String.format("%-"+idFieldLength+"s", idField),
				String.format("%-"+priceFieldLength+"."+priceFieldLength+"s", priceField),
				String.format("%-"+nameFieldLength+"."+ nameFieldLength+"s", nameField),
				String.format("%-"+quantityFieldLength+"s", quantityField),
				String.format("%-"+dateFieldLength+"s", dateField)
				);
	
	    showOrdersTableLine(
				String.format("%-"+idFieldLength+"s", 
						String.join("", Collections.nCopies(idFieldLength, "-"))),
				String.format("%-"+priceFieldLength+"."+priceFieldLength+"s",
						String.join("", Collections.nCopies(priceFieldLength, "-"))),
				String.format("%-"+nameFieldLength+"."+ nameFieldLength+"s", 
						String.join("", Collections.nCopies(nameFieldLength, "-"))),
				String.format("%-"+quantityFieldLength+"s",
						String.join("", Collections.nCopies(quantityFieldLength, "-"))),
				String.format("%-"+dateFieldLength+"s",
						String.join("", Collections.nCopies(dateFieldLength, "-")))
				);
		
	    for (HashMap<Object, Object> order : orderList) {
	    	showOrdersTableLine(
					String.format("%-"+idFieldLength+"s", order.get("id")),
					String.format("%-"+priceFieldLength+"s", order.get("price")),
					String.format("%-"+nameFieldLength+"s", order.get("name")),
					String.format("%-"+quantityFieldLength+"s", order.get("quantity")),
					String.format("%-"+dateFieldLength+"s", order.get("created_at"))				
					);
		} 
	 
	}
	
	@Override
	public boolean execute(String argsString) throws Exception {
		boolean isQueryOK = false;
		List<HashMap<Object, Object>> orderList = OrderDAO.getAllOrdersFullInfoList();
		isQueryOK = true;
		if (orderList.isEmpty()) {
			System.out.println("Orders table is empty");
		} else {
			showOrdersTable(orderList);
		}
		return isQueryOK;
	}
	


}

