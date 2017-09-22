package com.yourstyle.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yourstyle.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public UserDaoImpl(SessionFactory sessionFactory) {
		
		this.sessionFactory = sessionFactory;
	}

	@Transactional(rollbackOn = Exception.class)
	public boolean saveOrUpdate(User user) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		return true;
	}

	@Transactional
	public User getUserById(int userId) {
		
		User user = (User) sessionFactory.getCurrentSession().createQuery("select u from User u where u.id = :userId")
				.setParameter("userId", userId).uniqueResult();
		
		return (User) user;
	}

	@Transactional
	public boolean deleteUserById(int userId) {
		Session session = sessionFactory.getCurrentSession();
		
		Object persistenceInstance = session.load(User.class, userId);
		
		if(persistenceInstance !=null){
			
			session.delete(persistenceInstance);
			System.out.println("User deleted Successfully");
		}
		
		return true;
	}

	@Transactional
	public User getUserByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		
		User user = (User) session.createQuery("select u from User u where u.email = :email")
						.setParameter("email", email).uniqueResult();
		
		return user;
	}

	@Transactional
	public List<User> getAllUsers() {
		List<User> users = sessionFactory.getCurrentSession().createQuery("from User", User.class).list();
		return users;
	}

}
