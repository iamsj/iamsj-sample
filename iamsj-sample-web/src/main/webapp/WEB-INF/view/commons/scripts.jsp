<html>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<head>
<link href="<%=basePath%>media/css/bootstrap.min.css" rel="stylesheet"
	type="text/css" />

<link href="<%=basePath%>media/css/bootstrap-responsive.min.css"
	rel="stylesheet" type="text/css" />

<link href="<%=basePath%>media/css/font-awesome.min.css"
	rel="stylesheet" type="text/css" />

<link href="<%=basePath%>media/css/style-metro.css" rel="stylesheet"
	type="text/css" />

<link href="<%=basePath%>media/css/style.css" rel="stylesheet"
	type="text/css" />

<link href="<%=basePath%>media/css/style-responsive.css"
	rel="stylesheet" type="text/css" />

<link href="<%=basePath%>media/css/default.css" rel="stylesheet"
	type="text/css" id="style_color" />

<link href="<%=basePath%>media/css/uniform.default.css" rel="stylesheet"
	type="text/css" />

<link href="<%=basePath%>media/css/bootstrap-datetimepicker.min.css"
	rel="stylesheet" type="text/css" />


<!-- END GLOBAL MANDATORY STYLES -->

<link rel="shortcut icon" href="<%=basePath%>media/image/favicon.ico" />

<!-- BEGIN CORE PLUGINS -->
<script src="<%=basePath%>media/js/jquery-1.11.3.min.js"
	type="text/javascript"></script>

<script src="<%=basePath%>media/js/jquery-migrate-1.2.1.min.js"
	type="text/javascript"></script>

<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

<script src="<%=basePath%>media/js/jquery-ui-1.10.1.custom.min.js"
	type="text/javascript"></script>

<script src="<%=basePath%>media/js/bootstrap.min.js"
	type="text/javascript"></script>

<!--[if lt IE 9]>

	<script src="<%=basePath%>media/js/excanvas.min.js"></script>

	<script src="<%=basePath%>media/js/respond.min.js"></script>  

<![endif]-->

<script src="<%=basePath%>media/js/breakpoints.min.js"
	type="text/javascript"></script>

<script src="<%=basePath%>media/js/jquery.slimscroll.min.js"
	type="text/javascript"></script>

<script src="<%=basePath%>media/js/jquery.blockui.min.js"
	type="text/javascript"></script>

<script src="<%=basePath%>media/js/jquery.cookie.min.js"
	type="text/javascript"></script>

<script src="<%=basePath%>media/js/jquery.uniform.min.js"
	type="text/javascript"></script>

<script src="<%=basePath%>media/js/bootstrap-datetimepicker.min.js"
	type="text/javascript"></script>

<script src="<%=basePath%>media/js/bootstrap-datetimepicker.zh-CN.js"
	type="text/javascript"></script>

<script type="text/javascript"
	src="<%=basePath%>media/js/jquery.validate.min.js"></script>
	
<script type="text/javascript"
	src="<%=basePath%>media/js/additional-methods.min.js"></script>

<!-- END CORE PLUGINS -->

<script src="<%=basePath%>media/js/app.js"></script>
</head>
<body>
</body>
</html>