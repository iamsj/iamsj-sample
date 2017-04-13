<%@page import="java.util.Locale"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- BEGIN HEADER -->
<div class="header navbar navbar-inverse navbar-fixed-top">
	<!-- BEGIN TOP NAVIGATION BAR -->
	<div class="navbar-inner">
		<div class="container-fluid">
			<!-- BEGIN LOGO -->
			<a href="<%=basePath%>"
				style="text-shadow: 0 1px 1px rgba(0, 0, 0, 0.4); font-size: 20px; font-weight: bold; color: white; text-decoration: none; margin-left: 10px; display: inline-block; margin-top: -1px; margin-right: 0; padding-left: 0; padding-right: 0; height: 38px; line-height: 38px;">
				<fmt:message key="posp.title" />
			</a>
			<!-- END LOGO -->
			<!-- BEGIN RESPONSIVE MENU TOGGLER -->
			<a href="javascript:;" class="btn-navbar collapsed"
				data-toggle="collapse" data-target=".nav-collapse"> <img
				src="<%=basePath%>media/image/menu-toggler.png" alt="" />
			</a>
			<!-- END RESPONSIVE MENU TOGGLER -->

			<!-- BEGIN TOP NAVIGATION MENU -->
			<ul class="nav pull-right">
				<li class="dropdown">
					<div style="margin-top: 5px;">
						<select id="language" onchange="changeLanguage() " class="small">
							<option value="">--Select--</option>
							<option value="zh">简体中文</option>
							<option value="en">English</option>
						</select>
					</div>
				</li>
				<!-- BEGIN USER LOGIN DROPDOWN -->
				<li class="dropdown user"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown"> <img alt=""
						src="<%=basePath%>media/image/avatar1_small.jpg" /> <span
						class="username" id="username"></span>
						<i class="icon-angle-down"></i>
				</a>
					<ul class="dropdown-menu">
						<!-- <li><a href="extra_profile.html"><i class="icon-user"></i> ä¸ªäººä¿¡æ¯</a></li> -->
						<!-- <li><a href="page_calendar.html"><i class="icon-calendar"></i> æçæ¥å</a></li>
							<li><a href="inbox.html"><i class="icon-envelope"></i> æ¶ä»¶ç®±(3)</a></li> -->
						<!-- <li><a href="#"><i class="icon-tasks"></i> æçä»»å¡</a></li> -->
						<!-- <li class="divider"></li> -->
						<!-- 
						<li><a href="<%=basePath%>merchant/detail.htm"><fmt:message
									key="base.conf" /></a></li>
						 -->
						<li><a href="<%=basePath%>auth/login.htm">
								<!-- <i class="icon-key"></i> -->
								<fmt:message key="login.out" />
						</a></li>
						<li><a href="<%=basePath%>auth/forget.htm">
								<!-- <i class="icon-key"></i> -->
								<fmt:message key="login.forget" />
						</a></li>
					</ul></li>
				<!-- END USER LOGIN DROPDOWN -->
			</ul>
			<!-- END TOP NAVIGATION MENU -->
		</div>
	</div>
	<!-- END TOP NAVIGATION BAR -->
</div>
<script type="text/javascript">
	var local = window.location;
	var contextPath = local.pathname.split("/")[1];
	var basePathUrl = local.protocol + "//" + local.host + "/" + contextPath;
	function changeLanguage() {
		$.ajax({
			type : "POST",
			url : basePathUrl + "/changeLanguage.json",
			data : "langType=" + $("#language").val(),
			dataType : "json",
			async : true,
			error : function(data, error) {
				alert("change lang error!");
			},
			success : function(data) {
				window.location.reload();
			}
		});
	}

	jQuery(document).ready(function() {

		$.ajax({
			type : "get",
			url : basePathUrl + "/user/detail.json",
			async : false,
			error : function(data, error) {
				alert("load error!");
			},
			success : function(data) {
				var user = data.user;
				if (user != null && user != '') {
					$("#username").text(user.userName);
				}
			}
		});
	});
</script>
<!-- END HEADER -->