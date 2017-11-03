<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page isELIgnored="false"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/Pretty-Footer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/PageDisplay.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  
<title>Access Denied Page</title>
</head>
<jsp:include page="header.jsp" /> 
<body>
<div id="wrap">
<div id="main" class="container clear-top">
<div align="center"  style="padding-top: 75px; padding-left: 75px;">
<!-- <h4><font color="red">You are not Authorized to view this page...</font></h4> -->
<img src="${pageContext.request.contextPath}/resources/images/unauthorized_error.jpg" alt="Image" class="img-thumbnail">
</div>

</div>
</div>
</body>
<jsp:include page="footer.jsp" />
</html>