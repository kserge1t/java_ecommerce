package com.github.serj86.java_ecommerce.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.serj86.java_ecommerce.dao.UserDAO;
import com.github.serj86.java_ecommerce.dto.UserDTO;
import com.github.serj86.java_ecommerce.services.UserEditService;

@WebServlet(urlPatterns = { "/user-edit" })
public class UserEdit extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Display register page for Get requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	RequestDispatcher requestDispatcher;
	requestDispatcher = getServletContext().getRequestDispatcher("/user-edit.jsp");
	requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	RequestDispatcher requestDispatcher;
	HttpSession session = request.getSession();

	if (request.getParameter("delete") == null) {

	    UserDTO uDto = new UserDTO();

	    uDto.setId(Long.parseLong(request.getParameter("userId")));
	    uDto.setFirstName(request.getParameter("firstName"));
	    uDto.setLastName(request.getParameter("lastName"));
	    uDto.setEmail(request.getParameter("email"));
	    uDto.setPlainPassword(request.getParameter("password"));
	    uDto.setBalance(request.getParameter("balance"));
	    uDto.setRoleId(request.getParameter("roleId"));

	    UserEditService editUser = new UserEditService();
	    uDto = editUser.updateUser(uDto);

	    if (uDto != null) {
		session.setAttribute("user", uDto); // update session
		request.setAttribute("notice", "User successfully edited!");
	    } else {
		request.setAttribute("notice", "User edit failed!");
	    }

	    requestDispatcher = getServletContext().getRequestDispatcher("/user-edit.jsp");
	    requestDispatcher.forward(request, response);

	} else {
	    if (new UserDAO().deleteUserById(Long.parseLong(request.getParameter("userId")))) {
		request.setAttribute("notice", "User has been deleted...");
		session.setAttribute("user", null);
		requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
	    } else {
		request.setAttribute("notice", "User deletion failed!");
		requestDispatcher = getServletContext().getRequestDispatcher("/user-edit.jsp");
	    }
	    requestDispatcher.forward(request, response);
	}
    }

}
