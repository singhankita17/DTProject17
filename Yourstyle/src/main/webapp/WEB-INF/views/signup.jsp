<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page isELIgnored="false"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 5 Transitional//EN" "http://www.w3.org/TR/html5/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up Page</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="resources/css/Pretty-Footer.css">
  <link rel="stylesheet" href="resources/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css">
   <link rel="stylesheet" href="resources/lib/bootstrap-3.3.7-dist/js/bootstrap.min.js">
   <link rel="stylesheet" href="resources/lib/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<jsp:include page="header.jsp" /> 

	<form:form commandName="user" action="savesignup" method="post">	
		  <div class="container">
		  <p><font color="red">${errorMessage}</font></p>
		  <div class="form-group row">
			<div class="col-xs-6">
		  	<label for="firstName">First Name: </label>
		    <form:input class="form-control" name="firstName" placeholder="Enter FirstName" path ="firstName" required="required"/>
		    </div>
		   </div>
		    
		   <div class="form-group row">
			 <div class="col-xs-6">
		    <label for="lastName">Last Name: </label>
		    <form:input class="form-control" name="lastName" placeholder="Enter LastName" path ="lastName" required="required"/>
		</div>
		</div>
		
		<div class="form-group row">
				 <div class="col-xs-6">			 
		     <label for="phone">Mobile Number : </label>
		     <form:input class="form-control" name="phone" placeholder="Enter Mobile Number" path = "phone" maxlength="10"/>
		    </div>
		    </div>
		    
		     <div class="form-group row">
				 <div class="col-xs-6">
		    <label for="email">Email: </label>
		    <form:input type="email" class="form-control" name="email"  placeholder="Enter Email" path = "email" required="required"/>
		    </div>
		    </div>
		    
		    <div class="form-group row">
				 <div class="col-xs-6">
		    <label for="password">Password: </label>
		    <form:input type="password" class="form-control" path="password"  placeholder="Enter Password"  name="password" required="required"/>
		    </div>
		    </div>
			
			<div class="form-group row">
				 <div class="col-xs-6">		
		    <label for="passwordRepeat">Repeat Password: </label>
		    <input type="password" class="form-control"  placeholder="Enter Repeat Password"  name="passwordRepeat" required="required"/>
		    </div>
		    </div>
		    <br/>
		    <p>By creating an account you agree to our <a href="#">Terms and Privacy</a>.</p>
			<br/>
			<div class="form-group row">
				 <div class="col-xs-6">
		    <div class="clearfix">
		      <button type="submit" class="btn btn-success">Sign Up</button>
		      <button type="reset"  class="btn btn-danger">Cancel</button>		     
		    </div>
		    </div>
		    </div>
		  </div>
	</form:form>
	

	<jsp:include page="footer.jsp" /> 

</body>
</html>