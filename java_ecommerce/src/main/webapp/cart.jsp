<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US" />
<!DOCTYPE HTML>

<html>
<head>
<title>eCommerce Shopping Cart</title>
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
				<h2>Shopping Cart</h2>
				<p>You have <c:out value="${sessionScope.order.getCartItemsMap().size()}">no</c:out> item(s) in your shopping cart.</p>
			</header>
			
			<c:if test = "${sessionScope.order.getCartItemsMap().size() > 0}">
				<div class="table-wrapper">
					<table class="alt">
						<thead>
							<tr>
								<th class="2u align-center">SKU</th>
								<th class="3u align-center">Item Name</th>
								<th class="1u align-center">Price</th>
								<th class="2u align-center">Quantity</th>
								<th class="2u align-center">Row Total</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${sessionScope.order.getCartItemsMap()}" var="item">
								<tr>
									<td class="align-center"><h5>${item.value.getSku()}</h5></td>
									<td>${item.value.getName()}</td>
									<td class="align-right"><fmt:formatNumber value="${item.value.getPrice()}" type="currency" /></td>
									<td class="align-center">
										<div class="12u">
											<a href="cart?dec-sku=${item.value.getSku()}" class="icon fa-minus-square"></a>
											&nbsp;&nbsp;&nbsp;<b>${item.value.getQuantity()}</b>&nbsp;&nbsp;&nbsp;
											<a href="cart?inc-sku=${item.value.getSku()}" class="icon fa-plus-square"></a>
										</div>
										<div class="12u">
											<a href="cart?rem-sku=${item.value.getSku()}" class="icon fa-remove">&nbsp;Remove</a>
										</div>
									</td>
									<td class="align-right"><b><fmt:formatNumber value="${item.value.getPriceTotal()}" type="currency" /></b></td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="3"  class="align-right">
									<a href="cart?empty" class="button icon fa-remove">Empty Shopping Cart</a>
								</td>
								<td class="align-right">Grand Total:</td>
								<td class="align-right"><strong><em><fmt:formatNumber value="${sessionScope.order.getOrderTotal()}" type="currency" /></em></strong></td>
							</tr>
						</tfoot>
					</table>
				</div>
				
				<!-- Login -->
				<c:if test = "${empty sessionScope.user}">
					<section id="login" class="wrapper style3">
						<div id="cta" class="inner">
							<h3>Please login or register to checkout.</h3>
							<ul class="actions">
								<li><a href="index.jsp#login" class="button big scrolly icon fa-user">Login</a></li>
								<li><a href="index.jsp#register" class="button big scrolly icon fa-user-plus">Register</a></li>
							</ul>
						</div>
					</section>
				</c:if>
				
				<!-- Checkout -->
				<c:if test = "${not empty sessionScope.user}">
					<section id="checkout" class="wrapper style2 align-center">


						<div id="cta" class="inner">
							<h4>Checkout</h4>
							<p><c:out value="${sessionScope.user.firstName}">Guest</c:out>, your available balance is: <b><fmt:formatNumber value="${sessionScope.user.balance}" type="currency" /></b>, order total is: <fmt:formatNumber value="${sessionScope.order.getOrderTotal()}" type="currency" /></p>
							

							
							<c:choose>
								<c:when test="${sessionScope.user.balance >= sessionScope.order.getOrderTotal()}">
									<p>After placing the order your remaining balance will be: <b><fmt:formatNumber value="${sessionScope.user.balance - sessionScope.order.getOrderTotal()}" type="currency" /></b>.</p>
									<ul class="actions">
										<li><a href="cart?submit" class="button big scrolly special icon fa-check-square-o">Submit Order</a></li>
									</ul>
							  	</c:when>
							  	<c:otherwise>
							  		<p>Insufficient amount: <b><i><fmt:formatNumber value="${sessionScope.order.getOrderTotal() - sessionScope.user.balance}" type="currency" /></i></b>.</p>
									<ul class="actions">
										<li><span class="button big scrolly special disabled">Submit Order</span></li>
									</ul>
							  	</c:otherwise>
							</c:choose>
		
						</div>

					</section>
				</c:if>
				
			</c:if>
		</div>
	</section>
					

					
	<!-- Footer -->
	<c:import url="includes/footer.jsp" />

</body>
</html>