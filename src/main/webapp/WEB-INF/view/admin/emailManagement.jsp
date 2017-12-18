<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="adminTemplate" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://ckeditor.com" prefix="ckeditor"%>

<adminTemplate:adminDefaultDecorator>
<jsp:attribute name="title">Email Management</jsp:attribute>
<jsp:body>
<title>Email Management</title>

<%-- <div id="page-content">
    <div id='wrap'>
        <div id="page-heading">
            <ol class="breadcrumb">
                <li class='active'><a href="${contextPath}/admin/dashboard">Dashboard</a></li>
                <li>General Email Management</li>
            </ol>
        </div>
        
		<input type="hidden" id="contextPath" value="${contextPath}" />
		
        <div class="container">
        <div class="row">
		    <div class="col-md-12">
		        <div class="panel panel-midnightblue">
		        
		             <h4>General Email Management</h4>
		         
		         <c:if test="${not empty message}">
		        	 <div class="alert alert-success fade in">
					    <a href="#" class="close" data-dismiss="alert">&times;</a>
					    	${message}
					 </div>
		         </c:if>
		           
		        <form action="${contextPath}/admin/update-email-templateContent" class="form-horizontal row-border" id="updateGeneralEmailTemplateContent" method="post" accept-charset="UTF-8">
		           <input type="hidden" name="id" value="" id="emailTemplateContentId">
				   <input type="hidden" name="templateId" value="" id="emailTemplateId">
		          <div class="panel-body">
		          
		            <div class="form-group">
                        <label class="col-sm-2 control-label">Template <span style="color:red;">*</span></label>
                        <div class="col-sm-10">
                            <select id="masterHomeMenuName" name="masterHomeMenuName" class="form-control" required="required" title="Please Choose Template">
					            <option value="">---Select Template---</option>
					           <c:forEach items="${templates}" var="templates">
								 <option value="${templates.emailTemplateId}">${templates.templateName}</option>
							   </c:forEach>
					        </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Template Subject <span style="color:red;">*</span></label>
                        <div class="col-sm-10">
                            <input  id="templateSubject" name="templateSubject" placeholder="Template Subject"  class="form-control">
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="col-sm-2 control-label">Template Body <span style="color:red;">*</span></label>
                        <div class="col-sm-10">
                            <textarea  id="editor1" name="templateBody" placeholder="Template Body" class="form-control"></textarea>
                        	<br>
                        </div>
                        <div >
					        <div id="contactUsDiv" style="display:none">
								
								<label>Note:<span style="color:red;">* Content between the curly braces($ { }) are not applicable for the changes.</span></label>
							<h5>$ {firstName} - First Name</h5>
								<h5>$ {messageBody} - Body</h5>
							</div>
							<div id="forgetPasswordDiv" style="display:none">
							<label>Note:<span style="color:red;">* Content between the curly braces($ { }) are not applicable for the changes.</span></label>
								<h5>$ {userName} - User Name</h5>
								<h5>$ {password} - Password</h5>
							</div>
							<div id="regSuccessDiv" style="display:none">
							<label>Note:<span style="color:red;">* Content between the curly braces($ { }) are not applicable for the changes.</span></label>
								<h5>$ {userName} - User Name</h5>
								<h5>$ {email} - User Email</h5>
							</div>
							<div id="testQualifiedDiv" style="display:none">
							<label>Note:<span style="color:red;">* Content between the curly braces($ { }) are not applicable for the changes.</span></label>
								<h5>$ {userName} - User Name</h5>
								<h5>$ {marks} - Marks</h5>
							</div>
							<div id="testFailedDiv" style="display:none">
							<label>Note:<span style="color:red;">* Content between the curly braces($ { }) are not applicable for the changes.</span></label>
								<h5>$ {userName} - User Name</h5>
								<h5>$ {marks} - Marks</h5>
							</div>
							<div id="studentRegSuccessDiv" style="display:none">
							<label>Note:<span style="color:red;">* Content between the curly braces($ { }) are not applicable for the changes.</span></label>
								<h5>$ {userName} - User Name</h5>
								<h5>$ {email} - User Email</h5>
							</div>
							
							<div id="studentScheduleEmail" style="display:none">
							<label>Note:<span style="color:red;">* Content between the curly braces($ { }) are not applicable for the changes.</span></label>
								<h5>Session Schedule Date : $ {scheduleDate}</h5>
								<h5>Session Schedule Start Time : $ {schedulestartTime} </h5>
								<h5>Session Schedule End Time : $ {scheduleendTime}</h5>
							</div>
							
							<div id="userResetPassword" style="display:none">
							<label>Note:<span style="color:red;">* Content between the curly braces($ { }) are not applicable for the changes.</span></label>
								
								<!-- <h5>$ {url} - Url</h5> -->
							</div>
							
					    </div>
                    </div>
		          </div>
		          
		          <div class="panel-footer">
	                <div class="row">
	                    <div class="col-sm-6 col-sm-offset-4">
	                        <div class="btn-toolbar" align="center">
	                            <input type="submit" class="btn btn-primary" value="Update" >
	                        </div>
	                    </div>
	                </div>
	              </div>
	             </form>
		        </div>
		    </div>
		</div>
		
		<div class="row">
              <div class="col-md-12">
                    <div class="panel panel-sky">
                        <div class="panel-heading">
                            <h4>General Email Management</h4>
                        </div>
                        <div class="panel-body collapse in">
                        <div class = "table-block">
                            <table cellpadding="0" cellspacing="0" border="0" class="table table-striped table-bordered datatables" id="example">
                                <thead>
                                    <tr>
                                        <th width="5">S.No</th>
                                        <th width="30%">Email Subject</th>
                                        <th width="65%">Email Body</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${templateContent}" var="content" varStatus="status">
								     <tr>
										<td>${status.count}</td>
										<td>${content.templateSubject}</td>
										<td >${content.templateBody}</td>
									</tr>
								</c:forEach>
                                </tbody>
                            </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
       
            
	        </div>
	    </div> 
	</div> --%> 
	
	
	<div class='col-xs-12'>
              <div class='row'>
                <div class='col-sm-12'>
                  <div class='page-header'>
                    <h1 class='pull-left'>
                      <i class='icon-ok'></i>
                      <span>Validations</span>
                    </h1>
                    <div class='pull-right'>
                      <ul class='breadcrumb'>
                        <li>
                          <a href='index.html'>
                            <i class='icon-bar-chart'></i>
                          </a>
                        </li>
                        <li class='separator'>
                          <i class='icon-angle-right'></i>
                        </li>
                        <li>
                          Forms
                        </li>
                        <li class='separator'>
                          <i class='icon-angle-right'></i>
                        </li>
                        <li class='active'>Validations</li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
              <div class='row'>
                <div class='col-sm-12'>
                
                    <div class="container">
        <div class="row">
		    <div class="col-md-12">
		        <div class="panel panel-midnightblue">
		        
		             <h4>General Email Management</h4>
		         
		         <c:if test="${not empty message}">
		        	 <div class="alert alert-success fade in">
					    <a href="#" class="close" data-dismiss="alert">&times;</a>
					    	${message}
					 </div>
		         </c:if>
		           
		        <form action="${contextPath}/admin/update-email-templateContent" class="form-horizontal row-border" id="updateGeneralEmailTemplateContent" method="post" accept-charset="UTF-8">
		           <input type="hidden" name="id" value="" id="emailTemplateContentId">
				   <input type="hidden" name="templateId" value="" id="emailTemplateId">
		          <div class="panel-body">
		          
		            <div class="form-group">
                        <label class="col-sm-2 control-label">Template <span style="color:red;">*</span></label>
                        <div class="col-sm-10">
                            <select id="masterHomeMenuName" name="masterHomeMenuName" class="form-control" required="required" title="Please Choose Template">
					            <option value="">---Select Template---</option>
					           <c:forEach items="${templates}" var="templates">
								 <option value="${templates.emailTemplateId}">${templates.templateName}</option>
							   </c:forEach>
					        </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">Template Subject <span style="color:red;">*</span></label>
                        <div class="col-sm-10">
                            <input  id="templateSubject" name="templateSubject" placeholder="Template Subject"  class="form-control">
                        </div>
                    </div>
                     <div class="form-group">
                        <label class="col-sm-2 control-label">Template Body <span style="color:red;">*</span></label>
                        <div class="col-sm-10">
                            <textarea  id="editor1" name="templateBody" placeholder="Template Body" class="form-control"></textarea>
                        	<br>
                        </div>
                        <div >
					        <div id="contactUsDiv" style="display:none">
								
								<label>Note:<span style="color:red;">* Content between the curly braces($ { }) are not applicable for the changes.</span></label>
							<h5>$ {firstName} - First Name</h5>
								<h5>$ {messageBody} - Body</h5>
							</div>
							<div id="forgetPasswordDiv" style="display:none">
							<label>Note:<span style="color:red;">* Content between the curly braces($ { }) are not applicable for the changes.</span></label>
								<h5>$ {userName} - User Name</h5>
								<h5>$ {password} - Password</h5>
							</div>
							<div id="regSuccessDiv" style="display:none">
							<label>Note:<span style="color:red;">* Content between the curly braces($ { }) are not applicable for the changes.</span></label>
								<h5>$ {userName} - User Name</h5>
								<h5>$ {email} - User Email</h5>
							</div>
							<div id="testQualifiedDiv" style="display:none">
							<label>Note:<span style="color:red;">* Content between the curly braces($ { }) are not applicable for the changes.</span></label>
								<h5>$ {userName} - User Name</h5>
								<h5>$ {marks} - Marks</h5>
							</div>
							<div id="testFailedDiv" style="display:none">
							<label>Note:<span style="color:red;">* Content between the curly braces($ { }) are not applicable for the changes.</span></label>
								<h5>$ {userName} - User Name</h5>
								<h5>$ {marks} - Marks</h5>
							</div>
							<div id="studentRegSuccessDiv" style="display:none">
							<label>Note:<span style="color:red;">* Content between the curly braces($ { }) are not applicable for the changes.</span></label>
								<h5>$ {userName} - User Name</h5>
								<h5>$ {email} - User Email</h5>
							</div>
							
							<div id="studentScheduleEmail" style="display:none">
							<label>Note:<span style="color:red;">* Content between the curly braces($ { }) are not applicable for the changes.</span></label>
								<h5>Session Schedule Date : $ {scheduleDate}</h5>
								<h5>Session Schedule Start Time : $ {schedulestartTime} </h5>
								<h5>Session Schedule End Time : $ {scheduleendTime}</h5>
							</div>
							
							<div id="userResetPassword" style="display:none">
							<label>Note:<span style="color:red;">* Content between the curly braces($ { }) are not applicable for the changes.</span></label>
								
								<!-- <h5>$ {url} - Url</h5> -->
							</div>
							
					    </div>
                    </div>
		          </div>
		          
		          <div class="panel-footer">
	                <div class="row">
	                    <div class="col-sm-6 col-sm-offset-4">
	                        <div class="btn-toolbar" align="center">
	                            <input type="submit" class="btn btn-primary" value="Update" >
	                        </div>
	                    </div>
	                </div>
	              </div>
	             </form>
		        </div>
		    </div>
		    
		    <div class='group-header'>
                <div class='row'>
                  <div class='col-sm-6 col-sm-offset-3'>
                    <div class='text-center'>
                      <h2>Dynamic tables</h2>
                      <small class='text-muted'>Dynamic datables can handle large data collections, sorting, filtering...</small>
                    </div>
                  </div>
                </div>
              </div>
              <div class='row'>
                <div class='col-sm-12'>
                  <div class='box bordered-box orange-border' style='margin-bottom:0;'>
                    <div class='box-header orange-background'>
                      <div class='title'>Sortable data table with pagination</div>
                      <div class='actions'>
                        <a class="btn box-remove btn-xs btn-link" href="#"><i class='icon-remove'></i>
                        </a>
                        
                        <a class="btn box-collapse btn-xs btn-link" href="#"><i></i>
                        </a>
                      </div>
                    </div>
                    <div class='box-content box-no-padding'>
                      <div class='responsive-table'>
                        <div class='scrollable-area'>
                          <table class='data-table table table-bordered table-striped' style='margin-bottom:0;'>
                            <thead>
                              <tr>
                                <th>S.No </th>
                                <th>Email Subject</th>
                                <th>Email Body </th>
                              </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${templateContent}" var="content" varStatus="status">
							     <tr>
									<td>${status.count}</td>
									<td>${content.templateSubject}</td>
									<td >${content.templateBody}</td>
								</tr>
							</c:forEach>
                            </tbody>
                          </table>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
		    
		</div>
       </div>
     </div>
     </div>
 </div>
     
