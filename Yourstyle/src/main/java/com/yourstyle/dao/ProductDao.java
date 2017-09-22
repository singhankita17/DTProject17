package com.yourstyle.dao;

import java.util.List;

import com.yourstyle.model.Category;
import com.yourstyle.model.Product;

public interface ProductDao {

	public boolean saveOrUpdate(Product product);
	
	public Product getUserById(int productId);
	
	public Product getProductById(int productId);
	
	public boolean deleteProductById(int productId);
		
	public List<Product> getAllProducts();
}
