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
 <script src="${contextPath}/resources/default/js/jquery-3.1.1.min.js"></script>
 <%@ page import="com.sm.portal.edairy.model.EdairyActionEnum" %>

<defaultTemplate:defaultDecorator>
<jsp:attribute name="title">E-Dairy</jsp:attribute>
<jsp:body>
<link rel="stylesheet" href="${contextPath}/resources/default/css/inline.css" />
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
		
		
	 $("#ebookNextBtn").click(function(){
		 var list=${pagelist};
		var currentPageNo= $("#ebookPageNoId").html();
		var nextPageNo=parseInt(currentPageNo)+1;
		//alert(nextPageNo);
		 $.each(list,function( index, value ) {
			 
			  if(value.pageNo==nextPageNo){
				  //alert(value.pageName);
				$("#ebookPageContent").html(value.content);
				$("#ebookPageNoId").html(value.pageNo);
				$("#ebooPageDateId").html(value.date);
				$("#cptrName").html(value.chapterName);
				return false;
			  }
		}); 
	 });
		
	 $("#ebookPrevBtn").click(function(){
		 var list=${pagelist};
		var currentPageNo= $("#ebookPageNoId").html();
		var previousPageNo=parseInt(currentPageNo)-1;
		//alert(nextPageNo);
		 $.each(list,function( index, value ) {
			 
			  if(value.pageNo==previousPageNo){
				  //alert(value.pageName);
				$("#ebookPageContent").html(value.content);
				$("#ebookPageNoId").html(value.pageNo);
				$("#ebooPageDateId").html(value.date);
				$("#cptrName").html(value.chapterName);
				return false;
			  }
		}); 
	 });
	 
	 $("#editEbookId").click(function(){
		 //var list=${pagelist};
		 var href=$(this).attr("data-href");
		var currentPageNo= $("#ebookPageNoId").html();
		//var actionBy =$(this).attr("data-actionBy");
		//var userId=$(this).attr("data-userId");
		//var dairyId=$(this).attr("data-dairyId");
		var pageContent=$("#ebookPageContent").html();
		window.location.href=href+"&defaultPageNo="+currentPageNo;
		
	 });
		
	 $(".dedit-remote-json").click(function(e){
	  		e.preventDefault();
	  		
	  		var currentPageNo= $("#ebookPageNoId").html();
	  		
	  		var cptrName=$("#cptrName").html();
	  		$(".existingName").val(cptrName);
			$(".pageNo").val(currentPageNo);
	  		$("#chaptername").val(cptrName);
	  		
	  		console.log(cptrName);
	  		
	  		$("#updateCptrName").modal({
	  			backdrop: 'static', 
	        	keyboard: false,
	        	show:true,
	        	height:'100%',
	        	width:'100%'
	  			});
	  	});
		
	 
	});
	
	
 
</script>
<style type="text/css">

