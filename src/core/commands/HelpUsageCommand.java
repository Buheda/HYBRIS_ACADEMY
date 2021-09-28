package core.commands;

public class HelpUsageCommand  extends BaseCommandImp implements Command {

	@Override
	protected boolean isSpecificParamsValuesValid() {
		return true;
	}

	public static void ShowUsageInfo() {
		System.out.println("This application supports next commands:");
		System.out.println("	Help - info about application usage");
		System.out.println("	Create Product");
		System.out.println("	Create Order with a list of the products specified by id.");
		System.out.println("	Update Order Entries quantities.");
		System.out.println("	Show list of all products.");
		System.out.println("	Show list of all orders.");
		System.out.println("	Remove product by ID.");
		System.out.println("	Remove all products.");
		System.out.println("	exit - exit from application");
		
		System.out.println("Usage: ");
		System.out.println("create_product");
		System.out.println("  --name <arg>   product name");
		System.out.println("  --price <arg>  product price, integer");
		System.out.println("  --status <arg>  0,1,2 (0 - out of stock, 1 - in stock, 2 - running low)");
		
		System.out.println("remove_all_products");
		System.out.println("  --password <arg>   password for removing all products");

	}
	
	@Override
	public boolean execute() {
		ShowUsageInfo();
		return true;	
	}

}
