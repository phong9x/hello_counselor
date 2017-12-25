<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<div class="face-header">
    <div class="logo">
        <a href="/auth/home">
            <img src="/theme/admin/assets/images/logo/logo_talk.png" alt="logo" class="logo-default"/>
        </a>
    </div>
    <div class="face-menu">
        <ul>
            <li><a id="btn_fullscreen" class="icon1"><span>전체화면</span></a></li>
            <li><a id="btn_mute" class="icon2 talk"><span>마이크끄기</span></a></li>
            <li><a id="btn_doc" class="icon3"><span>문서공유</span></a></li>
            <li><a id="btn_exit" class="icon4"><span>상담종료</span></a></li>
        </ul>
    </div>
    <div class="icon-next">
        <a href="#">
            <i class="fa fa-chevron-right" aria-hidden="true"></i>
            <i class="fa fa-chevron-left" aria-hidden="true"></i>
        </a>
    </div>
</div>

<div class="face-time">
    <div class="time-md">
        <span class="title">남은 시간</span>
        <span class="time">
            <span id="hour">00</span>&nbsp;:&nbsp;
            <span id="minute">${sessionTime}</span>&nbsp;:&nbsp;
            <span id="second">00</span>
        </span>
    </div>
</div>

<div class="face-video">
    <div id="load-file" style="width: 100% !important;height: 100%; display:none;">

    </div>
    <div class="bg-video video_large position-center" style="top: 50% !important">
        <video width="100%" height="100%" id="partner_video">
            Your browser does not support the video tag
        </video>
    </div>
    <div class="thum-video video_small">
        <video width="100%" height="100%" id="me_video" muted>
            Your browser does not support the video tag
        </video>
    </div>
</div>

<div class="page-container">
    <div class="footer-content">
        <div class="md-content">
            <span>내담자 이름</span><span class="title">${user.fullname}</span>
            <button id="btnInfo"><span>자세히 보기</span><span class="icon-next"><i class="fa fa-angle-right" aria-hidden="true"></i></span></button>
        </div>
        <div class="md-content"><span>마인드체크 정보</span>
            <button id="btnMindcheck"><span>자세히 보기</span><span class="icon-next"><i class="fa fa-angle-right" aria-hidden="true"></i></span></button>
        </div>
        <div class="md-content"><span>자가진단 정보</span>
            <button id="btnSelfDiagnosis"><span>자세히 보기</span><span class="icon-next"><i class="fa fa-angle-right" aria-hidden="true"></i></span></button>
        </div>
    </div>
    <div class="footer-right">
        <div class="item icon3 open-chatface" id="btnAccountPsy">
            <span>검사 사이트계정 정보</span>
        </div>
        <div class="item icon2" id="btnPsyTest">
            <span>심리검사</span>
        </div>
        <div class="item icon1" id="btnMemo">
            <span>상담메모 입력</span>
        </div>
    </div>
</div>

<div class="menu-right">
    <div class="right-header">
        <span>문서 공유</span>
        <button id="btn_upload"></button>
        <input type="file" id="inpUpload" style="display:none" />
    </div>
    <span class="close-right"></span>
    <div class="right-content style1">
        <ul id="doc_file">

        </ul>
        <div class="right-content style2">
            <ul id="doc_thumb">

            </ul>
        </div>
    </div>
    <div class="right-fotter">
        <a></a>
    </div>
</div>
<!--.page-container-->
<input type="hidden" name="memberName"  value="${user.fullname}"/>
<!-- popup -->
<div id="popupLogout-facetalk" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk popupLogout-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>상담 종료</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body"><strong>상담을 종료하시겠습니까?</strong>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal">취소</button>
                <button type="button" class="btn close-face-popup popup_accept">상담 종료</button>
            </div>
        </div>
    </div>
</div>

