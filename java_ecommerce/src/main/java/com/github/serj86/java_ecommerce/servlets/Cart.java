package com.github.serj86.java_ecommerce.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
	
	// pDao.getProductsByFilter(active, minStock, maxStock, minPrice, maxPrice, category);
	//request.setAttribute("products", new ProductDAO().getProductsByFilter(true, 1, null, null, null, null));
	//List items = (List) session.getAttribute("items");
	
//	List items = new ArrayList();
//	System.out.println("request.getParameter('sku'): "+request.getParameter("sku"));
//	items.add(new ProductDAO().getProductBySku(request.getParameter("sku")));
	
	CartService cartService = new CartService();
	HashMap items = null;
	String msgOk = "Cart updated";
	String msgKo = null;
	String url = "/cart.jsp";
	
	if (request.getParameter("add") != null) {
	    items = cartService.add(request.getParameter("add"), (HashMap) session.getAttribute("items"));
	    url = "/catalog";
	    msgOk = "Item SKU: \""+request.getParameter("add")+"\" was added to your shopping cart.";
	    msgKo = "Item SKU: \""+request.getParameter("add")+"\" is out of stock or inactive.";
	}
	
	if (request.getParameter("inc") != null) {
	    items = cartService.add(request.getParameter("inc"), (HashMap) session.getAttribute("items"));
	    msgOk = "Item SKU: \""+request.getParameter("inc")+"\" quantity increased.";
	    msgKo = "Item SKU: \""+request.getParameter("inc")+"\" - Requested quantity is not available, or item is inactive.";
	}
	
	if (request.getParameter("dec") != null) {
	    items = cartService.subtract(request.getParameter("dec"), (HashMap) session.getAttribute("items"));
	    msgOk = "Item SKU: \""+request.getParameter("dec")+"\" quantity decreased.";
	    msgKo = "Item SKU: \""+request.getParameter("dec")+"\" - Requested quantity is no longer available, or item is inactive.";
	}
	
	if (request.getParameter("rem") != null) {
	    items = cartService.remove(request.getParameter("rem"), (HashMap) session.getAttribute("items"));
	    msgOk = "Item SKU: \""+request.getParameter("rem")+"\" was removed from your shopping cart.";
	    msgKo = "Item SKU: \""+request.getParameter("rem")+"\" remove unsuccessful!";
	}
	    
	if (items != null) {
	    session.setAttribute("items", items);
	    request.setAttribute("notice", msgOk);
	} else {
	    request.setAttribute("notice", msgKo);
	}
	
	requestDispatcher = getServletContext().getRequestDispatcher(url);
	requestDispatcher.forward(request, response);

    }

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	RequestDispatcher requestDispatcher;
	requestDispatcher = getServletContext().getRequestDispatcher("/cart.jsp");
	requestDispatcher.forward(request, response);
    }
    

}
