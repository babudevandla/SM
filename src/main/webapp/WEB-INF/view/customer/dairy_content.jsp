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
 <%@ page import="com.sm.portal.edairy.model.EdairyActionEnum" %>

<defaultTemplate:defaultDecorator>
<jsp:attribute name="title">E-Dairy</jsp:attribute>
<jsp:body>
<script>
	$(document).ready(function() {
		 var list=${pagelist};
		 console.log(list);
		 // var list=$.parseJSON("${pagelist}");
		// var list=${pagelist};
		  /* $.each(list,function( index, value ) {
			 
			  if(value.pageNo==2){
				  alert(value.pageName);
				$("#pageContent").html(value.pageName);
				
				return false;
			  }
		});   */
		  
	 $("#nextBtn").click(function(){
		 var list=${pagelist};
		var currentPageNo= $("#pageNoId").html();
		var nextPageNo=parseInt(currentPageNo)+1;
		//alert(nextPageNo);
		 $.each(list,function( index, value ) {
			 
			  if(value.pageNo==nextPageNo){
				  //alert(value.pageName);
				$("#pageContent").html(value.content);
				$("#pageNoId").html(value.pageNo);
				$("#pageDateId").html(value.date);
				return false;
			  }
		}); 
	 });
		
	 $("#prevBtn").click(function(){
		 var list=${pagelist};
		var currentPageNo= $("#pageNoId").html();
		var previousPageNo=parseInt(currentPageNo)-1;
		//alert(nextPageNo);
		 $.each(list,function( index, value ) {
			 
			  if(value.pageNo==previousPageNo){
				  //alert(value.pageName);
				$("#pageContent").html(value.content);
				$("#pageNoId").html(value.pageNo);
				$("#pageDateId").html(value.date);
				return false;
			  }
		}); 
	 });
	 
	 $("#editDairyId").click(function(){
		 //var list=${pagelist};
		 var href=$(this).attr("data-href");
		var currentPageNo= $("#pageNoId").html();
		var actionBy =$(this).attr("data-actionBy");
		//var userId=$(this).attr("data-userId");
		//var dairyId=$(this).attr("data-dairyId");
		var pageContent=$("#pageContent").html();
		window.location.href=href+"?edit="+"YES"+"&defaultPageNo="+currentPageNo+"&actionBy="+actionBy;
		
	 });
		
	});
	
	
 
</script>
<style type="text/css">

#pageContent{
	width: 100%;
    background-color: #FBF6F5;
    padding-left: 15px;
    border-color: darkgrey;
    /* color: black; */
    border-style: solid;
    border-top-width: 11px;
    padding-top: 18px;
    padding-left: 24px;
    padding-right: 18px;
}
</style>
 <div class="create-post">
   <div class="row">
   
   	<%-- <div class="col-md-3 col-sm-3">
         <div class="form-group">
           	<a href="${contextPath}/sm/create_edairy" class="btn btn-primary pull-right">
           		 Create New E-Diary 
           	</a>
         </div>
         </div> --%>
       </div>
   	</div>
   	<a class="btn btn-primary pull-left" id="prevBtn"  ><i class="fa fa-chevron-left" aria-hidden="true"></i> PRE </a>
	<a class="btn btn-primary pull-right nextBtn" id="nextBtn" >NEXT <i class="fa fa-chevron-right" aria-hidden="true"></i></a>
    <table id="navigatorTable" class="table" cellspacing="0" style ="width: 100%">
	    <tbody>
	    	<tr>
	    		<td ><label>Date:</label></td><td id="pageDateId">${dairyInfo.defaultPage.date}</td>
	    		<td><label>Page:</label></td><td id="pageNoId">${dairyInfo.defaultPage.pageNo}</td>
	    		<td><label>Select Date:</label></td><td id="sectedDateId"></td>
	    		<td align="right">
	    			<a id="editDairyId" data-href="${pageContext.request.contextPath}/sm/getDairyInfo/${userId}/${dairyId}" data-actionBy="${EdairyActionEnum.EDIT_PAGE}" data-userId="${userId}" data-dairyId="${dairyId}" class="btn btn-warning">
	    				<i class="fa fa-edit"></i> WRITE
	    			</a>
	    		</td>
	    	</tr>
	    </tbody>
    </table>
   <%--  <table id="example" class="table  table-bordered" cellspacing="0" style="width: 100%">
        <tbody>
        	<tr >
        		<td id="pageContent">${dairyInfo.defaultPage.content}</td>
        	</tr>
       </tbody>
    </table>  --%> 
    <div id="pageContent" >
    	${dairyInfo.defaultPage.content}
    </div>

<%-- <c:forEach items="${pages}" var="page" varStatus="status">
	${page}
</c:forEach> --%>

<span style="display: none;" id="pageListCont">${pagelist}</span>


    
</jsp:body>
 </defaultTemplate:defaultDecorator>