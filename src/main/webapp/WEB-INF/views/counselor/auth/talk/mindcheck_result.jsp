<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<div id="popup_mindcheck_list" class="popup-facetalk popup-faq" style="height: 100% !important;">
    <div class="modal-dialog popup-faq-new">
        <div class="modal-content">
            <div class="modal-header">
                <h4> <span>마인드체크 정보</span></h4>
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
<style>
	.modal-backdrop{display: none !important;}
	.modal-dialog{width: 100% !important;margin-top: 0 !important;border: none !important;box-shadow: none !important;}
	.modal-content{box-shadow: none !important;border: none !important;border-radius: 0 !important;}
	.popup-faq .popup-faq-new .modal-body .counseling-history.detail.notice-detail{max-height: 600px !important;}
</style>
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
<script>
var roomId  = extractQueryParam('roomId');
var $popupMindCheck = $('#popup_mindcheck_list');

if (roomId) {

        $
            .ajax({
                url         : '/auth/talk/mindcheck',
                method      : 'get',
                data        : {
                    roomId  : roomId
                },
                beforeSend  : toggleLoading
            })
            .then(function(res) {
                toggleLoading();
                // register Handlebars format date
                Handlebars.registerHelper('fmtDate', function(timestamp) {
                    var date = moment(timestamp);
                    return date.year() + '년 ' + (date.month() + 1) + '월 ' + date.date() + '일';
                });

                // render template
                var src     = $('#tpl_mindcheck').html();
                var tpl     = Handlebars.compile(src);
                var html    = tpl({mindchecks: res});

                $popupMindCheck.find('#accordion').html(html);

                $popupMindCheck
                    .attr('data-cache', true)
                    .modal('show');
            }, function(err) {
                toggleLoading();
                console.error(err);
            });

}
</script>