package com.github.serj86.java_ecommerce.dto;

public class UserDTO {

    private Long user_id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private Double balance;
    private Long roleId;
    private String roleName;

    public Long getRoleId() {
	return roleId;
    }

    public void setRoleId(Long roleId) {
	this.roleId = roleId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;

    }

    public String getFirstName() {
	return firstName;
    }

    public void setFirstName(String firstName) {
	this.firstName = firstName;
    }

    public String getLastName() {
	return lastName;
    }

    public void setLastName(String lastName) {
	this.lastName = lastName;
    }

    public Long getId() {
	return user_id;
    }

    public void setId(Long id) {
	this.user_id = id;
    }

    public String getRoleName() {
	return roleName;
    }

    public void setRoleName(String roleName) {
	this.roleName = roleName;
    }

}
