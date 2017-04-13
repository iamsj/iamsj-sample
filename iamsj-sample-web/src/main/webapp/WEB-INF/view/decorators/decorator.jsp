<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../commons/scripts.jsp"%>
<html>
<head>
<meta charset="utf-8" />
<title>POSP交易管理后台</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta content="" name="description" />
<meta content="" name="author" />
<script>
	$().ready(function() {
		App.init(); // initlayout and core plugins
		
		var msgFlag="${create_msg}";
		if(msgFlag=="success"){
			alert("创建成功");
		}else if(msgFlag=="error"){
			alert("创建失败");
		}

	});

</script>
</head>
<body class="page-header-fixed page-sidebar-fixed page-footer-fixed">
	<!-- BEGIN HEADER -->
	<jsp:include page="../commons/header.jsp"></jsp:include>	
	<!-- END HEADER -->
	<!-- BEGIN CONTAINER -->
	<div class="page-container row-fluid">		
		<jsp:include page="/menus.htm"></jsp:include>		
		<!-- END SIDEBAR -->
		<!-- <div class="page-content" id="content"></div> -->
		<div class="page-content" >
		 	<sitemesh:write property='body'>Body goes here. Blah blah blah.</sitemesh:write>
		 </div> 
		<!-- BEGIN PAGE -->
		<!-- END PAGE -->
	</div>
	<!-- END CONTAINER -->
<jsp:include page="../commons/footer.jsp"></jsp:include>	
	
	<!-- END JAVASCRIPTS -->
</body>
</html>
