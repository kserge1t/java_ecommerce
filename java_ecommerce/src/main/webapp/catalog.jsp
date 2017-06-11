<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="en_US" />
<!DOCTYPE HTML>

<html>
<head>
<title>eCommerce Catalog</title>
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

	<!-- Catalog -->
	<section id="catalog" class="wrapper style1">
		<div class="container">
			<header class="major">
				<h2>Products Catalog</h2>
				<p>Total products: <c:out value="${products.size()}">0</c:out></p>
			</header>
			<div class="container row">
				<c:forEach items="${products}" var="product">
					<div class="4u 6u(2) 12u$(3)">
						<article class="box post">
							<a href="#" class="image fit"><img	src="${product.mainImage}" alt=""/></a>
							<h3>${product.name}</h3>
							<div class="row">
								<div class="6u 12u(2)">
									<h4>SKU: ${product.sku}</h4>
								</div>
								<div class="6u 12u(2)">
									<strong><fmt:formatNumber value="${product.price}" type="currency" /></strong>
								</div>
								<div class="6u 12u(2)">
									<strong>
										<c:choose>
											<c:when test="${product.stock ge 10}">
										    	In Stock
											</c:when>
											<c:when test="${product.stock ge 1}">
										    	Only ${product.stock} Left!
											</c:when>
											<c:otherwise>
										    	Out of Stock!
											</c:otherwise>
										</c:choose>
									</strong>
								</div>
							</div>
							<p>${product.description}</p>
							<ul class="actions small fit">
								<li><a href="#" class="button fit">Details</a></li>
								<c:choose>
									<c:when test="${(product.stock le 0)}">
								    	<li><span class="button disabled fit">No Stock</span></li>
									</c:when>
									<c:when test="${(not product.active)}">
								    	<li><span class="button disabled fit">Inactive</span></li>
									</c:when>
									<c:otherwise>
										<li><a href="cart?add=${product.sku}" class="button special fit">To Cart</a></li>
									</c:otherwise>
								</c:choose>
							</ul>
						</article>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>

	<!-- Footer -->
	<c:import url="includes/footer.jsp" />

</body>
</html>