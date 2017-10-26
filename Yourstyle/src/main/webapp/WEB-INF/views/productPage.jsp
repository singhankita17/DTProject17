<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page isELIgnored="false"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet" href="resources/css/Pretty-Footer.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Add Product Page</title>
</head>
<jsp:include page="header.jsp" /> 
<body>

<div class="container">
	      <form:form commandName="product" method="post" action="saveProduct" enctype="multipart/form-data">
				<p><font color="red">${errorMessage}</font></p>				
				<h4>
				<strong>
			     <c:choose>
			     	<c:when test="${product.id==0}">  Add Product </c:when>
			     	<c:when test="${!empty product.id}">Update Product <br> <br>Product Id: <c:out value="${product.id}"/></c:when>
			     </c:choose>
			     </strong>
			     </h4>
			    		     
				<div class="form-group row">
				 <div class="col-xs-4">
				   <c:if test="${product.id != 0}">
				   <form:input type="hidden" class="form-control" path="id"  placeholder="Product Id" name = "id"/>
				   </c:if>					
				</div>
				</div>
				<br/>
				<div class="form-group row">
					<label for="productName" class="col-xs-4 control-label">Product Name</label>
					<div class="col-xs-4">
						<form:input name="productName" path="productName" placeholder="Product Name" class="form-control" required="required"/>
					</div>
				</div>
				<div class="form-group row">
					<label for="productDesc" class="col-xs-4 control-label">Product Description</label>
					<div class="col-xs-4">
						<form:textarea name="productDesc"  path="productDesc" placeholder="Product Description" class="form-control" />
					</div>
				</div>
				<div class="form-group row">
					<label for="brandName" class="col-xs-4 control-label">Brand Name</label>
					<div class="col-xs-4">
						<form:input name="brandName"  path="brandName" placeholder="Brand Name" class="form-control" required="required"/>
					</div>
				</div>
				<div class="form-group row">
					<label for="price" class="col-xs-4 control-label">Price</label>
					<div class="col-xs-4">
						<form:input name="price"  path="price" placeholder="Price" class="form-control" required="required"/>
					</div>
				</div>
				<div class="form-group row">
					<label for="inStock" class="col-xs-4 control-label">In Stock</label>
					<div class="col-xs-4">
						<form:radiobutton id="inStock"  path="inStock" value="true" label="Yes" checked="checked"></form:radiobutton>
						<form:radiobutton id="inStock"  path="inStock" value="false" label="No"></form:radiobutton>
					</div>
				</div>
				<div class="form-group row">
					<label for="quantityAvailable" class="col-xs-4 control-label">Quantity Available</label>
					<div class="col-xs-4">
						<form:input name="quantityAvailable"  path="quantityAvailable" placeholder="Quantity Available" class="form-control" required="required"/>
					</div>
				</div>
				<div class="form-group row">
					<label for="onSale" class="col-xs-4 control-label">On Sale</label>
					<div class="col-xs-4">
						<form:radiobutton id="onSale"  path="onSale" value="true" label="Yes"></form:radiobutton>
						<form:radiobutton id="onSale"  path="onSale" value ="false" label="No" checked="checked"></form:radiobutton>
					</div>
				</div>
				<div class="form-group row">
					<label for="salePrice" class="col-xs-4 control-label">Sale Price</label>
					<div class="col-xs-4">
						<form:input name="salePrice"  path="salePrice" placeholder="Sale Price" class="form-control" />
					</div>
				</div>
				<div class="form-group row">
					<label for="Product Category" class="col-xs-4 control-label">Product Category</label>
					<div class="col-xs-4">
						<form:select path="categoryId" class="form-control" required="true">
						 <form:option class="form-control"  value="0">---Select---</form:option>	
						 <c:forEach items="${categoryList}" var="category">				 
						 <form:option class="form-control"  value="${category.id}">${category.categoryName}</form:option>
						 </c:forEach>
						</form:select>
					</div>
				</div>
				<div class="form-group row">
					<label for="Product Supplier" class="col-xs-4 control-label">Product Supplier</label>
					<div class="col-xs-4">
						<form:select path="supplierId" class="form-control" required="true">
						 <form:option class="form-control"  value="0">---Select---</form:option>	
						 <c:forEach items="${supplierList}" var="supplier">							 	 
						 <form:option class="form-control"  value="${supplier.id}">${supplier.supplierName}</form:option>
						 </c:forEach>
						</form:select>
					</div>
				</div>
				<div class="form-group row">
					<label for="Product Image" class="col-xs-4 control-label">Product Image</label>
					<div class="col-xs-4">
						<form:input  type ="file" path="productImage" class="form-control" />
					</div>
				</div>
				<br/>
				<div class="form-group row">
					<label for="code" class="col-xs-4 control-label"></label>
						
						<div class="col-xs-4">
						
							
						<c:if test="${product.id==0}">
						<input type="submit" value="Add Product" id="btn-add" class="btn btn-primary" >
						
						</c:if> <c:if test="${product.id!=0}">
						
					   <input type="submit" value="Update Product" id="btn-update" class="btn btn-primary" >
					  </c:if>
					  						
						</div>
					</div>						
		</form:form><br/>
		
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
				<th>Product Action</th>
			</tr>	
			</thead>
			<tbody>
			   <c:forEach items="${productList}" var="product" varStatus="rowCount">
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
			   			<td>
			   			<img src="<c:url value="resources/images/${product.id}.jpg" />" class = "img-thumbnail" width="204px" height="106px"/>
			   			</td>
			   			<td><nobr>
			<a class="btn btn-primary" href="editproduct/${product.id}"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Edit</a>

			<a class="btn btn-primary"  href="removeproduct/${product.id}"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete
								</a>

							</nobr></td>
			   		</tr>
			   </c:forEach>
			</tbody>
		</table>
	</div>


</body>
<jsp:include page="footer.jsp" /> 
</html>