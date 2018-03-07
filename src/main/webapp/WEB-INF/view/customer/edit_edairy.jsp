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
 <h1>Edit Dairy :</h1>  <br/>
 
 <form:form action="${contextPath}/sm/savePageContent" method="post" modelAttribute="savePageContent">
 	<form:hidden path="${userId}"/>
 	<form:hidden path="${ dairyId}"/>
 	<input name="upload" type="button"	class="btn btn-primary uploadMultipleFiles1" value="Uploadfiles" />
 	<input type="text" name="userId" value="${dairyInfo.userId }"/>
 	<input type="text" name="dairyId" value="${dairyInfo.dairyId }"/>
	<textarea id="editor" name="dairy_content">${edairyDto.dairy_content}</textarea><br/>
	 <input name="submit" type="submit"	class="btn btn-primary" value="Update" />
</form:form>



<div class="modal fade" id="UploadfilesModel" role="dialog">
    <div class="modal-dialog">
    <form action="${contextPath}/sm/uploadMultipleFile" id="uploadMultiFiles" enctype="multipart/form-data" method="post">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" style="color: white;">&times;</button>
                <h4 class="modal-title filename"></h4>
            </div>
             <table id="fuTable"  border="1">
	    		<tr>
	        		<td> 
	        		<!-- <input name="files" type="file" multiple onchange="readURL(this);">
	        			<img id="blah" src="#" alt="your image" /> -->
	        		<input name="files" type="file" multiple>
	        		
	        		</td>
	   			 </tr>
 
   		 </table>
 
    <br>   <input  type="button" value="Add More File"  onclick="AddMoreFile('fuTable')">
            <div class="modal-body">
				<input type="text" name="pagecontent" id="pageContentIdVal">
				<!-- <span id="pageContentId"></span> -->
            </div>
            <div class="modal-footer">
            	 <button type="submit" class="btn btn-primary" id="" >Submit</button>
                 <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
      </form>
    </div>
</div>



<script type="text/javascript">
	
function AddMoreFile(tableID) {
    var table = document.getElementById(tableID);
    var rowCount = table.rows.length;
    var row = table.insertRow(rowCount);
    var col1 = row.insertCell(0);
    var colInput = document.createElement("input");
    colInput.type = "file";
    colInput.name="files["+(rowCount)+"]";
    col1.appendChild(colInput);
}

/* function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function (e) {
            $('#blah')
                .attr('src', e.target.result)
                .width(150)
                .height(200);
        };

        reader.readAsDataURL(input.files[0]);
    }
} */
	
 /* $(document).ready(function() {
	    	
	    	
	   
	  	  
	  	  
	    
 }); */
 
 </script>
	  	  
</jsp:body>
 </defaultTemplate:defaultDecorator>
 
