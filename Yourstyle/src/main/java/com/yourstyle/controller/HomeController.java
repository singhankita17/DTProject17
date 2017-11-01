package com.yourstyle.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yourstyle.dao.CategoryDao;
import com.yourstyle.dao.ProductDao;
import com.yourstyle.dao.SupplierDao;
import com.yourstyle.dao.UserDao;

@Controller
public class HomeController {
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@Autowired
	SupplierDao supplierDao;
	
	@Autowired
	ProductDao productDao;
	
	private static Logger log = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="/")
	public String showIndexPage(HttpSession session,Model model){
		/*
		try {
			productDao.indexProducts();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		log.info("showIndexPage : Adding categoryList to session attribute");
		//session.setAttribute("categoryList", categoryDao.getAllCategories());
		model.addAttribute("categoryList", categoryDao.getAllCategories());
		log.info("showIndexPage :  Redirecting to index page");
		return "index";
	}
	

	@RequestMapping(value="home", method = RequestMethod.GET)
	public String showHomePage(Model m){
		log.info("showHomePage : Redirect to homepage");
		m.addAttribute("categoryList", categoryDao.getAllCategories());
		return "home";
	}

	@RequestMapping(value="terms")
	public String showTermsOfUse(Model m){
		m.addAttribute("categoryList", categoryDao.getAllCategories());
		return "termsOfUse";
	}
	
	@RequestMapping(value="privacy")
	public String showPrivacyPolicy(Model m){
		m.addAttribute("categoryList", categoryDao.getAllCategories());
		return "privacypolicy";
	}
	
	@RequestMapping(value="contactus")
	public String showContactUsPage(Model m){
		m.addAttribute("categoryList", categoryDao.getAllCategories());
		return "contactUs";
	}
	
	@RequestMapping(value="adminHome")
	public String showAdminHomePage(Model m){
		m.addAttribute("categoryList", categoryDao.getAllCategories());		
		return "adminHome";
	}
}
