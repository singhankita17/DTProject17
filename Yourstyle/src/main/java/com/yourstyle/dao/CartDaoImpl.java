package com.yourstyle.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yourstyle.model.Cart;

@Repository("cartDao")
public class CartDaoImpl implements CartDao{
	
	private static Logger log = LoggerFactory.getLogger(CartDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory ;

	@Transactional
	@Override
	public boolean saveOrUpdate(Cart cart) {
		try{
		log.info("CartDaoImpl : save or update Cart detail");
		sessionFactory.getCurrentSession().saveOrUpdate(cart);
		return true;
		}catch (Exception e) {
			return false;
		}
	}

	@Transactional
	@Override
	public Cart getCartById(int cartId) {
		log.info("CartDaoImpl : get Cart detail by Id");
		Cart cart= (Cart) sessionFactory.getCurrentSession().createQuery("select c from Cart c where c.id = :cartId")
				.setParameter("cartId", cartId).uniqueResult();
		
		log.info("CartDaoImpl : Fetched Cart detail by Id => "+cart.toString());
		return cart;
	}

	@Transactional
	@Override
	public boolean deleteCartById(int cartId) {
		try{
		log.info("CartDaoImpl : get Session from session factory");
		
		Session session = sessionFactory.getCurrentSession();
		
		log.info("CartDaoImpl : load cart detail by Id");
		
		Object object = session.load(Cart.class, cartId);
		
		if(object!=null){
			
			session.delete(object);
			log.info("CartDaoImpl : Cart Object deleted Success fully");
			
			
		}
		}catch (Exception e) {
			return false;
		}
		return true;
	
	}

	@Transactional
	@Override
	public List<Cart> getCartByUserId(int userId) {
		log.info("CartDaoImpl : get Cart detail by user Id");
		List<Cart> cartList=  sessionFactory.getCurrentSession().createQuery("from Cart where userId = :userId",Cart.class).setParameter("userId", userId).list();
		
		log.info("CartDaoImpl : Fetched Cart detail by User Id => ");
		return cartList;
	}

	@Transactional
	public Cart getCartItem(int productId, int userId){
		log.info("CartDaoImpl : get Cart Item by user Id and product Id");
		Cart cart;
		try{
		String query = "from Cart where userId = :userId and productId = :productId and status = 'ACTIVE'";
		log.info("CartDaoImpl : get Cart Item by user Id and product Id ---set Parameter");
		Query queryObj = sessionFactory.getCurrentSession().createQuery(query,Cart.class).setParameter("userId", userId).setParameter("productId", productId);
		log.info("CartDaoImpl : getCartItem --- executeQuery");
		  cart = (Cart) queryObj.getSingleResult();
		}catch(Exception he){
			cart =null;
		}
		
		return cart;
	}

	@Transactional
	@Override
	public double getCartTotal(int userId) {
		double totalCartValue =0.0;
		try{
		String queryString = "select sum(subTotal) from Cart c where userId = :userId and status = 'ACTIVE'";
		Query query = sessionFactory.getCurrentSession().createQuery(queryString).setParameter("userId", userId);
		totalCartValue = (double) query.getSingleResult();
		}catch(Exception he){
			totalCartValue = 0;
		}
		return totalCartValue;
	}

	@Transactional
	@Override
	public long getCartSize(int userId) {
		long totalCartSize = 0;
		try{
		String queryString = "select count(c) from Cart c where userId = :userId and status = 'ACTIVE'";
		Query query = sessionFactory.getCurrentSession().createQuery(queryString).setParameter("userId", userId);
		totalCartSize = (long) query.getSingleResult();
		}catch(Exception he){
			totalCartSize = 0;
		}
		return totalCartSize;
	}
}
