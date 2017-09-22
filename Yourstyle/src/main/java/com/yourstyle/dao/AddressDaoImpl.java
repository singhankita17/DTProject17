package com.yourstyle.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.yourstyle.model.Address;

public class AddressDaoImpl implements AddressDao {

	@Autowired
	private SessionFactory sessionFactory;	
	
	public AddressDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean saveOrUpdate(Address address) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(address);
		return true;
	}

	@Transactional
	public Address getAddressById(int addressId) {
		Address address =  (Address) sessionFactory.getCurrentSession().createQuery("select a from Address a where a.id = :addressId")
				.setParameter("addressId", addressId).uniqueResult();
		return address;
	}

	@Transactional
	public boolean deleteAddressById(int addressId) {

		Session session = sessionFactory.getCurrentSession();
		
		Object persistenceInstance = session.load(Address.class, addressId);
		
		if(persistenceInstance !=null){
			
			session.delete(persistenceInstance);
			System.out.println("User deleted Successfully");
		}
		
		return true;
	}

	@Transactional
	public List<Address> getAllAddressOfUser(int userId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		CriteriaBuilder cb =  session.getCriteriaBuilder();
		CriteriaQuery<Address> cq = cb.createQuery(Address.class);
		
		Root<Address> addressRoot  = cq.from(Address.class);
		ParameterExpression<String> id = cb.parameter(String.class);
		
		cq.select(addressRoot).where(cb.equal(addressRoot.get("id"), id));
		
		Query query = session.createQuery(cq);
		query.setParameter("personId", userId);
		List<Address> results = query.getResultList();
		return results;
	}

}
