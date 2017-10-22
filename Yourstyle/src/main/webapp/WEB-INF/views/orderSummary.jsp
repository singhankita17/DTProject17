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
<title>Order Summary</title>
</head>
<jsp:include page="header.jsp" /> 
<body>

<div class="container">
<div class = "row">
      <div class = "col-xs-8">
      	<div class = "col-xs-5">
      		<h4>Shipping Address:</h4><br>
      		<p> ${address.name}<br/>
      		${address.address1}
      		<br/>${address.address2}
      		<br/>${address.landmark}
      		 <p>${address.city} &nbsp; ${address.state}
      		 <br/> Pincode : ${address.pincode}
      		<br/> Phone: <c:choose><c:when test="${not empty address.phone}">${address.phone}</c:when>
      		<c:when test="${empty address.phone}">${sessionScope.user.phone}</c:when>
      		</c:choose>
      	</div>
      	<div class = "col-xs-3">
      	 <h4>Payment Method </h4>
      	 <p>Cash on Delivery</p>
      	</div>
      </div>
      
      <div class= "col-xs-4">
      
      	 <div class ="jumbotron">
      	 <a class="btn btn-warning" href="">Place Order</a><br><br/>
      	 <p>Order Summary</p>
      	 <h5> Items Subtotal : ${cartTotalAmount}</h5>
      	 <h5> Delivery Charges: 50.0 </h5>
      	 <hr/>
      	 <c:set var="ordertot" value="${cartTotalAmount + 50}"></c:set>
      	 <p>Order Total : ${ordertot}</p>
      
      	 </div>
      </div>
  </div>
  </div>
</body>
<jsp:include page="footer.jsp" /> 
</html>