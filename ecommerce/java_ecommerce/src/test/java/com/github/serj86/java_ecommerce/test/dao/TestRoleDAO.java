package com.github.serj86.java_ecommerce.test.dao;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.serj86.java_ecommerce.dao.GenericDAO;
import com.github.serj86.java_ecommerce.dao.RoleDAO;
import com.github.serj86.java_ecommerce.entities.Role;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestRoleDAO {
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
    public void testAddRole() {
	RoleDAO roleDao = new RoleDAO();
	roleDao.deleteRoleByValue(testRole1);
	Role role = new Role();
	role.setRole(testRole1);
	
	GenericDAO genericDAO = new GenericDAO();
	assertTrue(genericDAO.add(role));
    }

    @Test
    public void testGetRoleById() {
	RoleDAO roleDao = new RoleDAO();
	Integer id = roleDao.getRoleIdByValue(testRole1).intValue();  // int method calls for long method so both get tested
	assertEquals(testRole1, roleDao.getRoleById(id).getRole());
    }

    @Test
    public void testGetRoleIdByValue() {
	RoleDAO roleDao = new RoleDAO();
	assertEquals(roleDao.getRoleByValue(testRole1).getId(), roleDao.getRoleIdByValue(testRole1));
    }

    @Test
    public void testGetRoleByValue() {
	RoleDAO roleDao = new RoleDAO();
	assertEquals(testRole1, roleDao.getRoleByValue(testRole1).getRole());
    }

    @Test
    public void testGetAllRoles() {
	RoleDAO roleDao = new RoleDAO();
	List<Role> roles = roleDao.getAllRoles();
	assertTrue(roles.size()>0);
    }

    @Test
    public void testUpdateRole() {
	GenericDAO genericDAO = new GenericDAO();
	RoleDAO roleDao = new RoleDAO();
	Role role = roleDao.getRoleByValue(testRole1);
	role.setRole(testRole2);
	assertTrue(genericDAO.update(role));
	assertTrue(roleDao.deleteRoleByValue(testRole2));
    }

    @Test
    public void testDeleteRole() {
	RoleDAO roleDao = new RoleDAO();
	Role role = roleDao.getRoleByValue(testRole1);
	
	GenericDAO genericDAO = new GenericDAO();
	assertTrue(genericDAO.delete(role));
    }

    @Test
    public void testDeleteRoleById() {
	RoleDAO roleDao = new RoleDAO();
	Integer id = roleDao.getRoleIdByValue(testRole1).intValue();  // int method calls for long method so both get tested
	assertTrue(roleDao.deleteRoleById(id));
    }

    @Test
    public void testDeleteRoleByValue() {
	RoleDAO roleDao = new RoleDAO();
	assertTrue(roleDao.deleteRoleByValue(testRole1));
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
