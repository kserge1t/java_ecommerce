package com.github.serj86.java_ecommerce.dto;

import java.util.HashSet;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.github.serj86.java_ecommerce.dao.CategoryDAO;
import com.github.serj86.java_ecommerce.entities.Category;
import com.github.serj86.java_ecommerce.entities.Product;
import com.github.serj86.java_ecommerce.entities.Product;

public class ProductDTO {

    private String id;
    private String sku;
    private String name;
    private String description;
    private HashSet<String> images;
    private String price;
    private String stock;
    private String active;
    private List<String> categoryNames;
    private List<String> categoryIds;
    
    public ProductDTO() {}
    
    public ProductDTO(Product product) {
	this.convertProductToDto(product);
    }

    public List<String> getCategoryNames() {
	return categoryNames;
    }

    public void setCategories(List<Category> categories) {
	for (Category category : categories) {
		this.categoryNames.add(String.valueOf(category.getCategory()));
		this.categoryIds.add(String.valueOf(category.getId()));
	}
    }

    public List<String> getCategoryIds() {
	return categoryIds;
    }

    public HashSet<String> getImages() {
	return images;
    }

    public <T> void setImages(HashSet<T> images) {
	for (T image : images) {
		this.images.add(String.valueOf(image));
	}
    }

    public String getPrice() {
        return price;
    }

    public <T> void setPrice(T balance) {
        this.price = String.valueOf(balance);
    }

    public String getStock() {
        return stock;
    }

    public <T> void setStock(T stock) {
        this.stock = String.valueOf(stock);
    }

    public String getSku() {
	return sku;
    }

    public <T> void setSku(T sku) {
	this.sku = String.valueOf(sku);
    }

    public String getActive() {
	return active;
    }

    public <T> void setActive(T active) {
	this.active = String.valueOf(active);
    }

    public String getName() {
	return name;
    }

    public <T> void setName(T name) {
	this.name = String.valueOf(name);
    }

    public String getDescription() {
	return description;
    }

    public <T> void setDescription(T description) {
	this.description = String.valueOf(description);
    }

    public String getId() {
	return id;
    }

    public <T> void setId(T id) {
	this.id = String.valueOf(id);
    }

    public void convertProductToDto(Product product) {
	this.setId(String.valueOf(product.getId()));
	this.setSku(String.valueOf(product.getSku()));
	this.setName(String.valueOf(product.getName()));
	this.setDescription(String.valueOf(product.getDescription()));
	this.setImages(product.getImages());
	this.setPrice(String.valueOf(product.getPrice()));
	this.setStock(String.valueOf(product.getStock()));
	this.setActive(String.valueOf(product.getActive()));
	this.setCategories(product.getCategories());
    }

    public Product convertDtoToProduct() {
	Product product = new Product();
	
	try {
	    product.setId(Long.parseLong(this.getId()));
	} catch (Exception e) {
	    System.out.println(e.getMessage()+" when setId("+this.getId()+") for Product");
	}

	try {
	product.setSku(this.getSku());
	} catch (Exception e) {
	    System.out.println(e.getMessage()+" when setSku("+this.getSku()+") for Product");
	}

	try {
	product.setName(this.getName());
	} catch (Exception e) {
	    System.out.println(e.getMessage()+" when setName("+this.getName()+") for Product");
	}

	try {
	product.setDescription(this.getDescription());
	} catch (Exception e) {
	    System.out.println(e.getMessage()+" when setDescription("+this.getDescription()+") for Product");
	}

	try {
	    product.setPrice(Double.parseDouble(this.getPrice()));
	} catch (Exception e) {
	    System.out.println(e.getMessage()+" when setBalance("+this.getPrice()+") for Product");
	}

	try {
	    product.setStock(Integer.getInteger(this.getStock()));
	} catch (Exception e) {
	    System.out.println(e.getMessage()+" when setBalance("+this.getStock()+") for Product");
	}

	try {
	    product.setActive(Boolean.parseBoolean(this.getActive()));
	} catch (Exception e) {
	    System.out.println(e.getMessage()+" when setBalance("+this.getActive()+") for Product");
	}

	return product;
    }

}
