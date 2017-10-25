package com.yourstyle.controller;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import com.yourstyle.dao.OrderDao;
import com.yourstyle.dao.ProductDao;
import com.yourstyle.model.Address;
import com.yourstyle.model.Cart;
import com.yourstyle.model.Orders;
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
	
	@Autowired
	OrderDao orderDao;
	
	@RequestMapping(value="addToCart/{id}",method = RequestMethod.GET)
	public String addProductToCart(@PathVariable("id") int id, HttpSession session,Model model,RedirectAttributes attributes){
		log.info("addProductToCart : Save Product to cart -- based on given Product Id");
		int qtyToAdd = 1;
		
		User user = (User) session.getAttribute("user");		
		Product  product = productDao.getProductById(id);
		Cart cartItem = cartDao.getCartItem(id, user.getId());
		
		int remainingQty = product.getQuantityAvailable() - qtyToAdd;
		
		if(remainingQty >= 0){
		
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
			
			attributes.addFlashAttribute("message", "This product was already added to cart");
			
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
			
			attributes.addFlashAttribute("message", "Product successfully added to cart");
			
		}
		
		cartDao.saveOrUpdate(cartItem);
		}
		else
		{
			attributes.addFlashAttribute("error", "Product not in Stock");
			
		}
		log.info("addProductToCart : Product added to cart --->"+id);
		//model.addAttribute("cartItem", product);
		//attributes.addFlashAttribute("product", product);
		//return "redirect:/";
		attributes.addFlashAttribute("product", product);
		return "redirect:/productDetailPage";
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
	
	@RequestMapping(value="editCartItem/{id}")
	public String editCartItem(@PathVariable("id") int id,@RequestParam("cartQty") int cartQty,HttpSession session,RedirectAttributes attributes){
		 
		log.info("editCartItem : Edit Cart Item details -- fetch Cart by Id");
		Cart cartItem = cartDao.getCartById(id);
		
		
		Product product = productDao.getProductById(cartItem.getProductId());
		
		int remainingQty = product.getQuantityAvailable() - cartQty;
		
		if(remainingQty >= 0){
									
				User user = (User) session.getAttribute("user");
				
				cartItem.setQuantityAdded(cartQty);
				
				if(product.isOnSale()){
					cartItem.setProductPrice(product.getSalePrice());
					cartItem.setSubTotal(product.getSalePrice() * cartQty);
				}else{
					cartItem.setProductPrice(product.getPrice());
					cartItem.setSubTotal(product.getPrice() * cartQty);
				}
				
				cartItem.setUpdatedBy(user.getEmail());
				cartItem.setUpdatedTimestamp(new Timestamp(System.currentTimeMillis()));
				cartDao.saveOrUpdate(cartItem);
				
				//attributes.addAttribute("error", "");
				
		}else{
			attributes.addFlashAttribute("error", "Insufficient stock for product --> "+product.getProductName());
		}
		
		return "redirect:/goToCart";
		
	}
	
	@RequestMapping(value="removeCartItem/{id}",method=RequestMethod.GET)
	public String removeCartItem(@PathVariable("id") int id,HttpSession session){
		 
		log.info("removeCartItem : Delete Cart Item details -- fetch Cart by Id");
		
		//Cart cartItem = cartDao.getCartById(id);
		
		//Product product = productDao.getProductById(cartItem.getProductId());
					
		//User user = (User) session.getAttribute("user");
				
		cartDao.deleteCartById(id);
		
		return "redirect:/goToCart";
		
	}
	
	
	@RequestMapping(value="shippingAddress",method = RequestMethod.GET)
	public String showShippingPage(@ModelAttribute("address") Address address,BindingResult result, HttpSession session,Model model){
		
		User user = (User) session.getAttribute("user");
		
		List<Cart> cartList = cartDao.getCartByUserId(user.getId());
				
		model.addAttribute("cartList", cartList);
		model.addAttribute("addressList", addressDao.getAllAddressOfUser(user.getId()));
		
		return "shipping";
	}
	
	
	@RequestMapping(value="selectShippingAddress",method = RequestMethod.POST)
	public String selectShippingAddress(@RequestParam("shipaddress") int id,HttpSession session,Model m,RedirectAttributes attributes){
		
		User user = (User) session.getAttribute("user");
		
		Address address = addressDao.getAddressById(id);
		session.setAttribute("address", address);
		attributes.addFlashAttribute("address", address);
		attributes.addFlashAttribute("cartTotalAmount", cartDao.getCartTotal(user.getId()));
		
		return "redirect:/orderSummary";
	}
	
	@RequestMapping(value="saveShippingAddress",method = RequestMethod.POST)
	public String saveShippingPage(@ModelAttribute("address") Address address,BindingResult result, HttpSession session,Model m,RedirectAttributes attributes){
		
		User user = (User) session.getAttribute("user");
		address.setCreatedBy("SYSTEM");
		address.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
		address.setPersonId(user.getId());
		
		addressDao.saveOrUpdate(address);
		session.setAttribute("address", address);
		attributes.addFlashAttribute("address", address);
		attributes.addFlashAttribute("cartTotalAmount", cartDao.getCartTotal(user.getId()));
		
		return "redirect:/orderSummary";
	}
	
	@RequestMapping(value="orderSummary",method = RequestMethod.GET)
	public String showOrderSummary(HttpSession session,Model model){
		
		User user = (User) session.getAttribute("user");
		Address address = (Address) session.getAttribute("address");
		model.addAttribute("address", address);
		model.addAttribute("cartTotalAmount", cartDao.getCartTotal(user.getId()));
		
		return "orderSummary";
	}
	
	@RequestMapping(value="processOrder")
	public String placeOrder(HttpSession session){
		log.info("placeOrder : Processing order details");
		Orders order=new Orders();
		
		log.info("placeOrder : Set user attributes");
		User user = (User) session.getAttribute("user");
		
		order.setUserId(user.getId());
		
		log.info("placeOrder : Set shipping address");
		Address address = (Address) session.getAttribute("address");
		
		order.setShipAddressId(address.getId());
		
		List<Cart> cartItemsList = cartDao.getCartByUserId(user.getId());
		
		double totalAmount = 0;
		log.info("placeOrder : update Total Order amount");
		for(Cart cartItem:cartItemsList){
			
			totalAmount += cartItem.getProductPrice() * cartItem.getQuantityAdded();
		}
		order.setTotalAmount(totalAmount);
		order.setOrderStatus("PROCESSED");		
		order.setPaymentMethod("COD");	
		order.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
		order.setCreatedBy("SYSTEM");		
		
		log.info("placeOrder : Save order details");
		orderDao.saveOrUpdate(order);
		
		log.info("placeOrder : Update product Quantity");
		for(Cart cartItem:cartItemsList){
			
			Product product = productDao.getProductById(cartItem.getProductId());
			int quantityRemaining = product.getQuantityAvailable() - cartItem.getQuantityAdded();
			product.setQuantityAvailable(quantityRemaining);
			if(quantityRemaining==0){
				product.setInStock(false);
			}
			productDao.saveOrUpdate(product);
		}
		log.info("placeOrder : Update Cart Status");
		String statusValue = "INACTIVE";
		cartDao.updateCartStatus(user.getId(), statusValue);
		
		return "acknowledgement";
	}

}
