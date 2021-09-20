import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class CheckDB {	
	private static boolean checkTable(Connection connection, String tableName, String createQuery) {
		System.out.print("check `"+tableName+"`: ");

		try {
			Statement stmt = connection.createStatement();  
			stmt.executeUpdate(createQuery);  
			
			DatabaseMetaData databaseMetaData = connection.getMetaData();
			ResultSet rs = databaseMetaData.getTables(null, null, tableName, new String[] {"TABLE"});			
     	    while (rs.next()) { 
	            String tName = rs.getString("TABLE_NAME");
	            if (tName != null && tName.equals(tableName)) {
	            	System.out.println("exist");
	            	return true;
	            }
	        }  			
		}
		catch (Exception e) {
        	System.out.println("couldn't be created because: ");
			System.out.println(e);
			return false;
		}
		System.out.println("not exist");
		return false;
	}

	public static void main(String[] args) {
		try {
			Properties prop = Settings.getDbConfig();
			Class.forName("com.mysql.jdbc.Driver");
		
			Connection connection = DriverManager.getConnection(prop.getProperty("db.url"),prop.getProperty("db.user"),prop.getProperty("db.password"));
			
			boolean isExistTables = 
			checkTable(connection, "orders", "CREATE TABLE IF NOT EXISTS `orders` ("
					+ " `id` int(11) NOT NULL AUTO_INCREMENT,"
					+ " `user_id` int(11) NOT NULL,"
					+ " `status` varchar(255) NOT NULL,"
					+ " `created_at` varchar(255) NOT NULL,"
					+ " PRIMARY KEY (`id`)"
					+ ")") &&
			checkTable(connection, "order_items", "CREATE TABLE IF NOT EXISTS `order_items` ("
					+ "  `order_id` int(11) NOT NULL,"
					+ "  `product_id` int(11) NOT NULL,"
					+ "  `quantity` int(11) NOT NULL,"
					+ "  UNIQUE KEY `order_id` (`order_id`)"
					+ ")") &&
			checkTable(connection, "products", "CREATE TABLE IF NOT EXISTS `products` ("
					+ "  `id` int(11) NOT NULL AUTO_INCREMENT,"
					+ "  `name` varchar(255) NOT NULL,"
					+ "  `price` int(11) NOT NULL,"
					+ "  `status` enum('out_of_stock','in_stock','running_low','') NOT NULL,"
					+ "  `created_at` datetime NOT NULL,"
					+ "  PRIMARY KEY (`id`)"
					+ ")");
			if (isExistTables) {				
				System.out.println("All tables exist");
			} 
			else
				System.out.println("Not all tables exist");
			
			connection.close(); 
		} catch(Exception e){ 
			System.out.println(e);
		}
	}
}




