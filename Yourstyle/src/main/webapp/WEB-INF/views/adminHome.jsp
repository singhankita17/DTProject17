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
<title>Welcome Page</title>
</head>
<jsp:include page="header.jsp" /> 
<body>

			<c:set var="slider1" value="Revlon"></c:set>
			<c:set var="slider2" value="tresemme"></c:set>
			<c:set var="slider3" value="colorbar"></c:set>
			<c:set var="slider4" value="vaseline"></c:set>

			<div class="col-sm-1">
			 </div>
			 <div class="col-sm-10">
			 	
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
			   <!--  Indicators -->
			    <ol class="carousel-indicators">
			      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
			      <li data-target="#myCarousel" data-slide-to="1"></li>
			      <li data-target="#myCarousel" data-slide-to="2"></li>
			      <li data-target="#myCarousel" data-slide-to="3"></li>
			    </ol>
			
			   <!--  Wrapper for slides -->
			    <div class="carousel-inner" role="listbox">
			      <div class="item active">
			      <a href="${pageContext.request.contextPath}/searchProductByName/${slider1}">
			        <img src="resources/images/revlon_desktop_slider.jpg" alt="Image">
			       </a>
			        <div class="carousel-caption">
			          <h3></h3>
			          <p></p>
			        </div>      
			      </div>
			
			      <div class="item">
			       <a href="${pageContext.request.contextPath}/searchProductByName/${slider2}">
			        <img src="resources/images/tresemme_desktop_slider.jpg" alt="Image">
			        </a>
			        <div class="carousel-caption">
			          <h3></h3>
			          <p></p>
			        </div>      
			      </div>
			      
			       <div class="item">
			        <a href="${pageContext.request.contextPath}/searchProductByName/${slider3}">
			        <img src="resources/images/colorbar_desktop_slider.jpg" alt="Image">
			        </a>
			        <div class="carousel-caption">
			          <h3></h3>
			          <p></p>
			        </div>      
			      </div>
			      
			       <div class="item">
			        <a href="${pageContext.request.contextPath}/searchProductByName/${slider4}">
			        <img src="resources/images/vaseline_desktop_slider.jpg" alt="Image">
			        </a>
			        <div class="carousel-caption">
			          <h3></h3>
			          <p></p>
			        </div>      
			      </div>
			    </div>
			
			   <!--  Left and right controls -->
			    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
			      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
			      <span class="sr-only">Previous</span>
			    </a>
			    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
			      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			      <span class="sr-only">Next</span>
			    </a>
			</div>
			</div>
			<div class="col-sm-1">
			 </div>
			 <c:set var="tab1" value="makeup eye lip"></c:set>
			  <c:set var="tab2" value="lip"></c:set>
			   <c:set var="tab3" value="hair body"></c:set>
			 
			<div class="container text-center">    
			  <h3>What you can look for</h3><br>
			  <div class="row">
			    <div class="col-sm-4">
			     <a href="${pageContext.request.contextPath}/searchProductByName/${tab1}">
			      <img src="resources/images/Tile-7.jpg" class="img-responsive" style="width:100%" alt="Image">
			      </a>
			      <p>MakeUp and Applicators</p>
			    </div>
			    <div class="col-sm-4"> 
			     <a href="${pageContext.request.contextPath}/searchProductByName/${tab2}">
			      <img src="resources/images/Tile-8.jpg" class="img-responsive" style="width:100%" alt="Image">
			     </a>
			      <p>Lipsticks, Lipliners and Lip color</p>    
			    </div>			    
			     <div class="col-sm-4"> 
			     <a href="${pageContext.request.contextPath}/searchProductByName/${tab3}">
			      <img src="resources/images/Tile-3.jpg" class="img-responsive" style="width:100%" alt="Image">
			     </a>
			      <p>Hair and Body Care Products</p>    
			    </div>
			  </div>
			</div><br>
	
			<div class="container text-center">    
			  <h3> Top Brands</h3><br>
			  <div class="row">
			    <div class="col-sm-4">
			      <div class="well">
			      <a href="${pageContext.request.contextPath}/searchProductByBrand/LAKME">
			       <img src="resources/images/lakme_logo.png" class="img-responsive" style="width:100%" alt="Image">
			       </a>
			      </div>
			      <div class="well">
			      <a href="${pageContext.request.contextPath}/searchProductByBrand/L'oreal">
			       <img src="resources/images/loreal_logo.png" class="img-responsive" style="width:100%" alt="Image">
			       </a>
			      </div>
			    </div>
			     <div class="col-sm-4">
			      <div class="well">
			       <a href="${pageContext.request.contextPath}/searchProductByBrand/maybelline">
			       <img src="resources/images/maybelline_logo.png" class="img-responsive" style="width:100%" alt="Image">
			       </a>
			      </div>
			      <div class="well">
			      <a href="${pageContext.request.contextPath}/searchProductByBrand/neutrogena">
			      <img src="resources/images/neutrogena_logo.png" class="img-responsive" style="width:100%" alt="Image">
			      </a>
			      </div>
			    </div>
			    <div class="col-sm-4">
			      <div class="well">
			      <a href="${pageContext.request.contextPath}/searchProductByBrand/thefaceshop">
			       <img src="resources/images/thefaceshop_logo.png" class="img-responsive" style="width:100%" alt="Image">
			       </a>
			      </div>
			      <div class="well">
			      <a href="${pageContext.request.contextPath}/searchProductByBrand/nyx">
			       <img src="resources/images/nyx_logo.png" class="img-responsive" style="width:100%" alt="Image">
			       </a>
			      </div>
			    </div>
			  </div>
			</div><br>

	<jsp:include page="footer.jsp" /> 
</body>
</html>