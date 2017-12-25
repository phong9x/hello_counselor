<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>


<style>
    .title-schedule .from_time{
        display     : block;
        font-size   : x-small;
    }

    .title-schedule .to_time{
        display     : block;
        font-size   : x-small;
    }

    .option-item{
        display     : block;
        font-size   : x-small;
        z-index     : 9;
    }

    #tb_schedule table tbody tr td {
        line-height: normal !important;
    }
</style>

<div class="member page-content-wrapper counseling-history schedule ${empty counselor.counselOnline or counselor.counselOnline eq 0 ? '' : 'schedule-full'}" id="schedule_layout">
    <div class="page-title">
        <div class="title"><span>내 스케줄</span></div>
        <div class="title-ct"><span>상담 가능한 시간을 선택하세요..</span></div>
    </div>
    <div class="page-content">
        <div class="container">
            <div class="schedule-tab border-full bg-fff">
                <div class="tab-main">
                    <div class="tab-title"><span>설정하기</span></div>
                    <div class="tab-content" id="menu">
                        <ul>
                            <li class="${empty counselor.status or counselor.status eq 2 ? '' : 'active'}">
                                <a href="#" data-value="${empty counselor.status or counselor.status eq 2 ? 0 : 1}" data-name="status" class="btn_menu" data-toggle="tooltip" data-placement="bottom">
                                    <span>서비스 노출</span>
                                    <span class="status">${empty counselor.status or counselor.status eq 2 ? 'Off' : 'On'}</span>
                                </a>
                            </li>
                            <li class="${empty counselor.counselOnline or counselor.counselOnline eq 0 ? '' : 'active'}">
                                <a href="#" data-value="${empty counselor.counselOnline or counselor.counselOnline eq 0 ? 0 : 1}" data-name="counselOnline" class="btn_menu" data-toggle="tooltip" data-placement="bottom">
                                    <span>실시간 상담</span>
                                    <span class="status">${empty counselor.counselOnline or counselor.counselOnline eq 0 ? 'Off' : 'On'}</span>
                                </a>
                            </li>
                            <li class="${empty counselor.statusPaymentCounselingUseVoucher or counselor.statusPaymentCounselingUseVoucher eq 0 ? '' : 'active'}">
                                <a href="#" data-value="${empty counselor.statusPaymentCounselingUseVoucher or counselor.statusPaymentCounselingUseVoucher eq 0 ? 0 : 1}" data-name="statusPaymentCounselingUseVoucher" class="btn_menu" data-toggle="tooltip" data-placement="bottom">
                                    <span>상담권 허용</span>
                                    <span class="status">${empty counselor.statusPaymentCounselingUseVoucher or counselor.statusPaymentCounselingUseVoucher eq 0 ? 'Off' : 'On'}</span>
                                </a>
                            </li>
                            <li class="${empty counselor.counseling50Minutes or counselor.counseling50Minutes eq 0 ? '' : 'active'}">
                                <a href="#" data-value="${empty counselor.counseling50Minutes or counselor.counseling50Minutes eq 0 ? 0 : 1}" data-name="counseling50Minutes" class="btn_menu" data-toggle="tooltip" data-placement="bottom">
                                    <span>50분 상담</span>
                                    <span class="status">${empty counselor.counseling50Minutes or counselor.counseling50Minutes eq 0 ? 'Off' : 'On'}</span>
                                </a>
                            </li>
                            <li>
                                <a href="#" data-value="${empty counselor.counselingRestTime ? '10' : counselor.counselingRestTime}" data-name="counselingRestTime" class="btn_menu_rest" data-toggle="tooltip" data-placement="bottom">
                                    <span>상담 쉬는 시간</span>
                                    <span class="status">${empty counselor.counselingRestTime ? '10' : counselor.counselingRestTime} 분</span>
                                </a>
                            </li>
                            <li>
                                <a href="#" id="btnRepeatSchedule" data-name="repeatSchedule" data-value="0" data-toggle="tooltip" data-placement="bottom">
                                    <span>반복 설정</span>
                                </a>
                            </li>
                            <li>
                                <a href="#" id="btnResetSchedule" data-name="resetSchedule" data-value="0" data-toggle="tooltip" data-placement="bottom">
                                    <span>스케줄 초기화</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>

            <div class="schedule-content border-full bg-fff" id="tb_schedule">
                <div class="save-schedule">
                    <button disabled id="btnSubmit">일정 저장하기</button>
                </div>

                <div class="schedule-top">
                    <span class="navi pre"  id="btnPrevWeek"><i class="fa fa-angle-left" aria-hidden="true"></i></span>
                    <span class="title-date">${currentWeek}</span>
                    <span class="navi next" id="btnNextWeek"><i class="fa fa-angle-right" aria-hidden="true"></i></span>
                </div>
                <div class="schedule-main-content">
                    <div class="owl-carousel owl-theme">

                        <!-- weeks -->
                        <div class="item">
                            <div class="schedule-table">
                                <div class="schedule-day">
                                    <ul>
                                        <jsp:useBean id="now" class="java.util.Date" />
                                        <fmt:formatDate value="${now}" pattern="MM.dd(E)" var="dateNow" />
                                        <c:forEach items="${fullSchedule}" var="day" varStatus="dayIndex">
                                            <fmt:formatDate value="${day.key}" pattern="MM.dd(E)" var="dateDay" />
                                            <li class="${dateNow eq dateDay ? 'today date' : 'date'}" data-date="<fmt:formatDate value='${day.key}' pattern='yyyy-MM-dd' />">
                                                <span>
                                                    ${dateDay}
                                                </span>
                                            </li>
                                            <li style="${empty counselor.counselOnline or counselor.counselOnline eq 0 ? 'display:none;' : ''}" class="online_time">
                                                <span>실시간</span>
                                            </li>
                                        </c:forEach>

                                    </ul>
                                </div>
                                <div class="schedule-table-content" id="table_wrapper">
                                    <div class="schedule-hours">
                                        <ul>
                                            <c:forEach begin="0" end="23" step="1" var="item">
                                                <li>
                                                    <c:choose>
                                                        <c:when test="${item lt 12}">
                                                            <span>오전</span>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <span>오후</span>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <span>${item lt 10 ? '0': ''}${item}:00</span>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    <div class="table-member-mng">
                                        <table class="table table-striped table-bordered table-hover text-center">
                                            <tbody>
                                                <c:forEach begin="0" end="47" step="1" var="item">

                                                    <tr>
                                                        <c:forEach items="${fullSchedule}" var="day" varStatus="dayIndex">
                                                            <td data-type="reservation" data-date="<fmt:formatDate value='${day.key}' pattern='yyyy-MM-dd' />" data-date-format="<fmt:formatDate value='${day.key}' pattern='M월 dd일(E)' />">

                                                                <fmt:parseNumber var="_item" integerOnly="true" type="number" value="${item / 2}" />

                                                                <c:if test="${item % 2 eq 0}">
                                                                    <span class="row-td" data-start="${_item}">
                                                                        <span class="title-schedule">
                                                                            <span class="from_time"></span>
                                                                            <span class="to_time"></span>
                                                                        </span>
                                                                    </span>
                                                                    <span class="row-td" data-start="${_item + 0.1}">
                                                                        <span class="title-schedule">
                                                                            <span class="from_time"></span>
                                                                            <span class="to_time"></span>
                                                                        </span>
                                                                    </span>
                                                                    <span class="row-td" data-start="${_item + 0.2}">
                                                                        <span class="title-schedule">
                                                                            <span class="from_time"></span>
                                                                            <span class="to_time"></span>
                                                                        </span>
                                                                    </span>
                                                                </c:if>
                                                                <c:if test="${item % 2 ne 0}">
                                                                    <span class="row-td" data-start="${_item + 0.3}">
                                                                        <span class="title-schedule">
                                                                            <span class="from_time"></span>
                                                                            <span class="to_time"></span>
                                                                        </span>
                                                                    </span>
                                                                    <span class="row-td" data-start="${_item + 0.4}">
                                                                        <span class="title-schedule">
                                                                            <span class="from_time"></span>
                                                                            <span class="to_time"></span>
                                                                        </span>
                                                                    </span>
                                                                    <span class="row-td" data-start="${_item + 0.5}">
                                                                        <span class="title-schedule">
                                                                            <span class="from_time"></span>
                                                                            <span class="to_time"></span>
                                                                        </span>
                                                                    </span>
                                                                </c:if>

                                                            </td>
                                                            <td data-type="online" data-date="<fmt:formatDate value='${day.key}' pattern='yyyy-MM-dd' />" style="${empty counselor.counselOnline or counselor.counselOnline eq 0 ? 'display:none;' : ''}" class="online_time" data-date-format="<fmt:formatDate value='${day.key}' pattern='M월 dd일(E)' />">

                                                                <c:if test="${item % 2 eq 0}">
                                                                    <span class="row-td" data-start="${_item}">
                                                                        <span class="title-schedule">
                                                                            <span class="from_time"></span>
                                                                            <span class="to_time"></span>
                                                                        </span>
                                                                    </span>
                                                                    <span class="row-td" data-start="${_item + 0.1}">
                                                                        <span class="title-schedule">
                                                                            <span class="from_time"></span>
                                                                            <span class="to_time"></span>
                                                                        </span>
                                                                    </span>
                                                                    <span class="row-td" data-start="${_item + 0.2}">
                                                                        <span class="title-schedule">
                                                                            <span class="from_time"></span>
                                                                            <span class="to_time"></span>
                                                                        </span>
                                                                    </span>
                                                                </c:if>
                                                                <c:if test="${item % 2 ne 0}">
                                                                    <span class="row-td" data-start="${_item + 0.3}">
                                                                        <span class="title-schedule">
                                                                            <span class="from_time"></span>
                                                                            <span class="to_time"></span>
                                                                        </span>
                                                                    </span>
                                                                    <span class="row-td" data-start="${_item + 0.4}">
                                                                        <span class="title-schedule">
                                                                            <span class="from_time"></span>
                                                                            <span class="to_time"></span>
                                                                        </span>
                                                                    </span>
                                                                    <span class="row-td" data-start="${_item + 0.5}">
                                                                        <span class="title-schedule">
                                                                            <span class="from_time"></span>
                                                                            <span class="to_time"></span>
                                                                        </span>
                                                                    </span>
                                                                </c:if>

                                                            </td>
                                                        </c:forEach>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div>
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

