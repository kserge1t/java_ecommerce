<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="date" class="java.util.Date" />
<footer id="footer">
	<ul class="menu">
		<li><a href="index.jsp" class="icon fa-home">&nbsp;Home</a></li>
		<li><a href="catalog" class="icon fa-book">&nbsp;Catalog</a></li>
		<c:if test = "${empty sessionScope.user}">
			<li><a href="index.jsp#login" class="icon fa-user">&nbsp;Login</a></li>
			<li><a href="index.jsp#register" class="icon fa-user-plus">&nbsp;Register</a></li>
		</c:if>
		<c:if test = "${not empty sessionScope.user}">
			<li><a href="logout" class="icon fa-sign-out">&nbsp;Logout</a></li>
		</c:if>
	</ul>
	<span class="copyright">
		&copy; Copyright <fmt:formatDate value="${date}" pattern="yyyy" />. All rights reserved &nbsp; <a href="https://github.com/serj86/java_ecommerce" target="_blank" class="icon fa-github">&nbsp;&nbsp;serj86</a>. Design by <a href="http://www.html5webtemplates.co.uk">Responsive Web Templates</a>
	</span>
</footer>