<div id="popupList-facetalk" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk popupList-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="text-align: center;padding-bottom: 5px;">
                <h4>회원 정보</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <div class="modal-list no-border">
                    <ul>
                        <li>
                            <span class="title">내담자 이름</span><span>${user.fullname}</span>
                        </li>
                        <li>
                            <span class="title">나이</span><span>${age}</span>
                        </li>
                        <li>
                            <span class="title">성별</span><span>${user.gender eq 1 ? '남자' : '여자'}</span>
                        </li>
                        <li>
                            <span class="title">최근 상담</span><span><fmt:formatDate value="${session.createDate}" />(<fmt:formatDate value="${session.startTime}" pattern="E" />)</span>
                        </li>
                        <li>
                            <span class="title">총 상담횟수</span><span>${total}</span>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="popupList-facechat" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk face-chat">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>상담 메모</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <div class="modal-top">
                    <span class="title">내담자 이름</span>
                    <span class="content">${user.fullname}</span>
                </div>
                <div class="modal-chat">
                    <div class="title"><span>상담 중에 가급적 필요한 내용만 적어주세요. 다른 상담사들은 볼 수 없습니다</span></div>
                    <textarea name="editor2" rows="6" data-error-container="#editor2_error" class="rw-ckeditor form-control"></textarea>
                    <div class="footer"><button id="btnUploadMemo">상담메모 입력</button></div>
                </div>
                <div class="modal-main">
                    <div class="modal-main-header">
                        <span class="title">상세 메모</span>
                    </div>
                    <div id="owl_memo" class="owl-carousel">

                    </div>
                </div>
            </div>
        </div>
    </div>
</div>





<div id="popupListv2-facetalk" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk popupListv2-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="text-align: center;padding-bottom: 5px;">
                <h4>마인드체크 정보</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <div class="modal-list no-border">
                    <ul id="mindcheckList" style="height: 350px; overflow: auto;">

                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<div id="popup_finish" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk popupListv2-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>상담 종료</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body"><strong>상담이 종료되었습니다.</strong>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal">상담 종료</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_reconnect_app" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk popupListv2-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>네트워크 지연 안내 팝업</h4>
            </div>
            <div class="modal-body">
            <p><strong>네트워크 지연으로 연결이 해제되었습니다. </strong></p>
            <p><strong>다시 연결합니다.</strong></p>
            </div>
            <div class="modal-footer">
                <button type="button" id="btn-reconnect-room-app" class="btn" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_reconnect_web" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk popupListv2-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>네트워크 지연 안내 팝업</h4>
            </div>
            <div class="modal-body">
            <p><strong>네트워크 지연으로 연결이 해제되었습니다. </strong></p>
            <p><strong>다시 연결합니다.</strong></p>
            </div>
            <div class="modal-footer">
                <button type="button" id="btn-reconnect-room-web" class="btn" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_finish_error" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk popupListv2-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>상담 종료</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body"><strong>연결 지연으로 상담이 종료되었습니다.</strong>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal">상담 종료</button>
            </div>
        </div>
    </div>
</div>



