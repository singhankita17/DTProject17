package com.yourstyle.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yourstyle.model.Supplier;

public class SupplierDaoImpl implements SupplierDao {

	@Autowired
	private SessionFactory sessionFactory;	
	
	public SupplierDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public boolean saveOrUpdate(Supplier supplier) {
		sessionFactory.getCurrentSession().saveOrUpdate(supplier);
		return true;
	}

	@Transactional
	public Supplier getSupplierById(int supplierId) {
		
		Supplier supplier = (Supplier) sessionFactory.getCurrentSession().createQuery("select s from Supplier s where s.id = :supplierId")
				.setParameter("supplierId", supplierId).uniqueResult();
		System.out.println(supplier.toString());
		return supplier;
		
	}

	@Transactional
	public boolean deleteSupplierById(int supplierId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Object persistenceInstance = session.load(Supplier.class, supplierId);
		
		if(persistenceInstance !=null){
			
			session.delete(persistenceInstance);
			System.out.println("User deleted Successfully");
		}
		
		return true;
	}

	@Transactional
	public List<Supplier> getAllSuppliers() {
		List<Supplier> supplier = sessionFactory.getCurrentSession().createQuery("from Supplier", Supplier.class).list();
		return supplier;
	}

}
