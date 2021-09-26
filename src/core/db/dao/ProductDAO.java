package core.db.dao;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.Statement;

import core.db.entity.Products;

public class ProductDAO {

	public static boolean CreateProduct(Products product) {
		
		/*boolean isProductCreated = false;
		   String sql = "INSERT INTO Products (ProductName, Price) Values (?, ?)";
		   PreparedStatement preparedStatement = conn.prepareStatement(sql);
		                preparedStatement.setString(1, name);
		                preparedStatement.setInt(2, price);
		                 
		                int rows = preparedStatement.executeUpdate();
		                 
		                System.out.printf("%d rows added", rows);
		*/
		return false;
	}

	public void GetAllProductsList() {
	}

	public void GetAllOrderProductsList() {
	}

	public void RemoveProductsById(int id) {
	}

	public void RemoveAllProducts(String password) {
	}
}
