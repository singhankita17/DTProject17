package com.yourstyle.controller;

import java.sql.Timestamp;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yourstyle.dao.CategoryDao;
import com.yourstyle.dao.ProductDao;
import com.yourstyle.dao.SupplierDao;
import com.yourstyle.model.Product;

@Controller
public class ProductController {
	
	
	private static Logger log = LoggerFactory.getLogger(ProductController.class);
	

	@Autowired
	ProductDao productDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	SupplierDao supplierDao;
	
	@RequestMapping(value="/productAddPage",method = RequestMethod.GET)
	public String showProductAddPage(@ModelAttribute("product") Product product,BindingResult result,Model model,RedirectAttributes redirectAttrs){
		
		log.info("showProductAddPage : Fetch all the categories, suppliers and products -- set in model");
		model.addAttribute("categoryList", categoryDao.getAllCategories());
		model.addAttribute("supplierList", supplierDao.getAllSuppliers());
		model.addAttribute("productList", productDao.getAllProducts());
		
		return "/productAddPage";
	}
	
	@RequestMapping(value="/saveProduct", method=RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product, BindingResult result,ModelMap model){
		
		log.info("saveProduct : Save product details");
		product.setCreatedBy("System");
		product.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
		
		productDao.saveOrUpdate(product);
		
		log.info("saveProduct : Add product to model");
		model.addAttribute("product", product);
		return "redirect:/productAddPage";
	}
	
	@RequestMapping(value="/editproduct/{id}", method = RequestMethod.GET)
	public String editproduct(@PathVariable("id") int id,Model model,RedirectAttributes attributes){
		 
		log.info("editproduct : Edit product details -- fetch product by Id");
		attributes.addFlashAttribute("product", productDao.getProductById(id));
		return "redirect:/productAddPage";
	}
	
	@RequestMapping(value="/removeproduct/{id}", method = RequestMethod.GET)
	public String removeProduct(@PathVariable("id") int id, Model model,RedirectAttributes attributes){
		
		log.info("removeProduct : Delete product details -- remove product by Id");
		productDao.deleteProductById(id);
		return "redirect:/productAddPage";
	}
}