<div id="popup-self-diagnosis-test-list" data-cache="false" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div style="width:600px;" class="modal-dialog popup-faq-tab">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="text-center"><span>자가진단 정보</span></h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <div class="tabbable-custom">
                    <ul class="nav nav-tabs">
                        <li class="active"><a href="#tab-11" data-toggle="tab"> <span>스트레스</span><i aria-hidden="true" class="fa fa-caret-down"></i></a></li>
                        <li><a href="#tab-12" data-toggle="tab"> <span>우울</span><i aria-hidden="true" class="fa fa-caret-down"></i></a></li>
                        <li><a href="#tab-13" data-toggle="tab"> <span>불안</span><i aria-hidden="true" class="fa fa-caret-down"></i></a></li>
                    </ul>

                    <div class="tab-content">
                        <div id="tab-11" class="tab-10 tab-pane active">
                            <p class="title">스트레스 자가진단 항목은 총 5개 문항이며, 5문항의 평균값으로 결과를 산출합니다.</p>
                            <div class="tab-ct-top">
                                <div class="item color">낮음  </div>
                                <div class="item color">중간  </div>
                                <div class="item color">높음 </div>
                            </div>
                            <div class="tab-ct-top">
                                <div class="item">0 ~ 1.6  </div>
                                <div class="item">1.8 ~ 2.6  </div>
                                <div class="item">2.8 이상  </div>
                            </div>
                            <div class="tab-content" id="accordionStress">
                        
                            </div>
                        </div>
                        <div id="tab-12" class="tab-10 tab-pane">
                    	    <p class="title">우울 자가진단 항목은 총 20개 문항이며, 20문항의 총점으로 결과를 산출합니다.</p>
                            <div class="tab-ct-top">
                                <div class="item color">낮음  </div>
                                <div class="item color">중간  </div>
                                <div class="item color">높음 </div>
                            </div>
                            <div class="tab-ct-top">
                                <div class="item">0 ~ 20  </div>
                                <div class="item">21 ~ 24  </div>
                                <div class="item">25 ~ 60  </div>
                            </div>
                            <div class="tab-content" id="accordionDepressed">

                            </div>
                        </div>
                        <div id="tab-13" class="tab-10 tab-pane">
                    	    <p class="title">불안 자가진단 항목은 총 21개 문항이며, 21문항의 총점으로 결과를 산출합니다.</p>
						    <div class="tab-ct-top">
                                <div class="item color">낮음  </div>
                                <div class="item color">중간  </div>
                                <div class="item color">높음 </div>
						    </div>
						    <div class="tab-ct-top">
                                <div class="item">0 ~ 21  </div>
                                <div class="item">22 ~ 35  </div>
                                <div class="item">36 ~ 63  </div>
						    </div>
						    <div class="tab-content" id="accordionAnxiety">
                        
                      	    </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script id="tpl_mindcheck" type="text/x-handlebars-template">
    {{#each mindchecks}}
        <div class="item panel panel-default">
            <div class="item-header">
                <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapse{{@index}}" aria-expanded="true" aria-controls="collapse{{@index}}" class="collapsed">
                    <div class="col-md-6 text-center">
                        {{{fmtDate createdDate }}}
                    </div>
                    <div class="col-md-6 text-center">
                        <span>{{{ point }}}점</span><i class="fa fa-angle-down" aria-hidden="true"></i>
                        <i class="fa fa-angle-up" aria-hidden="true"></i>
                    </div>
                </a>
            </div>
            <div id="collapse{{@index}}" role="tabpanel" aria-labelledby="heading{{@index}}" class="panel-collapse collapse">
                <div class="panel-body">
                    {{#each data}}
                        <div class="drop-item">
                            <div class="col-md-6">
                                <p>{{{ question }}}</p>
                            </div>
                            <div class="col-md-6">
                                <p>{{{ answer }}}</p>
                            </div>
                        </div>
                    {{/each}}
                </div>
            </div>
        </div>
    {{/each}}
</script>

<div id="popup_mindcheck_list" data-cache="false" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk popup-faq">
    <div class="modal-dialog popup-faq-new">
        <div class="modal-content">
            <div class="modal-header">
                <h4> <span>마인드체크 정보</span></h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <div class="title">
                    <p>마인드체크는 상담 시 위기사례 및 자살사고에 대한 인지에 도움이 되기 위한 간단한 설문입니다. 인적사항 외 심리상태 질문은 총 6문항이며, 배점은 0~4점으로 이루어집니다. 총 점은 24점이며, 18점 이상인 경우 위기사례에 해당합니다.</p>
                </div>
                <div class="modal-list counseling-history detail notice-detail">
                    <div class="main-ct return-faq">
                        <div class="main-item">
                            <div id="accordion" class="item-content">

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- end popup -->



<script id="tpl_file_upload" type="text/x-handlebars-template">
    <li style="pointer: cursor" data-docId="{{ docId }}">
        <div class="img">
            <img src="{{ fileSrc }}" alt="file"/>
        </div>
        <span class="notefile">
            {{ fileName }}
        </span>
        <span class="remove-pdf"></span> 
    </li>
</script>

<script id="tpl_file_upload_thumb" type="text/x-handlebars-template">
    <li style="pointer: cursor" data-docId="{{ docId }}" data-pageSeq="{{ docPageSeq }}">
        <div class="img">
            <img src="{{ fileSrc }}" alt="file"/>
        </div>
        <span class="notefile">
            {{ fileName }}
        </span>
    </li>
</script>

<script type="text/javascript">
    COUNSELOR_NAME = '${counselor.user.fullname}';
    SESSION_TIME = ${session.counselingTimeType};
    COUNSEL_TYPE = '${session.counselType}';
    DISCONNECT_COUNT = '${session.disconnectCount}';
    END_TIME = '${endTime}';
    START_TIME = '${startTime}';
    SESSION_ID = '${session.id}';
    ROOM_ID = '${session.roomId}';
</script>


