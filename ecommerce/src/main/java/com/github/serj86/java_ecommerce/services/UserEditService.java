package com.github.serj86.java_ecommerce.services;

import com.github.serj86.java_ecommerce.dao.GenericDAO;
import com.github.serj86.java_ecommerce.dao.RoleDAO;
import com.github.serj86.java_ecommerce.dao.UserDAO;
import com.github.serj86.java_ecommerce.dto.UserDTO;
import com.github.serj86.java_ecommerce.entities.User;

public class UserEditService {

    public boolean updateUser(UserDTO uDto) {
	User user = null;

	if (uDto.getId() != null) {
	    UserDAO uDao = new UserDAO(); 
	    user = uDao.getUserById(uDto.getId());

	    if (uDto.getFirstName().length() > 1) {
		user.setFirstName(uDto.getFirstName());
	    }

	    if (uDto.getLastName().length() > 1) {
		user.setLastName(uDto.getLastName());
	    }

	    if (uDto.getEmail().length() > 1) {
		user.setEmail(uDto.getEmail());
	    }

	    if (uDto.getPassword().length() > 1) {
		user.setHashedPassword(uDto.getPassword());
	    }

	    if (uDto.getRoleId() != null) {
		RoleDAO rDao = new RoleDAO();
		user.setRoleObject(rDao.getRoleById(uDto.getRoleId()));
	    }

	    if (uDto.getBalance() != null) {
		user.setBalance(uDto.getBalance());
	    } 
		System.out.println("Balace: "+uDto.getBalance());

	    GenericDAO gDao = new GenericDAO();
	    return gDao.update(user);

	}

	return false;
    }

}
