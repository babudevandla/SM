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

 <div class="create-post">
   <div class="row">
   	<div class="col-md-3 col-sm-3">
         <div class="form-group">
           	<a href="${contextPath}/sm/create_edairy" class="btn btn-primary pull-right">
           		 Create New E-Diary 
           	</a>
         </div>
         </div>
       </div>
   	</div>

    <table id="example" class="table  table-bordered" cellspacing="0" style="width: 100%">
        <thead>
            <tr>
                <th>S.No</th>
                <th>Name</th>
                <th>Data</th>
                <th>Created Date</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach items="${dairyList}" var="dairy" varStatus="status">
            <tr>
                <td>${status.count}</td>
                <td>${dairy.dairyname}</td>
                <td>${dairy.dairy_content}</td>
                <td>
                	<f:formatDate value="${dairy.dairyDate}" type="date" pattern="yyyy-MM-dd"/> &nbsp;
                	<f:formatDate value="${dairy.dairyDate}" type="time"/>
                </td>
                <td>
                	<c:choose>
                		<c:when test="${dairy.status }">
                			<button type="button" class="btn btn-success btn-xs">Active</button>
                		</c:when>
                		<c:otherwise>
                			<button type="button" class="btn btn-danger btn-xs">Inactive</button>
                		</c:otherwise>
                	</c:choose>
                </td>
                <td>
                	<a href="${contextPath}/sm/edit_edairy/${dairy.dairyId}" class=""><i class="fa fa-edit"></i> </a>
                	
                </td>
            </tr>
          </c:forEach>  
       </tbody>
    </table>  
</jsp:body>
 </defaultTemplate:defaultDecorator>