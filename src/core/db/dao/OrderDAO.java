package core.db.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import core.db.DBConnection;
import core.db.entity.Order_items;

public class OrderDAO {

	public static int createOrder(ArrayList<Integer> productsList) {
		int user_id = (int)(Math.random() * (101));
		Order_items item = new Order_items(user_id, Integer.parseInt(param), 1);
		/*String sql = "INSERT INTO ORDER_ITEMS (name, price, status, created_at) Values (?, ?, ?, ?)";
		PreparedStatement preparedStatement = DBConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, product.getName());
		preparedStatement.setInt(2, product.getPrice());
		preparedStatement.setInt(3, product.getStatus().ordinal());
		preparedStatement.setTimestamp(4, product.getCreated_at());
		preparedStatement.executeUpdate(); 
		ResultSet rs = preparedStatement.getGeneratedKeys();
*/
		int result = -1;
		/*if (rs.next()) {
			result = rs.getInt("id");
		}
		
		preparedStatement.close();*/
		return result;
	}

	private static int createOrderItem(Order_items item) {
		/*String sql = "INSERT INTO ORDER_ITEMS (name, price, status, created_at) Values (?, ?, ?, ?)";
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
		return result;*/
	}
}
