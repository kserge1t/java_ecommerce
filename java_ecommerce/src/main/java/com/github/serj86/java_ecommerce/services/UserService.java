package com.github.serj86.java_ecommerce.services;

import com.github.serj86.java_ecommerce.dao.GenericDAO;
import com.github.serj86.java_ecommerce.dao.UserDAO;
import com.github.serj86.java_ecommerce.entities.User;
import com.github.serj86.java_ecommerce.util.BCrypt;

public class UserService {
    
    private String message = null;

    /* Order Message Methods */
    public String getMessage() {
	String msg = message;
	this.message = null;
	return msg;
    }
    public void setMessage(String message) {
	this.message = message;
    }
    
    /* User Service Methods */
    
    public User registerUser(User user) {
	if (new UserDAO().getUserByEmail(user.getEmail()) == null) {
	    if (new GenericDAO().add(user)) {
		this.setMessage("You have been registered as: \""+user.getEmail()+"\"");
		return user;
	    } else {
		this.setMessage("Registration of \""+user.getEmail()+"\" failed.");
		return null;
	    }
	} else {
	    this.setMessage("Email \""+user.getEmail()+"\" already registered..");
	    return null;
	}
    }
    
    public boolean validateUserByEmail(String email, String password) {
	UserDAO uDao = new UserDAO();
	User user = uDao.getUserByEmail(email);
	if (user != null) {
	    this.setMessage("You have been authenticated as \""+user.getEmail()+"\"");
	    return BCrypt.checkpw(password, user.getPasswordHash());
	}
	this.setMessage("Authentication for \""+email+"\" failed.");
	return false;
    }

    public User getValidatedUserByEmail(String email, String password) {
	UserDAO uDao = new UserDAO();
	User user = uDao.getUserByEmail(email);
	if (user != null && BCrypt.checkpw(password, user.getPasswordHash())) {
	    this.setMessage("You have been authenticated as \""+user.getEmail()+"\"");
	    return user;
	}
	this.setMessage("Authentication for \""+email+"\" failed.");
	return null;
    }

    public boolean validateUserById(Long id, String password) {
	UserDAO uDao = new UserDAO();
	User user = uDao.getUserById(id);
	if (user != null) {
	    this.setMessage("You have been authenticated");
	    return BCrypt.checkpw(password, user.getPasswordHash());
	}
	this.setMessage("Authentication failed.");
	return false;
    }

    public boolean validateUserById(Integer id, String password) {
	return this.validateUserById(id.longValue(), password);
    }

    public User updateUser (User updatedUser) {

	User originalUser;
	try {
	    originalUser = new UserDAO().getUserById(updatedUser.getId());
	} catch (Exception e) {
	    System.out.println("Error: Could not get original user by id");
	    return null;
	}
	
	StringBuffer msg = new StringBuffer();
	msg.append("Updated: ");

	if (updatedUser.getEmail() == null || updatedUser.getEmail().isEmpty()) {
	    updatedUser.setEmail(originalUser.getEmail());
	    msg.append("Email ");
	}

	if (updatedUser.getFirstName() == null || updatedUser.getFirstName().isEmpty()) {
	    updatedUser.setFirstName(originalUser.getFirstName());
	    msg.append("First-Name ");
	}

	if (updatedUser.getLastName() == null || updatedUser.getLastName().isEmpty()) {
	    updatedUser.setLastName(originalUser.getLastName());
	    msg.append("Last-Name ");
	}

	// Admin users only
	if (updatedUser.getBalance() == null || originalUser.getRole().getId() != 1) {
	    updatedUser.setBalance(originalUser.getBalance());
	    msg.append("Balance ");
	}

	// Admin users only
	if (updatedUser.getRole() == null || originalUser.getRole().getId() != 1) {
	    updatedUser.setRole(originalUser.getRole());
	    msg.append("Role ");
	}

	if ((updatedUser.getPasswordHash() == null) || (updatedUser.getPasswordHash().isEmpty())) {
	    updatedUser.setPreHashedPassword(originalUser.getPasswordHash());
	    msg.append("Password ");
	}

	if (new GenericDAO().update(updatedUser)) {
	    this.setMessage(msg.toString());
	    return updatedUser;
	} else {
	    this.setMessage("User edition failed.");
	    return null;
	}
    }

}
