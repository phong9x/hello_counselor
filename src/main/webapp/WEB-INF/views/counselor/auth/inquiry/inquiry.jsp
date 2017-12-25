<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="member page-content-wrapper counseling-history detail notice-detail">
        <div class="page-title">
          <div class="title"><span>고객센터</span></div>
          <div class="title-ct"> <span>문의하기</span></div>
        </div>
        <div class="page-content inquiry">
          <div class="container">
            <div class="main-content border-full bg-fff">
              <div class="content-main">
                <div class="main-ct">
                    <div class="main-item">
                      <h4 style="font-weight: bold;">문의하기</h4>
                      <div class="item-content">
                        <div class="item">
                          <div class="title" style="color: #888;">이메일 </div>
                          <div class="col-md-3">
                            <input type="text" name="email_inquiry" data-required="1" class="form-control" value="${counselorEmail}"/>
                          </div>
                        </div>
                        <div class="item dot"> 
                          <div style="line-height: 28px;color: #888;" class="title">카테고리 </div>
                          <div style="height: 28px;" class="col-md-3">
                            <select name="category_inquiry" id="category_inquiry" class="form-control selectpickermember">
                              <option value="0">선택</option>
                              <option value="43">오류 및 장애 신고</option>
                              <option value="44">서비스 이용</option>
                              <option value="45">정산 관련</option>
                              <option value="46">제휴 관련</option>
                            </select>
                          </div>
                        </div>
                        <div class="item dot"> 
                          <div style="line-height: 160px;color: #888;" class="title">내용입력 </div>
                          <div class="col-md-6">
                            <textarea name="content_inquiry" id="content_inquiry" rows="8" data-error-container="#editor2_error" class="form-control"></textarea>
                          </div>
                        </div>
                      </div>
                    </div>
                    <div class="main-submit">
                      <button class="btn-send-inquiry"> <span>문의 하기</span></button>
                    </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      
      <div id="popup-inquiry" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="popup-psychlogical23 modal fade popup-facetalk">
      <div style="width: 400px;" class="modal-dialog popup-inquiry">
        <div class="modal-content">
          <div class="modal-header">
            <h4>받은 쪽지</h4>
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
          </div>
          <div class="modal-body">
            <p>문의 접수가 완료되었습니다.</p>
            <p>입력하신 메일로 빠른 시일 내에 답변 드리겠습니다.</p>
          </div>
          <div class="modal-footer">
          	<button class="link1 btn-confirm-result-ok">확인</button>
            <!-- Are you sure you want to delete?-->
          </div>
        </div>
      </div>
    </div>