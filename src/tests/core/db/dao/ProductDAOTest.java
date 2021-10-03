package tests.core.db.dao;

import static org.junit.Assert.*;

import java.sql.SQLIntegrityConstraintViolationException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import core.db.dao.OrderDAO;
import core.db.dao.ProductDAO;
import core.db.entity.Order_items;
import core.util.DateTimeFormatter;
import tests.core.commands.TestProductQueries;

public class ProductDAOTest {

	@Before
	public void before() throws Exception {
		TestProductQueries.cleanDB();
	}
	
	@AfterClass
	public static void AfterClass() throws Exception {
		TestProductQueries.cleanDB();
	}
	
	@Test
	public void testCreateProduct_True() throws Exception {
		int productId = ProductDAO.createProduct("testProduct", 
				10, 
				"out_of_stock", 
				DateTimeFormatter.getNow());
		assertTrue(TestProductQueries.isProductsExistsById(productId));
	}
	
	@Test(expected = SQLIntegrityConstraintViolationException.class)
	public void testCreateProduct_False_Exception() throws Exception {
		ProductDAO.createProduct("testProduct", 
				10, 
				"fdzgdfg", 
				DateTimeFormatter.getNow());
	}
	
	@Test
	public void testCreateProduct_False_NotExists() throws Exception {
		int productId = ProductDAO.createProduct("testProduct", 
				10, 
				"out_of_stock", 
				DateTimeFormatter.getNow());
		assertFalse(TestProductQueries.isProductsExistsById(++productId));
	}
	
	@Test
	public void testGetAllProducts_MaxLength_21() throws Exception {
		ProductDAO.createProduct("testProduct_maxlength", 
				10, 
				"out_of_stock", 
				DateTimeFormatter.getNow());
		assertEquals(21, ProductDAO.getAllProducts_MaxLength());
	}
	
	@Test
	public void testGetAllProducts_MaxLength__23() throws Exception {
		ProductDAO.createProduct("testProduct_maxlength23", 
				10, 
				"out_of_stock", 
				DateTimeFormatter.getNow());
		assertEquals(23, ProductDAO.getAllProducts_MaxLength());
	}
	

	@Test
	public void testIsProductsExistsById_True() throws Exception {
		int productId = TestProductQueries.createTestProduct();
		assertTrue(ProductDAO.isProductsExistsById(productId));
	}

	@Test
	public void testIsProductsExistsById_False() throws Exception {
		assertFalse(ProductDAO.isProductsExistsById(10));
	}
	
	@Test
	public void testGetProductsList_True() throws Exception {
		assertEquals(0, ProductDAO.getProductsList().size());
		TestProductQueries.createTestProduct();
		assertEquals(1, ProductDAO.getProductsList().size());
		TestProductQueries.createTestProduct();
		assertEquals(2, ProductDAO.getProductsList().size());
	}
	
	@Test
	public void testGetAllOrderedProductsList_True() throws Exception {
		int orderId = TestProductQueries.createTestOrder();
		int productId1 = TestProductQueries.createTestProduct();
		int productId2 = TestProductQueries.createTestProduct();
		
		TestProductQueries.createTestOrderItem(new Order_items(orderId, productId1, 1));
		TestProductQueries.createTestOrderItem(new Order_items(orderId, productId2, 2));
		
		assertTrue(OrderDAO.getOrderFullInfoById(orderId).next());
	}
	
	@Test
	public void testGetAllOrderedProductsList_Falsee() throws Exception {
		assertTrue(OrderDAO.getOrderFullInfoById(10).next());
	}

	
	@Test
	public void testRemoveProductsById_True() throws Exception {
		int productId = ProductDAO.createProduct("testProduct", 
				100,
				"in_stock",
				DateTimeFormatter.getNow());
		assertTrue(TestProductQueries.isProductsExistsById(productId));
		
		ProductDAO.removeProductsById(productId);
		assertFalse(TestProductQueries.isProductsExists());
	}

	@Test
	public void testRemoveAllProducts_True() throws Exception {
		TestProductQueries.createTestProduct();
		TestProductQueries.createTestProduct();
		assertTrue(TestProductQueries.isProductsExists());
		
		ProductDAO.removeAllProducts();
		assertFalse(TestProductQueries.isProductsExists());
	}

}
