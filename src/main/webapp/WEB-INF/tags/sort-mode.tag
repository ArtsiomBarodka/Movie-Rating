<%@ tag import="epam.my.project.configuration.SortMode" %>
<%@tag pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ attribute name="showTopColumn" required="false" type="java.lang.Boolean"%>

<fmt:setLocale value="${LOCALE}"/>
<fmt:setBundle basename="i18n/messages"/>
<c:set var="rating" value="<%=SortMode.RATING.name().toLowerCase()%>"/>
<c:set var="added" value="<%=SortMode.ADDED.name().toLowerCase()%>"/>
<c:set var="fees" value="<%=SortMode.FEES.name().toLowerCase()%>"/>
<c:set var="budget" value="<%=SortMode.BUDGET.name().toLowerCase()%>"/>
<c:set var="duration" value="<%=SortMode.DURATION.name().toLowerCase()%>"/>


<div id="sort-by">
    <div class="dropdown">
        <button class="btn btn-dark dropdown-toggle" type="button" id="sort-by-dropdown" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false"><fmt:message key="tag.sort-mode.submit"/></button>
        <div class="dropdown-menu" aria-labelledby="sort-by-dropdown">
            <c:choose>
                <c:when test="${showTopColumn}">
                    <a href="${pageContext.servletContext.contextPath}?sort=${rating}" class="dropdown-item ${SORT_MODE == rating ? "active" : ""}" type="button"><fmt:message key="tag.sort-mode.rating"/></a>
                    <a href="${pageContext.servletContext.contextPath}?sort=${fees}" class="dropdown-item ${SORT_MODE == fees ? "active" : ""}" type="button"><fmt:message key="tag.sort-mode.fees"/></a>
                    <a href="${pageContext.servletContext.contextPath}?sort=${budget}" class="dropdown-item ${SORT_MODE == budget ? "active" : ""}" type="button"><fmt:message key="tag.sort-mode.budget"/></a>
                    <a href="${pageContext.servletContext.contextPath}?sort=${duration}" class="dropdown-item ${SORT_MODE == duration ? "active" : ""}" type="button"><fmt:message key="tag.sort-mode.duration"/></a>
                </c:when>
                <c:otherwise>
                    <a href="${pageContext.servletContext.contextPath}?${QUERY}sort=${rating}&pageable=${PAGEABLE}" class="dropdown-item ${SORT_MODE == rating ? "active" : ""}" type="button"><fmt:message key="tag.sort-mode.rating"/></a>
                    <a href="${pageContext.servletContext.contextPath}?${QUERY}sort=${added}&pageable=${PAGEABLE}" class="dropdown-item ${SORT_MODE == added ? "active" : ""}" type="button"><fmt:message key="tag.sort-mode.added"/></a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>