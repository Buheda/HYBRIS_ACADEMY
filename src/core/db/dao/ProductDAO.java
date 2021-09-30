package core.db.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import core.db.DBConnection;
import core.db.entity.Products;
import core.db.entity.Products_status;

public class ProductDAO {

	public static int createProduct(String name, int price, Products_status status, Timestamp created_at) throws Exception {
		String sql = "INSERT INTO PRODUCTS (name, price, status, created_at) Values (?, ?, ?, ?)";
		PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql, 
				Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, name);
		preparedStatement.setInt(2, price);
		preparedStatement.setInt(3, status.ordinal());
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
		ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCTS inner join product_status on status=is");
		List<Products> productList = new ArrayList<Products>();
		while(rs.next()) {
			Products product = new Products();      
			product.setId(rs.getInt("id"));
			product.setName(rs.getString("name"));
			product.setPrice(rs.getInt("price"));
			product.setStatus(Products_status.fromInteger(rs.getInt("status")));
			product.setCreated_at(rs.getTimestamp("created_at"));
			productList.add(product);
		}
		
		rs.close();
		stmt.close();
		return productList;
	}
	
	public static void getAllOrderedProductsList() {
		/*SELECT ID, PRICE, STATUS, sum(quantity) as quantity FROM PRODUCTS INNER JOIN ORDER_ITEMS on PRODUCTS.id = ORDER_ITEMS.product_id group by id, price, status*/
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
