package com.github.serj86.java_ecommerce.services;

import com.github.serj86.java_ecommerce.dao.UserDAO;
import com.github.serj86.java_ecommerce.entities.User;
import com.github.serj86.java_ecommerce.util.BCrypt;

public class UserValidationService {

    public boolean validateUserByEmail(String email, String password) {
	UserDAO udao = new UserDAO();
	User user = udao.getUserByEmail(email);
	if (user != null) {
	    return BCrypt.checkpw(password, user.getPasswordHash());
	}
	return false;
    }

    public User getValidatedUserByEmail(String email, String password) {
	UserDAO udao = new UserDAO();
	User user = udao.getUserByEmail(email);
	if (user != null && BCrypt.checkpw(password, user.getPasswordHash())) {
	    return user;
	}
	return null;
    }

    public boolean validateUserById(Long id, String password) {
	UserDAO udao = new UserDAO();
	User user = udao.getUserById(id);
	if (user != null) {
	    return BCrypt.checkpw(password, user.getPasswordHash());
	}
	return false;
    }

    public boolean validateUserById(Integer id, String password) {
	return this.validateUserById(id.longValue(), password);
    }

}
