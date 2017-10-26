package com.yourstyle.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yourstyle.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	private static Logger log = LoggerFactory.getLogger(ProductDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public ProductDaoImpl(SessionFactory sessionFactory) {
		
		log.info("ProductDaoImpl : getSessionFactory");
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean saveOrUpdate(Product product) {
		
		log.info("ProductDaoImpl : save or update Product detail");
		sessionFactory.getCurrentSession().saveOrUpdate(product);
		log.info("ProductDaoImpl : save or update Product detail Successful");
		return true;
	}

	

	@Transactional
	public boolean deleteProductById(int productId) {
		log.info("ProductDaoImpl : get Sessionfactory");
		Session session = sessionFactory.getCurrentSession();
		log.info("ProductDaoImpl : load Product by Id --"+productId);
		Object peristanceInstance = session.load(Product.class, productId);
		log.info("ProductDaoImpl :  delete Product by Id --"+productId);
		if(peristanceInstance != null){
			session.delete(peristanceInstance);
			System.out.println("Product deleted successfully");
			log.info("ProductDaoImpl : Product deleted Successfully with product id --"+productId);
		}
		
		return true;
	}

	@Transactional
	public List<Product> getAllProducts() {
		log.info("ProductDaoImpl : get All Products details");
		List<Product> products = sessionFactory.getCurrentSession().createQuery("from Product",Product.class).list();
		return products;
	}

	@Transactional
	public Product getProductById(int productId) {
		log.info("ProductDaoImpl : get Products details by Id -- "+productId);
		Product product = (Product) sessionFactory.getCurrentSession().createQuery("select p from Product p where id = :productId")
				.setParameter("productId", productId).uniqueResult();
		System.out.println(product.toString());
		log.info("ProductDaoImpl : Successful retrieval of Products details  -- "+product.toString());
		
		return product;		
	}

	@Override
	public List<Product> getAllProductForCategory(int catId) {
		log.info("ProductDaoImpl : get all Products under given Category Id -- "+catId);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Product> products = session.createQuery("from Product where categoryId = :catId",Product.class).setParameter("catId", catId).list();
		session.getTransaction().commit();
		session.close();
		return products;
	}

	@Transactional
	public List<Product> getProductByBrand(String brandName) {
		String queryString = "from Product where brandName = :brandName";
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Product> products = session.createQuery(queryString,Product.class).setParameter("brandName", brandName).list();
		session.getTransaction().commit();
		session.close();
		
		return products;
	}

	@Transactional
	public List<Product> getProductBySearchText(String searchString) {
	    
		String queryString = "from Product where lower(productName) like '%"+searchString.toLowerCase()+"%' or lower(productDesc) like '%"+searchString.toLowerCase()+"%'";
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Product> products = session.createQuery(queryString,Product.class).list();
		session.getTransaction().commit();
		session.close();
		
		return products;
	}

}
