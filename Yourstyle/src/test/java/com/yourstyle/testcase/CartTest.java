package com.yourstyle.testcase;

import static org.junit.Assert.*;

import java.sql.Timestamp;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.yourstyle.dao.CartDao;
import com.yourstyle.dao.CategoryDao;
import com.yourstyle.dao.ProductDao;
import com.yourstyle.model.Cart;
import com.yourstyle.model.Category;



public class CartTest {
	
	@Autowired
	private static CartDao cartDao;
	
	@Autowired
	private static Cart cart;
	
	@Autowired
	private static ProductDao productDao;
	
	@BeforeClass
	public static void initialize(){
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.yourstyle.*");
		context.refresh();
		
		cart = (Cart) context.getBean("cart");
		
		cartDao = (CartDao) context.getBean("cartDao");
		
		productDao = (ProductDao) context.getBean("productDao");
	}
	
	@Ignore
	@Test
	public void addToCartTest(){
		
		cart.setProductId(101);
		cart.setQuantityAdded(2);
		cart.setStatus("ACTIVE");
		cart.setUserId(546);
		cart.setSubTotal(2 * productDao.getProductById(101).getPrice());
		cart.setCreatedBy("SYSTEM");
		cart.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
		assertTrue("Problem in saving to cart",cartDao.saveOrUpdate(cart));
	}
	
	@Ignore
	@Test
	public void updateCartTest(){
		
		cart.setId(644);
		cart.setProductId(101);
		cart.setQuantityAdded(2);
		cart.setStatus("ACTIVE");
		cart.setUserId(546);
		cart.setSubTotal(2 * productDao.getProductById(101).getPrice());
		cart.setUpdatedBy("SYSTEM");
		cart.setCreatedBy(cartDao.getCartById(644).getCreatedBy());
		cart.setCreatedTimestamp(cartDao.getCartById(644).getCreatedTimestamp());
		cart.setUpdatedTimestamp(new Timestamp(System.currentTimeMillis()));
		assertTrue("Problem in updating cart",cartDao.saveOrUpdate(cart));
	}
	@Ignore
	@Test
	public void getCartById(){
		
		cart = cartDao.getCartById(611);
		assertNotNull("Problem in retrieving Cart details based in cart Id", cart);
		showCart(cart);
	}
	@Ignore
	@Test
	public void getCartDetailByUserTest(){
		Cart cartObj = cartDao.getCartByUserId(48);
		assertNotNull("Problem in fetching cart detail by User Id",cartObj);
		showCart(cartObj);
	}
	
	private void showCart(Cart cartObj) {
		
		System.out.println("Cart Id: "+cartObj.getId());
		System.out.println("Product Id: "+cartObj.getProductId());
		System.out.println("User Id: "+cartObj.getUserId());
		System.out.println("Quantity : "+cartObj.getQuantityAdded());
		System.out.println("SubTotal: "+cartObj.getSubTotal());
			
	}

	@Ignore
	@Test
	public void deleteCartDetailTest(){
		assertTrue("Problem in deleting cart details ",cartDao.deleteCartById(612));
	}
	
	@Ignore
	@Test
	public void getCartItemTest(){
		
		cart = cartDao.getCartItem(135, 546);
		
		assertNotNull("Problem in retrieving cart item",cart);
		
		showCart(cart);
	}
	
	@Test
	public void getCartTotalValueTest(){
		
		double resultValue = cartDao.getCartTotal(546);
		System.out.println("totalCartValue = "+resultValue);
		assertNotNull("Problem in retrieving cart total value", resultValue);
	}
	
	@Test
	public void getCartSizeTest(){
		
		double sizeResult = cartDao.getCartSize(546);
		System.out.println("Cart Size : "+sizeResult);
		assertNotNull("Problem in fetching cart size ", sizeResult);
	}
}
