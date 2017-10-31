package com.yourstyle.dao;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
/*import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;*/
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yourstyle.model.Address;
import com.yourstyle.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	private static Logger log = LoggerFactory.getLogger(ProductDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public ProductDaoImpl(SessionFactory sessionFactory) {
		
		log.info("ProductDaoImpl : getSessionFactory");
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public boolean saveOrUpdate(Product product) {
		
		log.info("ProductDaoImpl : save or update Product detail");
		sessionFactory.getCurrentSession().saveOrUpdate(product);
		log.info("ProductDaoImpl : save or update Product detail Successful");
		return true;
	}

	

	@Transactional
	public boolean deleteProductById(int productId) {
		log.info("ProductDaoImpl : get Sessionfactory");
		Session session = sessionFactory.getCurrentSession();
		log.info("ProductDaoImpl : load Product by Id --"+productId);
		Object peristanceInstance = session.load(Product.class, productId);
		log.info("ProductDaoImpl :  delete Product by Id --"+productId);
		if(peristanceInstance != null){
			session.delete(peristanceInstance);
			System.out.println("Product deleted successfully");
			log.info("ProductDaoImpl : Product deleted Successfully with product id --"+productId);
		}
		
		return true;
	}

	@Transactional
	public List<Product> getAllProducts() {
		log.info("ProductDaoImpl : get All Products details");
		List<Product> products = sessionFactory.getCurrentSession().createQuery("from Product",Product.class).list();
		return products;
	}

	@Transactional
	public Product getProductById(int productId) {
		log.info("ProductDaoImpl : get Products details by Id -- "+productId);
		Product product = (Product) sessionFactory.getCurrentSession().createQuery("select p from Product p where id = :productId")
				.setParameter("productId", productId).uniqueResult();
		System.out.println(product.toString());
		log.info("ProductDaoImpl : Successful retrieval of Products details  -- "+product.toString());
		
		return product;		
	}

	@Override
	public List<Product> getAllProductForCategory(int catId) {
		log.info("ProductDaoImpl : get all Products under given Category Id -- "+catId);
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List<Product> products = session.createQuery("from Product where categoryId = :catId",Product.class).setParameter("catId", catId).list();
		session.getTransaction().commit();
		session.close();
		return products;
	}

	@Transactional
	public List<Product> getProductByBrand(String brandName) {
		String queryString = "from Product where lower(brandName) = :brandName";
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Product> products = session.createQuery(queryString,Product.class).setParameter("brandName", brandName.toLowerCase()).list();
		session.getTransaction().commit();
		session.close();
		
		return products;
	}

	@Transactional
	public List<Product> getProductBySearchText(String[] searchString) {
		
		String queryString = "from Product where (lower(productName) like '%"+searchString[0].toLowerCase()+"%' or lower(productDesc) like '%"+searchString[0].toLowerCase()+"%')";
	   
		for(int i=1;i<searchString.length;i++){
			
			queryString += " or (lower(productName) like '%"+searchString[i].toLowerCase()+"%' or lower(productDesc) like '%"+searchString[i].toLowerCase()+"%')";
		}
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Product> products = session.createQuery(queryString,Product.class).list();
		session.getTransaction().commit();
		session.close();
		
		return products;
		
		/*Session session = sessionFactory.getCurrentSession();
		log.info("ProductDaoImpl : search Products by search criteria --");
		CriteriaBuilder cb =  session.getCriteriaBuilder();
		CriteriaQuery<Product> cq = cb.createQuery(Product.class);
		
		
		Root<Product> addressRoot  = cq.from(Product.class);
		ParameterExpression<Integer> personId = cb.parameter(Integer.class);
		
		Expression<String> path = addressRoot.get("productName");
		Expression<String> param = cb.parameter(String.class);
		
		Expression<String> path1 = addressRoot.get("productDesc");
		Expression<String> param1 = cb.parameter(String.class);
		
		Predicate p = cb.or(cb.like(cb.lower(path), "%"+searchString[0]+"%"),cb.like(cb.lower(path1), "%"+searchString[0]+"%"));
		Predicate p1[] = new Predicate[10];
		Predicate pFinal;
		for(int i=1; i<searchString.length; i++){
			p1[i] = cb.or(p,cb.or(cb.like(cb.lower(path), "%"+searchString[i]+"%"),cb.like(cb.lower(path1), "%"+searchString[i]+"%")));
			pFinal=cb.or()
		}
		if(searchString.length==1){
			cq.select(addressRoot).where(p);
		}else if(searchString.length > 1){
			cq.select(addressRoot).where(p1);
		}
		
		
		Query query = session.createQuery(cq);
		
		//SuppressWarnings(value="unchecked")
		List<Product> results = (List<Product>) query.getResultList();
		if(results.iterator().hasNext()){
		
			System.out.println("value retrieved");
		}
		return results;*/
	
		
	}

	@Override
	public List<Product> getAllProductsByName(String searchString) {
		
		String searchArray[] = searchString.split(" ");
		String queryString = "from Product";
		if(searchArray.length == 0){
		
			queryString += " where (lower(productName) like '%"+searchString.toLowerCase()+"%' or lower(productDesc) like '%"+searchString.toLowerCase()+"%')";
		}else{
			
			queryString += " where (lower(productName) like '%"+searchArray[0].toLowerCase()+"%' or lower(productDesc) like '%"+searchArray[0].toLowerCase()+"%')";
			for(int i=1;i<searchArray.length;i++){
				
				queryString += " or (lower(productName) like '%"+searchArray[i].toLowerCase()+"%' or lower(productDesc) like '%"+searchArray[i].toLowerCase()+"%')";
			}
		}
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		List<Product> products = session.createQuery(queryString,Product.class).list();
		session.getTransaction().commit();
		session.close();
		
		return products;
	}	
	
}
