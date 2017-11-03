<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page isELIgnored="false"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>

<!-- <title>Online Site for all your beauty needs - YourStyle.Com</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="resources/css/Pretty-Footer.css">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">   
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 -->
</head>
<body>
	<nav class="navbar navbar-inverse navbar-static-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#ysNavbar" >
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/"><img alt="YourStyle"  style="max-width:70px; margin-top: -7px;" src="${pageContext.request.contextPath}/resources/images/logo.png"></a>
			</div>
			<div class="collapse navbar-collapse" id="ysNavbar">
			<ul class="nav navbar-nav">
				<li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
				<sec:authorize access="hasRole('ROLE_USER')|| isAnonymous()">
				<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Shop Categories<span class="caret"></span></a>
				<ul class="dropdown-menu">
				    <c:forEach items="${categoryList}" var="category">
						<li><a href="${pageContext.request.contextPath}/fetchByCategory/${category.id}">${category.categoryName}</a></li>
					</c:forEach>
				</ul>
				</li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Admin<span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="${pageContext.request.contextPath}/categoryPage"> Category </a></li>
					<li><a href="${pageContext.request.contextPath}/productPage"> Product</a></li>
					<li><a href="${pageContext.request.contextPath}/supplierPage"> Supplier</a></li>
				</ul>
				</li>
				</sec:authorize>
				<%-- 
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<a href="${pageContext.request.contextPath}/adminAdd">Admin Action<span class="caret"></span></a>
				</sec:authorize> --%>
			</ul>
			
			<form class="navbar-form navbar-right" action="${pageContext.request.contextPath}/searchProduct" method="post">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search" name="searchString">
					<button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span></button>
				</div>
			</form>
			
			<ul class="nav navbar-nav navbar-right">	
			 <sec:authorize access="isAnonymous()">	    
				   <li><a href="${pageContext.request.contextPath}/signup"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>				
				
				   <li><a href="${pageContext.request.contextPath}/login"><span class="glyphicon glyphicon-log-in"></span> LogIn</a></li>
				 </sec:authorize>  
				<sec:authorize access="isAuthenticated()">
					<li><a href="#">Welcome : <sec:authentication property="name"/></a></li>
					<li><a href="<c:url value="/logout" />">Logout</a></li>
				</sec:authorize>
				 <li><a href="${pageContext.request.contextPath}/goToCart"><span class="glyphicon glyphicon-shopping-cart my-cart-icon"><span class="badge badge-notify my-cart-badge"></span></span> Cart</a></li>
			</ul>
		  </div>
		</div>
	</nav>
	</body>
	</html>