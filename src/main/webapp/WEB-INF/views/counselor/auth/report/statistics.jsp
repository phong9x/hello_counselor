<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" import="java.util.*" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<%
    Calendar instance   = Calendar.getInstance();
    int currentYear     = instance.get(Calendar.YEAR);
    int currentMonth    = instance.get(Calendar.MONTH) + 1;
    List<Integer> years = new ArrayList<>();
    for (int i = currentYear - 10, n = currentYear + 10; i < n; i++) {
        years.add(i);
    }
    request.setAttribute("currentYear"  , currentYear);
    request.setAttribute("currentMonth" , currentMonth);
    request.setAttribute("years"        , years);
%>

<div class="member page-content-wrapper counseling-history massage report-statistic">
    <div class="page-title">
        <div class="title">
            <span>리포트</span>
        </div>
        <div class="title-ct">
            <span>통계 리포트</span>
        </div>
    </div>
    <div class="page-content">
        <div class="container">
            <div class="main-top border-full bg-fff">
                <div class="tabbable-custom">
                    <ul class="nav nav-tabs" id="main_tab">
                        <li>
                            <a data-target="#tab-11" data-type="period" data-chart="#tab-11-canvas" data-table="#tab-11-table">
                                <span>기간별</span>
                                <i aria-hidden="true" class="fa fa-caret-down"></i>
                            </a>
                        </li>
                        <li>
                            <a data-target="#tab-12" data-type="date" data-chart="#tab-12-canvas" data-table="#tab-12-table">
                                <span>요일별</span>
                                <i aria-hidden="true" class="fa fa-caret-down"></i>
                            </a>
                        </li>
                        <li>
                            <a data-target="#tab-13" data-type="time" data-chart="#tab-13-canvas" data-table="#tab-13-table">
                                <span>시간별</span>
                                <i aria-hidden="true" class="fa fa-caret-down"></i>
                            </a>
                        </li>
                    </ul>

                    <div class="tab-content">
                        <div id="tab-11" class="tab-10 tab-pane active">
                            <div class="content-top">
                                <div class="header-tab">
                                    <div class="navigator-bar">
                                        <ul>
                                            <li><a href="#">통계 리포트</a><i class="fa fa-angle-right"></i></li>
                                            <li><a href="#">기간별 통계</a></li>
                                        </ul>
                                    </div>
                                    <div class="block-search-tab">
                                        <ul class="type-filter" id="tab-11-method">
                                            <li class="active">
                                                <a class="btnMethod" data-method="day">일간</a>
                                            </li>
                                            <li>
                                                <a class="btnMethod" data-method="week">주간</a>
                                            </li>
                                            <li>
                                                <a class="btnMethod" data-method="month">월간</a>
                                            </li>
                                        </ul>
                                    </div>
                                    <div class="block-search-time">
                                        <div class="block-search-date" id="day">
                                            <div class="item icon-next datepicker-item">
                                                <input id="tab-11-fromTime" name="tab-11-fromTime" type="text" class="form-control picker-day"/>
                                            </div>
                                            <div class="item datepicker-item">
                                                <input id="tab-11-toTime" name="tab-11-toTime" type="text" class="form-control picker-day"/>
                                            </div>
                                        </div>
                                        <div class="block-search-date style2" id="week" style="display:none">
                                            <div class="item">
                                                <select name="year" title="성명" class="form-control selectpickermember">
                                                    <c:forEach items="${years}" var="item">
                                                        <option value="${item}" ${item eq currentYear ? 'selected' : ''}>${item}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="item">
                                                <select name="fromWeek" title="성명" class="form-control selectpickermember trigger-change">

                                                </select>
                                            </div>
                                            <div class="item">
                                                <select name="toWeek" title="성명" class="form-control selectpickermember trigger-change">

                                                </select>
                                            </div>
                                        </div>
                                        <div class="block-search-date style2" id="month" style="display:none">
                                            <div class="item">
                                                <select name="year" title="성명" class="form-control selectpickermember">
                                                    <c:forEach items="${years}" var="item">
                                                        <option value="${item}" ${item eq currentYear ? 'selected' : ''}>${item}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="item">
                                                <select name="fromMonth" title="성명" class="form-control selectpickermember trigger-change">
                                                    <c:forEach begin="1" end="12" step="1" var="item">
                                                        <option value="${item}" ${item eq currentMonth ? 'selected' : ''}>${item}월</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                            <div class="item">
                                                <select name="toMonth" title="성명" class="form-control selectpickermember trigger-change">
                                                    <c:forEach begin="1" end="12" step="1" var="item">
                                                        <option value="${item}" ${item eq currentMonth ? 'selected' : ''}>${item}월</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="padding-left: 90px; padding-right: 90px;position: relative;" class="main-content" id="tab-11-chart">

                                    <canvas id="tab-11-canvas" width="1000" height="400"></canvas>
                                    <div class="chart-legend"></div>

                                </div>
                                <div style="padding-left: 90px; padding-right: 90px;" class="main-content">
                                    <div class="table-wrapper">
                                        <div class="table-responsive">
                                            <table class="table table-striped table-hover text-center table-border">
                                                <thead>
                                                    <tr>
                                                        <th>날짜</th>
                                                        <th>예약수</th>
                                                        <th>상담수</th>
                                                        <th>상담 시간(분)</th>
                                                        <th>상담 금액(원)</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="tab-11-table">

                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="btn-control-report download-report">
                                            <button class="btn btnExcel">엑셀 다운로드</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div id="tab-12" class="tab-10 tab-pane">
                            <div class="content-top">
                                <div class="header-tab" style="padding-bottom: 20px;">
                                    <div class="navigator-bar">
                                        <ul>
                                            <li><a href="#">통계 리포트</a><i class="fa fa-angle-right"></i></li>
                                            <li><a href="#">요일별 통계</a></li>
                                        </ul>
                                    </div>
                                    <div class="block-search-time">
                                        <div class="block-search-date">
                                            <div class="item icon-next datepicker-item">
                                                <input id="tab-12-fromTime" name="tab-12-fromTime" type="text" class="form-control picker-day"/>
                                            </div>
                                            <div class="item datepicker-item">
                                                <input id="tab-12-toTime" name="tab-12-toTime" type="text" class="form-control picker-day"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div style="padding-left: 90px; padding-right: 90px;position: relative;" class="main-content">

                                    <canvas id="tab-12-canvas" width="1000" height="400"></canvas>
                                    <div class="chart-legend"></div>

                                </div>
                                <div style="padding-left: 90px; padding-right: 90px;" class="main-content">
                                    <div class="table-wrapper">
                                        <div class="table-responsive">
                                            <table class="table table-striped table-hover text-center table-border">
                                                <thead>
                                                    <tr>
                                                        <th>요일</th>
                                                        <th>예약수</th>
                                                        <th>상담수</th>
                                                        <th>상담 시간(분)</th>
                                                        <th>상담 금액(원)</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="tab-12-table">

                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="btn-control-report download-report">
                                            <button class="btn btnExcel">엑셀 다운로드</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div id="tab-13" class="tab-10 tab-pane">
                            <div class="content-top">
                                <div class="header-tab header-tab-13">
                                    <div class="navigator-bar">
                                        <ul>
                                            <li><a href="#">통계 리포트</a><i class="fa fa-angle-right"></i></li>
                                            <li><a href="#">시간별 통계</a></li>
                                        </ul>
                                    </div>
                                    <div class="block-search-right"><span class="note-search">※ 최근 30일까지만 조회 가능합니다.</span>
                                        <div class="item datepicker-item">
                                            <input id="tab-13-fromTime" name="tab-13-fromTime" type="text" class="form-control picker-day"/>
                                        </div>
                                    </div>
                                </div>
                                <div style="padding-left: 90px; padding-right: 90px;position: relative;" class="main-content">

                                    <canvas id="tab-13-canvas" width="1000" height="400"></canvas>
                                    <div class="chart-legend"></div>

                                </div>
                                <div style="padding-left: 90px; padding-right: 90px;" class="main-content">
                                    <div class="table-wrapper">
                                        <div class="table-responsive">
                                            <table class="table table-striped table-hover text-center table-border">
                                                <thead>
                                                    <tr>
                                                        <th>시간</th>
                                                        <th>예약수</th>
                                                        <th>상담수</th>
                                                        <th>상담 시간(분)</th>
                                                        <th>상담 금액(원)</th>
                                                    </tr>
                                                </thead>
                                                <tbody id="tab-13-table">

                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="btn-control-report download-report">
                                            <button class="btn btnExcel">엑셀 다운로드 </button>
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
</div>

