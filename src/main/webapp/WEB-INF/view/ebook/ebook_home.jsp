<%@ page pageEncoding="ISO-8859-1"  contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="defaultTemplate" tagdir="/WEB-INF/tags"%>
<%@ page import="com.sm.portal.edairy.model.EdairyActionEnum" %>
 <script src="${contextPath}/resources/default/js/jquery-3.1.1.min.js"></script>

<defaultTemplate:defaultDecorator>
<jsp:attribute name="title">E-Diary</jsp:attribute>
<jsp:body>
	Ebook home page
	<a href="${contextPath}/sm/creatEbook?userId=${userId}">Create Book</a>
</jsp:body>
 </defaultTemplate:defaultDecorator>