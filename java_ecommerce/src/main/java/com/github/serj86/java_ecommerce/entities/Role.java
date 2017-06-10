package com.github.serj86.java_ecommerce.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="roles")
public class Role {
    
    @Id
    @GeneratedValue
    @Column(name="role_id", unique = true, nullable = false, length = 20)
    private Long id;
    
    @Column(unique = true, nullable = false, length = 30)
    private String role;
    
    public Role() {}
    
    public Role(Long role_id, String role) {
	this.setId(role_id);
	this.setRole(role);
    }
    
    public Role(String role) {
	this.setRole(role);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long role_id) {
        this.id = role_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
