package com.github.serj86.java_ecommerce.services;

import com.github.serj86.java_ecommerce.dao.GenericDAO;
import com.github.serj86.java_ecommerce.dao.RoleDAO;
import com.github.serj86.java_ecommerce.dao.SettingDAO;
import com.github.serj86.java_ecommerce.dao.UserDAO;
import com.github.serj86.java_ecommerce.dto.UserDTO;
import com.github.serj86.java_ecommerce.entities.User;

public class UserRegistrationService {

    public User addUser(UserDTO uDto) {
    	User user = new User();
	user.setFirstName(uDto.getFirstName());
	user.setLastName(uDto.getLastName());
	user.setEmail(uDto.getEmail());
	user.setHashedPassword(uDto.getPassword());
	
	RoleDAO roleDao = new RoleDAO();
	SettingDAO setting = new SettingDAO();
	
	user.setRoleObject(roleDao.getRoleById(Long.parseLong(setting.getSettingValueByName("user_default_role_id"))));
	user.setBalance(Double.parseDouble(setting.getSettingValueByName("user_starting_balance")));
	
	GenericDAO gDao = new GenericDAO();
	gDao.add(user);
	
	return user;
    	}

}
