<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<div class="member page-content-wrapper counseling-history detail psychological-info">
        <div class="page-title">
          <div class="title"><span>심리검사</span></div>
          <div class="title-ct"> <span>회원에게 요청한 심리상담 정보입니다.</span></div>
        </div>
        <div class="page-content">
          <div class="container">
            <div class="main-content border-full bg-fff">
              <div class="content-main">
                <div class="main-ct">
                  <div style="margin-bottom: 40px;" class="main-item">
                    <div class="item-title"> <span>상세 정보</span></div>
                    <div class="item-content">
                      <div class="item">
                        <div class="item-left"><span class="title">심리검사 요청 번호</span>
                          <div class="left-ct"><span>${r.id }</span></div>
                        </div>
                        <div class="item-right"><span class="title">회원명</span>
                          <div class="left-ct"><span>${r.user.fullname }(${r.user.email}) </span>
                            <button class="popup-psychlogical3">쪽지쓰기</button>
                          </div>
                        </div>
                      </div>
                      <div class="item">
                        <div class="item-left"><span class="title">요청일시</span>
                          <div class="left-ct"><span>
                          <fmt:formatDate value="${r.requestPsychological}" pattern="yyyy-MM-dd"/>(<fmt:formatDate value="${r.requestPsychological}" pattern="EE"/>) 
                           <fmt:formatDate value="${r.requestPsychological }" pattern="HH:mm:ss"/></span>
                            <button data-id="${r.id}" class="style-v2 cancel_psychologicaltest">심리검사 요청 취소</button>
                          </div>
                        </div>
                        <div class="item-right"><span class="title">결제일시</span>
                          <div class="left-ct">
                          <span>
                          <c:if test="${r.paymentPsychological != null}">
                          	<fmt:formatDate value="${r.paymentPsychological}" pattern="yyyy-MM-dd"/>(<fmt:formatDate value="${r.paymentPsychological}" pattern="EE"/>) 
                           <fmt:formatDate value="${r.paymentPsychological }" pattern="HH:mm:ss"/>
                          </c:if>
                           </span>
                           </div>
                        </div>
                      </div>
                    </div>
                  </div>
                  
                  <div style="margin-bottom: 20px;" class="main-item">
                    <div class="item-title"> <span>심리검사 요청</span></div>
                    <div class="item-content">
                      <div class="itemv2">
	                      	<c:forEach items="${psyDetailList }" var="t">
	                      		<span class="title">심리검사</span>
	                      		<c:if test="${t.fileUrl != null}">
	                      			<div class="item-right">
	                      				<span class="title-2" style="width: auto;">${t.testName}</span>
	                      				<span class="color2">${t.fileName}</span>
	                      			</div>
	                      		</c:if>
	                      		<c:if test="${t.fileUrl == null}">
	                      		 <form method="post" enctype="multipart/form-data">
	                      			<div style="border-top: 1px solid #e5e5e5;" class="item-right"><span class="title-2" style="width: auto;">${t.testName}</span>
			                          <div class="inputfile">
			                            <div class="tr-file-upload image-upload">
			                              <label class="btn green btn-select-file">검사 결과 업로드
			                                <input type="file" accept="application/pdf" id="pdfFile" data-userPsyTestId="${t.userPsychologicalId}" 
			                                data-userPsyFileId="${t.id}" 
			                                data-testName="${t.testName}" 
			                                data-psyTestCounselorId="${r.counselor.id}"
			                                name="pdfFile" class="hidden input-file" multiple/>
			                              </label><span class="help-inline file">pdf 파일만 허용</span>
			                            </div>
			                          </div>
			                        </div>
			                        </form>
	                      		</c:if>
	                      	</c:forEach>
                      </div>
                    </div>
                  </div>
                  
                </div>
              </div>
            </div>
            <div class="main-submit">
              <button class="psychological_back_list"><span>목록 보기</span></button>
            </div>
          </div>
        </div>
      </div>
      
      <div id="popupfirstlogin" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4>심리상담사 등록 완료</h4>
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
          </div>
          <div class="modal-body">
            <h4>
              <p>[이름] 상담사님. 환영합니다.</p>
              <p>Hello 공식 심리상담사 등록이 완료되었습니다.</p><br/>
              <p>상담사 매뉴얼에는 정산, 상담 설정 등 상담사님들이 꼭 확인해야 하는 내용들이 포함되어 있습니다. </p><br/>
              <p class="bold">아래의 상담사 매뉴얼을 다운로드하여 꼭 확인해주세요.</p>
              <!-- Are you sure you want to delete?-->
            </h4>
          </div>
          <div class="modal-footer"><a data-dismiss="modal" class="btn red btn-confirm">상담사 매뉴얼 Download</a>
          </div>
        </div>
      </div>
    </div>
    <!-- .popupConfirmDelete-->

    <div id="popupConfirmDelete" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
          </div>
          <div class="modal-body">
            <h4><strong>삭제하시겠습니까?</strong>
              <!-- Are you sure you want to delete?-->
            </h4>
          </div>
          <div class="modal-footer"><a href="#" class="btn red btn-confirm">확인</a>
            <button type="button" data-dismiss="modal" class="btn btn-default">취소</button>
          </div>
        </div>
      </div>
    </div>
    <!-- .popupConfirmDelete-->
	
	<div id="popup-psychlogical3" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="popup-psychlogical23 modal fade popup-facetalk">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
          </div>
          <div class="modal-body popup3">
            <div class="modal-ct">
              <div class="modal-title"><span>받는 회원 선택</span>
                <div class="search3">
                  <input type="text" name="name" value="홍길동" class="form-control"/>
                  <button>검색</button>
                </div>
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
                    </div><span class="ct">${r.user.fullname }</span><span>(${r.user.email })</span>
                  </li>
                </ul>
              </div>
            </div>
            <div class="modal-ct">
              <div class="modal-title"><span>쪽지 내용 입력</span><span class="total"><span id="msgRemain">0</span> / 500</span></div>
              <div class="modal-main">
                <textarea name="editor2" id="content_mess" rows="6" maxlength="500" data-error-container="#editor2_error" class="rw-ckeditor form-control"></textarea>
              </div>
            </div>
          </div>
          <div class="modal-footer"> <button class="send-email-psychlogical" data-userid="${r.user.id}" data-psyTestCounselorId="${r.id}" data-email="${r.user.email}">쪽지 보내기</button>
            <!-- Are you sure you want to delete?-->
          </div>
        </div>
      </div>
    </div>
    <!-- .popupConfirmDelete-->
    
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

    <div id="popupConfirmCancelRequest" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
          </div>
          <div class="modal-body">
            <h4><strong>${r.user.fullname } 에게 요청한 심리검사를 취소하시겠습니까?</strong>
              <!-- Are you sure you want to delete?-->
            </h4>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-default btn-not-cancelrequest-psy">취소</button>
            <button type="button" class="btn red btn-confirm-cancelrequest-psy">확인</button>
          </div>
        </div>
      </div>
    </div>
      
<script type="text/javascript">
	$(document).ready(function() {
	
		 $('#content_mess').on('keyup', function(evt) {
		        var msgRemain = $(this).val().length;
		        $('#msgRemain').text(msgRemain);
		    });
		
		
	});
</script>

