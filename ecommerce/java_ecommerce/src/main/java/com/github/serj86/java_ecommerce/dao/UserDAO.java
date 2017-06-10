package com.github.serj86.java_ecommerce.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.github.serj86.java_ecommerce.entities.Product;
import com.github.serj86.java_ecommerce.entities.Role;
import com.github.serj86.java_ecommerce.entities.User;

public class UserDAO {

    // Get all the users
    public List<User> getAllUsers() {
	List<User> users = new ArrayList<User>();
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    users = session.createCriteria(User.class).list();
	    session.flush();
	    session.getTransaction().commit();
	    return users;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Get users by role
    public List<User> getUsersByRole(Role role) {
	List<User> users = new ArrayList<User>();
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    users = session.createCriteria(User.class).add(Restrictions.eq("role", role)).list();
	    session.flush();
	    session.getTransaction().commit();
	    return users;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Get user by id (integer)
    public User getUserById (int userId) {
	return this.getUserById((long) userId);
    }

    // Get user by id (Long)
    public User getUserById(Long userId) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    User user = (User) session.get(User.class, userId);
	    session.flush();
	    session.getTransaction().commit();
	    return user;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }

    // Get user by email
    public User getUserByEmail(String email) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    User user = (User) session.createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
	    session.flush();
	    session.getTransaction().commit();
	    return user;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }

    // Delete user by email
    public boolean deleteUserByEmail(String email) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    User user = (User) session.createCriteria(User.class).add(Restrictions.eq("email", email)).uniqueResult();
	    session.delete(user);
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
    
    // Delete user by id (integer)
    public boolean deleteUserById (int userId) {
	if (this.deleteUserById((long) userId)) return true;
	return false;
    }

    // Delete user by id (Long)
    public boolean deleteUserById (Long userId) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    User user = (User) session.load(User.class, new Long(userId));
	    session.delete(user);
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
