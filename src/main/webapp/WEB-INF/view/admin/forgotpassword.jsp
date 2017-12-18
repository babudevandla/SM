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
    <link href='${contextPath}/resources/admin/assets/images/meta_icons/apple-touch-icon.png' rel='apple-touch-icon-precomposed'>
    <link href='${contextPath}/resources/admin/assets/images/meta_icons/apple-touch-icon-57x57.png' rel='apple-touch-icon-precomposed' sizes='57x57'>
    <link href='${contextPath}/resources/admin/assets/images/meta_icons/apple-touch-icon-72x72.png' rel='apple-touch-icon-precomposed' sizes='72x72'>
    <link href='${contextPath}/resources/admin/assets/images/meta_icons/apple-touch-icon-114x114.png' rel='apple-touch-icon-precomposed' sizes='114x114'>
    <link href='${contextPath}/resources/admin/assets/images/meta_icons/apple-touch-icon-144x144.png' rel='apple-touch-icon-precomposed' sizes='144x144'>
    <link href="${contextPath}/resources/admin/assets/stylesheets/bootstrap/bootstrap.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${contextPath}/resources/admin/assets/stylesheets/light-theme.css" media="all" id="color-settings-body-color" rel="stylesheet" type="text/css" />
    <link href="${contextPath}/resources/admin/assets/stylesheets/theme-colors.css" media="all" rel="stylesheet" type="text/css" />
    <link href="${contextPath}/resources/admin/assets/stylesheets/demo.css" media="all" rel="stylesheet" type="text/css" />
    
  </head>
  <body class='contrast-red login contrast-background'>
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
                  <h1 class='text-center title'>Forgot password</h1>
                  <form action='${contextPath}/admin/send-forgotpwd' method='get'>
                    <div class='form-group'>
                      <div class='controls with-icon-over-input'>
                        <input placeholder="E-mail" class="form-control" name="email" type="text" />
                        <i class='icon-user text-muted'></i>
                      </div>
                    </div>
                    <input type="submit" class='btn btn-block' value="Send me the instructions">
                  </form>
                  <div class='text-center'>
                    <hr class='hr-normal'>
                    <a href='${contextPath}/admin/login'>
                      <i class='icon-chevron-left'></i>
                      I already know my password
                    </a>
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
                    <!-- <a href='sign_up.html'>
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
  </body>

</html>
