package com.github.serj86.java_ecommerce.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.github.serj86.java_ecommerce.entities.Category;
import com.github.serj86.java_ecommerce.entities.Product;

public class ProductDAO {

    // Get all the products
    public List<Product> getAllProducts() {
	List<Product> products = new ArrayList<Product>();
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    products = session.createCriteria(Product.class).list();
	    session.flush();
	    session.getTransaction().commit();
	    return products;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Get active products
    public List<Product> getActiveProducts() {
	List<Product> products = new ArrayList<Product>();
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    products = session.createCriteria(Product.class).add(Restrictions.eq("active", true)).list();
	    session.flush();
	    session.getTransaction().commit();
	    return products;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Get inactive products
    public List<Product> getInactiveProducts() {
	List<Product> products = new ArrayList<Product>();
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    products = session.createCriteria(Product.class).add(Restrictions.eq("active", false)).list();
	    session.flush();
	    session.getTransaction().commit();
	    return products;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Get out-of-stock products
    public List<Product> getOutOfStockProducts() {
	List<Product> products = new ArrayList<Product>();
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    products = session.createCriteria(Product.class).add(Restrictions.le("stock", 0)).list();
	    session.flush();
	    session.getTransaction().commit();
	    return products;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Get in-stock products
    public List<Product> getInStockProducts() {
	List<Product> products = new ArrayList<Product>();
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    products = session.createCriteria(Product.class).add(Restrictions.ge("stock", 1)).list();
	    session.flush();
	    session.getTransaction().commit();
	    return products;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Get products by categories
    public List<Product> getProductsByCategories(Category category[]) {
	List<Product> products = new ArrayList<Product>();
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    products = session.createCriteria(Product.class).add(Restrictions.in("categories", category)).list();
	    session.flush();
	    session.getTransaction().commit();
	    return products;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Get product by id (integer)
    public Product getProductById (int productId) {
	return this.getProductById((long) productId);
    }

    // Get product by id (Long)
    public Product getProductById(Long productId) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Product product = (Product) session.get(Product.class, productId);
	    session.flush();
	    session.getTransaction().commit();
	    return product;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }

    // Get product by sku
    public Product getProductBySku(String sku) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Product product = (Product) session.createCriteria(Product.class).add(Restrictions.eq("sku", sku)).uniqueResult();
	    session.flush();
	    session.getTransaction().commit();
	    return product;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }

    // Get products by filter
    public List<Product> getProductsByFilter(Boolean active, Integer minStock, Integer maxStock, Double minPrice, Double maxPrice, Category category) {
	List<Product> products = new ArrayList<Product>();
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Criteria criteria = session.createCriteria(Product.class);
	    if (active != null) criteria.add(Restrictions.eq("active", active));
	    if (minStock != null) criteria.add(Restrictions.ge("stock", minStock));
	    if (maxStock != null) criteria.add(Restrictions.le("stock", maxStock));
	    if (minPrice != null) criteria.add(Restrictions.ge("price", minPrice));
	    if (maxPrice != null) criteria.add(Restrictions.le("price", maxPrice));
	    if (category != null) {
		List<Category> categories = new ArrayList<Category>();
		categories.add(category);
		criteria.add(Restrictions.in("categories", categories));
	    }
	    products = criteria.list();
	    session.flush();
	    session.getTransaction().commit();
	    return products;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }

    // Delete product by sku
    public boolean deleteProductBySku(String sku) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Product product = (Product) session.createCriteria(Product.class).add(Restrictions.eq("sku", sku)).uniqueResult();
	    session.delete(product);
	    session.flush();
	    session.getTransaction().commit();
	    return true;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return false;
    }
    
    // Delete product by id (integer)
    public boolean deleteProductById (int productId) {
	if (this.deleteProductById((long) productId)) return true;
	return false;
    }

    // Delete product by id (Long)
    public boolean deleteProductById (Long productId) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Product product = (Product) session.load(Product.class, new Long(productId));
	    session.delete(product);
	    session.flush();
	    session.getTransaction().commit();
	    return true;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return false;
    }
    
}
