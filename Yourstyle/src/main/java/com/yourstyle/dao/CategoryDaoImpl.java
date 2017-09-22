package com.yourstyle.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.yourstyle.model.Category;

public class CategoryDaoImpl implements CategoryDao {
	
	@Autowired
	private SessionFactory sessionFactory ;
	

	public CategoryDaoImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean saveOrUpdate(Category category) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(category);
		return true;
	}

	@Transactional
	public Category getCategoryById(int categoryId) {
		
		Category category = (Category) sessionFactory.getCurrentSession().createQuery("select c from Category c where id = :categoryId")
																.setParameter("categoryId", categoryId).uniqueResult();
		System.out.println(category.toString());
		return category;
	}

	@Transactional
	public boolean deleteCategoryById(int categoryId) {
		
		Session session = sessionFactory.getCurrentSession();
		
		Object peristanceInstance = session.load(Category.class, categoryId);
		
		if(peristanceInstance != null){
			session.delete(peristanceInstance);
			System.out.println("Category deleted successfully");
		}
		
		return true;
	}

	@Transactional
	public List<Category> getCategoriesByName(String name) {
		 Session session = sessionFactory.getCurrentSession();
				
		 List<Category> categories = session.createQuery("from Category where categoryName like :name",Category.class)
				 					.setParameter("name", "'%"+name+"%'").list();
		 return (List<Category>)categories;
	}

	@Transactional
	public List<Category> getAllCategories() {
		List<Category> categories = sessionFactory.getCurrentSession().createQuery("from Category",Category.class).list();
		return categories;
	}

}
