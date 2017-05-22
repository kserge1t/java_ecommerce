<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>Please Login</title>

<script>
function validate_form() {
    var email = document.forms["loginForm"]["email"].value;
    var password = document.forms["loginForm"]["password"].value;
    var emailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    
    if (email == "" || password == "") {
    	alert("Please fill both, email and password.");
        return false;
    }
    
    if (!emailFormat.test(email)) {
    	alert("You have entered an invalid email address!");
        return false;
    }
}
</script>

</head>
<body>
	<h1>Login Page</h1>
	<c:if test="${not empty requestScope.notice}">
	   <p><strong><c:out value="${requestScope.notice} "/></strong></p>
	</c:if>
	<form name="loginForm" action="authenticate" onsubmit="return validate_form()" method="post">
		<table>
			<tr>
				<td>eMail:</td>
				<td><input type="text" name="email" placeholder="eMail" required></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" placeholder="Password" required></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Submit"></td>
			</tr>
		</table>
	</form>
	<p>
	<ul>
		<li><a href="index.jsp">Back to Homepage</a>
		<li><a href="register.jsp">Register</a>
	</ul>
	</p>
	
</body>
</html>