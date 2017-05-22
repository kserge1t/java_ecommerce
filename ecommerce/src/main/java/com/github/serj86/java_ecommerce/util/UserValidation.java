package com.github.serj86.java_ecommerce.util;

import com.github.serj86.java_ecommerce.dao.UserDAO;
import com.github.serj86.java_ecommerce.entities.User;

public class UserValidation {

    public boolean validateUserByEmail(String email, String password) {
	UserDAO udao = new UserDAO();
	User user = udao.getUserByEmail(email);
	if (user != null) {
	    return BCrypt.checkpw(password, user.getHashedPassword());
	}
	return false;
    }

    public User getValidatedUserByEmail(String email, String password) {
	UserDAO udao = new UserDAO();
	User user = udao.getUserByEmail(email);
	if (user != null && BCrypt.checkpw(password, user.getHashedPassword())) {
	    return user;
	}
	return null;
    }

    public boolean validateUserById(Long id, String password) {
	UserDAO udao = new UserDAO();
	User user = udao.getUserById(id);
	if (user != null) {
	    return BCrypt.checkpw(password, user.getHashedPassword());
	}
	return false;
    }

    public boolean validateUserById(Integer id, String password) {
	return this.validateUserById(id.longValue(), password);
    }

}
