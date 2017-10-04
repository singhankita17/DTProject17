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
				<a class="navbar-brand" href="/Yourstyle/"><img alt="YourStyle"  style="max-width:70px; margin-top: -7px;" src="resources/images/logo.png"></a>
			</div>
			<div class="collapse navbar-collapse" id="ysNavbar">
			<ul class="nav navbar-nav">
				<li class="active"><a href="/Yourstyle/">Home</a></li>
				<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Categories<span class="caret"></span></a>
				<ul class="dropdown-menu">
				    <c:forEach items="${categoryList}" var="category">
						<li><a href="#">${category.categoryName}</a></li>
					</c:forEach>
				</ul>
				</li>
				<sec:authorize access="hasRole('ROLE_ADMIN')">
				<li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Admin<span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="/Yourstyle/categoryPage">Add Category</a></li>
					<li><a href="/Yourstyle/productAddPage">Add Product</a></li>
					<li><a href="/Yourstyle/supplierPage">Add Supplier</a></li>
				</ul>
				</li>
				</sec:authorize>
			</ul>
			<form class="navbar-form navbar-right">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search">
					<button type="submit" class="btn btn-default">Go <span class="glyphicon glyphicon-search"></span></button>
				</div>
			</form>
			<ul class="nav navbar-nav navbar-right">		    
				   <li><a href="/Yourstyle/signup"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>				
				<sec:authorize access="isAnonymous()">
				   <li><a href="login"><span class="glyphicon glyphicon-log-in"></span> LogIn</a></li>
				 </sec:authorize>  
				<sec:authorize access="isAuthenticated()">
					<li><a href="<c:url value="/logout" />">Logout</a></li>
				</sec:authorize>
			</ul>
		  </div>
		</div>
	</nav>
	</body>
	</html>