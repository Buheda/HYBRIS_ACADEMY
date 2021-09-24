package shopApp;
import java.util.HashMap;
import java.util.Scanner;

import db.queries.*;

public class ShopApplication {

	private static HashMap<Command, HashMap<String,String>> getCommandParameters(){
		Command command = null;
		HashMap<String, String> commandParamsList = new HashMap<>();
		HashMap<Command, HashMap<String,String>> resultHashMap = null;
		boolean isParametersParsed = false;
		
		System.out.println("Please input command:");
		Scanner scanner = new Scanner(System.in);
		String commandLine = scanner.nextLine();
		String[] commandsArr = commandLine.split(" ");

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
		}
			
		boolean isCommandsArrCountAcceptable = commandsArr.length % 2 != 0;

		if (null != command && isCommandsArrCountAcceptable) {
			isParametersParsed = true;
			for (int i=1;i<commandsArr.length;i=i+2) {
				if (commandsArr[i].startsWith("--")) {
					commandParamsList.put(commandsArr[i].replaceFirst("^--", ""), commandsArr[i+1]);
				} else {
					isParametersParsed = false;
					break;
				}
			}
		}
		
		if (isParametersParsed) {
			resultHashMap = new HashMap<>();
			resultHashMap.put(command, commandParamsList);
		}
		
		return resultHashMap;
	}
	
	public static void main(String[] args) {
		HelpUsageCommand.ShowUsageInfo();
		HashMap<Command, HashMap<String,String>> commandParameters = null;
		while (true){
			commandParameters = getCommandParameters();
			if (null != commandParameters) {
				commandParameters.entrySet().iterator().next().getKey().execute();
			}
			else
				System.err.println("Not enought parameters");
		}  
	}

}