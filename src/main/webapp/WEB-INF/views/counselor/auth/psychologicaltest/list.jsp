<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<div class="member page-content-wrapper counseling-history psychological">
        <div class="page-title">
          <div class="title"><span>심리검사</span></div>
          <div class="title-ct"> <span>회원에게 요청한 심리검사 정보입니다.</span></div>
        </div>
        <div class="page-content">
          <div class="container">
           <form name="test">
            <div class="main-top border-full bg-fff">
              <div class="content-top">
                <div class="md-content">
                  <div class="md-item"><span class="title">심리검사명</span>
                  <c:set value=" ${fn:join(testname0,' ') } " var="test_name_value"></c:set>
                    <div class="item-ct style-list">
                    	<c:forEach items="${psychologicalTestSettings}" var="p">
                    		<div class="item radio-checkboxstyle2">
                    			<c:set value=" ${p.id } " var="test_id"></c:set>
                    			<c:choose>
                    				<c:when test="${fn:contains(test_name_value, test_id)}">
                    					<input type="checkbox" value="${p.id}" name="testname0" checked="checked" /><span>${p.testName}</span>
                    				</c:when>
                    				<c:otherwise>
                    					<input type="checkbox" value="${p.id}" name="testname0" /><span>${p.testName}</span>
                    				</c:otherwise>
                    			</c:choose>
		                     </div>
                    	</c:forEach>
                    </div>
                  </div>
                  <div class="md-item"><span class="title">상태</span>
                    <div class="item-ct">
                    
                    <c:set value=" ${fn:join(statuss,' ') } " var="statuss_value"></c:set>
                    
                    	<c:forEach begin="0" end="4" var="s">
                    		<div class="item radio-checkboxstyle2">
                    			<c:set value=" ${s } " var="s_id"></c:set>
                    			<c:choose>
                    				<c:when test="${fn:contains(statuss_value, s_id)}">
                    					<input type="checkbox" value="${s}" name="statuss" checked="checked" /><span>
	                    					<c:if test="${s==0}">결제 대기</c:if>
			                    			<c:if test="${s==1}">결제 완료</c:if>
			                    			<c:if test="${s==2}">결제 포기</c:if>
			                    			<c:if test="${s==3}">결제 취소</c:if>
			                    			<c:if test="${s==4}">요청 취소</c:if></span>
                    				</c:when>
                    				<c:otherwise>
                    					<input type="checkbox" value="${s}" name="statuss" /><span>
                    						<c:if test="${s==0}">결제 대기</c:if>
			                    			<c:if test="${s==1}">결제 완료</c:if>
			                    			<c:if test="${s==2}">결제 포기</c:if>
			                    			<c:if test="${s==3}">결제 취소</c:if>
			                    			<c:if test="${s==4}">요청 취소</c:if></span>
                    				</c:otherwise>
                    			</c:choose>
                    		</div>
                    	</c:forEach>
                    </div>
                  </div>
                  <div class="md-item"><span class="title">회원 검색</span>
                    <div class="item-ct input-form">
                      <input type="text" name="fullname" id="fullname" value="${fullname}" data-required="1" class="form-control"/>
                    </div>
                  </div>
                </div>
              	<button class="md-link" type="submit"> 검색</button>
              </div>
            </div>
             </form>
            <div class="main-content border-full bg-fff">
              <div class="content-main">
                <div class="main-top"><span class="title">심리검사 요청 리스트</span><span class="price">${totalElement}건</span>
                
                  <select name="orderBy" id="order_psy_test"  class="form-control selectpickermember pull-right">
                    <option value="request_psychological" ${orderBy == 'request_psychological' ? 'selected' : ''}>최근 요청 순</option>
                    <option value="payment_psychological" ${orderBy == 'payment_psychological' ? 'selected' : ''}>최근 결제 순</option>
                    <option value="fuName" ${orderBy == 'fuName' ? 'selected' : ''}>이름 순</option>
                  </select>
                  
                  <button class="right v2 popup-psychlogical2">심리검사 요청</button>
                  <button class="right popup-psychlogical1">심리검사 사이트 정보</button>
                </div>
                <div class="main-ct">
                  <div class="table-wrapper">
                    <div class="table-responsive">
                      <table class="table table-striped table-hover text-center">
                        <thead>
                          <tr>
                            <th>번호</th>
                            <th>회원명</th>
                            <th>심리검사</th>
                            <th>요청 일시</th>
                            <th>결제 일시</th>
                            <th>상태</th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach items="${list}" var="ps">
                          		<tr>
		                            <td>${ps.id}</td>
		                            <td><a href="/auth/psychologicaltest/detail?id=${ps.id }">${ps.userFullName}</a></td>
		                            <td> 
		                              <p>${ps.testName}</p>
		                            </td>
		                            <td>
		                            	<fmt:formatDate value="${ps.requestPsychological}" pattern="yyyy-MM-dd"/>(<fmt:formatDate value="${ps.requestPsychological}" pattern="EE"/>) 
                           				<fmt:formatDate value="${ps.requestPsychological}" pattern="HH:mm:ss"/>
		                            </td>
		                            <td>
		                            	<c:if test="${ps.paymentPsychological != null}">
		                            		<fmt:formatDate value="${ps.paymentPsychological}" pattern="yyyy-MM-dd"/>(<fmt:formatDate value="${ps.paymentPsychological}" pattern="EE"/>) 
                           					<fmt:formatDate value="${ps.paymentPsychological}" pattern="HH:mm:ss"/>
		                            	</c:if>
		                            </td>
		                            <td>
		                           			<c:if test="${ps.statusPayment==0}">결제 대기</c:if>
			                    			<c:if test="${ps.statusPayment==1}">결제 완료</c:if>
			                    			<c:if test="${ps.statusPayment==2}">결제 포기</c:if>
			                    			<c:if test="${ps.statusPayment==3}">결제 취소</c:if>
			                    			<c:if test="${ps.statusPayment==4}">요청 취소</c:if>
		                            </td>
		                         </tr>
                          </c:forEach>
                        </tbody>
                      </table>

                    </div>
                    <jsp:include page="/WEB-INF/views/counselor/layout/pagination_custom.jsp">
                                <jsp:param name="currentIndex"             value="${currentIndex}"/>
                                <jsp:param name="url"              value="${pagination_navigator}"/>
                                <jsp:param name="totalPages"        value="${totalPages}"/>
                                <jsp:param name="param_url"     value="${param_url}"/>
                    </jsp:include>
                  </div>
                </div>
              </div>
            </div>
          </div>
          
         
        </div>
      </div>
      
