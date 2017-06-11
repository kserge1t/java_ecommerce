package com.github.serj86.java_ecommerce.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/logout" })
public class UserLogout extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Display login page for Get requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	
	RequestDispatcher requestDispatcher;
	HttpSession session = request.getSession();
	session.invalidate();

	request.setAttribute("notice", "You have been logged out.");
	requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
	requestDispatcher.forward(request, response);
	
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	RequestDispatcher requestDispatcher;
	HttpSession session = request.getSession();
	session.invalidate();

	request.setAttribute("notice", "You have been logged out.");
	requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
	requestDispatcher.forward(request, response);
	
    }

}
