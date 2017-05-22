package com.github.serj86.java_ecommerce.test.dao;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.serj86.java_ecommerce.dao.RoleDAO;
import com.github.serj86.java_ecommerce.dao.SettingDAO;
import com.github.serj86.java_ecommerce.dao.UserDAO;
import com.github.serj86.java_ecommerce.entities.Role;
import com.github.serj86.java_ecommerce.entities.User;
import com.github.serj86.java_ecommerce.util.PasswordHashing.CannotPerformOperationException;
import com.github.serj86.java_ecommerce.util.PasswordHashing.InvalidHashException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestUserDAO {
    public static String testEmail1 = "test@testing.com";
    public static String testEmail2 = "testing@test.com";
    
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
		User user = new User();
		
		user.setFirstName(testEmail1);
		user.setLastName("Tset");
		user.setEmail(testEmail1);
		user.setBalance(100.00);
		user.setRoleObject(roleDao.getRoleByValue(new SettingDAO().getSettingValueByName("user_default_role")));
		user.setHashedPassword("Secret");
	}
    }

    
//    @Test
//    public void testAddUser() {
//	UserDAO userDao = new UserDAO();
//	userDao.deleteUserByEmail(testEmail1);
//	User user = new User();
//	user.setUser(testEmail1);
//	assertTrue(userDao.addUser(user));
//    }

    @Test //OK
    public void testGetUserById() {
	UserDAO userDao = new UserDAO();
	Integer id = userDao.getUserByEmail(testEmail1).getId().intValue();  // int method calls for long method so both get tested
	assertEquals(testEmail1, userDao.getUserById(id).getEmail());
    }

//    @Test
//    public void testGetUserIdByEmail() {
//	UserDAO userDao = new UserDAO();
//	assertEquals(Long.class, userDao.getUserIdByEmail(testEmail1).getClass());
//    }

    @Test //OK
    public void testGetUserByEmail() {
	UserDAO userDao = new UserDAO();
	assertEquals(testEmail1, userDao.getUserByEmail(testEmail1).getEmail());
    }

    @Test // OK
    public void testGetAllUsers() {
	UserDAO userDao = new UserDAO();
	List<User> users = userDao.getAllUsers();
	assertTrue(users.size()>0);
    }

    @Test // OK
    public void testUpdateUser() {
	// Values to update:
	String firstName = "Testing";
	String lasttName = "Gnitset";
	String email = testEmail2;
	Double balance = 0.00;
	String password = "Password";
	
	UserDAO userDao = new UserDAO();
	User user = userDao.getUserByEmail(testEmail1);
	user.setFirstName(firstName);
	user.setLastName(lasttName);
	user.setEmail(email);
	user.setBalance(balance);
	user.setHashedPassword(password);
	
	// Delete existing testing user, if any
	if (userDao.getUserByEmail(email) != null) {
	    userDao.deleteUserByEmail(email);
	}
	
	assertTrue(userDao.updateUser(user));
	assertEquals(firstName, userDao.getUserByEmail(email).getFirstName());
	assertEquals(lasttName, userDao.getUserByEmail(email).getLastName());
	assertEquals(email, userDao.getUserByEmail(email).getEmail());
	assertEquals(balance, userDao.getUserByEmail(email).getBalance());
	assertTrue(userDao.getUserByEmail(email).validate(password));
	assertTrue(userDao.deleteUserByEmail(email));
    }

    @Test // OK
    public void testDeleteUser() {
	UserDAO userDao = new UserDAO();
	User user = userDao.getUserByEmail(testEmail1);
	assertTrue(userDao.deleteUser(user));
    }

//    @Test // 
//    public void testDeleteUserById() {
//	UserDAO userDao = new UserDAO();
//	Integer id = userDao.getUserIdByValue(testEmail1).intValue();  // int method calls for long method so both get tested
//	assertTrue(userDao.deleteUserById(id));
//    }

    @Test // OK
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
