package com.yourstyle.dao;


import java.util.List;

import com.yourstyle.model.Cart;


public interface CartDao {

	public boolean saveOrUpdate(Cart cart);
	
	public Cart getCartById(int cartId);
	
	public boolean deleteCartById(int cartId);
	
	public List<Cart> getCartByUserId(int userId);
	
	public Cart getCartItem(int productId, int userId);
	
	public double getCartTotal(int userId);
	
	public long getCartSize(int userId);
	
	public int updateCartStatus(int userId,String status);
	
}
