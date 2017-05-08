<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>Please Login</title>
</head>
<body>
	<h1>Login Page</h1>
	<strong><c:out value="${requestScope.notice}"></c:out></strong>
	<form action="authenticate" method="post">
		<table>
			<tr>
				<td>eMail:</td>
				<td><input type="text" name="email" placeholder="eMail"></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" placeholder="Password"></td>
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