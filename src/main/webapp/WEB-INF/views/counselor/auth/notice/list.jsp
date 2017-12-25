<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/counselor/layout/include.jsp" %>

<div class="member page-content-wrapper counseling-history psychological" style="margin-bottom: 130px;">
        <div class="page-title">
          <div class="title"><span>고객센터</span></div>
          <div class="title-ct"> <span>공지사항</span></div>
        </div>
        <div class="page-content">
          <div class="container">
            <div class="main-content border-full bg-fff">
              <div class="content-main">
              	<form>
	                <div class="main-top"><span class="title">공지사항 리스트</span><span class="price">${totalElement}건</span>
	                  <select name="orderBy" id="btn-notice-sort" class="form-control selectpickermember pull-right">
	                    <option value="create_date" ${orderBy == 'create_date' ? 'selected' : ''}>최근 등록 순</option>
	                    <option value="view_number" ${orderBy == 'view_number' ? 'selected' : ''}>조회수 높은 순</option>
	                  </select>
	                </div>
                </form>
                <div class="main-ct">
                  <div class="table-wrapper">
                    <div class="table-responsive">
                      <table class="table table-striped table-hover text-center">
                        <thead>
                          <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성일</th>
                            <th>조회수</th>
                          </tr>
                        </thead>
                        <tbody>
                          <c:forEach items="${list}" var="n">
                          	<tr>
	                            <td>${n.id }</td>
	                            <td><a href="${pageContext.request.contextPath}/auth/notice/detail?id=${n.id }"> ${n.title }</a></td>
	                            <td><fmt:formatDate pattern="yyyy-MM-dd" value="${n.createDate }"></fmt:formatDate></td>
	                            <td>${n.viewNumber }</td>
                          	</tr>
                          </c:forEach>
                        </tbody>
                      </table>

                    </div>
                    
                   <jsp:include page="/WEB-INF/views/counselor/layout/pagination_custom.jsp">
                                <jsp:param name="currentIndex"             value="${currentIndex}"/>
                                <jsp:param name="url"              value="${pagination_navigator}"/>
                                <jsp:param name="totalPages"        value="${totalPages}"/>
                                <jsp:param name="param_url"     value="${param_url}"/>
                    </jsp:include>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

