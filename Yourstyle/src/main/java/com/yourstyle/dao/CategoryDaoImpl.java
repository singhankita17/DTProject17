package com.yourstyle.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yourstyle.model.Category;

public class CategoryDaoImpl implements CategoryDao {
	
	private static Logger log = LoggerFactory.getLogger(CategoryDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory ;
	

	public CategoryDaoImpl(SessionFactory sessionFactory) {
		
		log.info("CategoryDaoImpl : getSessionFactory");
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean saveOrUpdate(Category category) {
		
		log.info("CategoryDaoImpl : save or update Category detail");
		sessionFactory.getCurrentSession().saveOrUpdate(category);
		return true;
	}

	@Transactional
	public Category getCategoryById(int categoryId) {
		
		log.info("CategoryDaoImpl : get Category detail by Id -- "+categoryId);		
		Category category = (Category) sessionFactory.getCurrentSession().createQuery("select c from Category c where id = :categoryId")
																.setParameter("categoryId", categoryId).uniqueResult();
		System.out.println(category.toString());
		log.info("CategoryDaoImpl : get Category detail by Id --category detail "+category.toString());
		return category;
	}

	@Transactional
	public boolean deleteCategoryById(int categoryId) {
		
		log.info("CategoryDaoImpl : delete Category detail by Id --getSession ");
		Session session = sessionFactory.getCurrentSession();
		log.info("CategoryDaoImpl : delete Category detail by Id --load Category record --"+categoryId);
		Object peristanceInstance = session.load(Category.class, categoryId);
		
		log.info("CategoryDaoImpl : delete Category detail by Id --delete selected category --"+categoryId);
		if(peristanceInstance != null){
			session.delete(peristanceInstance);
			System.out.println("Category deleted successfully");
			log.info("CategoryDaoImpl : Category deleted successfully --"+categoryId);
		}
		
		return true;
	}

	@Transactional
	public List<Category> getCategoriesByName(String name) {
		log.info("CategoryDaoImpl : getCategoriesByName-- getCurrentSession");
		 Session session = sessionFactory.getCurrentSession();
		
		 log.info("CategoryDaoImpl : getCategoriesByName-- select Category where categoryName contains "+name);
		 List<Category> categories = session.createQuery("from Category where categoryName like :name",Category.class)
				 					.setParameter("name", "'%"+name+"%'").list();
		 return (List<Category>)categories;
	}

	@Transactional
	public List<Category> getAllCategories() {
		
		log.info("CategoryDaoImpl : getAll the categories");
		List<Category> categories = sessionFactory.getCurrentSession().createQuery("from Category",Category.class).list();
		return categories;
	}

}
