<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>



 <div class="member page-content-wrapper counseling-history change-pass">
        <div class="page-title">
          <div class="title"><span>비밀번호 변경</span></div>
          <div class="title-ct"> <span>개인정보 보호를 위한 비밀번호 변경 안내</span></div>
        </div>
        <div class="page-content">
          <div class="container">
            <div class="icon-key-pass"><img src="/theme/admin/assets/images/icon/icon-changepass.png" alt="logo"/>
              <p class="color888"> <span class="blod">김태희 </span>상담사 님께서는 동일한 비밀번호를 3개월 동안 사용하고 계십니다.</p>
              <p class="color888">안전한 정보 보호를 위해 3개월마다 비밀</p>
            </div>
            <div class="main-content">
                <div class="border-full bg-fff" style="display: inline-block;"> 
                  <div class="row">
                    <div class="col-md-4 title">현재 비밀번호</div>
                    <div class="col-md-6">
                      <input type="text" name="current_password" data-required="1" class="form-control onchange_current_pass"/>
                      <p class="result_pass" style="display: none; margin: 0 0 10px; font-size: x-small; color: red;">비밀번호는 영문+숫자+특수문자 혼용하여 8~20자 이내로 설정해야 합니다.</p>
                      <p class="result_current_test" style="display: none; margin: 0 0 10px; font-size: x-small; color: red;">비밀번호가 일치하지 않습니다.</p>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4 title">현재 비밀번호</div>
                    <div class="col-md-6">
                      <input type="text" name="new_password" data-required="1" maxlength="20" minlength="8" placeholder="8~20자 이내, 영문+숫자+특수문자 혼용" class="form-control onchange_new_password"/>
                      <p class="result_new_password" style="display: none; margin: 0 0 10px; font-size: x-small; color: red;">비밀번호는 영문+숫자+특수문자 혼용하여 8~20자 이내로 설정해야 합니다.</p>
                    	<p class="result_new_password_test" style="display: none; margin: 0 0 10px; font-size: x-small; color: red;">aaaaaaaadsfffffaaaaaaa</p>
                    </div>
                  </div>
                  <div class="row">
                    <div class="col-md-4 title">현재 비밀번호</div>
                    <div class="col-md-6">
                      <input type="text" name="confirm_password" data-required="1" maxlength="20" minlength="8" placeholder="8~20자 이내, 영문+숫자+특수문자 혼용" class="form-control onchange_confirm_password"/>
                      <p class="result_confirm_password" style="display: none; margin: 0 0 10px; font-size: x-small; color: red;">비밀번호는 영문+숫자+특수문자 혼용하여 8~20자 이내로 설정해야 합니다.</p>
                    </div>
                  </div>
                </div>
                <div class="main-submit">
                  <button type="submit" class="btn-change-pass"><span>비밀번호 변경</span></button>
                </div>
                
            </div>
          </div>
        </div>
      </div>
      
      <div id="popupInforChangedPass" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"></button>
          </div>
          <div class="modal-body">
            <h4><strong>비밀번호가 정상적으로 변경되었습니다. 변경한 비밀번호로 다시 로그인해주세요.</strong>
            </h4>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn-back_gate-screen">확인</button>
          </div>
        </div>
      </div>
    </div>