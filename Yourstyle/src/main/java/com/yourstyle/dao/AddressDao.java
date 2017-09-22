package com.yourstyle.dao;

import java.util.List;

import com.yourstyle.model.Address;

public interface AddressDao {

	public boolean saveOrUpdate(Address address);
	
	public Address getAddressById(int addressId);
	
	public boolean deleteAddressById(int addressId);
	
	public List<Address> getAllAddressOfUser(int userId);
}
