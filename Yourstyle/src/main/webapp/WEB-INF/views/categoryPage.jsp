<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page isELIgnored="false"%>
 <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Category Page</title>
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
	      <form:form commandName="category" method="post" action="saveCategory">
				<p><font color="red">${errorMessage}</font></p>				
				<h4>
				<strong>
			     <c:choose>
			     	<c:when test="${category.id==0}">  Add New Item </c:when>
			     	<c:when test="${!empty category.id}">Update Category for Id: <c:out value="${category.id}"/></c:when>
			     </c:choose>
			     </strong>
			     </h4>
			    	<hr>	     
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
						
							
						<c:if test="${category.id==0}">
						<input type="submit" value="Add Category" id="btn-add" class="btn btn-primary" >
						
						</c:if> <c:if test="${category.id!=0}">
						
					   <input type="submit" value="Update Category" id="btn-update" class="btn btn-primary" >
					  </c:if>
						
						
						</div>
					</div>
						
		</form:form><br/>
		
		<table class="table table-striped table-bordered" style="width:80%">
			<thead>
			<tr>
				<th>S. No.</th>
				<th>Category Id</th>
				<th>Category Name</th>
				<th>Category Description</th>
				<th>Category Action</th>
			</tr>	
			</thead>
			<tbody>
			   <c:forEach items="${categoryList}" var="category" varStatus="loopCounter">
			   		<tr>
			   			<td><c:out value="${loopCounter.count}"></c:out></td>
			   			<td><c:out value="${category.id}"></c:out></td>
			   			<td><c:out value="${category.categoryName}"></c:out></td>
			   			<td><c:out value="${category.categoryDescription}"></c:out></td>
			   			<td><nobr>
<a class="btn btn-primary" href="editcategory/${category.id}"><span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Edit</a>

<a class="btn btn-primary"  href="removecategory/${category.id}"><span class="glyphicon glyphicon-trash" aria-hidden="true"></span> Delete
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