package com.github.serj86.java_ecommerce.services;

import java.util.Calendar;
import java.util.Map;

import com.github.serj86.java_ecommerce.dao.GenericDAO;
import com.github.serj86.java_ecommerce.dao.ProductDAO;
import com.github.serj86.java_ecommerce.entities.CartItem;
import com.github.serj86.java_ecommerce.entities.Order;
import com.github.serj86.java_ecommerce.entities.Product;
import com.github.serj86.java_ecommerce.entities.User;

public class OrderService {

    private String message = null;
    private Order order = new Order();

    /* Order Message Methods */
    public String getMessage() {
	String msg = message;
	this.message = null;
	return msg;
    }
    public void setMessage(String message) {
	this.message = message;
    }
    
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    
    /* Order Total Methods */
    public Double getTotal () {
	return this.order.getOrderTotal();
    }
    public void setTotal (Double total) {
	this.order.setOrderTotal(total);
    }
    public void addToTotal (Double amount) {
	this.order.setOrderTotal(this.getTotal() + amount);
    }
    public void subtractFromTotal (Double amount) {
	this.order.setOrderTotal(this.getTotal() - amount);
    }
    public void multiplyTotalBy (Double amount) {
	this.order.setOrderTotal(this.getTotal() * amount);
    }
    public void devideTotalBy (Double amount) {
	this.order.setOrderTotal(this.getTotal() / amount);
    }
    
    
    /* Cart Items Methods */

    public Map<String, CartItem> getCartItemsMap() {
	return order.getCartItemsMap();
    }
    
    private CartItem cartItemAdd(CartItem item) {
	return order.getCartItemsMap().put(item.getSku(), item);
    }
    
    public CartItem cartItemRemove(String sku) {
	return order.getCartItemsMap().remove(sku);
    }
    
    public CartItem cartItemGet(String sku) {
	return order.getCartItemsMap().get(sku);
    }
    
    public Boolean cartItemContains(String sku) {
	return order.getCartItemsMap().containsKey(sku);
    }
    
    
    /* Service Methods */
    
    public CartItem convertToCartItem (Product product) {
	CartItem cartItem = new CartItem();
	cartItem.setSku(product.getSku());
	cartItem.setName(product.getName());
	cartItem.setPrice(product.getPrice());
	cartItem.setQuantity(0); // default quantity is 0
	return cartItem;
    }
    
    public void addProductBySku(String sku) {
	Product inventoryItem = new ProductDAO().getProductBySku(sku);
	
	if (inventoryItem != null && inventoryItem.getActive() && inventoryItem.getStock() > 0) {
	    if (this.cartItemGet(sku) != null) {
		if (inventoryItem.getStock() > this.cartItemGet(sku).getQuantity()) {
		    this.cartItemGet(sku).setQuantity(this.cartItemGet(sku).getQuantity() + 1);
		    this.addToTotal(inventoryItem.getPrice());
		    this.setMessage("SKU \""+sku+"\" - Cart quantity has been increased by one, current quantity: "+this.cartItemGet(sku).getQuantity());
		} else {
		    this.cartItemGet(sku).setQuantity(inventoryItem.getStock());
		    this.setMessage("SKU \""+sku+"\" - Cannot add item, available quantity is "+inventoryItem.getStock()+", in cart: "+this.cartItemGet(sku).getQuantity());
		}
	    } else {
		this.cartItemAdd(this.convertToCartItem(inventoryItem));
		this.cartItemGet(sku).setQuantity(1);
		this.addToTotal(inventoryItem.getPrice());
		this.setMessage("SKU \""+sku+"\" has been added, current quantity in cart is "+this.cartItemGet(sku).getQuantity());
	    }
	} else {
	    this.setMessage("Item with SKU \""+sku+"\" cannot be added, it may be out of stock or inactive...");
	    this.removeCartItemBySku(sku); // Remove sku from cart in case it was there
	}
    }
    
    public void subtractQuantityBySku(String sku) {
	    if (this.cartItemContains(sku) && this.cartItemGet(sku).getQuantity() > 1) {
		this.cartItemGet(sku).setQuantity(this.cartItemGet(sku).getQuantity()-1);
		this.subtractFromTotal(this.cartItemGet(sku).getPrice());
		this.setMessage("SKU \""+sku+"\" - Cart quantity decreased, new quantity: "+this.cartItemGet(sku).getQuantity());
	    } else {
		this.setMessage("SKU \""+sku+"\" - Quantity cannot be zero.");
	    }
    }
    
    public void removeCartItemBySku(String sku) {
	    if (this.cartItemContains(sku)) {
		this.subtractFromTotal( this.cartItemGet(sku).getPrice() * this.cartItemGet(sku).getQuantity() );
		this.cartItemRemove(sku);
		this.setMessage("SKU \""+sku+"\" - Has been removed from the cart.");
		if (this.getCartItemsMap().isEmpty()) {
		    this.setOrder(null);
		}
	    } else {
		this.setMessage("Item with SKU \""+sku+"\" is not in cart.");
	    }
    }
    
    public Boolean submitOrder(User user) {
	if (user.getBalance() >= this.getOrder().getOrderTotal()) {
	    this.getOrder().setUser(user);
	    this.getOrder().getUser().setBalance(this.getOrder().getUser().getBalance() - this.getOrder().getOrderTotal());
	    this.getOrder().setOrderDate(Calendar.getInstance());
	    if (new GenericDAO().add(this.getOrder())) {
		user.getOrders().add(this.getOrder()); // update user with order
		this.setMessage("Order has been submited!");
		return true;
	    } else {
		this.getOrder().getUser().setBalance(this.getOrder().getUser().getBalance() + this.getOrder().getOrderTotal());
		this.setMessage("Order submission failed!");
	    }
	} else {
	    this.setMessage("Insufficient balance...");
	}
	return false;
    }
    
}
