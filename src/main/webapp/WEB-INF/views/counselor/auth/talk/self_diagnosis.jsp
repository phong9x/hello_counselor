<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<div id="popup-self-diagnosis-test-list" class="modal fade popup-facetalk" style="height: 100% !important;">
    <div style="width:600px;" class="modal-dialog popup-faq-tab">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="text-center"><span>자가진단 정보</span></h4>
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
<style>
	.modal-backdrop{display: none !important;}
	.modal-dialog{width: 100% !important;margin-top: 0 !important;border: none !important;box-shadow: none !important;}
	.modal-content{box-shadow: none !important;border: none !important;border-radius: 0 !important;}
</style>
<script id="tpl_selfDiagnosis" type="text/x-handlebars-template">
    {{#each test}}
        <div class="item">
            <div class="item-left">{{{ createDate }}}</div>
            <div class="item-right">{{{ point }}}</div>
        </div>
    {{else}}
        <div class="item-left">결과가 없습니다.</div>
    {{/each}}
</script>
<script>

	var roomId  = extractQueryParam('roomId');

        var $popupSelfDiagnosisTestList = $('#popup-self-diagnosis-test-list');
        
        if (roomId) {
                $
                    .ajax({
                        url     : '/auth/talk/selfDiagnosis',
                        method  : 'get',
                        data    : {
                            roomId  : roomId
                        },
                        beforeSend: toggleLoading
                    })
                    .then(function(res) {
                        toggleLoading();

                        // render template
                        var src     = $('#tpl_selfDiagnosis').html();
                        var tpl     = Handlebars.compile(src);
                        var html    = tpl({mindchecks: res});

                        $popupSelfDiagnosisTestList
                            .find('#accordionStress')
                            .html(tpl({test: res.stressDiagnosis}));
                        $popupSelfDiagnosisTestList
                            .find('#accordionDepressed')
                            .html(tpl({test: res.dipressedDiagnosis}));
                        $popupSelfDiagnosisTestList
                            .find('#accordionAnxiety')
                            .html(tpl({test: res.anxietyDiagnosis}));

                        $popupSelfDiagnosisTestList
                            .attr('data-cache', true)
                            .modal('show');
                    }, function(err) {
                        toggleLoading();
                        console.error(err);
                    });
        }
        
</script>