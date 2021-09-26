package core.db;

import java.util.Properties;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static DBConnection instance = null;
	private static Properties connectionProperties = null;
	private static Connection connection = null;
	private static DBType dbType = null;

	private DBConnection() throws Exception {
		try {
			InputStream input = new FileInputStream("local.properties");
			connectionProperties = new Properties();
			connectionProperties.load(input);
	        if ("hsqldb".equals(connectionProperties.getProperty("db.type"))) {
		        Class.forName("org.hsqldb.jdbcDriver");
				dbType = DBType.HSQLDB;
	        }
	        else if ("mysql".equals(connectionProperties.getProperty("db.type"))) {
			    Class.forName("com.mysql.jdbc.Driver");
			    dbType = DBType.MySQL;
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
			instance = new DBConnection();
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

	public static DBType getDBType() throws Exception {
		checkDBConnection();
		return dbType;
	}
}
