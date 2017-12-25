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

		<div class="page-container">
			<tiles:insertAttribute name="menu" />
            <tiles:insertAttribute name="body" />
            <tiles:insertAttribute name="footer" />
		</div>

		<!-- real time popup -->
		<div id="popup-realtime" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk popupLogout-facetalk">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4>실시간 상담 요청</h4>
                        <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
                    </div>
                    <div class="modal-body"><strong>실시간 상담 요청이 들어왔습니다.</strong>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn close-face-popup popup_accept">상담 정보 확인</button>
                    </div>
                </div>
            </div>
        </div>

        <div id="popup-schedule" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk popupLogout-facetalk">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4>예약 안내</h4>
                        <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
                    </div>
                    <div class="modal-body">
                        <p><strong>새로운 상담 예약이 등록되었습니다.</strong></p>
                        <p><strong>상담 관리 > 상담 메뉴에서 확인해보세요.</strong></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn close-face-popup popup_accept">예약 정보 확인</button>
                    </div>
                </div>
            </div>
        </div>

        <div id="popup-psypayment" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk popupLogout-facetalk">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4>심리검사 결제완료 안내</h4>
                        <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
                    </div>
                    <div class="modal-body">
                        <p><strong><span class="member-info"></span>님이 심리검사 결제를 완료하였습니다.</strong></p>
                        <p><strong>심리검사 사이트에 접속하셔서 인증코드를 발송해주세요.</strong></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn close-face-popup popup_accept">심리검사 정보 확인</button>
                    </div>
                </div>
            </div>
        </div>

        <div id="popup-message" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk popupLogout-facetalk">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4>쪽지 수신 안내</h4>
                        <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
                    </div>
                    <div class="modal-body"><strong><span class="member-info"></span>님이 쪽지를 보냈습니다.</strong>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn close-face-popup popup_accept">쪽지 정보 확인</button>
                    </div>
                </div>
            </div>
        </div>
		
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
	    <script type="text/javascript" src="/theme/admin/assets/libs/starwars.js"></script>
	    <script type="text/javascript" src="/theme/admin/assets/libs/bootstrap-rating.js"></script>
	    <script type="text/javascript" src="/theme/admin/assets/libs/Chart.min.js"></script>
	
	    <!-- Vendor jQuery (PAGE LEVEL STYLES - METRONIC)-->
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/global/scripts/metronic.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/admin/layout/scripts/layout.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/admin/layout/scripts/quick-sidebar.js"></script>
	    <script type="text/javascript" src="/theme/admin/libs/metronic-admin/admin/layout/scripts/demo.js"></script>
	
	    <!-- Theme Script-->
		<script type="text/javascript" src="/theme/admin/assets/js/bootstrap-select.min.js"></script>
		<script type="text/javascript" src="/theme/admin/assets/js/jquery.popupoverlay.js"></script>
		<script type="text/javascript" src="/theme/admin/assets/js/polling.js"></script>

		<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.6/handlebars.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/underscore.js/1.8.3/underscore-min.js"></script>

	    <script type="text/javascript">
	        // init menu
	        //// open menu
            $('ul.page-sidebar-menu').on('mouseenter', 'li', function(evt) {
                $('.page-dropdow-menu').css('display', 'block');
            });

            //// close menu
            $('.page-dropdow-menu').on('mouseleave', function() {
                $(this).css('display', 'none');
                $('.menu-sub1').removeClass('active');
            })

            //// set active menu
            $('.menu-sub1').on('hover', function() {
                var $menu = $(this).closest('.menu-sub1');
                if (!$menu.hasClass('active')) {
                    // reset all
                    $('.menu-sub1').removeClass('active');
                    // active this menu
                    $menu.addClass('active');
                }
            });
	    </script>
	    
	</body>
</html>