/* #ebookPageContent{
    font-family: "Helvetica Neue",Helvetica,Arial,sans-serif;
	width: 100%;
    background-color: #FBF6F5;
    padding-left: 15px;
    border-color: darkgrey;
    border-style: dotted;
    border-top-width: 11px;
    padding-top: 18px;
    padding-left: 24px;
    padding-right: 18px;
} */
 div#ebookPageContent {
    overflow-y: auto;
    height: 100%;
    overflow-x: hidden;
    width: 100%;
} 
</style>
 <div class="create-post" style="min-height: 0px;">
   <div class="row" >
	<div class="container-fluid" >
	 <div class="book-read" itemscope="" itemtype="http://schema.org/Book">
	 <c:choose>
  		<c:when test="${not empty eBook.coverImage}">
  			<img src="${contextPath}${WebDavServerURL}?filePath=${eBook.coverImage}" alt="user" class="profile-photo-lg" style="border-radius:0px;"/>
  		</c:when>
  		<c:otherwise>
  			 <img src="${contextPath}/resources/default/images/Book_icon.png" alt="user" class="profile-photo-lg" style="border-radius:0px;"/>
  		</c:otherwise>
  	</c:choose>&nbsp;&nbsp;
  	<span style="font-size: 17px;;font-weight: bold;color: slategray;font-family: sans-serif;"> ${eBook.bookTitle}</span><br/><br/>
		<div class="xol-xs-12">
		<div class="xb-reader" data-hash="true" style="height: 100%;" >
		    <div class="reader-vertical-line"></div>
		    <div class="reader-header-outher">
		        <div class="reader-header">
		            <div class="reader-header-left" style="width: 100%;">
		                <h1 class="reader-name-inner" style="overflow-wrap: break-word;">
		                    <a href="javascript:void(0);" title="Chapter Name" id="ebookPageDateId" itemprop="name" ><span id="cptrName">${eBook.defaultPage.chapterName}</span>  </a> <span class="dedit-remote-json"><img src="${contextPath}/resources/default/images/edit.png"> </span>
		                    <a id="editEbookId" data-href="${pageContext.request.contextPath}/sm/editEbookContent?userId=${eBook.userId}&bookId=${eBook.bookId}"  class="btn btn-warning pull-right">
			    				<i class="fa fa-edit"></i> EDIT
			    			</a>
		                 </h1>
		                <div class="reader-info-inner" style="overflow-wrap: break-word;">
		                     Page : <a href="javascript:void(0);" id="ebookPageNoId" title="Page No">${eBook.defaultPage.pageNo}</a> &nbsp;&nbsp;&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i> &nbsp;&nbsp;&nbsp; 
		                     <a href="javascript:void(0);" id="sectedDateId" title="Created Date" itemprop="genre"> Mar 22, 2018 12:23:38 PM</a> &nbsp;&nbsp;&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i> &nbsp;&nbsp;&nbsp; 
		                     <a href="javascript:void(0);" title="Read Fantasy books" itemprop="genre">Topic name</a>                
		                </div>
		            </div>
		            <div class="clear"></div>
		        </div>
		    </div>
		    <div class="reader-content-outer" >
		        <div class="xb-reader-prev col-sm-1 hidden-xs text-center" style="width:4%">
		        	<a  id="ebookPrevBtn"  title="Prev page" style="cursor: pointer"><span class="glyphicon glyphicon-chevron-left"></span></a>
		        </div>
		        <div class="reader-content col-xs-12 col-sm-10" style="width:92%">
		        	<div id="ebookPageContent" style="visibility: visible;">
		        		${eBook.defaultPage.content}
		        	</div>
		        </div>
		        <div class="xb-reader-next col-sm-1 hidden-xs text-center" style="width:4%">
		        	<a  title="Next page"  id="ebookNextBtn" style="cursor: pointer"><span class="glyphicon glyphicon-chevron-right"></span></a>
		        </div>
		        <div class="clear"></div>
		    </div>
		   
		</div>  
		 <span style="font-size: 17px;float: right;font-weight: bold;color: slategray;font-family: sans-serif;"> ${eBook.bookTitle}</span>  
		</div>
		</div>   
		 </div>

<span style="display: none;" id="pageListCont">${pagelist}</span>

<div class="modal fade" id="updateCptrName" role="dialog">
    <div class="modal-dialog">
    <form action="${contextPath}/sm/updateChapter" id="updateCptrNameFrom"  method="get" >
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" style="color: white;">&times;</button>
                <h4 class="modal-title filename">Change Chapter Name</h4>
            </div>
            <div class="modal-body">
            	<input type="text" name="newChapterName" id="chaptername" class="form-control" >
				<input type="hidden" name="bookId" class="bookId" value="${eBook.bookId}">
				<input type="hidden" name="userId" class="userId" value="${eBook.userId}">
				<input type="hidden" name="pageNo" class="pageNo" >
				<input type="hidden" name="existingName" class="existingName">
            </div>
            <div class="modal-footer">
            	 <button type="submit" class="btn btn-primary changeCptrName" id="updateCptr" data-href="${contextPath}/sm/updateChapter" >Update</button>
            	  <button type="submit" class="btn btn-primary changeCptrName" id="createCptr" data-href="${contextPath}/sm/createNewChapter">Create</button>
                 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
      </form>
    </div>
</div>
    
</jsp:body>
 </defaultTemplate:defaultDecorator>