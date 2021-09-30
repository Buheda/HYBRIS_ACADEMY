package apps;

import java.util.Scanner;

import core.commands.listedArguments.CreateOrderCommand;
import core.commands.mappedArguments.CreateProductCommand;
import core.commands.noArguments.ShowAllProductsCommand;
import core.db.DBConnection;
import core.db.entity.Products;
import dbApp.tablesManager.Factory;
import java.io.PrintStream;
import java.io.IOException;
import java.io.OutputStream;


public class Generator {
	private static Scanner scanner;
	private final static int COUNT_RECORD = 50;
	private final static PrintStream oldOut = System.out;
	private final static PrintStream oldErr = System.err;

	
	private static void switchOffConsole() {
		System.setOut(new PrintStream(new OutputStream() {
		      @Override public void write(int b) throws IOException {}
		    }));
		
		System.setErr(new PrintStream(new OutputStream() {
		      @Override public void write(int b) throws IOException {}
		    }));
	}
	
	private static void switchOnConsole() {
		System.setOut(oldOut);
		System.setErr(oldErr);
	}
	
	private static boolean askYN(String question) {
		System.out.println(question+" y/n");
		String commandLine = scanner.nextLine();
		while (!commandLine.equals("y") && !commandLine.equals("n")) {
			System.out.println("Incorrect answer");
			System.out.println("Do you need to drop old tables? y/n");
			commandLine = scanner.nextLine();
		}
		return commandLine.equals("y");
	}
	
	private static void dropTables() throws Exception {		
		System.out.println("removing tables...");
		Factory.getDBManager().dropTables();
		System.out.println("Done!");
	}
	
	private static void createTables() throws Exception {
		System.out.println("Creating tables..."); 
		Factory.getDBManager().createTables();
		System.out.println("Done!");
	}
	
	private static void generateProducts() throws Exception {
		System.out.println("Generating products...");
		CreateProductCommand command = new CreateProductCommand();
		switchOffConsole();
		for (int i = 0; i < COUNT_RECORD; ++i) {
			int countPossibleStatuses = 3;
			int maxPrice = 1500;
			int minPrice = 100;
			
			int status = (int)(Math.random() * countPossibleStatuses);
			int price = minPrice + (int)(Math.random() * (maxPrice-minPrice));
			command.execute("--name Product"+i+" --status "+Products.getStatusTypes().get(status)+" --price "+price);
		}
		switchOnConsole();
		System.out.println("Done!");
	}
	
	private static void generateOrders() throws Exception {
		System.out.println("Generating orders...");
		CreateOrderCommand command = new CreateOrderCommand();
		StringBuffer params = new StringBuffer();
		switchOffConsole();
		for (int i = 0; i < COUNT_RECORD; ++i) {
			int maxProductsCountInOrder = 10;
			int productsCountInOrder = 1 + (int)(Math.random() * maxProductsCountInOrder);  
			int productid = 0;
			params.setLength(0);
			for (int j = 0; j < productsCountInOrder; ++j ) {
				productid = (int)(Math.random() * COUNT_RECORD);
				params.append(" "+productid);
			}
			command.execute(params.toString());
		}
		switchOnConsole();
		System.out.println("Done!");
	}
	
	private static void showAllProducts() throws Exception {
		ShowAllProductsCommand command = new ShowAllProductsCommand();
		command.execute();
	}
	
	public static void main(String[] args) throws Exception {
		boolean isDeleted = false;
		boolean isCreated = false;
		scanner = new Scanner(System.in);
		if (askYN("Do you need to drop old tables?"))
			dropTables();
		if (askYN("Do you need to create new tables?"))
			createTables();
		if (askYN("Do you need to generate example data?")) {
			generateProducts();
			generateOrders();
			showAllProducts();
		}
		
		DBConnection.getConnection().close();
		scanner.close();
	}

}
