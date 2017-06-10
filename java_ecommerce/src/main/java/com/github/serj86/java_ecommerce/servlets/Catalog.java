package com.github.serj86.java_ecommerce.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.github.serj86.java_ecommerce.dao.ProductDAO;

@WebServlet(urlPatterns = { "/catalog" })
public class Catalog extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	RequestDispatcher requestDispatcher;
	
	// pDao.getProductsByFilter(active, minStock, maxStock, minPrice, maxPrice, category);
	//request.setAttribute("products", new ProductDAO().getProductsByFilter(true, 1, null, null, null, null));
	request.setAttribute("products", new ProductDAO().getAllProducts()); // Get all products
	requestDispatcher = getServletContext().getRequestDispatcher("/catalog.jsp");
	requestDispatcher.forward(request, response);

    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	RequestDispatcher requestDispatcher;
	ProductDAO pDao = new ProductDAO();
	
	Boolean active = Boolean.parseBoolean(request.getParameter("active"));
	Boolean outOfStock = Boolean.parseBoolean(request.getParameter("outOfStock"));
	Integer minStock = 1;
	if (outOfStock) minStock = null;
	Double minPrice = Double.parseDouble(request.getParameter("minPrice"));
	Double maxPrice = Double.parseDouble(request.getParameter("maxPrice"));
	//Category category = request.getParameter("categoryId");
	

	
	// pDao.getProductsByFilter(active, minStock, maxStock, minPrice, maxPrice, category);
	request.setAttribute("products", pDao.getProductsByFilter(active, minStock, null, minPrice, maxPrice, null));
	requestDispatcher = getServletContext().getRequestDispatcher("/catalog.jsp");
	requestDispatcher.forward(request, response);
    }
    

}
