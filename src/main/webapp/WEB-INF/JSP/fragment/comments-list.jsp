<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<fmt:setLocale value="${LOCALE}"/>
<fmt:setBundle basename="i18n/messages"/>



<c:set var="comments" value="${MOVIE != null ?  MOVIE.comments : USER.comments}"/>
<c:set var="contextPath" value="${pageContext.servletContext.contextPath}"/>

<c:forEach var="comment" items="${MOVIE != null ?  MOVIE.comments : USER.comments}">
    <li class="media py-3">
        <c:choose>
            <c:when test="${not empty MOVIE}">
                <img src="${comment.user.imageLink}" class="align-self-start mr-3" alt="${comment.user.account.name}">
            </c:when>
            <c:otherwise>
                <img src="${comment.movie.imageLink}" class="align-self-start mr-3" alt="${comment.movie.name}">
            </c:otherwise>
        </c:choose>
        <div class="media-body">
            <c:choose>
                <c:when test="${not empty MOVIE}">
                    <h5 class="d-inline mt-0 mb-1">
                        <a href="${contextPath}/app/user/${comment.user.uid}">${comment.user.account.name}</a>
                        <c:if test="${not empty CURRENT_ACCOUNT_DETAILS && CURRENT_ACCOUNT_DETAILS.id == comment.user.account.id}">
                            <a href="${contextPath}/app/comment/delete?id=${comment.id}&return=${contextPath}/app/movie/${MOVIE.uid}" class="float-right text-danger"><i class="fa fa-times" aria-hidden="true"></i></a>
                        </c:if>
                    </h5>
                </c:when>
                <c:otherwise>
                    <h5 class="d-inline mt-0 mb-1">
                        <a href="/app/movie/${comment.movie.uid}">${comment.movie.name}</a>
                        <c:if test="${not empty CURRENT_ACCOUNT_DETAILS && CURRENT_ACCOUNT_DETAILS.id == comment.user.account.id}">
                            <a href="${contextPath}/app/comment/delete?id=${comment.id}&return=${contextPath}/app/user/${USER.uid}" class="float-right text-danger"><i class="fa fa-times" aria-hidden="true"></i></a>
                        </c:if>
                    </h5>
                </c:otherwise>
            </c:choose>
            <p class="text-justify d-block">${comment.content}</p>
            <p class="d-inline text-muted"><fmt:message key="app.rating"/></p>
            <div class="d-inline-flex rating-field" data-raiting-value="${comment.rating}">
                <span class="fa fa-star mx-1" data-raiting="1"></span>
                <span class="fa fa-star mx-1" data-raiting="2"></span>
                <span class="fa fa-star mx-1" data-raiting="3"></span>
                <span class="fa fa-star mx-1" data-raiting="4"></span>
                <span class="fa fa-star mx-1" data-raiting="5"></span>
            </div>
            <p class="text-muted">
                <span><fmt:formatDate type="both"  dateStyle="medium" timeStyle="medium" value="${comment.created}"/></span>
            </p>
        </div>
    </li>
</c:forEach>