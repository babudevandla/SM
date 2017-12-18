<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
    <title>Sign in | Easy Living</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <meta content='text/html;charset=utf-8' http-equiv='content-type'>
    <meta content='Easy Living Administration' name='description'>
    <link href='${contextPath}/resources/admin/assets/images/meta_icons/favicon.ico' rel='shortcut icon' type='image/x-icon'>
	<link rel="stylesheet" href="${contextPath}/resources/admin/dist/css/bootstrap.min.css" />
	<link rel="stylesheet" href="${contextPath}/resources/admin/css/font-awesome/4.5.0/css/font-awesome.min.css" />
	<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />
	<link rel="stylesheet" href="${contextPath}/resources/admin/dist/css/ace.min.css" />
	<link rel="stylesheet" href="${contextPath}/resources/admin/dist/css/ace-rtl.min.css" />

 </head>
  <%-- <body class='contrast-red login contrast-background'>
    <div class='middle-container'>
      <div class='middle-row'>
        <div class='middle-wrapper'>
          <div class='login-container-header'>
            <div class='container'>
              <div class='row'>
                <div class='col-sm-12'>
                  <div class='text-center'>
                    Easy Living
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class='login-container'>
            <div class='container'>
              <div class='row'>
                <div class='col-sm-4 col-sm-offset-4'>
                  <h1 class='text-center title'>Sign in</h1>
                    <c:if test="${not empty message}">
					   <div class="alert alert-success fade in" id="sucessMsg">
						  <a href="#" class="close" data-dismiss="alert">&times;</a>
						    ${message}
					   </div>
					 </c:if>
					 <c:if test="${not empty error}">
					    <div class="alert alert-error" ><span style="color: red;" > 
							${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message} !! Enter correct Email and password</span>
						</div>
					</c:if> 
					<c:if test="${accessDeined}">
					   <div class="alert alert-error" ><span style="color: red;" > 
							 !! Access Denied Please provide valid Email and Password</span>
					   </div>
					</c:if> 
                  <form action='<c:url value='/admin/j_spring_security_check' />' class='validate-form' method='post'>
                    <div class='form-group'>
                      <div class='controls with-icon-over-input'>
                        <input placeholder="E-mail" class="form-control" data-rule-required="true" name="j_username" type="text" />
                        <i class='icon-user text-muted'></i>
                      </div>
                    </div>
                    <div class='form-group'>
                      <div class='controls with-icon-over-input'>
                        <input placeholder="Password" class="form-control" data-rule-required="true" name="j_password" type="password" />
                        <i class='icon-lock text-muted'></i>
                      </div>
                    </div>
                    
                    <input type="submit" class='btn btn-block' value="Sign in">
                  </form>
                  <div class='text-center'>
                    <hr class='hr-normal'>
                    <a href='${contextPath}/admin/forgot-password'>Forgot your password?</a>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class='login-container-footer'>
            <div class='container'>
              <div class='row'>
                <div class='col-sm-12'>
                  <div class='text-center'>
                   <!--  <a href='sign_up.html'>
                      <i class='icon-user'></i>
                      New to Flatty?
                      <strong>Sign up</strong>
                    </a> -->
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
   </div>
    
    <script src="${contextPath}/resources/admin/assets/javascripts/jquery/jquery.min.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/admin/assets/javascripts/jquery/jquery.mobile.custom.min.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/admin/assets/javascripts/jquery/jquery-migrate.min.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/admin/assets/javascripts/jquery/jquery-ui.min.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/admin/assets/javascripts/plugins/jquery_ui_touch_punch/jquery.ui.touch-punch.min.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/admin/assets/javascripts/bootstrap/bootstrap.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/admin/assets/javascripts/plugins/modernizr/modernizr.min.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/admin/assets/javascripts/plugins/retina/retina.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/admin/assets/javascripts/theme.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/admin/assets/javascripts/demo.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/admin/assets/javascripts/plugins/validate/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${contextPath}/resources/admin/assets/javascripts/plugins/validate/additional-methods.js" type="text/javascript"></script>
  </body> --%>
  
  <body class="login-layout">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="ace-icon fa fa-leaf green"></i>
									<span class="red">Ace</span>
									<span class="white" id="id-text2">Application</span>
								</h1>
								<h4 class="blue" id="id-company-text">&copy; Company Name</h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="ace-icon fa fa-coffee green"></i>
												Please Enter Your Information
											</h4>

											<div class="space-6"></div>
											<c:if test="${not empty message}">
											   <div class="alert alert-success fade in" id="sucessMsg">
												  <a href="#" class="close" data-dismiss="alert">&times;</a>
												    ${message}
											   </div>
											 </c:if>
											 <c:if test="${not empty error}">
											    <div class="alert alert-error" ><span style="color: red;" > 
													${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message} !! Enter correct Email and password</span>
												</div>
											</c:if> 
											<c:if test="${accessDeined}">
											   <div class="alert alert-error" ><span style="color: red;" > 
													 !! Access Denied Please provide valid Email and Password</span>
											   </div>
											</c:if> 
											 <form action='<c:url value='/admin/j_spring_security_check' />' class='validate-form' method='post'>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input placeholder="E-mail" class="form-control" data-rule-required="true" name="j_username" type="text" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input placeholder="Password" class="form-control" data-rule-required="true" name="j_password" type="password" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">
														<label class="inline">
															<input type="checkbox" class="ace" />
															<span class="lbl"> Remember Me</span>
														</label>

														<button type="button" class="width-35 pull-right btn btn-sm btn-primary">
															<i class="ace-icon fa fa-key"></i>
															<input type="submit" class="bigger-110" value="Login">
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>

											<div class="social-or-login center">
												<span class="bigger-110">Or Login Using</span>
											</div>

											<div class="space-6"></div>

											<div class="social-login center">
												<a class="btn btn-primary">
													<i class="ace-icon fa fa-facebook"></i>
												</a>

												<a class="btn btn-info">
													<i class="ace-icon fa fa-twitter"></i>
												</a>

												<a class="btn btn-danger">
													<i class="ace-icon fa fa-google-plus"></i>
												</a>
											</div>
										</div><!-- /.widget-main -->

										<div class="toolbar clearfix">
											<div>
												<a href="#" data-target="#forgot-box" class="forgot-password-link">
													<i class="ace-icon fa fa-arrow-left"></i>
													I forgot my password
												</a>
											</div>

											<div>
												<a href="#" data-target="#signup-box" class="user-signup-link">
													I want to register
													<i class="ace-icon fa fa-arrow-right"></i>
												</a>
											</div>
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.login-box -->

								<div id="forgot-box" class="forgot-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header red lighter bigger">
												<i class="ace-icon fa fa-key"></i>
												Retrieve Password
											</h4>

											<div class="space-6"></div>
											<p>
												Enter your email and to receive instructions
											</p>

											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control" placeholder="Email" />
															<i class="ace-icon fa fa-envelope"></i>
														</span>
													</label>

													<div class="clearfix">
														<button type="button" class="width-35 pull-right btn btn-sm btn-danger">
															<i class="ace-icon fa fa-lightbulb-o"></i>
															<span class="bigger-110">Send Me!</span>
														</button>
													</div>
												</fieldset>
											</form>
										</div><!-- /.widget-main -->

										<div class="toolbar center">
											<a href="#" data-target="#login-box" class="back-to-login-link">
												Back to login
												<i class="ace-icon fa fa-arrow-right"></i>
											</a>
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.forgot-box -->

								<div id="signup-box" class="signup-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header green lighter bigger">
												<i class="ace-icon fa fa-users blue"></i>
												New User Registration
											</h4>

											<div class="space-6"></div>
											<p> Enter your details to begin: </p>

											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control" placeholder="Email" />
															<i class="ace-icon fa fa-envelope"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" placeholder="Username" />
															<i class="ace-icon fa fa-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="Password" />
															<i class="ace-icon fa fa-lock"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" placeholder="Repeat password" />
															<i class="ace-icon fa fa-retweet"></i>
														</span>
													</label>

													<label class="block">
														<input type="checkbox" class="ace" />
														<span class="lbl">
															I accept the
															<a href="#">User Agreement</a>
														</span>
													</label>

													<div class="space-24"></div>

													<div class="clearfix">
														<button type="reset" class="width-30 pull-left btn btn-sm">
															<i class="ace-icon fa fa-refresh"></i>
															<span class="bigger-110">Reset</span>
														</button>

														<button type="button" class="width-65 pull-right btn btn-sm btn-success">
															<span class="bigger-110">Register</span>

															<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
														</button>
													</div>
												</fieldset>
											</form>
										</div>

										<div class="toolbar center">
											<a href="#" data-target="#login-box" class="back-to-login-link">
												<i class="ace-icon fa fa-arrow-left"></i>
												Back to login
											</a>
										</div>
									</div><!-- /.widget-body -->
								</div><!-- /.signup-box -->
							</div><!-- /.position-relative -->

							<div class="navbar-fixed-top align-right">
								<br />
								&nbsp;
								<a id="btn-login-dark" href="#">Dark</a>
								&nbsp;
								<span class="blue">/</span>
								&nbsp;
								<a id="btn-login-blur" href="#">Blur</a>
								&nbsp;
								<span class="blue">/</span>
								&nbsp;
								<a id="btn-login-light" href="#">Light</a>
								&nbsp; &nbsp; &nbsp;
							</div>
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->
		<script src="${contextPath}/resources/admin/js/ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<![endif]-->
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='components/_mod/jquery.mobile.custom/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
			 $(document).on('click', '.toolbar a[data-target]', function(e) {
				e.preventDefault();
				var target = $(this).data('target');
				$('.widget-box.visible').removeClass('visible');//hide others
				$(target).addClass('visible');//show target
			 });
			});
			
			
			
			//you don't need this, just used for changing background
			jQuery(function($) {
			 $('#btn-login-dark').on('click', function(e) {
				$('body').attr('class', 'login-layout');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'blue');
				
				e.preventDefault();
			 });
			 $('#btn-login-light').on('click', function(e) {
				$('body').attr('class', 'login-layout light-login');
				$('#id-text2').attr('class', 'grey');
				$('#id-company-text').attr('class', 'blue');
				
				e.preventDefault();
			 });
			 $('#btn-login-blur').on('click', function(e) {
				$('body').attr('class', 'login-layout blur-login');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'light-blue');
				
				e.preventDefault();
			 });
			 
			});
		</script>
	</body>

</html>
