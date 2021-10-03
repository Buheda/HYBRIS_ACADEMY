package core.db.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import core.db.DBConnection;
import core.db.entity.Products;

public abstract class ProductDAO {

	public static int createProduct(String name, int price, String status, Timestamp created_at) throws Exception {
		int result = -1;

		String sql = "INSERT INTO PRODUCTS (name, price, status, created_at) Values (?, ?, ?, ?)";
		PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql, 
				Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, name);
		preparedStatement.setInt(2, price);
		preparedStatement.setObject(3, status);
		preparedStatement.setTimestamp(4, created_at);
		try {
			preparedStatement.executeUpdate(); 
			ResultSet rs = preparedStatement.getGeneratedKeys();
			rs.next();
			result = rs.getInt(1);					
		} catch (Exception E) {
		}
		
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
			result = -1;
		}
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
	
	public static List<HashMap<Object, Object>> getAllOrderedProductsList() throws Exception {
		Statement stmt =  DBConnection.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT product_id as id, name, price, status, sum(quantity) as quantity "
				+ "FROM PRODUCTS INNER JOIN ORDER_ITEMS on PRODUCTS.id = ORDER_ITEMS.product_id "
				+ "group by product_id, name, price, status order by quantity desc");
		List<HashMap<Object, Object>> result = Util.parseResultSet(rs);

		rs.close();
		stmt.close();
		return result;		
	}

	public static boolean removeProductsById(int id) throws Exception {
		Statement stmt =  DBConnection.getConnection().createStatement();
		int result = stmt.executeUpdate("DELETE FROM PRODUCTS where ID ="+id + " and id NOT in (SELECT product_id FROM order_items)");
		stmt.close();
		
		return result>0;
	}

	public static boolean removeAllProducts() throws Exception {
		Statement stmt =  DBConnection.getConnection().createStatement();
		int result = stmt.executeUpdate("DELETE FROM PRODUCTS where id NOT in (SELECT product_id FROM order_items)");
		stmt.close();
		
		return result>0;
	}
}
