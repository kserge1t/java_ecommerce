package com.github.serj86.java_ecommerce.client;

import com.github.serj86.java_ecommerce.dao.UserDAO;
import com.github.serj86.java_ecommerce.entities.User;
import com.github.serj86.java_ecommerce.util.PasswordHashing;
import com.github.serj86.java_ecommerce.util.PasswordHashing.CannotPerformOperationException;
import com.github.serj86.java_ecommerce.util.PasswordHashing.InvalidHashException;

public class test {

    public static void main(String[] args) {

	// Password Hashing check
	PasswordHashing hash = new PasswordHashing();
	String hashedPassword = null;
	boolean verified = false;
	try {
	    hashedPassword = hash.createHash("password");
	} catch (CannotPerformOperationException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	String hashing = "sha1:64000:18:8KOYLo0i3kjntGrXTLfNqlPJkvCdug2w:Y2+4EPp4VOYEdOmPY192wIDu"; // Hashing of "123"
	try {
	    //verified = hash.verifyPassword("password", hashedPassword);
	    verified = hash.verifyPassword("123", hashing);
	} catch (CannotPerformOperationException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (InvalidHashException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	System.out.println("New hash: "+hashedPassword);
	System.out.println("Hard coded hash: "+hashing);
	System.out.println("Validation: "+verified);
	
	
//	RoleDAO roleDao = new RoleDAO();
//	System.out.println(roleDao.getRoleById(User.defaultRoleId).getRole());
//	
	// User class check
	User user = new User();
	UserDAO userDao = new UserDAO();
	//user = userDao.getUserById(1);
	user = userDao.getUserByEmail("admin@admin.com");
	System.out.println(user.getFirstName());
	System.out.println(user.getLastName());
	System.out.println(user.getEmail());
	System.out.println(user.getBalance());
	System.out.println(user.getRole().getRole());
	System.out.println(user.getHashedPassword());
	
	// Set password check
//	try {
//	    user.setHashedPassword("123");
//	    System.out.println(user.getHashedPassword());
//	} catch (CannotPerformOperationException e) {
//	    // TODO Auto-generated catch block
//	    e.printStackTrace();
//	}
	
//	userDao.updateUser(user);
	
	// Validation method check
	try {
	    System.out.println(user.validate("123"));
	} catch (CannotPerformOperationException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (InvalidHashException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	
	// User authentication test
	String email = "admin@admin.com2";
	String password = "123";
	boolean authenticated = false;

	UserDAO userDao2 = new UserDAO();
	User user2 = userDao2.getUserByEmail(email);
	if (user2 != null) {
	    try {
		authenticated = user.validate(password);
	    } catch (CannotPerformOperationException e) {
		e.printStackTrace();
	    } catch (InvalidHashException e) {
		e.printStackTrace();
	    }
	}

	if (authenticated) {
	    System.out.println("<h2>Authenticated - Welcome " + user2.getFirstName() + " " + user2.getLastName() + "!</h2>");
	}


    }

}
