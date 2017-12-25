<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="member page-content-wrapper counseling-history massage">
        <div class="page-title">
          <div class="title"><span>고객센터</span></div>
          <div class="title-ct"> <span>서비스 이용약관 및 개인정보 취급방침.</span></div>
        </div>
        <div class="page-content term-sevice">
          <div class="container">
            <div class="main-top border-full bg-fff">
              <div class="tabbable-custom">
                <ul class="nav nav-tabs">
                  <li class="active"><a href="#tab-11" data-toggle="tab"> <span>서비스 이용약관 </span><i aria-hidden="true" class="fa fa-caret-down"></i></a></li>
                  <li><a href="#tab-12" data-toggle="tab"> <span>개인정보 취급방침</span><i aria-hidden="true" class="fa fa-caret-down"></i></a></li>
                </ul>

                <div class="tab-content">
                  <div id="tab-11" class="tab-10 tab-pane active">
                    <div class="module-content"> 
                      <p>${termsOfService.content}</p>
                    </div>
                  </div>
                  <div id="tab-12" class="tab-10 tab-pane">
                    <div class="module-content"> 
                      <p>${privacyPolicy.content } </p>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>