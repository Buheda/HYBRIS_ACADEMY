package core.db.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import core.db.DBConnection;
import core.db.entity.Products;
import core.db.entity.Products_status;
import core.persistent.CommandsErrors;

public class ProductDAO {

	public static int createProduct(Products product) throws Exception {
		String sql = "INSERT INTO PRODUCTS (name, price, status, created_at) Values (?, ?, ?, ?)";
		PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, product.getName());
		preparedStatement.setInt(2, product.getPrice());
		preparedStatement.setInt(3, product.getStatus().ordinal());
		preparedStatement.setTimestamp(4, product.getCreated_at());
		preparedStatement.executeUpdate(); 
		ResultSet rs = preparedStatement.getGeneratedKeys();

		int result = -1;
		if (rs.next()) {
			result = rs.getInt("id");
		}
		
		preparedStatement.close();
		return result;
	}
	
	public static int getAllProducts_MaxLength() throws Exception  {
		Statement stmt =  DBConnection.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT MAX(LENGTH(name)) as length FROM PRODUCTS");
		int result = 0;
		if (rs.next()) {
			result = rs.getInt("length");
		}
		
		rs.close();
		stmt.close();
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
	
	public static List<Products> getProductsList() throws Exception{
		Statement stmt =  DBConnection.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCTS");
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

	}

	public static int removeProductsById(int id) throws Exception {
		Statement stmt =  DBConnection.getConnection().createStatement();
		int result = stmt.executeUpdate("DELETE FROM PRODUCTS where ID ="+id);
		stmt.close();
		return result;
	}

	public static int removeAllProducts(String password) throws Exception {
		if (core.persistent.FinalProperties.REMOVE_ALL_PRODUCTS_PASSWORD.equals(password)) {
			Statement stmt =  DBConnection.getConnection().createStatement();
			int result = stmt.executeUpdate("DELETE FROM PRODUCTS");
			stmt.close();
			return result;
		} else {
			CommandsErrors.showCommandsErrorsMessage(CommandsErrors.INCORRECT_PASSWORD);
			return -1;
		}
		
	}
	
}
