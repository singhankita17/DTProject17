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
			</ul>
			<ul class="nav navbar-nav navbar-right">
			<sec:authorize access="isAuthenticated()">
					<li><a href="#">Welcome : <sec:authentication property="name"/></a></li>
					<li><a href="<c:url value="/logout" />">Logout</a></li>
				</sec:authorize>
			</ul>
			</div>
		</div>
	</nav>
	</body>
	</html>