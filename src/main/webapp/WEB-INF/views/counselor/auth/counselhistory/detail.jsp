<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<div class="member page-content-wrapper counseling-history detail">
    <div class="page-title">
        <div class="title">
            <span>상담내역</span>
        </div>
        <div class="title-ct">
            <span>지난 상담내역 확인이 가능합니다.</span>
        </div>
    </div>
    <div class="page-content">
        <div class="container">
            <div class="main-content border-full bg-fff">
                <div class="content-main">
                    <div class="main-ct">
                        <div style="margin-bottom: 40px;" class="main-item">
                            <div class="item-title">
                                <span>상세정보</span>
                            </div>
                            <div class="item-content">
                                <div class="item">
                                    <div class="item-left">
                                        <span class="title">상담번호</span>
                                        <div class="left-ct">
                                            <span>${session.id}</span>
                                        </div>
                                    </div>
                                    <div class="item-right">
                                        <span class="title">회원명 / 아이디</span>
                                        <div class="left-ct">
                                            <span><c:out value="${session.user.fullname}" /> / <c:out value="${session.user.email}" /></span>
                                            <button id="btnCreateMessage">쪽지쓰기</button>
                                        </div>
                                    </div>
                                </div>
                                <div class="item">
                                    <div class="item-left">
                                        <span class="title">상담 시작</span>
                                        <div class="left-ct">
				                        	<c:choose>
				                             	<c:when test="${session.counselType == 1 && session.entryCounselorTime != null }">
				                             		<c:set var="startTime" value="${session.entryCounselorTime}"></c:set>
				                             	</c:when>
				                             	<c:otherwise>
				                             		 <c:set var="startTime" value="${session.startTime}"></c:set>
				                             	</c:otherwise>
				                             </c:choose>
                                            <span><fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd (E) HH:mm" /></span>
                                        </div>
                                    </div>
                                    
                                    <div class="item-right">
                                        <span class="title">상담 종료</span>
                                        <div class="left-ct">
                                            <span><fmt:formatDate value="${session.endTime}" pattern="yyyy-MM-dd (E) HH:mm" /></span>
                                        </div>
                                    </div>
                                    
                                </div>
                                <div class="item">
                                    <div class="item-left">
                                        <span class="title">요청 상담 시간</span>
                                        <div class="left-ct">
                                            <span>
                                                ${session.counselingTimeType}분
                                            </span>
                                        </div>
                                    </div>
                                    <div class="item-right">
                                        <span class="title"> 실제 상담 시간</span>
                                        <div class="left-ct">
                                            <span>
                                                <jsp:useBean id="counselTime" class="java.util.Date"/>
                                                <c:set target="${counselTime}" property="time" value="${session.endTime.getTime() - startTime.getTime()}" />
                                                <fmt:formatDate value="${counselTime}" pattern="mm 분 ss 초 " />
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="item">
                                    <div class="item-left">
                                        <span class="title">결제 구분</span>
                                        <div class="left-ct">
                                            <span>
                                                <c:choose>
                                                    <c:when test="${1 == session.paymentHistory.typeCoin}">
                                                        예치금 사용
                                                    </c:when>
                                                    <c:otherwise>
                                                        상담권 사용
                                                    </c:otherwise>
                                                </c:choose>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="item-right">
                                        <span class="title">상태</span>
                                        <div class="left-ct">
                                            <span>
                                                <c:choose>
                                                    <c:when test="${2 == session.status}">
                                                        상담완료
                                                    </c:when>
                                                    <c:otherwise>
                                                        환급
                                                    </c:otherwise>
                                                </c:choose>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="item">
                                    <div class="item-left">
                                        <span class="title">만족도 평가</span>
                                        <div class="left-ct">
                                            <span>
                                                <c:choose>
                                                    <c:when test="${not empty satisfaction}">
                                                        <input type="number" data-readonly="" value="${satisfaction.point}" class="rating" style="display:none;"/> (${satisfaction.point})
                                                    </c:when>
                                                    <c:otherwise>
                                                        ---
                                                    </c:otherwise>
                                                </c:choose>
                                            </span>
                                            <c:choose>
                                                <c:when test="${not empty satisfaction}">
                                                    <button id="btnSatisfaction" data-point="${satisfaction.point}" data-comment="${satisfaction.comment}">만족도 후기 보기</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button id="btnSatisfaction" disabled>만족도 후기 보기</button>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                    <div class="item-right">
                                        <span class="title"> 문진 정보 보기</span>
                                        <div class="left-ct">
                                            <span>
                                                <a href="javascript:;" id="btnMindCheck" style="color:#526fd1">마인드체크 정보 보기</a>
                                                &nbsp;/&nbsp;
                                                <a href="javascript:;" id="btnSelfDiagnosis" style="color:#526fd1">자가진단 정보 보기</a>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div style="margin-bottom: 20px;" class="main-item">
                            <div class="item-title">
                                <span>상담 메모</span>
                                <span class="note-title">상담 시 입력했던 내용</span>
                                <span class="edit" id="btnCreateMemo">작성</span>
                            </div>
                            <div class="item-content">
                                <c:if test="${not empty memos.content}">
                                    <c:set var="memoDate" value="" />
                                    <c:forEach items="${memos.content}" var="item" varStatus="i">

                                        <fmt:formatDate value="${item.createDate}" pattern="yyyy/MM/dd" var="_memoDate" />

                                        <c:choose>
                                            <c:when test="${memoDate ne _memoDate}">
                                                <c:if test="${memoDate ne ''}">
                                                    </div>
                                                </c:if>

                                                <div class="itemv2">
                                                    <span class="title">
                                                        <fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd (E)" />
                                                    </span>
                                                    <div class="item-right">
                                                        <span><c:out value="${item.memo}" /></span>
                                                        <div class="right-remove">
                                                            <div class="date-time">
                                                                <span><fmt:formatDate value="${item.createDate}" pattern="HH:mm:ss" /></span>
                                                                <span class="remove btnMemoDelete" data-id="${item.id}"></span>
                                                            </div>
                                                        </div>
                                                    </div>
                                            </c:when>

                                            <c:otherwise>

                                                <div style="border-top: 1px solid #e5e5e5;" class="item-right">
                                                    <span><c:out value="${item.memo}" /></span>
                                                    <div class="right-remove">
                                                        <div class="date-time">
                                                            <span><fmt:formatDate value="${item.createDate}" pattern="HH:mm:ss" /></span>
                                                            <span class="remove btnMemoDelete" data-id="${item.id}"></span>
                                                        </div>
                                                    </div>
                                                </div>

                                                <c:if test="${memos.totalElements eq (i.index + 1)}">
                                                    </div>
                                                </c:if>

                                            </c:otherwise>
                                        </c:choose>

                                        <c:set var="memoDate" value="${_memoDate}" />

                                    </c:forEach>
                                </c:if>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="main-submit">
                <button onclick="window.location='/auth/counselhistory/list'">
                    <span>목록 보기</span>
                </button>
            </div>
        </div>
    </div>
