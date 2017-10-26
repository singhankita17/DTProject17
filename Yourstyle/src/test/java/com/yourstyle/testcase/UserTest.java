package com.yourstyle.testcase;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.yourstyle.dao.UserDao;
import com.yourstyle.model.Address;
import com.yourstyle.model.User;

@Ignore
public class UserTest {
	
		@Autowired
		private static User user;
		
		@Autowired
		private static UserDao userDao;
		
		@SuppressWarnings("resource")
		
		
		@BeforeClass
		public static void initialize(){
			AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
			context.scan("com.yourstyle.*");
			context.refresh();
			userDao = (UserDao) context.getBean("userDao");
		
			user = (User) context.getBean("user");
			
		}
		
		@Test
		public void createUserTest(){
			
			user.setFirstName("piyush");			
			user.setLastName("sharma");
			user.setEmail("sr.piyush94@gmail.com");
			user.setPassword("12345");
			user.setRole("Customer");
			user.setEnabled(true);
			user.setCreatedBy("SYSTEM");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			user.setCreatedTimestamp(timestamp);		
			
			boolean flag = userDao.saveOrUpdate(user);
			assertEquals("createUserTestCase",true,flag);
		}
		
		
		
		@Test
		public void updateUserTest(){
			
			user.setId(48);
			user.setFirstName("rohan");			
			user.setLastName("ram");
			user.setEmail("rohanram@gmail.com");
			user.setPassword("12345");
			user.setRole("Customer");
			user.setEnabled(true);
			user.setUpdatedBy("SYSTEM");
			user.setCreatedBy("SYSTEM");
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			user.setCreatedTimestamp(timestamp);
			Timestamp timestamp1 = new Timestamp(System.currentTimeMillis());
			user.setUpdatedTimestamp(timestamp1);
			Address address = new Address();
			address.setId(97);
			address.setName("Rohan");
			address.setAddress1("road no 1");
			address.setAddress2("Kukatpally");
			address.setCity("Hyderabad");
			address.setState("Telangana");
			address.setPincode(500032);
			address.setEmail("rohanram@gmail.com");
			address.setPhone(9849383);
			address.setPersonId(48);
			address.setCreatedBy("TEST");
			address.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
			Set<Address> userAddress = new HashSet<Address>();
			userAddress.add(address);
			user.setUserAddress(userAddress);
			
			boolean flag = userDao.saveOrUpdate(user);
			assertEquals("updateUserTestCase",true,flag);
		}
		
				
		@Test
		public void findUserByEmailTest(){
			
			String email = "xyz123@ymail.com";
			
			User us = userDao.getUserByEmail(email);
			assertEquals("findUserByEmailTest", "xyz123@ymail.com", us.getEmail());
		}
		
		@Test
		public void findUserByIdTest(){
			
			int userId = 1;
			
			User us = userDao.getUserById(userId);
			
			assertEquals(1,us.getId());
			assertEquals("rish", us.getFirstName());
			assertEquals(null, us.getLastName());
			assertEquals("xyz123@ymail.com", us.getEmail());
			assertEquals("rishabh", us.getPassword());
			assertEquals("ROLE_USER", us.getRole());
			assertEquals(true, us.getEnabled());
			
		}
		
		@Test
		public void findAllUserTest(){
			
			List<User> userList = userDao.getAllUsers();
			for(Iterator<User> itr = userList.iterator(); itr.hasNext(); ){
				User u = itr.next();
				System.out.println(u);
			}
			assertEquals("findAllUserTest",2, userList.size());
		}
			
}
