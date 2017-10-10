<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page isELIgnored="false"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Supplier Page</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="resources/css/Pretty-Footer.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
	<jsp:include page="header.jsp" /> 

	<div class="container">
	      <form:form commandName="supplier" method="post" action="saveSupplier">
				<p><font color="red">${errorMessage}</font></p>				
				<h5>
				<strong>
			     <c:choose>
			     	<c:when test="${supplier.id==0}">  Add New Supplier </c:when>
			     	<c:when test="${!empty supplier.id}">Update Supplier for Id: <c:out value="${supplier.id}"/></c:when>
			     </c:choose>
			     </strong>
			     </h5>
			    		     
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
						
							
						<c:if test="${supplier.id==0}">
						<input type="submit" value="Add Supplier" id="btn-add" class="btn btn-primary" >
						
						</c:if> <c:if test="${supplier.id!=0}">
						
					   <input type="submit" value="Update Supplier" id="btn-update" class="btn btn-primary" >
					  </c:if>
						
						
						</div>
					</div>
						
		</form:form><br/>
		
		<table class="table table-striped table-bordered" style="width:80%">
			<thead>
			<tr>
				<th>S. No.</th>
				<th>Supplier Id</th>
				<th>Supplier Name</th>
				<th>Supplier Description</th>
				<th>Supplier Action</th>
			</tr>	
			</thead>
			<tbody>
			   <c:forEach items="${supplierList}" var="supplier" varStatus="loopCounter">
			   		<tr>
			   			<td><c:out value="${loopCounter.count}"></c:out></td>
			   			<td><c:out value="${supplier.id}"></c:out></td>
			   			<td><c:out value="${supplier.supplierName}"></c:out></td>
			   			<td><c:out value="${supplier.supplierAddress}"></c:out></td>
			   			<td><nobr>
			   			
<a class="btn btn-primary" href="editsupplier/${supplier.id}"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Edit</a>

<a class="btn btn-primary"  href="removesupplier/${supplier.id}"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete
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