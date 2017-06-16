<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US" />
<!DOCTYPE HTML>

<html>
<head>
<title>eCommerce Order History</title>
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
	<header id="header" class="skel-layers-fixed">
		<c:import url="includes/header_content.jsp" />
	</header>

	<!-- Shopping Cart -->
	<section id="cart" class="wrapper style1">
		<div class="container">
		
			<header class="major">
				<h2>Orders Summary</h2>
				<c:if test = "${not empty sessionScope.user}">
					<p>You previously placed <c:out value="${sessionScope.user.getOrders().size()}">no</c:out> orders.</p>
				</c:if>
			</header>
			
			<!-- Login -->
			<c:if test = "${empty sessionScope.user}">
				<section id="login" class="wrapper style2">
					<div id="cta" class="inner">
						<h3>Please login or register to see your orders summary.</h3>
						<ul class="actions">
							<li><a href="index.jsp#login" class="button big scrolly icon fa-user">Login</a></li>
							<li><a href="index.jsp#register" class="button big scrolly icon fa-user-plus">Register</a></li>
						</ul>
					</div>
				</section>
			</c:if>
			
			<c:if test = "${sessionScope.user.getOrders().size() > 0}">
				<div class="table-wrapper">
					<table>
						<thead>
							<tr>
								<th class="align-center">Order ID</th>
								<th class="align-left">Order Date</th>
								<th class="align-right">Order Total</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${sessionScope.user.getOrders()}" var="order">
							<c:set var = "allTotal" scope = "request" value = "${allTotal + order.getOrderTotal()}"/>
								<tr>
									<td class="align-center"><h5>${order.getId()}</h5></td>
									<td><fmt:formatDate type="both" dateStyle="full" value="${order.getOrderDate().getTime()}"/></td>
									<td class="align-right"><fmt:formatNumber value="${order.getOrderTotal()}" type="currency" /></td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<td class="align-right" colspan="2">All orders total:</td>
								<td class="align-right"><strong><em><fmt:formatNumber value="${allTotal}" type="currency" /></em></strong></td>
							</tr>
						</tfoot>
					</table>
				</div>
			</c:if>
		</div>
	</section>
					
	<!-- Footer -->
	<c:import url="includes/footer.jsp" />

</body>
</html>