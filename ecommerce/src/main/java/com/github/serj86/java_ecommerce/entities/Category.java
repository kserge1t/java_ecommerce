package com.github.serj86.java_ecommerce.entities;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="categories")
public class Category {
    
    @Id
    @GeneratedValue
    @Column(name="category_id", unique = true, nullable = false, length = 20)
    private Long id;
    
    @Column(unique = true, nullable = false, length = 50)
    private String category;
    
    @Column(unique = false, nullable = false)
    private Boolean visible;
    
    public Category() {}
    
    public Category(String name, Boolean visible) {
	this.setCategory(name);
	this.setVisible(visible);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long category_id) {
        this.id = category_id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

}
