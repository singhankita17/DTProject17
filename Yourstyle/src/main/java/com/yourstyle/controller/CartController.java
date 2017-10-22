package com.yourstyle.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yourstyle.dao.AddressDao;
import com.yourstyle.dao.CartDao;
import com.yourstyle.dao.ProductDao;
import com.yourstyle.model.Address;
import com.yourstyle.model.Cart;
import com.yourstyle.model.Category;
import com.yourstyle.model.Product;
import com.yourstyle.model.User;

@Controller
public class CartController {
	
	
	private static Logger log = LoggerFactory.getLogger(ProductController.class);
	

	@Autowired
	ProductDao productDao;
	
	@Autowired
	CartDao cartDao;
	
	@Autowired
	AddressDao addressDao;
	
	@RequestMapping(value="/addToCart/{id}",method = RequestMethod.GET)
	public String addProductToCart(@PathVariable("id") int id, HttpSession session,Model model,RedirectAttributes attributes){
		log.info("addProductToCart : Save Product to cart -- based on given Product Id");
		int qtyToAdd = 1;
		
		User user = (User) session.getAttribute("user");		
		Product  product = productDao.getProductById(id);
		Cart cartItem = cartDao.getCartItem(id, user.getId());
		
		if( cartItem != null){
			
			qtyToAdd = cartItem.getQuantityAdded() + qtyToAdd;
			cartItem.setQuantityAdded(qtyToAdd);
			if(product.isOnSale()){
				cartItem.setProductPrice(product.getSalePrice());
				cartItem.setSubTotal(product.getSalePrice() * qtyToAdd);
			}else{
				cartItem.setProductPrice(product.getPrice());
				cartItem.setSubTotal(product.getPrice() * qtyToAdd);
			}
			cartItem.setUpdatedTimestamp(new Timestamp(System.currentTimeMillis()));
			cartItem.setUpdatedBy("SYSTEM");
			
			cartDao.saveOrUpdate(cartItem);
			
		}else{
			cartItem = new Cart();
			
			cartItem.setProductId(id);
			cartItem.setQuantityAdded(qtyToAdd);
			
			cartItem.setUserId(user.getId());
			cartItem.setStatus("ACTIVE");
			if(product.isOnSale()){
				cartItem.setProductPrice(product.getSalePrice());
				cartItem.setSubTotal(product.getSalePrice() * qtyToAdd);
			}else{
				cartItem.setProductPrice(product.getPrice());
				cartItem.setSubTotal(product.getPrice() * qtyToAdd);
			}
			cartItem.setCreatedBy("SYSTEM");
			cartItem.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
			
			cartDao.saveOrUpdate(cartItem);
		}
		
		log.info("addProductToCart : Product added to cart --->"+id);
		//model.addAttribute("cartItem", product);
		//attributes.addFlashAttribute("product", product);
		return "redirect:/";
	}
	
	@RequestMapping(value="goToCart",method = RequestMethod.GET)
	public String viewCart(HttpSession session,Model model){
		User user = (User) session.getAttribute("user");
		List<Cart> cartList = cartDao.getCartByUserId(user.getId());
		
		model.addAttribute("productList", productDao.getAllProducts());
		
		model.addAttribute("cartList", cartList);
		
		if(cartDao.getCartSize(user.getId())!=0){
			model.addAttribute("cartTotalAmount", cartDao.getCartTotal(user.getId()));
		}else{
			model.addAttribute("EmptyCart", true);
		}
		
		return "CartPage";
	}
	
	@RequestMapping(value="editCartItem/{id}",method=RequestMethod.GET)
	public String editCartItem(@PathVariable("id") int id,@RequestParam("cartQty") int cartQuantity,HttpSession session,RedirectAttributes attributes){
		 
		log.info("editCartItem : Edit Cart Item details -- fetch Cart by Id");
		Cart cartItem = cartDao.getCartById(id);
		
		Product product = productDao.getProductById(cartItem.getProductId());
		
		if(product.getQuantityAvailable() - cartQuantity >= 0){
			
		
				product.setQuantityAvailable(product.getQuantityAvailable() - cartQuantity);
				
				User user = (User) session.getAttribute("user");
				product.setUpdatedBy(user.getEmail());
				product.setUpdatedTimestamp(new Timestamp(System.currentTimeMillis()));
				
				productDao.saveOrUpdate(product);
				
				
				cartItem.setQuantityAdded(cartQuantity);
				
				if(product.isOnSale()){
					cartItem.setProductPrice(product.getSalePrice());
					cartItem.setSubTotal(product.getSalePrice() * cartQuantity);
				}else{
					cartItem.setProductPrice(product.getPrice());
					cartItem.setSubTotal(product.getPrice() * cartQuantity);
				}
				
				cartItem.setUpdatedBy(user.getEmail());
				cartItem.setUpdatedTimestamp(new Timestamp(System.currentTimeMillis()));
				cartDao.saveOrUpdate(cartItem);
				
				//attributes.addAttribute("error", "");
				
		}else{
			attributes.addAttribute("error", "Insufficient stock for product --> "+product.getProductName());
		}
		
		return "redirect:/goToCart";
		
	}
	
	@RequestMapping(value="removeCartItem/{id}",method=RequestMethod.GET)
	public String removeCartItem(@PathVariable("id") int id,HttpSession session){
		 
		log.info("removeCartItem : Delete Cart Item details -- fetch Cart by Id");
		
		Cart cartItem = cartDao.getCartById(id);
		
		Product product = productDao.getProductById(cartItem.getProductId());
		
		product.setQuantityAvailable(product.getQuantityAvailable() + cartItem.getQuantityAdded());
		
		User user = (User) session.getAttribute("user");
		product.setUpdatedBy(user.getEmail());
		product.setUpdatedTimestamp(new Timestamp(System.currentTimeMillis()));
		
		productDao.saveOrUpdate(product);
		
		cartDao.deleteCartById(id);
		
		return "redirect:/goToCart";
		
	}
	
	
	@RequestMapping(value="shippingAddress",method = RequestMethod.GET)
	public String showShippingPage(@ModelAttribute("address") Address address,BindingResult result, HttpSession session,Model model){
		User user = (User) session.getAttribute("user");
		
		List<Cart> cartList = cartDao.getCartByUserId(user.getId());
				
		model.addAttribute("cartList", cartList);
		
		return "shipping";
	}
	
	@RequestMapping(value="saveShippingAddress",method = RequestMethod.POST)
	public String saveShippingPage(@ModelAttribute("address") Address address,BindingResult result, HttpSession session,Model m,RedirectAttributes attributes){
		
		User user = (User) session.getAttribute("user");
		address.setCreatedBy("SYSTEM");
		address.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
		address.setPersonId(user.getId());
		
		addressDao.saveOrUpdate(address);
		
		attributes.addFlashAttribute("address", address);
		attributes.addFlashAttribute("cartTotalAmount", cartDao.getCartTotal(user.getId()));
		
		return "redirect:/orderSummary";
	}
	
	@RequestMapping(value="orderSummary",method = RequestMethod.GET)
	public String showOrderSummary(HttpSession session,Model model){
		
		User user = (User) session.getAttribute("user");
		
		model.addAttribute("address", addressDao.getAddressOfUser(user.getId()));
		model.addAttribute("cartTotalAmount", cartDao.getCartTotal(user.getId()));
		
		return "orderSummary";
	}

}
