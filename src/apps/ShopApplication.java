package apps;

import java.util.Scanner;

import core.commands.*;
import core.commands.listedArguments.CreateOrderCommand;
import core.commands.listedArguments.RemoveAllProductsCommand;
import core.commands.listedArguments.RemoveProductCommand;
import core.commands.mappedArguments.CreateProductCommand;
import core.commands.mappedArguments.UpdateOrderCommand;
import core.commands.noArguments.ExitApplicationCommand;
import core.commands.noArguments.HelpUsageCommand;
import core.commands.noArguments.ShowAllOrdersCommand;
import core.commands.noArguments.ShowProductsCommand;
import core.commands.noArguments.UnknownCommand;

public class ShopApplication {

	static Scanner scanner;
	
	public static void main(String[] args)  {
		HelpUsageCommand.showUsageInfo();
		scanner = new Scanner(System.in);
		Command command = null;
		while (true){
			try {
				System.out.println("Please input command:");
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
					case "show_orders":
						command = new ShowAllOrdersCommand();
						break;
					case "show_products":
						command = new ShowProductsCommand();
						break;
					case "remove_product":
						command = new RemoveProductCommand();
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
				System.err.println(e.getStackTrace());
			}  
		}
	//	
	}
	
	public static void shutdownApplication() {
		scanner.close();
		System.exit(0);
	}

}