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

<div class="member page-content-wrapper counseling-history report-profile">
    <div class="page-title">
        <div class="title">
            <span>리포트</span>
        </div>
        <div class="title-ct">
            <span>정산 리포트</span>
        </div>
    </div>
    <div class="page-content">
        <div class="container">
            <div class="main-content border-full bg-fff">
                <ul class="block-info">
                    <li>아래의 지급 금액은 상담금액에서 부가세(10%) 및 소득세(3.3%)가 공제된 금액입니다.</li>
                    <li>정산 금액은 해당월(M) 기준 M+1 말일에 입력하신 계좌 정보로 일괄 지급해 드립니다.</li>
                    <li>정산 관련하여 궁금하신 사항은 고객센터 > 문의하기를 이용해주시기 바랍니다.</li>
                </ul>
                <div class="block-info-item">
                    <span class="title">계좌 정보</span>
                    <div class="list-value">
                        <span class="item-value">${model.accountBankName}</span>
                        <span class="item-value">계좌번호 : ${model.accountBankNumber}</span>
                        <span class="item-value">예금주 : ${model.accountBankHolder}</span>
                    </div>
                    <div class="btn-control-report edit-report">
                        <button class="btn" onclick="location.href='/auth/info/detail#tab-11'">계좌정보 수정</button>
                    </div>
                </div>
            </div>
            <div class="main-content border-full bg-fff">
                <div class="content-main">
                    <div class="main-top block-header"><span class="title">정산 요약</span>
                        <form id="form_filter">
                            <select name="month" class="form-control selectpickermember pull-right">
                                <c:forEach begin="1" end="12" step="1" var="item">
                                    <c:choose>
                                        <c:when test="${not empty model.month}">
                                            <option value="${item}" ${item eq model.month ? 'selected' : ''}>${item} 월</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${item}" ${item eq currentMonth ? 'selected' : ''}>${item} 월</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                            <select name="year" class="form-control selectpickermember pull-right">
                                <c:forEach items="${years}" var="item">
                                    <c:choose>
                                        <c:when test="${not empty model.year}">
                                            <option value="${item}" ${item eq model.year ? 'selected' : ''}>${item} 년</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option value="${item}" ${item eq currentYear ? 'selected' : ''}>${item} 년</option>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>
                            </select>
                        </form>
                    </div>

                    <div class="main-ct">
                        <div class="table-wrapper">
                            <div class="table-responsive">
                                <table class="table table-striped table-hover text-center table-border">
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th>예치금 사용</th>
                                            <th>상담권 사용</th>
                                            <th>합계</th>
                                            <th>수익 분배율</th>
                                            <th>지급 금액</th>
                                            <th>상태</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%--
                                        <c:if test="${not empty model.profit}">
                                            <tr>
                                                <td>${model.month}월</td>
                                                <td>
                                                    <fmt:formatNumber value="${model.profit.coin}" type="number" />
                                                </td>
                                                <td>
                                                    <fmt:formatNumber value="${model.profit.voucher}" type="number" />
                                                </td>
                                                <td>
                                                    <fmt:formatNumber value="${model.profit.profit}" type="number" />
                                                </td>
                                                <td>${model.profit.percent}%</td>
                                                <td>
                                                    <fmt:formatNumber value="${model.profit.adminRefund}" type="number" />
                                                </td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${model.profit.status eq 1}">
                                                            <div class="text-blue">지급완료</div>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <div class="text-orange">미지급</div>
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                            </tr>
                                        </c:if>
                                        --%>

                                        <%-- Testing purpose --%>
                                        <c:set var="_totalCoin"     value="0" />
                                        <c:set var="_totalVoucher"  value="0" />
                                        <c:set var="_total"  value="0" />
                                        <fmt:formatNumber value="${tax_vat}" type="number" var="VAT_TAX"/>
                                        <fmt:formatNumber value="${tax_profit}" type="number" pattern="#.###"  var="PROFIT_TAX"/>
                                        <c:forEach items="${model.paymentHistories}" var="item" varStatus="index">
                                        	<c:set value="${item.coin * (1-VAT_TAX)}" var="number"></c:set>
                                            <c:set value="${(number - (number % 1000) + (number % 1000 == 0 ? 0 : 1000))}" var="coin"></c:set>
                                            <c:choose>
                                                <c:when test="${item.typeCoin eq 1}">
                                                    <c:set var="_totalCoin"     value="${_totalCoin + coin}" />
                                                </c:when>
                                                <c:otherwise>
                                                    <c:set var="_totalVoucher" value="${_totalVoucher + coin}" />
                                                </c:otherwise>
                                            </c:choose>
                                            <c:set var="_total"     value="${_total + coin}" />
                                        </c:forEach>
                                        <tr>
                                        	
                                            <td><span id="_month"></span>월</td>
                                            <td>
                                                <fmt:formatNumber value="${_totalCoin}" type="number"  maxFractionDigits="3"/>
                                            </td>
                                            <td>
                                                <fmt:formatNumber value="${_totalVoucher }" type="number" />
                                            </td>
                                            <td>
                                                <fmt:formatNumber value="${ _total }" type="number" />
                                            </td>
                                            <td>${percent}%</td>
                                            <td>
                                            	<c:set value="${ _total * percent/100 * (1 - PROFIT_TAX) }" var="adminRefund"></c:set>
                                                <fmt:formatNumber value="${adminRefund - adminRefund % 1000 + (adminRefund % 1000 ==0?0:1000)}" type="number" maxFractionDigits="3"  />
                                                
                                            </td>
                                            <td>
                                               	 <c:choose>
                                               	 	<c:when test="${profit_status == 1 }">
                                               	 		지급완료
                                               	 	</c:when>
                                               	 	<c:otherwise>
                                               	 		<span style="color: red">미지급</span>
                                               	 	</c:otherwise>
                                               	 </c:choose>
                                            </td>
                                        </tr>

                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                    <div class="main-top block-header">
                        <span class="title">정산 내역</span>
                        <div class="btn-control-report download-report" style="margin-top: 0;">
                            <button class="btn btnExcel">엑셀 다운로드</button>
                        </div>
                    </div>
                    <div class="main-ct">
                        <div class="table-wrapper">
                            <div class="table-responsive">
                                <table class="table table-striped table-hover text-center table-border" id="tbl_profit">
                                    <thead>
                                        <tr>
                                            <th>번호</th>
                                            <th>회원명</th>
                                            <th>상담 일시</th>
                                            <th>결제 구분</th>
                                            <th>금액</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="totalCoin" value="0" />
                                        <c:forEach items="${model.paymentHistories}" var="item" varStatus="index">
                                            
                                            <tr>
                                                <td>${index.index + 1}</td>
                                                <td>${item.user.fullname}</td>
                                                <td>
                                                    <fmt:formatDate value="${item.createDate}" pattern="yyyy-MM-dd HH:mm:ss" />
                                                </td>
                                                <td>
                                                    <c:choose>
                                                        <c:when test="${item.typeCoin eq 1}">
                                                            예치금 사용
                                                        </c:when>
                                                        <c:otherwise>
                                                            상담권 사용
                                                        </c:otherwise>
                                                    </c:choose>
                                                </td>
                                                <td>
                                                    <c:set value="${item.coin * (1-VAT_TAX)}" var="number"></c:set>
                                                    <c:set value="${(number - (number % 1000) + (number % 1000 == 0 ? 0 : 1000))}" var="coin"></c:set>
                                                    <fmt:formatNumber value="${coin }" type="number" maxFractionDigits="3"/>
                                                </td>
                                            </tr>
                                            <c:set var="totalCoin" value="${totalCoin + coin}" />
                                        </c:forEach>
                                    </tbody>
                                </table>


                                <div class="total-price">
                                    <div class="title"> 총  정산 금액</div>
                                    <div class="price">
                                        <fmt:formatNumber value="${totalCoin}" type="number" /> 원
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

<form id="form_excel" method="post" action="/auth/report/excel" style="display:none">
    <input id="headers" name="headers" />
    <input id="bodies" name="bodies" />
</form>

<script>
    $('#form_filter').on('change', 'select', function() {
        $(this).closest('form').submit();
    });

    /************************************************************************
    *
    * Download excel
    *
    ************************************************************************/
    $('.btnExcel').on('click', function(evt) {
        // generate data from table
        var $table  = $('#tbl_profit');
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

    $(document).ready(function() {

        $('#_month').text($('[name=month]').val());

    });
</script>