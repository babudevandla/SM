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
    
    <div class="create-post">
        <div class="row">
        	<div class="col-md-3 col-sm-3">
              <div class="form-group">
                	<button class="btn btn-primary pull-right" data-toggle="modal" data-target="#myModal">
                		<i class="fa fa-folder" aria-hidden="true"></i> &nbsp; Create Folder
                	</button>
              </div>
              </div>
              <div class="col-md-3 col-sm-3">
            	<div class="form-group">
                	<button class="btn btn-primary pull-right" > 
                		<i class="fa fa-cloud-upload" aria-hidden="true"></i>&nbsp;  Upload Documents
                	</button>
              	</div>
              </div>
            </div>
        	</div>
        
     <div class="media">
       	<div class="row js-masonry" data-masonry='{ "itemSelector": ".grid-item", "columnWidth": ".grid-sizer", "percentPosition": true }'>
           	<div class="widget">
            <div class="table-responsive">
            <table class="table user-list"  style="margin-left: 10px; width: 767px;">
              <thead>
                <tr>
                  <th><span>Folder Name</span></th>
                  <th class="text-center"><span>Size</span></th>
                  <th><span>Updated</span></th>
                  <th>&nbsp;</th>
                </tr>
              </thead>
              <tbody>
               <%-- <c:forEach items="${foldersFiles}" var="folders" varStatus="status">
                <tr>
                  <td>
                  <c:choose>
                   	<c:when test="${not empty navigatePath}">
                   		<c:set value="${navigatePath}:${folders.key}" var="folderPath"/>
                   	</c:when>
                   	<c:otherwise>
                   		<c:set value="${folders.key}" var="folderPath"/>
                   	</c:otherwise>
                   </c:choose>
                  <c:if test="${folders.value eq false}">
                    <img src="${contextPath}/resources/default/images/folder.svg" alt="${folders.key}">
                    <a href="${contextPath}/sm/openfolder?userid=${userid}&foldername=${folderPath}" data-folder="${folders.key}" data-userid="${user.userId}" style="cursor: pointer;"
                    		class="user-link">${folders.key}</a>
                   </c:if> 
                    <c:if test="${folders.value}">
                   	  <img src="${contextPath}/resources/default/images/file.png" alt="file">
                   	  <a href="${contextPath}/sm/view_file?userid=${userid}&filename=${folders.key}" data-folder="${folders.key}" style="cursor: pointer;"
                    		class="user-link">${folders.key}</a>
                    </c:if>
                  </td>
                  <td class="text-center">
                    0 kB
                  </td>
                  <td>
                    2013/08/08
                  </td>
                  <td style="width: 20%;">
                    <a href="#" class="table-link success">
                      <span class="fa-stack">
                        <i class="fa fa-square fa-stack-2x"></i>
                        <i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
                      </span>
                    </a>
                    <a href="#" class="table-link">
                      <span class="fa-stack">
                        <i class="fa fa-square fa-stack-2x"></i>
                        <i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
                      </span>
                    </a>
                    <a href="#" class="table-link danger">
                      <span class="fa-stack">
                        <i class="fa fa-square fa-stack-2x"></i>
                        <i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
                      </span>
                    </a>
                  </td>
                </tr>
                </c:forEach>  --%>
                <c:forEach items="${foldersFiles}" var="folders" varStatus="status">
                <tr>
                <td>
                <a href="${contextPath}/sm/openfolder" style="cursor: pointer;"
                    		class="user-link">
                	<img src="${contextPath}/resources/default/images/folder.svg" alt="Folder" style="width: 25px;">
                 	${folders.foldername}
                 </a>	
                 </td>
                <td class="text-center">
                     ${folders.file_size}
                  </td>
                  <td>
                  	<f:formatDate value="${folders.created_date}" pattern="yyyy-MM-dd"/>
                     <f:formatDate value="${folders.created_date}" type="time"/>
                  </td>
                  <td style="width: 20%;">
                    <a href="#" class="table-link success">
                      <span class="fa-stack">
                        <i class="fa fa-square fa-stack-2x"></i>
                        <i class="fa fa-search-plus fa-stack-1x fa-inverse"></i>
                      </span>
                    </a>
                    <a href="#" class="table-link">
                      <span class="fa-stack">
                        <i class="fa fa-square fa-stack-2x"></i>
                        <i class="fa fa-pencil fa-stack-1x fa-inverse"></i>
                      </span>
                    </a>
                    <a href="#" class="table-link danger">
                      <span class="fa-stack">
                        <i class="fa fa-square fa-stack-2x"></i>
                        <i class="fa fa-trash-o fa-stack-1x fa-inverse"></i>
                      </span>
                    </a>
                  </td>
                </tr>
                </c:forEach>
              </tbody>
            </table>
            </div>
            </div>
        </div>
     </div>     
     
    
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Create Folder</h4>
        </div>
        <div class="modal-body">
           <input type="text" name="folderName" id="folderId" placeholder="Folder name" class="form-control">
        </div>
        <div class="modal-footer">
         <button type="button" class="btn btn-primary submitFolder" data-href="${contextPath}/sm/create_folder" data-userid="${userid}"
         data-parentid="0" >Save</button>
          <button type="button" class="btn btn-primary"  data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  
  <script type="text/javascript">
	$(document).ready(function() { $('.form-popup').modal({ show: true, }) }); 
</script>
    
  </jsp:body>
 </defaultTemplate:defaultDecorator>
  