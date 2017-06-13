package com.github.serj86.java_ecommerce.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue
    @Column(name = "product_id", unique = true, nullable = false, length = 20)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String sku;

    @Column(unique = false, nullable = false, length = 80)
    private String name;

    @Column(unique = false, nullable = true)
    private String description;

    @Column(unique = false, nullable = false, length = 20)
    private Double price;

    @Column(unique = false, nullable = false, length = 20)
    private Integer stock;

    @Column(unique = false, nullable = false)
    private Boolean active;

    @Column(unique = false, nullable = true)
    private String mainImage;

    // Implement additional images.
//    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name="category_id", unique = false, nullable = true)
//    private List<String> images;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "product_category", joinColumns = { @JoinColumn(name = "product_id") }, inverseJoinColumns = { @JoinColumn(name = "category_id") })
    private List<Category> categories;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getSku() {
	return sku;
    }

    public void setSku(String sku) {
	this.sku = sku;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public Double getPrice() {
	return price;
    }

    public void setPrice(Double price) {
	this.price = price;
    }

    public Integer getStock() {
	return stock;
    }

    public void setStock(Integer stock) {
	this.stock = stock;
    }

    public Boolean getActive() {
	return active;
    }

    public void setActive(Boolean active) {
	this.active = active;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
    
}
