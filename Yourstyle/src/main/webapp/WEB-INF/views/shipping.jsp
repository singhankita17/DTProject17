<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>   
  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="resources/css/Pretty-Footer.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Shipping Address</title>
</head>
<jsp:include page="header.jsp" /> 
<body>
<div class="container">
	
	<form:form commandName="address" action="saveShippingAddress" method="post">
		<div class="form-group row">
		<div class= "col-xs-6">
		<label for="name"> Full Name: </label>
		<form:input class="form-control" name="name" path ="name" required="required"/>
		</div>
		</div>
		
		 <div class="form-group row">
			<div class="col-xs-6">
		    <label for="address1"> Flat / House No. / Floor / Building:  </label>
		    <form:input  class="form-control" name="address1"  path = "address1" required="required"/>
		    </div>
		 </div>
		 
		 <div class="form-group row">
			<div class="col-xs-6">
		    <label for="address2"> Colony / Street / Locality:   </label>
		    <form:input  class="form-control" name="address2"  path = "address2" required="required"/>
		    </div>
		 </div>
		 
		 <div class="form-group row">
			<div class="col-xs-6">
		    <label for="landmark"> Landmark: 
									(optional)   </label>
		    <form:input  class="form-control" name="landmark"  path = "landmark" />
		    </div>
		 </div>
		 
		  <div class="form-group row">
			<div class="col-xs-6">
		    <label for="city"> Town / City: </label>
		    <form:input  class="form-control" name="city"  path = "city" required="required"/>
		    </div>
		 </div>
		 
		 
		  <div class="form-group row">
			<div class="col-xs-6">
		    <label for="state"> State:   </label>
		    <form:input  class="form-control" name="state"  path = "state" required="required"/>
		    </div>
		 </div>
		 
		  <div class="form-group row">
			<div class="col-xs-6">
		    <label for="pincode"> Pincode:   </label>
		    <form:input  class="form-control" name="pincode"  path = "pincode" required="required"/>
		    </div>
		 </div>
		 
		 <div class="form-group row">
			<div class="col-xs-6">
		    <label for="phone"> Alternate Mobile: 
									(optional)   </label>
		    <form:input  class="form-control" name="phone"  path = "phone" />
		    </div>
		 </div>
		 
		 <div class="form-group row">
			<div class="col-xs-6">
		    <label for="email"> Alternate Email: 
									(optional)   </label>
		    <form:input type="email" class="form-control" name="email"  path = "email" />
		    </div>
		 </div>
		 
		 <div class="form-group row">
			<div class="col-xs-6">
		    <label for="addressType"> Address Type:   </label>
		   				<form:select path="addressType" class="form-control" required="true">
						 <form:option class="form-control"  value="0">---Select---</form:option>	
						 <form:option class="form-control"  value="HOME">Home (All day Delivery)</form:option>			 	 
						 <form:option class="form-control"  value="OFFICE">Office (Delivery between 10 AM and 5 PM )</form:option>
						</form:select>
		    </div>
		 </div>
		 
		 <div class="form-group row">
			<div class="col-xs-6">
		    <div class="clearfix">
		      <button type="submit" class="btn btn-success">Save & Deliver Here</button>
		      <button type="reset"  class="btn btn-danger">Cancel</button>		     
		    </div>
		    </div>
		 </div>
		 
	</form:form>
	</div>

</body>
<jsp:include page="footer.jsp" /> 
</html>