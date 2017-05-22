package com.github.serj86.java_ecommerce.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="roles")
public class Role {
    
    @Id
    @GeneratedValue
    private Long role_id;
    
    @Column(unique = true)
    private String role;

    public Long getId() {
        return role_id;
    }

    public void setId(Long role_id) {
        this.role_id = role_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
