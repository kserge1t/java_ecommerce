<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!DOCTYPE HTML>

<html>
	<head>
		<title>eCommerce Homepage</title>
		<meta http-equiv="content-type" content="text/html; charset=utf-8" />
		<meta name="description" content="" />
		<meta name="keywords" content="" />
		<!--[if lte IE 8]><script src="css/ie/html5shiv.js"></script><![endif]-->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		<script src="js/jquery.min.js"></script>
		<script src="js/jquery.dropotron.min.js"></script>
		<script src="js/jquery.scrollgress.min.js"></script>
		<script src="js/jquery.scrolly.min.js"></script>
		<script src="js/jquery.slidertron.min.js"></script>
		<script src="js/skel.min.js"></script>
		<script src="js/skel-layers.min.js"></script>
		<script src="js/init.js"></script>
		<script type="text/javascript" src="js/form-validator.js"></script>
		<noscript>
			<link rel="stylesheet" href="css/skel.css" />
			<link rel="stylesheet" href="css/style.css" />
			<link rel="stylesheet" href="css/style-xlarge.css" />
		</noscript>
		<!--[if lte IE 9]><link rel="stylesheet" href="css/ie/v9.css" /><![endif]-->
		<!--[if lte IE 8]><link rel="stylesheet" href="css/ie/v8.css" /><![endif]-->
	</head>
	<body class="landing">

		<!-- Header -->
			<header id="header" class="alt skel-layers-fixed">
				<c:import url = "includes/header_content.jsp"/>
			</header>

		<!-- Banner -->
			<section id="banner">
				<div class="inner">
					<h2>Welcome <c:out value="${sessionScope.user.firstName}"></c:out></h2>
					
					<c:if test = "${empty sessionScope.user}">
					<p>Please login or register.</p>
					<ul class="actions">
						<li><a href="#login" class="button big scrolly">Login</a></li>
						<li><a href="#register" class="button big scrolly">Register</a></li>
					</ul>
					</c:if>
					
				</div>
			</section>

		<!-- Login Form -->
		<c:if test = "${empty sessionScope.user}">
			<section id="login" class="wrapper style1">
				<div class="container">
					<header class="major">
						<h2>Existing users login form</h2>
						<p>Please login using your email and password.</p>
						<form name="loginForm" action="login" onsubmit="return validate_form()" method="post">
						
							<div class="row uniform">
								<div class="12u">
									<ul class="actions">
										<li><input type="text" name="email" placeholder="eMail" required></li>
										<li><input type="password" name="password" placeholder="Password" required></li>
									</ul>
								</div>
							</div>
							
							<div class="row uniform">
								<div class="12u">
									<ul class="actions">
										<li><input type="submit" value="Login" disabled></li>
										<li><input type="reset" value="Clear" class="alt" /></li>
									</ul>
								</div>
							</div>
							
						</form>
					</header>
				</div>
			</section>
		</c:if>
			
		<!-- Register Form -->
		<c:if test = "${empty sessionScope.user}">
			<section id="register" class="wrapper style1">
				<div id="cta" class="container">
					<h2>New user registration form</h2>
					<p>Please fill all the fields.</p>
					
					<form name="registerForm" action="register" onsubmit="return validateUserForm()" method="post">
					
						<div class="row uniform">
							<div class="6u 12u(3)">
								<input type="text" name="firstName" placeholder="First Name" required>
							</div>
							<div class="6u 12u(3)">
								<input type="text" name="lastName" placeholder="Last Name" required>
							</div>
						</div>
						
						<div class="row uniform">
							<div class="12u">
								<input class="email" id="err" type="email" name="email" placeholder="eMail" required>
							</div>
						</div>
						
						<div class="row uniform">
							<div class="6u 12u(3)">
								<input class="password" id="err" type="password" name="password" placeholder="Password" required>
							</div>
							<div class="6u 12u(3)">
								<input class="confirmPassword" id="err" type="password" name="confirmPassword" placeholder="Confirm Password" required>
							</div>
						</div>
						
						<div class="row uniform">
							<div class="12u">
								<ul class="actions">
									<li><input type="submit" value="Register" disabled></li>
									<li><input type="reset" value="Clear" class="alt" /></li>
								</ul>
							</div>
						</div>
					
					</form>
				</div>
			</section>
		</c:if>
		
		<!-- Edit Form -->
		<c:if test = "${not empty sessionScope.user}">
			<section id="edit" class="wrapper style2">
				<div id="cta" class="container">
					<h2>Edit user</h2>
					<p>Input values to change</p>

					<form name="editForm" action="user-edit" method="post">
					
						<input type="hidden" name="userId" value="<c:out value="${sessionScope.user.id}"></c:out>">
						
						<div class="row uniform">
							<div class="12u">
								<ul class="actions">
									<li>First Name:</li>
									<li><input type="text" name="firstName" placeholder="<c:out value="${sessionScope.user.firstName}"></c:out>"></li>
								</ul>
							</div>
						</div>
						
						<div class="row uniform">
							<div class="12u">
								<ul class="actions">
									<li>Last Name:</li>
									<li><input type="text" name="lastName" placeholder="<c:out value="${sessionScope.user.lastName}"></c:out>"></li>
								</ul>
							</div>
						</div>
						
						<div class="row uniform">
							<div class="12u">
								<ul class="actions">
									<li>eMail:</li>
									<li><input class="email" type="email" name="email" placeholder="<c:out value="${sessionScope.user.email}"></c:out>"></li>
								</ul>
							</div>
						</div>
						
						<c:if test="${user.roleId == 1}">
						
							<div class="row uniform">
								<div class="12u">
									<ul class="actions">
										<li>Balance:</li>
										<li><input type="text" name="balance" placeholder="<c:out value="${sessionScope.user.balance}"></c:out>"></li>
									</ul>
								</div>
							</div>
							
							<div class="row uniform">
								<div class="12u">
									<ul class="actions">
										<li>Role ID:</li>
										<li><input type="text" name="roleId" placeholder="<c:out value="${sessionScope.user.roleId} (${sessionScope.user.roleName})"></c:out>"></li>
									</ul>
								</div>
							</div>
							
						</c:if>
						
						<div class="row uniform">
							<div class="12u">
								<ul class="actions">
									<li>Set New Password:</li>
									<li><input class="password" type="password" name="password" placeholder="New Password"></li>
								</ul>
							</div>
						</div>
						
						<div class="row uniform">
							<div class="12u">
								<ul class="actions">
									<li>Confirm Password:</li>
									<li><input class="confirmPassword" type="password" name="confirmPassword" placeholder="Confirm Password"></li>
								</ul>
							</div>
						</div>
						
						<div class="row uniform">
							<div class="12u">
								<input type="checkbox" id="delete" name="delete">
								<label for="delete"> Delete Account!</label>
							</div>
						</div>
						
						<div class="row uniform">
							<div class="12u">
								<ul class="actions">
									<li><input type="submit" value="Confirm"></li>
									<li><input type="reset" value="Clear" class="alt" /></li>
								</ul>
							</div>
						</div>
						
					</form>

				</div>
			</section>
		</c:if>
			
		<!-- Footer -->
		<c:import url = "includes/footer.jsp"/>

	</body>
</html>