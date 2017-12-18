<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="adminTemplate" tagdir="/WEB-INF/tags"%>

<adminTemplate:adminDefaultDecorator>
<jsp:attribute name="title">Dashboard | Create Agent</jsp:attribute>
<jsp:body>
<title>Dashboard | Create Agent</title>

<div class="main-content-inner">
	<div class="breadcrumbs ace-save-state" id="breadcrumbs">
		<ul class="breadcrumb">
			<li>
				<i class="ace-icon fa fa-home home-icon"></i>
				<a href="${contextPath}/admin/dashboard">Dashboard</a>
			</li>
	
			<li>
				<a href="${contextPath}/admin/agents-list">Agents Listings</a>
			</li>
			<li class="active">Create Agent</li>
		</ul>
	
		<div class="nav-search" id="nav-search">
			<form class="form-search">
				<span class="input-icon">
					
				</span>
			</form>
		</div>
	</div>
	
	<div class="page-content">
		<div class="ace-settings-container" id="ace-settings-container">
			<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
				<i class="ace-icon fa fa-cog bigger-130"></i>
			</div>
	
			<div class="ace-settings-box clearfix" id="ace-settings-box">
				<div class="pull-left width-50">
					<div class="ace-settings-item">
						<div class="pull-left">
							<select id="skin-colorpicker" class="hide">
								<option data-skin="no-skin" value="#438EB9">#438EB9</option>
								<option data-skin="skin-1" value="#222A2D">#222A2D</option>
								<option data-skin="skin-2" value="#C6487E">#C6487E</option>
								<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
							</select>
						</div>
						<span>&nbsp; Choose Skin</span>
					</div>
	
					<div class="ace-settings-item">
						<input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-navbar" autocomplete="off" />
						<label class="lbl" for="ace-settings-navbar"> Fixed Navbar</label>
					</div>
	
					<div class="ace-settings-item">
						<input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-sidebar" autocomplete="off" />
						<label class="lbl" for="ace-settings-sidebar"> Fixed Sidebar</label>
					</div>
	
					<div class="ace-settings-item">
						<input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-breadcrumbs" autocomplete="off" />
						<label class="lbl" for="ace-settings-breadcrumbs"> Fixed Breadcrumbs</label>
					</div>
	
					<div class="ace-settings-item">
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-rtl" autocomplete="off" />
						<label class="lbl" for="ace-settings-rtl"> Right To Left (rtl)</label>
					</div>
	
					<div class="ace-settings-item">
						<input type="checkbox" class="ace ace-checkbox-2 ace-save-state" id="ace-settings-add-container" autocomplete="off" />
						<label class="lbl" for="ace-settings-add-container">
							Inside
							<b>.container</b>
						</label>
					</div>
				</div>
	
				<div class="pull-left width-50">
					<div class="ace-settings-item">
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-hover" autocomplete="off" />
						<label class="lbl" for="ace-settings-hover"> Submenu on Hover</label>
					</div>
	
					<div class="ace-settings-item">
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-compact" autocomplete="off" />
						<label class="lbl" for="ace-settings-compact"> Compact Sidebar</label>
					</div>
	
					<div class="ace-settings-item">
						<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-highlight" autocomplete="off" />
						<label class="lbl" for="ace-settings-highlight"> Alt. Active Item</label>
					</div>
				</div>
			</div>
		</div>
	
		<div class="page-header">
			<h1>
				Create Agent
			</h1>
		</div>
	
		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->
				<form class="form-horizontal" action="${contextPath}/admin/create-agent" role="form" method="post" enctype="multipart/form-data">
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Agent name :</label>
						<div class="col-sm-9">
							<input type="text" id="agent_name" name="agent_name" placeholder="Agent name" class="col-xs-10 col-sm-5" />
						</div>
					</div>
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Email :</label>
						<div class="col-sm-9">
							<input type="text" id="agent_email" name="agent_email" placeholder="Email" class="col-xs-10 col-sm-5" />
						</div>
					</div>
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Mobile number :</label>
						<div class="col-sm-9">
							<input type="text" id="mobile_no" name="mobile_no" placeholder="Mobile number" class="col-xs-10 col-sm-5" />
						</div>
					</div>
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Office number : </label>
						<div class="col-sm-9">
							<input type="text" id="office_no"  name="office_no" placeholder="Office number" class="col-xs-10 col-sm-5" />
						</div>
					</div>
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Description : </label>
						<div class="col-sm-9">
							<textarea placeholder="Description" name="description" class="col-xs-10 col-sm-5" style="margin: 0px; width: 622px; height: 125px;"></textarea>
						</div>
					</div>
					<div class="space-4"></div>
					<div class="form-group">
						<label class="col-sm-3 control-label no-padding-right" for="form-field-1"> Agent Image :</label>
						<div class="col-sm-9">
							<input type="file" name="file" class="col-xs-10 col-sm-5" >
						</div>
					</div>
					<div class="space-4"></div>
					<div class="clearfix form-actions">
						<div class="col-md-offset-3 col-md-9">
							<button class="btn btn-info" type="submit">
								<i class="ace-icon fa fa-check bigger-110"></i>
								Submit
							</button>
	
							&nbsp; &nbsp; &nbsp;
							<button class="btn" type="reset">
								<i class="ace-icon fa fa-undo bigger-110"></i>
								Reset
							</button>
						</div>
					</div>
				</form>
					<div class="hr hr-24"></div>
	
					
						</div>
					</div>
				</div><!-- PAGE CONTENT ENDS -->
			</div>
		</div>
	</div>
 </div>

</jsp:body>
</adminTemplate:adminDefaultDecorator>