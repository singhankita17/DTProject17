package com.yourstyle.controller;

import java.sql.Timestamp;
import java.util.Collection;

import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.yourstyle.dao.CategoryDao;
import com.yourstyle.dao.ProductDao;
import com.yourstyle.dao.SupplierDao;
import com.yourstyle.dao.UserDao;
import com.yourstyle.model.User;

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
		
		private static Logger log = LoggerFactory.getLogger(HomeController.class);
		
		
		
		@RequestMapping(value="/")
		public String showIndexPage(HttpSession session){
			
			log.info("showIndexPage : Adding categoryList to session attribute");
			session.setAttribute("categoryList", categoryDao.getAllCategories());
			log.info("showIndexPage :  Redirecting to index page");
			return "index";
		}
		
		//Added error and logout parameters
		@RequestMapping(value="login", method = RequestMethod.GET)
		public ModelAndView loginPage(Model m,HttpSession session,@RequestParam(value="error",required = false) String error,@RequestParam(value="logout",required = false) String logout){
			
			ModelAndView model = new ModelAndView();
			log.info("loginPage : Adding categoryList to session attribute");
			//session.setAttribute("categoryList", categoryDao.getAllCategories());
			if(error!=null){
				model.addObject("error","Invalid Credentials (Username or Password)");
			}
			if(logout!=null){
				model.addObject("message", "Logged Out from Yourstyle");
			}
			log.info("loginPage : Redirecting to login page");
			m.addAttribute("categoryList", categoryDao.getAllCategories());
			model.setViewName("login");
			return model;
		}
		//Commenting login code for using Spring Security
		/*
		@RequestMapping(value="/login",method = RequestMethod.POST)
		public String showHomePage(@ModelAttribute("email") String email,@ModelAttribute("password") String password,ModelMap model,HttpSession session){
			Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			String userName = principal.toString();
			/*if (principal instanceof User) {
	            userName = ((User)principal).getFirstName()+" "+((User)principal).getLastName() ;
	        } else {
	            userName = principal.toString();
	        }*/
			/*log.info("showHomePage : Fetching User by Email Id");
			User user = userDao.getUserByEmail(email);
			
				if(user==null || !(user.getEmail().equals(email) && user.getPassword().equals(password))){
					log.info("showHomePage : either user is NULL or Username/password is not valid ---> Redirect to login page");
					model.addAttribute("errorMessage", "Invalid Credentials");
					return "/login";
				}*/
			/*
				log.info("showHomePage : Valid credentials -- set user name in model -- redirect to homepage");
			model.addAttribute("userName",userName);
			return "home";
		}*/
		
		@RequestMapping(value = "/login_attributes")
		public String login_session_attributes(HttpSession session,Model model) {
			log.info("login_attributes :  Fetching details from SecurityContextHolder --> email");
			String email = SecurityContextHolder.getContext().getAuthentication().getName();
			System.out.println("Email = "+email);
			log.info("login_attributes :  User details from Database using given email");
			User user = userDao.getUserByEmail(email);
			System.out.println("User = "+user.toString());
			log.info("login_attributes :  Checking if user details found or not Else redirect to Index page");
			if(user!=null){		
				log.info("login_attributes :  Fetching Authorities from Security Context");
					Collection<? extends GrantedAuthority> authorities = (Collection<? extends GrantedAuthority>) SecurityContextHolder.getContext()
					.getAuthentication().getAuthorities();
					
					String role="ROLE_ADMIN";
					model.addAttribute("user", user);
					for (GrantedAuthority authority : authorities) 
					{
						log.info("login_attributes :  check for Admin Role");
					     if (authority.getAuthority().equals(role)) 
					     {
					    	 model.addAttribute("supplierList",supplierDao.getAllSuppliers());
					    	
					    	 model.addAttribute("productList", productDao.getAllProducts());
						 
					     }
					     model.addAttribute("categoryList",categoryDao.getAllCategories());
					     log.info("login_attributes :  Redirect to home Page");
					     return "home";
					}
			}
			
			return "redirect:/";
		
		}
		//Logout Handler
		@RequestMapping(value="/logout", method=RequestMethod.GET)
		public String logout(HttpServletRequest request, HttpServletResponse response,HttpSession session){
			log.info("logout :  Fetching Authentication from Security Context");
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if(auth!=null){
				log.info("logout :  If Authentication found call Logout Handler");
				new SecurityContextLogoutHandler().logout(request, response, auth);
				
			}
			
			return "redirect:/login?logout";
		}
		
		//loginfailed Handler
		@RequestMapping(value="loginfailed", method=RequestMethod.GET)
		public String loginerror(ModelMap model){
			log.info("loginerror :  Redirect to login with error");
			return "redirect:/login?error";
		}
		
		//Access Denied Handler
		@RequestMapping(value="accessDenied", method = RequestMethod.GET)
		public String showAccessDeniedPage(ModelMap model){
			log.info("showAccessDeniedPage :  Redirect to Access Denied Page");
			return "accessDenied";
		}
		
		@RequestMapping(value="home", method = RequestMethod.GET)
		public String showHomePage(){
			log.info("showHomePage : Redirect to homepage");
			return "home";
		}
		
		@RequestMapping(value="signup", method = RequestMethod.GET)
		public String showSignUpPage(Model model){
			log.info("showSignUpPage : Set user detail in model -- Redirect to signup");
			model.addAttribute("user", new User());
			model.addAttribute("categoryList", categoryDao.getAllCategories());
			return "signup";
		}
		
		@RequestMapping(value="savesignup", method = RequestMethod.POST)
		public String saveSignUpPage(@Valid @ModelAttribute("user") User user, BindingResult result,ModelMap model){
			
			if(result.hasErrors()){
				return "signup";
			}else{
			
			log.info("saveSignUpPage : Fetching user detail based on email");
			User userExisting = userDao.getUserByEmail(user.getEmail());
			
			log.info("saveSignUpPage : Check if email is already registered");
			
			if(userExisting != null){
				log.info("saveSignUpPage : This Email is already registered -- Show error");
				
				model.addAttribute("errorMessage","This Email is already registered");
				model.addAttribute("user", new User());
				return "login";
			}
			log.info("saveSignUpPage : Register the new user");
			user.setRole("ROLE_USER");
			user.setEnabled(true);
			user.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
			user.setCreatedBy("System");
			
			log.info("saveSignUpPage : Save details of User");
			userDao.saveOrUpdate(user);
			
			model.addAttribute("firstname", user.getFirstName()+" "+user.getLastName());
			
			return "home";
			}
		}
}
