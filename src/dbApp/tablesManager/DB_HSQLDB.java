package dbApp.tablesManager;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import core.db.DBConnection;


public class DB_HSQLDB implements DB {

	@Override
	public boolean createTables() throws Exception {
		StringBuilder curTable = new StringBuilder("Tables");

		try {
			return CreateTable_Order(curTable) &&
					CreateTable_OrderItems(curTable) &&
					CreateTable_Products(curTable) &&
					CreateTable_ProductsStatus(curTable);
		}
		catch (Exception e) {
			System.err.println(curTable + " couldn't be created because: ");
			System.err.println(e);
			throw e;
		}
	}

	private boolean CreateTable_Order(StringBuilder curTableName) throws Exception {
		boolean isTableExist = false;
		curTableName = new StringBuilder("ORDERS");

		Statement stmt =  DBConnection.getConnection().createStatement();
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS "+curTableName+" ("
				+ " id int NOT NULL IDENTITY,"
				+ " user_id int NOT NULL,"
				+ " status varchar(255) NOT NULL,"
				+ " created_at varchar(255) NOT NULL,"
				+ " PRIMARY KEY (id)"
				+ ")");
	    DatabaseMetaData databaseMetaData = DBConnection.getConnection().getMetaData();
		ResultSet rs = databaseMetaData.getTables(null, null, curTableName.toString(), new String[] {"TABLE"});
 	    while (rs.next()) {
            String tName = rs.getString("TABLE_NAME");
            if (tName != null && tName.equals(curTableName.toString())) {
            	System.out.println(curTableName+" exist");
            	isTableExist = true;
            }
        }
		rs.close();
		stmt.close();
		return isTableExist;
	}
	
	private boolean CreateTable_OrderItems(StringBuilder curTableName) throws Exception {
		boolean isTableExist = false;
		curTableName = new StringBuilder("ORDER_ITEMS");

		Statement stmt =  DBConnection.getConnection().createStatement();
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS "+curTableName+" ("
				+ "  order_id int NOT NULL,"
				+ "  product_id int NOT NULL,"
				+ "  quantity int NOT NULL"
				+ ")");
		
		try {
			stmt.executeUpdate("ALTER TABLE "+curTableName+" ADD CONSTRAINT unique_order_item UNIQUE(order_id, product_id)");	
		} catch  (Exception e) {
			//System.err.println(e);
		}
		
		DatabaseMetaData databaseMetaData = DBConnection.getConnection().getMetaData();
		ResultSet rs = databaseMetaData.getTables(null, null, curTableName.toString(), new String[] {"TABLE"});
		
 	    while (rs.next()) {
            String tName = rs.getString("TABLE_NAME");
            if (tName != null && tName.equals(curTableName.toString())) {
            	System.out.println(curTableName+" exist");
            	isTableExist = true;
            }
        }
		rs.close();
		stmt.close();
		return isTableExist;
	};
	
	private boolean CreateTable_Products(StringBuilder curTableName) throws Exception {
		boolean isTableExist = false;
		curTableName = new StringBuilder("PRODUCTS");
		
		Statement stmt =  DBConnection.getConnection().createStatement();
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS "+curTableName+" ("
				+ "  id int NOT NULL IDENTITY,"
				+ "  name varchar(255) NOT NULL,"
				+ "  price int NOT NULL,"
				+ "  status int NOT NULL,"
				+ "  created_at datetime NOT NULL,"
				+ "  PRIMARY KEY (id)"
				+ ")");
		
		DatabaseMetaData databaseMetaData = DBConnection.getConnection().getMetaData();
		ResultSet rs = databaseMetaData.getTables(null, null, curTableName.toString(), new String[] {"TABLE"});
		
 	    while (rs.next()) {
            String tName = rs.getString("TABLE_NAME");
            if (tName != null && tName.equals(curTableName.toString())) {
            	System.out.println(curTableName+" exist");
            	isTableExist = true;
            }
        }
		rs.close();
		stmt.close();
		return isTableExist;
	};
	
	private boolean CreateTable_ProductsStatus(StringBuilder curTableName) throws Exception {
		boolean isTableExist = false;
		curTableName = new StringBuilder("PRODUCTS_STATUS");
		
		Statement stmt =  DBConnection.getConnection().createStatement();
		stmt.executeUpdate("CREATE TABLE IF NOT EXISTS "+curTableName+" ("
				+ "  id int NOT NULL IDENTITY,"
				+ "  status varchar(255) NOT NULL,"
				+ "  PRIMARY KEY (id)"
				+ ")");
		
		DatabaseMetaData databaseMetaData = DBConnection.getConnection().getMetaData();
		ResultSet rs = databaseMetaData.getTables(null, null, curTableName.toString(), new String[] {"TABLE"});
		
 	    while (rs.next()) {
            String tName = rs.getString("TABLE_NAME");
            if (tName != null && tName.equals(curTableName.toString())) {
            	System.out.println(curTableName+" exist");
            	isTableExist = true;
            	stmt.executeUpdate("DELETE FROM "+curTableName);
            	stmt.executeUpdate("INSERT INTO "+curTableName+" (id, status) VALUES (0, 'out_of_stock')");
            	stmt.executeUpdate("INSERT INTO "+curTableName+" (id, status) VALUES (1, 'in_stock')");
            	stmt.executeUpdate("INSERT INTO "+curTableName+" (id, status) VALUES (2, 'running_low')");
            }
        }
		rs.close();
		stmt.close();
		return isTableExist;
	};
	
	@Override
	public boolean dropTables() throws Exception {
		String curTable = "Tables";
		try {
			return DropTable_OrderItems(curTable) &&
					DropTable_Order(curTable) &&
					DropTable_Products(curTable);
		}
		catch (Exception e) {
	    	System.err.println(curTable + " couldn't be dropped because: ");
			System.err.println(e);
			throw e;
		}
	};

	private boolean DropTable_Order(String curTableName) throws Exception {
		curTableName = "ORDERS";
		
		Statement stmt =  DBConnection.getConnection().createStatement();
		stmt.executeUpdate("DROP TABLE "+curTableName);  
		stmt.close();
		return true;
	}
	
	private boolean DropTable_OrderItems(String curTableName) throws Exception {
		curTableName = "ORDER_ITEMS";
		
		Statement stmt =  DBConnection.getConnection().createStatement();
		stmt.executeUpdate("DROP TABLE "+curTableName);  
		stmt.close();
		return true;
	};
	
	private boolean DropTable_Products(String curTableName) throws SQLException, Exception {
		curTableName = "PRODUCTS";
		
		Statement stmt =  DBConnection.getConnection().createStatement();
		stmt.executeUpdate("DROP TABLE "+curTableName);  
		stmt.close();
		return true;
	}
}
