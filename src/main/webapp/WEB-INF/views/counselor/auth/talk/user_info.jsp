<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<div id="popupList-facetalk" style="height: 100% !important;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="text-align: center;padding-bottom: 5px;">
                <h4>회원 정보</h4>
            </div>
            <div class="modal-body">
                <div class="modal-list no-border">
                    <ul>
                        <li>
                            <span class="title">내담자 이름</span><span>${user.fullname}</span>
                        </li>
                        <li>
                            <span class="title">나이</span><span>${age}</span>
                        </li>
                        <li>
                            <span class="title">성별</span><span>${user.gender eq 1 ? '남자' : '여자'}</span>
                        </li>
                        <li>
                            <span class="title">최근 상담</span><span><fmt:formatDate value="${session.createDate}" />(<fmt:formatDate value="${session.startTime}" pattern="E" />)</span>
                        </li>
                        <li>
                            <span class="title">총 상담횟수</span><span>${total}회</span>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<style>
	.modal-backdrop{display: none !important;}
	.modal-dialog{width: 100% !important;margin-top: 0 !important;border: none !important;box-shadow: none !important;}
	.modal-content{box-shadow: none !important;border: none !important;border-radius: 0 !important;}
	.modal-list li .title{float: left;width: 100px;}
</style>