<div id="popup-confim-delete" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="popup-psychlogical23 modal fade popup-facetalk">
    <div style="width: 400px;" class="modal-dialog popup-inquiry">
        <div class="modal-content">
            <div class="modal-header">
                <h4>스케쥴 삭제</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <p>설정한 스케줄을 삭제하시겠습니까?</p>
                <p class="color888">*단, 회원이 이미 예약한 시간은 초기화되지 않습니다.</p>
            </div>
            <div class="modal-footer schedule">
                <a data-dismiss="modal" class="link2">취소하기</a>
                <a class="link1 accept">스케쥴 삭제하기</a>
                <!-- Are you sure you want to delete?-->
            </div>
        </div>
    </div>
</div>

<div id="popup-choiceDate" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="popup-choiceDate modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>반복 설정</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <div class="modal-about">
                    <p>요일과 반복 종료 날짜를 선택하세요.</p>
                    <p>해당 요일 스케줄 그대로 반복 종료 날짜까지 적용됩니다.</p>
                    <p>단, 회원이 이미 예약한 시간은 변경되지 않습니다.</p>
                </div>
                <div class="modal-item">
                    <div class="sub-title">요일 선택</div>
                    <div class="list-choice">
                        <ul>
                            <li>
                                <div class="radio-checkboxstyle2">
                                    <input id="checkbox1" type="checkbox" value="2" name="day_of_week"/>
                                    <label for="checkbox1">월요일</label>
                                </div>
                            </li>
                            <li>
                                <div class="radio-checkboxstyle2">
                                    <input id="checkbox2" type="checkbox" value="3" name="day_of_week"/>
                                    <label for="checkbox2">화요일</label>
                                </div>
                            </li>
                            <li>
                                <div class="radio-checkboxstyle2">
                                    <input id="checkbox3" type="checkbox" value="4" name="day_of_week"/>
                                    <label for="checkbox3">수요일</label>
                                </div>
                            </li>
                            <li>
                                <div class="radio-checkboxstyle2">
                                    <input id="checkbox4" type="checkbox" value="5" name="day_of_week"/>
                                    <label for="checkbox4">목요일</label>
                                </div>
                            </li>
                            <li>
                                <div class="radio-checkboxstyle2">
                                    <input id="checkbox5" type="checkbox" value="6" name="day_of_week"/>
                                    <label for="checkbox5">금요일</label>
                                </div>
                            </li>
                            <li>
                                <div class="radio-checkboxstyle2">
                                    <input id="checkbox6" type="checkbox" value="7" name="day_of_week"/>
                                    <label for="checkbox6">토요일</label>
                                </div>
                            </li>
                            <li>
                                <div class="radio-checkboxstyle2">
                                    <input id="checkbox7" type="checkbox" value="1" name="day_of_week"/>
                                    <label for="checkbox7">일요일</label>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="modal-item">
                    <div class="sub-title" style="padding-bottom: 10px;">
                        <span>반복 종료 날짜 선택</span>
                        <div id="datepickerpopup" class="input-group date">
                            <input name="upperLimit" data-date-format="yyyy/mm/dd" type="text" class="form-control"/>
                            <span class="input-group-addon trigger_date">
                                <span class="glyphicon glyphicon-calendar">날짜선택</span>
                            </span>
                        </div>
                    </div>
                    <div class="modal-item-content"></div>
                </div>
                <div class="modal-footer">
                    <button href="#" class="btn-confirm accept">적용하기</button>
                    <!-- Are you sure you want to delete?-->
                </div>
            </div>
        </div>
        <!-- .popupConfirmDelete-->
    </div>
