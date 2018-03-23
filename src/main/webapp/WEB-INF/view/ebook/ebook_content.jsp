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
    height: 600px;
    overflow-x: hidden;
} 
</style>
 <div class="create-post" style="min-height: 0px;">
   <div class="row" >
   
   	<%-- <div class="col-md-3 col-sm-3">
         <div class="form-group">
           	<a href="${contextPath}/sm/create_edairy" class="btn btn-primary pull-right">
           		 Create New E-Diary 
           	</a>
         </div>
         </div> --%>
       </div>
   	</div>
   	<!-- <a class="btn btn-primary pull-left" id="ebookPrevBtn"  ><i class="fa fa-chevron-left" aria-hidden="true"></i> PRE </a>
	<a class="btn btn-primary pull-right nextBtn" id="ebookNextBtn" >NEXT <i class="fa fa-chevron-right" aria-hidden="true"></i></a> -->
   <%--  <table id="navigatorTable" class="table" cellspacing="0" style ="width: 100%">
	    <tbody>
	    	<tr>
	    		<td ><label>Chapter:</label></td><td id="ebookPageDateId">${eBook.defaultPage.chapterName}</td>
	    		<td><label>Page:</label></td><td id="ebookPageNoId">${eBook.defaultPage.pageNo}</td>
	    		<td><label>Select Date:</label></td><td id="sectedDateId"></td>
	    		<td align="right">
	    			<a id="editEbookId" data-href="${pageContext.request.contextPath}/sm/editEbookContent?userId=${eBook.userId}&bookId=${eBook.bookId}"  class="btn btn-warning">
	    				<i class="fa fa-edit"></i> WRITE
	    			</a>
	    		</td>
	    	</tr>
	    </tbody>
    </table>
    <div id="ebookPageContent" >
    	${eBook.defaultPage.content}
    </div> --%>

<div class="container-fluid" >
<div class="book-read" itemscope="" itemtype="http://schema.org/Book">
<div class="xol-xs-12">
<div class="xb-reader" data-caplink="https://www.readanybook.com/ebook-promo/565144" data-hash="true" style="height: 100%;">
    <div class="reader-vertical-line"></div>
    <div class="reader-header-outher">
        <div class="reader-header">
            <div class="reader-header-left" style="width: 100%;">
                <h1 class="reader-name-inner" style="overflow-wrap: break-word;">
                    <a href="javascript:void(0);" title="Chapter Name" id="ebookPageDateId" itemprop="name">${eBook.defaultPage.chapterName}</a>
                    <a id="editEbookId" data-href="${pageContext.request.contextPath}/sm/editEbookContent?userId=${eBook.userId}&bookId=${eBook.bookId}"  class="btn btn-warning pull-right">
	    				<i class="fa fa-edit"></i> EDIT
	    			</a>
                 </h1>
                <div class="reader-info-inner" style="overflow-wrap: break-word;">
                     <a href="javascript:void(0);" id="ebookPageNoId" title="Page No">${eBook.defaultPage.pageNo}</a> &nbsp;&nbsp;&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i> &nbsp;&nbsp;&nbsp; 
                     <a href="javascript:void(0);" id="sectedDateId" title="Created Date" itemprop="genre"> Mar 22, 2018 12:23:38 PM</a> &nbsp;&nbsp;&nbsp;<i class="fa fa-angle-double-right" aria-hidden="true"></i> &nbsp;&nbsp;&nbsp; 
                     <a href="javascript:void(0);" title="Read Fantasy books" itemprop="genre">Fantasy</a>                
                </div>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <div class="reader-content-outer" >
        <div class="xb-reader-prev col-sm-1 hidden-xs text-center">
        	<a  id="ebookPrevBtn"  title="Prev page"><span class="glyphicon glyphicon-chevron-left"></span></a>
        </div>
        <div class="reader-content col-xs-12 col-sm-10" >
        	<div id="ebookPageContent" style="visibility: visible;">
        		${eBook.defaultPage.content}
        	</div>
        </div>
        <div class="xb-reader-next col-sm-1 hidden-xs text-center">
        	<a  title="Next page"  id="ebookNextBtn" ><span class="glyphicon glyphicon-chevron-right"></span></a>
        </div>
        <div class="clear"></div>
    </div>
   <!--  <div class="reader-options collapsed">
        <div class="col-xs-4">
            <a href="javascript:;" title="Previous page" class="xb-reader-prev"><i class="fa fa-angle-double-left" aria-hidden="true"></i> Prev</a>
        </div>
        <div class="col-xs-4 text-center when-collapsed">
            <div class="download-panel-bottom hidden-xs">
                <a rel="nofollow" target="_blank" href="http://desyncs.com/TDS/?sub=124&amp;q=Scarlet&amp;place=read&amp;img=https%3A%2F%2Ffiles.readanybook.com%2F515244%2Ffiles%2Fscarlet.jpg&amp;eid=9782897672492&amp;type=eid" title="Download book">DOWNLOAD</a><span> this page</span>
            </div>
            <a href="javascript:;" class="visible-xs files-link" title="Read other files of this book">Files (<span class="xb-reader-files-count">2</span>)</a>
        </div>
        <div class="col-xs-4 text-center when-not-collapsed" style="display: none;">
            <a href="javascript:;" class="files-link files-link-close" title="Close and return to reading">Close</a>
        </div>
        <div class="col-xs-4 text-right">
            <a href="javascript:;" title="Next page" class="xb-reader-next">Next <i class="fa fa-angle-double-right" aria-hidden="true"></i></a>
        </div>
        <div class="clear"></div>
    </div> -->
</div>    
</div>
</div>   
 </div>

<span style="display: none;" id="pageListCont">${pagelist}</span>


    
</jsp:body>
 </defaultTemplate:defaultDecorator>