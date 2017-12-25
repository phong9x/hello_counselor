<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<div class="member page-content-wrapper counseling-history detail notice-detail">
        <div class="page-title">
          <div class="title"><span>고객센터</span></div>
          <div class="title-ct"> <span>FAQ  &nbsp; | &nbsp;  자주묻는 질문</span></div>
        </div>
        <div class="page-content">
          <div class="container">
            <div class="main-content border-full bg-fff">
              <div class="content-main">
                <div class="main-ct">
                  <div class="item-title"> 
                    <h4 style="font-weight: bold;">FAQ</h4>
                  </div>
                  
                  <div class="border-full form-search-faq">
                    <div class="col-md-2 text-right">키워드 검색</div>
                    <div class="search-faq">
                      <input type="text" name="keyword" value="${keyword}" id="keyword" data-required="1" class="form-control"/>
                      <div class="main-submit">
                        <button type="submit" class="btn-search-faq"> <span>검색</span></button>
                      </div>
                    </div>
                  </div>
                </div>
               
				
				<div class="modal-body">
					<div class="modal-list counseling-history detail notice-detail">
					  <div class="main-ct return-faq">
						<div class="main-item">
              			<h4>자주 묻는 질문</h4>
						<style>
							.counseling-history.detail .main-item .item-content .item.collapse{display: none;}
							.counseling-history.detail .main-item .item-content .item.collapse.in{display: inline-block;}
							.item-header:hover{cursor: pointer;}
						</style>
						  <div class="item-content">
							
								<c:if test="${list.size() > 0}">
									<c:forEach begin="0" end="${list.size()}" items="${list}" var="l">
										<div class="item" style="padding-left: 0;padding-right: 0;"> 
											<div class="item-header" style="padding-left: 15px;" href="#faq1${l.id }" data-toggle="collapse">
												<span class="color2">Q. </span><span>${l.title}</span>
											</div>
											<c:if test="${l.content != null }">
									  			<div id="faq1${l.id }" class="item style2 collapse" style="background: #f3f3f3;border:none;">
									  				<span class="color2">A. </span><span>${l.content}</span>
									  			</div>
									  		</c:if>
										</div>
									</c:forEach>
								</c:if>
								<c:if test="${list.size() == 0}">
								<div class="item"> 
									<div class="item-header">
										<p style="text-align: center;">결과를 찾을 수 없음</p>
									</div>
								</div>
								</c:if>
						  </div>
						</div>
					  </div>
					</div>
				  </div>
              </div>
            </div>
          </div>
        </div>
      </div>