</div>

<div id="popup-confim-schedule" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="popup-psychlogical23 modal fade popup-facetalk">
    <div style="width: 400px;" class="modal-dialog popup-inquiry">
        <div class="modal-content">
            <div class="modal-header">
                <h4>스케줄 초기화</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <p>설정한 스케줄 모두 초기화하겠습니까? </p>
                <p class="color888">*단, 회원이 이미 예약한 시간은 초기화되지 않습니다.</p>
            </div>
            <div class="modal-footer schedule">
                <a data-dismiss="modal" class="link2">취소하기</a>
                <a class="link1 accept">스케줄 초기화하기</a>
                <!-- Are you sure you want to delete?-->
            </div>
        </div>
    </div>
</div>

<div id="popup-confirm-exit" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="popup-psychlogical23 modal fade popup-facetalk">
    <div style="width: 400px;" class="modal-dialog popup-inquiry">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <p>변경사항이 저장되지 않았습니다.저장하지 않고 이 페이지를 벗어나시겠습니까?</p>
            </div>
            <div class="modal-footer schedule">
                <a data-dismiss="modal" class="link2">취소</a>
                <a class="link1 accept">확인</a>
            </div>
        </div>
    </div>
</div>

<div id="tooltip" style="display: none">
    <div id="tip_status_1">
        <p>내 상담사 정보를 app에 노출할지 여부를 설정합니다.</p>
        <p>ON으로 설정되어 있는 경우 app 이용자(내담자)가 내 상담사 정보 및 예약 가능 스케줄을 볼 수 있습니다.</p>
        <p>클릭 시 OFF로 변경되며 저장하면 내 상담사 정보 및 스케줄이 app에 노출되지 않으므로 상담 예약이 불가합니다.</p>
    </div>
    <div id="tip_status_0">
        <p>내 상담사 정보를 app에 노출할지 여부를 설정합니다.</p>
        <p>OFF으로 설정되어 있는 경우 app 이용자(내담자)가 내 상담사 정보 및 예약 가능 스케줄을 볼 수 없습니다.</p>
        <p>클릭 시 ON로 변경되며 저장하면 내 상담사 정보 및 스케줄이 app에 노출되며 상담 예약이 가능합니다.</p>
    </div>
    <div id="tip_counselOnline_1">
        <p>실시간 상담 스케줄을 설정할 수 있습니다.</p>
        <p>실시간 상담 스케줄은 예약 가능한 시간으로 선택한 시간 내에서만 설정할 수 있으며 예약 불가한 시간에는 실시간 상담도 불가합니다. 실시간 상담 스케줄 설정 후 저장하면 설정한 시간동안 app에서 “바로 상담 가능”으로 노출됩니다.</p>
        <p>클릭 시 OFF로 변경되며 저장하면 실시간 상담은 진행할 수 없습니다.</p>
    </div>
    <div id="tip_counselOnline_0">
        <p>실시간 상담을 진행할 수 없는 상태입니다.</p>
        <p>클릭 시 ON으로 변경되며 스케줄 시간 영역에 실시간 상담 스케줄 설정이 가능하도록 추가됩니다.</p>
    </div>
    <div id="tip_statusPaymentCounselingUseVoucher_1">
        <p>결제 수단이 헬로코인, 상담권 여부와 상관없이 상담 진행 가능한 상태입니다.</p>
        <p>클릭 시 OFF로 변경되며 저장하면 상담권으로는 app 이용자(내담자)가 상담 시작/예약을 할 수 없게 됩니다. 하지만 이미 상담권으로 예약된 상담은 취소되지 않습니다.</p>
    </div>
    <div id="tip_statusPaymentCounselingUseVoucher_0">
        <p>결제 수단이 헬로코인인 경우에만 상담 진행이 가능한 상태입니다.</p>
        <p>클릭 시 ON으로 변경되며 저장하면 헬로코인, 상담권 여부와 상관없이 상담 진행이 가능해집니다.</p>
    </div>
    <div id="tip_counseling50Minutes_1">
        <p>기본 상담시간 30분 외 추가로 선택합니다.</p>
        <p>클릭 시 기본 상담시간 30분만 진행할 수 있으며 이미 예약된 50분 상담은 변경되지 않습니다.</p>
    </div>
    <div id="tip_counseling50Minutes_0">
        <p>기본 상담시간 30분 외 추가로 선택합니다.</p>
        <p>클릭 시 기본 상담시간 30분 외 50분 상담도 가능하며 app 이용자(내담자)가 실시간 상담 혹은 상담 예약 시 상담시간은 선택할 수 있습니다.</p>
    </div>
    <div id="tip_counselingRestTime">
        <p>상담 종료 후 상담 사이 쉬는 시간을 설정할 수 있습니다. 클릭 시마다 10분 – 20분 – 30분 – 0분 으로 변경됩니다.</p>
        <p>연속으로 상담이 2건 이상 예약되는 경우 상담 종료 후 후처리 등 쉬는 시간이 필요할 수 있으니 원하는 시간으로 설정합니다.</p>
    </div>
    <div id="tip_repeatSchedule_0">
        <p>상담 가능시간을 설정 후 반복설정 버튼을 클릭하시면 설정한 시간을 추후 다시 설정할 필요없이 원하는 기간까지 반복 설정이 가능합니다.</p>
        <p>반복 설정은 특정 요일만 선택할 수도 있습니다.</p>
    </div>
    <div id="tip_resetSchedule_0">
        <p>클릭 시 설정한 모든 스케줄이 초기화됩니다. 단, 이미 예약된 상담은 취소되지 않습니다.</p>
    </div>
