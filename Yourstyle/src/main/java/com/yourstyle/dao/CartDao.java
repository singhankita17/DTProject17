package com.yourstyle.dao;


import com.yourstyle.model.Cart;


public interface CartDao {

	public boolean saveOrUpdate(Cart cart);
	
	public Cart getCartById(int cartId);
	
	public boolean deleteCartById(int cartId);
	
	public Cart getCartByUserId(int userId);
	
	public Cart getCartItem(int productId, int userId);
	
	public double getCartTotal(int userId);
	
	public long getCartSize(int userId);
}
