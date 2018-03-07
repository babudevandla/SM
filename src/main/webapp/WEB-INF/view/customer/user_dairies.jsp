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
           		 Create New E-Dairy 
           	</a>
         </div>
         </div>
       </div>
   	</div>

    <table id="example" class="table  table-bordered" cellspacing="0" style="width: 100%">
        
        <tbody>
        <c:forEach items="${userDairies.dairyList}" var="dairy" varStatus="status">
            <tr>            
            	<td><a href="${contextPath}/sm/getDairyInfo/${userDairies.userId}/${dairy.dairyId}" >${dairy.name}</a>  </td>              
            </tr>
          </c:forEach>  
       </tbody>
    </table>  
</jsp:body>
 </defaultTemplate:defaultDecorator>