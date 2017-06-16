package com.github.serj86.java_ecommerce.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.serj86.java_ecommerce.entities.Order;
import com.github.serj86.java_ecommerce.entities.User;
import com.github.serj86.java_ecommerce.services.OrderService;

@WebServlet(urlPatterns = { "/cart" })
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	RequestDispatcher requestDispatcher;
	HttpSession session = request.getSession();
	String dispatchUrl = "/cart.jsp";
	
	OrderService orderService = new OrderService();
	
	if (session.getAttribute("order") != null) {
	    orderService.setOrder((Order) session.getAttribute("order"));
	}
	
	if (request.getParameter("add-sku") != null) {
	    orderService.addProductBySku(request.getParameter("add-sku"));
	    dispatchUrl = "/catalog";
	}
	
	if (request.getParameter("inc-sku") != null) {
	    orderService.addProductBySku(request.getParameter("inc-sku"));
	}
	
	if (request.getParameter("dec-sku") != null) {
	    orderService.subtractQuantityBySku(request.getParameter("dec-sku"));
	}
	
	if (request.getParameter("rem-sku") != null) {
	    orderService.removeCartItemBySku(request.getParameter("rem-sku"));
	}
	
	if (request.getParameter("empty") != null) {
	    session.setAttribute("order", null);
	    request.setAttribute("notice", "Shopping cart has been emptied.");
	} else {
	    session.setAttribute("order", orderService.getOrder());
	    request.setAttribute("notice", orderService.getMessage());
	}
	
	if (request.getParameter("submit") != null && session.getAttribute("user") != null && session.getAttribute("order") != null) {
	    if (orderService.submitOrder((User) session.getAttribute("user"))){
	    	session.setAttribute("order", null);
	    	dispatchUrl = "/orders.jsp";
	    }
	    request.setAttribute("notice", orderService.getMessage());
	}

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
