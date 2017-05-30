package com.github.serj86.java_ecommerce.services;

import com.github.serj86.java_ecommerce.dao.GenericDAO;
import com.github.serj86.java_ecommerce.dao.UserDAO;
import com.github.serj86.java_ecommerce.dto.UserDTO;
import com.github.serj86.java_ecommerce.entities.User;

public class UserEditService {

    public UserDTO updateUser(UserDTO updatedUserDto) {

	User originalUser;
	try {
	    originalUser = new UserDAO().getUserById(Long.parseLong(updatedUserDto.getId()));
	} catch (Exception e) {
	    System.out.println("Error: Could not get original user by id");
	    return null;
	}

	if (updatedUserDto.getEmail() == null || updatedUserDto.getEmail().isEmpty()) {
	    updatedUserDto.setEmail(originalUser.getEmail());
	}

	if (updatedUserDto.getFirstName() == null || updatedUserDto.getFirstName().isEmpty()) {
	    updatedUserDto.setFirstName(originalUser.getFirstName());
	}

	if (updatedUserDto.getLastName() == null || updatedUserDto.getLastName().isEmpty()) {
	    updatedUserDto.setLastName(originalUser.getLastName());
	}

	// Admin users only
	if (updatedUserDto.getBalance() == null || updatedUserDto.getBalance().isEmpty() || originalUser.getRoleObject().getId() != 1) {
	    updatedUserDto.setBalance(originalUser.getBalance());
	}

	// Admin users only
	if (updatedUserDto.getRoleId() == null || updatedUserDto.getRoleId().isEmpty() || originalUser.getRoleObject().getId() != 1) {
	    updatedUserDto.setRoleId(originalUser.getRoleObject().getId());
	    updatedUserDto.setRoleName(originalUser.getRoleObject().getRole());
	}

	if (((updatedUserDto.getPlainPassword() == null) || updatedUserDto.getPlainPassword().isEmpty())
		&& ((updatedUserDto.getHashedPassword() == null) || (updatedUserDto.getHashedPassword().isEmpty()))) {
	    updatedUserDto.setHashedPassword(originalUser.getPasswordHash());
	}

	if (new GenericDAO().update(updatedUserDto.convertDtoToUser())) {
	    return updatedUserDto;
	} else {
	    return null;
	}
    }

}
