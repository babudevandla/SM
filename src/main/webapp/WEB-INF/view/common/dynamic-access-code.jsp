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
            <a class="navbar-brand " href="${contextPath}/" ><strong style="color: white;">SM</strong></a>
          </div>

          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right main-menu">
             <%--  <li class="dropdown">
               <a href="${contextPath}/login" >Login </a>
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
                   <li class="active"><a href="${contextPath}/signup" >Register</a></li>
                  <li ><a href="${contextPath}/login" >Login</a></li>
                </ul><!--Tabs End-->
              </div>
              
              <!--Registration Form Contents-->
              <div class="tab-content">
                <div >
                  <h3>Dynamic Access Code ( DAC )</h3>
                  <p class="text-muted">OTP sent your register mobile number</p>
                  <c:if test="${not empty message}">
	       	    	<c:if test="${flag}"><div class="alert alert-danger" role="alert">	</c:if>
	       	    	<c:if test="${flag eq false}"><div class="alert alert-success" role="alert">	</c:if>
		           	  <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
					  <strong>${message}</strong> 
					</div>
				</c:if>
                  <!--Register Form-->
                  <form action="${contextPath}/submit-dac" method="get" id="dac_update">
                    <input type="hidden" name="userId" value="${userId}">
                      <div class="form-group">
                       <input type="text" id="dynamic_access_code" name="dynamic_access_code" class="form-control" placeholder=" Enter dynamic access code" maxlength="6" required>
                      </div>
                      <div class="center">
                      <input name="submit" type="submit"	class="btn btn-azure" value="Continue"  />&nbsp;&nbsp;&nbsp;
                      </div>
                    </form>
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

</html>

