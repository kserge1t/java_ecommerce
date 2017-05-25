package com.github.serj86.java_ecommerce.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.serj86.java_ecommerce.dto.UserDTO;
import com.github.serj86.java_ecommerce.services.UserEditService;

@WebServlet(urlPatterns = {"/user-edit"})
public class UserEdit extends HttpServlet {
    private static final long serialVersionUID = 1L;
    RequestDispatcher requestDispatcher;
    
    // Display register page for Get requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	requestDispatcher = getServletContext().getRequestDispatcher("/user-edit.jsp");
	requestDispatcher.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	UserDTO uDto = new UserDTO();
	uDto.setId(Long.parseLong(request.getParameter("userId")));
	uDto.setFirstName(request.getParameter("firstName"));
	uDto.setLastName(request.getParameter("lastName"));
	uDto.setEmail(request.getParameter("email"));
	uDto.setPassword(request.getParameter("password"));
	
	// Handle null pointer exception for null values
	//uDto.setBalance(Double.parseDouble(request.getParameter("balance")));
	//uDto.setRoleId(Long.parseLong(request.getParameter("roleId")));

	UserEditService addUser = new UserEditService();
	if (addUser.updateUser(uDto)) {
	    response.getWriter().append("<p>User updated!");
	}
	

	response.getWriter().append("<p><a href='user-edit.jsp'>Edit</a></p>");
	response.getWriter().append("<p><a href='login.jsp'>Login</a></p>");
	response.getWriter().append("<p><a href='index.jsp'>Back to Homepage</a></p>");
	
    }

}
