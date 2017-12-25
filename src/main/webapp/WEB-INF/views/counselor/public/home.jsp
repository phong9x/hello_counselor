<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>


<div class="member page-content-wrapper">
    <div id="slide-login">
        <div class="owl-carousel owl-theme">
        	<c:forEach begin="0" end="4" items="${list}" var="i">
        		<div class="item"><img src="${pageContext.request.contextPath}${i.imageUrl}"/></div>
        	</c:forEach>
        </div>
        <div class="login-contact">
            <div class="container">
                <div class="left">
                    <span>서비스 관련 문의</span>
                    <span>hello_counselor@aimmed.com</span>
                </div>
                <div class="right"><span class="popup-faq">자주 묻는 질문</span></div>
            </div>
        </div>
    </div>
</div>

<div id="popup-faq" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk popup-faq">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4> <span>자주묻는 질문</span><span class="color2">Q&A</span></h4>
            <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
          </div>
          <div class="modal-body">
            <div class="modal-list counseling-history detail notice-detail">
              <div class="main-ct return-faq">
                <div class="main-item">
                  <div id="accordion" class="item-content">
                  <c:forEach items="${faqs}" var="f">
                    <div class="item panel panel-default">
                    	
                    		<div class="item-header">
                    			<span class="color2">Q. </span>
                    			<span>${f.title}</span>
                    			<a style="position: relative;width: 45px;text-align: right;" class="pull-right collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#faq${f.id}" aria-expanded="true" aria-controls="faq${f.id}">
		                          <i style="float: left;margin-top: 11px;" class="fa fa-angle-down" aria-hidden="true"></i>
                          		  <i style="float: left;margin-top: 11px;" class="fa fa-angle-up" style="margin-right: 10px;" aria-hidden="true"></i>
                          		  <span class="close-faq">닫기</span>
                          		  <span class="open-faq">열기</span>
                          		 </a>
		                     </div>
                    		<c:if test="${f.content != null}">
                    			<div id="faq${f.id}" class="panel-collapse collapse" role="tabpanel" >
                    				<div class="panel-body">
		                    			<span class="color1">A. </span>
		                    			<span>${f.content}</span>
	                    			</div>
                    			</div>
                    		</c:if>
                    	
                    </div>
                    </c:forEach>
                  </div>
                </div>
              </div>
            </div>
            <div class="modal-footer">
              <button type="submit" class="btn btn-close-popup-faq">닫기</button>
              <!-- Are you sure you want to delete?-->
            </div>
          </div>
        </div>
      </div>
  </div>
  
<script>
    $(document).ready(function() {
        $("#slide-login .owl-carousel").owlCarousel({
            singleItem: true,
            pagination: true,
            autoPlay: true,
            stopOnHover:false,
            nav:true,
            addClassActive: true
        });
        
     // accdion
        $(function () {
          $(document.body).on('click', '.popup-faq .close-face', function(e) {
            $('.popup-faq .panel-collapse.in').removeClass('in');
            $('.popup-faq .item-header a.pull-right').addClass('collapsed');
          });
        });
    });
</script>