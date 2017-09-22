package com.yourstyle.controller;

import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yourstyle.dao.CategoryDao;
import com.yourstyle.dao.UserDao;
import com.yourstyle.model.User;

@Controller
public class HomeController {

		@Autowired
		UserDao userDao;
		
		@Autowired
		CategoryDao categoryDao;
		
				
		@RequestMapping(value="/")
		public String showIndexPage(HttpSession session){
			session.setAttribute("categoryList", categoryDao.getAllCategories());
			return "index";
		}
		
		@RequestMapping(value="/login", method = RequestMethod.GET)
		public String loginPage(HttpSession session){
			session.setAttribute("categoryList", categoryDao.getAllCategories());
			return "login";
		}
		
		@RequestMapping(value="/login",method = RequestMethod.POST)
		public String showHomePage(@ModelAttribute("email") String email,@ModelAttribute("password") String password,ModelMap model){
			User user = userDao.getUserByEmail(email);
			
				if(user==null || !(user.getEmail().equals(email) && user.getPassword().equals(password))){
					model.addAttribute("errorMessage", "Invalid Credentials");
					return "/login";
				}
			
			model.addAttribute("firstname", user.getFirstName()+" "+user.getLastName());
			
			return "home";
		}
		
		@RequestMapping(value="/home", method = RequestMethod.GET)
		public String showHomePage(){
			return "home";
		}
		
		@RequestMapping(value="/signup", method = RequestMethod.GET)
		public String showSignUpPage(Model model){
			model.addAttribute("user", new User());
			return "signup";
		}
		
		@RequestMapping(value="/signup", method = RequestMethod.POST)
		public String saveSignUpPage(@ModelAttribute("user") User user, BindingResult result,ModelMap model){
			User userExisting = userDao.getUserByEmail(user.getEmail());
			if(userExisting != null){
				model.addAttribute("errorMessage","This Email is already registered");
				model.addAttribute("user", new User());
				return "signup";
			}
				
			user.setRole("Customer");
			user.setEnabled(true);
			user.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
			user.setCreatedBy("System");
			userDao.saveOrUpdate(user);
			model.addAttribute("firstname", user.getFirstName()+" "+user.getLastName());
			return "home";
		}
}
