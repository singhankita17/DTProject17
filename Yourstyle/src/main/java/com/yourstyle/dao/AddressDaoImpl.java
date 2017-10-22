package com.yourstyle.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yourstyle.model.Address;

public class AddressDaoImpl implements AddressDao {

	private static Logger log = LoggerFactory.getLogger(AddressDaoImpl.class);

	
	@Autowired
	private SessionFactory sessionFactory;	
	
	public AddressDaoImpl(SessionFactory sessionFactory) {
		
		log.info("AddressDaoImpl : getSessionFactory");
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean saveOrUpdate(Address address) {
		
		log.info("AddressDaoImpl : save or update Address detail");
		sessionFactory.getCurrentSession().saveOrUpdate(address);
		log.info("AddressDaoImpl : Successful save or update Address detail");
		return true;
	}

	@Transactional
	public Address getAddressById(int addressId) {
		
		log.info("AddressDaoImpl : get Address detail by Id --"+addressId);
		Address address =  (Address) sessionFactory.getCurrentSession().createQuery("select a from Address a where a.id = :addressId")
				.setParameter("addressId", addressId).uniqueResult();
		log.info("AddressDaoImpl : Address detail by Id --"+address.toString());
		return address;
	}

	@Transactional
	public boolean deleteAddressById(int addressId) {
		log.info("AddressDaoImpl : get Session Factory");
		Session session = sessionFactory.getCurrentSession();
		log.info("AddressDaoImpl : load Address detail by Id --"+addressId);
		Object persistenceInstance = session.load(Address.class, addressId);
		
		if(persistenceInstance !=null){
			log.info("AddressDaoImpl : delete Address detail by Id --"+addressId);
			session.delete(persistenceInstance);
			log.info("AddressDaoImpl : Address deleted Successfully --"+addressId);
			System.out.println("Address deleted Successfully");
		}
		
		return true;
	}

	@Transactional
	public List<Address> getAllAddressOfUser(int userId) {
		log.info("AddressDaoImpl : get Session Factory");
		Session session = sessionFactory.getCurrentSession();
		log.info("AddressDaoImpl : load all Address details of given user Id --"+userId);
		CriteriaBuilder cb =  session.getCriteriaBuilder();
		CriteriaQuery<Address> cq = cb.createQuery(Address.class);
		
		Root<Address> addressRoot  = cq.from(Address.class);
		ParameterExpression<String> id = cb.parameter(String.class);
		
		cq.select(addressRoot).where(cb.equal(addressRoot.get("id"), id));
		
		Query query = session.createQuery(cq);
		query.setParameter("personId", userId);
		
		log.info("AddressDaoImpl : criteria query of Address details of given user Id --"+query.toString());
		@SuppressWarnings(value="unchecked")
		List<Address> results = (List<Address>) query.getResultList();
		log.info("AddressDaoImpl : Successful retrieval of Address details of given user Id --"+userId);
		return results;
	}
	
	@Transactional
	public Address getAddressOfUser(int userId){
		
		Session session = sessionFactory.openSession();
		Address address = session.createQuery("from Address where personId = :userId",Address.class).setParameter("userId", userId).uniqueResult();
		session.close();
		return address;
	}

}
