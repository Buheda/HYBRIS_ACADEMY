package shopApp;
import java.util.HashMap;
import java.util.Scanner;

import db.queries.*;

public class ShopApplication {

	private static HashMap<Command, HashMap<String,String>> getCommand(){
		System.out.println("Please input command:");
		Scanner scanner = new Scanner(System.in);
		String commandLine = scanner.nextLine();
		String[] commandsArr = commandLine.split(" ");
		
		Command command = new UnknownCommand();
		HashMap<Command, HashMap<String,String>> resultHashMap = null;	
		boolean isCmdParsed = true;
		HashMap<String, String> cmdParamsList = null;
		boolean isCmdParamsShouldBeParsed = commandsArr.length % 2 != 0;

		switch (commandsArr[0].toLowerCase()) {
			case "help": 
				command = new HelpUsageCommand();
				isCmdParamsShouldBeParsed = false;
				break;
			case "exit":
				command = new ExitApplicationCommand();
				isCmdParamsShouldBeParsed = false;
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
				command = new ShowOrdersCommand();
				break;
			case "show_products":
				command = new ShowProductsCommand();
				break;
			case "remove_products":
				command = new RemoveProductCommand();
				break;					
			case "remove_all_products":
				command = new RemoveAllProductsCommand();
				break;
			default:
				command = new UnknownCommand();
				isCmdParamsShouldBeParsed = false;
				break;
		}
		
		if (isCmdParamsShouldBeParsed) {
			cmdParamsList = new HashMap<>();
			if (null != command) {
				for (int i=1;i<commandsArr.length;i=i+2) {
					if (commandsArr[i].startsWith("--")) {
						cmdParamsList.put(commandsArr[i].replaceFirst("^--", ""), commandsArr[i+1]);
					} else {
						isCmdParsed = false;
						break;
					}
				}
			}
		}
		
		if (isCmdParsed) {
			resultHashMap = new HashMap<>();
			resultHashMap.put(command, cmdParamsList);
		}
		
		return resultHashMap;
	}
	
	public static void main(String[] args)  {
		HelpUsageCommand.ShowUsageInfo();
		HashMap<Command, HashMap<String,String>> command = null;
		while (true){
			command = getCommand();
			try {
				command.entrySet().iterator().next().getKey().execute(command.entrySet().iterator().next().getValue());
			} catch (Exception e) {
				System.err.println(e);
			}
		}  
	}

}