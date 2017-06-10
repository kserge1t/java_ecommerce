package com.github.serj86.java_ecommerce.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.serj86.java_ecommerce.dao.SettingDAO;
import com.github.serj86.java_ecommerce.dto.UserDTO;
import com.github.serj86.java_ecommerce.entities.User;
import com.github.serj86.java_ecommerce.services.UserRegistrationService;

@WebServlet(urlPatterns = { "/register" })
public class UserRegistration extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Display register page for Get requests
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

	SettingDAO setting = new SettingDAO();
	UserDTO uDto = new UserDTO();
	uDto.setFirstName(request.getParameter("firstName"));
	uDto.setLastName(request.getParameter("lastName"));
	uDto.setEmail(request.getParameter("email"));
	uDto.setPlainPassword(request.getParameter("password"));
	uDto.setBalance(setting.getSettingValueByName("user_starting_balance"));
	uDto.setRoleId(setting.getSettingValueByName("user_default_role_id"));

	UserRegistrationService addUser = new UserRegistrationService();
	User user = addUser.registerUserDto(uDto);

	if (user != null) {
	    uDto.convertUserToDto(user);
	    session.setAttribute("user", uDto);
	    request.setAttribute("notice", "Welcome " + uDto.getFirstName() + " " + uDto.getLastName() + "!");
	    requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
	} else {
	    request.setAttribute("notice", "User registration failed!");
	    requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
	}
	requestDispatcher.forward(request, response);

    }

}
