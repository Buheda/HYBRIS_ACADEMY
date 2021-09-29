package core.commands.listedArguments;

import java.util.ArrayList;
import java.util.stream.Collectors;

import core.commands.Command;
import core.db.dao.OrderDAO;


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
	protected boolean executeCommand() {
		boolean isQueryOK = false;
		ArrayList<Integer> items = new ArrayList<>(); 
		items.addAll(params.stream().map(Integer::valueOf).collect(Collectors.toList()));
		int rowsCount = OrderDAO.createOrder(items);
		if (-1 != rowsCount) {
			isQueryOK = true;
			System.out.println("Order was successfully added with id="+rowsCount);
		} else  {
			System.out.println("Product wasn't added");
		}
		return isQueryOK;
	}



}

