<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<!DOCTYPE HTML>

<html>
	<head>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
				<c:import url = "includes/header_content.jsp"/>
			</header>

		<!-- Catalog -->
			<section id="catalog" class="wrapper style1">
				<div class="container">
					<header class="major">
						<h2>Products Catalog</h2>
					</header>
					<div class="row">
						<div class="4u 6u(2) 12u$(3)">
							<article class="box post">
								<a href="#" class="image fit"><img src="images/pic01.jpg" alt="" /></a>
								<h3>Sed amet lorem</h3>
								<p>Ipsum dolor tempus et commodo lorem accumsan et adipiscing blandit porttitor feugiat tempus lorem faucibus.</p>
								<ul class="actions">
									<li><a href="#" class="button">Learn More</a></li>
								</ul>
							</article>
						</div>
						<div class="4u 6u$(2) 12u$(3)">
							<article class="box post">
								<a href="#" class="image fit"><img src="images/pic02.jpg" alt="" /></a>
								<h3>Massa accumsan</h3>
								<p>Ipsum dolor tempus et commodo lorem accumsan et adipiscing blandit porttitor feugiat tempus lorem faucibus.</p>
								<ul class="actions">
									<li><a href="#" class="button">Learn More</a></li>
								</ul>
							</article>
						</div>
						<div class="4u$ 6u(2) 12u$(3)">
							<article class="box post">
								<a href="#" class="image fit"><img src="images/pic03.jpg" alt="" /></a>
								<h3>Faucibus portitor</h3>
								<p>Ipsum dolor tempus et commodo lorem accumsan et adipiscing blandit porttitor feugiat tempus lorem faucibus.</p>
								<ul class="actions">
									<li><a href="#" class="button">Learn More</a></li>
								</ul>
							</article>
						</div>
						<div class="4u 6u$(2) 12u$(3)">
							<article class="box post">
								<a href="#" class="image fit"><img src="images/pic04.jpg" alt="" /></a>
								<h3>Non placerat</h3>
								<p>Ipsum dolor tempus et commodo lorem accumsan et adipiscing blandit porttitor feugiat tempus lorem faucibus.</p>
								<ul class="actions">
									<li><a href="#" class="button">Learn More</a></li>
								</ul>
							</article>
						</div>
						<div class="4u 6u(2) 12u$(3)">
							<article class="box post">
								<a href="#" class="image fit"><img src="images/pic05.jpg" alt="" /></a>
								<h3>Adipiscing dolor</h3>
								<p>Ipsum dolor tempus et commodo lorem accumsan et adipiscing blandit porttitor feugiat tempus lorem faucibus.</p>
								<ul class="actions">
									<li><a href="#" class="button">Learn More</a></li>
								</ul>
							</article>
						</div>
						<div class="4u$ 6u$(2) 12u$(3)">
							<article class="box post">
								<a href="#" class="image fit"><img src="images/pic06.jpg" alt="" /></a>
								<h3>Feugiat tempus</h3>
								<p>Ipsum dolor tempus et commodo lorem accumsan et adipiscing blandit porttitor feugiat tempus lorem faucibus.</p>
								<ul class="actions">
									<li><a href="#" class="button">Learn More</a></li>
								</ul>
							</article>
						</div>
					</div>
				</div>
			</section>
			
		<!-- Footer -->
		<c:import url = "includes/footer.jsp"/>

	</body>
</html>