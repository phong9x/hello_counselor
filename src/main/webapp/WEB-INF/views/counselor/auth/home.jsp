<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<div class="member page-content-wrapper">
    <div class="page-content">
        <div class="container">
            <!-- home top 3-->
            <div class="home-top3 row">
                <div class="col-md-9">
                    <div class="content border-full bg-fff">
                    <style>
                    	.page-content-wrapper .home-top3 .content .content-main .item .link{z-index:9;}
                    </style>
                        <div class="content-top">
                            <div class="top3-title">
                                <span class="md-title">내 프로필</span>
                                <span class="md-ct">현재 나의 상태를 나타냅니다</span>
                            </div>
                            <div class="top3-content">
                                <a class="link" href="/auth/info/detail#tab-12"><span>프로필 수정</span><i class="fa fa-caret-right" aria-hidden="true"></i></a>
                                <div class="acc-img">
                                    <img src="${counselor.thumbnailUrl}" class="logo-default"/>
                                </div>
                                <div class="acc-content">
                                    <div class="title">
                                        <span class="bold">${counselor.user.fullname}</span>
                                        <span class="ct">심리상담사</span>
                                        <div class="item-review stylev2">
                                            <input type="number" data-readonly="" value="${counselor.points}" class="rating" readonly="readonly" />
                                        </div>
                                    </div>
                                    <div class="item">
                                        <span>소개</span>
                                        <p>${fn:substring(counselor.introduce, 0, 30)}...</p>
                                    </div>
                                    <div class="item-bottom">
                                        <div class="md">
                                            <div class="md-left">약력</div>
                                            <div class="md-right">${certificates} 건</div>
                                        </div>
                                        <div class="md">
                                            <div class="md-left">자격증</div>
                                            <div class="md-right">${educations} 건</div>
                                        </div>
                                        <div class="md">
                                            <div class="md-left">상담횟수</div>
                                            <div class="md-right">${counselor.counselNumber}</div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="content border-full bg-fff">
                        <div class="content-main">
                            <div class="item">
                                <a href="/auth/schedule/list" class="link">자세히 보기</a>
                                <div class="title">
                                    <span>실시간 상담</span><i class="fa fa-commenting" aria-hidden="true"></i>
                                </div>
                                <p class="ct">
                                    <span>실시간 스케줄 설정이 가능합니다</span>
                                </p>
                                <div class="status ${isActive ? 'on' : 'off'}">
                                    <span>${isActive ? 'ON' : 'OFF'}</span>
                                </div>
                            </div>
                            <div class="item">
                                <a class="link" href="/auth/reservation/list">자세히 보기</a>
                                <div class="title">
                                    <span>상담 예정</span><i class="fa fa-clock-o" aria-hidden="true"></i>
                                </div>
                                <p class="ct">
                                    <span>현재 상담 예정된 건수</span>
                                </p>
                                <div class="status">
                                    <span class="number">${scheduledCounselor}</span><span class="spa">건</span>
                                </div>
                            </div>
                            <div class="item">
                                <a href="/auth/message/list" class="link">자세히 보기</a>
                                <div class="title">
                                    <span>받은 쪽지</span><i class="fa fa-envelope" aria-hidden="true"></i>
                                </div>
                                <p class="ct"><span>새로 받은 쪽지 수</span></p>
                                <div class="status">
                                    <span class="number">${notSeenMessage}</span><span class="spa">건</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="content-right border-full bg-fff">
                        <div class="md-item">
                            <div class="icon"><i class="fa fa-dollar" aria-hidden="true"></i></div>
                            <p class="title">지난 달 정산 금액</p>
                            <c:if test="${isCounselorCenter == 0 }">
                            	<c:if test="${getTotalMoneyByMonth != null }">
                            		<p><span class="price">${getTotalMoneyByMonth.adminRefund} </span><span>원</span></p>
	                            	<div class="note-link">
	                            		<c:if test="${getTotalMoneyByMonth.status == 0}">미지급</c:if>
	                            		<c:if test="${getTotalMoneyByMonth.status == 1}">지급완료</c:if>
	                            	</div>
                            	</c:if>
                            	<c:if test="${getTotalMoneyByMonth == null }">
                            		<p><span class="price">0 </span><span>원</span></p>
                            		<div class="note-link">미지급</div>
                            	</c:if>
                            	
                            </c:if>
                            <c:if test="${isCounselorCenter == 1 }"><p>소속 상담센터 정산</p></c:if>
                        </div>
                        <div class="md-item">
                            <div class="icon"><i class="fa fa-calendar" aria-hidden="true"></i></div>
                            <p class="title">지난 달 상담 건수</p>
                            <p><span class="price">${lastCounsel} </span><span>건</span></p>
                        </div>
                        <div class="md-item">
                            <div class="icon"><i class="fa fa-clock-o" aria-hidden="true"></i></div>
                            <p class="title">이번 달 상담 건수</p>
                            <p><span class="price">${currentCounsel} </span><span>건</span></p>
                        </div>
                    </div>
                </div>
            </div>

            <div class="home-top4 row">
                <div class="col-md-9">
                    <div class="content border-full bg-fff">
                        <div class="md-content">
                            <div class="md-title"> <span>공지사항</span><a href="/auth/notice/list" class="link">더보기</a></div>
                            <div class="row">
                            	<c:forEach items="${notices}" var="item">
                            		<div class="col-md-6">
	                                    <div class="title">
	                                    	<span class="title-content">
		                                    	<a href="/auth/notice/detail?id=${item.id}" style="color: #526fd1;">
			                                        ${item.title}
		                                        </a>
	                                        </span>
	                                        <span class="date-time" >
	                                        	<fmt:formatDate pattern = "yyyy-MM-dd" value = "${item.createDate}" />
	                                        </span>
	                                    </div>
	                                </div>
                            	</c:forEach>
                            </div>
                        </div>

                        <div class="md-content">
                            <div class="md-title"> <span>만족도 평가</span></div>
                            <div class="row">
                            	<c:forEach items="${comments}" var="item">
                            		<div class="col-md-6" onclick="showCommentPopup(${item.id})">
	                                    <div class="md-content-title " data-content="${item.comment}" data-point="${item.point}">
	                                    	<span class="title">${item.comment}</span>
					                        <div class="item-review">
					                          <input type="number" data-readonly="" value="${item.point}" class="rating"/>
					                        </div>
	                                    </div>
	                                </div>
	                                <div id="popupShowComment${item.id}" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade ">
								      <div class="modal-dialog" style="width: 350px; margin-top: 100px;">
								        <div class="modal-content">
								          <div class="modal-header">
								          	<h4>만족도 평가</h4>
								            <button type="button" data-dismiss="modal" aria-hidden="true" class="close"><jsp:text/></button>
								          </div>
								          <div class="modal-body" style="padding-bottom: 40px;">
								          	<div class="input-rating"><input type="number" data-readonly="" value="${item.point}" name="pointp" id="pointp" class="rating"/></div>
								            <p class="content-msss">${item.comment}</p>
								          </div>
								        </div>
								      </div>
    								</div>
                            	</c:forEach>
                                
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="content content-right border-full bg-fff">
                        <div class="md-item">
                            <div class="title">
                                <i class="fa fa-question-circle" aria-hidden="true"></i><span>서비스 관련 문의</span>
                            </div>
                            <span class="mail">hello_counselor@aimmed.com</span>
                        </div>
                        <div class="md-item">
                            <span>상담사 메뉴얼을 보시려면?  </span>
                            <div class="item-psd">
                                <div class="md-ct">
                                    <a id="btnDownloadManual" href="/theme/admin/assets/docs/counselor_manual.pdf" target="_blank">
                                        <i class="fa fa-save" aria-hidden="true"></i><span>상담사 매뉴얼 보기</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- .page-content-->
