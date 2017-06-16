package com.github.serj86.java_ecommerce.entities;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

@Entity(name="orders")
public class Order {

    @Id
    @GeneratedValue
    @Column(name="order_id", unique = true, nullable = false, length = 20)
    private Long id;
    
    @Column(unique = false, nullable = false)
    private Calendar orderDate = Calendar.getInstance();
    
    @Column(unique = false, nullable = false, length = 50)
    private Double orderTotal = 0.00;
    
    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name="user_id") // (name="order_id", insertable=false, updatable=false)
    private User user;
    
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval=true)
    @JoinColumn(name="order_id")
    @MapKey(name = "sku")
    private Map<String, CartItem> cartItemsMap = new HashMap<String, CartItem>();


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }


    public void setUser(User user) {
        this.user = user;
    }


    public Calendar getOrderDate() {
        return orderDate;
    }


    public void setOrderDate(Calendar orderDate) {
        this.orderDate = orderDate;
    }


    public Double getOrderTotal() {
        return orderTotal;
    }


    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }


    public Map<String, CartItem> getCartItemsMap() {
        return cartItemsMap;
    }

    public void setCartItemsMap(Map<String, CartItem> cartItemsMap) {
        this.cartItemsMap = cartItemsMap;
    }

}
