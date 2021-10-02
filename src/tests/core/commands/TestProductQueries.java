package tests.core.commands;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import core.db.DBConnection;
import core.db.entity.Order_items;
import core.util.DateTimeFormatter;

public class TestProductQueries {

	public static void cleanDB() throws Exception {
		removeAllOrderItems();
		removeAllOrders();
		removeAllProducts();
	}
	
	public static int createTestProduct() throws Exception {
		String sql = "INSERT INTO PRODUCTS (name, price, status, created_at) Values (?, ?, ?, ?)";
		PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, "testName");
		preparedStatement.setInt(2, 10);
		preparedStatement.setString(3, "in_stock");
		preparedStatement.setTimestamp(4, DateTimeFormatter.getNow());
		preparedStatement.executeUpdate();
		
		ResultSet rs = preparedStatement.getGeneratedKeys();

		rs.next();
		int result = rs.getInt("id");
				
		preparedStatement.close();
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
	
	public static int removeProductsById(int id) throws Exception {
		Statement stmt =  DBConnection.getConnection().createStatement();
		int result = stmt.executeUpdate("DELETE * FROM PRODUCTS where ID ="+id);
		stmt.close();
		return result;
	}
	
	public static int removeAllProducts() throws Exception {
		Statement stmt =  DBConnection.getConnection().createStatement();
		int result = stmt.executeUpdate("DELETE FROM PRODUCTS");
		stmt.close();
		return result;
	}
	
	public static void createTestOrderItem(Order_items item) throws Exception {
		String sql = "INSERT INTO ORDER_ITEMS (order_id, product_id, quantity) Values (?, ?, ?)";
		PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
		preparedStatement.setInt(1, item.getOrder_id());
		preparedStatement.setInt(2, item.getProduct_id());
		preparedStatement.setInt(3, item.getQuantity());
		preparedStatement.executeUpdate(); 
		preparedStatement.close();
	}
	
	public static int getOrderItemsQuantity(int orderid, int productid) throws Exception {
		String sql = "SELECT QUANTITY FROM ORDER_ITEMS Where order_id = ? and product_id = ?";
		PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
		preparedStatement.setInt(1, orderid);
		preparedStatement.setInt(2, productid);
		ResultSet rs = preparedStatement.executeQuery(); 
		
		int result = -1;
		if (rs.next()) {
			result = rs.getInt("quantity");
		}
		
		preparedStatement.close();
		return result;
	}
	
	public static void removeAllOrderItems() throws Exception {
		Statement stmt =  DBConnection.getConnection().createStatement();
		stmt.executeUpdate("DELETE FROM ORDER_ITEMS");
		stmt.close();
	}

	public static int createTestOrder() throws Exception {
		String sql = "INSERT INTO ORDERS (user_id, status, created_at) Values (?, ?, ?)";
		PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql, 
				Statement.RETURN_GENERATED_KEYS);
	
		preparedStatement.setInt(1, 200+(int)(Math.random() * (101)));
		preparedStatement.setString(2, "test");
		preparedStatement.setString(3, DateTimeFormatter.timestampToStr(DateTimeFormatter.getNow()));
		preparedStatement.executeUpdate(); 
		ResultSet rs = preparedStatement.getGeneratedKeys();

		int result = -1;
		if (rs.next()) {
			result = rs.getInt("id");
		}
		
		preparedStatement.close();
		return result;
	}
	
	public static boolean isOrderExistsById(int id) throws Exception {
		Statement stmt =  DBConnection.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM ORDERS where id="+id);
		boolean result = rs.next();
		rs.close();
		stmt.close();
		return result;
	}
	
	public static boolean createOrderItem(Order_items item) throws Exception {
		String sql = "INSERT INTO ORDER_ITEMS (order_id, product_id, quantity) Values (?, ?, ?)";
		PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
		preparedStatement.setInt(1, item.getOrder_id());
		preparedStatement.setInt(2, item.getProduct_id());
		preparedStatement.setInt(3, item.getQuantity());
		
		boolean result = false;
		try {
			int rowCount = preparedStatement.executeUpdate();
			result = rowCount > 0;
		} catch (SQLIntegrityConstraintViolationException E) {
			System.err.println("Order Item wasn't added. The same product already added to order");	
		}
		
		preparedStatement.close();
		return result;
	}

	public static void removeAllOrders() throws Exception {
		Statement stmt =  DBConnection.getConnection().createStatement();
		stmt.executeUpdate("DELETE FROM ORDERS");
		stmt.close();
	}

}