</div>

<div id="my_tooltip" class="well add-title-schedule" style="display:none">
    <div class="add-title">
        <a href="#" class="my_tooltip_close close-add-title" style="">Close</a>
        <h4>예약 스케줄 설정</h4>
        <div class="add-content">
            <p>시간</p>
            <div class="time">
                <span class="star-time">

                </span>
                <select name="end-hour" title="성명" class="form-control selectpickermember">
                    <option value="00">0</option>
                    <option value="01">1</option>
                    <option value="02">2</option>
                    <option value="03">3</option>
                    <option value="04">4</option>
                    <option value="05">5</option>
                    <option value="06">6</option>
                    <option value="07">7</option>
                    <option value="08">8</option>
                    <option value="09">9</option>
                    <option value="10">10</option>
                    <option value="11">11</option>
                    <option value="12">12</option>
                    <option value="13">13</option>
                    <option value="14">14</option>
                    <option value="15">15</option>
                    <option value="16">16</option>
                    <option value="17">17</option>
                    <option value="18">18</option>
                    <option value="19">19</option>
                    <option value="20">20</option>
                    <option value="21">21</option>
                    <option value="22">22</option>
                    <option value="23">23</option>
                </select>
                <select name="end-minute" title="성명" class="form-control selectpickermember">
                    <option value="00">00</option>
                    <option value="10">10</option>
                    <option value="20">20</option>
                    <option value="30">30</option>
                    <option value="40">40</option>
                    <option value="50">50</option>
                </select>
            </div>
        </div>
        <div class="footer text-center">
            <button class="my_tooltip_close fall_open">확인</button>
        </div>
    </div>
</div>

