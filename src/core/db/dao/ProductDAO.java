package core.db.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import core.db.DBConnection;
import core.db.entity.Products;

public class ProductDAO {

	public static int createProduct(String name, int price, String status, Timestamp created_at) throws Exception {
		String sql = "INSERT INTO PRODUCTS (name, price, status, created_at) Values (?, ?, ?, ?)";
		PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql, 
				Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, name);
		preparedStatement.setInt(2, price);
		preparedStatement.setString(3, status);
		preparedStatement.setTimestamp(4, created_at);
		preparedStatement.executeUpdate(); 
		ResultSet rs = preparedStatement.getGeneratedKeys();

		rs.next();
		int result = rs.getInt("id");
				
		preparedStatement.close();
		return result;
	}
	
	
	public static int getAllProducts_MaxLength() {
		int result = 0;
		try {
			Statement stmt =  DBConnection.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT MAX(LENGTH(name)) as length FROM PRODUCTS");
			rs.next();
			result = rs.getInt("length");
		
			rs.close();
			stmt.close();
		} catch (Exception e) {
		}
		return result;
	}
	
	public static boolean isProductsExists() throws Exception {
		Statement stmt =  DBConnection.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCTS");
		boolean result = rs.next();
		rs.close();
		stmt.close();
		return result;
	}
	
	public static boolean isProductsExistsById(int id) throws Exception {
		Statement stmt =  DBConnection.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCTS where id="+id);
		boolean result = rs.next();
		rs.close();
		stmt.close();
		return result;
	}
	
	public static List<Products> getProductsList() throws Exception{
		Statement stmt =  DBConnection.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCTS");
		List<Products> productList = new ArrayList<Products>();
		while(rs.next()) {
			Products product = new Products(rs.getInt("id"),
											rs.getString("name"),
											rs.getInt("price"),
											rs.getString("status"),
											rs.getTimestamp("created_at"));
			productList.add(product);
		}
		
		rs.close();
		stmt.close();
		return productList;
	}
	
	public static ResultSet getAllOrderedProductsList() throws Exception {
		Statement stmt =  DBConnection.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT product_id as id, name, price, status, sum(quantity) as quantity "
				+ "FROM PRODUCTS INNER JOIN ORDER_ITEMS on PRODUCTS.id = ORDER_ITEMS.product_id "
				+ "group by product_id, name, price, status order by quantity desc");
		stmt.close();
		return rs;		
	}

	public static void removeProductsById(int id) throws Exception {
		Statement stmt =  DBConnection.getConnection().createStatement();
		stmt.executeUpdate("DELETE FROM PRODUCTS where ID ="+id);
		stmt.close();
	}

	public static void removeAllProducts() throws Exception {
		Statement stmt =  DBConnection.getConnection().createStatement();
		stmt.executeUpdate("DELETE FROM PRODUCTS");
		stmt.close();
	}
}