<div id="popup_date_filter_error" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>일별 조회는 최대 60일만 가능합니다.</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_week_filter_error" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>주별 조회는 최대 8주만 가능합니다.</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<div id="popup_month_filter_error" tabindex="-1" aria-hidden="true" data-backdrop="static" data-keyboard="false" class="modal fade popup-facetalk">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4>주의</h4>
                <button type="button" data-dismiss="modal" aria-hidden="true" class="close-face"></button>
            </div>
            <div class="modal-body">
                <strong>월별 조회는 최대 6개월만 가능합니다.</strong>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn close-face-popup popup_accept" data-dismiss="modal">확인</button>
            </div>
        </div>
    </div>
</div>

<form id="form_excel" method="post" action="/auth/report/excel" style="display:none">
    <input id="headers" name="headers" />
    <input id="bodies" name="bodies" />
</form>

<script type="text/javascript">
    $(document).ready(function() {

        var DATE_FORMAT = 'YYYY/MM/DD';
        var CHART;

        /************************************************************************
        *
        * Trigger change week
        *
        ************************************************************************/
        $('#week').on('change', '[name=year]', function(evt) {
            var year = $(this).val();
            var totalWeeks  = moment().weeksInYear();
            var currWeek    = moment().week();
            var html = '';
            for(var i = 1, n = Number.parseInt(totalWeeks); i <= n; i++) {
                var firstDayOfWeek  = moment().week(i).day(1).format('MM/DD');
                var lastDayOfWeek   = moment().week(i).day(7).format('MM/DD');

                html += '<option value="' + i + '" ' + (currWeek == i ? 'selected' : '') + ' data-from="' + year + '/' + firstDayOfWeek + '" data-to="' + year + '/' + lastDayOfWeek + '">';
                html += 'W' + i + ' (' + firstDayOfWeek + ' ~ ' + lastDayOfWeek + ')' + '</option>';
            }

            $('#week').find('[name=fromWeek]').html(html);
            $('#week').find('[name=toWeek]').html(html);
            $('#week').find('.selectpickermember').selectpicker('refresh');

        });

        /************************************************************************
        *
        * Download excel
        *
        ************************************************************************/
        $('.btnExcel').on('click', function(evt) {
            // generate data from table
            var $table  = $(this).closest('.tab-pane').find('table');
            var headers = [];
            var bodies  = [];

            $table.find('thead th').each(function(index, item) {
                headers.push($(item).text().trim());
            });
            $table.find('tbody tr').each(function(index, item) {
                var body = [];
                $(item).find('td').each(function(index, item) {
                     body.push($(item).text().trim());
                });
                bodies.push(body.join(':'));
            });

            // submit form
            var $form = $('#form_excel');
            $form.find('#headers').val(headers);
            $form.find('#bodies').val(bodies.join(';'));

            $form.submit();
        });


        /************************************************************************
        *
        * Main menu
        *
        ************************************************************************/
        $('#main_tab').on('click', 'a', function(evt) {
            evt.preventDefault();
            $(this).tab('show');

            var data = {};

            var type = $(this).attr('data-type');
            data.type = type;
            if (type == 'period') {
                var method = $('#tab-11-method').find('li.active').find('a').attr('data-method');
                data.method = method;
                if (method == 'day') {
                    data.fromTime   = $('#tab-11-fromTime').val();
                    data.toTime     = $('#tab-11-toTime').val();
                } else if (method == 'week') {
                    data.fromTime   = $('#week [name=fromWeek]').find('option:selected').attr('data-from');
                    data.toTime     = $('#week [name=toWeek]').find('option:selected').attr('data-to');
                } else {
                    data.fromTime   = $('#month [name=year]').val() + '/' + $('#month [name=fromMonth]').val() + '/01';
                    data.toTime     = $('#month [name=year]').val() + '/' + $('#month [name=toMonth]').val() + '/31';
                }
            } else if (type == 'date') {
                data.fromTime   = $('#tab-12-fromTime').val();
                data.toTime     = $('#tab-12-toTime').val();
            } else {
                data.fromTime   = $('#tab-13-fromTime').val();
                data.toTime     = $('#tab-13-fromTime').val();
            }

            // init canvas
            var chartId     = $(this).attr('data-chart');
            var tableId     = $(this).attr('data-table');

            fetchData(data, function(err, res) {

                // draw chart
                drawchart(chartId, res);

                res = convertData(res, chartId);

                // draw table
                drawtable(tableId, res);
            });

        });

        /************************************************************************
        *
        * Sub menu
        *
        ************************************************************************/
        $('#tab-11-method').on('click', '.btnMethod', function(evt) {
            evt.preventDefault();

            var $caller     = $('#main_tab li:first a');
            var type        = $('#main_tab li:first a').attr('data-type');
            var method      = $(this).attr('data-method');
            var $activeLi   = $(this).closest('li');

            // hide and show date inputs
            $('#tab-11-method').find('li').removeClass('active');
            $activeLi.addClass('active');

            $('.block-search-time').find('.block-search-date').css('display', 'none');
            $('.block-search-time').find('#' + method).css('display','');

            // fetch data and draw
            var data = {};
            data.type   = type;
            data.method = method;

            if (method == 'day') {
                data.fromTime   = $('#tab-11-fromTime').val();
                data.toTime     = $('#tab-11-toTime').val();
            } else if (method == 'week') {
                data.fromTime   = $('#week [name=fromWeek]').find('option:selected').attr('data-from');
                data.toTime     = $('#week [name=toWeek]').find('option:selected').attr('data-to');
            } else {
                data.fromTime   = $('#month [name=year]').val() + '/' + $('#month [name=fromMonth]').val() + '/01';
                data.toTime     = $('#month [name=year]').val() + '/' + $('#month [name=toMonth]').val() + '/31';
            }

            // init canvas
            var chartId     = $caller.attr('data-chart');
            var tableId     = $caller.attr('data-table');

            fetchData(data, function(err, res) {
                // draw chart
                drawchart(chartId, res);

                res = convertData(res, chartId);

                // draw table
                drawtable(tableId, res);
            });

        });

        function convertData(res, chartId) {
            var resConverted = {}
            if (chartId == '#tab-13-canvas') {
                _.each(res, function(item, key) {
                    var _key    = Number.parseInt(key);
                    var upper   = _key + 1;
                    var newKey  = (_key < 10 ? '0' + _key : _key) + ':00 ~ ' + (upper < 10 ? '0' + upper : upper) + ':00'
                    resConverted[newKey] = item;
                })
            } else if (chartId == '#tab-11-canvas') {
                var newKeys = _.keys(res).reverse();
                for (var i = 0, n = newKeys.length; i < n; i++) {
                    var key     = newKeys[i];
                    if (key.indexOf('~') > -1) {
                        var keys    = key.split(' ~ ');
                        var year    = $('#tab-11').find('[name=year]').val();
                        var newKey  = year + '-' + keys[0] + ' ~ ' + year + '-' + keys[1];
                        resConverted[newKey]    = res[key];
                    } else {
                        resConverted[newKeys[i]] = res[newKeys[i]];
                    }
                }
            } else {
                resConverted = res;
            }

            return resConverted;
        }

        function init() {

            // init date inputs
            var _moment = moment();

            $('#tab-11-fromTime').val(moment().day(1).format(DATE_FORMAT));
            $('#tab-11-toTime').val(_moment.format(DATE_FORMAT));

            $('#tab-12-fromTime').val(moment().day(1).format(DATE_FORMAT));
            $('#tab-12-toTime').val(_moment.format(DATE_FORMAT));

            $('#tab-13-fromTime').val(_moment.format(DATE_FORMAT));

            $('#week').find('[name=year]').trigger('change');

            // init date picker
            $('.picker-day').datepicker({
                format      : 'yyyy/mm/dd',
                language    : 'kr',
                autoclose   : true
            });

            // init data
            $('#main_tab a[data-target=#tab-11]').trigger('click');
        }

        init();

        /************************************************************************
        *
        * Trigger filter
        *
        ************************************************************************/
        $('#tab-11').on('change', '.trigger-change', function(evt) {
            var $this       = $(this);
            var target      = $(this).closest('.block-search-date').attr('id');
            var thisName    = $this.attr('name');
            var isValid     = false;
            var popup       = 'popup_week_filter_error';
            var currentTime = $this.val();

            if (thisName == undefined) return false;

            if ('week' == target) {
                popup       = 'popup_week_filter_error';
                if ('fromWeek' == thisName) {
                    var upperLimit = $('#week').find('[name=toWeek]').val();
                    isValid = (upperLimit - currentTime) < 8;
                } else {
                    var lowerLimit = $('#week').find('[name=fromWeek]').val();
                    isValid = (currentTime - lowerLimit) < 8;
                }
            } else {
                popup       = 'popup_month_filter_error';
                if ('fromMonth' == thisName) {
                    var upperLimit = $('#month').find('[name=toMonth]').val();
                    isValid = (upperLimit - currentTime) < 6;
                } else {
                    var lowerLimit = $('#month').find('[name=fromMonth]').val();
                    isValid = (currentTime - lowerLimit) < 6;
                }
            }

            if (isValid) {
                $('#tab-11-method').find('a[data-method=' + target + ']').trigger('click');
            } else {
                $('#' + popup).modal('show');
            }
        })

        $('#tab-11 .picker-day').datepicker().on('changeDate', function(evt) {
            var $this       = $(this);
            var thisId      = $this.attr('id');
            var deltaDate   = 0;
            var currentDate = $this.val();

            if (thisId == undefined) return false;

            if ('tab-11-fromTime' == thisId) {
                var upperLimit = $('#tab-11-toTime').val();
                delta = moment(upperLimit, DATE_FORMAT).dayOfYear() - moment(currentDate, DATE_FORMAT).dayOfYear();
            } else {
                var lowerLimit = $('#tab-11-fromTime').val();
                delta = moment(currentDate, DATE_FORMAT).dayOfYear() - moment(lowerLimit, DATE_FORMAT).dayOfYear();
            }

            if (delta >= 60) {
                $('#popup_date_filter_error').modal('show');
            } else {
                $('#tab-11-method').find('a[data-method=day]').trigger('click');
            }
        });
        $('#tab-12 .picker-day').datepicker().on('changeDate', function(evt) {
            $('#main_tab').find('a[data-target=#tab-12]').trigger('click');
        });
        $('#tab-13 .picker-day').datepicker().on('changeDate', function(evt) {
            $('#main_tab').find('a[data-target=#tab-13]').trigger('click');
        });

        function fetchData(data, callback) {
            $
                .ajax({
                    url     : '/auth/report/statistics/data',
                    method  : 'get',
                    data    : data,
                    beforeSend: toggleLoading
                })
                .then(function(res) {
                    console.log('fetch data', res);
                    toggleLoading();
                    callback(null, res);
                }, function(err) {
                    toggleLoading();
                    callback(err, null);
                });
        }

        function drawchart(chartId, data) {
            var ctx     = $(chartId);
            var $legend = ctx.next('.chart-legend');
            var _labels = [];
            var _dataReservation = [];
            var _dataCounseled      = [];

            var datasetReservation      = {
                label : '예약수',
                lineTension: 0.1,
                backgroundColor: "#fff",
                borderColor: "#8191a1",
                fontColor: "#8191a1",
                borderCapStyle: 'butt',
                borderDash: [],
                borderDashOffset: 0.0,
                borderJoinStyle: 'miter',
                pointBorderColor: "#8191a1",
                pointBackgroundColor: "#8191a1",
                pointBorderWidth: 1,
                pointHoverRadius: 5,
                pointHoverBackgroundColor: "#8191a1",
                pointHoverBorderColor: "#8191a1",
                pointHoverBorderWidth: 0,
                pointRadius: 1,
                pointHitRadius: 5
            };

            var datasetCounseled        = {
                label : '상담수',
                lineTension: 0.1,
                backgroundColor: "#fff",
                borderColor: "#50b7f0",
                fontColor: "#50b7f0",
                borderCapStyle: 'butt',
                borderDash: [],
                borderDashOffset: 0.0,
                borderJoinStyle: 'miter',
                pointBorderColor: "#50b7f0",
                pointBackgroundColor: "#50b7f0",
                pointBorderWidth: 1,
                pointHoverRadius: 5,
                pointHoverBackgroundColor: "#50b7f0",
                pointHoverBorderColor: "#50b7f0",
                pointHoverBorderWidth: 0,
                pointRadius: 1,
                pointHitRadius: 5
            }


            _.each(data, function(item, key) {
                _labels.push(key);
                _dataReservation.push(item.reservation);
                _dataCounseled.push(item.counseled);
            });

            datasetReservation.data     = _dataReservation;
            datasetCounseled.data       = _dataCounseled;

            if (CHART) {
                CHART.destroy();
            }

            CHART = new Chart(ctx, {
                type: 'line',
                labels: _labels,
                data: {
                    labels: _labels,
                    datasets:
                    [
                        datasetReservation,
                        datasetCounseled
                    ]
                },
                options: {
                    scales: {
                        xAxes: [{
                            gridLines: {
                                display:false
                            }
                        }],
                        yAxes: [{
                            gridLines: {
                                display:false
                            }
                        }]
                    }
                }
            });

            $legend.html(CHART.generateLegend());
        }

        function drawtable(tableId, data) {
            var html = '';
            var totalReservation    = 0;
            var totalCounseled      = 0;
            var totalTime           = 0;
            var totalMoney          = 0;

            _.each(data, function(item, key) {

                totalReservation    += Number.parseInt(item.reservation);
                totalCounseled      += Number.parseInt(item.counseled);
                totalTime           += Number.parseInt(item.time);
                totalMoney          += Number.parseInt(item.money);

                html += '<tr>';
                html +=     '<td>' + key + '</td>';
                html +=     '<td>' + item.reservation + '</td>';
                html +=     '<td>' + item.counseled + '</td>';
                html +=     '<td>' + item.time + '</td>';
                html +=     '<td>' + formatNumber(item.money) + '</td>';
                html += '</tr>';
            });

            var summaryHtml = '<tr>';
            summaryHtml     +=     '<td>합계</td>';
            summaryHtml     +=     '<td>' + totalReservation + '</td>';
            summaryHtml     +=     '<td>' + totalCounseled + '</td>';
            summaryHtml     +=     '<td>' + totalTime + '</td>';
            summaryHtml     +=     '<td>' + formatNumber(totalMoney) + '</td>';
            summaryHtml     += '</tr>';

            $(tableId).html(summaryHtml + html);
        }

    });

</script>