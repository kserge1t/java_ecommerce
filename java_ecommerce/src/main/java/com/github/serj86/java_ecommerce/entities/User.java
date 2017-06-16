package com.github.serj86.java_ecommerce.entities;

import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;

import com.github.serj86.java_ecommerce.util.BCrypt;

@Entity(name="users")
public class User {

    @Id
    @GeneratedValue
    @Column(name="user_id", unique = true, nullable = false, length = 20)
    private Long id;

    @Column(unique = true, nullable = false, length = 255)
    private String email;
    
    @Column(unique = false, nullable = false, length = 255)
    private String passwordHash;
    
    @Column(unique = false, nullable = false, length = 30)
    private String firstName;
    
    @Column(unique = false, nullable = false, length = 30)
    private String lastName;
    
    @Column(unique = false, nullable = false, length = 20)
    private Double balance;

    @ManyToOne(cascade=CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name="role_id", unique = false, nullable = false)
    private Role role;

    @OneToMany(mappedBy="user", fetch = FetchType.EAGER, orphanRemoval=true)
    private Set<Order> orders = new TreeSet<Order>();

    public Role getRole() {
	return role;
    }

    public void setRole(Role role) {
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

    public String getPasswordHash() {
	return passwordHash;
    }

    public void setPasswordHash(String plainPassword) {
	this.passwordHash = BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // For setting hashed password directly
    public void setPreHashedPassword(String hashedPassword) {
	this.passwordHash = hashedPassword;
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
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public Set<Order> getOrders() {
        return orders;
    }

}
