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
   <link rel="stylesheet" href="resources/css/PageDisplay.css">
  <link rel="stylesheet" href="resources/lib/bootstrap-3.3.7-dist/css/bootstrap.min.css">
   <link rel="stylesheet" href="resources/lib/bootstrap-3.3.7-dist/js/bootstrap.min.js">
   <link rel="stylesheet" href="resources/lib/bootstrap-3.3.7-dist/css/bootstrap-theme.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <style type="text/css">
  .vertical-offset-100{
    padding-top:100px;
}
  </style>
</head>
<body>
	<jsp:include page="headercart.jsp" /> 

<div id="wrap">
<div id="main" class="container clear-top">

    <div class="row vertical-offset-100">
		<div class="col-md-4 col-md-offset-4">
    		<div class="panel panel-default">
			  	<div class="panel-heading">
			    	<h3 class="panel-title">Please sign in</h3>
			 	</div>
			  	<div class="panel-body">
			  	<p><font color="red">${error}</font></p>
			    	<form action="perform_login"  method="post">
                    <fieldset>
			    	  	<div class="form-group">
			    		    <input class="form-control" placeholder="E-mail" name="email"  id="email" type="email">
			    		</div>
			    		<div class="form-group">
			    			<input class="form-control" placeholder="Password" type="password" id="password"  name="password" maxlength="15"  value="">
			    		</div>
			    		
			    		<input class="btn btn-lg btn-success btn-block" type="submit" value="Login">
			    	</fieldset>
			      	</form>
			    </div>
			</div>
		</div>
	</div>

<!-- old code -->
			<%-- 	<p><font color="red">${error}</font></p>
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
				</form><br/> --%>
	</div>
	
</div>
	<jsp:include page="footer.jsp" /> 

</body>
</html>