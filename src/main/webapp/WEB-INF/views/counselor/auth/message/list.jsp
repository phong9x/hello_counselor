<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="member page-content-wrapper counseling-history massage" style="margin-bottom: 130px;">
        <div class="page-title">
          <div class="title"><span>쪽지</span></div>
          <div class="title-ct"> <span>회원과 주고 받은 쪽지 리스트입니다.</span></div>
        </div>
        <div class="page-content">
       
          <div class="container">
            <div class="main-top border-full bg-fff">
              <div class="tabbable-custom">
                <div class="sent-mail"><i class="fa fa-envelope-o"></i><span>쪽지쓰기</span></div>
                <ul class="nav nav-tabs">
                  <li class="${number_diff eq 1 ? 'active' : ''} tab_message_1"><a href="#tab-11" data-toggle="tab"> <span>받은 쪽지 </span><i aria-hidden="true" class="fa fa-caret-down"></i></a></li>
                  <li class="${number_diff eq 2 ? 'active' : ''} tab_message_2"><a href="#tab-12" data-toggle="tab"> <span>보낸 쪽지</span><i aria-hidden="true" class="fa fa-caret-down"></i></a></li>
                </ul>

                <div class="tab-content">
                  <div id="tab-11" class="tab-10 tab-pane ${number_diff eq 1 ? 'active' : ''}  t_mess1">
                    <div class="content-top">
                    <form id ="test11">
                      <div class="md-content">
                        <div class="md-item abc"><span class="title">받은 날짜</span>
                          <div class="item-ct">
                            <div class="item icon-next datepicker-item">
                              <input type="hidden" name="number_diff" id="number_diff" value="1" />
                              <input name="start_mess_tab1" type="text" data-date-format="yyyy-mm-dd" id="start_mess_tab1" value="${start_mess_tab1}" class="form-control date-picker_message"/>
                            </div>
                            <div class="item datepicker-item">
                              <input name="end_mess_tab1" type="text" id="end_mess_tab1" data-date-format="yyyy-mm-dd" value="${end_mess_tab1}" class="form-control date-picker_message"/>
                            </div>
                          </div>
                        </div>
                        <div class="md-item"><span class="title">검색</span>
                          <div class="item-ct input-form">
                            <input type="text" name="keyword_message1" value="${keyword_message1}" id="keyword_message1" data-required="1" class="form-control"/>
                          </div>
                        </div>
                      </div>
                      <button class="md-link tab1_mess" type="submit"> 검색</button>
                      </form>
                    </div>
                    
                  </div>
                  
                  
                  <div id="tab-12" class="tab-10 tab-pane ${number_diff eq 2 ? 'active' : ''} t_mess2">
                    <div class="content-top">
                    <form id ="test12">
                      <div class="md-content">
                        <div class="md-item"><span class="title">보낸 날짜</span>
                          <div class="item-ct">
                            <div class="item icon-next datepicker-item">
                             <input type="hidden" name="number_diff" id="number_diff" value="2" />
                              <input name="start_mess_tab2" type="text" data-date-format="yyyy-mm-dd" id="start_mess_tab2" value="${start_mess_tab2}" class="form-control date-picker"/>
                            </div>
                            <div class="item datepicker-item">
                              <input name="end_mess_tab2" type="text" data-date-format="yyyy-mm-dd" id="end_mess_tab2" value="${end_mess_tab2}" class="form-control date-picker"/>
                            </div>
                          </div>
                        </div>
                        <div class="md-item"><span class="title">검색</span>
                          <div class="item-ct input-form">
                            <input type="text" name="keyword_message2" id="keyword_message2" value="${keyword_message2}" data-required="1" class="form-control"/>
                          </div>
                        </div>
                      </div>
                      <button class="md-link tab2_mess" type="submit"> 검색</button>
                      </form>
                    </div>
                  </div>
                  
                </div>
              </div>
            </div>
            <div class="main-content border-full bg-fff">
              <div class="content-main">
                <div class="main-top">
                	<span class="title">
                		<c:if test="${number_diff == 1}">받은 쪽지 리스트</c:if>
                		<c:if test="${number_diff == 2}">보낸 쪽지 리스트</c:if>
                	</span>
                	<span class="price">${totalElement}건</span>
                  <select name="orderBy" id="order_inbox_mess" class="form-control selectpickermember pull-right">
                    <option value="create_date_mess" ${orderBy == 'create_date_mess' ? 'selected' : '' }>최근 순</option>
                    <option value="full_name_user_send" ${orderBy == 'full_name_user_send' ? 'selected' : '' }>이름 순</option>
                  </select>
                </div>
                <div class="main-ct">
                  <div class="table-wrapper">
                    <div class="table-responsive">
                      <table class="table table-striped table-hover text-center">
                        <thead>
                          <tr>
                            <th> 
                              <div class="radio-checkboxstyle2">
                                <input id="massage-all-checkbox" type="checkbox" value="0" name="service"/>
                              </div>
                            </th>
                            <th>번호</th>
                            <th>보낸 회원</th>
                            <th>내용</th>
                            <th>받은 일시</th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach items="${list}" var="l">
                          	<tr>
	                            <td> 
	                              <div class="radio-checkboxstyle2" id="rates">
	                                <input type="checkbox" value="${l.messageId }" name="mess_radio" class="item-checkbox mess_checked_delete"/>
	                              </div>
	                            </td>
	                            <td>${l.id}</td>
	                            <td data-id="${l.id}" data-userId="${l.userId}" data-fullname="${l.userName}" data-email="${l.userEmail}" data-date="${number_diff == 1 ? l.receivedDate : l.sendDate}"
	                            data-number_diff="${number_diff}" data-content="${l.content}" class="openpopup-confim detail_mess">${l.userName}</td>
	                            <td data-number_diff="${number_diff}" data-id="${l.id}" data-userId="${l.userId}" data-fullname="${l.userName}" data-email="${l.userEmail}" data-date="${number_diff == 1 ? l.receivedDate : l.sendDate}"
	                            data-content="${l.content}" class="openpopup-confim">
	                            	<c:if test="${fn:length(l.content) > 30}">${fn:substring(l.content, 0, 30)} [...]</c:if>
	                            	<c:if test="${fn:length(l.content) <= 30}">${l.content}</c:if>
	                            </td>
	                            <td>
	                            <c:if test="${number_diff == 1}">
	                            	<fmt:formatDate value="${l.receivedDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
	                            </c:if>
	                            <c:if test="${number_diff == 2}">
	                            	<fmt:formatDate value="${l.sendDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
	                            </c:if>
	                            </td>
                            </tr>
                          </c:forEach>
                        </tbody>
                      </table>

                    <div>
                    	<button class="search-name delete_message_list" data-total="${totalElement}">삭제</button>
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
      </div>
      
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
	                  <input type="text" name="search_keyword" placeholder="회원명 또는 아이디 입력"  onfocus="this.placeholder = ''" onblur="this.placeholder = '회원명 또는 아이디 입력'" class="form-control"/>
	                  <button data-list-user="${userList}" class="search_user_mess">검색</button>
	                </div>
	              </div>
	              <div class="modal-main">
	                <ul> 
	                	<c:forEach items="${userList}" var="lis">
	                		<li>
			                    <div class="title">
			                      <div data-error-container="#form_2_services_error" class="checkbox-list input-checkboxstyle2">
			                        <label>
			                          <input type="radio" id="user_id_ms" value="${lis.userId}" name="service" class="radio1"/>
			                        </label>
			                      </div>
			                    </div><span class="list-search-items"><span class="ct">${lis.fullName}</span><span>(${lis.email})</span></span>
			                  </li>
	                	</c:forEach>
	                </ul>
	              </div>
	            </div>
	            <div class="modal-ct">
	              <div class="modal-title btn-count-char"><span>쪽지 내용 입력</span><span class="total"><span id="msgRemain">0</span> / 500</span></div>
	              <div class="modal-main">
	                <textarea name="editor2" id="content2_ms" rows="6" data-error-container="#editor2_error" class="rw-ckeditor form-control"></textarea>
	              </div>
	            </div>
	          </div>
	          <div class="modal-footer"><button class="link1 btn-send-mail">쪽지 보내기</button>
	            <!-- Are you sure you want to delete?-->
	          </div>
	        </div>
	      </div>
	    </div>
    
    <div id="popup-psychlogical4" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="popup-psychlogical23 modal fade popup-facetalk">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face ps4"></button>
          </div>
          <div class="modal-body popup3">
            <div class="modal-ct">
              <div class="modal-title"><span>받는 회원 선택</span>
                <!-- <div class="search3">
                  <input type="text" name="name" placeholder="회원명 또는 아이디 입력"  onfocus="this.placeholder = ''" onblur="this.placeholder = '회원명 또는 아이디 입력'" class="form-control"/>
                  <button>검색</button>
                </div> -->
              </div>
              <div class="modal-main content_send_mess">
              	<div class="modal-main">
	                <ul>
	               		<li>
		                    <div class="title">
		                      <div data-error-container="#form_2_services_error" class="checkbox-list input-checkboxstyle2">
		                        <label>
		                          <input type="radio" value="1" name="service" checked="checked" class="radio1"/>
		                           <input type="hidden" class="data-userid"/>
		                        </label>
		                      </div>
		                    </div><span class="ct data-fullname"></span><span class="data-mail"></span>
		                  </li>
	                </ul>
	              </div>
              </div>
            </div>
            <div class="modal-ct">
              <div class="modal-title"><span>쪽지 내용 입력</span><span class="total"><span id="msgRemain1">0</span> / 500</span></div>
              <div class="modal-main">
                <textarea name="editor2" rows="6" id="content_mss" data-error-container="#editor2_error" class="rw-ckeditor form-control"></textarea>
              </div>
            </div>
          </div>
          <div class="modal-footer">
            <button class="link1 send_email_mess_detail" type="submit"> 쪽지 보내기</button>
          </div>
        </div>
      </div>
    </div>
    
   <div id="popup-massage" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="popup-psychlogical23 modal fade popup-facetalk">
      <div style="width: 400px;" class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4>${number_diff eq 1 ? '받은 쪽지' : '보낸 쪽지'}</h4>
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
          </div>
          <div class="modal-body">
            <div class="modal-massage">
              <div class="row">
                <div class="col-md-3"><span class="color888">보낸 회원</span></div>
                <div class="col-md-9"><span class="fullname_"></span> (<span class="email_"></span>)</div>
              </div>
              <div class="row">
                <div class="col-md-3"><span class="color888">받은 일시</span></div>
                <div class="col-md-9"><span class="date_"></span></div>
              </div>
              <div class="modal-main" style="border: 1px solid #cdcdcd;padding: 10px;min-height: 120px;">
              	<p class="content_"> </p>
              </div>
            </div>
          </div>
          <c:if test="${number_diff == 1}">
          		<div class="modal-footer">
          			<button class="link1 sent-mail_mess_setail send_mess_detail" type="submit"> 답장쓰기</button>
          		</div>
          </c:if>
        </div>
      </div>
    </div>
    
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

    <div id="popup_msg_empty" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>쪽지 내용을 입력하세요.</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
	
		 $('#popup-psychlogical3 #content2_ms').on('keyup', function(evt) {
		        var msgRemain = $(this).val().length;
		        $('#msgRemain').text(msgRemain);
		    });
		 
		 $('#popup-psychlogical4 #content_mss').on('keyup', function(evt) {
		        var msgRemain = $(this).val().length;
		        $('#msgRemain1').text(msgRemain);
		    });
		
		
	});
</script>


    
