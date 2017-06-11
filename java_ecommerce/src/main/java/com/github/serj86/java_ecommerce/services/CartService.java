package com.github.serj86.java_ecommerce.services;

import java.util.HashMap;
import com.github.serj86.java_ecommerce.dao.ProductDAO;
import com.github.serj86.java_ecommerce.entities.Product;

public class CartService {

    public <T> HashMap<String,T> add(String sku, HashMap<String,T> items) {
	if (items == null) {
	    items = new HashMap<String,T>();
	}
	
	Product cartProduct;
	Product inventoryProduct = new ProductDAO().getProductBySku(sku);
	
	if (inventoryProduct == null || !inventoryProduct.getActive()) {
	    return null;
	}
	
	if (items.containsKey(sku)) {
	    cartProduct = (Product) items.get(sku);
	    if (inventoryProduct.getStock() > cartProduct.getStock()) {
		cartProduct.setStock(cartProduct.getStock()+1);
	    } else {
		return null;
	    }
	} else {
	    cartProduct = inventoryProduct;
	    if (inventoryProduct.getStock() > 0) {
		cartProduct.setStock(1);
	    } else {
		return null;
	    }
	}
	
	items.put(sku, (T) cartProduct);
	return items;
    }
    
    public <T> HashMap<String,T> remove(String sku, HashMap<String,T> items) {
	if (items!=null && items.containsKey(sku)) {
	    items.remove(sku);
	    return items;
	}
	return null;
    }
    
    public <T> HashMap<String,T> subtract(String sku, HashMap<String,T> items) {
	if (items!=null && items.containsKey(sku)) {
	    Product cartProduct = (Product) items.get(sku);
	    if (cartProduct.getStock() <= 0) return null;
	    cartProduct.setStock(cartProduct.getStock()-1);
	    return items;
	}
	return null;
    }

}
