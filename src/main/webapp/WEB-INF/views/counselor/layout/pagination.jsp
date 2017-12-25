<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<div class="rw-pagination">
    <ul class="pagination">
        <c:if test="${not param.first}">
            <li>
                <a data-page="${param.number}"><span>이전</span></a>
            </li>
        </c:if>

        <c:if test="${param.number - 2 ge 0}">
            <li>
                <a data-page="${param.number - 1}">${param.number - 1}</a>
            </li>
        </c:if>
        <c:if test="${param.number - 1 ge 0}">
            <li>
                <a data-page="${param.number}">${param.number}</a>
            </li>
        </c:if>

        <li class="active">
            <a data-page="${param.number+1}">${param.number + 1}</a>
        </li>

        <c:if test="${param.totalPages - param.number gt 1}">
            <li>
                <a data-page="${param.number + 2}">${param.number + 2}</a>
            </li>
        </c:if>
        <c:if test="${param.totalPages - param.number gt 2}">
            <li>
                <a data-page="${param.number + 3}">${param.number + 3}</a>
            </li>
        </c:if>

        <c:if test="${not param.last}">
            <li>
                <a data-page="${param.number + 2}"><span>다음</span></a>
            </li>
        </c:if>
    </ul>
</div>

<script type="text/javascript">
    var PATTERN_PAGE = /page=\d*/i;
    $('.rw-pagination').find('li').not('active').on('click', 'a', function() {
        var page        = $(this).attr('data-page');
        var currentHref = window.location.href;

        if (currentHref.match(PATTERN_PAGE)) {
            currentHref = currentHref.replace(PATTERN_PAGE, 'page=' + encodeURIComponent(page));
        } else {
            if (location.search.length) {
                currentHref += '&page=' + encodeURIComponent(page);
            } else {
                currentHref += '?page=' + encodeURIComponent(page);
            }
        }

        window.location = currentHref;
    });
</script>