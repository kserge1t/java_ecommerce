<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<title>Homepage</title>
</head>
<body>
	<h1>Welcome!</h1>
	<h3><c:out value="${requestScope.notice}"></c:out></h3>
	<ul>
		<li><a href="register.jsp">Register</a>
		<li><a href="login.jsp">Log-In</a>
	</ul>
</body>
</html>