<div id="popup-psychlogical" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="popup-psychlogical modal fade popup-facetalk">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4>심리검사 사이트 정보</h4>
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
          </div>
          <div class="modal-body">
            <div class="modal-body-title"><span>심리검사 사이트 접속 시 아래의 계정으로 로그인하시면 됩니다.</span></div>
            <div class="modal-list">
              <ul>
                <li>
                	<span class="title" style="height: 100% !important;line-height: 65px;">가이던스</span>
                	<p style="margin-bottom: 0;"><span>아이디</span><span class="color4">${user.username}</span></p>
                	<p style="margin-bottom: 0;margin-top: -5px;"><span>비밀번호</span>
                	<span class="color4">
                		<fmt:formatDate pattern = "yyyyMMdd" value = "${user.dayOfBirth}" /> 
                	</span>
                	 <p style="margin-bottom: 0;margin-top: -5px;"><span></span><span style="margin-left: 70px;">로그인 후 수정 가능합니다</span></p>
                	</p>
                </li>
                <li><span class="title">어세스타</span><span>직접 가입하신 계정으로 로그인</span></li>
              </ul>
            </div>
            <div class="modal-footer" style="padding-bottom: 0;">
            	<a href="http://guidance.hellowithyou.com" target="_blank" class="link1 link-update1">가이던스 바로가기</a>
            	<a href="http://www.career4u.net/Main/Main.asp" target="_blank" class="link2 link-update1">어세스타 바로가기</a>
              <!-- Are you sure you want to delete?-->
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- .popupConfirmDelete-->

    <div id="popup-psychlogical2" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="popup-psychlogical23 modal fade popup-facetalk">
      <div class="modal-dialog">
      <form name="test1">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
          </div>
          <div class="modal-body">
            <div class="modal-ct">
              <div class="modal-title"><span>받는 회원 선택</span></div>
              <div class="modal-main">
                <ul> 
                <c:forEach items="${userCounselingSessions}" var="uc">
                	 <li>
	                    <div class="title">
	                      <div data-error-container="#form_2_services_error" class="checkbox-list input-checkboxstyle2">
	                        <label>
	                          <input type="radio" value="${uc.userId }" name="user_id" checked="checked" class="radio1"/>
	                        </label>
	                      </div>
	                    </div><span class="ct">${uc.fullName }</span><span>(${uc.email })</span>
	                  </li>
                </c:forEach>
                </ul>
              </div>
            </div>
            <div class="modal-ct">
              <div class="modal-title"><span>심리검사 선택</span></div>
              <div class="modal-main">
                <ul> 
	                <c:forEach items="${psychologicalTestSettings}" var="pc">
	                	<c:if test="${pc.testName eq 'MBTI'}">
	                    	 <c:if test="${mbtiCertificate == null}">
	                    	 	<li class="none-active">
				                    <div class="title">
				                      <div class="radio-checkboxstyle2">
				                        <input type="checkbox" value="${pc.id}" data-fee="${pc.fee}" name="test_name" class="test-name"/>
				                      </div>
				                    </div><span>
				                    	${pc.testName}
				                    </span>
			                  	</li>
	                    	 </c:if>
	                    	 <c:if test="${mbtiCertificate != null}">
	                    	 	<li>
				                    <div class="title">
				                      <div class="radio-checkboxstyle2">
				                        <input type="checkbox" value="${pc.id}" data-fee="${pc.fee}" name="test_name" class="test-name"/>
				                      </div>
				                    </div><span>
				                    	${pc.testName}
				                    </span>
			                  	</li>
	                    	 </c:if>
	                    </c:if>
	                    
	                    <c:if test="${(pc.testName ne 'MBTI') && (pc.testName ne 'MMTIC')}">
	                    	<li>
				                    <div class="title">
				                      <div class="radio-checkboxstyle2">
				                        <input type="checkbox" value="${pc.id}" data-fee="${pc.fee}" name="test_name" class="test-name"/>
				                      </div>
				                    </div><span>
				                    	${pc.testName}
				                    </span>
			                  	</li>
	                    </c:if>
	                </c:forEach>
                </ul>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <div class="footer-ct"><span class="title">심리검사 <span class="count-checked">0</span>건,</span><span id="display-fee">0</span>원 (VAT 포함)  또는 상담권 <span class="count-checked">0</span>매
            </div><button type="submit" data-id="${counselorId}" class="link1  save-user-psychlogical">회원에게 심리검사 요청</button>
            <!-- Are you sure you want to delete?-->
          </div>
        </div>
        </form>
      </div>
    </div>
    <!-- .popupConfirmDelete-->

