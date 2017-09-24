package com.yourstyle.controller;

import java.sql.Timestamp;
import org.slf4j.Logger;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
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
		
		private static Logger log = LoggerFactory.getLogger(HomeController.class);
		
		
		
		@RequestMapping(value="/")
		public String showIndexPage(HttpSession session){
			
			log.info("showIndexPage : Adding categoryList to session attribute");
			session.setAttribute("categoryList", categoryDao.getAllCategories());
			log.info("showIndexPage :  Redirecting to index page");
			return "index";
		}
		
		@RequestMapping(value="/login", method = RequestMethod.GET)
		public String loginPage(HttpSession session){
			
			log.info("loginPage : Adding categoryList to session attribute");
			session.setAttribute("categoryList", categoryDao.getAllCategories());
			log.info("loginPage : Redirecting to login page");
			return "login";
		}
		
		@RequestMapping(value="/login",method = RequestMethod.POST)
		public String showHomePage(@ModelAttribute("email") String email,@ModelAttribute("password") String password,ModelMap model){
			log.info("showHomePage : Fetching User by Email Id");
			User user = userDao.getUserByEmail(email);
			
				if(user==null || !(user.getEmail().equals(email) && user.getPassword().equals(password))){
					log.info("showHomePage : either user is NULL or Username/password is not valid ---> Redirect to login page");
					model.addAttribute("errorMessage", "Invalid Credentials");
					return "/login";
				}
				log.info("showHomePage : Valid credentials -- set user name in model -- redirect to homepage");
			model.addAttribute("firstname", user.getFirstName()+" "+user.getLastName());
			
			return "home";
		}
		
		@RequestMapping(value="/home", method = RequestMethod.GET)
		public String showHomePage(){
			log.info("showHomePage : Redirect to homepage");
			return "home";
		}
		
		@RequestMapping(value="/signup", method = RequestMethod.GET)
		public String showSignUpPage(Model model){
			log.info("showSignUpPage : Set user detail in model -- Redirect to signup");
			model.addAttribute("user", new User());
			return "signup";
		}
		
		@RequestMapping(value="/signup", method = RequestMethod.POST)
		public String saveSignUpPage(@ModelAttribute("user") User user, BindingResult result,ModelMap model){
			
			log.info("saveSignUpPage : Fetching user detail based on email");
			User userExisting = userDao.getUserByEmail(user.getEmail());
			
			log.info("saveSignUpPage : Check if email is already registered");
			
			if(userExisting != null){
				log.info("saveSignUpPage : This Email is already registered -- Show error");
				
				model.addAttribute("errorMessage","This Email is already registered");
				model.addAttribute("user", new User());
				return "signup";
			}
			log.info("saveSignUpPage : Register the new user");
			user.setRole("Customer");
			user.setEnabled(true);
			user.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
			user.setCreatedBy("System");
			
			log.info("saveSignUpPage : Save details of User");
			userDao.saveOrUpdate(user);
			
			model.addAttribute("firstname", user.getFirstName()+" "+user.getLastName());
			
			return "home";
		}
}
