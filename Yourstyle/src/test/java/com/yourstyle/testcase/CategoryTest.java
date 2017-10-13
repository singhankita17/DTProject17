package com.yourstyle.testcase;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.yourstyle.dao.CategoryDao;
import com.yourstyle.model.Category;


@Ignore
public class CategoryTest {
		
	@Autowired
	private static CategoryDao categoryDao;
	
	@Autowired
	private static Category category;
	
	@SuppressWarnings("resource")
	@BeforeClass
	public static void initialize(){
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.yourstyle.*");
		context.refresh();
		
		category = (Category) context.getBean("category");
		
		categoryDao = (CategoryDao) context.getBean("categoryDao");
	}
	
	@Ignore
	@Test
	public void createCategoryTest(){
		
		category.setCategoryName("Hair");
		category.setCategoryDescription("All products related to HairCare"); 
		category.setCreatedBy("SYSTEM");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		category.setCreatedTimestamp(timestamp);
		
		Boolean response = categoryDao.saveOrUpdate(category);
		assertEquals("createCategoryTest",true,response);
	}
	
	@Ignore
	@Test
		public void updateCategoryTest(){
		
				category.setId(68);
				category.setCategoryName("Wellness");
				category.setCategoryDescription("Health related products"); 
				category.setUpdatedBy("SYSTEM");
				Timestamp timestamp = new Timestamp(System.currentTimeMillis());
				category.setUpdatedTimestamp(timestamp);
				
				Boolean response = categoryDao.saveOrUpdate(category);
				assertEquals("updateCategoryTest",true,response);
		}
	
	@Ignore	
		@Test
		public void fetchCategoryByName(){
			
			String name = "Well";
			
			List<Category> categories = categoryDao.getCategoriesByName(name);
			
			Iterator<Category> itr = categories.iterator();
			while(itr.hasNext()){
				Category category = itr.next();
				System.out.println(category.getCategoryName());
				assertTrue(category.getCategoryName().contains(name) );
			}
			
		}
		
	@Ignore
		@Test
		public void getCategoryById(){
			
			category = categoryDao.getCategoryById(132);
			
			assertNotNull("Problem in fetching category by Id",category);
			
			category.toString();
		}
	
	@Ignore
	@Test
	public void deleteCategoryById(){
		
		assertTrue(categoryDao.deleteCategoryById(418));		
		
	}
	
	@Ignore
	@Test
	public void getAllCategories(){
		
		List<Category> listCategories = categoryDao.getAllCategories();
		assertNotNull("Problem in retrieving", listCategories);
		
		for(Category cat:listCategories){
			System.out.println(cat.getId());
			System.out.println(cat.getCategoryName());
			System.out.println(cat.getCategoryDescription());
		}
	}
}
