package com.yourstyle.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yourstyle.model.Supplier;

public class SupplierDaoImpl implements SupplierDao {

	private static Logger log = LoggerFactory.getLogger(SupplierDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;	
	
	public SupplierDaoImpl(SessionFactory sessionFactory) {
		log.info("SupplierDaoImpl : getSessionFactory");
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public boolean saveOrUpdate(Supplier supplier) {
		
		log.info("SupplierDaoImpl : save or update Supplier detail");
		sessionFactory.getCurrentSession().saveOrUpdate(supplier);
		return true;
	}

	@Transactional
	public Supplier getSupplierById(int supplierId) {
		
		log.info("SupplierDaoImpl : get Supplier detail by Id -- "+supplierId);
		Supplier supplier = (Supplier) sessionFactory.getCurrentSession().createQuery("select s from Supplier s where s.id = :supplierId")
				.setParameter("supplierId", supplierId).uniqueResult();
		//System.out.println(supplier.toString());
		log.info("SupplierDaoImpl : get Supplier detail by Id --supplier detail "+supplier.toString());
		return supplier;
		
	}

	@Transactional
	public boolean deleteSupplierById(int supplierId) {
		
		
		log.info("SupplierDaoImpl : delete Supplier detail by Id --getSession ");
		Session session = sessionFactory.getCurrentSession();
		
		log.info("SupplierDaoImpl : deleteSupplierById -- load supplier record --"+supplierId);
		Object persistenceInstance = session.load(Supplier.class, supplierId);
		
		log.info("SupplierDaoImpl : deleteSupplierById -- delete selected supplier --"+supplierId);
		if(persistenceInstance !=null){
			
			session.delete(persistenceInstance);
			log.info("SupplierDaoImpl :  -- User deleted Successfully -- supplierid --"+supplierId);
			System.out.println("User deleted Successfully");
		}
		
		return true;
	}

	@Transactional
	public List<Supplier> getAllSuppliers() {
		
		log.info("SupplierDaoImpl : getAll the suppliers");
		List<Supplier> supplier = sessionFactory.getCurrentSession().createQuery("from Supplier", Supplier.class).list();
		return supplier;
	}

}
