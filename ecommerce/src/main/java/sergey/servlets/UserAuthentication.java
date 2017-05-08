package sergey.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sergey.dao.UserDAO;
import sergey.entities.User;
import sergey.util.PasswordHashing.CannotPerformOperationException;
import sergey.util.PasswordHashing.InvalidHashException;

@WebServlet(urlPatterns = { "/authenticate" })
public class UserAuthentication extends HttpServlet {
    private static final long serialVersionUID = 1L;
    RequestDispatcher requestDispatcher;
    
    // Display login page for Get requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	request.setAttribute("notice", "Please login using eMail and Password.");
	requestDispatcher = getServletContext().getRequestDispatcher("/login.jsp");
	requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	String email = request.getParameter("email");
	String password = request.getParameter("password");
	boolean authenticated = false;
	response.getWriter().append("email: "+email);
	response.getWriter().append("password: "+password);

	UserDAO userDao = new UserDAO();
	userDao.getUserByEmail(email);
	User user = userDao.getUserByEmail(email);
	if (user != null) {
	    try {
		authenticated = user.validate(password);
	    } catch (CannotPerformOperationException e) {
		e.printStackTrace();
	    } catch (InvalidHashException e) {
		e.printStackTrace();
	    }
	}

	if (authenticated) {
	    response.getWriter().append("<h2>Authenticated - Welcome " + user.getFirstName() + " " + user.getLastName() + "!</h2>");
	} else {
	    response.getWriter().append("<h2>User authentication failed!</h2>");
	    response.getWriter().append("<p><a href='login.jsp'>Try to login again.</a></p>");
	    response.getWriter().append("<p><a href='register.jsp'>Register new account</a></p>");
	}
	response.getWriter().append("<p><a href='index.jsp'>Back to Homepage</a></p>");
    }

}
