<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/Pretty-Footer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/PageDisplay.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Acknowledgement</title>
</head>
<jsp:include page="header.jsp" /> 
<body>
<div id="wrap">
<div id="main" class="container clear-top">

<h4><font color="blue">Your order has been placed successfully. Thank you for shopping with us.</font> </h4>

<div class="row">
	 <div class="col-sm-12 col-md-10 col-md-offset-1">
	
	<table class="table table-hover table-condensed">
	
	<c:if test="${not empty error}">
	    	<h4> <font color="red">${error} </font></h4>
	</c:if>
	
		<thead>
			<tr>
			<th>Product</th>
			<th>Expected Delivery</th>
			<th>Quantity</th>
			<th class="text-center">Price</th>
			<th class="text-center">Total</th>
			
			</tr>	
		</thead>
		<tbody>
			<c:forEach items="${cartList}" var ="cart">
			<tr>
			 <td class="col-sm-8 col-md-6">
			<div class="media">
			 <a class="thumbnail pull-left" href="${pageContext.request.contextPath}/productDetail/${cart.productId}"><img class="media-object" src="<c:url value="/resources/images/${cart.productId}.jpg"/>" class="img-thumbnail" width="72px" height="72px"/></a>
					
			<div class="media-body">
			<c:forEach items="${productList}" var="product">	
				<c:if test="${product.id == cart.productId}">
			<h4 class="media-heading"><a href="${pageContext.request.contextPath}/productDetail/${cart.productId}">${product.productName}</a></h4>
                                <h5 class="media-heading"> by <a href="#">${product.brandName}</a></h5>
                                <span>Status: </span><span class="text-success"><strong>In Stock</strong></span>
                            </div>
                        </div></td>
                 </c:if>
              </c:forEach>
             <td class="col-sm-1 col-md-1">
             With in 7 working days
 			 </td>
			 <td class="col-sm-1 col-md-1" style="text-align: center">
			
			${cart.quantityAdded}
			
			</td>
			 <td class="col-sm-1 col-md-1 text-center"><strong><span class="fa fa-inr"></span> ${cart.productPrice}</strong></td>
			<c:set var="subtot" value="${cart.productPrice * cart.quantityAdded}"></c:set>
			 <td class="col-sm-1 col-md-1 text-center"><strong><span class="fa fa-inr"></span> ${subtot}</strong></td>
			
				
		</tr>
		
		<c:set var="gtot" value="${gtot + cart.productPrice * cart.quantityAdded}"></c:set>
		</c:forEach>
		<tr>
			<td>  </td>
			<td>  </td>
			<td>  </td>
			<td><h5>SubTotal</h5></td>
			<td class="text-right"><h5><strong><span class="fa fa-inr"></span> ${gtot}</strong></h5></td>
		</tr>
		<tr>
             <td>   </td>
             <td>   </td>
             <td>   </td>
             <td><h5>Estimated shipping</h5></td>
             <td class="text-right"><h5><strong><span class="fa fa-inr"></span> 50.0</strong></h5></td>
         </tr>
         <c:set var="grosstotal" value="${gtot+50}"></c:set>
          <tr>
              <td>   </td>
              <td>   </td>
              <td><h3>Total</h3></td>
              <td colspan="2" class="text-right"><h3><strong><span class="fa fa-inr"></span>${grosstotal}</strong></h3></td>
          </tr>
		
		</tbody>
		
	</table>
	
		</div>
	</div>


</div>
</div>
</body>
</html>