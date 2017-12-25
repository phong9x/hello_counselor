<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<div class="member page-content-wrapper counseling-history" style="margin-bottom: 100px;">
    <div class="page-title">
        <div class="title">
            <span>상담예정 내역</span>
        </div>
        <div class="title-ct">
            <span>아래와 같이 상담이 예정되어 있습니다.</span>
        </div>
    </div>
    <div class="page-content">
        <div class="container">
            <div class="main-top border-full bg-fff">
                <div class="content-top">
                    <form>
                        <div class="md-content">
                            <div class="md-item">
                                <span class="title">상담 구분</span>
                                <div class="item-ct">
                                    <div class="item radio-checkboxstyle2">
                                        <input type="checkbox" value="1" name="counselType0" ${params.counselType0 eq 1 ? 'checked' : ''} /><span>실시간 상담</span>
                                    </div>
                                    <div class="item radio-checkboxstyle2">
                                        <input type="checkbox" value="2" name="counselType1" ${params.counselType1 eq 2 ? 'checked' : ''} /><span>예약</span>
                                    </div>
                                </div>
                            </div>
                            <div class="md-item">
                                <span class="title">결제 구분</span>
                                <div class="item-ct">
                                    <div class="item radio-checkboxstyle2">
                                        <input type="checkbox" value="1" name="paymentType0" ${params.paymentType0 eq 1 ? 'checked' : ''} /><span>예치금 사용</span>
                                    </div>
                                    <div class="item radio-checkboxstyle2">
                                        <input type="checkbox" value="3" name="paymentType1" ${params.paymentType1 eq 3 ? 'checked' : ''} /><span>상담권 사용</span>
                                    </div>
                                </div>
                            </div>
                            <div class="md-item"><span class="title">회원 검색</span>
                                <div class="item-ct input-form">
                                    <input type="text" name="fullname" value="${params.fullname}" data-required="1" class="form-control"/>
                                </div>
                            </div>
                        </div>
                        <button class="md-link" type="submit">
                            검색
                        </button>
                    </form>
                </div>
            </div>

            <div class="main-content border-full bg-fff">
                <div class="content-main">
                    <div class="main-top">
                        <span class="title">상담 예정 리스트</span>
                        <span class="price">${sessionEntities.totalElements}건</span>
                        <select name="sorts" class="form-control selectpickermember pull-right">
                            <option value="startTime"       ${fn:contains(sessionEntities.sort, 'startTime') ? 'selected' : ''}     data-direction="desc">상담 빠른 순</option>
                            <option value="user.fullname"   ${fn:contains(sessionEntities.sort, 'user.fullname') ? 'selected' : ''} data-direction="asc">이름 순</option> </select>
                    </div>
                    <div class="main-ct">
                        <div class="table-wrapper">
                            <div class="table-responsive">
                                <table class="table table-striped table-hover text-center">
                                    <thead>
                                        <tr>
                                            <th>번호</th>
                                            <th>상담 구분</th>
                                            <th>회원명</th>
                                            <th>결제 구분</th>
                                            <th>상담 예정 일시</th>
                                            <th>상담 종료 예정</th>
                                            <th>남은 시간</th>
                                            <th>상담실 입장</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <jsp:useBean id="now" class="java.util.Date" scope="request" />
                                    	<c:forEach items="${sessionEntities.content}" var="item" varStatus="index">
                                   			<c:choose>
                                            	<c:when test="${item.counselType == 1 && item.entryCounselorTime != null }">
                                            		<c:set value="${item.entryCounselorTime}" var="startTime"></c:set>
                                            	</c:when>
                                            	<c:otherwise>
                                            		<c:set value="${item.startTime}" var="startTime"></c:set>
                                            	</c:otherwise>
                                            </c:choose>
                                    	    <c:set var="deltaSessionRequestTime" value="${item.startTime.getTime() - now.getTime()}" />
                                    		<tr>
                                    		    <td>
                                    		        ${sessionEntities.totalElements - sessionEntities.size*sessionEntities.number - index.index}
                                    		    </td>
	                                            <td>
	                                                <c:choose>
                                                        <c:when test="${1 == item.counselType}">
                                                            실시간 상담
                                                        </c:when>
                                                        <c:otherwise>
                                                            예약
                                                        </c:otherwise>
                                                    </c:choose>
	                                            </td>
	                                            <td>
	                                                <a href="/auth/reservation/detail?sessionId=${item.id}">
	                                                    ${item.user.fullname}
	                                                </a>
	                                            </td>
	                                            <td>
                                                    <c:choose>
                                                        <c:when test="${1 == item.paymentHistory.typeCoin}">
                                                            예치금 사용
                                                        </c:when>
                                                        <c:otherwise>
                                                            상담권 사용
                                                        </c:otherwise>
                                                    </c:choose>
	                                            </td>
	                                            <td>
	                                            
	                                            <fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd HH:mm:ss" />
	                                            </td>
	                                            <td>
                                                    <fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />
	                                            </td>
	                                            <td>
	                                                <c:choose>
	                                                    <c:when test="${1 == item.counselType}">
                                                            -
                                                        </c:when>
                                                        <c:otherwise>
                                                            <c:choose>
                                                                <c:when test="${1 == item.status}">
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
                                                                </c:when>
                                                                <c:otherwise>
                                                                    -
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:otherwise>
	                                                </c:choose>
	                                            </td>
	                                            <td>
	                                                <c:choose>
                                                        <c:when test="${0 == item.status}">
	                                                        <a class="button-chis v2" style="cursor: default;pointer-events: none">예약취소됨</a>
                                                        </c:when>
                                                        <c:when test="${1 == item.status}">
                                                            <c:choose>
                                                                <c:when test="${1 == item.counselType}">
                                                                    <a class="button-chis btn_tab" href="/auth/talk?id=${item.id}">입장</a>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <c:choose>
                                                                        <c:when test="${deltaSessionRequestTime le 300000}">
                                                                            <a class="button-chis btn_tab" href="/auth/talk?id=${item.id}">입장</a>
                                                                        </c:when>
                                                                        <c:otherwise>
                                                                            <a class="button-chis v2" style="cursor: default;pointer-events: none">입장</a>
                                                                        </c:otherwise>
                                                                    </c:choose>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </c:when>
                                                        <c:otherwise>
	                                                        <a class="button-chis v2" style="cursor: default;pointer-events: none">상담 완료</a>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
	                                        </tr>
                                    	</c:forEach>
                                    </tbody>
                                </table>
                            </div>

                            <jsp:include page="/WEB-INF/views/counselor/layout/pagination.jsp">
                                <jsp:param name="first"             value="${sessionEntities.first}"/>
                                <jsp:param name="last"              value="${sessionEntities.last}"/>
                                <jsp:param name="totalPages"        value="${sessionEntities.totalPages}"/>
                                <jsp:param name="totalElements"     value="${sessionEntities.totalElements}"/>
                                <jsp:param name="numberOfElements"  value="${sessionEntities.numberOfElements}"/>
                                <jsp:param name="size"              value="${sessionEntities.size}"/>
                                <jsp:param name="number"            value="${sessionEntities.number}"/>
                            </jsp:include>

                        </div>
                    </div>
                </div>
            </div>
            <!-- .block-1-->
            <!-- .member-main-->
        </div>
    </div>
    <!-- .page-content-->
</div>

<script type="text/javascript">
    sortable('select[name=sorts]');

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

    $('.btn_tab').on('click', function(evt) {
        evt.preventDefault();
        openWindow($(this).attr('href'));
    });

</script>