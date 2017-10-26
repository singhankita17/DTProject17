package com.yourstyle.dao;

import java.util.List;

import com.yourstyle.model.User;

public interface UserDao {

	public boolean saveOrUpdate(User user);
	
	public User getUserById(int userId);
	
	public User getUserByEmail(String email);
	
	public List<User> getAllUsers();
	
}
