<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="member page-content-wrapper counseling-history detail notice-detail" style="margin-bottom: 190px;">
        <div class="page-title">
          <div class="title"><span>고객센터</span></div>
          <div class="title-ct"> <span>공지사항</span></div>
        </div>
        <div class="page-content">
          <div class="container">
            <div class="main-content border-full bg-fff">
              <div class="content-main">
                <div class="main-ct">
                  <div class="main-item">
                    <div class="item-title"> <span>공지사항</span></div>
                    <div class="item-content">
                      <div class="item"> 
                        <div class="row"><span>${notice.title } </span>
                          <div class="notice-detail-right"><span class="title">조회수     </span><span>${notice.viewNumber }</span></div>
                          <div class="notice-detail-right style"><span class="title">작성일</span><span><fmt:formatDate pattern="yyyy-MM-dd" value="${notice.createDate }"></fmt:formatDate></span></div>
                        </div>
                        <div class="row">
                          <p>${notice.content }</p>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              <div class="main-submit">
                <button class="btn-back-list"><span>목록 보기</span></button>
              </div>
            </div>
          </div>
        </div>
      </div>

