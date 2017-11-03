<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
  <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/Pretty-Footer.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/PageDisplay.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ImageDisplay.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>Product Detail Page</title>
</head>
<jsp:include page="header.jsp" /> 
<body>

<div id="wrap">
<div id="main" class="container clear-top">
<c:if test="${not empty error}">
	    	<h4> <font size="4px" color="red">${error} </font></h4>
	</c:if>
	<c:if test="${not empty message}">
	    	<h4> <font size="2px" color="blue">${message} </font></h4>
	</c:if>
	<br>
	<br>
<div class = "row">
      <div class = "col-xs-4">
      	<img id="productImg" src="<c:url value="${pageContext.request.pathInfo}/resources/images/${product.id}.jpg"/>" class="img-thumbnail"/>
      	<div id="myModal" class="modal">
			  <span class="close">&times;</span>
			  <img class="modal-content" id="img01">
			  <div id="caption">${product.productName}</div>
		</div>
 
      </div>
      <div class = "col-xs-5 product_content">
      <form action="${pageContext.request.contextPath}/addToCart/${product.id}">
      <h4>Product Id: <span>${product.id}</span></h4>
      <h3>${product.productName}</h3>
      <div class="ratings">
                  <p>
                    <span class="fa fa-star-o"></span>
                    <span class="fa fa-star-o"></span>
                    <span class="fa fa-star-o"></span>
                    <span class="fa fa-star-o"></span>
                    <span class="fa fa-star-o"></span>
                  </p>
       </div>
       
        <p>Brand : <strong>${product.brandName}</strong></p>
      <div  class="space-ten"></div>
      <p>${product.productDesc}</p>
        <c:if test="${not product.onSale}">
        <h3 class="cost"><span class="fa fa-inr"></span>${product.price} </h3>
        </c:if>
        <c:if test="${product.onSale eq true}">
        <h3 class="cost"><span class="fa fa-inr"></span>${product.salePrice} <small class="pre-cost"><span class="fa fa-inr"></span>${product.price} </small></h3>
        </c:if>
     <br>
       <div class="col-md-4">
      <input type="number" id="quantityToAdd" name="quantityToAdd" value="1" class="form-control" size="5" required>
      </div>
      <div class="space-ten"></div>
      <div class="col-md-4">
      <c:choose>     
      <c:when test="${product.inStock eq true}"> Available in Stock</c:when>
      <c:when test="${not product.inStock}"> Out Of Stock</c:when>
      </c:choose>
      </div>
      <br><br>
      	<button id="addbtn" class="btn btn-primary btn-block"> <span class="glyphicon glyphicon-shopping-cart"></span> &nbsp;Add To Cart </button>
      </form>
     <%--  <div id="gobtn">
       	<a href="${pageContext.request.contextPath}/" class="btn btn-success"> Continue Shopping</a>
        <a href="${pageContext.request.contextPath}/gotoCart" class="btn btn-primary"> Go to Cart &nbsp;<span class="fa fa-cart"></span></a>
      </div> --%>
      </div>
      </div>
      
       <div class="col-xs-9">
                    <ul class="menu-items">
                        <li class="active">Description</li>
                        <li>Specifications</li>
                        <li>Reviews</li>
                        <li>Q&A</li>
                    </ul>
                    <div style="width:100%;border-top:1px solid silver">
                        <p style="padding:15px;">
                            ${product.productDesc}
                            
                        </p>
                        <small>
                            <ul>
                            </ul>  
                        </small>
                    </div>
                </div>
   </div>
</div>
<script>
// Get the modal
var modal = document.getElementById('myModal');

// Get the image and insert it inside the modal - use its "alt" text as a caption
var img = document.getElementById('productImg');
var modalImg = document.getElementById("img01");
var captionText = document.getElementById("caption");
img.onclick = function(){
    modal.style.display = "block";
    modalImg.src = this.src;
    captionText.innerHTML = this.alt;
}

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks on <span> (x), close the modal
span.onclick = function() { 
    modal.style.display = "none";
}

$(document).ready(function(){
	
    //-- Click on detail
    $("ul.menu-items > li").on("click",function(){
        $("ul.menu-items > li").removeClass("active");
        $(this).addClass("active");
    })

    $(".attr,.attr2").on("click",function(){
        var clase = $(this).attr("class");

        $("." + clase).removeClass("active");
        $(this).addClass("active");
    })
    
  /*   $("#addbtn").on("click",function(){
    	$("#addbtn").hide();
    	$("#gobtn").show();
    }) */
});

</script>

</body>
<jsp:include page="footer.jsp" /> 
</html>