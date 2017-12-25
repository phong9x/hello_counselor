<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<div class="member page-content-wrapper counseling-history massage find-id">
    <div style="margin-bottom: 15px;" class="page-title">
        <div class="title"><span>아이디 및 비밀번호 찾기</span></div>
        <div class="title-ct"> <span>회원 아이디 및 비밀번호 찾기</span></div>
    </div>
    <div class="page-content inquiry">
        <div class="container">
            <div class="main-top border-full bg-fff">
                <div class="tabbable-custom">
                    <ul class="nav nav-tabs">
                        <li class="active">
                            <a href="#tab-11" data-toggle="tab"> <span>아이디 찾기</span><i aria-hidden="true" class="fa fa-caret-down"></i></a>
                        </li>
                        <li>
                            <a href="#tab-12" data-toggle="tab"> <span>비밀번호 찾기</span><i aria-hidden="true" class="fa fa-caret-down"></i></a>
                        </li>
                    </ul>

                    <div class="tab-content">
                        <div id="tab-11" class="tab-10 tab-pane active">
                            <div class="module-content">
                                <div id="form_1">
                                    <div class="main-item">
                                        <h5>회원정보에 등록한 정보와 동일한 정보를 입력해주세요.</h5>
                                        <div class="item-content">
                                            <div class="item">
                                                <div class="title">이름 </div>
                                                <div class="col-md-3">
                                                    <input type="text" name="fullname" data-required="1" class="form-control"/>
                                                </div>
                                            </div>
                                            <div class="item">
                                                <div class="title">휴대폰 번호 </div>
                                                <div class="item-row">
                                                    <div class="col-md-12">
                                                        <input type="text" name="phone" data-required="1" placeholder="-없이 입력" class="form-control"/>
                                                        <button class="sendToken">인증번호 발송</button>
                                                    </div>
                                                    <div class="col-md-12">
                                                        <input type="text" name="token" data-required="1" placeholder="인증번호 입력" class="form-control"/>
                                                        <button class="checkToken">인증번호 확인</button>
                                                        <span class="time" style="display: none;">
                                                            <span class="mm">02</span>:<span class="ss">59</span>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="main-submit">
                                        <button disabled id="btnForm1Submit">
                                            <span>다음</span>
                                        </button>
                                    </div>
                                </div>
                            </div>

                            <div class="module-content" id="has_content" style="display:none;">
                                <div class="main-item">
                                    <h5>회원정보에 등록한 정보와 동일한 정보를 입력해주세요.</h5>
                                    <div class="item-content">
                                        <div class="item">
                                            <div class="title">아이디(이메일) </div>
                                            <div class="col-md-3">
                                                <p id="has_content_id"></p>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="main-submit">
                                    <button style="width: 220px;" id="btnTriggerLogin">
                                        <i aria-hidden="true" class="fa fa-user-circle-o">&nbsp;&nbsp;</i><span>로그인 하기</span>
                                    </button>
                                </div>
                            </div>

                            <div class="module-content" id="no_content" style="display:none;">
                                <div class="main-item">
                                    <h4 class="text-center color888">일치하는 정보가 없습니다.</h4>
                                </div>
                                <div class="main-submit">
                                    <button style="width: 220px;" onclick="window.location.href='/public/register'">
                                        <i aria-hidden="true" class="fa fa-pencil">&nbsp;&nbsp;</i><span>심리상담사 등록 신청</span>
                                    </button>
                                </div>
                            </div>

                        </div>

                        <div id="tab-12" class="tab-10 tab-pane">
                            <div class="module-content">
                                <div id="form_2">
                                    <div class="main-item">
                                        <h5>회원정보에 등록한 정보와 동일한 정보를 입력해주세요.</h5>
                                        <div class="item-content">
                                            <div class="item" style="border-bottom: 1px dotted #cdcdcd;">
                                                <div class="title">이름 </div>
                                                <div class="col-md-3">
                                                    <input type="text" name="fullname" data-required="1" class="form-control"/>
                                                </div>
                                            </div>
                                            <div class="item">
                                                <div class="title">아이디(이메일)</div>
                                                <div class="col-md-3">
                                                    <input type="text" name="username" data-required="1" class="form-control"/>
                                                </div>
                                            </div>
                                            <div class="item">
                                                <div class="title">휴대폰 번호 </div>
                                                <div class="item-row">
                                                    <div class="col-md-12">
                                                        <input type="text" name="phone" data-required="1" placeholder="-없이 입력" class="form-control"/>
                                                        <button class="sendToken">인증번호 발송</button>
                                                    </div>
                                                    <div class="col-md-12">
                                                        <input type="text" name="token" data-required="1" placeholder="인증번호 입력" class="form-control"/>
                                                        <button class="checkToken">인증번호 확인</button>
                                                        <span class="time" style="display: none;">
                                                            <span class="mm">02</span>:<span class="ss">59</span>
                                                        </span>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="main-submit">
                                        <button disabled id="btnForm2Submit">
                                            <span>다음</span>
                                        </button>
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

<div id="findId_result">

</div>

