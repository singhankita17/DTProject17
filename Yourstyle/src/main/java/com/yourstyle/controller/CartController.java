package com.yourstyle.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import com.yourstyle.dao.CategoryDao;
import com.yourstyle.dao.OrderDao;
import com.yourstyle.dao.PaymentDao;
import com.yourstyle.dao.ProductDao;
import com.yourstyle.model.Address;
import com.yourstyle.model.Cart;
import com.yourstyle.model.Orders;
import com.yourstyle.model.Payment;
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
	
	@Autowired
	PaymentDao paymentDao;
	
	@Autowired
	CategoryDao categoryDao;
	
	@RequestMapping(value="addToCart/{id}",method = RequestMethod.GET)
	public String addProductToCart(@PathVariable("id") int id,@RequestParam("quantityToAdd") int quantityToAdd, HttpSession session,Model model,RedirectAttributes attributes){
		log.info("addProductToCart : Save Product to cart -- based on given Product Id");
		int qtyToAdd = quantityToAdd;
		
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
			
			model.addAttribute("message", "This product was already added to cart");
			
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
			
			model.addAttribute("message", "Product successfully added to cart");
			
		}
		model.addAttribute("categoryList", categoryDao.getAllCategories());
		cartDao.saveOrUpdate(cartItem);
		model.addAttribute("addbtnStatus", false);
		}
		else
		{
			model.addAttribute("error", "Product not in Stock");
			
		}
		log.info("addProductToCart : Product added to cart --->"+id);
		//model.addAttribute("cartItem", product);
		//attributes.addFlashAttribute("product", product);
		//return "redirect:/";
		model.addAttribute("product", product);
		return "productDetailPage";
	}
	
	@RequestMapping(value="/goToCart",method = RequestMethod.GET)
	public String viewCart(HttpSession session,Model model){
		
		if(session != null){
		User user = (User) session.getAttribute("user");
		
		List<Cart> cartList = cartDao.getCartByUserId(user.getId());
		
		model.addAttribute("productList", productDao.getAllProducts());
		
		model.addAttribute("cartList", cartList);
		
				
		if(cartDao.getCartSize(user.getId())!=0){
			model.addAttribute("cartTotalAmount", cartDao.getCartTotal(user.getId()));
		}else{
			model.addAttribute("EmptyCart", true);
		}
		}else{
			model.addAttribute("error", "User session invalid");
		}
			
		
		model.addAttribute("categoryList", categoryDao.getAllCategories());
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
		
		return "redirect:/showpaymentPage";
	}
	
	@RequestMapping(value="saveShippingAddress",method = RequestMethod.POST)
	public String saveShippingPage(@Valid @ModelAttribute("address") Address address,BindingResult result, HttpSession session,Model m,RedirectAttributes attributes){
		if(result.hasErrors()){
			System.out.println(result.getAllErrors().toString());
			return "shipping";
		}else{
		User user = (User) session.getAttribute("user");
		address.setCreatedBy("SYSTEM");
		address.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
		address.setPersonId(user.getId());
		
		addressDao.saveOrUpdate(address);
		session.setAttribute("address", address);
		attributes.addFlashAttribute("address", address);
		attributes.addFlashAttribute("cartTotalAmount", cartDao.getCartTotal(user.getId()));
		
		return "redirect:/showpaymentPage";
		}
	}
	
	@RequestMapping(value="orderSummary",method = RequestMethod.GET)
	public String showOrderSummary(HttpSession session,Model model){
		
		User user = (User) session.getAttribute("user");
		Address address = (Address) session.getAttribute("address");
		Payment payment = (Payment) session.getAttribute("payment");
		model.addAttribute("address", address);
		model.addAttribute("payment", payment);
		model.addAttribute("cartTotalAmount", cartDao.getCartTotal(user.getId()));
		model.addAttribute("cartList", cartDao.getCartByUserId(user.getId()));
		model.addAttribute("productList",productDao.getAllProducts());
		return "orderSummary";
	}
	
	@RequestMapping(value="processOrder")
	public String placeOrder(HttpSession session, Model model){
				
		log.info("placeOrder : Set user attributes");
		User user = (User) session.getAttribute("user");		
		
		log.info("placeOrder : Set shipping address");
		Address address = (Address) session.getAttribute("address");
		
		log.info("placeOrder : Set payment details");
		Payment payment = (Payment) session.getAttribute("payment");
		
		log.info("placeOrder : Processing order details");	
		List<Cart> cartItemsList = cartDao.getCartByUserId(user.getId());
				
		double totalAmount = 0;
		
		log.info("placeOrder : update Total Order amount");
		for(Cart cartItem:cartItemsList){
			
			totalAmount += cartItem.getProductPrice() * cartItem.getQuantityAdded();
		}
				
		
		
		log.info("placeOrder : Update product Quantity");
		for(Cart cartItem:cartItemsList){
			
			Orders order=new Orders();
			order.setUserId(user.getId());
			order.setShipAddressId(address.getId());
			order.setPaymentId(payment.getId());
			order.setTotalAmount(totalAmount);
			order.setOrderStatus("PROCESSED");	
			order.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
			order.setCreatedBy("SYSTEM");
			order.setCartId(cartItem.getId());
			log.info("placeOrder : Save order details");
			
			orderDao.saveOrUpdate(order);
						
			Product product = productDao.getProductById(cartItem.getProductId());
			int quantityRemaining = product.getQuantityAvailable() - cartItem.getQuantityAdded();
			product.setQuantityAvailable(quantityRemaining);
			if(quantityRemaining==0){
				product.setInStock(false);
			}else{
				product.setInStock(true);
			}
			productDao.saveOrUpdate(product);
			
			log.info("placeOrder : Update Cart Status");
			String statusValue = "INACTIVE";
			cartItem.setStatus(statusValue);
			cartDao.saveOrUpdate(cartItem);
		}
		
		return "redirect:showinvoice";
	}
	
	@RequestMapping(value="showpaymentPage")
	public String showPaymentPage(@ModelAttribute("payment") Payment payment,BindingResult result,HttpSession session,Model model){
		User user = (User) session.getAttribute("user");
		Address address = (Address) session.getAttribute("address");
		model.addAttribute("address", address);
		//model.addAttribute("paymentList", paymentDao.getUserCardPaymentInfo(user.getId()));
		model.addAttribute("cartTotalAmount", cartDao.getCartTotal(user.getId()));
		
		return "paymentPage";
	}
	
	@RequestMapping(value="selectPaymentMethod")
	public String selectPaymentMethod(@Valid @ModelAttribute("payment") Payment payment,BindingResult result,HttpSession session,Model m,RedirectAttributes attributes){
		if(result.hasErrors()){
			System.out.println(result.getAllErrors().toString());
			return "paymentPage";
		}else{
		User user = (User) session.getAttribute("user");
		String paymentChoice = "";
		if(payment.getPaymentMethod().equals("creditcard")){
			paymentChoice = "Credit Card";
		}else if(payment.getPaymentMethod().equals("debitcard")){
			paymentChoice = "Debit Card";
		}else if(payment.getPaymentMethod().equals("netbanking")){
			paymentChoice = "NetBanking";
		}else if(payment.getPaymentMethod().equals("cod")){
			paymentChoice = "Cash On Delivery";
		}
		
		double totalAmount = cartDao.getCartTotal(user.getId());
		payment.setUserId(user.getId());
		payment.setTotalAmount(totalAmount);
		paymentDao.savePaymentInfo(payment);
		
		session.setAttribute("payment", payment);
		attributes.addFlashAttribute("payment", payment);
		attributes.addFlashAttribute("paymentChoice", paymentChoice);
		attributes.addFlashAttribute("cartTotalAmount", totalAmount);
		
		return "redirect:/orderSummary";
		}
	}
	
	@RequestMapping(value="showinvoice")
	public String showInvoiceAcknoledgement(HttpSession session,Model model){
			
		if(session != null){
			
			User user = (User) session.getAttribute("user");
			
			List<Orders> orderList = orderDao.getAllOrdersOfUser(user.getId());
			List<Cart> cartList = new ArrayList<Cart>();
			
			for(Orders order:orderList){
				
				cartList.add(cartDao.getCartById(order.getCartId()));
			}
			
		    model.addAttribute("cartList", cartList);
		    model.addAttribute("productList", productDao.getAllProducts());
		    model.addAttribute("categoryList", categoryDao.getAllCategories());
		}
		else{
			
			model.addAttribute("error", "No order detail found");
			
		}
		return "acknowledgement";
	}

}
