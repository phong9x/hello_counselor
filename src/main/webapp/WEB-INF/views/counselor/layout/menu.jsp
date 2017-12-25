<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<div class="page-sidebar-wrapper vetical-menu">
    <div class="page-sidebar navbar-collapse collapse">
        <div class="container">
            <ul data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" class="page-sidebar-menu">
                <li>
                    <a href="/auth/home"><span class="title">홈</span></a>
                </li>
                <li>
                    <a><span class="title">상담 관리</span></a>
                </li>
                <li>
                    <a><span class="title">리포트</span></a>
                </li>
                <li>
                    <a><span class="title">고객센터</span></a>
                </li>
            </ul>

            <div class="page-dropdow-menu" style="display:none">
                <div class="container">
                    <div class="menu-sub1 menu1">
                        <a href="/auth/home">
                            <div class="logomenu">
                                <img src="/theme/admin/assets/images/logo/logov2.png" class="logo-default"/>
                            </div>
                        </a>
                    </div>
                    <div class="menu-sub1 menu2">
                        <ul>
                            <li onclick="remove_new_status(this)">
                                <a href="/auth/reservation/list">
                                    <span>상담예정 내역</span>
                                    <span id="new-session" class="new-menu" style="display:none;"></span>
                                </a>
                            </li>
                            <li>
                                <a href="/auth/schedule/list">
                                    <span>스케줄</span>
                                </a>
                            </li>
                            <li>
                                <a href="/auth/counselhistory/list"><span>상담내역</span></a>
                            </li>
                            <li onclick="remove_new_status(this)">
                                <a href="/auth/psychologicaltest/list">
                                    <span>심리검사</span>
                                    <span id="new-psypayment" class="new-menu" style="display:none;"></span>
                                </a>
                            </li>
                            <li onclick="remove_new_status(this)">
                                <a href="/auth/message/list">
                                    <span>쪽지</span>
                                    <span id="new-message" class="new-menu" style="display:none;"></span>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="menu-sub1 menu3">
                        <ul>
                            <li>
                                <a href="/auth/report/statistics"><span>통계 리포트</span></a>
                            </li>
                            <c:if test="${userItem.role ne 3}">
                                <li>
                                    <a href="/auth/report/profit"><span>정산 리포트</span></a>
                                </li>
                            </c:if>
                        </ul>
                    </div>
                    <div class="menu-sub1 menu4">
                        <ul>
                            <li>
                                <a href="/auth/notice/list">
                                    <span>공지사항 </span>
                                </a>
                            </li>
                            <li>
                                <a href="/auth/faq/list">
                                    <span>faq</span>
                                </a>
                            </li>
                            <li>
                                <a href="/auth/policy/policy">
                                    <span>이용약관 / 개인정보정책 </span>
                                </a>
                            </li>
                            <li>
                                <a href="/auth/inquiry/inquiry">
                                    <span>문의하기</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- .page-sidebar-wrapper-->