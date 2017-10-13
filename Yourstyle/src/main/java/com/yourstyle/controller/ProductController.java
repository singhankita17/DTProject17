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
	
	
	@RequestMapping(value="productPage",method = RequestMethod.GET)
	public String showProductPage(@ModelAttribute("product") Product product,BindingResult result,Model model,RedirectAttributes redirectAttrs){
		
		log.info("showProductPage : Fetch all the categories, suppliers and products -- set in model");
		
		model.addAttribute("categoryList", categoryDao.getAllCategories());
		model.addAttribute("supplierList", supplierDao.getAllSuppliers());
		model.addAttribute("productList", productDao.getAllProducts());
		
		return "productPage";
	}
	
	@RequestMapping(value="saveProduct", method=RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product,BindingResult result,ModelMap model,@RequestParam("productImage") MultipartFile file){
		
		log.info("saveProduct : Save product details");
		product.setCreatedBy("System");
		product.setCreatedTimestamp(new Timestamp(System.currentTimeMillis()));
		log.info("saveProduct : Save the product details to DB");
		productDao.saveOrUpdate(product);
		log.info("saveProduct : Calling upload File method");
		String imageName="";
		if(!file.isEmpty()){
			try {
				imageName = uploadFile(file,product.getId());
				log.info("saveProduct : Add Image file name to product :"+imageName);
			} catch (IOException e) {
				log.info("saveProduct : Add Image file name to product :"+imageName+" failed to upload => "+e.getMessage());
				model.addAttribute("errorMessage","Failed to upload file => "+e.getMessage());
			}
		}else{
			model.addAttribute("errorMessage", "Problem in File Uploading");
		}
		log.info("saveProduct : Add product to model");
		model.addAttribute("product", product);
		return "redirect:/productPage";
	}
	
	private String uploadFile(MultipartFile fileDetail, int productId) throws IOException {
		
		log.info("uploadFile : Upload file to the server");
		String UPLOAD_DIRECTORY = "wtpwebapps/Yourstyle/resources/images";
		
		log.info("uploadFile : UPLOAD_DIRECTORY = "+UPLOAD_DIRECTORY);
		String rootPath = System.getProperty("catalina.base");
		log.info("uploadFile : rootPath = "+rootPath);
		File dir = new File(rootPath+File.separator+UPLOAD_DIRECTORY);
		
		
		String filename = String.valueOf(productId)+".jpg";
		File fileToUpload = new File(dir.getAbsolutePath()+File.separator+filename);
		log.info("uploadFile : File complete path = "+dir.getAbsolutePath()+File.separator+filename);
			
		log.info("uploadFile : Convert file to a byte array ");
		byte[] bytes=fileDetail.getBytes();
		BufferedOutputStream bufferedOutputStream;
		log.info("uploadFile : Upload file to the given path and write bytes to the file");
		bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(fileToUpload));
		bufferedOutputStream.write(bytes);
		bufferedOutputStream.flush();
		bufferedOutputStream.close();
		

		return filename;
	}

	@RequestMapping(value="/editproduct/{id}", method = RequestMethod.GET)
	public String editproduct(@PathVariable("id") int id,Model model,RedirectAttributes attributes){
		 
		log.info("editproduct : Edit product details -- fetch product by Id");
		attributes.addFlashAttribute("product", productDao.getProductById(id));
		return "redirect:/productPage";
	}
	
	@RequestMapping(value="/removeproduct/{id}", method = RequestMethod.GET)
	public String removeProduct(@PathVariable("id") int id, Model model,RedirectAttributes attributes){
		
		log.info("removeProduct : Delete product details -- remove product by Id");
		productDao.deleteProductById(id);
		log.info("removeProduct : Remove image file from server");
		removefile(id);
		log.info("removeProduct : Redirect to product page");
		return "redirect:/productPage";
	}
	
	private void removefile(int productId) {
		
		log.info("removefile : Remove file from the server");
		String UPLOAD_DIRECTORY = "wtpwebapps/Yourstyle/resources/images";
		log.info("removefile : UPLOAD_DIRECTORY = "+UPLOAD_DIRECTORY);
		String rootPath = System.getProperty("catalina.base");
		log.info("removefile : rootPath = "+rootPath);
		File dir = new File(rootPath+File.separator+UPLOAD_DIRECTORY);
		
		String filename = String.valueOf(productId)+".jpg";
		File fileToRemove = new File(dir.getAbsolutePath()+File.separator+filename);
		fileToRemove.delete();
		log.info("removeFile : Image File removed from  => "+dir.getAbsolutePath()+File.separator+filename);			
		
	}

	//Added to fetch Products based on Categories
	@RequestMapping(value="fetchByCategory/{id}", method = RequestMethod.GET)
	public String fetchProductByCategory(@PathVariable("id") int id, Model model,RedirectAttributes attributes){
		
		log.info("fetchProductByCategory : Fetch product details -- based on given Category Id");
		List<Product> products = productDao.getAllProductForCategory(id);
		log.info("fetchProductByCategory : Product fetched for display  based on Category Id");
		attributes.addFlashAttribute("products", products);
		return "redirect:/home";
	}
	
	@RequestMapping(value="productDetail/{id}",method = RequestMethod.GET)
	public String getProductById(@PathVariable("id") int id,Model model,RedirectAttributes attributes){
		log.info("getProductById : Fetch product details -- based on given Product Id");
		Product  product = productDao.getProductById(id);
		log.info("getProductById : Product fetched for display  based on product Id"+product.getId());
		model.addAttribute("product", product);
		//attributes.addFlashAttribute("product", product);
		return "productDetailPage";
	}
	
	@RequestMapping(value="productDetailPage", method = RequestMethod.GET)
	public String showProductDetailPage(Model m){
		log.info("showProductDetailPage : Redirect to ProductDetailPage");
		return "productDetailPage";
	}
	
}
