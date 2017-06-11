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
				<h2>Shopping Cart</h2>
				<p>You have <c:out value="${cart.getProductsMap().size()}">no</c:out> item(s) in your shopping cart.</p>
			</header>
			
			<c:if test = "${cart.getProductsMap().size() > 0}">
				<div class="table-wrapper">
					<table class="alt">
						<thead>
							<tr>
								<th class="2u align-center">Image</th>
								<th class="2u align-center">SKU</th>
								<th class="3u align-center">Name</th>
								<th class="1u align-center">Price</th>
								<th class="2u align-center">Quantity</th>
								<th class="2u align-center">Total</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${cart.getProductsMap()}" var="item">
								<tr>
									<td><a href="#" class="image fit"><img	src="${item.value.mainImage}" alt=""/></a></td>
									<td class="align-center"><h5>${item.value.sku}</h5></td>
									<td><a href="#">${item.value.name}</a></td>
									<td class="align-right"><fmt:formatNumber value="${item.value.price}" type="currency" /></td>
									<td class="align-center">
										<div class="12u">
											<a href="cart?inc-sku=${item.value.sku}" class="icon fa-plus-square"></a>
											&nbsp;&nbsp;&nbsp;<b>${item.value.stock}</b>&nbsp;&nbsp;&nbsp;
											<a href="cart?dec-sku=${item.value.sku}" class="icon fa-minus-square"></a>
										</div>
										<div class="12u">
											<a href="cart?rem-sku=${item.value.sku}" class="icon fa-remove">&nbsp;Remove</a>
										</div>
									</td>
									<td class="align-right"><b><fmt:formatNumber value="${item.value.price * item.value.stock}" type="currency" /></b></td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="4"></td>
								<td class="align-right">Grand Total:</td>
								<td class="align-right"><strong><em><fmt:formatNumber value="${cart.getTotal()}" type="currency" /></em></strong></td>
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