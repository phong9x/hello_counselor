<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<div id="popup-psychlogical2" class="popup-psychlogical23 popup-facetalk" style="height: 100% !important;">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
            </div>
            <div class="modal-body">
                <div class="modal-ct">
                    <div class="modal-title"><span>받는 회원 선택</span>
                </div>
                <div class="modal-main">
                    <ul>
                        <li>
                            <div class="title">
                                <div data-error-container="#form_2_services_error" class="checkbox-list input-checkboxstyle2">
                                    <label>
                                        <input type="radio" checked="checked" class="radio1"/>
                                    </label>
                                </div>
                            </div>
                            <span class="ct" id="fullname">${user.fullname}</span><span>${user.email}</span>
                        </li>
                    </ul>
                </div>
            </div>

            <div class="modal-ct">
                <div class="modal-title"><span>심리검사 선택</span></div>
                    <div class="modal-main">
                        <ul>
                            <c:forEach items="${psy}" var="item">
                                <li class="${item.testName eq 'MBTI' and counselor.mbtiCertificate eq null ? 'none-active': 'active' }">
                                    <div class="title">
                                        <div class="radio-checkboxstyle2">
                                            <input type="checkbox" value="${item.id}" name="psyName" data-fee="${item.fee}"/>
                                        </div>
                                    </div>
                                    <span>${item.testName}</span>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <div class="footer-ct">
                    <span class="title">심리검사
                        <span id="total-psy"        data-value="0">0</span>건,
                        <span id="total-money"      data-value="0">0</span>원 (VAT 포함)  또는 상담권
                        <span id="total-voucher"    data-value="0">0</span>매</span>
                </div>
                <a class="link1 popup_accept">회원에게 심리검사 요청</a>
            </div>
        </div>
    </div>
</div>

<style>
	.modal-backdrop{display: none !important;}
	.modal-dialog{width: 100% !important;margin-top: 0 !important;border: none !important;box-shadow: none !important;}
	.modal-content{box-shadow: none !important;border: none !important;border-radius: 0 !important;}
	.modal-list li .title{float: left;width: 100px;}
</style>

<script>
$('#popup-psychlogical2').on('click', '.popup_accept', function(evt) {
    var roomId  = extractQueryParam('roomId');

    if (roomId) {
        var $parent = $('#popup-psychlogical2');
        var psy     = [];
        var money   = 0;
        $parent
            .find('[name=psyName]:checked')
            .each(function(index, item) {
                psy.push(item.value);
                money += Number.parseInt(item.attributes['data-fee'].value);
            });

        if (psy.length) {
            $
                .ajax({
                    url     : '/auth/talk/psy',
                    method  : 'post',
                    data    : {
                        roomId  : roomId,
                        money   : money,
                        psy     : psy.toString()
                    },
                    beforeSend: toggleLoading
                })
                .then(function(res) {
                    toggleLoading();
                    window.close();
                }, function(err) {
                    toggleLoading();
                    window.close();
                });
        }
    }
});
</script>