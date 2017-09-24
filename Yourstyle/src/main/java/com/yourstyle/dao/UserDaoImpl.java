package com.yourstyle.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yourstyle.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	private static Logger log = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public UserDaoImpl(SessionFactory sessionFactory) {
		
		log.info("UserDaoImpl : getSessionFactory");
		this.sessionFactory = sessionFactory;
	}

	@Transactional(rollbackOn = Exception.class)
	public boolean saveOrUpdate(User user) {
		
		log.info("UserDaoImpl : saveOrUpdate user detail");
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		return true;
	}

	@Transactional
	public User getUserById(int userId) {
		
		log.info("UserDaoImpl : getUserById -- select User by id --"+userId);
		
		User user = (User) sessionFactory.getCurrentSession().createQuery("select u from User u where u.id = :userId")
				.setParameter("userId", userId).uniqueResult();
		
		return (User) user;
	}

	@Transactional
	public boolean deleteUserById(int userId) {
		
		log.info("UserDaoImpl : deleteUserById -- getSession ");
		Session session = sessionFactory.getCurrentSession();
		log.info("UserDaoImpl : deleteUserById -- load user record --"+userId);
		Object persistenceInstance = session.load(User.class, userId);
		
		log.info("UserDaoImpl : deleteUserById -- delete selected user --"+userId);
		if(persistenceInstance !=null){
			
			session.delete(persistenceInstance);
			log.info("UserDaoImpl : User deleted Successfully with id --"+userId);
			System.out.println("User deleted Successfully");
		}
		
		return true;
	}

	@Transactional
	public User getUserByEmail(String email) {
		
		log.info("UserDaoImpl : getUserByEmail -- getCurrentSession");
		Session session = sessionFactory.getCurrentSession();
		
		log.info("UserDaoImpl : getUserByEmail -- select user by email");
		User user = (User) session.createQuery("select u from User u where u.email = :email")
						.setParameter("email", email).uniqueResult();
		
		return user;
	}

	@Transactional
	public List<User> getAllUsers() {
		
		log.info("UserDaoImpl : getAll the users");
		List<User> users = sessionFactory.getCurrentSession().createQuery("from User", User.class).list();
		return users;
	}

}