</div>
<!-- .member-->

<div id="popup_counselor_manual" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog" style="width: 500px;">
        <div class="modal-content">
            <div class="modal-header" style="border-bottom: 1px solid #e5e5e5;">
                <h4>심리상담사 등록 완료</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <p>
                    [${counselor.user.fullname}] 상담사님. 환영합니다.<br/>
                    Hello 공식 심리상담사 등록이 완료되었습니다.
                </p>
                <p>
                    상담사 매뉴얼에는 정산, 상담 설정 등 상담사님들이 꼭 확인해야 하는 내용들이 포함되어 있습니다.
                </p>
                <p>
                    <strong>아래의 상담사 매뉴얼을 다운로드하여 꼭 확인해주세요.</strong>
                </p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept">상담사 매뉴얼 Download</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    function showCommentPopup(id) {
		$('#popupShowComment'+id).modal('show');
	};

    $(document).ready(function() {

        if($('input[name="status_support"]').value=="Off"){
          $('.btn-support').hide();
        }
        $(".stars-default").rating();
        $(".stars-green").rating('create',{coloron:'green',onClick:function(){ alert('rating is ' + this.attr('data-rating')); }});
        $(".stars-herats").rating('create',{coloron:'red',limit:10,glyph:'glyphicon-heart'});
    	
    	$('.popupShowComment').on("click", function() {
    		var content = $(this).attr('data-content');
    		var point = parseFloat($(this).attr('data-point'));
    		console.log(point);
    		$('input[name=pointp]').val(point);
    		$('.content-msss').html(content);
    		$('#popupShowComment').modal('show');
    	});

        /**********************************************************************
        *
        * show first login popup
        *
        **********************************************************************/
        var firstLogin = ${counselor.user.loginFirst} || 0;
        if (firstLogin == 1) {
            // update to 0
            $
                .ajax({
                    url     : '/auth/home',
                    method  : 'put'
                })
                .then(function(res) {

                }, function(err) {

                });
            // show popup
            $('#popup_counselor_manual').modal('show');
        }

        $('#popup_counselor_manual').on('click', '.popup_accept', function(evt) {
            evt.preventDefault();
            $('#popup_counselor_manual').modal('hide');
            window.open('/theme/admin/assets/docs/counselor_manual.pdf', '_blank');
        });

    });
</script>