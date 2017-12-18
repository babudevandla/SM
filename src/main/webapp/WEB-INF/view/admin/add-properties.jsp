<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="adminTemplate" tagdir="/WEB-INF/tags"%>

<adminTemplate:adminDefaultDecorator>
<jsp:attribute name="title">Dashboard | Easy Living</jsp:attribute>
<jsp:body>
<title>Dashboard | Easy Living</title>

           <div class="main-content-inner">
			<div class="breadcrumbs ace-save-state" id="breadcrumbs">
				<ul class="breadcrumb">
					<li>
						<i class="ace-icon fa fa-home home-icon"></i>
						<a href="${contextPath}/admin/dashboard"> Dashboard</a>
					</li>

					<li>
						<a href="${contextPath}/admin/property-listings">Property Listing</a>
					</li>
					<li class="active">Add Propertits</li>
				</ul>

				<div class="nav-search" id="nav-search">
					<form class="form-search">
						<span class="input-icon">
							<!-- <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
							<i class="ace-icon fa fa-search nav-search-icon"></i> -->
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

				<div class="row">
					<div class="col-xs-12">
						<!-- PAGE CONTENT BEGINS -->
							<div class="row">
									<div class="col-sm-2"></div>
									<div class="col-sm-8">
										<div class="widget-box">
											<div class="widget-header">
												<h4 class="widget-title">Custom File Input</h4>

												<div class="widget-toolbar">
													<a href="#" data-action="collapse">
														<i class="ace-icon fa fa-chevron-up"></i>
													</a>

													<a href="#" data-action="close">
														<i class="ace-icon fa fa-times"></i>
													</a>
												</div>
											</div>

											<div class="widget-body">
												<div class="widget-main">
													<div class="form-group">
														<div class="col-xs-12">
															<input type="file" id="id-input-file-2" />
														</div>
													</div>

													<div class="form-group">
														<div class="col-xs-12">
															<input multiple="" type="file" id="id-input-file-3" />
														</div>
													</div>

													<label>
														<input type="checkbox" name="file-format" id="id-file-format" class="ace" />
														<span class="lbl"> Allow only images</span>
													</label>
												</div>
											</div>
										</div>
									</div>
									<div class="col-sm-2"></div>
							</div>
						<!-- PAGE CONTENT ENDS -->
					</div>
				</div>
			</div>
		</div>

</jsp:body>
</adminTemplate:adminDefaultDecorator>
