package com.github.serj86.java_ecommerce.client;

import com.github.serj86.java_ecommerce.dao.CategoryDAO;
import com.github.serj86.java_ecommerce.dao.GenericDAO;
import com.github.serj86.java_ecommerce.dao.ProductDAO;

public class test {

    public static void main(String[] args) {
	GenericDAO gDao = new GenericDAO();
	ProductDAO pDao = new ProductDAO();
	CategoryDAO cDao = new CategoryDAO();
//	List<Product> products = pDao.getAllProducts();
//	List<Category> categories = new ArrayList();

	/*
	for (Product product : products) {
	    System.out.print("Product Name: \""+product.getName()+"\" | Categories: ");
	    categories = product.getCategories();
	    for (Category category : categories) {
		System.out.print(category.getCategory()+", ");
	    }
	    System.out.println();
	}
	*/

    }

}
