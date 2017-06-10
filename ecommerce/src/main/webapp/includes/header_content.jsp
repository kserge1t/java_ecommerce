<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h1 id="msg"><c:out value="${requestScope.notice}">Hello <c:out value="${sessionScope.user.firstName}">Guest</c:out>!</c:out></h1>
<nav id="nav">
	<ul>
	
		<li><a href="index.jsp">Home</a></li>
		<c:if test = "${empty sessionScope.user}">
			<li><a href="index.jsp#login">Login</a></li>
			<li><a href="index.jsp#register">Register</a></li>
		</c:if>
		
		<li><a href="catalog.jsp">Catalog</a></li>
		
		<c:if test = "${not empty sessionScope.user}">
			
			<li><a href="#">Cart</a></li>
		
			<li>
				<a href="" class="icon fa-angle-down">Account</a>
				<ul>
					<li><a href="index.jsp#edit">Edit User</a></li>
					<li><a href="logout">Logout</a></li>
				</ul>
			</li>
			
			<c:if test = "${sessionScope.user.roleId == 1}">
				<li>
					<a href="" class="icon fa-angle-down">Manage</a>
					<ul>
						<li>
							<a href="" class="icon fa-angle-left"> Store</a>
							<ul>
								<li><a href="#">Products</a></li>
								<li><a href="#">Categories</a></li>
							</ul>
						</li>
						<li>
							<a href="" class="icon fa-angle-left"> Accounts</a>
							<ul>
								<li><a href="#">Users</a></li>
								<li><a href="#">Roles</a></li>
							</ul>
						</li>
					</ul>
				</li>
			</c:if>
			
		</c:if>
		
	</ul>
</nav>