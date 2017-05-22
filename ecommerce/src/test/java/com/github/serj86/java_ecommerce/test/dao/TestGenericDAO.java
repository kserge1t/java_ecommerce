package com.github.serj86.java_ecommerce.test.dao;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.serj86.java_ecommerce.dao.GenericDAO;
import com.github.serj86.java_ecommerce.dao.RoleDAO;
import com.github.serj86.java_ecommerce.entities.Role;

import static org.junit.Assert.assertTrue;

public class TestGenericDAO {
    public static String testRole1 = "Test";
    public static String testRole2 = "Testing";
    
    @BeforeClass
    public static void setUp() {
	RoleDAO roleDao = new RoleDAO();
	// Delete existing test role, if any.
	if (roleDao.getRoleByValue(testRole1) != null) {
	    roleDao.deleteRoleByValue(testRole1);
	}
	if (roleDao.getRoleByValue(testRole2) != null) {
	    roleDao.deleteRoleByValue(testRole2);
	}
    }

    @Before
    public void preTestCheck() {
	RoleDAO roleDao = new RoleDAO();
	GenericDAO genericDAO = new GenericDAO();
	if (roleDao.getRoleByValue(testRole1) == null) {
	    Role role = new Role();
	    role.setRole(testRole1);
	    genericDAO.add(role);
	}
    }

    
    @Test
    public void testAdd() {
	RoleDAO roleDao = new RoleDAO();
	roleDao.deleteRoleByValue(testRole1);
	Role role = new Role();
	role.setRole(testRole1);
	
	GenericDAO genericDAO = new GenericDAO();
	assertTrue(genericDAO.add(role));
    }

    @Test
    public void testUpdate() {
	GenericDAO genericDAO = new GenericDAO();
	RoleDAO roleDao = new RoleDAO();
	Role role = roleDao.getRoleByValue(testRole1);
	role.setRole(testRole2);
	assertTrue(genericDAO.update(role));
	assertTrue(roleDao.deleteRoleByValue(testRole2));
    }

    @Test
    public void testDelete() {
	RoleDAO roleDao = new RoleDAO();
	Role role = roleDao.getRoleByValue(testRole1);
	
	GenericDAO genericDAO = new GenericDAO();
	assertTrue(genericDAO.delete(role));
    }
    
    @AfterClass
    public static void clean() {
	RoleDAO roleDao = new RoleDAO();
	if (roleDao.getRoleByValue(testRole1) != null) {
	    roleDao.deleteRoleByValue(testRole1);
	}
	if (roleDao.getRoleByValue(testRole2) != null) {
	    roleDao.deleteRoleByValue(testRole2);
	}
    }

}
