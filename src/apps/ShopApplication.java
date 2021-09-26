package apps;

import java.util.HashMap;
import java.util.Scanner;

import core.commands.*;

public class ShopApplication {
	
	private static Command getCommand(){
		System.out.println("Please input command:");
		Scanner scanner = new Scanner(System.in);
		String commandLine = scanner.nextLine();
		String[] commandsArr = commandLine.split(" ");
		
		Command resultCommand = new UnknownCommand();
		HashMap<String, String> cmdParamsList = null;
		boolean isCmdParamsShouldBeParsed = commandsArr.length % 2 != 0;

		switch (commandsArr[0].toLowerCase()) {
			case "help": 
				resultCommand = new HelpUsageCommand();
				isCmdParamsShouldBeParsed = false;
				break;
			case "exit":
				resultCommand = new ExitApplicationCommand();
				isCmdParamsShouldBeParsed = false;
				break;
			case "create_product":
				resultCommand = new CreateProductCommand();
				break;
			case "create_order":
				resultCommand = new CreateOrderCommand();
				break;
			case "update_order":
				resultCommand = new UpdateOrderCommand();
				break;
			case "show_orders":
				resultCommand = new ShowOrdersCommand();
				break;
			case "show_products":
				resultCommand = new ShowProductsCommand();
				break;
			case "remove_products":
				resultCommand = new RemoveProductCommand();
				break;					
			case "remove_all_products":
				resultCommand = new RemoveAllProductsCommand();
				break;
			default:
				resultCommand = new UnknownCommand();
				isCmdParamsShouldBeParsed = false;
				break;
		}
		
		if (isCmdParamsShouldBeParsed) {
			cmdParamsList = new HashMap<>();
			if (null != resultCommand) {
				for (int i=1;i<commandsArr.length;i=i+2) {
					if (commandsArr[i].startsWith("--")) {
						cmdParamsList.put(commandsArr[i].replaceFirst("^--", ""), commandsArr[i+1]);
					} else {
						break;
					}
				}
			}
		}
		
		resultCommand.setParams(cmdParamsList);
		return resultCommand;
	}
	
	public static void main(String[] args)  {
		HelpUsageCommand.ShowUsageInfo();
		Command command = null;
		while (true){
			command = getCommand();
			try {
				command.execute();
			} catch (Exception e) {
				System.err.println(e);
			}
		}  
	}

}