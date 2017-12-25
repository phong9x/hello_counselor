<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<html>
	<head>
		<meta charset="utf-8"/>
	    <title>Hello || <tiles:insertAttribute name="title" /></title>
	    <meta name="viewport" 		content="width=1280, initial-scale=1.0" />
	    <meta name="description" 	content="Hello Application" />
	    <link rel="shortcut icon" href="/theme/admin/assets/images/icon/favicon.ico" type="image/x-icon">
	    <!-- Web Fonts-->
	
	    <!-- Vendor CSS (GLOBAL MANDATORY STYLES)-->
	    <link rel="stylesheet" type="text/css" href="/theme/admin/libs/metronic-admin/global/plugins/font-awesome/css/font-awesome.min.css"/>
	    <link rel="stylesheet" type="text/css" href="/theme/admin/libs/metronic-admin/global/plugins/simple-line-icons/simple-line-icons.min.css"/>
	    <link rel="stylesheet" type="text/css" href="/theme/admin/libs/metronic-admin/global/plugins/bootstrap/css/bootstrap.min.css"/>
	    <link rel="stylesheet" type="text/css" href="/theme/admin/libs/metronic-admin/global/plugins/uniform/css/uniform.default.css"/>
	    <link rel="stylesheet" type="text/css" href="/theme/admin/libs/metronic-admin/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css"/>
	
	    <!-- Vendor CSS (GLOBAL PAGE LEVEL STYLES)-->
	    <link rel="stylesheet" type="text/css" href="/theme/admin/libs/metronic-admin/global/plugins/select2/select2.css"/>
	    <link rel="stylesheet" type="text/css" href="/theme/admin/libs/metronic-admin/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css"/>
	    <link rel="stylesheet" type="text/css" href="/theme/admin/libs/metronic-admin/global/plugins/bootstrap-daterangepicker/daterangepicker-bs3.css"/>
	    <link rel="stylesheet" type="text/css" href="/theme/admin/libs/metronic-admin/global/plugins/fullcalendar/fullcalendar.min.css"/>
	    <link rel="stylesheet" type="text/css" href="/theme/admin/libs/metronic-admin/global/plugins/owl.carousel/assets/owl.carousel.css">
	
	    <!-- Vendor CSS (THEME STYLES)-->
	    <link rel="stylesheet" type="text/css" href="/theme/admin/libs/metronic-admin/global/css/components.css"/>
	    <link rel="stylesheet" type="text/css" href="/theme/admin/libs/metronic-admin/global/css/plugins.css"/>
	    <link rel="stylesheet" type="text/css" href="/theme/admin/libs/metronic-admin/admin/layout/css/layout.css"/>
	    <link rel="stylesheet" type="text/css" href="/theme/admin/libs/metronic-admin/admin/layout/css/themes/darkblue.css"/>
	 	<link rel="stylesheet" type="text/css" href="/theme/admin/assets/css/bootstrap-select.min.css"/>
	    <!-- Theme CSS-->
	    <link rel="stylesheet" type="text/css" href="/theme/admin/assets/css/admin-countselor.css"/>
	    
		<script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/plugins/jquery.min.js"></script>
		<script type="text/javascript" src="/theme/admin/assets/js/counselor.js"></script>
  		<script type="text/javascript" src="/theme/admin/assets/js/countselor_js.js"></script>
	    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries-->
	    <!-- WARNING: Respond.js doesn't work if you view the page via file://-->
	    <!--if lt IE 9
	    script(src='https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js')
	    script(src='https://oss.maxcdn.com/respond/1.4.2/respond.min.js')
	    -->
	</head>
	<body class="page-header-fixed page-quick-sidebar-over-content">

	    <tiles:insertAttribute name="heading" />

	    <div class="clearfix"></div>

        <tiles:insertAttribute name="body" />

        <tiles:insertAttribute name="footer" />
		
		<script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/plugins/jquery-migrate.min.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/plugins/jquery-ui/jquery-ui.min.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/plugins/bootstrap/js/bootstrap.min.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/plugins/jquery.blockui.min.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/plugins/jquery.cokie.min.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/plugins/uniform/jquery.uniform.min.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js"></script>
	
	    <!-- Vendor jQuery (PAGE LEVEL PLUGINS - METRONIC)-->
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/plugins/jquery-validation/js/jquery.validate.min.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/plugins/jquery-validation/js/additional-methods.min.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/plugins/select2/select2.min.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.kr.min.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/plugins/ckeditor/ckeditor.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/plugins/ckeditor/adapters/jquery.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/plugins/ckeditor/lang/ko.js"></script>
	    <!-- IMPORTANT! fullcalendar depends on jquery-ui.min.js for drag & drop support, and moment.min.js-->
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/plugins/moment.min.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/plugins/fullcalendar/fullcalendar.min.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/plugins/fullcalendar/lang/ko.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/plugins/owl.carousel/owl.carousel.min.js"></script>
	
	    <!-- Vendor jQuery (PAGE LEVEL STYLES - METRONIC)-->
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/scripts/metronic.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/admin/layout/scripts/layout.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/admin/layout/scripts/quick-sidebar.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/admin/layout/scripts/demo.js"></script>
	
	    <!-- Theme Script-->
		<script type="text/javascript" src="/theme/admin/assets/js/bootstrap-select.min.js"></script>

        <script type="text/javascript">
            localStorage.removeItem('setting');
        </script>
	</body>
</html>