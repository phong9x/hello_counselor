<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<div class="page-header navbar">
    <div class="page-header-inner container">
        <!-- BEGIN LOGO-->
        <div class="page-logo">
            <a href="${empty sessionScope.loggedInUser ? '/' : '/auth/home'}">
                <img src="/theme/admin/assets/images/logo/logo11.png" alt="logo" class="logo-default"/>
                <span>심리상담사 전용 페이지</span>
            </a>
        </div>
        <!-- .page-logo-->

        <a href="javascript:;" data-toggle="collapse" data-target=".navbar-collapse" class="menu-toggler responsive-toggler"></a>
        <!-- .responsive-toggler-->

        <div class="top-menu">
            <c:if test="${not empty sessionScope.loggedInUser}">
                <div class="top-accmenu acount">
                    <div class="dropdown">
                        <div id="dLabel" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="acc-name">
                            <span class="avatar">
                                <img src="${sessionScope.loggedInUser.avatar}" alt="avatar" class="logo-default"/>
                            </span>
                            <span class="name">${sessionScope.loggedInUser.fullname}</span>
                            <span class="icon"><i class="fa fa-angle-down" aria-hidden="true"></i></span>
                        </div>

                        <div aria-labelledby="dLabel" class="dropdown-menu">
                            <div class="title">
                                <span>${sessionScope.loggedInUser.fullname}</span>
                                <span class="normal">${sessionScope.loggedInUser.category} 심리상담사</span>
                            </div>
                            <div class="acount-content">
                                <div class="item-acount">
                                    <a href="/auth/info/detail#tab-11" class="quicklink"><span class="icon iconacc"></span><span class="text">기본 정보 수정</span><span class="icon-next"><i class="fa fa-angle-right" aria-hidden="true"></i></span></a>
                                </div>
                                <div class="item-acount">
                                    <a href="/auth/info/detail#tab-12" class="quicklink"><span class="icon iconsetting"></span><span class="text">서비스 노출 정보 수정</span><span class="icon-next"><i class="fa fa-angle-right" aria-hidden="true"></i></span></a>
                                </div>
                            </div>
                            <div class="acc-footer">
                                <span>최근 로그인   <fmt:formatDate value="${sessionScope.loggedInUser.lastLogin}" pattern="yyyy-MM-dd HH:mm:ss" /></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="top-logout">
                    <button id="btn_logout">로그아웃</button>
                </div>

            </c:if>

            <c:if test="${empty sessionScope.loggedInUser}">
                <div class="top-accmenu">
                    <div class="dropdown">
                        <div id="dLabel" type="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" class="acc-name">
                            <span class="name">로그인</span>
                        </div>
                        <div aria-labelledby="dLabel" class="dropdown-menu">
                            <p class="title">심리상담사 로그인</p>
                            <form class="form-horizontal" id="form_login" autocomplete="off">
                                <div class="acc-content">
                                    <div class="item">
                                        <input type="text" id="username" name="username" class="form-control" />
                                        <p class="note-error" style="display:none">아이디를 입력해주세요.</p>
                                    </div>
                                    <div class="item">
                                        <input type="password" id="password" name="password" class="form-control" />
                                        <p class="note-error" style="display:none">비밀번호 입력해주세요.</p>
                                    </div>
                                    <button type="submit" class="btn green submit">로그인</button>
                                </div>
                            </form>
                            <div class="acc-footer">
                                <button class="v1 btn-changepass" onclick="window.location='/public/findidpwd'">아이디/비밀번호 찾기</button>
                                <button class="v2" onclick="window.location='/public/register'">심리상담사 등록 바로가기</button>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>

        </div>
        <!-- .top-menu-->
    </div>
</div>
<!-- .page-header-->

<div id="popupLoginFailed" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
            </div>
            <div class="modal-body">
                <h4>
                    <strong id="error_msg">아이디 또는 비밀번호가 일치하지 않습니다.</strong>
                </h4>
            </div>
            <div class="modal-footer">
                <button type="button" data-dismiss="modal" class="btn btn-default">확인</button>
            </div>
        </div>
    </div>
</div>
<!-- .popupConfirmDelete-->

<script>
    $('#btn_logout').on('click', function(evt) {
        evt.preventDefault();
        $
            .ajax({
                url     : '/auth/logout',
                method  : 'get'
            })
            .then(function(res) {
                window.location.href = '/';
            }, function(err) {
                console.error(err);
            });
    });

    $('#form_login').on('submit', function(evt) {
        evt.preventDefault();

        // form validation
        var isError   = false;
        $('p.note-error').css('display', 'none');
        var $username = $('#username');
        var $password = $('#password');
        var _username = $username.val();
        var _password = $password.val();

        if (_username.length === 0) {
            $username.next('p').css('display', '');
            isError = true;
        }
        if (_password.length === 0) {
            $password.next('p').css('display', '');
            isError = true;
        }

        if (isError) {
            return false;
        }

        $
            .ajax({
                url         : '/public/home',
                method      :'post',
                contentType : 'application/json; charset=UTF-8',
                beforeSend  : toggleLoading,
                data        : JSON.stringify({
                    username    : _username,
                    password    : _password,
                })
            })
            .then(function(res) {
                window.location.href = '/auth/home';
            }, function(err) {
                toggleLoading();
                var errorMsg = "아이디 또는 비밀번호가 일치하지 않습니다.";

                if (err.responseText == -1) {
                    errorMsg = "아이디 또는 비밀번호가 일치하지 않습니다.";
                } else if (err.responseText == 0) {
                    errorMsg = "상담사 등록 신청이 진행 중입니다.영업일 기준 5일 정도 소요될 수 있습니다.";
                } else if (err.responseText == 3) {
                    errorMsg = "상담사 등록이 보류되었습니다. 메일로 보내드린 보류 사유 확인 후 다시 등록 신청을 해주세요.";
                } else if (err.responseText == 9) {
                	errorMsg = "탈퇴처리된 계정입니다.";
                }

                $('#error_msg').text(errorMsg);
                $('#popupLoginFailed').modal('show');
            });
    });

    $('.quicklink').on('click', function(evt) {
        evt.preventDefault();
        var _href = $(this).attr('href');
        window.location.href = _href;
        if (location.pathname.startsWith('/auth/info/detail')) {
            window.location.reload();
        }
    });

</script>

<div class="clearfix"></div>