package com.github.serj86.java_ecommerce.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.github.serj86.java_ecommerce.entities.CartItem;
import com.github.serj86.java_ecommerce.entities.User;

public class CartItemDAO {

    // Get all the products
    public List<CartItem> getAllProducts() {
	List<CartItem> products = new ArrayList<CartItem>();
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    products = session.createCriteria(CartItem.class).list();
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
    public CartItem getProductOrderedById (int productId) {
	return this.getProductOrderedById((long) productId);
    }

    // Get product by id (Long)
    public CartItem getProductOrderedById(Long productId) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    CartItem product = (CartItem) session.get(CartItem.class, productId);
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

    // Get products by sku
    public List<CartItem> getProductsBySku(String sku) {
	List<CartItem> products = new ArrayList<CartItem>();
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Criteria criteria = session.createCriteria(CartItem.class);
	    criteria.add(Restrictions.eq("sku", sku));
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

    // Get products by user
    public List<CartItem> getProductsByUser(User user) {
	List<CartItem> products = new ArrayList<CartItem>();
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Criteria criteria = session.createCriteria(CartItem.class);
	    criteria.add(Restrictions.eq("user", user));
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

    // Get products by filter
    public List<CartItem> getProductOrderedsByFilter(Integer minQuantity, Integer maxQuantity, Double minPrice, Double maxPrice, User user) {
	List<CartItem> products = new ArrayList<CartItem>();
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Criteria criteria = session.createCriteria(CartItem.class);
	    if (minQuantity != null) criteria.add(Restrictions.ge("quantity", minQuantity));
	    if (maxQuantity != null) criteria.add(Restrictions.le("quantity", maxQuantity));
	    if (minPrice != null) criteria.add(Restrictions.ge("price", minPrice));
	    if (maxPrice != null) criteria.add(Restrictions.le("price", maxPrice));
	    if (user != null) criteria.add(Restrictions.eq("user", user));
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
    
}
