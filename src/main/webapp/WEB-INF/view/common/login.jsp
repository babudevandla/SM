<%@ page pageEncoding="ISO-8859-1"  contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="defaultTemplate" tagdir="/WEB-INF/tags"%>

<!DOCTYPE html>
<html lang="en">
	
<head>
	<link rel="stylesheet" href="${contextPath}/resources/default/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${contextPath}/resources/default/css/style.css" />
	<link rel="stylesheet" href="${contextPath}/resources/default/css/ionicons.min.css" />
    <link rel="stylesheet" href="${contextPath}/resources/default/css/font-awesome.min.css" />
   
   <!--Google Font-->
   <link href="https://fonts.googleapis.com/css?family=Lato:300,400,400i,700,700i" rel="stylesheet">
   
   <!--Favicon-->
   <link rel="shortcut icon" type="image/png" href="${contextPath}/resources/default/images/fav.png"/>
</head>
<body>

    <!-- Header
    ================================================= -->
		<header id="header-inverse">
      <nav class="navbar navbar-default navbar-fixed-top menu">
        <div class="container">

          <!-- Brand and toggle get grouped for better mobile display -->
          <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${contextPath}/"><strong style="color: white;"> 
            	<img src="${contextPath}/resources/default/images/sm_logo.jpg" alt="Folder" style="width: 60px;"> </strong>
            </a>
          </div>

          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right main-menu">
              <%-- <li class="dropdown">
                <a href="${contextPath}/signup" >Register </a>
             </li> --%>
              <li class="dropdown"><a href="contact.html">Contact</a></li>
            </ul>
            
          </div>
        </div>
      </nav>
    </header>
    <!--Header End-->
    
    <!-- Landing Page Contents
    ================================================= -->
    <div id="lp-register">
    	<div class="container wrapper">
        <div class="row">
        	<div class="col-sm-5">
            <div class="intro-texts">
            	<h1 class="text-white">Make Cool Friends !!!</h1>
            	<p>Friend Finder is a social network template that can be used to connect people. The template offers Landing pages, News Feed, Image/Video Feed, Chat Box, Timeline and lot more. <br /> <br />Why are you waiting for? Buy it now.</p>
              <button class="btn btn-primary">Learn More</button>
            </div>
          </div>
        	<div class="col-sm-6 col-sm-offset-1">
            <div class="reg-form-container"> 
            
              <!-- Register/Login Tabs-->
              <div class="reg-options">
                <ul class="nav nav-tabs">
                  <li ><a href="${contextPath}/signup" >Register</a></li>
                  <li class="active"><a href="${contextPath}/login" >Login</a></li>
                </ul><!--Tabs End-->
              </div>
              
              <!--Registration Form Contents-->
              <div class="tab-content">
                <!--Login-->
                <div >
                  <h3>Login</h3>
                  <p class="text-muted">Log into your account</p>
                  <c:if test="${not empty error}">
			            <div style="color:red">
			                Your fake login attempt was bursted, dare again !!<br />
			                Caused : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			            </div>
			        </c:if>
                  <!--Login Form-->
                   <form name="f" action="${contextPath}/j_spring_security_check"    method="POST">
                     <div class="row">
                      <div class="form-group col-xs-12">
                        <label for="my-email" class="sr-only">Email</label>
                        <input id="my-email" class="form-control input-group-lg" type="text" name="j_username" title="Enter Username" placeholder="Your Username"/>
                      </div>
                    </div>
                    <div class="row">
                      <div class="form-group col-xs-12">
                        <label for="my-password" class="sr-only">Password</label>
                        <input id="my-password" class="form-control input-group-lg" type="password" name="j_password" title="Enter password" placeholder="Password"/>
                      </div>
                    </div>
	                  <p><a href="#">Forgot Password?</a></p>
	                   <input name="submit" type="submit"	class="btn btn-primary" value="Login Now" />
                   </form><!--Login Form Ends--> 
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="row">
          <div class="col-sm-6 col-sm-offset-6">
          
            <!--Social Icons-->
            <ul class="list-inline social-icons">
              <li><a href="#"><i class="icon ion-social-facebook"></i></a></li>
              <li><a href="#"><i class="icon ion-social-twitter"></i></a></li>
              <li><a href="#"><i class="icon ion-social-googleplus"></i></a></li>
              <li><a href="#"><i class="icon ion-social-pinterest"></i></a></li>
              <li><a href="#"><i class="icon ion-social-linkedin"></i></a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>

    <!--preloader-->
    <div id="spinner-wrapper">
      <div class="spinner"></div>
    </div>
    

    <!-- Scripts
    ================================================= -->
     <script src="${contextPath}/resources/default/js/jquery-3.1.1.min.js"></script>
    <script src="${contextPath}/resources/default/js/bootstrap.min.js"></script>
    <script src="${contextPath}/resources/default/js/jquery.appear.min.js"></script>
	<script src="${contextPath}/resources/default/js/jquery.incremental-counter.js"></script>
    <script src="${contextPath}/resources/default/js/script.js"></script>
    
	</body>

<!-- Mirrored from thunder-team.com/friend-finder/index-register.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 18 Oct 2017 07:24:12 GMT -->
</html>

