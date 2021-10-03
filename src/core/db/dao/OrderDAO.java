package core.db.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;

import core.db.DBConnection;
import core.db.entity.Order_items;

public abstract class OrderDAO {
	
	public static int createOrder(int user_id, String status, String created_at) throws Exception {
		String sql = "INSERT INTO ORDERS (user_id, status, created_at) Values (?, ?, ?)";
		PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql, 
				Statement.RETURN_GENERATED_KEYS);
		
		preparedStatement.setInt(1, user_id);
		preparedStatement.setObject(2, status);
		preparedStatement.setString(3, created_at);
		preparedStatement.executeUpdate(); 
		ResultSet rs = preparedStatement.getGeneratedKeys();

		rs.next();
		int result = rs.getInt(1);
				
		preparedStatement.close();
		return result;
	}
	
	public static void removeOrderById(int id) throws Exception {
		Statement stmt =  DBConnection.getConnection().createStatement();
		stmt.executeUpdate("DELETE FROM ORDERS where ID ="+id);
		stmt.close();
	}
	
	public static boolean createOrderItem(Order_items item) throws Exception {
		boolean result = false;
		String sql = "INSERT INTO ORDER_ITEMS (order_id, product_id, quantity) Values (?, ?, ?)";
		PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
		preparedStatement.setInt(1, item.getOrder_id());
		preparedStatement.setInt(2, item.getProduct_id());
		preparedStatement.setInt(3, item.getQuantity());
		try {
			preparedStatement.executeUpdate();
			result = true;
		} catch (Exception E) {
		}
		
		preparedStatement.close();
		return result;
	}

	public static boolean updateOrderedItemQuantity(int orderid, int productid, int quantity) throws Exception {
		String sql = "UPDATE ORDER_ITEMS SET quantity=? where order_id = ? and product_id = ?";
		PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
		preparedStatement.setInt(1, quantity);
		preparedStatement.setInt(2, orderid);
		preparedStatement.setInt(3, productid);
		int rowCount = preparedStatement.executeUpdate(); 
		preparedStatement.close();
		
		return rowCount>0;
	}

	public static List<HashMap<Object, Object>> getAllOrdersFullInfoList() throws Exception {
		Statement stmt =  DBConnection.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT ORDERS.id as id, price*quantity as price, name, quantity, ORDERS.created_at  as created_at"
				+ " FROM PRODUCTS INNER JOIN ORDER_ITEMS on PRODUCTS.id = ORDER_ITEMS.product_id "
				+ "inner join orders on orders.id = order_items.order_id order by orders.id");

		List<HashMap<Object, Object>> result = Util.parseResultSet(rs);
		
		rs.close();
		stmt.close();
		return result;	
	}

	public static List<HashMap<Object, Object>> getOrderFullInfoById(int id) throws Exception {
		Statement stmt =  DBConnection.getConnection().createStatement();
		ResultSet rs = stmt.executeQuery("SELECT ORDERS.id as id, price*quantity as price, name, quantity, ORDERS.created_at  as created_at"
				+ " FROM PRODUCTS INNER JOIN ORDER_ITEMS on PRODUCTS.id = ORDER_ITEMS.product_id "
				+ "inner join orders on orders.id = order_items.order_id where orders.id="+id+" order by orders.id");

		List<HashMap<Object, Object>> result = Util.parseResultSet(rs);

		rs.close();
		stmt.close();
		return result;	
	}
	
}
