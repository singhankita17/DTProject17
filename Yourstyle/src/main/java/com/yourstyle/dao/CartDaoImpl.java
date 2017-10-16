package com.yourstyle.dao;

import javax.persistence.Query;
import javax.transaction.Transactional;

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
	public Cart getCartByUserId(int userId) {
		log.info("CartDaoImpl : get Cart detail by user Id");
		Cart cart= (Cart) sessionFactory.getCurrentSession().createQuery("select c from Cart c where c.userId = :userId")
				.setParameter("userId", userId).uniqueResult();
		
		log.info("CartDaoImpl : Fetched Cart detail by Id => "+cart.toString());
		return cart;
	}

	@Transactional
	public Cart getCartItem(int productId, int userId){
		log.info("CartDaoImpl : get Cart Item by user Id and product Id");
		String query = "from Cart where userId = :userId and productId = :productId and status = 'ACTIVE'";
		log.info("CartDaoImpl : get Cart Item by user Id and product Id ---set Parameter");
		Query queryObj = sessionFactory.getCurrentSession().createQuery(query).setParameter("userId", userId).setParameter("productId", productId);
		log.info("CartDaoImpl : getCartItem --- executeQuery");
		Cart cart = (Cart) queryObj.getSingleResult();
		
		return cart;
	}

	@Transactional
	@Override
	public double getCartTotal(int userId) {
		String queryString = "select sum(subTotal) from Cart c where userId = :userId and status = 'ACTIVE'";
		Query query = sessionFactory.getCurrentSession().createQuery(queryString).setParameter("userId", userId);
		double totalCartValue = (double) query.getSingleResult();
		return totalCartValue;
	}

	@Transactional
	@Override
	public long getCartSize(int userId) {
		String queryString = "select count(c) from Cart c where userId = :userId and status = 'ACTIVE'";
		Query query = sessionFactory.getCurrentSession().createQuery(queryString).setParameter("userId", userId);
		long totalCartSize = (long) query.getSingleResult();
		return totalCartSize;
	}
}
