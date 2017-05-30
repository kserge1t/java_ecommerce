package com.github.serj86.java_ecommerce.dto;

import com.github.serj86.java_ecommerce.dao.RoleDAO;
import com.github.serj86.java_ecommerce.entities.User;

public class UserDTO {

    private String user_id;
    private String email;
    private String plainPassword;
    private String hashedPassword;
    private String firstName;
    private String lastName;
    private String balance;
    private String roleId;
    private String roleName;
    
    public UserDTO() {}
    
    public UserDTO(User user) {
	this.convertUserToDto(user);
    }

    public String getRoleId() {
	return roleId;
    }

    public <T> void setRoleId(T roleId) {
	this.roleId = String.valueOf(roleId);
    }

    public String getBalance() {
        return balance;
    }

    public <T> void setBalance(T balance) {
        this.balance = String.valueOf(balance);
    }

    public String getEmail() {
	return email;
    }

    public <T> void setEmail(T email) {
	this.email = String.valueOf(email);
    }

    public String getFirstName() {
	return firstName;
    }

    public <T> void setFirstName(T firstName) {
	this.firstName = String.valueOf(firstName);
    }

    public String getLastName() {
	return lastName;
    }

    public <T> void setLastName(T lastName) {
	this.lastName = String.valueOf(lastName);
    }

    public String getId() {
	return user_id;
    }

    public <T> void setId(T id) {
	this.user_id = String.valueOf(id);
    }

    public String getRoleName() {
	return roleName;
    }

    public <T> void setRoleName(T roleName) {
	this.roleName = String.valueOf(roleName);
    }

    public String getPlainPassword() {
	return plainPassword;
    }

    public <T> void setPlainPassword(T plainPassword) {
	this.plainPassword = String.valueOf(plainPassword);
    }

    public String getHashedPassword() {
	return hashedPassword;
    }

    public <T> void setHashedPassword(T hashedPassword) {
	this.hashedPassword = String.valueOf(hashedPassword);
    }
    
    public void convertUserToDto(User user) {
	this.setId(String.valueOf(user.getId()));
	this.setEmail(user.getEmail());
	this.setFirstName(user.getFirstName());
	this.setLastName(user.getLastName());
	this.setRoleId(String.valueOf(user.getRoleObject().getId()));
	this.setRoleName(user.getRoleObject().getRole());
	this.setBalance(String.valueOf(user.getBalance()));
	this.setHashedPassword(user.getPasswordHash());
    }

    public User convertDtoToUser() {
	User user = new User();
	
	try {
	    user.setId(Long.parseLong(this.getId()));
	} catch (Exception e) {
	    System.out.println(e.getMessage()+" when setId("+this.getId()+") for User");
	}

	try {
	user.setEmail(this.getEmail());
	} catch (Exception e) {
	    System.out.println(e.getMessage()+" when setEmail("+this.getEmail()+") for User");
	}

	try {
	user.setFirstName(this.getFirstName());
	} catch (Exception e) {
	    System.out.println(e.getMessage()+" when setFirstName("+this.getFirstName()+") for User");
	}

	try {
	user.setLastName(this.getLastName());
	} catch (Exception e) {
	    System.out.println(e.getMessage()+" when setLastName("+this.getLastName()+") for User");
	}

	try {
	    user.setRoleObject(new RoleDAO().getRoleById(Integer.parseInt(this.getRoleId())));
	} catch (Exception e) {
	    System.out.println(e.getMessage()+" when setRoleObject("+this.getRoleId()+") for User");
	}

	try {
	    user.setBalance(Double.parseDouble(this.getBalance()));
	} catch (Exception e) {
	    System.out.println(e.getMessage()+" when setBalance("+this.getBalance()+") for User");
	}

	try {
	    if (this.getPlainPassword() != null)
	    user.setPasswordHash(this.getPlainPassword());
	} catch (Exception e) {
	    System.out.println(e.getMessage()+" when setPasswordHash("+this.getPlainPassword()+") for User");
	}

	try {
	    if (this.getHashedPassword() != null)
	    user.setPreHashedPassword(this.getHashedPassword());
	} catch (Exception e) {
	    System.out.println(e.getMessage()+" when setPreHashedPassword("+this.getHashedPassword()+") for User");
	}

	return user;
    }

}
