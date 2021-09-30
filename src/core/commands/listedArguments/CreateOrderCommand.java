package core.commands.listedArguments;

import core.commands.Command;
import core.db.dao.OrderDAO;
import core.db.dao.ProductDAO;
import core.db.entity.Order_items;
import core.util.DateTimeFormatter;


public class CreateOrderCommand extends BaseCommand_ArgumentsList implements Command {

	@Override
	protected boolean isParamsValuesValid() {
		try {
			for (String param : params) {
				Integer.parseInt(param);
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	protected boolean executeCommand() throws Exception {
		boolean isQueryOK = false;
		int resultOrderId = -1;
		boolean isOrderItemsCreated = false;
		resultOrderId = OrderDAO.createOrder((int)(Math.random() * (101)), 
				"new", 
				DateTimeFormatter.timestampToStr(DateTimeFormatter.getNow()));
		for (String productId : params) {
			if (ProductDAO.isProductsExistsById(Integer.parseInt(productId))) {
					Order_items item = new Order_items(resultOrderId, Integer.parseInt(productId), 1);
					if (OrderDAO.createOrderItem(item))
						isOrderItemsCreated = true;
			}
		}

		
		if (!isOrderItemsCreated) {
			OrderDAO.removeOrderById(resultOrderId);
			System.err.println("Order wasn't added. Selected Products don't exist");
		} else {
			isQueryOK = true;
			System.out.println("Order was successfully added with id=" + resultOrderId);
		}
		return isQueryOK;
	}



}

