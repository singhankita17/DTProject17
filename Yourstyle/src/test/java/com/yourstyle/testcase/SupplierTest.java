package com.yourstyle.testcase;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.yourstyle.dao.SupplierDao;
import com.yourstyle.model.Address;
import com.yourstyle.model.Product;
import com.yourstyle.model.Supplier;

@Ignore
public class SupplierTest {

	@Autowired
	private static Supplier supplier;
	
	@Autowired
	private static SupplierDao supplierDao;
	
	@BeforeClass
	public static void initialize(){
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		
		context.scan("com.yourstyle.*");
		context.refresh();
		
		supplierDao = (SupplierDao) context.getBean("supplierDao");
		
		supplier = (Supplier) context.getBean("supplier");
		
	}
	
	@Ignore
	@Test
	public void createSupplierTest(){
		
		supplier.setSupplierName("VSR Merchants");
		supplier.setSupplierAddress("Sector - 71, Noida, UP");
		boolean flag = supplierDao.saveOrUpdate(supplier);
		assertEquals("createSupplierTest",flag,true);
		
	}
	
	@Ignore
	@Test
	public void updateSupplierTest(){
		supplier.setId(134);
		supplier.setSupplierName("BIO VEDA");
		
		Product product = new Product();
		product.setId(101);
		product.setProductName("Tulsi Ginger Tea (100 gm)");
		product.setBrandName("Organic India");
		product.setCategoryId(68);
		product.setProductDesc("An unique and all time favourite Tulsi Ginger combines the anti-stress and immune supporting properties of Tulsi, with aromatic smell and amazing qualities of Ginger, traditionally used to activate the body's fire element, to burn up toxins and improve digestion");
		product.setPrice(200);
		product.setCreatedBy("TEST");
		product.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
		product.setSupplierId(109);
		Set<Product> products = new HashSet<Product>();
		products.add(product);
		supplier.setProducts(products);
		
		
		supplier.setSupplierAddress("Sector - 71, Noida, UP");
		
		boolean flag = supplierDao.saveOrUpdate(supplier);
		assertEquals("updateSupplierTest",flag,true);
		
	}
	
	@Ignore
	@Test
	public void getSupplierById(){
		
		supplier = supplierDao.getSupplierById(419);
		
		assertNotNull("Problem in fetching supplier detail based on ID",supplier);
		
		supplier.toString();
	}
	
	@Ignore
	@Test
	public void deleteSupplierById(){
		
		assertTrue(supplierDao.deleteSupplierById(482));
	}
	
	@Test
	public void getAllSuppliers(){
		
		List<Supplier> listSuppliers = supplierDao.getAllSuppliers();
		
		assertNotNull("Probelm in retrieving", listSuppliers);
		
		for(Supplier supplier:listSuppliers){
			
			System.out.println(supplier.getId());
			System.out.println(supplier.getSupplierName());
			System.out.println(supplier.getSupplierAddress());
		}
	}
}
