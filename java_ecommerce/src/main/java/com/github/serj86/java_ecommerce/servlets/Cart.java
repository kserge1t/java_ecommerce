package com.github.serj86.java_ecommerce.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.serj86.java_ecommerce.services.CartService;

@WebServlet(urlPatterns = { "/cart" })
public class Cart extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	RequestDispatcher requestDispatcher;
	HttpSession session = request.getSession();
	
	CartService cart = (CartService) session.getAttribute("cart");
	if (cart == null) {
	    cart = new CartService();
	}
	String dispatchUrl = "/cart.jsp";
	
	if (request.getParameter("add-sku") != null) {
	    cart.addProductBySku(request.getParameter("add-sku"));
	    dispatchUrl = "/catalog";
	}
	
	if (request.getParameter("inc-sku") != null) {
	    cart.addProductBySku(request.getParameter("inc-sku"));
	}
	
	if (request.getParameter("dec-sku") != null) {
	    cart.subtractQuantityBySku(request.getParameter("dec-sku"));
	}
	
	if (request.getParameter("rem-sku") != null) {
	    cart.removeProductBySku(request.getParameter("rem-sku"));
	}
	
	session.setAttribute("cart", cart);
	request.setAttribute("notice", cart.getMessage());
	requestDispatcher = getServletContext().getRequestDispatcher(dispatchUrl);
	requestDispatcher.forward(request, response);

    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	RequestDispatcher requestDispatcher;
	requestDispatcher = getServletContext().getRequestDispatcher("/cart.jsp");
	requestDispatcher.forward(request, response);
    }
    

}
