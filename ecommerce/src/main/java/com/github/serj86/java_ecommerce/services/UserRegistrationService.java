package com.github.serj86.java_ecommerce.services;

import com.github.serj86.java_ecommerce.dao.GenericDAO;
import com.github.serj86.java_ecommerce.dao.UserDAO;
import com.github.serj86.java_ecommerce.dto.UserDTO;
import com.github.serj86.java_ecommerce.entities.User;

public class UserRegistrationService {

    public User registerUserDto(UserDTO uDto) {
	User user = uDto.convertDtoToUser();
	if (new UserDAO().getUserByEmail(user.getEmail()) == null) {
	    if (registerUser(user)) {
		return user;
	    } else {
		return null;
	    }
	} else {
	    System.out.println("Existing user with email: "+user.getEmail());
	    return null;
	}
    }

    public boolean registerUser(User user) {
	GenericDAO gDao = new GenericDAO();
	return gDao.add(user);
    }

}
