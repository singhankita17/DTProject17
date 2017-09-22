package com.yourstyle.controller;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yourstyle.dao.SupplierDao;
import com.yourstyle.model.Supplier;

@Controller
public class SupplierController {

	@Autowired
	SupplierDao supplierDao;
	
		
	@RequestMapping(value="/supplierPage",method = RequestMethod.GET)
	public String showListOfSuppliers(@ModelAttribute("supplier") Supplier supplier,  BindingResult result,  
            Model model, 
            RedirectAttributes redirectAttrs){
		
		List<Supplier> supplierList = supplierDao.getAllSuppliers();
		
		model.addAttribute("supplierList", supplierList);
		
		return "/supplierPage";
		
	}
	
	@RequestMapping(value="/saveSupplier",method = RequestMethod.POST)
	public String saveSupplier(@ModelAttribute("supplier") Supplier supplier,Model model,BindingResult result){
		supplier.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
		supplier.setCreatedBy("System");
		
		supplierDao.saveOrUpdate(supplier);
		model.addAttribute("supplier", supplier);
		return "redirect:/supplierPage";
	}
	
	@RequestMapping(value="/editsupplier/{id}", method = RequestMethod.GET)
	public String editsupplier(@PathVariable("id") int id,Model model,RedirectAttributes attributes){
		 
		attributes.addFlashAttribute("supplier", supplierDao.getSupplierById(id));
		return "redirect:/supplierPage";
	}
	
	@RequestMapping(value="/removesupplier/{id}", method = RequestMethod.GET)
	public String removesupplier(@PathVariable("id") int id, Model model,RedirectAttributes attributes){
		
		supplierDao.deleteSupplierById(id);
		return "redirect:/supplierPage";
	}
	
}
