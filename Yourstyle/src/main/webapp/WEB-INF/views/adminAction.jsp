<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page isELIgnored="false"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Admin Action - YourStyle.Com</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="resources/css/Pretty-Footer.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<jsp:include page="header.jsp" /> 
<body>
<div class="container">
<div class="page-header"><h5>Admin Page</h5></div>
<div class="container">
	<div class="row">
		<div class="col-md-6">
			<div class="panel with-nav-tabs panel-primary">
			<div class="panel-heading">
				 <ul class="nav nav-tabs">
				    <li class="active"><a data-toggle="tab" href="#tab1prime">Category</a></li>
				    <li><a data-toggle="tab" href="#tab2prime">Supplier</a></li>
				    <li><a data-toggle="tab" href="#tab3prime">Product</a></li>
				  </ul>
			</div>
			<div class="panel-body">
			<div class="tab-content">
				<div class="tab-pane fade in active" id ="tab1prime">
				
					<form:form commandName="category" method="post" action="saveCategory">
							<p><font color="red">${errorMessage}</font></p>				
							    		     
						<div class="form-group row">
						 <div class="col-xs-4">
						   <c:if test="${category.id != 0}">
						   <form:input type="hidden" class="form-control" path="id"  placeholder="Category Id" name = "id"/>
						   </c:if>					
						</div>
						</div>
						<br/>
						<div class="form-group row">
							<label for="categoryName" class="col-xs-4 control-label">Category Name</label>
							<div class="col-xs-4">
								<form:input name="categoryName" path="categoryName" placeholder="Category Name" class="form-control" />
							</div>
						</div>
						<div class="form-group row">
							<label for="categoryDescription" class="col-xs-4 control-label">Category description</label>
							<div class="col-xs-4">
								<form:input name="categoryDescription"  path="categoryDescription" placeholder="Category Description" class="form-control" />
								 
							</div>
						</div>
						<br/>
						<div class="form-group row">
							<label for="code" class="col-xs-4 control-label"></label>						
								<div class="col-xs-4">						
								<input type="submit" value="Add Category" id="btn-add" class="btn btn-primary" >						
								</div>
						</div>						
					</form:form>
				<br/>
				</div>
				<div class="tab-pane fade" id="tab2prime">
						<form:form commandName="supplier" method="post" action="saveSupplier">
							<p><font color="red">${errorMessage}</font></p>				
				     
						<div class="form-group row">
						 <div class="col-xs-4">
						   <c:if test="${supplier.id != 0}">
						   <form:input type="hidden" class="form-control" path="id"  placeholder="Supplier Id" name = "id"/>
						   </c:if>					
						</div>
						</div>
						<br/>
						<div class="form-group row">
							<label for="supplierName" class="col-xs-4 control-label">Supplier Name</label>
							<div class="col-xs-4">
								<form:input name="supplierName" path="supplierName" placeholder="Supplier Name" class="form-control" />
							</div>
						</div>
				<div class="form-group row">
					<label for="supplierAddress" class="col-xs-4 control-label">Supplier Address</label>
					<div class="col-xs-4">
						<form:input name="supplierAddress"  path="supplierAddress" placeholder="Supplier Address" class="form-control" />
						 
					</div>
				</div>
				<br/>
				<div class="form-group row">
					<label for="code" class="col-xs-4 control-label"></label>
						
						<div class="col-xs-4">			
						<input type="submit" value="Add Supplier" id="btn-add" class="btn btn-primary" >						
						</div>
					</div>
						
				</form:form><br/>
				</div>
				<div class="tab-pane fade" id="tab3prime">
				
					 <form:form commandName="product" method="post" action="saveProduct" enctype="multipart/form-data">
					<p><font color="red">${errorMessage}</font></p>				
								    		     
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
							<input type="submit" value="Add Product" id="btn-add" class="btn btn-primary" >				  						
							</div>
						</div>						
				</form:form><br/>
				</div>
				
			</div>
			</div>
			</div>
		</div>
	</div>
</div>
</div>
</body>
</html>