</div>

<!-- popup -->
<div id="popup_delete_memo" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>상담 메모를 삭제하시겠습니까? 삭제하시면 복구되지 않습니다.</strong>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal">취소</button>
                <button type="button" class="btn close-face-popup popup_accept" id="accept">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_create_memo" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>상담 메모 작성</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <textarea id="memoBody" placeholder="남길 메모를 작성해주세요" rows="10" style="width: 100%; resize: none;"></textarea>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" id="accept">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_create_message" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="popup-psychlogical23 modal fade popup-facetalk">
    <div class="modal-dialog" style="width: 810px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body popup3">
                <div class="modal-ct">
                    <div class="modal-title">
                        <span>받는 회원 선택</span>
                    </div>
                    <div class="modal-main">
                        <ul>
                            <li>
                                <div class="title">
                                    <div data-error-container="#form_2_services_error" class="checkbox-list input-checkboxstyle2">
                                        <label>
                                            <input type="radio" value="1" name="service" checked="checked" class="radio1"/>
                                        </label>
                                    </div>
                                </div>
                                <span class="ct"><c:out value="${session.user.fullname}" /></span>
                                <span><c:out value="${session.user.email}" /></span>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="modal-ct">
                    <div class="modal-title">
                        <span>쪽지 내용 입력</span>
                        <span class="total"><span id="msgRemain">0</span> / 500</span>
                    </div>
                    <div class="modal-main">
                        <textarea id="msgBody" rows="6" maxlength="500" data-error-container="#editor2_error" class="rw-ckeditor form-control"></textarea>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <a id="accept" class="link1">쪽지 보내기</a>
            </div>
        </div>
    </div>
