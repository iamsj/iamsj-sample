<%@page import="org.springframework.util.CollectionUtils"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- BEGIN SIDEBAR -->
<div class="page-sidebar nav-collapse collapse">
	<!-- BEGIN SIDEBAR MENU -->
	<ul class="page-sidebar-menu">
		<li>
			<!-- BEGIN SIDEBAR TOGGLER BUTTON -->
			<div class="sidebar-toggler hidden-phone"></div> <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
		</li>
		<!-- <li class="start active ">
			<a href="index.html"> 
			<i class="icon-home"></i> 
			<span class="title">Dashboard</span> 
			<span class="selected"></span>
			</a>
		</li> -->
		<c:forEach items="${permList}" var="p">
			<li class="">
					<a href="javascript:;"> 
					<i class=""></i> 
					<span class="title"><spring:message code="${p.parentView.code}"></spring:message></span> 
					<span class="selected"></span> 
					<span class="arrow open"></span>
					</a>
				<ul class="sub-menu">
					<c:forEach items="${p.childView}" var="view">
						<li>
							<i class="active"></i>
							<a href="<%=basePath %>/${view.url}"><spring:message code="${view.code}"></spring:message></a>
						</li>
					</c:forEach>
				</ul>
			</li>
		</c:forEach>
	</ul>
	<!-- END SIDEBAR MENU -->
</div>