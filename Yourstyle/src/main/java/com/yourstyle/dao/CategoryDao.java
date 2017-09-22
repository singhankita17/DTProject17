package com.yourstyle.dao;

import java.util.List;

import com.yourstyle.model.Category;


public interface CategoryDao {

	public boolean saveOrUpdate(Category category);
	
	public Category getCategoryById(int categoryId);
	
	public boolean deleteCategoryById(int categoryId);
	
	public List<Category> getCategoriesByName(String name);
	
	public List<Category> getAllCategories();
}
