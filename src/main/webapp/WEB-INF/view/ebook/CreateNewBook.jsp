<%@ page pageEncoding="ISO-8859-1"  contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="defaultTemplate" tagdir="/WEB-INF/tags"%>

<%@page import="com.sm.portal.ebook.enums.BookSizeEnum"%>
<%@page import="com.sm.portal.ebook.enums.PageSizeEnum"%>
 <script src="${contextPath}/resources/default/js/jquery-3.1.1.min.js"></script>

<defaultTemplate:defaultDecorator>
<jsp:attribute name="title">E-Book</jsp:attribute>
<jsp:body>
	<h3>Create New Book</h3>
	<form:form action="${contextPath}/sm/creatEbook" id="createEbook"  method="POST" commandName="eBook" >
	
		<form:hidden path="userId" />
		BookName: <form:input path="bookTitle" id="bookTitle" maxlength="150"/></br>
		BookSize: 
		
		 <form:select path="bookSize" id="bookSize" >
                           <c:forEach var="bookSizeVar"  items="${bookSizeEnumList}">
                           		 <%-- <c:choose>
	     	                		<c:when test ="${bookSize == bookSizeVar.pageSize}">
	     	                			<form:option value="${bookSizeVar.pageSize}">${bookSizeVar.pageSize}</form:option>
	     	                		</c:when>
	     	                		<c:otherwise>
	     	                			<c:choose>
		     	                			<c:when test="${bookSize != bookSizeVar.pageSize}">
		     	                				<form:option value="${bookSizeVar.pageSize}">${bookSizeVar.pageSize}</form:option>
		     	                			</c:when>
	     	                			</c:choose>
	     	                		</c:otherwise>
	     	                	</c:choose>  --%>
                              <form:option value="${bookSizeVar.bookSize}">${bookSizeVar.bookSize}</form:option>
                           </c:forEach>
           </form:select>Pages per book</br>
           
           Page Size: <form:select path="pageSize" id="pageSize" >
                           <c:forEach var="pageSizeVar"  items="${pageSizeEnumList}">
                           <%--  <c:choose>
	     	                		<c:when test ="${pageSize == pageSizeVar.pageSize}">
	     	                			<form:option value="${pageSizeVar.pageSize}">${pageSizeVar.pageSize}</form:option>
	     	                		</c:when>
	     	                		<c:otherwise>
	     	                			<c:choose>
		     	                			<c:when test="${pageSize != pageSizeVar.pageSize}">
		     	                				<form:option value="${pageSizeVar.pageSize}">${pageSizeVar.pageSize}</form:option>
		     	                			</c:when>
	     	                			</c:choose>
	     	                		</c:otherwise>
	     	                	</c:choose>  --%>
                            <form:option value="${pageSizeVar.pageSize}">${pageSizeVar.pageSize}</form:option>
                           </c:forEach>
           </form:select> Lines per Page</br>
           
            <input type="submit"  value="Create" />
	</form:form>
	
	
</jsp:body>
 </defaultTemplate:defaultDecorator>