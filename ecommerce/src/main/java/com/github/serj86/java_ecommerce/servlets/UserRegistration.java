package com.github.serj86.java_ecommerce.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.serj86.java_ecommerce.dto.UserDTO;
import com.github.serj86.java_ecommerce.entities.User;
import com.github.serj86.java_ecommerce.services.UserRegistrationService;

@WebServlet(urlPatterns = {"/register"})
public class UserRegistration extends HttpServlet {
    private static final long serialVersionUID = 1L;
    RequestDispatcher requestDispatcher;
    
    // Display register page for Get requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	requestDispatcher = getServletContext().getRequestDispatcher("/register.jsp");
	requestDispatcher.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	UserDTO uDto = new UserDTO();
	uDto.setFirstName(request.getParameter("firstName"));
	uDto.setLastName(request.getParameter("lastName"));
	uDto.setEmail(request.getParameter("email"));
	uDto.setPassword(request.getParameter("password"));

	UserRegistrationService addUser = new UserRegistrationService();
	User user = addUser.addUser(uDto);
	
	// Move to registration success page 
	response.getWriter().append("<li>User First Name: "+user.getFirstName());
	response.getWriter().append("<li>User Last Name: "+user.getLastName());
	response.getWriter().append("<li>User Email: "+user.getEmail());
	response.getWriter().append("<li>User Balance: "+user.getBalance());
	response.getWriter().append("<li>User Role: "+user.getRoleObject().getRole());
	
	response.getWriter().append("<p>New user registration succeeeded! Please login using your email <b>"+request.getParameter("email")+"</b> and password.</p>");
	response.getWriter().append("<p><a href='login.jsp'>Login</a></p>");
	response.getWriter().append("<p><a href='index.jsp'>Back to Homepage</a></p>");
	
    }

}
