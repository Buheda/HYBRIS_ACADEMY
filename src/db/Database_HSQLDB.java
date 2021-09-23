package db;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database_HSQLDB implements Database {

	@Override
	public boolean createDatabase() {
		StringBuilder curTable = null;
		try {
			return CreateTable_Order(curTable) &&
					CreateTable_OrderItems(curTable) &&
					CreateTable_Products(curTable);
		}
		catch (Exception e) {
	    	System.out.println(curTable + " couldn't be created because: ");
			System.out.println(e);
			return false;
		}
	}

	private boolean CreateTable_Order(StringBuilder curTableName) throws SQLException, Exception {
		curTableName = new StringBuilder("ORDERS");
    	System.out.println(curTableName+" ?");

		Statement stmt =  DBController.getConnection().createStatement();
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS "+curTableName+" ("
				+ " id int NOT NULL IDENTITY,"
				+ " user_id int NOT NULL,"
				+ " status varchar(255) NOT NULL,"
				+ " created_at varchar(255) NOT NULL,"
				+ " PRIMARY KEY (id)"
				+ ")");
	    DatabaseMetaData databaseMetaData = DBController.getConnection().getMetaData();
		ResultSet rs = databaseMetaData.getTables(null, null, curTableName.toString(), new String[] {"TABLE"});
		
 	    while (rs.next()) {
            String tName = rs.getString("TABLE_NAME");
            if (tName != null && tName.equals(curTableName.toString())) {
            	System.out.println(curTableName+" exist");
            	return true;
            }
        }
 	    
		return false;
	}
	
	private boolean CreateTable_OrderItems(StringBuilder curTableName) throws SQLException, Exception {
		curTableName = new StringBuilder("ORDER_ITEMS");
		
		Statement stmt =  DBController.getConnection().createStatement();
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS "+curTableName+" ("
				+ "  order_id int NOT NULL,"
				+ "  product_id int NOT NULL,"
				+ "  quantity int NOT NULL"
				+ ")");
		
		try {
			stmt.executeUpdate("ALTER TABLE "+curTableName+" ADD CONSTRAINT unique_order_id UNIQUE(order_id)");	
		} catch  (Exception e) {
			System.err.println(e);
		}
		
		DatabaseMetaData databaseMetaData = DBController.getConnection().getMetaData();
		ResultSet rs = databaseMetaData.getTables(null, null, curTableName.toString(), new String[] {"TABLE"});
		
 	    while (rs.next()) {
            String tName = rs.getString("TABLE_NAME");
            if (tName != null && tName.equals(curTableName.toString())) {
            	System.out.println(curTableName+" exist");
            	return true;
            }
        }
 	    
		return false;
	};
	
	private boolean CreateTable_Products(StringBuilder curTableName) throws SQLException, Exception {
		curTableName = new StringBuilder("PRODUCTS");
		
		Statement stmt =  DBController.getConnection().createStatement();
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS "+curTableName+" ("
				+ "  id int NOT NULL IDENTITY,"
				+ "  name varchar(255) NOT NULL,"
				+ "  price int NOT NULL,"
				+ "  status int NOT NULL,"
				+ "  created_at datetime NOT NULL,"
				+ "  PRIMARY KEY (id)"
				+ ")");
		
		DatabaseMetaData databaseMetaData = DBController.getConnection().getMetaData();
		ResultSet rs = databaseMetaData.getTables(null, null, curTableName.toString(), new String[] {"TABLE"});
		
 	    while (rs.next()) {
            String tName = rs.getString("TABLE_NAME");
            if (tName != null && tName.equals(curTableName.toString())) {
            	System.out.println(curTableName+" exist");
            	return true;
            }
        }
 	    
		return false;
	};
}