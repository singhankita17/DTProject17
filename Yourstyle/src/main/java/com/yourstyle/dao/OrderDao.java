package com.yourstyle.dao;

import java.util.List;

import com.yourstyle.model.Orders;

public interface OrderDao {

	public boolean saveOrUpdate(Orders order);
	
	public Orders getOrderById(int orderId);
	
	public boolean deleteOrderById(int orderId);
	
	public List<Orders> getAllOrdersOfUser(int userId);
}
