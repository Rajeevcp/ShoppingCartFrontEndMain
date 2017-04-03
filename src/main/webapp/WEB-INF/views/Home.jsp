<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Welcome to Electronics World</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<link rel="stylesheet" href="<c:url value="/resources/css/main.css"></c:url>">
<link rel="stylesheet" href='<c:url value="/resources/lib/jquery/jquery-ui-1.10.4.custom.css"/>'>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>

.btn-danger {
    color: #9c9539;
    background-color: rgba(217, 79, 187, 0.19) !important ;
    border-color: #222222 !important;
}
.glyphicon {
    font-size: 33px !important;
    color: #5bc0de !important;
}
</style>
</head>
<body>
<div class="navbar navbar-inverse navbar-static-top">
      <div class="container">
     <div style="margin-top: -47px;">
     
     <c:choose>
     <c:when test="${empty isUserLoggedIn}">
     <a class="btn btn-info" href="home">Home</a>
      <a class="btn btn-info" href="login">Login</a>
     <a class="btn btn-info" href="register">Register</a>
     </c:when>
     <c:otherwise>
     <a class="btn btn-info" href="home">Home</a>
    <c:url value="/j_spring_security_logout" var="logoutUrl" />
     
     <a href="${logoutUrl}" class="btn btn-info">Logout</a>

	
    <div class="button-wrapper pull-right">
	<a class="btn btn-danger btn-lg" data-toggle="tooltip" data-placement="bottom" title="You don't have any bookings yet." href="myCart"><span class="glyphicon glyphicon-shopping-cart"></span> <span class="items">${cartSize}</span></a>
</div> 
</c:otherwise>
     </c:choose>
     </div>
 	<div>
       
          <h2 class="brand text-center" style="color:#ffffff">Shopping Carts</h2>
        </div>
     <c:if test="${userRole == 'ROLE_ADMIN'}">

		
         <jsp:include page="ProductMenu.jsp"></jsp:include>
         <jsp:include page="Admin/AdminHome.jsp"></jsp:include>
	</c:if>
	<c:if test="${userRole == 'customer'}">
	
       <jsp:include page="ProductMenu.jsp"></jsp:include>

     </c:if>
     <c:if test="${empty userRole}">
 
  <jsp:include page="ProductMenu.jsp"></jsp:include>
   </c:if>
      
	

	
      </div>
   </div>
    
    <div id="ww">

    <c:if test="${not empty msg }">
<div class="alert alert-success" role="alert">
 ${msg }
</div>
</c:if>
 <c:if test="${not empty errorMessage }">
<div class="alert alert-danger" role="alert">
${errorMessage}
</div>
</c:if>
<!-- <div class="alert alert-info" role="alert"> -->
<!--   <strong>Heads up!</strong> This alert needs your attention, but it's not super important. -->
<!-- </div> -->
<!-- <div class="alert alert-warning" role="alert"> -->
<!--   <strong>Warning!</strong> Better check yourself, you're not looking too good. -->
<!-- </div> -->
<!-- <div class="alert alert-danger" role="alert"> -->
<!--   <strong>Oh snap!</strong> Change a few things up and try submitting again. -->
<!-- </div> -->
    
    <c:if test="${isUserClickedLogin==true}">
              <c:if test="${empty userRole}">
				<jsp:include page="Login.jsp"></jsp:include>
			
			</c:if>
			
			</c:if>
			<c:if test="${isUserClickedRegister==true}">
			<c:if test="${empty userRole}">
				<jsp:include page="Register.jsp"></jsp:include>
			</c:if>
			
			</c:if>
			
			<c:if test="${isAdminClickedCategories==true}">
				<%-- <%@ include file="Admin/AdminHome.jsp"%> --%>

				<%@ include file="Admin/Category.jsp"%>
			</c:if>


			<c:if test="${isAdminClickedProduct==true}">
				<%-- <%@ include file="Admin/AdminHome.jsp"%> --%>

				<%@ include file="Admin/Product.jsp"%>
			</c:if>

			<c:if test="${isAdminClickedSupplier==true}">
				<%-- <%@ include file="Admin/AdminHome.jsp"%> --%>

				<%@ include file="Admin/Supplier.jsp"%>
			</c:if>
			<c:if test="${userClikedProduct == true }">
			<%@ include file="ProductView.jsp"%>
			</c:if>
			<c:if test="${userClickedCart == true }">
			
			<%@ include file="Cart.jsp" %>
			</c:if>
			 <c:if test="${empty userRole}">
			 <c:if test="${empty isUserClickedLogin}">
	    <div class="container">
			<div class="row">
				<div class="col-lg-8 col-lg-offset-2 centered">
					<img src='<c:url value="/resources/images/shoppingcart-logo.png"></c:url>' alt="Stanley">
					<h1>Hi, Welcome to Shopping Carts!</h1>
					
				</div><!-- /col-lg-8 -->
			</div><!-- /row -->
	    </div> <!-- /container -->
	    </c:if>
	   </c:if>
	</div>


			
	
</body>
</html>
