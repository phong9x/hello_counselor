<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<div class="member page-content-wrapper counseling-history">
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
            <div class="main-top border-full bg-fff">
                <div class="content-top">
                    <form>
                        <div class="md-content">
                            <div class="md-item">
                                <span class="title">상담 날짜</span>
                                <div class="item-ct">
                                    <div class="item icon-next datepicker-item">
                                        <input name="fromTime" type="text" value="${params.fromTime}" data-date-format="yyyy/mm/dd" class="form-control date-picker"/>
                                    </div>
                                    <div class="item datepicker-item">
                                        <input name="toTime" type="text" value="${params.toTime}" data-date-format="yyyy/mm/dd" class="form-control date-picker"/>
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
                                        <input type="checkbox" value="2" name="paymentType1" ${params.paymentType1 eq 2 ? 'checked' : ''} /><span>상담권 사용</span>
                                    </div>
                                </div>
                            </div>
                            <div class="md-item">
                                <span class="title">상태</span>
                                <div class="item-ct">
                                    <div class="item radio-checkboxstyle2">
                                        <input type="checkbox" value="2" name="status0" ${params.status0 eq 2 ? 'checked' : ''} /><span>상담완료</span>
                                    </div>
                                    <div class="item radio-checkboxstyle2">
                                        <input type="checkbox" value="3" name="status1" ${params.status1 eq 3 ? 'checked' : ''} /><span>환급</span>
                                    </div>
                                </div>
                            </div>
                            <div class="md-item">
                                <span class="title">회원 검색</span>
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
                        <span class="title">지난 상담내역 리스트</span>
                        <span class="price">${sessionEntities.totalElements}건</span>
                        <select name="sorts" class="form-control selectpickermember pull-right">
                            <option value="startTime"       ${fn:contains(sessionEntities.sort, 'startTime') ? 'selected' : ''}     data-direction="desc">최근 상담 순</option>
                            <option value="user.fullname"   ${fn:contains(sessionEntities.sort, 'user.fullname') ? 'selected' : ''} data-direction="asc">이름 순</option> </select>
                    </div>
                    <div class="main-ct">
                        <div class="table-wrapper">
                            <div class="table-responsive">
                                <table class="table table-striped table-hover text-center">
                                    <thead>
                                        <tr>
                                            <th>번호</th>
                                            <th>회원명</th>
                                            <th>상담시작</th>
                                            <th>상담종료</th>
                                            <th>요청 상담 시간</th>
                                            <th>상담 시간</th>
                                            <th>결제 구분</th>
                                            <th>만족도 평가</th>
                                            <th>상태</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    	<c:forEach items="${sessionEntities.content}" var="item" varStatus="index">
                                    		<tr>
                                    		    <td>
                                    		        ${sessionEntities.totalElements - sessionEntities.size*sessionEntities.number - index.index}
                                    		    </td>
	                                            <td>
	                                                <a href="/auth/counselhistory/detail?sessionId=${item.id}">
	                                                    ${item.user.fullname}
	                                                </a>
	                                            </td>
	                                            <td>
	                                            <c:choose>
	                                            	<c:when test="${item.counselType == 1 && item.entryCounselorTime != null }">
	                                            		<c:set value="${item.entryCounselorTime}" var="startTime"></c:set>
	                                            	</c:when>
	                                            	<c:otherwise>
	                                            		<c:set value="${item.startTime}" var="startTime"></c:set>
	                                            	</c:otherwise>
	                                            </c:choose>
	                                                <fmt:formatDate value="${startTime}" pattern="yyyy-MM-dd HH:mm:ss" />
	                                            </td>
	                                            <td>
                                                    <fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                                                </td>
                                                <td>
                                                    ${item.counselingTimeType}분
                                                </td>
                                                <td>
                                                    <jsp:useBean id="counselTime" class="java.util.Date"/>
                                                    <c:set target="${counselTime}" property="time" value="${item.endTime.getTime() - startTime.getTime()}" />
                                                    <fmt:formatDate value="${counselTime}" pattern="mm 분 ss 초 " />
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
	                                                <c:choose>
	                                                    <c:when test="${not empty item.point}">
	                                                        <input type="number" data-readonly="" value="${item.point}" class="rating" style="display:none;"/> (${item.point})
	                                                    </c:when>
	                                                    <c:otherwise>
	                                                        ---
	                                                    </c:otherwise>
	                                                </c:choose>
	                                            </td>
	                                            <td>
	                                                <c:choose>
                                                        <c:when test="${2 == item.status}">
                                                            <button class="button-chis">상담완료</button>
                                                        </c:when>
                                                        <c:otherwise>
	                                                        <button class="button-chis v2">환급</button>
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
</script>