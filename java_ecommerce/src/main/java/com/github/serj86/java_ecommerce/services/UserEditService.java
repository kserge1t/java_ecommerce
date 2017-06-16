package com.github.serj86.java_ecommerce.services;

import com.github.serj86.java_ecommerce.dao.GenericDAO;
import com.github.serj86.java_ecommerce.dao.UserDAO;
import com.github.serj86.java_ecommerce.entities.User;

public class UserEditService {

    public User updateUser(User updatedUser) {

	User originalUser;
	try {
	    originalUser = new UserDAO().getUserById(updatedUser.getId());
	} catch (Exception e) {
	    System.out.println("Error: Could not get original user by id");
	    return null;
	}

	if (updatedUser.getEmail() == null || updatedUser.getEmail().isEmpty()) {
	    updatedUser.setEmail(originalUser.getEmail());
	}

	if (updatedUser.getFirstName() == null || updatedUser.getFirstName().isEmpty()) {
	    updatedUser.setFirstName(originalUser.getFirstName());
	}

	if (updatedUser.getLastName() == null || updatedUser.getLastName().isEmpty()) {
	    updatedUser.setLastName(originalUser.getLastName());
	}

	// Admin users only
	if (updatedUser.getBalance() == null || originalUser.getRole().getId() != 1) {
	    updatedUser.setBalance(originalUser.getBalance());
	}

	// Admin users only
	if (updatedUser.getRole() == null || originalUser.getRole().getId() != 1) {
	    updatedUser.setRole(originalUser.getRole());
	}

	if ((updatedUser.getPasswordHash() == null) || (updatedUser.getPasswordHash().isEmpty())) {
	    updatedUser.setPreHashedPassword(originalUser.getPasswordHash());
	}

	if (new GenericDAO().update(updatedUser)) {
	    return updatedUser;
	} else {
	    return null;
	}
    }

}
