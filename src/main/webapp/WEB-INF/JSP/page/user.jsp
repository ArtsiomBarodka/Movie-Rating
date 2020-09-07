<%@ page import="com.epam.mrating.configuration.SecurityConfiguration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<c:set var="admin" value="<%=SecurityConfiguration.ROLE_ADMIN%>"/>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>

<fmt:setLocale value="${LOCALE}"/>
<fmt:setBundle basename="i18n/messages"/>

<main id="movie-page" class="container-fluid">
    <section class="media row justify-content-center my-4">
        <img src="${USER.imageLink}" class="col-3 mr-3" alt="${USER.account.name}">
        <div class="media-body col-8">
            <h3 class="d-inline mt-0 mb-1">${USER.account.name}</h3>
            <div id="movie-rating" class="d-inline-flex align-items-center rating-field mt-3 mb-1" >
                <div class="badge badge-pill badge-warning raiting-number">${USER.rating}</div>
                    <c:if test="${CURRENT_ACCOUNT_DETAILS.id == USER.account.id || CURRENT_ACCOUNT_DETAILS.role == admin}">
                        <div id="edit" class="ml-3">
                            <a href="/app/user/edit/${USER.uid}" class="btn btn-light"><fmt:message key="user.edit.submit"/></a>
                        </div>
                    </c:if>
                <c:if test="${CURRENT_ACCOUNT_DETAILS.id == USER.account.id}">
                    <div id="edit" class="ml-3">
                        <a href="${contextPath}/app/user/delete" class="btn btn-light"><fmt:message key="user.delete.button"/></a>
                    </div>
                </c:if>
            </div>
            <p>
                <div><fmt:message key="user.created.label"/> : <span><fmt:formatDate value="${USER.created}" pattern="dd.MM.yyyy"/></span></div>
                <div><fmt:message key="user.banned.label2"/> : <span>
                    <c:choose>
                        <c:when test="${USER.banned}">
                            <fmt:message key="user.banned.true"/>
                        </c:when>
                        <c:otherwise>
                            <fmt:message key="user.banned.false"/>
                        </c:otherwise>
                    </c:choose>
                </span></div>
                <div><fmt:message key="user.rating.label"/> : <span>${USER.rating}</span></div>
                <div><fmt:message key="user.comments.label"/> : <span>${TOTAL_COMMENTS_COUNT}</span></div>
            </p>
        </div>
    </section>
    <section id="comments" class="row justify-content-center" data-page-count="${PAGE_COUNT}" data-page-number="1">
        <ul id="comments-user-list" class="list-unstyled col-10">
            <h4 class="d-flex justify-content-center"><fmt:message key="comment.title"/></h4>
            <div class="alert alert-light" role="alert">
                <fmt:message key="comments.found.message.before"/> ${TOTAL_COMMENTS_COUNT} <fmt:message key="comments.found.message.after"/>
            </div>
            <jsp:include page="../fragment/comments-list.jsp"/>
        </ul>
    </section>
    <tags:pageable/>
</main>
