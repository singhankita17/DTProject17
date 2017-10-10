<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page isELIgnored="false"%>
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
<title>Welcome to Home Page</title>
</head>
<jsp:include page="header.jsp" /> 
<body>
<div class="container" style="height: 450px;">
Welcome user <sec:authentication property="name"/> !!!


		<table class="table table-striped table-bordered" style="width:80%">
			<thead>
			<tr>
				<th>S. No.</th>
				<th>Product Id</th>
				<th>Product Name</th>
				<th>Product Description</th>
				<th>Brand Name</th>
				<th>Price</th>
				<th>In Stock</th>
				<th>Quantity</th>
				<th>OnSale</th>
				<th>Sale Price</th>
				<th>Product Image</th>
				
			</tr>	
			</thead>
			<tbody>
			   <c:forEach items="${products}" var="product" varStatus="rowCount">
			   		<tr>
			   			<td><c:out value="${rowCount.count}"></c:out></td>
			   			<td><c:out value="${product.id}"></c:out></td>
			   			<td><c:out value="${product.productName}"></c:out></td>
			   			<td><c:out value="${product.productDesc}"></c:out></td>
			   			<td><c:out value="${product.brandName}"></c:out></td>
			   			<td><c:out value="${product.price}"></c:out></td>
			   			<td><c:out value="${product.inStock}"></c:out></td>
			   			<td><c:out value="${product.quantityAvailable}"></c:out></td>
			   			<td><c:out value="${product.onSale}"></c:out></td>
			   			<td><c:out value="${product.salePrice}"></c:out></td>
			   			<!-- Added in Multipart changes for image display -->
			   			<td><img src="resources/images/${product.id}.jsp" class="img-responsive" style="width:100%"/></td>
			   		</tr>
			   </c:forEach>
			</tbody>
		</table>
</div>
</body>
<jsp:include page="footer.jsp" /> 
</html>