<%@ page import="com.sm.portal.constants.WebDavServerConstant" %>
<% 
	//pageContext.setAttribute("WebDav_Server_Url", WebDavServerConstant.WEBDAV_SERVER_URL);
	pageContext.setAttribute("WebDavServerURL", WebDavServerConstant.MEDIA_URL);
%>

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
<jsp:attribute name="title">E-Book</jsp:attribute>
<jsp:body>
 <style>
 .bookSmallImg {
    max-width: 70px;
}
 .greyText {
    color: #999999;
    font-size: 11px;
}
.checked {
    color: red;
}



</style>	

<script>


</script>	
	</br>
	
	<div class="create-post">
      	<div class="row">
      		<a href="${contextPath}/sm/creatEbook?userId=${userId}"  class="btn btn-secondary" style="padding-top: 5px;">
      			<i class="fa fa-book" aria-hidden="true"></i>&nbsp; Create E-Book
      		</a>
      	</div>
      </div>
      <div  class="row">
      <form:form action="${contextPath}/sm/eBooklist" method="get" commandName="searchDto">
       	<div class="col-md-10 col-sm-10">
      		<form:input path="bookTitle" class="form-control" placeholder="Search books" style="height: 37px;"/>
      		<form:hidden path="userId" value="${userBooks.userId}"/>
      	</div>
      	 <div class="col-md-2 col-sm-2">
      		<input type="submit" value="Search" class="btn btn-secondary">
      	</div>	
      	</form:form>
      </div>
      <br/>
     <div class="people-nearby">
       <c:forEach items="${userBooks.books }" var="book" >
         <div class="nearby-user">
           <div class="row" style="height: 100px;">
             <div class="col-md-4 col-sm-4">
             	<a href="${contextPath}/sm/getEbookContent?userId=${userBooks.userId}&bookId=${book.bookId}">
             	<c:choose>
             		<c:when test="${not empty book.coverPage}">
             			<img src="${contextPath}${WebDavServerURL}?filePath=${book.coverPage}" alt="user" id="myImg"  class="profile-photo-lg bookSmallImg" style="border-radius:0px;"/>
             		</c:when>
             		<c:otherwise>
             			 <img src="${contextPath}/resources/default/images/Book_icon.png" alt="user" id="myImg"  class="profile-photo-lg bookSmallImg" style="border-radius:0px;"/>
             		</c:otherwise>
             	</c:choose>
               </a>&nbsp;
              
               <span>
	               <span class="fa fa-star checked"></span>
					<span class="fa fa-star checked"></span>
					<span class="fa fa-star checked"></span>
					<span class="fa fa-star"></span>
					<span class="fa fa-star"></span> 3.00 avg rating
			    </span>
                <br/><span style="font-size: 10px;"> by </span><span class="greyText">${book.createdBy} </span>
                <p>&nbsp;<span class="label label-primary">${book.bookSize} </span>&nbsp;Pages  | &nbsp;<span class="label label-warning">${book.pageSize}</span>&nbsp; Lines per Page </p>
             </div>
             <div class="col-md-5 col-sm-5">
             	<h5><a href="${contextPath}/sm/getEbookContent?userId=${userBooks.userId}&bookId=${book.bookId}" class="profile-link">${book.bookTitle}</a>	</h5>
              	<p><f:formatDate value="${book.createdDate}" type="both"/> </p>
               <c:choose>
               		<c:when test="${book.status eq 'ACTIVE'}">
               			<span class="label label-success">${book.status}</span>
               		</c:when>
               		<c:when test="${book.status eq 'HIDE'}">
               			<span class="label label-warning">${book.status}</span>
               		</c:when>
               		<c:otherwise>
               			<span class="label label-danger">${book.status}</span>
               		</c:otherwise>
               </c:choose>
             </div>
             <div class="col-md-3 col-sm-3">
	             <form action="${contextPath}/sm/uploadCoverimg" enctype="multipart/form-data" method="post">
	             	Update cover Img:<input type="file" name="coverImg" ><br/>
	             	<input type="hidden" name="bookId" value="${book.bookId}">
	             	<input type="hidden" name="userId" value="${userBooks.userId}">
	             	<input type="submit" value="Upload" class="btn btn-info">
	             </form>
             </div>
             
           </div>
         </div>
         
       </c:forEach>
       </div>
        
</jsp:body>
 </defaultTemplate:defaultDecorator>