</div>

<div id="popup_msg_complete" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>발송이 완료 되었습니다.</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept_close_complete" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_msg_empty" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>쪽지 내용을 입력하세요.</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<script id="tpl_mindcheck" type="text/x-handlebars-template">

    {{#each mindchecks}}

        <div class="item panel panel-default">
            <div class="item-header">
                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse{{@index}}" aria-expanded="true" aria-controls="collapse{{@index}}" class="collapsed">
                    <div class="col-md-6 text-center">
                        {{{fmtDate createdDate }}}
                    </div>
                    <div class="col-md-6 text-center">
                        <span>{{{ point }}}점</span><i class="fa fa-angle-down" aria-hidden="true"></i>
                        <i class="fa fa-angle-up" aria-hidden="true"></i>
                    </div>
                </a>
            </div>
            <div id="collapse{{@index}}" role="tabpanel" aria-labelledby="heading{{@index}}" class="panel-collapse collapse">
                <div class="panel-body">
                    {{#each data}}
                        <div class="drop-item">
                            <div class="col-md-6">
                                <p>{{{ question }}}</p>
                            </div>
                            <div class="col-md-6">
                                <p>{{{ answer }}}</p>
                            </div>
                        </div>
                    {{/each}}
                </div>
            </div>
        </div>

    {{/each}}

</script>

<script id="tpl_selfDiagnosis" type="text/x-handlebars-template">
    {{#each test}}
        <div class="item">
           <div class="item-left">{{{ createDate }}}</div>
           <div class="item-right">{{{ point }}}</div>
        </div>
    {{else}}
         <div class="item-left">결과가 없습니다.</div>
    {{/each}}
</script>

<div id="popup_mindcheck_list" data-cache="false" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk popup-faq">
    <div class="modal-dialog popup-faq-new">
        <div class="modal-content">
            <div class="modal-header">
                <h4> <span>마인드체크 정보</span></h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <div class="title">
                    <p>마인드체크는 상담 시 위기사례 및 자살사고에 대한 인지에 도움이 되기 위한 간단한 설문입니다. 인적사항 외 심리상태 질문은 총 6문항이며, 배점은 0~4점으로 이루어집니다. 총 점은 24점이며, 18점 이상인 경우 위기사례에 해당합니다.</p>
                </div>
                <div class="modal-list counseling-history detail notice-detail">
                    <div class="main-ct return-faq">
                        <div class="main-item">
                            <div id="accordion" class="item-content">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="popup-self-diagnosis-test-list" data-cache="false" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div style="width:600px;" class="modal-dialog popup-faq-tab">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="text-center"><span>자가진단 정보</span></h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <div class="tabbable-custom">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab-11" data-toggle="tab"> <span>스트레스</span><i aria-hidden="true" class="fa fa-caret-down"></i></a></li>
                        <li><a href="#tab-12" data-toggle="tab"> <span>우울</span><i aria-hidden="true" class="fa fa-caret-down"></i></a></li>
                        <li><a href="#tab-13" data-toggle="tab"> <span>불안</span><i aria-hidden="true" class="fa fa-caret-down"></i></a></li>
                    </ul>

                    <div class="tab-content">
                        <div id="tab-11" class="tab-10 tab-pane active">
                            <p class="title">스트레스 자가진단 항목은 총 5개 문항이며, 5문항의 평균값으로 결과를 산출합니다.</p>
                            <div class="tab-ct-top">
                                <div class="item color">낮음  </div>
                                <div class="item color">중간  </div>
                                <div class="item color">높음 </div>
                            </div>
                            <div class="tab-ct-top">
                                <div class="item">0 ~ 1.6  </div>
                                <div class="item">1.8 ~ 2.6  </div>
                                <div class="item">2.8 이상  </div>
                            </div>
                            <div class="tab-content" id="accordionStress">
                        
                            </div>
                        </div>
                        <div id="tab-12" class="tab-10 tab-pane">
                    	    <p class="title">우울 자가진단 항목은 총 20개 문항이며, 20문항의 총점으로 결과를 산출합니다.</p>
						    <div class="tab-ct-top">
                                <div class="item color">낮음  </div>
                                <div class="item color">중간  </div>
                                <div class="item color">높음 </div>
						    </div>
						    <div class="tab-ct-top">
                                <div class="item">0 ~ 20  </div>
                                <div class="item">21 ~ 24  </div>
                                <div class="item">25 ~ 60  </div>
						    </div>
						    <div class="tab-content" id="accordionDepressed">
                        
                      	    </div>
                        </div>
                        <div id="tab-13" class="tab-10 tab-pane">
                    	    <p class="title">불안 자가진단 항목은 총 21개 문항이며, 21문항의 총점으로 결과를 산출합니다.</p>
						    <div class="tab-ct-top">
                                <div class="item color">낮음  </div>
                                <div class="item color">중간  </div>
                                <div class="item color">높음 </div>
						    </div>
						    <div class="tab-ct-top">
                                <div class="item">0 ~ 21  </div>
                                <div class="item">22 ~ 35  </div>
                                <div class="item">36 ~ 63  </div>
						    </div>
						  
						    <div class="tab-content" id="accordionAnxiety">
                        
                      	    </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
        
<div id="popup_rating" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade ">
    <div class="modal-dialog" style="width: 350px; margin-top: 100px;">
        <div class="modal-content">
            <div class="modal-header">
                <h4>만족도 평가</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
            </div>
            <div class="modal-body" style="padding-bottom: 40px;">
                <div class="input-rating">
                    <input type="number" data-readonly="" value="${satisfaction.point}" id="point" class="rating" readonly/>
                </div>
                <p class="content-msg">${satisfaction.comment}</p>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    $(document).ready(function() {

        var userId      = ${session.user.id} || 0;

        $('.btnMemoDelete').on('click', function(evt) {
            evt.preventDefault();
            var memoId = $(this).attr('data-id');

            $('#popup_delete_memo').modal('show');
            $('#popup_delete_memo')
                .off('click', '#accept')
                .on('click', '#accept', function(evt) {
                    $
                        .ajax({
                            url: '/auth/counselmemo/detail?memoId=' + memoId,
                            method: 'delete'
                        })
                        .then(function(res) {
                            window.location.reload();
                        }, function(err) {
                            console.error(err);
                        });
                });


        });

        $('#btnCreateMemo').on('click', function(evt) {
            $('#popup_create_memo').modal('show');
            $('#popup_create_memo')
                .off('click', '#accept')
                .on('click', '#accept', function(evt) {
                    var memo = $('#memoBody').val().trim();
                    if (!memo) return;
                    $
                        .ajax({
                            url: '/auth/counselmemo',
                            method: 'post',
                            data: {
                                userId      : userId,
                                memo        : memo
                            }
                        })
                        .then(function(res) {
                            window.location.reload();
                        }, function(err) {
                            console.error(err);
                        });
                });
        });

        $('#msgBody').on('keyup', function(evt) {
            var msgRemain = $(this).val().length;
            $('#msgRemain').text(msgRemain);
        });

        $('#btnCreateMessage').on('click', function(evt) {
            $('#popup_create_message').modal('show');

            $('#popup_create_message')
                .off('click', '#accept')
                .on('click', '#accept', function(evt) {
                    var msg = $('#msgBody').val().trim();
                    if (!msg) {
                        $('#popup_msg_empty').modal('show');
                        return false;
                    }

                    $
                        .ajax({
                            url: '/api/v1/public/ajax_send_message_detail',
                            method: 'post',
                            data: {
                                dataUserId: userId,
                                content: msg
                            }
                        })
                        .then(function(res) {
                            $('#popup_msg_complete').modal('show');
                            $('.popup_accept_close_complete').on('click', function(evt) {
                                window.location.reload();
                            });
                        }, function(err) {
                            console.error(err);
                        });
                });
        });

        function renderMindCheck(data) {
            // register Handlebars format date
            Handlebars.registerHelper('fmtDate', function(timestamp) {
                var date = moment(timestamp);
                return date.year() + '년 ' + (date.month() + 1) + '월 ' + date.date() + '일';
            });

            // render template
            var src     = $('#tpl_mindcheck').html();
            var tpl     = Handlebars.compile(src);
            var html    = tpl({mindchecks: data});

            $('#accordion').html(html);
        }
        
        $('#btnMindCheck').on('click', function(evt) {
            evt.preventDefault();

            var $popupMindCheck = $('#popup_mindcheck_list');
            var cacheData = $popupMindCheck.attr('data-cache') === 'true' ? true : false;

            if (cacheData) {
                $popupMindCheck.modal('show');
            } else {
                $
                    .ajax({
                        url     : '/auth/mindcheck',
                        method  : 'get',
                        data    : {
                            userId: userId
                        },
                        beforeSend: toggleLoading
                    })
                    .then(function(res) {
                        toggleLoading();
                        renderMindCheck(res);
                        $popupMindCheck
                            .attr('data-cache', true)
                            .modal('show');
                    }, function(err) {
                        toggleLoading();
                        console.error(err);
                    });

            }
        });
        
        function renderSelfDiagnosis(data) {
            var src     = $('#tpl_selfDiagnosis').html();
            var tpl     = Handlebars.compile(src);
            var html    = tpl({test: data});

            return html;
        }
        
        $('#btnSelfDiagnosis').on('click', function(evt) {
            evt.preventDefault();

            var $popupSelfDiagnosisTestList = $('#popup-self-diagnosis-test-list');
            var cacheData = $popupSelfDiagnosisTestList.attr('data-cache') === 'true' ? true : false;

            if (cacheData) {
                $popupSelfDiagnosisTestList.modal('show');
            } else {
                $
                    .ajax({
                        url     : '/auth/selfDiagnosis',
                        method  : 'get',
                        data    : {
                            userId: userId
                        },
                        beforeSend: toggleLoading
                    })
                    .then(function(res) {
                        toggleLoading();

                        $popupSelfDiagnosisTestList
                            .find('#accordionStress')
                            .html(renderSelfDiagnosis(res.stressDiagnosis));
                        $popupSelfDiagnosisTestList
                            .find('#accordionDepressed')
                            .html(renderSelfDiagnosis(res.dipressedDiagnosis));
                        $popupSelfDiagnosisTestList
                            .find('#accordionAnxiety')
                            .html(renderSelfDiagnosis(res.anxietyDiagnosis));

                        $popupSelfDiagnosisTestList
                            .attr('data-cache', true)
                            .modal('show');
                    }, function(err) {
                        toggleLoading();
                        console.error(err);
                    });

            }
        });
        
        
        $('#btnSatisfaction').on('click', function(evt) {

            evt.preventDefault();
            var $this   = $(this);
            var $popup  = $('#popup_rating');

            if (!$this.attr('disabled')) {
                $popup.modal('show');
            }

        });

    });


</script>