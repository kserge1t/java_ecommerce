<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<footer id="footer">
	<ul class="menu">
		<li><a href="index.jsp">Home</a></li>
		<li><a href="catalog.jsp">Catalog</a></li>
		<c:if test = "${empty sessionScope.user}">
			<li><a href="index.jsp#login">Login</a></li>
			<li><a href="index.jsp#register">Register</a></li>
		</c:if>
		<c:if test = "${not empty sessionScope.user}">
			<li><a href="logout">Logout</a></li>
		</c:if>
	</ul>
	<span class="copyright">
		&copy; Copyright. All rights reserved. Design by <a href="http://www.html5webtemplates.co.uk">Responsive Web Templates</a>
	</span>
</footer>