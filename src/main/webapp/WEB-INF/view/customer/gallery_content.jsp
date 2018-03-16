<%@ page import="com.sm.portal.constants.WebDavServerConstant" %>
<% 
	pageContext.setAttribute("WebDav_Server_Url", WebDavServerConstant.WEBDAV_SERVER_URL);
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

<defaultTemplate:defaultDecorator>
<jsp:attribute name="title">File Manager</jsp:attribute>
<jsp:body>
 
<style>
.modal-dialog {
  width: 75%;
 }
.modal-header {
    background-color: #337AB7;
    padding:16px 16px;
    color:#FFF;
    border-bottom:2px dashed #337AB7;
 }
</style>
  
    <div class="create-post">
        <div class="row">
	               
	        <form action="${contextPath}/sm/upload_files" method="post" enctype="multipart/form-data">
              <div class="col-md-3 col-sm-3">
            	<div class="form-group">
            	<button class="btn btn-primary pull-right">
                		<!-- <i class="fa fa-cloud-upload" aria-hidden="true"></i>&nbsp;  --> 
                	<input type="file" name="fileName" />	
                	<input type="hidden" name="userid" value="${userid}">
                	<input type="hidden" name="folderPath" value="${folderInfo.folderPath}">
                	<input type="hidden" name="folderId" value="${folderInfo.fId}">
                	</button>
                	&nbsp;&nbsp;  
                	<button type="submit" class="btn btn-primary pull-right"><i class="fa fa-upload" aria-hidden="true"></i> Upload</button>
                	<!-- <input type="submit" class="btn btn-primary pull-right" value="Upload"> -->
              	</div>
              </div>
              </form>
              
            </div>
            <div align="right">
            	<a href="${contextPath}/sm/getGallerContent?userid=${userId}&filesType=ALL" > ALL</a>  &nbsp; | &nbsp; &nbsp;
            	<a href="${contextPath}/sm/getGallerContent?userid=${userId}&filesType=IMAGE" ><i class="fa fa-image" aria-hidden="true"></i> IMAGE</a>  &nbsp; &nbsp;|  &nbsp; &nbsp;
            	<a href="${contextPath}/sm/getGallerContent?userid=${userId}&filesType=VIDEO" ><i class="fa fa-file-video-o" aria-hidden="true"></i> VIDEO</a>  &nbsp; &nbsp;|  &nbsp; &nbsp;
	            <a href="${contextPath}/sm/getGallerContent?userid=${userId}&filesType=DOCUMENT" ><i class="fa fa-file-text" aria-hidden="true"></i> DOCUMENTS</a> 
            </div>
        </div>
         
    
   <div class="media">
   	<div class="row js-masonry" data-masonry='{ "itemSelector": ".grid-item", "columnWidth": ".grid-sizer", "percentPosition": true }'>
      <c:forEach items="${galleryContent.files}" var="files" varStatus="status">
    	<div class="grid-item col-md-4 col-sm-4" >
          	<div class="media-grid" >
                  <div class="img-wrapper" data-toggle="modal" data-target=".modal-4" >
                  <c:choose>
				      <c:when test="${files.fileType eq 'IMAGE' }">
                    		<img src="${WebDav_Server_Url}${files.filePath}" alt="" class="img-responsive post-image" style="height: 134px;"/>
                   	  </c:when> 
                   	  <c:when test="${files.fileType eq 'DOCUMENT' }">
              				<img alt="" src="${contextPath}/resources/default/images/pdf_icon.png" style="height: 134px;">
              		  </c:when>
              		  <c:when test="${files.fileType eq 'VIDEO' }">
              				<video  height="134" controls>
							  <source src="${WebDav_Server_Url}${files.filePath}" type="video/mp4" style="height: 134px;">
							</video>
              			</c:when>
              			<c:when test="${files.fileType eq 'AUDIO' }">
              				<audio height="134" controls>
							  <source src="${WebDav_Server_Url}${files.filePath}" type="audio/mpeg"  style="height: 134px;">
							</audio>
              			</c:when>
              			<c:when test="${files.fileType eq 'doc' }">
              				<img alt="" src="${contextPath}/resources/default/images/doc_icon.png" style="height: 134px;">
              			</c:when>
              			<c:otherwise>
              				<i class="fa fa-file" aria-hidden="true"></i>
              			</c:otherwise>
              		</c:choose>
                  </div>
                  <div class="media-info">
                    <!-- <div class="reaction">
                      <a class="btn text-green"><i class="fa fa-thumbs-up"></i> 73</a>
                      <a class="btn text-red"><i class="fa fa-thumbs-down"></i> 4</a>
                    </div> -->
                    <div class="user-info">
                      <img src="${WebDav_Server_Url}${files.filePath}" alt="" class="profile-photo-sm pull-left" />
                      <div class="user">
                        <h6><a href="#" class="profile-link">John Doe</a></h6>
                        <a class="text-green" href="#">Friend</a>
                      </div>
                    </div>
                  </div>
                </div>
            </div>   
          </c:forEach>   
     </div>
    </div>
  


<script type="text/javascript">

	
	
</script>
    
  </jsp:body>
 </defaultTemplate:defaultDecorator>
  