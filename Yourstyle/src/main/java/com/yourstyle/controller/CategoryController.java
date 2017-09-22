package com.yourstyle.controller;

import java.sql.Timestamp;
import java.util.List;

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
import com.yourstyle.model.Category;

@Controller
public class CategoryController {
	
	@Autowired
	CategoryDao categoryDao;
	
	@RequestMapping(value="/categoryPage", method = RequestMethod.GET)
	public String showlistOfCategory(@ModelAttribute("category") Category category,  BindingResult result,  
            Model model, 
            RedirectAttributes redirectAttrs){
		
		 List<Category> categoryList = categoryDao.getAllCategories();
	     model.addAttribute("categoryList", categoryList);
		return "/categoryPage";
	}
	
	@RequestMapping(value="/saveCategory", method = RequestMethod.POST)
	public String saveCategory(@ModelAttribute("category") Category category, BindingResult result,ModelMap model){
		category.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
		category.setCreatedBy("System");
		categoryDao.saveOrUpdate(category);
		model.addAttribute("category", category);
		return "redirect:/categoryPage";
	}
	
	@RequestMapping(value="/editcategory/{id}", method = RequestMethod.GET)
	public String editcategory(@PathVariable("id") int id,Model model,RedirectAttributes attributes){
		 
		attributes.addFlashAttribute("category", categoryDao.getCategoryById(id));
		return "redirect:/categoryPage";
	}
	
	@RequestMapping(value="/removecategory/{id}", method = RequestMethod.GET)
	public String removeCategory(@PathVariable("id") int id, Model model,RedirectAttributes attributes){
		
		categoryDao.deleteCategoryById(id);
		return "redirect:/categoryPage";
	}
}
