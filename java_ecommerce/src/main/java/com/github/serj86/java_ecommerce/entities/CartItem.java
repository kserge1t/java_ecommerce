package com.github.serj86.java_ecommerce.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "cart_items")
public class CartItem {

    @Id
    @GeneratedValue
    @Column(name = "product_id", unique = true, nullable = false, length = 20)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="order_id", insertable=false, updatable=false)
    private Order order;

    @Column(unique = false, nullable = false, length = 50)
    private String sku;

    @Column(unique = false, nullable = false, length = 80)
    private String name;

    @Column(unique = false, nullable = false, length = 20)
    private Double price;

    @Column(unique = false, nullable = false, length = 20)
    private Integer quantity;

    @Column(unique = false, nullable = false, length = 20)
    private Double priceTotal;
    

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
    
    public Double getPrice() {
	return price;
    }

    public void setPrice(Double price) {
	this.price = price;
        this.updatePriceTotal();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
        this.updatePriceTotal();
    }

    public Double getPriceTotal() {
        return priceTotal;
    }

    private void setPriceTotal(Double priceTotal) {
        this.priceTotal = priceTotal;
    }

    private void updatePriceTotal() {
        try {
	    this.setPriceTotal(this.getQuantity()*this.getPrice());
	} catch (Exception e) {}
    }
    
}
