<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page isELIgnored="false"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LogIn Page</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="resources/css/Pretty-Footer.css">
  <link rel="stylesheet" href="resources/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css">
   <link rel="stylesheet" href="resources/lib/bootstrap-3.3.7-dist/js/bootstrap.min.js">
   <link rel="stylesheet" href="resources/lib/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<jsp:include page="header.jsp" /> 

	<div class="container" style="height: 450px;">
				<p><font color="red">${error}</font></p>
				<h3>LogIn Form </h3>
				<form action="perform_login"  method="post">
				<br/>
				<br/>
			
				<div class="form-group row">
				 <div class="col-xs-6">
					<label for="username">Email:  </label>
					<input type="email" class="form-control" id="email" name="email"/>
				</div>
				</div>
				<br/>
				<div class="form-group row">
					 <div class="col-xs-6">
					<label for="password">Password: </label>
					<input type="password" class="form-control" id="password"  name="password" maxlength="15" />
					</div>
				</div>
				<br/>
				<button type="submit" class="btn btn-default">Submit</button><br/>	<br/>		
				</form><br/>
	</div>
	

	<jsp:include page="footer.jsp" /> 

</body>
</html>