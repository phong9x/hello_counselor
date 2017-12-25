<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<div class="member page-content-wrapper counseling-history detail">
    <div class="page-title">
        <div class="title">
            <span>상담예정 내역</span>
        </div>
        <div class="title-ct">
            <span>아래와 같이 상담이 예정되어 있습니다.</span>
        </div>
    </div>
    <div class="page-content detail-style2">
        <div class="container">
            <div style="margin-bottom: 10px;" class="main-content-top border-full bg-fff">
                <div class="item-title">
                    <span>요약 정보</span>
                </div>
                <div class="item-content">
                    <div class="item">
                        <span class="title comment">상담 구분</span>
                        <span class="content">
                            <c:choose>
                                <c:when test="${1 == session.counselType}">
                                    실시간 상담
                                </c:when>
                                <c:otherwise>
                                    예약
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </div>
                    <div class="item">
                        <span class="title acc">회원</span>
                        <span class="content">${session.user.fullname}</span>
                    </div>
                    <div class="item">
                        <span class="title next">상담 시작</span>
                        <span class="content">
                        	<c:set var="startTime" value="${session.startTime}"></c:set>
                        	<c:choose>
                             	<c:when test="${session.counselType == 1 && session.entryCounselorTime != null }">
                             		<c:set var="startTime" value="${session.entryCounselorTime}"></c:set>
                             	</c:when>
                             	<c:otherwise>
                             		<c:set var="startTime" value="${session.startTime}"></c:set>
                             	</c:otherwise>
                             </c:choose>
                            <fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd" />(<fmt:formatDate value="${startTime}" pattern="E" />)
                            <fmt:formatDate value="${startTime}" pattern="HH:mm" />
                        </span>
                    </div>
                    <div class="item">
                        <span class="title clock">상담 시간</span>
                        <span class="content">${session.counselingTimeType}분</span>
                    </div>
                    <div class="item">
                        <span class="title clock2">남은 시간</span>
                        <span class="content">
                            <jsp:useBean id="now" class="java.util.Date" scope="request" />
                            <c:set var="deltaSessionRequestTime" value="${startTime.getTime() - now.getTime()}" />
                            <c:choose>
                                <c:when test="${deltaSessionRequestTime > 0 and deltaSessionRequestTime <= 3600000}">
                                    <fmt:parseNumber value="${deltaSessionRequestTime / 60000}" integerOnly="true" var="minute" />
                                    <fmt:parseNumber value="${(deltaSessionRequestTime - (minute * 60000)) / 1000}" integerOnly="true" var="second" />
                                    ${minute}분${second}초
                                </c:when>
                                <c:when test="${deltaSessionRequestTime > 3600000}">
                                    <fmt:parseNumber value="${deltaSessionRequestTime / 3600000}" integerOnly="true" var="hour" />
                                    <fmt:parseNumber value="${(deltaSessionRequestTime - (hour * 3600000)) / 60000}" integerOnly="true" var="minute" />
                                    ${hour}시간${minute}분
                                </c:when>
                                <c:otherwise>
                                    -
                                </c:otherwise>
                            </c:choose>
                        </span>
                    </div>
                </div>
            </div>
            <div class="main-content border-full bg-fff">
                <div class="content-main">
                    <div class="main-ct">
                        <div style="margin-bottom: 10px;" class="main-item">
                            <div class="item-title">
                                <span>상세 정보</span>
                            </div>
                            <div class="item-content">
                                <div class="item">
                                    <div class="item-left">
                                        <span class="title">번호</span>
                                        <div class="left-ct">
                                            <span>${session.user.id}</span>
                                        </div>
                                    </div>
                                    <div class="item-right">
                                        <span class="title">회원명</span>
                                        <div class="left-ct">
                                            <span>${session.user.fullname}</span>
                                            <button id="btnCreateMessage">쪽지 쓰기</button>
                                            <c:choose>
                                                <c:when test="${hasCounselingHistory}">
                                                    <button onclick="window.location='/auth/counselhistory/list?fullname=${session.user.fullname}'">지난 상담내역 보기</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button disabled="disabled" style="cursor: no-drop">지난 상담내역 보기</button>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                    </div>
                                    <div class="item-right">
                                        <span class="title"> 회원 아이디</span>
                                        <div class="left-ct">
                                            <span>${session.user.email}</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="item">
                                    <div class="item-left">
                                        <span class="title">상담 시간</span>
                                        <div class="left-ct">
                                            <span>
                                                <c:choose>
                                                    <c:when test="${not empty session.endTime}">
                                                        <fmt:parseNumber value="${(session.endTime.getTime() - startTime.getTime()) / 3600000}" integerOnly="true" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        ${session.counselingTimeType}분
                                                    </c:otherwise>
                                                </c:choose>
                                            </span>
                                        </div>
                                    </div>
                                    <div class="item-right">
                                        <span class="title">상담 시작</span>
                                        <div class="left-ct">
                                            <span>
                                                <fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd (E) HH:mm" />
                                            </span>
                                        </div>
                                    </div>
                                    <div class="item-right">
                                        <span class="title">상담 종료 예정</span>
                                        <div class="left-ct">
                                            <span>
                                                <c:choose>
                                                    <c:when test="${not empty session.endTime}">
                                                        <fmt:formatDate value="${session.endTime}" pattern="yyyy-MM-dd" />( <fmt:formatDate value="${session.endTime}" pattern="E" /> )
                                                        <fmt:formatDate value="${session.endTime}" pattern="HH:mm" />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <jsp:useBean id="endDate" class="java.util.Date"/>
                                                        <jsp:setProperty name="endDate" property="time" value="${startTime.getTime() + 1800000}"/>
                                                        <fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd" />( <fmt:formatDate value="${endDate}" pattern="E" /> )
                                                        <fmt:formatDate value="${endDate}" pattern="HH:mm" />
                                                    </c:otherwise>
                                                </c:choose>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="item">
                                    <div class="item-left">
                                        <span class="title">상담 구분</span>
                                        <div class="left-ct">
                                            <a class="link">
                                                <c:choose>
                                                    <c:when test="${1 == session.counselType}">
                                                        실시간 상담
                                                    </c:when>
                                                    <c:otherwise>
                                                        예약
                                                    </c:otherwise>
                                                </c:choose>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="item-right">
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
                                        <span class="title">남은 시간</span>
                                        <div class="left-ct">
                                            <span>
                                                <c:choose>
                                                    <c:when test="${deltaSessionRequestTime > 0 and deltaSessionRequestTime <= 3600000}">
                                                        <fmt:parseNumber value="${deltaSessionRequestTime / 60000}" integerOnly="true" var="minute" />
                                                        <fmt:parseNumber value="${(deltaSessionRequestTime - (minute * 60000)) / 1000}" integerOnly="true" var="second" />
                                                        ${minute}분${second}초
                                                    </c:when>
                                                    <c:when test="${deltaSessionRequestTime > 3600000}">
                                                        <fmt:parseNumber value="${deltaSessionRequestTime / 3600000}" integerOnly="true" var="hour" />
                                                        <fmt:parseNumber value="${(deltaSessionRequestTime - (hour * 3600000)) / 60000}" integerOnly="true" var="minute" />
                                                        ${hour}시간${minute}분
                                                    </c:when>
                                                    <c:otherwise>
                                                        -
                                                    </c:otherwise>
                                                </c:choose>
                                            </span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="main-submit">
                <button onclick="location.href='/auth/reservation/list'">
                    <span>목록 보기</span>
                </button>

                <c:if test="${1 eq session.status}">
                    <c:choose>
                        <c:when test="${1 eq session.counselType}">
                            <button class="v2 btn_tap" data-href="/auth/talk?id=${session.id}">
                                <span>상담실 입장</span>
                            </button>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${deltaSessionRequestTime le 600000}">
                                <button class="v2 btn_tap" data-href="/auth/talk?id=${session.id}">
                                    <span>상담실 입장</span>
                                </button>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </c:if>
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
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var userId      = ${session.user.id} || 0;
    var windowOpener;

    function openWindow(url) {
        windowOpener = window.open(url, '_blank');
    }

    function closeWindow() {
        if (windowOpener) {
            windowOpener.close();
            location.href = '/auth/counselhistory/list';
        }
    }

    $('.btn_tap').on('click', function(evt) {
        evt.preventDefault();
        var url = $(this).attr('data-href');
        openWindow(url);
    });

    $('#msgBody').on('keyup', function(evt) {
        $('#msgRemain').text($(this).val().length);
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
                    }, function(err) {
                        console.error(err);
                    });
            });
    });

</script>