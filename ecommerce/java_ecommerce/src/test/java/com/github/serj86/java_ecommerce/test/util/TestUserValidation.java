package com.github.serj86.java_ecommerce.test.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.serj86.java_ecommerce.dao.GenericDAO;
import com.github.serj86.java_ecommerce.dao.RoleDAO;
import com.github.serj86.java_ecommerce.dao.SettingDAO;
import com.github.serj86.java_ecommerce.dao.UserDAO;
import com.github.serj86.java_ecommerce.entities.User;
import com.github.serj86.java_ecommerce.services.UserValidationService;
import com.github.serj86.java_ecommerce.util.BCrypt;

public class TestUserValidation {
    public static String testEmail = "test@testing.com";
    public static String testPassword = "Secret123";

    @BeforeClass
    public static void setUp() {
	RoleDAO roleDao = new RoleDAO();
	GenericDAO gDao = new GenericDAO();
	UserDAO uDao = new UserDAO();
	User user = new User();
	
	
	// Delete existing test users, if any
	if (uDao.getUserByEmail(testEmail) != null) {
	    uDao.deleteUserByEmail(testEmail);
	    System.out.println("Found and deleted exisitng test user.");
	}
	
	// Create test user
	user.setFirstName(testEmail);
	user.setLastName("Tset");
	user.setEmail(testEmail);
	user.setBalance(Double.parseDouble(new SettingDAO().getSettingValueByName("user_starting_balance")));
	user.setRoleObject(roleDao.getRoleById(Long.getLong(new SettingDAO().getSettingValueByName("user_default_role_id"))));
	user.setPasswordHash(testPassword);
	gDao.add(user);
	System.out.println("New test user has been created.");
    }

    @Test
    public void testBCrypt() {
	String hashedPassword = BCrypt.hashpw(testPassword, BCrypt.gensalt());
	assertTrue(BCrypt.checkpw(testPassword, hashedPassword));
    }

    @Test
    public void testValidateUserByEmail() {
	UserValidationService uVal = new UserValidationService();
	assertTrue(uVal.validateUserByEmail(testEmail, testPassword));
    }

    @Test
    public void testGetValidatedUserByEmail() {
	UserDAO userDao = new UserDAO();
	UserValidationService uVal = new UserValidationService();
	assertEquals(userDao.getUserByEmail(testEmail).getId(), uVal.getValidatedUserByEmail(testEmail, testPassword).getId());
    }

    @Test
    public void testValidateUserById() {
	UserDAO userDao = new UserDAO();
	UserValidationService uVal = new UserValidationService();
	assertTrue(uVal.validateUserById(userDao.getUserByEmail(testEmail).getId(), testPassword));
    }

    @AfterClass
    public static void clean() {
	UserDAO userDao = new UserDAO();
	
	// Delete test users, if exists
	if (userDao.getUserByEmail(testEmail) != null) {
	    userDao.deleteUserByEmail(testEmail);
	    System.out.println("Test user deleted.");
	}
    }

}
