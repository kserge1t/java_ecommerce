package com.github.serj86.java_ecommerce.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.serj86.java_ecommerce.dao.GenericDAO;
import com.github.serj86.java_ecommerce.dto.UserDTO;
import com.github.serj86.java_ecommerce.entities.User;
import com.github.serj86.java_ecommerce.services.UserService;

@WebServlet(urlPatterns = { "/user-edit" })
public class UserEdit extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Display homepage page for Get requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	RequestDispatcher requestDispatcher;
	requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
	requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	RequestDispatcher requestDispatcher;
	HttpSession session = request.getSession();
	UserDTO userDto = new UserDTO((User) session.getAttribute("user"));

	if (request.getParameter("delete") == null) {
	    
	    userDto.setFirstName(request.getParameter("firstName"));
	    userDto.setLastName(request.getParameter("lastName"));
	    userDto.setEmail(request.getParameter("email"));
	    userDto.setPasswordHash(request.getParameter("password"));
	    userDto.setBalance(request.getParameter("balance"));
	    userDto.setRole(request.getParameter("roleId"));

	    UserService userService = new UserService();
	    User user = userService.updateUser(userDto.convertToUser());

	    if (user != null) {
		session.setAttribute("user", user); // update session
		request.setAttribute("notice", "User successfully edited!");
	    } else {
		request.setAttribute("notice", "User edit failed!");
	    }

	    requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
	    requestDispatcher.forward(request, response);

	} else {
	    if (new GenericDAO().delete((User) session.getAttribute("user"))) {
		request.setAttribute("notice", "User has been deleted...");
		session.setAttribute("user", null);
		requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
	    } else {
		request.setAttribute("notice", "User deletion failed!");
		requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
	    }
	    requestDispatcher.forward(request, response);
	}
    }

}
