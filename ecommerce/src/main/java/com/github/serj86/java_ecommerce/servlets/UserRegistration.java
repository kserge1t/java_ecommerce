package com.github.serj86.java_ecommerce.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.serj86.java_ecommerce.dao.RoleDAO;
import com.github.serj86.java_ecommerce.dao.SettingDAO;
import com.github.serj86.java_ecommerce.dao.UserDAO;
import com.github.serj86.java_ecommerce.entities.User;

@WebServlet(urlPatterns = {"/register"})
public class UserRegistration extends HttpServlet {
    private static final long serialVersionUID = 1L;
    RequestDispatcher requestDispatcher;
    
    // Display register page for Get requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	request.setAttribute("notice", "Please register.");
	requestDispatcher = getServletContext().getRequestDispatcher("/register.jsp");
	requestDispatcher.forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	String firstName = request.getParameter("firstName");
	String lastName = request.getParameter("lastName");
	String email = request.getParameter("email");
	String password = request.getParameter("confirmPassword");
	
	
	// Move logic to separate class
	User user = new User();
	user.setFirstName(firstName);
	user.setLastName(lastName);
	user.setEmail(email);
	user.setHashedPassword(password);
	RoleDAO roleDao = new RoleDAO();
	SettingDAO setting = new SettingDAO();
	//user.setRoleObject(roleDao.getRoleByValue(setting.getSettingValueByName("user_default_role")));
	user.setRoleObject(roleDao.getRoleById(Long.parseLong(setting.getSettingValueByName("user_default_role_id"))));
	user.setBalance(Double.parseDouble(setting.getSettingValueByName("user_starting_balance")));
	

	//response.getWriter().append("<li>password: "+request.getParameter("password"));
	//response.getWriter().append("<li>confirmPassword: "+request.getParameter("confirmPassword"));
	response.getWriter().append("<li>User First Name: "+user.getFirstName());
	response.getWriter().append("<li>User Last Name: "+user.getLastName());
	response.getWriter().append("<li>User Email: "+user.getEmail());
	response.getWriter().append("<li>User Balance: "+user.getBalance());
	//response.getWriter().append("<li>User Role: "+user.getRole());
	//response.getWriter().append("<li>User Password: "+user.getHashedPassword());
	
	
	UserDAO userDao = new UserDAO();
	userDao.addUser(user);
	
	response.getWriter().append("<p>New user registration succeeeded! Please login using your email <b>"+request.getParameter("email")+"</b> and password.</p>");
	response.getWriter().append("<p><a href='login.jsp'>Login</a></p>");
	response.getWriter().append("<p><a href='index.jsp'>Back to Homepage</a></p>");
	
    }

}
