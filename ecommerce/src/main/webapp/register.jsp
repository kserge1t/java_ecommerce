<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>New User Registration</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="js/UserFormValidator.js"></script>

</head>
<body>
	<h1>New User Registration Form:</h1>
	<h3><c:out value="${requestScope.notice}"></c:out></h3>
	<form name="userForm" action="register" onsubmit="return validateUserForm()" method="post">
		<table>
			<tr id="firstName">
				<td>First Name:</td>
				<td><input type="text" name="firstName" placeholder="First Name" required></td>
				<td><span class="msg"></span></td>
			</tr>
			<tr id="lastName">
				<td>Last Name:</td>
				<td><input type="text" name="lastName" placeholder="Last Name" required></td>
				<td><span class="msg"></span></td>
			</tr>
			<tr id="email">
				<td>eMail:</td>
				<td><input type="text" name="email" placeholder="eMail" required></td>
				<td><span class="msg"></span></td>
			</tr>
			<tr id="password">
				<td>Password:</td>
				<td><input type="password" name="password" placeholder="Password" required></td>
				<td><span class="msg"></span></td>
			</tr>
			<tr id="confirmPassword">
				<td>Confirm Password:</td>
				<td><input type="password" name="confirmPassword" placeholder="Confirm Password" required></td>
				<td><span class="msg"></span></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input id="submit" type="submit" value="Submit"></td>
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