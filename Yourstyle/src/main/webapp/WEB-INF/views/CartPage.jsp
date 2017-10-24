<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
  <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet" href="resources/css/Pretty-Footer.css">
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">   
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
   <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Cart Page</title>

</head>
<jsp:include page="header.jsp" /> 
<body>
	<div class="container">
	<div class="row">
	<div class= "col-xs-6">
	
	<table class="table table-hover table-condensed">
	<c:choose>
	<c:when test="${EmptyCart eq true}">
			<h4>Your Shopping Cart is Empty</h4>
	</c:when>
	
	<c:when test="${not EmptyCart}">
	<c:if test="${not empty error}">
	    	<h4> <font color="red">${error} </font></h4>
	</c:if>
		<thead>
			<tr>
			<th>Product</th>
			<th>Quantity</th>
			<th>Price</th>
			<th>Total</th>
			<th>Action</th>
			</tr>	
		</thead>
		<tbody>
		<c:forEach items="${cartList}" var="cart">
		 <form action="editCartItem/${cart.id}" method="post">
			<tr>
			<td>
			<div>
			<img src="<c:url value="${pageContext.request.pathInfo}/resources/images/${cart.productId}.jpg"/>" class="img-thumbnail" width="200px" height="350px"/>
			</div>	
			</td>
			<td>
			
			<input type="number" name="cartQty" id="cartQuantity" value="${cart.quantityAdded}" min="1">
			
			</td>
			<td>${cart.productPrice}</td>
			<c:set var="subtot" value="${cart.productPrice * cart.quantityAdded}"></c:set>
			<td>${subtot}</td>
			<td><nobr>
 <button type="submit" name = "cartQty" class="btn btn-sm  btn-info"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Edit</button>
 
<br/><br/>
<a class="btn btn-sm btn-danger"  href="removeCartItem/${cart.id}"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> Remove
								</a>
							</nobr></td>
				
		</tr>
		
		<c:set var="gtot" value="${gtot + cart.productPrice * cart.quantityAdded}"></c:set>
		</form>
		</c:forEach>
		<tr>
			<td colspan="3"><h4>Total </h4></td>
			<td colspan="2"><h4>${gtot}</h4></td>
		</tr>
		<tr>
		<td colspan="5" align="right"><a href="${pageContext.request.contextPath}/" class="btn btn-success"> Continue Shopping</a>
		<a href="${pageContext.request.contextPath}/shippingAddress" class="btn btn-warning"> &nbsp; Checkout</a></td>
		</tr>
		</tbody>
		</c:when>
	</c:choose>
	</table>
	
		</div>
	</div>
	</div>
	
</body>
<jsp:include page="footer.jsp" /> 
</html>