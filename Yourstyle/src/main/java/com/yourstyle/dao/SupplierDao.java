package com.yourstyle.dao;

import java.util.List;

import com.yourstyle.model.Supplier;

public interface SupplierDao {

	public boolean saveOrUpdate(Supplier supplier);
	
	public Supplier getSupplierById(int supplierId);
	
	public boolean deleteSupplierById(int supplierId);
		
	public List<Supplier> getAllSuppliers();
}
