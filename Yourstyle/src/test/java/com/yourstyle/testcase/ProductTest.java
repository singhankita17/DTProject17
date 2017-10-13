package com.yourstyle.testcase;

import static org.junit.Assert.*;
import java.sql.Timestamp;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.yourstyle.dao.ProductDao;
import com.yourstyle.model.Product;

@Ignore
public class ProductTest {

	@Autowired
	private static Product product;
	
	@Autowired
	private static ProductDao productDao;
	
	@BeforeClass
	public static void initialize(){
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.yourstyle.*");
		context.refresh();
		
		productDao = (ProductDao) context.getBean("productDao");
		product = (Product) context.getBean("product");		
	}
	
	@Ignore
	@Test
	public void createProductTest(){
		product.setProductName("Bio Henna Leaf Fresh Texture Shampoo & Conditioner -120 Ml");
		product.setBrandName("BIOTIQUE");
		product.setCategoryId(132);
		product.setProductDesc("This luxurious formula is a blend of pure henna leaves, soap nut and berberry to cleanse, condition and add a hint of henna highlights. Smoothes hair texture and brings out the richest brown tones. Leaves hair full of natural body and shine. Can be used daily to keep away premature grey. COCOA BUTTER is one of the world’s most effective skin care ingredients used for ages to soothe and repair skin");
		product.setPrice(100);
		product.setCreatedBy("TEST");
		product.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
		product.setSupplierId(134);
		
		boolean flag = productDao.saveOrUpdate(product);
		assertEquals("createProductTest",flag,true);
	}
	
	@Ignore
	@Test
	public void updateProductTest(){
		product.setId(101);
		product.setProductName("Tulsi Ginger Tea (100 gm)");
		product.setBrandName("Organic India");
		product.setCategoryId(68);
		product.setProductDesc("An unique and all time favourite Tulsi Ginger combines the anti-stress and immune supporting properties of Tulsi, with aromatic smell and amazing qualities of Ginger, traditionally used to activate the body's fire element, to burn up toxins and improve digestion");
		product.setPrice(200);
		product.setCreatedBy("TEST");
		product.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
		product.setUpdatedBy("SYSTEM");
		product.setUpdatedTimestamp(new Timestamp(System.currentTimeMillis()));
		product.setSupplierId(109);
		
		boolean flag = productDao.saveOrUpdate(product);
		assertEquals("createProductTest",flag,true);
	}
	
	@Ignore
	@Test
	public void  getProductById(){
		
		product = productDao.getProductById(354);
		
		assertNotNull("Problem in fetching product by Product Id", product);
		
		System.out.println(product.getId());
		
		System.out.println(product.getProductName());
		
		System.out.println(product.getProductDesc());
		
	}
	
	@Ignore
	@Test
	public void getAllProducts(){
		
		List<Product> products = productDao.getAllProducts();
		
		assertNotNull("Problem in retrieving products",products);
		
		for(Product product:products){
			System.out.println(product.getId());
			System.out.println(product.getProductName());
			System.out.println(product.getProductDesc());
		}
	}
	@Ignore
	@Test
	public void deleteProductById(){
		
		assertTrue("Problem in product deletion",productDao.deleteProductById(453));
	}
}
