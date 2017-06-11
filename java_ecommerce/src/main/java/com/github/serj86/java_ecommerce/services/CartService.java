package com.github.serj86.java_ecommerce.services;

import java.util.HashMap;
import com.github.serj86.java_ecommerce.dao.ProductDAO;
import com.github.serj86.java_ecommerce.entities.Product;

public class CartService {

    private String message = null;
    private Double total = 0.00;
    private HashMap<String, Product> productsMap = new HashMap<String, Product>();

    /* Cart Message Methods */
    public String getMessage() {
	String msg = message;
	this.message = null;
	return msg;
    }
    public void setMessage(String message) {
	this.message = message;
    }
    
    /* Cart Total Methods */
    public Double getTotal () {
	return total;
    }
    public void setTotal (Double total) {
	this.total = total;
    }
    public void addToTotal (Double amount) {
	this.total = this.total + amount;
    }
    public void subtractFromTotal (Double amount) {
	this.total = this.total - amount;
    }
    public void multiplyTotalBy (Double amount) {
	this.total = this.total * amount;
    }
    public void devideTotalBy (Double amount) {
	this.total = this.total / amount;
    }
    
    
    /* Cart Products Methods */

    public HashMap<String, Product> getProductsMap() {
	return productsMap;
    }

    /*
    public void setProductsMap(HashMap<String, Product> productsMap) {
	this.productsMap = productsMap;
    }
    */
    
    public Product getProductBySku(String sku) {
	return productsMap.get(sku);
    }
    
    public void addProductBySku(String sku) {
	Product inventoryItem = new ProductDAO().getProductBySku(sku);
	
	if (inventoryItem != null && inventoryItem.getActive() && inventoryItem.getStock() > 0) {
	    if (productsMap.containsKey(sku)) {
		if (inventoryItem.getStock() > this.getProductBySku(sku).getStock()) {
		    this.getProductBySku(sku).setStock(this.getProductBySku(sku).getStock()+1);
		    this.addToTotal(inventoryItem.getPrice());
		    this.setMessage("SKU \""+sku+"\" - Cart quantity has been increased by one, current quantity: "+productsMap.get(sku).getStock());
		} else {
		    this.getProductBySku(sku).setStock(inventoryItem.getStock());
		    this.setMessage("SKU \""+sku+"\" - Cannot add item, available quantity is "+inventoryItem.getStock()+", in cart: "+this.getProductBySku(sku).getStock());
		}
	    } else {
		inventoryItem.setStock(1); // stock to be used as qty in cart, adds 1 to cart
		productsMap.put(sku, inventoryItem);
		this.addToTotal(inventoryItem.getPrice());
		this.setMessage("SKU \""+sku+"\" has been added, current quantity in cart is "+productsMap.get(sku).getStock());
	    }
	} else {
	    this.setMessage("Item with SKU \""+sku+"\" cannot be added, it may be out of stock or inactive...");
	    productsMap.remove(sku); // Remove sku from cart in case it was there
	}
    }
    
    public void subtractQuantityBySku(String sku) {
	    if (this.getProductBySku(sku)!=null && this.getProductBySku(sku).getStock() > 0) {
		this.getProductBySku(sku).setStock(this.getProductBySku(sku).getStock()-1);
		this.subtractFromTotal(this.getProductBySku(sku).getPrice());
		this.setMessage("SKU \""+sku+"\" - Cart quantity decreased, new quantity: "+this.getProductBySku(sku).getStock());
	    } else {
		this.setMessage("SKU \""+sku+"\" - Quantity already zero, or item is no longer in the cart.");
	    }
    }
    
    public void removeProductBySku(String sku) {
	    if (productsMap.containsKey(sku)) {
		this.subtractFromTotal( this.getProductBySku(sku).getPrice() * this.getProductBySku(sku).getStock() );
		productsMap.remove(sku);
		this.setMessage("SKU \""+sku+"\" - Has been removed from the cart.");
	    } else {
		this.setMessage("Item with SKU \""+sku+"\" is not in cart.");
	    }
    }
    
}
