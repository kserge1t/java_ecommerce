package com.github.serj86.java_ecommerce.dao;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.github.serj86.java_ecommerce.entities.Category;

public class CategoryDAO {

    // Get all the categories
    public List<Category> getAllCategories() {
	List<Category> categories = new ArrayList<Category>();
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    categories = session.createCriteria(Category.class).list();
	    session.flush();
	    session.getTransaction().commit();
	    return categories;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Get Visible categories
    public List<Category> getVisibleCategories() {
	List<Category> categories = new ArrayList<Category>();
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    categories = session.createCriteria(Category.class).add(Restrictions.eq("visible", true)).list();
	    session.flush();
	    session.getTransaction().commit();
	    return categories;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Get Invisible categories
    public List<Category> getInvisibleCategories() {
	List<Category> categories = new ArrayList<Category>();
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    categories = session.createCriteria(Category.class).add(Restrictions.eq("visible", false)).list();
	    session.flush();
	    session.getTransaction().commit();
	    return categories;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Get category by id (integer)
    public Category getCategoryById (int categoryId) {
	return this.getCategoryById((long) categoryId);
    }

    // Get category by id (Long)
    public Category getCategoryById(Long categoryId) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Category category = (Category) session.get(Category.class, categoryId);
	    session.flush();
	    session.getTransaction().commit();
	    return category;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Get category by name
    public Category getCategoryByName(String name) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Category category = (Category) session.createCriteria(Category.class).add(Restrictions.eq("category", name)).uniqueResult();
	    session.flush();
	    session.getTransaction().commit();
	    return category;
	} catch (RuntimeException e) {
	    session.getTransaction().rollback();
	    e.printStackTrace();
	} finally {
	    session.close();
	}
	return null;
    }
    
    // Delete category by id (integer)
    public boolean deleteCategoryById (int categoryId) {
	if (this.deleteCategoryById((long) categoryId)) return true;
	return false;
    }

    // Delete category by id (Long)
    public boolean deleteCategoryById (Long categoryId) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Category category = (Category) session.load(Category.class, categoryId);
	    session.delete(category);
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

    // Delete category by name
    public boolean deleteCategoryByName(String name) {
	Session session = HibernateUtil.getSessionFactory().openSession();
	try {
	    session.beginTransaction();
	    Category category = (Category) session.createCriteria(Category.class).add(Restrictions.eq("category", name)).uniqueResult();
	    session.delete(category);
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
