package com.github.serj86.java_ecommerce.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.github.serj86.java_ecommerce.entities.Order;

public class OrderDAO {
    
    // Get all the orders
    public List<Order> getAllOrders() {
	List<Order> orders = new ArrayList<Order>();
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    orders = session.createCriteria(Order.class).list();
	    session.flush();
	    session.getTransaction().commit();
	    return orders;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Get all the orders for user id (Long)
    public List<Order> getOrdersForUserId(Long user_id) {
	List<Order> orders = new ArrayList<Order>();
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    orders = session.createCriteria(Order.class).add(Restrictions.eq("user_id", user_id)).list();
	    session.flush();
	    session.getTransaction().commit();
	    return orders;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Get all the orders for user id (Integer)
    public List<Order> getOrdersForUserId(Integer user_id) {
	return this.getOrdersForUserId((long) user_id);
    }
    
    // Get order by id (integer)
    public Order getOrderById (int orderId) {
	return this.getOrderById((long) orderId);
    }

    // Get order by id (Long)
    public Order getOrderById(Long orderId) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Order order = (Order) session.get(Order.class, orderId);
	    session.flush();
	    session.getTransaction().commit();
	    return order;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Delete order by id (integer)
    public boolean deleteOrderById (int orderId) {
	if (this.deleteOrderById((long) orderId)) return true;
	return false;
    }

    // Delete order by id (Long)
    public boolean deleteOrderById (Long orderId) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Order order = (Order) session.load(Order.class, orderId);
	    session.delete(order);
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
