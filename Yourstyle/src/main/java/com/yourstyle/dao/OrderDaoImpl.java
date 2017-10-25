package com.yourstyle.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yourstyle.model.Cart;
import com.yourstyle.model.Orders;

@Repository("orderDao")
public class OrderDaoImpl implements OrderDao {

	private static Logger log = LoggerFactory.getLogger(OrderDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	@Transactional
	@Override
	public boolean saveOrUpdate(Orders order) {
		try{
			sessionFactory.getCurrentSession().saveOrUpdate(order);
			return true;
		}catch (Exception e) {
			return false;
		}
		
	}

	@Transactional
	@Override
	public Orders getOrderById(int orderId) {
		Cart cart= (Cart) sessionFactory.getCurrentSession().createQuery("from Order where id = :orderId")
				.setParameter("orderId", orderId).uniqueResult();
		return null;
	}

	@Transactional
	@Override
	public boolean deleteOrderById(int orderId) {
		try{
			
			Session session = sessionFactory.getCurrentSession();
			
			Object object = session.load(Orders.class, orderId);
			
			if(object!=null){
				
				session.delete(object);
				log.info("OrderDaoImpl : Order Object deleted Successfully");				
				
			}
			return true;
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public List<Orders> getAllOrdersOfUser(int userId) {
		
		List<Orders> orderList=  sessionFactory.getCurrentSession().createQuery("from Order where userId = :userId",Orders.class).setParameter("userId", userId).list();
		return orderList;
	}

}
