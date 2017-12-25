<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<c:if test="${totalPages > 0}">
	 <div class="rw-pagination">
		<c:url var="firstUrl" value="${url}?page=1&amp;${param_url}" />
		<c:url var="lastUrl" value="${url}?page=${totalPages}&amp;${param_url}" />
		<c:url var="prevUrl" value="${url}?page=${currentIndex - 1}&amp;${param_url}" />
		<c:url var="nextUrl" value="${url}?page=${currentIndex + 1}&amp;${param_url}" />
			<ul class="pagination">
				<c:choose>
					<c:when test="${currentIndex == 1}">
						<li class="disabled">
							<a ><span>이전</span></a><jsp:text/>
						</li>
					</c:when>
					<c:otherwise>
						<li>
							<a href="${prevUrl}"><span>이전</span><jsp:text/></a><jsp:text/>
						</li>
					</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${beginIndex}" end="${endIndex}">
					<c:url var="pageUrl" value="${url}?page=${i}&amp;${param_url}" />
					<c:choose>
						<c:when test="${i == currentIndex}">
                         	<li class="active"><a><c:out value="${i}" /></a><jsp:text/></li>
						</c:when>
						<c:otherwise>
							<li><a href="${pageUrl}"><c:out value="${i}"/></a><jsp:text/></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${currentIndex == totalPages}">
						<li class="disable"><a><span>다음</span></a><jsp:text/></li>
					</c:when>
					<c:otherwise>
						<li ><a href="${nextUrl}"><span>다음</span><jsp:text/></a><jsp:text/></li>
					</c:otherwise>
				</c:choose>
			</ul>
	</div>
	</c:if>
	
	<c:if test="${totalPages == 0}">
	 <div class="rw-pagination">
		<c:url var="firstUrl" value="" />
		<c:url var="lastUrl" value="" />
		<c:url var="prevUrl" value="" />
		<c:url var="nextUrl" value="" />
			<ul class="pagination">
					<c:url var="pageUrl" value="" />
					<li class="disable"><a><span>이전</span></a><jsp:text/></li>
					<li class="active"><a><c:out value="1" /></a><jsp:text/></li>
					<li class="disable"><a><span>다음</span></a><jsp:text/></li>
			</ul>
		</div>
	</c:if>

