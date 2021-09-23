package db;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database_MySql implements Database {

	@Override
	public boolean createDatabase() throws Exception {
		String curTable = "Tables";
		try {
			return CreateTable_Order(curTable) &&
					CreateTable_OrderItems(curTable) &&
					CreateTable_Products(curTable);
		}
		catch (Exception e) {
	    	System.err.println(curTable + " couldn't be created because: ");
			System.err.println(e);
			throw e;
		}
	}

	private boolean CreateTable_Order(String curTableName) throws SQLException, Exception {
		boolean isTableExist = false;
		curTableName = "ORDERS";
		
		Statement stmt =  DBController.getConnection().createStatement();
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `"+curTableName+"` ("
				+ " `id` int NOT NULL AUTO_INCREMENT,"
				+ " `user_id` int NOT NULL,"
				+ " `status` varchar(255) NOT NULL,"
				+ " `created_at` varchar(255) NOT NULL,"
				+ " PRIMARY KEY (`id`)"
				+ ")");  
	    DatabaseMetaData databaseMetaData = DBController.getConnection().getMetaData();
		ResultSet rs = databaseMetaData.getTables(null, null, curTableName, new String[] {"TABLE"});
		
 	    while (rs.next()) {
            String tName = rs.getString("TABLE_NAME");
            if (tName != null && tName.equals(curTableName)) {
            	System.out.println(curTableName+" exist");
            	isTableExist = true;
            }
        }
		rs.close();
		stmt.close();
		return isTableExist;
	}
	
	private boolean CreateTable_OrderItems(String curTableName) throws SQLException, Exception {
		boolean isTableExist = false;
		curTableName = "ORDE_ITEMS";
		
		Statement stmt =  DBController.getConnection().createStatement();
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `"+curTableName+"` ("
				+ "  `order_id` int NOT NULL,"
				+ "  `product_id` int NOT NULL,"
				+ "  `quantity` int NOT NULL,"
				+ "  UNIQUE KEY `order_id` (`order_id`)"
				+ ")");  
	    DatabaseMetaData databaseMetaData = DBController.getConnection().getMetaData();
		ResultSet rs = databaseMetaData.getTables(null, null, curTableName, new String[] {"TABLE"});
		
 	    while (rs.next()) {
            String tName = rs.getString("TABLE_NAME");
            if (tName != null && tName.equals(curTableName)) {
            	System.out.println(curTableName+" exist");
            	isTableExist = true;
            }
        }
		rs.close();
		stmt.close();
		return isTableExist;
	};
	
	private boolean CreateTable_Products(String curTableName) throws SQLException, Exception {
		boolean isTableExist = false;
		curTableName = "PRODUCTS";
		
		Statement stmt =  DBController.getConnection().createStatement();
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS `"+curTableName+"` ("
			+ "  `id` int NOT NULL AUTO_INCREMENT,"
			+ "  `name` varchar(255) NOT NULL,"
			+ "  `price` int NOT NULL,"
			+ "  `status` enum('out_of_stock','in_stock','running_low') NOT NULL,"
			+ "  `created_at` datetime NOT NULL,"
			+ "  PRIMARY KEY (`id`)"
			+ ")");  
	    DatabaseMetaData databaseMetaData = DBController.getConnection().getMetaData();
		ResultSet rs = databaseMetaData.getTables(null, null, curTableName, new String[] {"TABLE"});
		
 	    while (rs.next()) {
            String tName = rs.getString("TABLE_NAME");
            if (tName != null && tName.equals(curTableName)) {
            	System.out.println(curTableName+" exist");
            	isTableExist = true;
            }
        }
		rs.close();
		stmt.close();
		return isTableExist;
	};

}
