package com.github.serj86.java_ecommerce.test.dao;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.serj86.java_ecommerce.dao.GenericDAO;
import com.github.serj86.java_ecommerce.dao.RoleDAO;
import com.github.serj86.java_ecommerce.dao.SettingDAO;
import com.github.serj86.java_ecommerce.dao.UserDAO;
import com.github.serj86.java_ecommerce.entities.User;
import com.github.serj86.java_ecommerce.services.UserValidationService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestUserDAO {
    public static final String testEmail1 = "test@testing.com";
    public static final String testEmail2 = "testing@test.com";
    
    @BeforeClass
    public static void setUp() {
	UserDAO userDao = new UserDAO();
	// Delete existing test users, if any
	if (userDao.getUserByEmail(testEmail1) != null) {
	    userDao.deleteUserByEmail(testEmail1);
	}
	if (userDao.getUserByEmail(testEmail2) != null) {
	    userDao.deleteUserByEmail(testEmail2);
	}
    }

    @Before
    public void preTestCheck() {
	UserDAO userDao = new UserDAO();
	
	if (userDao.getUserByEmail(testEmail1) == null) {
		RoleDAO roleDao = new RoleDAO();
		GenericDAO gDao = new GenericDAO();
		User user = new User();
		
		user.setFirstName("Test");
		user.setLastName("Tset");
		user.setEmail(testEmail1);
		user.setBalance(100.00);
		user.setRole(roleDao.getRoleById(Integer.parseInt(new SettingDAO().getSettingValueByName("user_default_role_id"))));
		user.setPasswordHash("Secret");
		
		gDao.add(user);
	}
	
	if (userDao.getUserByEmail(testEmail2) != null) {
	    userDao.deleteUserByEmail(testEmail2);
	}
    }

    @Test
    public void testGetUserById() {
	UserDAO userDao = new UserDAO();
	Integer id = userDao.getUserByEmail(testEmail1).getId().intValue();  // int method calls for long method so both get tested
	assertEquals(testEmail1, userDao.getUserById(id).getEmail());
    }

    @Test
    public void testGetUserByEmail() {
	UserDAO userDao = new UserDAO();
	assertEquals(testEmail1, userDao.getUserByEmail(testEmail1).getEmail());
    }

    @Test
    public void testGetAllUsers() {
	UserDAO userDao = new UserDAO();
	List<User> users = userDao.getAllUsers();
	assertTrue(users.size()>0);
    }

    @Test
    public void testGetUsersByRole() {
	UserDAO userDao = new UserDAO();
	RoleDAO roleDao = new RoleDAO();
	List<User> users = userDao.getUsersByRole(roleDao.getRoleById(Integer.parseInt(new SettingDAO().getSettingValueByName("user_default_role_id"))));
	assertTrue(users.size()>0);
    }

    @Test
    public void testUpdateUser() {
	// Values to update:
	String firstName = "Testing";
	String lasttName = "Gnitset";
	String email = testEmail2;
	Double balance = 0.00;
	String password = "Password";
	
	UserValidationService uVal = new UserValidationService();
	GenericDAO gDao = new GenericDAO();
	UserDAO uDao = new UserDAO();
	User user = uDao.getUserByEmail(testEmail1);
	user.setFirstName(firstName);
	user.setLastName(lasttName);
	user.setEmail(email);
	user.setBalance(balance);
	user.setPasswordHash(password);
	
	// Delete existing testing user, if any
	if (uDao.getUserByEmail(email) != null) {
	    uDao.deleteUserByEmail(email);
	}
	
	assertTrue(gDao.update(user));
	assertEquals(firstName, uDao.getUserByEmail(email).getFirstName());
	assertEquals(lasttName, uDao.getUserByEmail(email).getLastName());
	assertEquals(email, uDao.getUserByEmail(email).getEmail());
	assertEquals(balance, uDao.getUserByEmail(email).getBalance());
	assertTrue(uVal.validateUserByEmail(email, password));
	assertTrue(uDao.deleteUserByEmail(email));
    }

    @Test
    public void testDeleteUser() {
	UserDAO uDao = new UserDAO();
	GenericDAO gDao = new GenericDAO();
	User user = uDao.getUserByEmail(testEmail1);
	assertTrue(gDao.delete(user));
    }

    @Test
    public void testDeleteUserById() {
	UserDAO userDao = new UserDAO();
	Integer id = userDao.getUserByEmail(testEmail1).getId().intValue();  // int method calls for long method so both get tested
	assertTrue(userDao.deleteUserById(id));
    }

    @Test
    public void testDeleteUserByEmail() {
	UserDAO userDao = new UserDAO();
	assertTrue(userDao.deleteUserByEmail(testEmail1));
    }
    
    
    @AfterClass
    public static void clean() {
	UserDAO userDao = new UserDAO();
	if (userDao.getUserByEmail(testEmail1) != null) {
	    userDao.deleteUserByEmail(testEmail1);
	}
	if (userDao.getUserByEmail(testEmail2) != null) {
	    userDao.deleteUserByEmail(testEmail2);
	}
    }

}
