package com.yourstyle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yourstyle.dao.CategoryDao;
import com.yourstyle.dao.ProductDao;
import com.yourstyle.dao.SupplierDao;
import com.yourstyle.model.Category;
import com.yourstyle.model.Product;
import com.yourstyle.model.Supplier;

@Controller
public class AdminController {
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	SupplierDao supplierDao;
	
	@Autowired
	ProductDao productDao;
	
	@RequestMapping("adminAdd")
	public String showAdminPage(Model m){
		m.addAttribute("category", new Category());
		m.addAttribute("supplier", new Supplier());
		m.addAttribute("product", new Product());
		return "adminAction";
	}

	@ModelAttribute
	public void addAttribute(Model m){
		
		m.addAttribute("categoryList", categoryDao.getAllCategories());
		m.addAttribute("supplierList", supplierDao.getAllSuppliers());
		m.addAttribute("productList", productDao.getAllProducts());
	}
}
