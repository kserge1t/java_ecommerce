package com.github.serj86.java_ecommerce.dto;

import com.github.serj86.java_ecommerce.dao.RoleDAO;
import com.github.serj86.java_ecommerce.entities.Role;
import com.github.serj86.java_ecommerce.entities.User;
import com.github.serj86.java_ecommerce.util.BCrypt;

public class UserDTO {

    private Long id;
    private String email;
    private String passwordHash;
    private String firstName;
    private String lastName;
    private Double balance;
    private Long roleId;
    private String roleName;

    public UserDTO() {
    }

    public UserDTO(User user) {
	this.setId(user.getId());
	this.setEmail(user.getEmail());
	this.setPreHashedPassword(user.getPasswordHash());
	this.setFirstName(user.getFirstName());
	this.setLastName(user.getEmail());
	this.setBalance(user.getBalance());
	this.setRoleId(user.getRole().getId());
	this.setRoleName(user.getRole().getRole());
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public void setId(String id) {
	try {
	    this.setId(Long.valueOf(id));
	} catch (NumberFormatException e) {
	    this.id = null;
	}
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = processStringValue(email);
    }

    public String getPasswordHash() {
	return passwordHash;
    }

    public void setPasswordHash(String plainPassword) {
	plainPassword = processStringValue(plainPassword);
	if (plainPassword != null)
	    this.passwordHash = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
	else 
	    this.passwordHash = null;
    }

    public void setPreHashedPassword(String hashedPassword) {
	this.passwordHash = processStringValue(hashedPassword);
    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = processStringValue(firstName);
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = processStringValue(lastName);
    }

    public Double getBalance() {
	return balance;
    }

    public void setBalance(Double balance) {
	this.balance = balance;
    }

    public void setBalance(String balance) {
	try {
	    this.setBalance(Double.valueOf(balance));
	} catch (Exception e) {
	    this.balance = null;
	}
    }

    public Long getRoleId() {
	return roleId;
    }

    public void setRoleId(Long roleId) {
	this.roleId = roleId;
	
    }

    public void setRoleId(String roleId) {
	try {
	    this.roleId = Long.valueOf(roleId);
	} catch (Exception e) {
	    this.roleId = null;
	}
    }

    public String getRoleName() {
	return roleName;
    }

    public void setRoleName(String roleName) {
	this.roleName = processStringValue(roleName);
    }

    public void setRole(Role role) {
	this.roleId = role.getId();
	this.roleName = role.getRole();
    }

    public void setRole(Long role_id) {
	this.setRole(new RoleDAO().getRoleById(role_id));
    }

    public void setRole(String role_id) {
	try {
	    this.setRole(new RoleDAO().getRoleById(Long.valueOf(roleId)));
	} catch (Exception e) {
	    this.roleId = null;
	    this.roleName = null;
	}
    }

    /* Other Methods */

    public User convertToUser() {
	User user = new User();

	try {
	    user.setId(this.getId());
	    user.setEmail(this.getEmail());
	    user.setPreHashedPassword(this.getPasswordHash());
	    user.setFirstName(this.getFirstName());
	    user.setLastName(this.getLastName());
	    user.setBalance(this.getBalance());
	    user.setRole(new RoleDAO().getRoleById(this.getRoleId()));
	} catch (Exception e) {
	    user = null;
	    e.printStackTrace();
	}

	return user;

    }

    private String processStringValue(String value) {
	if (value.trim().isEmpty())
	    return null;
	else
	    return value.trim();
    }

}
