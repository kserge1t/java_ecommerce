package com.github.serj86.java_ecommerce.test.dao;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.serj86.java_ecommerce.dao.GenericDAO;
import com.github.serj86.java_ecommerce.dao.CategoryDAO;
import com.github.serj86.java_ecommerce.entities.Category;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCategoryDAO {
    static Category testCategoryVisible = new Category("Test Visible Category", true);
    static Category testCategoryInvisible = new Category("Test Invisible Category", false);

    @BeforeClass
    public static void setUp() {
	CategoryDAO categoryDao = new CategoryDAO();
	// Delete existing test categories, if any.
	if (categoryDao.getCategoryByName(testCategoryVisible.getCategory()) != null) {
	    categoryDao.deleteCategoryByName(testCategoryVisible.getCategory());
	}
	if (categoryDao.getCategoryByName(testCategoryInvisible.getCategory()) != null) {
	    categoryDao.deleteCategoryByName(testCategoryInvisible.getCategory());
	}
    }

    @Before
    public void preTestCheck() {
	CategoryDAO categoryDao = new CategoryDAO();
	GenericDAO genericDAO = new GenericDAO();
	if (categoryDao.getCategoryByName(testCategoryVisible.getCategory()) == null) {
	    genericDAO.add(testCategoryVisible);
	}
	if (categoryDao.getCategoryByName(testCategoryInvisible.getCategory()) == null) {
	    genericDAO.add(testCategoryInvisible);
	}
    }

    @Test
    public void testAddCategory() {
	CategoryDAO categoryDao = new CategoryDAO();
	categoryDao.deleteCategoryByName(testCategoryVisible.getCategory());
	categoryDao.deleteCategoryByName(testCategoryInvisible.getCategory());
	
	GenericDAO genericDAO = new GenericDAO();
	assertTrue(genericDAO.add(testCategoryVisible));
	assertTrue(genericDAO.add(testCategoryInvisible));
    }

    @Test
    public void testGetCategoryByName() {
	CategoryDAO categoryDao = new CategoryDAO();
	assertEquals(testCategoryVisible.getCategory(), categoryDao.getCategoryByName(testCategoryVisible.getCategory()).getCategory());
    }

    @Test
    public void testGetAllCategories() {
	CategoryDAO categoryDao = new CategoryDAO();
	List<Category> categories = categoryDao.getAllCategories();
	assertTrue(categories.size() > 0);
    }

    @Test
    public void testGetVisibleCategories() {
	CategoryDAO categoryDao = new CategoryDAO();
	List<Category> categories = categoryDao.getVisibleCategories();
	assertTrue(categories.size() > 0);
    }

    @Test
    public void testGetInvisibleCategories() {
	CategoryDAO categoryDao = new CategoryDAO();
	List<Category> categories = categoryDao.getInvisibleCategories();
	assertTrue(categories.size() > 0);
    }

    @Test
    public void testUpdateCategory() {
	GenericDAO genericDAO = new GenericDAO();
	CategoryDAO categoryDao = new CategoryDAO();
	Category category = categoryDao.getCategoryByName(testCategoryVisible.getCategory());
	category.setVisible(false);
	assertTrue(genericDAO.update(category));
	assertEquals(categoryDao.getCategoryByName(category.getCategory()).getVisible(),category.getVisible());
	assertTrue(categoryDao.deleteCategoryByName(category.getCategory()));
    }

    @Test
    public void testDeleteCategory() {
	GenericDAO genericDAO = new GenericDAO();
	CategoryDAO categoryDao = new CategoryDAO();
	Category category = categoryDao.getCategoryByName(testCategoryVisible.getCategory());
	assertTrue(genericDAO.delete(category));
    }

    @Test
    public void testDeleteCategoryById() {
	CategoryDAO categoryDao = new CategoryDAO();
	Integer id = categoryDao.getCategoryByName(testCategoryVisible.getCategory()).getId().intValue(); // int method calls for long method so both get tested
	assertTrue(categoryDao.deleteCategoryById(id));
    }

    @Test
    public void testDeleteCategoryByName() {
	CategoryDAO categoryDao = new CategoryDAO();
	assertTrue(categoryDao.deleteCategoryByName(testCategoryVisible.getCategory()));
    }

    @AfterClass
    public static void clean() {
	CategoryDAO categoryDao = new CategoryDAO();
	if (categoryDao.getCategoryByName(testCategoryVisible.getCategory()) != null) {
	    categoryDao.deleteCategoryByName(testCategoryVisible.getCategory());
	}
	if (categoryDao.getCategoryByName(testCategoryInvisible.getCategory()) != null) {
	    categoryDao.deleteCategoryByName(testCategoryInvisible.getCategory());
	}
    }

}
