package com.yourstyle.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yourstyle.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	public ProductDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean saveOrUpdate(Product product) {
		sessionFactory.getCurrentSession().saveOrUpdate(product);
		return true;
	}

	@Transactional
	public Product getUserById(int productId) {
		
		Product product = (Product) sessionFactory.getCurrentSession().createQuery("select p from Product p where id = :productId")
				.setParameter("productId", productId).uniqueResult();
		
		return product;
	}

	@Transactional
	public boolean deleteProductById(int productId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Object peristanceInstance = session.load(Product.class, productId);
		
		if(peristanceInstance != null){
			session.delete(peristanceInstance);
			System.out.println("Product deleted successfully");
		}
		
		return true;
	}

	@Transactional
	public List<Product> getAllProducts() {
		List<Product> products = sessionFactory.getCurrentSession().createQuery("from Product",Product.class).list();
		return products;
	}

	@Transactional
	public Product getProductById(int productId) {
		
		Product product = (Product) sessionFactory.getCurrentSession().createQuery("select p from Product p where id = :productId")
				.setParameter("productId", productId).uniqueResult();
		System.out.println(product.toString());
		
		return product;		
	}

}
