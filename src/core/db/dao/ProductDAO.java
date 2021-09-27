package core.db.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.security.sasl.AuthenticationException;

import core.db.DBConnection;
import core.db.entity.Products;
import core.db.entity.Products_status;

public class ProductDAO {

	public static int createProduct(Products product) throws Exception {
		String sql = "INSERT INTO PRODUCTS (name, price, status, created_at) Values (?, ?, ?, ?)";
		PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
		preparedStatement.setString(1, product.getName());
		preparedStatement.setInt(2, product.getPrice());
		preparedStatement.setInt(3, product.getStatus().ordinal());
		preparedStatement.setTimestamp(4, product.getCreated_at());

		return preparedStatement.executeUpdate();
	}
	
	public static int getAllProducts_MaxLength() throws Exception  {
		Statement stmt =  DBConnection.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT MAX(LENGTH(name)) as length FROM PRODUCTS");
		if (rs.next()) {
			return rs.getInt("length");
		}
		else
			return 0;
	}
	
	public static boolean isProductsExists() throws Exception {
		Statement stmt =  DBConnection.getConnection().createStatement();
		return stmt.execute("SELECT * FROM PRODUCTS");
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
		return productList;
	}
	
	public static void getAllOrderedProductsList() {

	}

	public static int removeProductsById(int id) throws Exception {
		Statement stmt =  DBConnection.getConnection().createStatement();
		return stmt.executeUpdate("DELETE * FROM PRODUCTS where ID ="+id);
	}

	public static int removeAllProducts(String password) throws Exception {
		if (persistent.FinalProperties.REMOVE_ALL_PRODUCTS_PASSWORD.equals(password)) {
			Statement stmt =  DBConnection.getConnection().createStatement();
			return stmt.executeUpdate("DELETE FROM PRODUCTS");
		} else
			throw new AuthenticationException ("incorrect password for removing all products");
	}
	
}
