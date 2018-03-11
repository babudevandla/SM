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
<jsp:attribute name="title">E-Dairy</jsp:attribute>
<jsp:body>
	<a href="${contextPath}/sm/getDairyInfo/${userId}/${dairyId}?actionBy=${EdairyActionEnum.TODAYS_PAGE}">Go to today's page</a></br>
	<a href="${contextPath}/sm/getDairyInfo/${userId}/${dairyId}?actionBy=${EdairyActionEnum.LAST_UPDATD_DATE}">Go to last updated page</a></br>
	<a href="${contextPath}/sm/getDairyInfo/${userId}/${dairyId}?actionBy=${EdairyActionEnum.SELECTED_DATE}">Select date to open page</a></br>
	<a href="${contextPath}/sm/getDairyInfo/${userId}/${dairyId}?actionBy=${EdairyActionEnum.FAVORITE_PAGE}&defaultPageNo=1">select favorite Page</a></br>
	<a href="${contextPath}/sm/getDairyInfo/${userId}/${dairyId}?actionBy=${EdairyActionEnum.TITLE_PAGE}&defaultPageNo=1">Open page by page title</a>
	<%-- <a href="${contextPath}/sm/getDairyInfo/${userDairies.userId}/${dairy.dairyId}" >${dairy.name}</a> --%>
    
</jsp:body>
 </defaultTemplate:defaultDecorator>