<div id="popup_findid_notfound" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>입력하신 휴대폰 번호와 일치하는 정보가 없습니다.</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_findid_verified" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>인증되었습니다</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_findpwd_guide" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>임시 비밀번호를 휴대폰으로 전송하였습니다.임시 비밀번호로 로그인 하신 후 비밀번호를 변경해주세요.</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_mobilephone_verified" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>인증되었습니다</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_mobilephone_token_sent" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>인증번호가 전송되었습니다.</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_mobilephone_token_empty" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>인증번호를 입력해 주세요.</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_mobilephone_token_wrong" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>인증번호를 확인해주세요.</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<script>

    /******************************************************************
    *
    * Find ID
    *
    ******************************************************************/
    $('#popup_findid_notfound').on('click', '.popup_accept', function(evt) {
        $('#popup_findid_notfound').modal('hide');
        $('#tab-11').find('.module-content').css('display', 'none');
        $('#tab-11').find('#no_content').css('display', '');
    });

    $('#btnForm1Submit').on('click', function(evt) {
        $('#tab-11').find('.module-content').css('display', 'none');
        $('#tab-11').find('#has_content').css('display', '');
    });

    $('#btnTriggerLogin').on('click', function(evt) {
        evt.preventDefault();
        $('#dLabel').trigger('click');
    });

    var timerId;
    $('#form_1').on('click', '.sendToken', function(evt) {
        if (timerId) {
            clearInterval(timerId);
        }
        evt.preventDefault();
        var $parent     = $('#form_1');
        var fullname    = $parent.find('[name=fullname]').val();
        var phone       = $parent.find('[name=phone]').val();
        toggleLoading();
        $
            .ajax({
                url: '/public/findid',
                method: 'post',
                data: {
                    fullname    : fullname,
                    phone       : phone
                }
            })
            .then(function(res) {
            	toggleLoading();
            	$('#popup_mobilephone_token_sent').modal('show');
                //console.log(res);

                var token 		= res.token;
                var id          = res.id;
                var countdown 	= 180;
                $parent.find('.time').show();
                timerId 	    = setInterval(function() {
                    countdown--;
                    if (countdown <= 0) clearInterval(timerId);

                    var mm = parseInt(countdown/60)
                    var ss = countdown - mm*60;

                    $parent.find('.mm').text(mm < 10 ? '0' + mm : mm);
                    $parent.find('.ss').text(ss < 10 ? '0' + ss : ss);

                }, 1000);

                $parent.find('.checkToken').off('click').on('click', function(evt) {
                    if (countdown <= 0) return;
                    clearInterval(timerId);

                    var _token = $parent.find('[name=token]').val();

                    if (!_token) $('#popup_mobilephone_token_empty').modal('show');
                    else if (_token != token) $('#popup_mobilephone_token_wrong').modal('show');
                    else {
                        $('#popup_findid_verified').modal('show');
                        // draw result
                        $('#has_content_id').text(id);
                        $('#btnForm1Submit').removeAttr('disabled');
                    }
                });
            }, function(err) {
            	toggleLoading();
                $('#popup_findid_notfound').modal('show');
            });
    });



    /******************************************************************
    *
    * Find Password
    *
    ******************************************************************/
    var timerPwd;
    $('#form_2').on('click', '.sendToken', function(evt) {
        if (timerPwd) {
            clearInterval(timerPwd);
        }
        evt.preventDefault();
        var $parent     = $('#form_2');
        var fullname    = $parent.find('[name=fullname]').val();
        var username    = $parent.find('[name=username]').val();
        var phone       = $parent.find('[name=phone]').val();
        toggleLoading();
        $
            .ajax({
                url: '/public/findpwd',
                method: 'post',
                data: {
                    fullname    : fullname,
                    username    : username,
                    phone       : phone
                }
            })
            .then(function(res) {
            	toggleLoading();
                //console.log(res);
                $('#popup_mobilephone_token_sent').modal('show');
                var token 		= res.token;
                var countdown 	= 180;
                $parent.find('.time').show();
                timerId 	    = setInterval(function() {
                    countdown--;
                    if (countdown <= 0) clearInterval(timerId);

                    var mm = parseInt(countdown/60)
                    var ss = countdown - mm*60;

                    $parent.find('.mm').text(mm < 10 ? '0' + mm : mm);
                    $parent.find('.ss').text(ss < 10 ? '0' + ss : ss);

                }, 1000);

                $parent.find('.checkToken').off('click').on('click', function(evt) {
                    if (countdown <= 0) return;
                    clearInterval(timerId);

                    var _token = $parent.find('[name=token]').val();

                    if (!_token) $('#popup_mobilephone_token_empty').modal('show');
                    else if (_token != token) $('#popup_mobilephone_token_wrong').modal('show');
                    else {
                        // draw result
                        $('#popup_findid_verified').modal('show');                        
                        $('#btnForm2Submit').removeAttr('disabled');
                    }
                });

                $('#btnForm2Submit').off('click').on('click', function(evt) {

                    $('#popup_findpwd_guide').modal('show');

                    $
                        .ajax({
                            url: '/public/findpwd/confirm',
                            method: 'post',
                            data: {
                                fullname    : fullname,
                                username    : username,
                                phone       : phone
                            }
                        })
                        .then(function(res) {
                            console.log(res);
                        }, function(err) {
                            console.error(err);
                        });
                });

            }, function(err) {
            	toggleLoading();
                console.error(err);
            });
    });

</script>

