<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>New User Registration</title>

<script>
function validate_form() {
	
	var firstName = document.forms["registrationForm"]["firstName"].value;
	var lastName = document.forms["registrationForm"]["lastName"].value;
    var email = document.forms["registrationForm"]["email"].value;
    var password = document.forms["registrationForm"]["password"].value;
    var confirmPassword = document.forms["registrationForm"]["confirmPassword"].value;
    var emailFormat = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
    
    if (firstName == "" || lastName == "" || email == "" || password == "" || confirmPassword == "") {
    	alert("Please fill all the required fields.");
        return false;
    }
    
    if (!emailFormat.test(email)) {
    	alert("You have entered an invalid email address!");
    	registrationForm.email.focus();
        return false;
    }
    
    if (password != confirmPassword) {
    	alert("Both password fields must match!");
    	registrationForm.password.focus();
        return false;
    }
    
    if (password.length < 6) {
    	alert("Password must be at least 6 characters long!");
    	registrationForm.password.focus();
        return false;
    }
}
</script>

</head>
<body>
	<h1>New User Registration Form:</h1>
	<strong><c:out value="${requestScope.notice}"></c:out></strong>
	<form name="registrationForm" action="register" onsubmit="return validate_form()" method="post">
		<table>
			<tr>
				<td>First Name:</td>
				<td><input type="text" name="firstName" placeholder="First Name" required></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input type="text" name="lastName" placeholder="Last Name" required></td>
			</tr>
			<tr>
				<td>eMail:</td>
				<td><input type="text" name="email" placeholder="eMail" required></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" placeholder="Password" required></td>
			</tr>
			<tr>
				<td>Confirm Password:</td>
				<td><input type="password" name="confirmPassword" placeholder="Confirm Password" required></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form>
	<p>
	<ul>
		<a href="index.jsp">Back to Homepage</a>
	</ul>
	</p>
</body>
</html>