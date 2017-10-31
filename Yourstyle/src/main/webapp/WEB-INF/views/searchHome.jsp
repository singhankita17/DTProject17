<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/Pretty-Footer.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/PageDisplay.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>All Products</title>
</head>
<jsp:include page="header.jsp" /> 
<body>
<div id="wrap">
<div id="main" class="container clear-top">

	<div class="row">        
	<c:forEach items="${products}" var="product" varStatus="rowCount">     
        	<div class="col-md-4 col-xs-4">        
              <div class="thumbnail">
               <img src="${pageContext.request.contextPath}/resources/images/${product.id}.jpg" alt="" class="img-responsive"  style="max-height:220px">
                <div class="caption">
                <h4><a href="productDetail/${product.id}"><c:out value="${product.productName}"></c:out></a></h4>
                <div  class="space-ten"></div>
                  <h4 class="center-block"><span class="fa fa-inr"></span> <c:out value="${product.price}"></c:out></h4>
                   <div  class="space-ten"></div>
                    </div>
                <div class="ratings">
                  <p>
                    <span class="fa fa-star"></span>
                    <span class="fa fa-star"></span>
                    <span class="fa fa-star"></span>
                    <span class="fa fa-star"></span>
                    <span class="fa fa-star-o"></span>
                     (15 reviews)
                  </p>
                </div>
                <div  class="space-ten"></div>
               
                <div class="btn-ground text-center">
                    <button type="button" class="btn btn-primary"> Shop Now &nbsp;<i class="fa fa-caret-right"></i></button>
            		<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#product_view"><i class="fa fa-search"></i> Quick View</button>
                </div>
            <!-- Modal Class declaration -->
            <div class="modal fade product_view" id="product_view">
      <div class="modal-dialog">
        <div class="modal-content">
       
            <div class="modal-header">
                <a href="#" data-dismiss="modal" class="class pull-right"><span class="glyphicon glyphicon-remove"></span></a>
                <h3 class="modal-title">${product.productName}</h3>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6 product_img">
                        <img src="${pageContext.request.contextPath}/resources/images/${product.id}.jpg" class="img-responsive">
                    </div>
                    <div class="col-md-6 product_content">
                        <h4>Product Id: <span>${product.id}</span></h4>
                        <div class="rating">
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            <span class="glyphicon glyphicon-star"></span>
                            (10 reviews)
                        </div>
                        <p>${product.productDesc}</p>
                        <c:if test="${not product.onSale}">
                        <h3 class="cost"><span class="fa fa-inr"></span>${product.price} </h3>
                        </c:if>
                        <c:if test="${product.onSale eq true}">
                        <h3 class="cost"><span class="fa fa-inr"></span>${product.salePrice} <small class="pre-cost"><span class="glyphicon glyphicon-usd"></span>${product.price} </small></h3>
                        </c:if>
                        <div class="row">     
                            <div class="col-md-4 col-sm-12">
                                <select class="form-control" name="select">
                                    <option value="" selected="">QTY</option>
                                    <option value="">1</option>
                                    <option value="">2</option>
                                    <option value="">3</option>
                                    <option value="">4</option>
                                     <option value="">5</option>
                                </select>
                            </div>
                        </div>
                        <div class="space-ten"></div>
                        <div class="btn-ground">
                            <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-shopping-cart"></span> Add To Cart</button>
                            <button type="button" class="btn btn-primary"><span class="glyphicon glyphicon-heart"></span> Add To Wishlist</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        
        </div>
</div>
            
                <div class="space-ten"></div>
              </div>
            </div>
   </c:forEach>        
    </div>
</div>
</div>
   
</body>
<jsp:include page="footer.jsp" /> 
</html>