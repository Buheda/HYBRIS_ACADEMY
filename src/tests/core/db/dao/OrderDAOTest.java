package tests.core.db.dao;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import core.db.dao.OrderDAO;
import core.db.entity.Order_items;
import core.util.DateTimeFormatter;
import tests.core.commands.TestProductQueries;

public class OrderDAOTest {

	@Before
	public void before() throws Exception {
		TestProductQueries.cleanDB();
	}
	
	@AfterClass
	public static void AfterClass() throws Exception {
		TestProductQueries.cleanDB();
	}
	
	@Test
	public void testCreateOrder_True() throws Exception {
		int orderId = OrderDAO.createOrder(100, 
				"in_stock", 
				DateTimeFormatter.timestampToStr(DateTimeFormatter.getNow()));
		assertTrue(TestProductQueries.isOrderExistsById(orderId));
	}

	@Test
	public void testCreateOrder_False_NotExists() throws Exception {
		int orderId = OrderDAO.createOrder(100, 
				"in_stock", 
				DateTimeFormatter.timestampToStr(DateTimeFormatter.getNow()));
		assertFalse(TestProductQueries.isOrderExistsById(++orderId));
	}
		
	@Test
	public void testCreateOrderItem_True() throws Exception {
		assertTrue(OrderDAO.createOrderItem(new Order_items(10, 100, 1)));	
	}
	
	@Test
	public void removeOrderById_True()  throws Exception {
		int orderId = OrderDAO.createOrder(100, 
				"in_stock", 
				DateTimeFormatter.timestampToStr(DateTimeFormatter.getNow()));
		assertTrue(TestProductQueries.isOrderExistsById(orderId));
		
		OrderDAO.removeOrderById(orderId);
		assertFalse(TestProductQueries.isOrderExistsById(orderId));
		
	}
	
	@Test
	public void testCreateOrderItem_False_AlreadyExists() throws Exception {
		assertTrue(OrderDAO.createOrderItem(new Order_items(10, 100, 1)));
		assertFalse(OrderDAO.createOrderItem(new Order_items(10, 100, 1)));	
	}

	@Test
	public void testUpdateOrderedItemQuantity_True()  throws Exception {
		assertTrue(OrderDAO.createOrderItem(new Order_items(10, 100, 1)));
		assertTrue(OrderDAO.updateOrderedItemQuantity(10, 100, 15));	
	}
	
	@Test
	public void testUpdateOrderedItemQuantity_False()  throws Exception {
		assertFalse(OrderDAO.updateOrderedItemQuantity(10, 100, 15));	
	}
	
	@Test
	public void testGetAllOrdersFullInfoList_True()  throws Exception {
		int orderId = TestProductQueries.createTestOrder();
		int productId1 = TestProductQueries.createTestProduct();
		int productId2 = TestProductQueries.createTestProduct();
		
		TestProductQueries.createTestOrderItem(new Order_items(orderId, productId1, 1));
		TestProductQueries.createTestOrderItem(new Order_items(orderId, productId2, 2));

		assertFalse(OrderDAO.getAllOrdersFullInfoList().isEmpty());
	}	
	
	@Test
	public void testGetAllOrdersFullInfoList_False_NotFound()  throws Exception {	
		assertTrue(OrderDAO.getAllOrdersFullInfoList().isEmpty());
	}
	
	@Test
	public void testGetOrderFullInfoById_True()  throws Exception {
		int orderId = TestProductQueries.createTestOrder();
		int productId1 = TestProductQueries.createTestProduct();
		int productId2 = TestProductQueries.createTestProduct();
		
		TestProductQueries.createTestOrderItem(new Order_items(orderId, productId1, 1));
		TestProductQueries.createTestOrderItem(new Order_items(orderId, productId2, 2));
		
		assertFalse(OrderDAO.getOrderFullInfoById(orderId).isEmpty());
	}
	
	@Test
	public void testGetOrderFullInfoById_False_NotFound()  throws Exception {
		assertTrue(OrderDAO.getOrderFullInfoById(10).isEmpty());
	}	
}
