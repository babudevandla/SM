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
<jsp:attribute name="title">Personal Assistant </jsp:attribute>
<jsp:body>
   
   <h3 align="center"> Personal Assistant !</h3>
   <a href="${contextPath}/sm/new_job_settings" class="btn btn-danger" >New Job Create</a>
    <hr/> 
     
     <table id="dynamic-table" class="table table-striped table-bordered table-hover">
		<thead>
			<tr>
				<th>Agnetname</th>
				<th>Email</th>
				<th>Mobile no</th>
				<th class="hidden-480">Office no</th>
				<th>Description</th>
				<th class="hidden-480">Status</th>
			</tr>
		</thead>
		<tbody>
		 <tr>
		 	<td></td>
		 	<td></td>
		 	<td></td>
		 	<td></td>
		 	<td></td>
		 	<td></td>
		 </tr>
		</tbody>
	</table>
  </jsp:body>
 </defaultTemplate:defaultDecorator>
  