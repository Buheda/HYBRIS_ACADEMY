package db;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBController {
	private static DBController dbUitlInstance = null;
	
	private static Connection connection;
	private static Database db;

	private DBController() throws Exception{
		try {
			InputStream input = new FileInputStream("local.properties");
			Properties connectionProperties = new Properties();
			connectionProperties.load(input);
					
	        if ("hsqldb".equals(connectionProperties.getProperty("db.type"))) {
		        Class.forName("org.hsqldb.jdbcDriver");
		        db = new Database_HSQLDB();
	        }
	        else if ("mysql".equals(connectionProperties.getProperty("db.type"))) {
			    Class.forName("com.mysql.jdbc.Driver");
		        db = new Database_MySql();
	        }
	        //else 
	        	//throw InvalidPropertyException
	        connection = DriverManager.getConnection(
				connectionProperties.getProperty("db.url"),
				connectionProperties.getProperty("db.user"),
				connectionProperties.getProperty("db.password"));
		} catch (Exception e) {
			System.out.println("Couldn't connect to database: " + e);
			throw e;
		}
	};
		
	private static void checkDBUtilInstance() throws Exception {
		if (dbUitlInstance == null) {
			dbUitlInstance = new DBController();
		}
	}
	
	public static Connection getConnection() throws Exception {
		checkDBUtilInstance();
		return connection;
	}
	
	public static Database getDB() throws Exception {
		checkDBUtilInstance();
		return db;	
	}
}