<script>

    $(document).ready(function() {
        // if today is Sunday, redirect to previous week
        var week                = extractQueryParam('week');
        var curDay              = moment().day();
        var counseling50Minutes = ${empty counselor.counseling50Minutes ? 0 : counselor.counseling50Minutes};

        if (!week && 0 == curDay) {
            window.location.href = '/auth/schedule/list?week=-1';
        }

        var fullScheduleJson    = JSON.parse('${fullScheduleJson}');

        console.log(fullScheduleJson);

        var reservationTimes    = [];
        var onlineTimes         = [];
        var reservedTimes       = [];
        var restTimes           = [10, 20, 30];
        var lastRestIdx         = restTimes.length - 1;
        var timesDisplay        = [
                                    '00:00','00:10','00:20','00:30','00:40','00:50',
                                    '01:00','01:10','01:20','01:30','01:40','01:50',
                                    '02:00','02:10','02:20','02:30','02:40','02:50',
                                    '03:00','03:10','03:20','03:30','03:40','03:50',
                                    '04:00','04:10','04:20','04:30','04:40','04:50',
                                    '05:00','05:10','05:20','05:30','05:40','05:50',
                                    '06:00','06:10','06:20','06:30','06:40','06:50',
                                    '07:00','07:10','07:20','07:30','07:40','07:50',
                                    '08:00','08:10','08:20','08:30','08:40','08:50',
                                    '09:00','09:10','09:20','09:30','09:40','09:50',
                                    '10:00','10:10','10:20','10:30','10:40','10:50',
                                    '11:00','11:10','11:20','11:30','11:40','11:50',
                                    '12:00','12:10','12:20','12:30','12:40','12:50',
                                    '13:00','13:10','13:20','13:30','13:40','13:50',
                                    '14:00','14:10','14:20','14:30','14:40','14:50',
                                    '15:00','15:10','15:20','15:30','15:40','15:50',
                                    '16:00','16:10','16:20','16:30','16:40','16:50',
                                    '17:00','17:10','17:20','17:30','17:40','17:50',
                                    '18:00','18:10','18:20','18:30','18:40','18:50',
                                    '19:00','19:10','19:20','19:30','19:40','19:50',
                                    '20:00','20:10','20:20','20:30','20:40','20:50',
                                    '21:00','21:10','21:20','21:30','21:40','21:50',
                                    '22:00','22:10','22:20','22:30','22:40','22:50',
                                    '23:00','23:10','23:20','23:30','23:40','23:50', '24:00'
                                ];
        var timesSelect         = [
                                    0,0.1,0.2,0.3,0.4,0.5,
                                    1,1.1,1.2,1.3,1.4,1.5,
                                    2,2.1,2.2,2.3,2.4,2.5,
                                    3,3.1,3.2,3.3,3.4,3.5,
                                    4,4.1,4.2,4.3,4.4,4.5,
                                    5,5.1,5.2,5.3,5.4,5.5,
                                    6,6.1,6.2,6.3,6.4,6.5,
                                    7,7.1,7.2,7.3,7.4,7.5,
                                    8,8.1,8.2,8.3,8.4,8.5,
                                    9,9.1,9.2,9.3,9.4,9.5,
                                    10,10.1,10.2,10.3,10.4,10.5,
                                    11,11.1,11.2,11.3,11.4,11.5,
                                    12,12.1,12.2,12.3,12.4,12.5,
                                    13,13.1,13.2,13.3,13.4,13.5,
                                    14,14.1,14.2,14.3,14.4,14.5,
                                    15,15.1,15.2,15.3,15.4,15.5,
                                    16,16.1,16.2,16.3,16.4,16.5,
                                    17,17.1,17.2,17.3,17.4,17.5,
                                    18,18.1,18.2,18.3,18.4,18.5,
                                    19,19.1,19.2,19.3,19.4,19.5,
                                    20,20.1,20.2,20.3,20.4,20.5,
                                    21,21.1,21.2,21.3,21.4,21.5,
                                    22,22.1,22.2,22.3,22.4,22.5,
                                    23,23.1,23.2,23.3,23.4,23.5,24
                                ];
        var isModified          = false;
        var currentWeek         = ${week};
        var tableWrapper        = document.getElementById('table_wrapper');


        /**********************************************************************************
         *
         * Tooltip menu
         *
         **********************************************************************************/
         // init tooltip
         $('[data-toggle=tooltip]').tooltip({html: true});

         $('[data-toggle=tooltip]').on('mouseenter', function(evt) {
            var name    = $(this).attr('data-name');
            var val     = $(this).attr('data-value');
            var html    = '';
            if (name == 'counselingRestTime') {
                html    = $('#tooltip').find('#tip_' + name).html();
            } else {
                html    = $('#tooltip').find('#tip_' + name + '_' + val).html();
            }

            $(this).attr('title', html)
                      .tooltip('fixTitle')
                      .tooltip('show');
         });

         /**********************************************************************************
          *
          * Tooltip schedule
          *
          **********************************************************************************/
         $('#tb_schedule').on('click', '.resizable', function(evt) {

             var $this      = $(this);
             var $tooltip   = $('#my_tooltip');

             var startIndex = $this.attr('data-start');
             var endIndex   = $this.attr('data-end');
             var startTime  = $this.closest('td').attr('data-date-format');

             $tooltip.find('.star-time').text(startTime + ', ' + getDisplayTimeFromIndex(startIndex) + ' ~ ');
             $tooltip.find('button.my_tooltip_close').attr('data-resizable-id', $this.attr('id'));
             
             var endTime = getDisplayTimeFromIndex(endIndex);
             console.log("endTime: "+endTime);
             if(endTime != ''){
            	 var split = endTime.split(':');
            	 var $selectHour = $tooltip.find('[name=end-hour]')
            	 var $selectMinute = $tooltip.find('[name=end-minute]')
            	 
            	 $selectHour.val(split[0]);
            	 $selectMinute.val(split[1]);
            	 
            	 $selectHour.selectpicker('val',split[0]);
            	 $selectMinute.selectpicker('val',split[1]);
            	 
             }
             
             
             
             $tooltip.popup({
                  type: 'tooltip',
                  vertical: 'top',
                  tooltipanchor: evt.target
              }).popup('show');
         });

         $('#my_tooltip').on('click', '.my_tooltip_close', function(evt) {

             var $tooltip   = $('#my_tooltip');
             var $resizable = $('#' + $(this).attr('data-resizable-id'));

             var endHour    = $tooltip.find('[name=end-hour]').val();
             var endMin     = $tooltip.find('[name=end-minute]').val();
             var startIndex = $resizable.attr('data-start');
             var endIndex   = timesDisplay.indexOf(endHour + ':' + endMin);

             $resizable.siblings('.title-schedule').find('.to_time').text(' ~ ' + getDisplayTimeFromIndex(endIndex));
             $resizable
                .attr('data-end', endIndex)
                .css('height'   , (Number.parseInt(endIndex) - Number.parseInt(startIndex))*10 + 'px');

             changeSchedule();
         });

        /**********************************************************************************
         *
         * Confirm exit
         *
         **********************************************************************************/
         function confirmExit(callback) {
            if (isModified) {
                $('#popup-confirm-exit').modal('show');
                $('#popup-confirm-exit')
                    .off('click', '.accept')
                    .on('click', '.accept', callback);
            } else {
                callback();
            }
         }

         function unbindExit() {
            window.onbeforeunload = function() { }
         }

         window.onbeforeunload = function(e) {
            if (isModified) {
                return '변경사항이 저장되지 않았습니다.저장하지 않고 이 페이지를 벗어나시겠습니까?';
            }
         }

        /**********************************************************************************
         *
         * Navigation week
         *
         **********************************************************************************/
         if(currentWeek < -12) window.location.href = '/auth/schedule/list?week=-12';
         if(currentWeek > 12) window.location.href = '/auth/schedule/list?week=12';

         $('.navi').on('click', function(evt) {
            evt.preventDefault();
            var id = $(this).attr('id');

            unbindExit();

            confirmExit(function() {
                if ('btnPrevWeek' == id) {
                    window.location.href = '/auth/schedule/list?week=' + (Number.parseInt(currentWeek) - 1);
                } else {
                    window.location.href = '/auth/schedule/list?week=' + (Number.parseInt(currentWeek) + 1);
                }
            });
         });


        /**********************************************************************************
         *
         * Populate previous data
         *
         **********************************************************************************/
         function populateData() {
             _.each(fullScheduleJson, function(dayVal, dayKey) {
                if (dayVal != null) {
                    var reservationDate = moment(dayVal.reservationDate).format('YYYY-MM-DD');
                    if (dayVal.onlineTime != null) {
                        var onlineTimeJson = {};
                        onlineTimeJson.date = reservationDate;
                        onlineTimeJson.data = dayVal.onlineTime.split(';').map(function(item) { return Number.parseFloat(item); });
                        onlineTimes.push(onlineTimeJson);
                    }
                    if (dayVal.reservationTime != null) {
                        var reservationTimeJson = {};
                        reservationTimeJson.date = reservationDate;
                        reservationTimeJson.data = dayVal.reservationTime.split(';').map(function(item) {return Number.parseFloat(item); });
                        reservationTimes.push(reservationTimeJson);
                    }
                    if (dayVal.reservedTime != null) {
                        var reservedTimeJson = {};
                        reservedTimeJson.date = reservationDate;
                        reservedTimeJson.data = dayVal.reservedTime.map(function(item) {return item; });
                        reservedTimes.push(reservedTimeJson);
                    }
                }
             });

             if (onlineTimes.length) {
                _.each(onlineTimes, function(memo) {
                    if (memo.data.length) {
                        drawMemo(memo, 'online');
                    }
                })

             }

             if (reservationTimes.length) {
                _.each(reservationTimes, function(memo) {
                    if (memo.data.length) {
                        drawMemo(memo, 'reservation');
                    }
                });
             }

             if (reservedTimes.length) {
                _.each(reservedTimes, function(memo) {
                    if (memo.data.length) {
                        drawReserved(memo, 'reservation');
                    }
                });
             }
         }

        function drawMemo(memo, type) {

            var date    = memo.date;
            var data    = memo.data;

            if (data.length) {
                var memos   = {};

                for (var i = 0, n = data.length; i < n; i++) {

                    var prevNumber 		= i === 0 ? data[i] : data[i-1];
                    var currentNumber 	= data[i];
                    var nextNumber 		= i < n - 1 ? data[i+1] : data[n-1];

                    var lowerDelta 		= Number((currentNumber - prevNumber).toFixed(1));

                    if (!lowerDelta || lowerDelta > 0.5) {
                        memos[currentNumber] = 1;
                    }

                    if (lowerDelta == 0.1 || lowerDelta == 0.5) {
                        var lastShitKey 	= _.keys(memos).reverse()[0];
                        var lastShitVal 	= Number.parseInt(memos[lastShitKey]);

                        memos[lastShitKey] 	= lastShitVal + 1;
                    }
                }

                if (_.keys(memos).length) {

                    _.each(memos, function(val, key) {

                        key             = Number.parseFloat(key);
                        val             = Number.parseFloat(val);
                        var startIndex  = timesSelect.indexOf(key);
                        var endIndex    = timesSelect.indexOf(key) + val;
                        var scheduleId  = Math.floor(Math.random()*100000);

                        var $item       = $('.schedule-main-content')
                                            .find('td[data-type=' + type + '][data-date=' + date + ']')
                                            .find('span[data-start="' + key + '"]');

                        var fromTime    = getDisplayTimeFromIndex(startIndex);
                        var endTime     = getDisplayTimeFromIndex(endIndex);

                        $item.find('.from_time').text(fromTime);
                        $item.find('.to_time').text(' ~ ' + endTime);

                        $item.append('<div class="resizable memo" id="' + scheduleId + '" data-start="' + startIndex + '" data-end="' + endIndex + '" data-type="' + type +'" data-date="' + date + '" style="height:' + (endIndex - startIndex)*10 +'px"></div>');
                        $item.closest('td').addClass('nonclickable');

                    });
                }
            }
        }

        function drawReserved(memo, type) {
            var date    = memo.date;
            var data    = memo.data;

            if (data.length) {
                for (var i = 0, n = data.length; i < n; i++) {

                    var curMemo     = data[i];
                    var keys        = Object.keys(curMemo);
                    var vals        = Object.values(curMemo);

                    var numKeyStart = Number.parseFloat(keys[0]);
                    var numKeyEnd   = Number.parseFloat(keys[1]);

                    if (Number.isInteger(numKeyStart)) {
                        numKeyStart = Number.parseInt(numKeyStart);
                    }
                    if (Number.isInteger(numKeyEnd)) {
                        numKeyEnd   = Number.parseInt(numKeyEnd);
                    }

                    var startIndex  = timesSelect.indexOf(numKeyStart);
                    var endIndex    = timesSelect.indexOf(numKeyEnd);

                    var scheduleId  = Math.floor(Math.random()*100000);

                    var $item = $('.schedule-main-content')
                                .find('td[data-type=' + type + '][data-date=' + date + ']')
                                .find('span[data-start="' + numKeyStart + '"]');

                    var fromTime    = vals[0];
                    var endTime     = vals[1];
                    var style       = 'top:0px;height:' + (endIndex - startIndex) * 10 + 'px';

                    $item.append('<div class="option-item" id="' + scheduleId + '" data-start="' + startIndex + '" data-end="' + endIndex + '" data-type="' + type +'" style="' + style + '" data-date="' + date + '">' + fromTime + ' ~ ' + endTime  + '</div>');

                }
            }
        }

        populateData();

        /**********************************************************************************
        *
        * Counselor changes schedule
        *
        **********************************************************************************/
        function changeSchedule() {
            isModified = true;
            $('#btnSubmit').removeAttr('disabled');
        }

        function changeLayout() {
            var $layout = $('#schedule_layout');
            if ($layout.hasClass('schedule-full')) {
                $layout.removeClass('schedule-full');
                $('.online_time').css('display', 'none');
            } else {
                $layout.addClass('schedule-full');
                $('.online_time').css('display', '');
            }
        }

        /**********************************************************************************
        *
        * Changes settings
        *
        **********************************************************************************/
        $('#menu').on('click', '.btn_menu', function(evt) {
            var $this           = $(this);
            var currentStatus   = Number.parseInt($(this).attr('data-value'));
            var newStatus       = currentStatus ? 0 : 1;
            var action          = $this.attr('data-name');
            $this.attr('data-value', newStatus);
            $this.find('.status').text(newStatus ? 'On' : 'Off');
            if (newStatus) {
                $this.closest('li').addClass('active');
            } else {
                $this.closest('li').removeClass('active');
            }

            changeSchedule();

            if (action === 'counselOnline') {
                changeLayout();
            }
        });

        $('#menu').on('click', '.btn_menu_rest', function(evt) {
            var $this       = $(this);
            var currentVal  = Number.parseInt($this.attr('data-value'));
            var currentIdx  = restTimes.indexOf(currentVal);
            var newVal      = restTimes[0];

            if (currentIdx > -1 && currentIdx !== lastRestIdx) {
                newVal = restTimes[currentIdx + 1];
            }

            $this.attr('data-value', newVal);
            $this.find('.status').text(newVal + ' 분');

            changeSchedule();
        });


        /**********************************************************************************
        *
        * Create schedule
        *
        **********************************************************************************/
        $('#tb_schedule').on('click', 'tbody td:not(.nonclickable)', function(evt) {
            var $this       = $(this);
            var $span       = $(this).find('span:first');
            var scheduleId  = Math.floor(Math.random()*100000);
            var type        = $this.attr('data-type');
            var date        = $this.attr('data-date');
			console.log("startIndex: "+$this.find('span:first').attr('data-start'));
            var startIndex   = timesSelect.indexOf(Number.parseFloat($span.attr('data-start')));
            var endIndex     = startIndex + 3;

            var fromTime    = getDisplayTimeFromIndex(startIndex);
            var endTime     = getDisplayTimeFromIndex(endIndex);
            

            $span.find('.from_time').text(fromTime);
            $span.find('.to_time').text(' ~ ' + endTime);

            $span.append('<div class="resizable memo" id="' + scheduleId + '" data-start="' + startIndex + '" data-end="' + endIndex + '" data-type="' + type +'" data-date="' + date + '" style="height:30px"></div>');
            $this.addClass('nonclickable');
			
            $('#my_tooltip').popup('hide');
            $span.find('.resizable').click();
            
            changeSchedule();
        });

        function getDisplayTimeFromIndex(index) {
            return timesDisplay[index];
        }

        /**********************************************************************************
        *
        * Delete schedule
        *
        **********************************************************************************/
        $('#tb_schedule').on('dblclick', '.resizable', function(evt) {
            var $this       = $(this);
            var scheduleId  = Number.parseInt($(this).attr('id'));
            $('#my_tooltip').popup('hide');
            $('#popup-confim-delete').modal('show');
            $('#popup-confim-delete')
                .off('click', '.accept')
                .on('click', '.accept', function(evt) {
                    var $span = $this.closest('span');
                    $this.closest('td').removeClass('nonclickable');
                    $span
                        .find('.title-schedule')
                        .find('.from_time').text('');
                    $span
                        .find('.title-schedule')
                        .find('.to_time').text('')
                    $('#' + scheduleId).remove();
                    $('#popup-confim-delete').modal('hide');
                    changeSchedule();
                });
        });

        /**********************************************************************************
        *
        * Repeat schedule
        *
        **********************************************************************************/
        $('#datepickerpopup').find('[name=upperLimit]').datepicker({
            rtl         : Metronic.isRTL(),
            orientation : "left",
            autoclose   : true,
            language    : 'kr',
            dateformat  : 'yyyy/mm/dd'
        });

        $('#datepickerpopup').on('click', '.trigger_date', function(evt) {
            $('#datepickerpopup').find('[name=upperLimit]').datepicker("show");
        });

        $('#btnRepeatSchedule').on('click', function(evt) {
            $('#popup-choiceDate').modal('show');
        });

        $('#popup-choiceDate').on('click', '.accept', function(evt) {
            var upperLimit = $('#popup-choiceDate').find('[name=upperLimit]').val();
            var daysOfWeek = [];
            var currentWeek= extractQueryParam('week') || 0;
            $('#popup-choiceDate')
                .find('[name=day_of_week]:checked')
                .each(function(index, item) {
                    daysOfWeek.push(item.value);
                });

            if (upperLimit && daysOfWeek.length) {
                $
                    .ajax({
                        url     : '/auth/schedule/repeat',
                        method  : 'post',
                        data    : {
                            upperLimit  : upperLimit,
                            daysOfWeek  : daysOfWeek.toString(),
                            currentWeek : currentWeek
                        },
                        beforeSend: toggleLoading
                    })
                    .then(function(res) {
                        toggleLoading();
                        $('#popup-choiceDate').modal('hide');
                    }, function(err) {
                        toggleLoading();
                        $('#popup-choiceDate').modal('hide');
                    });
            }
        });

        /**********************************************************************************
        *
        * Reset schedule
        *
        **********************************************************************************/
        $('#popup-confim-schedule').on('click', '.accept', function(evt) {
            evt.preventDefault();
            $
                .ajax({
                    url         : '/auth/schedule/reset',
                    method      : 'delete',
                    beforeSend  : toggleLoading
                })
                .then(function(res) {
                    toggleLoading();
                    window.location.reload();
                }, function(err) {
                    toggleLoading();
                    console.error(err);
                });

            // remove all memos
            $('.schedule-main-content').find('.memo').remove();
            $('.schedule-main-content').find('.option-item').remove();
            $('.schedule-main-content').find('.from_time').text('');
            $('.schedule-main-content').find('.to_time').text('');
            $('.schedule-main-content').find('td.nonclickable').removeClass('nonclickable');

            // re-populate data
            populateData();

            // disable submit button
            $('#btnSubmit').attr('disabled', 'disabled');

            // close popup
            $('#popup-confim-schedule').modal('hide');

            isModified = false;
        });

        $('#btnResetSchedule').on('click', function(evt) {
            $('#popup-confim-schedule').modal('show');
        });

        /**********************************************************************************
        *
        * Submit form
        *
        **********************************************************************************/
        $('#btnSubmit').on('click', function(evt) {

            // calculate all data
            var data = {};
            //// gather menu
            data.status                             = $('#menu').find('a[data-name=status]').attr('data-value');
            data.counselOnline                      = $('#menu').find('a[data-name=counselOnline]').attr('data-value');
            data.statusPaymentCounselingUseVoucher  = $('#menu').find('a[data-name=statusPaymentCounselingUseVoucher]').attr('data-value');
            data.counselingRestTime                 = $('#menu').find('a[data-name=counselingRestTime]').attr('data-value');
            data.counseling50Minutes                = $('#menu').find('a[data-name=counseling50Minutes]').attr('data-value');

            //// gather memo
            var reservations    = {};
            var onlines         = {};
            $('.schedule-day').find('li.date').each(function(index, item) {
                var date = item.getAttribute('data-date');
                reservations[date]  = [];
                onlines[date]       = [];
            });

            $('.schedule-main-content').find('.memo').each(function(index, item) {
                var $item           = $(item);
                var date            = $item.attr('data-date');
                var type            = $item.attr('data-type');
                var startIndex      = Number.parseInt($item.attr('data-start'));
                var endIndex        = Number.parseInt($item.attr('data-end'));
                var data            = timesSelect.slice(startIndex, endIndex);

                if (type == 'reservation') {
                    if (_.has(reservations, date)) {
                        reservations[date] = _.sortBy(_.union(reservations[date], data));
                    } else {
                        reservations[date] = _.sortBy(data);
                    }
                } else if (type == 'online') {
                    if (_.has(onlines, date)) {
                        onlines[date] = _.sortBy(_.union(onlines[date], data));
                    } else {
                        onlines[date] = _.sortBy(data);
                    }
                }

            });

            var reservationsArray   = [];
            var onlinesArray        = [];

            _.each(reservations, function(val, key) {
                var item    = {};
                item[key]   = val.join(';');
                reservationsArray.push(item);
            });

            _.each(onlines, function(val, key) {
                var item    = {};
                item[key]   = val.join(';');
                onlinesArray.push(item);
            });

            data.reservationsArray   = reservationsArray;
            data.onlinesArray        = onlinesArray;

            $
                .ajax({
                    url         : '/auth/schedule',
                    method      : 'put',
                    contentType : 'application/json; charset=UTF-8',
                    data        : JSON.stringify(data),
                    beforeSend  : toggleLoading
                })
                .then(function(res) {
                    window.onbeforeunload = function() { }
                    window.location.reload();
                }, function(err) {
                    toggleLoading();
                    console.error(err);
                });

        });

    });

</script>