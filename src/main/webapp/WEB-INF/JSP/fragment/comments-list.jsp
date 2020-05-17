<%--
  Created by IntelliJ IDEA.
  User: Artsiom
  Date: 04.05.2020
  Time: 21:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt"    uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:choose>
    <c:when test="${USER}">
        <c:set var="comments" value="${USER.comments}"/>
    </c:when>
    <c:otherwise>
        <c:set var="comments" value="${MOVIE.comments}"/>
    </c:otherwise>
</c:choose>
<c:forEach var="comment" items="${comments}">
    <li class="media py-3">
        <c:choose>
            <c:when test="${USER}">
                <img src="${comment.user.imageLink}" class="align-self-start mr-3" alt="${comment.user.account.name}">
            </c:when>
            <c:otherwise>
                <img src="${comment.movie.imageLink}" class="align-self-start mr-3" alt="${comment.movie.name}">
            </c:otherwise>
        </c:choose>
        <div class="media-body">
            <c:choose>
                <c:when test="${USER}">
                    <h5 class="d-inline mt-0 mb-1"><a href="#">${comment.user.account.name}</a></h5>
                </c:when>
                <c:otherwise>
                    <h5 class="d-inline mt-0 mb-1"><a href="#">${comment.movie.name}</a></h5>
                </c:otherwise>
            </c:choose>
            <p class="text-justify d-block">${comment.content}
            </p>
            <p class="d-inline text-muted">Rating </p>
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

