<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${empty user}">
	<jsp:forward page="login.jsp" />
</c:if>
<title>User Edit</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript" src="js/UserFormValidator.js"></script>

</head>
<body>
	<h1>User Editing:</h1>
	<h3>
		<c:out value="${requestScope.notice}"></c:out>
	</h3>
	<!-- onsubmit="return validateUserForm()" -->
	<form name="userForm" action="user-edit" method="post">
		<input type="hidden" name="userId" value="<c:out value="${user.id}"></c:out>">
		<table>
			<tr id="firstName">
				<td>First Name:</td>
				<td><input type="text" name="firstName" placeholder="<c:out value="${user.firstName}"></c:out>"></td>
				<td><span class="msg"></span></td>
			</tr>
			<tr id="lastName">
				<td>Last Name:</td>
				<td><input type="text" name="lastName" placeholder="<c:out value="${user.lastName}"></c:out>"></td>
				<td><span class="msg"></span></td>
			</tr>
			<tr id="email">
				<td>eMail:</td>
				<td><input type="email" name="email" placeholder="<c:out value="${user.email}"></c:out>"></td>
				<td><span class="msg"></span></td>
			</tr>
			<c:if test="${user.roleId == 1}">
				<tr id="balance">
					<td>Balance:</td>
					<td><input type="text" name="balance" placeholder="<c:out value="${user.balance}"></c:out>"></td>
					<td><span class="msg"></span></td>
				</tr>
				<tr id="roleId">
					<td>Role ID:</td>
					<td><input type="text" name="roleId" placeholder="<c:out value="${user.roleId} (${user.roleName})"></c:out>"></td>
					<td><span class="msg"></span></td>
				</tr>
			</c:if>
			<tr id="password">
				<td>Set New Password:</td>
				<td><input type="password" name="password" placeholder="New Password"></td>
				<td><span class="msg"></span></td>
			</tr>
			<tr id="confirmPassword">
				<td>Confirm Password:</td>
				<td><input type="password" name="confirmPassword" placeholder="Confirm Password"></td>
				<td><span class="msg"></span></td>
			</tr>
			<tr id="delete">
				<td colspan="2" align="center"><input type="checkbox" name="delete"> Delete Account!</td>
				<td><span class="msg"></span></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input id="submit" type="submit" value="Submit Changes"></td>
			</tr>
		</table>
	</form>
	<p>
	<ul>
		<a href="index.jsp">Back to Homepage</a>
	</ul>
	<ul>
		<a href="logout">Logout</a>
	</ul>
	</p>
</body>
</html>