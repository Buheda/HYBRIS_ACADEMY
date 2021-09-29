package core.db.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;

import core.db.DBConnection;
import core.db.entity.Order;
import core.db.entity.Order_items;
import core.db.entity.Products;
import core.util.DateTimeFormatter;

public class OrderDAO {

	private static int createNewOrder() throws Exception {
		String sql = "INSERT INTO ORDERS (user_id, status, created_at) Values (?, ?, ?)";
		PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		int user_id = (int)(Math.random() * (101));
		
		preparedStatement.setInt(1, user_id);
		preparedStatement.setString(2, "new");
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
	
	private static boolean createOrderItem(Order_items item) throws Exception {
		String sql = "INSERT INTO ORDER_ITEMS (order_id, product_id, quantity) Values (?, ?, ?)";
		PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql);
		preparedStatement.setInt(1, item.getOrder_id());
		preparedStatement.setInt(2, item.getProduct_id());
		preparedStatement.setInt(3, item.getQuantity());
		int rowCount = preparedStatement.executeUpdate(); 
		preparedStatement.close();
		
		return rowCount>0;
	}
	
	public static int createOrder(ArrayList<Integer> productsList) throws Exception {
		boolean isOrderCreated = false;
		Order newOrder = null;
		int resultOrderId = -1;
		boolean isOrderItemsCreated = false;
		
		for (Integer productId : productsList) {
			if (ProductDAO.isProductsExistsById(productId)) {
				if (!isOrderCreated) {
					resultOrderId = createNewOrder();
					if (-1 == resultOrderId) {
						break;
					}
					isOrderCreated = true;
				}
				if (isOrderCreated) {
					Order_items item = new Order_items(resultOrderId, productId, 1);
					if (createOrderItem(item)) {
						isOrderItemsCreated = true;
					}
				}
			}
		}
		
		if (!isOrderItemsCreated) {
			removeOrderById(resultOrderId);
			resultOrderId = -1;
		}
		
		return resultOrderId;
	}

	private static int removeOrderById(int id) throws Exception {
		Statement stmt =  DBConnection.getConnection().createStatement();
		int result = stmt.executeUpdate("DELETE FROM ORDERS where ID ="+id);
		stmt.close();
		return result;
	}
}
