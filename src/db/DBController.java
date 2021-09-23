package db;

import java.util.Properties;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBController {
	
	private static DBController instance = null;
	private static Properties connectionProperties = null;
	private static Connection connection = null;
	private static Database db;
	
	private DBController() throws Exception {
		try {
			InputStream input = new FileInputStream("local.properties");
			connectionProperties = new Properties();
			connectionProperties.load(input);
					
	        if ("hsqldb".equals(connectionProperties.getProperty("db.type"))) {
		        Class.forName("org.hsqldb.jdbcDriver");
		        db = new Database_HSQLDB();
	        }
	        else if ("mysql".equals(connectionProperties.getProperty("db.type"))) {
			    Class.forName("com.mysql.jdbc.Driver");
		        db = new Database_MySql();
	        } 
	        else
	        	throw new IllegalArgumentException("Incorrect database type");	    
		} catch (Exception e) {
			System.err.println("Couldn't read connection properties: " + e.getMessage() + "\n");
			connectionProperties = null;
			throw e;
		}
	};
		
	private static void checkDBConnection() throws Exception {
		if (instance == null) {
			instance = new DBController();
		}
		
		try {
			if ((null != connectionProperties) && (null == connection || connection.isClosed())) {
				connection = DriverManager.getConnection(
					connectionProperties.getProperty("db.url"),
					connectionProperties.getProperty("db.user"),
					connectionProperties.getProperty("db.password"));
			}
		} catch (Exception e) {
			System.err.println("Couldn't connect to database: " + e.getMessage() + "\n");
			throw e;
		}
	}
		
	public static Connection getConnection() throws Exception {
		checkDBConnection();
		return connection;
	}
	
	public static Database getDB() throws Exception {
		checkDBConnection();
		return db;	
	}
}