<ckeditor:replace replace="editor1" basePath="${contextPath}/resources/admin/ckeditor/" />
 <!-------------JavaScript------------->
<script type='text/javascript' src='${contextPath}/resources/admin/assets/js/jquery-1.10.2.min.js'></script> 
<script type="text/javascript">
var contextPath;


$(document).ready(function(){
	$('#masterHomeMenuName').change(function() {
		if ($('#masterHomeMenuName').val() != "") {
			$.get('${contextPath}/admin/emailTemplateBodySubject',{emailTemplateId : $('#masterHomeMenuName').val()},function(data) {
				if(data!=''){
					$('#templateSubject').val(data.templateSubject);
					$('#emailTemplateContentId').val(data.id);
					$('#emailTemplateId').val(data.templateId);
					$('.emailbodydata').val(data.body);
					/* CKEDITOR.instances.ckeditor_full.setData(data.body); */
					//$('.note-editable').html(data.body);$('#summernote').html(data.body);
					CKEDITOR.instances.editor1.setData(data.templateBody);
					if(data.id==4){
						$('#forgetPasswordDiv').show();
						$('#contactUsDiv,#regSuccessDiv,#testQualifiedDiv,#testFailedDiv,#studentRegSuccessDiv,#studentScheduleEmail,#userResetPassword').hide();
					}
					if(data.id==6){
						$('#contactUsDiv').show();
						$('#forgetPasswordDiv,#regSuccessDiv,#testQualifiedDiv,#testFailedDiv,#studentRegSuccessDiv,#studentScheduleEmail,#userResetPassword').hide();
					}
					if(data.id==7){
						$('#regSuccessDiv').show();
						$('#forgetPasswordDiv,#contactUsDiv,#testQualifiedDiv,#testFailedDiv,#studentRegSuccessDiv,#studentScheduleEmail,#userResetPassword').hide();
					}
					if(data.id==8){
						$('#testQualifiedDiv').show();
						$('#contactUsDiv,#forgetPasswordDiv,#regSuccessDiv,#testFailedDiv,#studentRegSuccessDiv,#studentScheduleEmail,#userResetPassword').hide();
					}
					if(data.id==9){
						$('#testFailedDiv').show();
						$('#contactUsDiv,#forgetPasswordDiv,#regSuccessDiv,#testQualifiedDiv,#studentRegSuccessDiv,#studentScheduleEmail,#userResetPassword').hide();
					}
					if(data.id==10){
						$('#studentScheduleEmail').show();
						$('#contactUsDiv,#forgetPasswordDiv,#regSuccessDiv,#testQualifiedDiv,#testFailedDiv,#studentRegSuccessDiv,#userResetPassword').hide();
					}
					if(data.id==11){
						$('#userResetPassword').show();
						$('#contactUsDiv,#forgetPasswordDiv,#regSuccessDiv,#testQualifiedDiv,#testFailedDiv,#studentScheduleEmail,#studentRegSuccessDiv').hide();
					}
					if(data.id==12){
						$('#studentRegSuccessDiv').show();
						$('#contactUsDiv,#forgetPasswordDiv,#regSuccessDiv,#testQualifiedDiv,#testFailedDiv,#studentScheduleEmail,#userResetPassword,').hide();
					}
					
					
				}
				
			});
		}else{
			
			$('#emailSubjectTextArea').val('');
			/* CKEDITOR.instances.ckeditor_full.setData(''); */
			$('#applicantSignUpDiv,#adminNotificationsDiv,#RegistrationSuccessDiv,#RegistrationsuccessAdminDiv,#forgetPasswordAdminDiv,#forgetPasswordDiv,#checkoutStatusDiv,#closedoutDocumentsDiv,#WorkOrderRequestDiv,#WorkOrdercreatedNotificationToAdminDiv,#checkInStatusDiv,#resumeStatusNotificationToContractorDiv,#documentsStatusNotifcationToContractorDiv,#contractorJobAcceptanceDiv,#closedoutDocuments,#CronejobemailDiv').hide();
			$('.note-editable').html("");$('#summernote').html("");
			}
	});
	
	
	
	$("#updateGeneralEmailTemplateContent").validate({
		  errorClass:'inputError',
			rules: {
				masterHomeMenuName:{required:true},
				},
				message:{
					masterHomeMenuName:{
						required:"Please Choose Template"
					},
				},
			
			 submitHandler: function(form) {	
				$('#summernote').html($('.note-editable').html());
				$('.transparent-background, .loader').show();
			           form.submit();
				}
		});
	
	
	
	
	
	
	
	
});        
         

</script>
<!-------------JavaScript------------->  
</jsp:body>
</adminTemplate:adminDefaultDecorator>