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
	
	private static Command switchCommand(String str) {
		Command command = null;
		switch (str.toLowerCase()) {
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
		
		return command;
	}
	
	private static void parseCommandLineAndExecute(String commandLine) throws Exception {
		String[] commandsArr = commandLine.split(" ", 2);
		
		Command command = switchCommand(commandsArr[0]);		

		if (commandsArr.length > 1)
			command.execute(commandsArr[1]);
		else 
			command.execute(null);
	}	
	
	public static void main(String[] args)  {
		Command command = null;
		try {
			if (args.length == 1) {
				command = switchCommand(args[0]);
				command.execute();
			} else if (args.length > 1) {
				String commandLine = String.join(",", args);
				parseCommandLineAndExecute(commandLine);
			} else {
				HelpUsageCommand.showUsageInfo();				
			}
			
		} catch (Exception e) {
			System.err.println(e);
		}  
		
		System.out.println("");


		scanner = new Scanner(System.in);
		
		while (true){
			try {
				System.out.println("\nPlease input command:");
				String commandLine = scanner.nextLine();
				parseCommandLineAndExecute(commandLine);				
				
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