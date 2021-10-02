package tests.core.db.dao;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import core.db.dao.OrderDAO;
import core.db.entity.Order_items;
import core.util.DateTimeFormatter;
import tests.core.commands.TestProductQueries;

public class OrderDAOTests {

	@Before
	public void before() throws Exception {
		TestProductQueries.cleanDB();
	}
	
	@AfterClass
	public static void AfterClass() throws Exception {
		TestProductQueries.cleanDB();
	}
	
	@Test
	public void testCreateOrder_valid() throws Exception {
		int orderId = OrderDAO.createOrder(100, 
				"in_stock", 
				DateTimeFormatter.timestampToStr(DateTimeFormatter.getNow()));
		assertTrue(TestProductQueries.isOrderExistsById(orderId));
	}

	@Test
	public void testCreateOrder_Invalid_NotExists() throws Exception {
		int orderId = OrderDAO.createOrder(100, 
				"in_stock", 
				DateTimeFormatter.timestampToStr(DateTimeFormatter.getNow()));
		assertFalse(TestProductQueries.isOrderExistsById(++orderId));
	}
		
	@Test
	public void testCreateOrderItem() throws Exception {
		assertTrue(OrderDAO.createOrderItem(new Order_items(10, 100, 1)));	
	}
	
	@Test
	public void removeOrderById()  throws Exception {
		int orderId = OrderDAO.createOrder(100, 
				"in_stock", 
				DateTimeFormatter.timestampToStr(DateTimeFormatter.getNow()));
		assertTrue(TestProductQueries.isOrderExistsById(orderId));
		
		OrderDAO.removeOrderById(orderId);
		assertFalse(TestProductQueries.isOrderExistsById(orderId));
		
	}
	
	@Test
	public void testCreateOrderItem_Invalid_AlreadyExists() throws Exception {
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
	public void testGetAllOrdersFullInfoList()  throws Exception {
		int orderId = TestProductQueries.createTestOrder();
		int productId1 = TestProductQueries.createTestProduct();
		int productId2 = TestProductQueries.createTestProduct();
		
		TestProductQueries.createTestOrderItem(new Order_items(orderId, productId1, 1));
		TestProductQueries.createTestOrderItem(new Order_items(orderId, productId2, 2));
		
		assertTrue(OrderDAO.getAllOrdersFullInfoList().next());
	}	
	
	@Test
	public void testGetAllOrdersFullInfoList_NotFound()  throws Exception {	
		assertFalse(OrderDAO.getAllOrdersFullInfoList().next());
	}
	
	@Test
	public void testGetOrderFullInfoById()  throws Exception {
		int orderId = TestProductQueries.createTestOrder();
		int productId1 = TestProductQueries.createTestProduct();
		int productId2 = TestProductQueries.createTestProduct();
		
		TestProductQueries.createTestOrderItem(new Order_items(orderId, productId1, 1));
		TestProductQueries.createTestOrderItem(new Order_items(orderId, productId2, 2));
		
		assertTrue(OrderDAO.getOrderFullInfoById(orderId).next());
	}
	
	@Test
	public void testGetOrderFullInfoById_NotFound()  throws Exception {
		assertFalse(OrderDAO.getOrderFullInfoById(10).next());
	}	
}
