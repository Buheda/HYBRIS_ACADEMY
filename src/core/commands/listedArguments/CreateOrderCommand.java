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
	protected boolean executeCommand() throws Exception {
		boolean isQueryOK = false;
		ArrayList<Integer> items = new ArrayList<>(); 
		items.addAll(params.stream().map(Integer::valueOf).collect(Collectors.toList()));
		System.out.println(items);
		int id = OrderDAO.createOrder(items);
		if (-1 != id) {
			isQueryOK = true;
			System.out.println("Order was successfully added with id="+id);
		} else  {
			System.out.println("Order wasn't added");
		}
		return isQueryOK;
	}



}

