package apps;

import java.util.Scanner;

import core.commands.*;
import core.commands.listedArguments.CreateOrderCommand;
import core.commands.listedArguments.RemoveAllProductsCommand;
import core.commands.listedArguments.RemoveProductByIDCommand;
import core.commands.listedArguments.ShowOrderByIDCommand;
import core.commands.mappedArguments.CreateProductCommand;
import core.commands.mappedArguments.UpdateOrderCommand;
import core.commands.noArguments.ExitApplicationCommand;
import core.commands.noArguments.HelpUsageCommand;
import core.commands.noArguments.ShowAllOrderedProductsCommand;
import core.commands.noArguments.ShowAllOrdersCommand;
import core.commands.noArguments.ShowAllProductsCommand;
import core.commands.noArguments.UnknownCommand;

public class ShopApplication {

	static Scanner scanner;
	
	public static void main(String[] args)  {
		HelpUsageCommand.showUsageInfo();
		scanner = new Scanner(System.in);
		Command command = null;
		while (true){
			try {
				System.out.println("\nPlease input command:");
				String commandLine = scanner.nextLine();
				String[] commandsArr = commandLine.split(" ", 2);
				command = new UnknownCommand();

				switch (commandsArr[0].toLowerCase()) {
					case "help": 
						command = new HelpUsageCommand();
						break;
					case "exit":
						command = new ExitApplicationCommand();
						break;
					case "create_product":
						command = new CreateProductCommand();
						break;
					case "create_order":
						command = new CreateOrderCommand();
						break;
					case "update_order":
						command = new UpdateOrderCommand();
						break;
					case "show_order":
						command = new ShowOrderByIDCommand();
						break;						
					case "show_all_products":
						command = new ShowAllProductsCommand();
						break;
					case "show_ordered_products":
						command = new ShowAllOrderedProductsCommand();
						break;
					case "show_all_orders":
						command = new ShowAllOrdersCommand();
						break;
					case "remove_product":
						command = new RemoveProductByIDCommand();
						break;					
					case "remove_all_products":
						command = new RemoveAllProductsCommand();
						break;
					default:
						command = new UnknownCommand();
						break;
				}

				if (commandsArr.length > 1)
					command.execute(commandsArr[1]);
				else 
					command.execute(null);
				
			} catch (Exception e) {
				System.err.println(e);
			}  
		}
	//	
	}
	
	public static void shutdownApplication() {
		scanner.close();
		System.exit(0);
	}

}