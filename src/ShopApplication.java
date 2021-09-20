import java.sql.*;
import java.util.Properties;  

public class ShopApplication {
	public static void main(String[] args) {
		try {
			Properties prop = Settings.getDbConfig();
			Class.forName("com.mysql.jdbc.Driver");		
			Connection connection = DriverManager.getConnection(prop.getProperty("db.url"),prop.getProperty("db.user"),prop.getProperty("db.password"));
			System.out.println("connection - Ok");
			
			connection.close();			
		} catch(Exception e){ 
			System.out.println(e);
		}
	}

}