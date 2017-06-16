package com.github.serj86.java_ecommerce.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.serj86.java_ecommerce.dao.RoleDAO;
import com.github.serj86.java_ecommerce.dao.SettingDAO;
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
	User user = new User();
	user.setFirstName(request.getParameter("firstName"));
	user.setLastName(request.getParameter("lastName"));
	user.setEmail(request.getParameter("email"));
	user.setPasswordHash(request.getParameter("password"));
	user.setBalance(Double.parseDouble(setting.getSettingValueByName("user_starting_balance")));
	user.setRole(new RoleDAO().getRoleById(Long.parseLong(setting.getSettingValueByName("user_default_role_id"))));

	UserRegistrationService addUser = new UserRegistrationService();
	user = addUser.registerUserDto(user);

	if (user != null) {
	    session.setAttribute("user", user);
	    request.setAttribute("notice", "Welcome " + user.getFirstName() + " " + user.getLastName() + "!");
	    requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
	} else {
	    request.setAttribute("notice", "User registration failed!");
	    requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
	}
	requestDispatcher.forward(request, response);

    }

}
