package com.github.serj86.java_ecommerce.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.github.serj86.java_ecommerce.util.BCrypt;

@Entity(name="users")
public class User {

    @Id
    @GeneratedValue
    private Long user_id;

    @Column(unique = true)
    private String email;
    
    private String hashedPassword;
    private String firstName;
    private String lastName;
    private Double balance;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="role_id")
    private Role role;

    public Role getRoleObject() {
	return role;
    }

    public void setRoleObject(Role role) {
	this.role = role;
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

    public String getHashedPassword() {
	return hashedPassword;
    }

    public void setHashedPassword(String password) {
	hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());;

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

}
