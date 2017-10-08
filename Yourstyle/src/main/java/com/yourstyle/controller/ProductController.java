package com.yourstyle.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
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
	public String saveProduct(@ModelAttribute("product") Product product,BindingResult result,ModelMap model,@RequestParam("file") MultipartFile file){
		
		log.info("saveProduct : Save product details");
		product.setCreatedBy("System");
		product.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
		log.info("saveProduct : Calling upload File method");
		String imageName = uploadFile(file,product.getId());
		log.info("saveProduct : Add Image file name to product :"+imageName);
		product.setProductImage(imageName);
		productDao.saveOrUpdate(product);
		
		log.info("saveProduct : Add product to model");
		model.addAttribute("product", product);
		return "redirect:/productAddPage";
	}
	
	private String uploadFile(MultipartFile file, int productId) {
		
		log.info("uploadFile : Upload file to the server");
		String UPLOAD_DIRECTORY = "wtpwebapps/Yourstyle/resources/images";
		log.info("uploadFile : UPLOAD_DIRECTORY = "+UPLOAD_DIRECTORY);
		String rootPath = System.getProperty("catalina.base");
		log.info("uploadFile : rootPath = "+rootPath);
		File dir = new File(rootPath+File.separator+UPLOAD_DIRECTORY);
		if(!dir.exists()){
			dir.mkdirs();
		}
		String filename = file.getOriginalFilename();
		log.info("uploadFile : File complete path = "+dir.getAbsolutePath()+File.separator+filename);
		
		try {	
			log.info("uploadFile : Convert file to a byte array ");
		byte[] bytes=file.getBytes();
		BufferedOutputStream bufferedOutputStream;
		log.info("uploadFile : Upload file to the given path and write bytes to the file");
		bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(dir.getAbsolutePath()+File.separator+filename)));
		bufferedOutputStream.write(bytes);
		bufferedOutputStream.flush();
		bufferedOutputStream.close();
		} catch (FileNotFoundException e) {
			
			return "Failed to upload file : "+filename+" => "+e.getMessage();
		} catch (IOException e) {
			
			return "Failed to upload file : "+filename+" => "+e.getMessage();
		}

		return filename;
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
	
	//Added to fetch Products based on Categories
	@RequestMapping(value="/fetchByCategory/{id}", method = RequestMethod.GET)
	public String fetchProductByCategory(@PathVariable("id") int id, Model model,RedirectAttributes attributes){
		
		log.info("fetchProductByCategory : Fetch product details -- based on given Category Id");
		List<Product> products = productDao.getAllProductForCategory(id);
		log.info("fetchProductByCategory : Product fetched for display  based on Category Id");
		attributes.addFlashAttribute("products", products);
		return "redirect:/home";
	}
}
