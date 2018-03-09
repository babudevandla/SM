<%@ page pageEncoding="ISO-8859-1"  contentType="text/html; charset=UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="defaultTemplate" tagdir="/WEB-INF/tags"%>
 <script src="${contextPath}/resources/default/js/jquery-3.1.1.min.js"></script>

<defaultTemplate:defaultDecorator>
<jsp:attribute name="title">E-Dairy</jsp:attribute>
<jsp:body>

<script>
	$(document).ready(function() {
		// var list=${pagelist};
		 //console.log(list);
		  
		 /* $.each(list, function( index, value ) {
			$("#pageContent").html(list[2].pageName);
		}); */  
		
	});
	
	
 
</script>

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
   	<a class="btn btn-primary pull-left" id="prevBtn" data-pageid="-1"><i class="fa fa-chevron-left" aria-hidden="true"></i> PRE </a>
	<a class="btn btn-primary pull-right nextBtn" id="" data-pageid="1" onclick="IteratePageList(${pagelist});">NEXT <i class="fa fa-chevron-right" aria-hidden="true"></i></a>
    <table id="example" class="table  table-bordered" cellspacing="0" style="width: 100%">
        <tbody>
        	<tr >
        		<td id="pageContent"></td>
        	</tr>
       </tbody>
    </table>  


<span style="display: none;" id="pageListCont">${pagelist}</span>


    
</jsp:body>
 </defaultTemplate:defaultDecorator>