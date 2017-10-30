package com.yourstyle.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.yourstyle.dao.AddressDao;
import com.yourstyle.dao.AddressDaoImpl;
import com.yourstyle.dao.CategoryDao;
import com.yourstyle.dao.CategoryDaoImpl;
import com.yourstyle.dao.ProductDao;
import com.yourstyle.dao.ProductDaoImpl;
import com.yourstyle.dao.SupplierDao;
import com.yourstyle.dao.SupplierDaoImpl;
import com.yourstyle.dao.UserDao;
import com.yourstyle.dao.UserDaoImpl;

@Configuration
@ComponentScan(basePackages = "com.yourstyle.*")
@EnableTransactionManagement
public class ApplicationContextConfig {

	
		@Bean(name = "dataSource")
		public DataSource getDataSource(){
			DriverManagerDataSource dataSource = new DriverManagerDataSource();
			dataSource.setDriverClassName("org.h2.Driver");
			dataSource.setUrl("jdbc:h2:tcp://localhost/~/Ecommerce");
			dataSource.setUsername("sa");
			dataSource.setPassword("");
			System.out.println("DataSource Connection Settings");
			return dataSource;
		}
		
		
		@Bean
		public Properties getHibernateProperties(){
			
			Properties properties = new Properties();
			properties.put("hibernate.show_sql", "true");
			properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
			properties.put("hibernate.hbm2ddl.auto", "update");			
			System.out.println("Setting Hibernate Properties");
			
			return properties;
		}
		
		@Autowired
		@Bean(name = "sessionFactory")
		public SessionFactory getSessionFactory(DataSource dataSource){
					LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
					sessionBuilder.addProperties(getHibernateProperties());
					sessionBuilder.scanPackages("com.yourstyle");
					System.out.println("Session Factory Configuration");
					
					return sessionBuilder.buildSessionFactory();
		}
		
		@Autowired
		@Bean(name = "transactionManager")
		public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory){
			HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);
			System.out.println("Transaction Configuration");
			
			return transactionManager;
			
		}
		
		@Autowired
		@Bean(name="userDao")
		public UserDao getUserDao(SessionFactory sessionFactory){
			return new UserDaoImpl(sessionFactory);
		}
		
		
		@Autowired
		@Bean(name="categoryDao")
		public CategoryDao getCategoryDao(SessionFactory sessionFactory){
			return new CategoryDaoImpl(sessionFactory);
		}
			
		@Autowired
		@Bean(name="addressDao")
		public AddressDao getAddressDao(SessionFactory sessionFactory){
			return new AddressDaoImpl(sessionFactory);
		}
		
		@Autowired
		@Bean(name="supplierDao")
		public SupplierDao getSupplierDao(SessionFactory sessionFactory){
			return new SupplierDaoImpl(sessionFactory);
		}
		
		@Autowired
		@Bean(name="productDao")
		public ProductDao getProductDao(SessionFactory sessionFactory){
			return new ProductDaoImpl(sessionFactory);
		}
	
}
