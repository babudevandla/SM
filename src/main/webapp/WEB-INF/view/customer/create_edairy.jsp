<%@ page pageEncoding="ISO-8859-1"  contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="defaultTemplate" tagdir="/WEB-INF/tags"%>

<defaultTemplate:defaultDecorator>
<jsp:attribute name="title">E-Dairy</jsp:attribute>
<jsp:body>

 <h1>New Dairy :</h1>  <br/>
 <form:form action="${contextPath}/sm/submit_edairy" method="post" modelAttribute="edairyDto">
<textarea id="editor" name="dairy_content">
  
</textarea>
<br/>
 <input name="submit" type="submit"	class="btn btn-primary" value="Save" />
</form:form>
<!-- <h1> CKEditor Plug-In : </h1> <br/>

<textarea id="myfield" name="myfield"></textarea> -->

</jsp:body>
 </defaultTemplate:defaultDecorator>