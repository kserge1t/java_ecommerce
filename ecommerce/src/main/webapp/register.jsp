<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>New User Registration</title>
</head>
<body>
	<h1>New User Registration Form:</h1>
	<strong><c:out value="${requestScope.notice}"></c:out></strong>
	<form action="register" method="post">
		<table>
			<tr>
				<td>First Name:</td>
				<td><input type="text" name="firstName"
					placeholder="First Name"></td>
			</tr>
			<tr>
				<td>Last Name:</td>
				<td><input type="text" name="lastName" placeholder="Last Name"></td>
			</tr>
			<tr>
				<td>eMail:</td>
				<td><input type="text" name="email" placeholder="eMail"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password"
					placeholder="Password"></td>
			</tr>
			<tr>
				<td>Confirm Password:</td>
				<td><input type="password" name="confirmPassword"
					placeholder="Confirm Password"></td>
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